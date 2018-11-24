package com.dcdzsoft.business.py;

import javax.sql.RowSet;

import org.apache.commons.lang.StringUtils;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 检测短信发送量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSNumCheck extends ActionBean
{

    public boolean doBusiness(InParamPYSMSNumCheck in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        boolean result = true;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (in.MsgType <= 0
			||in.MsgSize <= 0 )
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		if(StringUtils.isEmpty(in.DepartmentID)){
		    if(StringUtils.isNotEmpty(ControlParam.getInstance().getHeaderDepartmentID())){
		        in.DepartmentID = ControlParam.getInstance().getHeaderDepartmentID();
		    }else{
		        in.DepartmentID = com.dcdzsoft.constant.Constant.DEFAULT_HEAD_DEPARTMENTID; 
		    }
		}
		//计算发送短信的实际数量
		int bytesPerSms = NumberUtils.parseInt(ControlParam.getInstance().getBytePerSMS());
        if(bytesPerSms< Constant.SMS_SIZE_BYTE){
            bytesPerSms= Constant.SMS_SIZE_BYTE;
        }
        int smsNum = (int)Math.ceil((double)in.MsgSize/bytesPerSms) ;
        if(smsNum<=0 && in.MsgSize>0){
            smsNum = 1;
        }
        
        
        
        
        //充值账户检查
		if("1".equalsIgnoreCase(ControlParam.getInstance().getCheckSMSAccount())){
		    
		    //账户信息
	        PYSMSAccountDAO smsAccountDAO = daoFactory.getPYSMSAccountDAO();
	        PYSMSAccount smsAccount = new PYSMSAccount();
	        
	        //查询运营网点对应的短信充值账户
	        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
	        whereSQL.add("DepartmentID", in.DepartmentID);
	        String sql = "SELECT AccountID,AccountStatus FROM V_PYSMSAccount_d1 a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
	        
	        RowSet rset = dbSession.executeQuery(sql,whereSQL);
	        if(RowSetUtils.rowsetNext(rset)){
	            smsAccount.AccountID = RowSetUtils.getStringValue(rset, "AccountID");
	            smsAccount = smsAccountDAO.find(smsAccount);
	            if(SysDict.SMS_ACCOUNT_STATUS_NORMAL.equalsIgnoreCase(smsAccount.AccountStatus)){
	                //正常
	                if(SMSInfo.MSG_TYPE_SMSOWING == in.MsgType){
                        //！！欠费通知短信需要发送
                        result = true;
	                }else{
	                    if(SysDict.BILL_METHOD_USAGE.equalsIgnoreCase(smsAccount.Ways)){
	                        //按投递量统计
	                        if((smsAccount.TotalSMSNum+smsAccount.SMSCreditLimit) >= smsNum){
	                            result = true;
	                        }else{
	                            result = false;
	                        }
	                    }else{
	                        //按月或按年统计
	                        if(smsAccount.ExpirationDate!=null
	                            &&sysDate.after(smsAccount.ExpirationDate) ){
	                             //超过有效期
	                             result = false;
	                         }else{
	                             result = true;
	                         }
	                    }
	                    
	                    //短信数量不足或刚刚过期
	                    if(!result){
	                        //短信数量不足或刚刚过期，更新账户状态,并发送停用通知
                            JDBCFieldArray setCols = new JDBCFieldArray();
                            JDBCFieldArray whereCols = new JDBCFieldArray();
                            setCols.add("AccountStatus", SysDict.SMS_ACCOUNT_STATUS_DISABLE);//2-账号状态 欠费停用
                            setCols.add("WarningFlag", "1");//告警通知状态：1-已发送
                            setCols.add("LastModifyTime", sysDateTime);
                            whereCols.add("AccountID", smsAccount.AccountID);
                            
                            smsAccountDAO.update(setCols, whereCols);
                            SMSManager.getInstance().sentSMSOwingReport(in.DepartmentID, smsAccount.AccountName, smsAccount.Phone);
	                    }
	                }
	            }else if(SysDict.SMS_ACCOUNT_STATUS_DISABLE.equalsIgnoreCase(smsAccount.AccountStatus)){
	                //2-账号状态 欠费停用   
	                if(SMSInfo.MSG_TYPE_SMSOWING == in.MsgType){
	                    //！！欠费通知短信需要发送
	                    result = true;
	                }else{
	                    result = false;
	                }
	            }else{
	                //注销
	                result = false;
	            }
	            //允许发送，则更新短信统计、更新短信使用量
	            if(result){
	                if(SysDict.BILL_METHOD_USAGE.equalsIgnoreCase(smsAccount.Ways)){
	                    //按使用量计费，更新剩余短信数量
	                    JDBCFieldArray setCols = new JDBCFieldArray();
	                    JDBCFieldArray whereCols = new JDBCFieldArray();
	                    setCols.addSQL(" TotalSMSNum=TotalSMSNum- "+smsNum);
	                    
	                    whereCols.add("AccountID", smsAccount.AccountID);
	                    smsAccountDAO.update(setCols, whereCols);
	                }
	                modifySMSStat(in.DepartmentID,in.MsgType, smsNum);//更新短信统计
	            }
	        }else{
	            //无充值账号
	            result = false;
	        }
		}else{
		    //未开通短信充值账号功能，允许发送，只更新短信统计
		    result = true;
		    modifySMSStat(in.DepartmentID,in.MsgType, smsNum);//更新短信统计
		}
		
        return result;
    }
    /**
     * 更新短信统计
     * @param TerminalNo 短信统计记录运营网点：DepartmentID->TerminalNo
     * @param MsgType
     * @param smsNum
     * @throws EduException
     */
    private void modifySMSStat(String TerminalNo,int MsgType,int smsNum) throws EduException {
        MBSmsStatDAO smsStatDAO = daoFactory.getMBSmsStatDAO();
        MBSmsStat smsStat = new MBSmsStat();
        smsStat.TerminalNo = TerminalNo;//
        smsStat.OccurYear = DateUtils.currentYear();
        smsStat.OccurMonth = DateUtils.currentMonth();

        if (smsStatDAO.isExist(smsStat)) {
            JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();

            setCols.addSQL(" TotalNum=TotalNum+ "+smsNum);
            switch(MsgType){
            case SMSInfo.MSG_TYPE_DELIVERY://投递
            case SMSInfo.MSG_TYPE_RESENT://重发投递
                setCols.addSQL(" PwdNum=PwdNum+ "+smsNum);
                break;
            case SMSInfo.MSG_TYPE_EXPIRED://逾期
                setCols.addSQL(" ExpireNum=ExpireNum+ "+smsNum);
                break;
            case SMSInfo.MSG_TYPE_REMINDER://催领
                setCols.addSQL(" ReminderNum=ReminderNum+ "+smsNum);
                break;
            case SMSInfo.MSG_TYPE_TAKEDOUT://取件
                setCols.addSQL(" PickupNum=PickupNum+ "+smsNum);
                break;
            case SMSInfo.MSG_TYPE_CHECKCODE://注册验证码
            case SMSInfo.MSG_TYPE_REGISTER://投递员注册
                setCols.addSQL(" DynamicNum=DynamicNum+ "+smsNum);
                break;  
            case SMSInfo.MSG_TYPE_SENDURGENTSMS://紧急取件短信
                setCols.addSQL(" OtherNum=OtherNum+ "+smsNum);
                break;
            default:
                setCols.addSQL(" OtherNum=OtherNum+ "+smsNum);
                break;
            }
            whereCols.add("TerminalNo", TerminalNo);
            whereCols.add("OccurYear", smsStat.OccurYear);
            whereCols.add("OccurMonth", smsStat.OccurMonth);

            smsStatDAO.update(setCols, whereCols);
        } else {
            smsStat.TotalNum = smsNum;
            smsStat.PwdNum = 0;
            smsStat.ExpireNum = 0;
            smsStat.ReminderNum = 0;
            smsStat.DynamicNum = 0;
            smsStat.PickupNum = 0;
            smsStat.OtherNum = 0;
            switch(MsgType){
            case SMSInfo.MSG_TYPE_DELIVERY://投递
            case SMSInfo.MSG_TYPE_RESENT://重发投递
                smsStat.PwdNum = smsNum;
                break;
            case SMSInfo.MSG_TYPE_EXPIRED://逾期
                smsStat.ExpireNum = smsNum;
                break;
            case SMSInfo.MSG_TYPE_REMINDER://催领
                smsStat.ReminderNum = smsNum;
                break;
            case SMSInfo.MSG_TYPE_TAKEDOUT://取件
                smsStat.PickupNum = smsNum;
                break;
            case SMSInfo.MSG_TYPE_CHECKCODE://注册验证码
            case SMSInfo.MSG_TYPE_REGISTER://投递员注册
                smsStat.DynamicNum = smsNum;
                break;  
            case SMSInfo.MSG_TYPE_SENDURGENTSMS://紧急取件短信
                smsStat.OtherNum = smsNum;
                break;
            default:
                smsStat.OtherNum = smsNum;
                break;
            }
            smsStatDAO.insert(smsStat);
        }
    }
}
