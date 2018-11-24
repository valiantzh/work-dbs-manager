package com.dcdzsoft.business.py;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 发送欠费通知 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSOwingReportSend extends ActionBean
{

    public int doBusiness(InParamPYSMSOwingReportSend in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.AccountID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		boolean isToDisable = false;//短信账户是否停用
		boolean isWarningFlag = false;//是否发送欠费通知短信
	    //账户信息
        PYSMSAccountDAO smsAccountDAO = daoFactory.getPYSMSAccountDAO();
        
        //修改告警通知的发送状态,以便再次发送
        int reminderDays = NumberUtils.parseInt(ControlParam.getInstance().getSendWarningPerDays());
        if(reminderDays<=0){
            reminderDays = 1;
        }
        JDBCFieldArray setCols00 = new JDBCFieldArray();
        JDBCFieldArray whereCols00 = new JDBCFieldArray();
        setCols00.add("WarningFlag", "0");//告警通知状态：0-未发送；1-已发送
        setCols00.add("LastModifyTime", sysDateTime);
        whereCols00.add("AccountID", in.AccountID);
        whereCols00.add("WarningFlag", "1");
        whereCols00.add("LastModifyTime","<", DateUtils.addTimeStamp(sysDateTime, -reminderDays));
        smsAccountDAO.update(setCols00, whereCols00);
        
        PYSMSAccount smsAccount = new PYSMSAccount();
        smsAccount.AccountID = in.AccountID;
        smsAccount = smsAccountDAO.find(smsAccount);
        
        //更新账户信息
        switch(smsAccount.Ways){
        case SysDict.BILL_METHOD_USAGE://按量充值
            if((smsAccount.TotalSMSNum+smsAccount.SMSCreditLimit)<=0 
                && SysDict.SMS_ACCOUNT_STATUS_NORMAL.equalsIgnoreCase(smsAccount.AccountStatus)){
                isToDisable = true;
            }
            
            if(!"1".equalsIgnoreCase(smsAccount.WarningFlag)
                    &&smsAccount.TotalSMSNum < smsAccount.WarningLimit){
                isWarningFlag = true;
            }
            break;
        case SysDict.BILL_METHOD_MONTHLY://包月
        case SysDict.BILL_METHOD_YARELY://包年
            if(smsAccount.ExpirationDate!=null
                &&sysDate.after(smsAccount.ExpirationDate)
                && SysDict.SMS_ACCOUNT_STATUS_NORMAL.equalsIgnoreCase(smsAccount.AccountStatus)){
                isToDisable = true;
            }
            
            if(!"1".equalsIgnoreCase(smsAccount.WarningFlag)){
                int days = DateUtils.diffDate(smsAccount.ExpirationDate, sysDate);
                if(days < 30){
                    isWarningFlag = true;
                }
            }
            break;
        default:
            break;
        }
        if(isToDisable){
            //更新账户状态
            JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();
            setCols.add("AccountStatus", SysDict.SMS_ACCOUNT_STATUS_DISABLE);//2-账号状态 欠费停用
            setCols.add("LastModifyTime", sysDateTime);
            whereCols.add("AccountID", in.AccountID);
            smsAccountDAO.update(setCols, whereCols);
        }
        
        //发送通知
        if(commonDAO.isPhoneNumber(smsAccount.Phone)
            &&StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg())){
            if(isToDisable){
                SMSManager.getInstance().sentSMSOwingReport(Constant.DEFAULT_HEAD_DEPARTMENTID, smsAccount.AccountName, smsAccount.Phone);
            }else if(isWarningFlag){
                //更新账户状态
                JDBCFieldArray setCols = new JDBCFieldArray();
                JDBCFieldArray whereCols = new JDBCFieldArray();
                setCols.add("WarningFlag", "1");//告警通知状态：1-已发送
                setCols.add("LastModifyTime", sysDateTime);
                whereCols.add("AccountID", in.AccountID);
                smsAccountDAO.update(setCols, whereCols);
                
                if(SysDict.BILL_METHOD_USAGE.equalsIgnoreCase(smsAccount.Ways)){
                    SMSManager.getInstance().sentSMSOwingReport(smsAccount.DepartmentID, smsAccount.AccountName,(int)smsAccount.TotalSMSNum, smsAccount.Phone);
                }else{
                    SMSManager.getInstance().sentSMSOwingReport(smsAccount.DepartmentID, smsAccount.AccountName,smsAccount.ExpirationDate,smsAccount.Phone);
                }
            }
            
        }
        return result;
    }
}
