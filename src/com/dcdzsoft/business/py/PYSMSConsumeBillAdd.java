package com.dcdzsoft.business.py;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 添加短信消费账单 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSConsumeBillAdd extends ActionBean
{

    public int doBusiness(InParamPYSMSConsumeBillAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.AccountID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		
		
	    //账户信息
        PYSMSAccountDAO smsAccountDAO = daoFactory.getPYSMSAccountDAO();
        PYSMSAccount smsAccount = new PYSMSAccount();
        smsAccount.AccountID = in.AccountID;
        smsAccount = smsAccountDAO.find(smsAccount);
        
        //查询前月份短信使用量统计
        long SMSNum = 0;//使用短信数量
        long SMSBalance = 0;//剩余短信数量
        int OccurYear = DateUtils.currentYear();
        int OccurMonth = DateUtils.currentMonth();
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.add("OccurYear", OccurYear);
        whereSQL.add("OccurMonth", OccurMonth);
        whereSQL.add("DepartmentID", smsAccount.DepartmentID);
        String sql = "SELECT TotalNum FROM V_Stat4SMS_d_month a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
        
        RowSet rset = dbSession.executeQuery(sql,whereSQL);
        if(RowSetUtils.rowsetNext(rset)){
            long LastSMSNum = RowSetUtils.getLongValue(rset, "TotalNum");//当前月份短信使用量
            if(LastSMSNum> smsAccount.LastSMSNum){
                SMSNum = LastSMSNum-smsAccount.LastSMSNum;//当前月份短信使用量-前一次记录的短信使用量
            }else{
                SMSNum = LastSMSNum;//隔月  需要在零点之前操作？？？？？否则会有误差 
            }
            smsAccount.LastSMSNum = LastSMSNum;//记录当前月份短信使用量
        }else{
            smsAccount.LastSMSNum = 0;//当前月份没有短信使用量
        }
        
        //更新账户信息
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        switch(smsAccount.Ways){
        case SysDict.BILL_METHOD_USAGE://按量充值
            SMSBalance = smsAccount.TotalSMSNum;
            if((smsAccount.TotalSMSNum+smsAccount.SMSCreditLimit)<=0 
                && SysDict.SMS_ACCOUNT_STATUS_NORMAL.equalsIgnoreCase(smsAccount.AccountStatus)){
                setCols.add("AccountStatus", SysDict.SMS_ACCOUNT_STATUS_DISABLE);//2-账号状态 欠费停用
            }
            break;
        case SysDict.BILL_METHOD_MONTHLY://包月
        case SysDict.BILL_METHOD_YARELY://包年
            SMSBalance = smsAccount.WarningLimit-smsAccount.LastSMSNum;
            if(smsAccount.ExpirationDate!=null
                    &&sysDate.after(smsAccount.ExpirationDate)
                    && SysDict.SMS_ACCOUNT_STATUS_NORMAL.equalsIgnoreCase(smsAccount.AccountStatus)){
                setCols.add("AccountStatus", SysDict.SMS_ACCOUNT_STATUS_DISABLE);//2-账号状态 欠费停用
            }
            break;
        default:
            throw new EduException(ErrorCode.ERR_TOPUPFAILWAYNOTEXISTS);
        }
        setCols.add("LastSMSNum", smsAccount.LastSMSNum);
        setCols.add("LastCountTime", sysDateTime);
        whereCols.add("AccountID", in.AccountID);
        smsAccountDAO.update(setCols, whereCols);
        
        if(SMSNum>0){//记录短信消耗流水
            PYSMSBillWaterDAO billWaterDAO = daoFactory.getPYSMSBillWaterDAO(); 
            PYSMSBillWater billWater = new PYSMSBillWater();
            billWater.AccountID = in.AccountID;
            billWater.BillWaterID = StringUtils.getUUID();
            billWater.BillTime = sysDateTime;
            billWater.BillType = SysDict.BILL_TYPE_CONSUMED;
            billWater.Ways = smsAccount.Ways;
            billWater.Amount = 0.0;
            billWater.BillNum = 0;
            billWater.Units = smsAccount.Ways;
            billWater.SMSNum = SMSNum;
            billWater.SMSBalance = SMSBalance; 
            billWater.ServiceID = "";
            billWater.Remark = in.Remark;
            billWaterDAO.insert(billWater);
        }
        
        return result;
    }
}
