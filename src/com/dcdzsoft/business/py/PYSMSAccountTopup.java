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
 * <p>Description: 短信账户充值 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSAccountTopup extends ActionBean
{

    public int doBusiness(InParamPYSMSAccountTopup in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.AccountID)
			||StringUtils.isEmpty(in.Ways))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		//账户信息
        PYSMSAccountDAO smsAccountDAO = daoFactory.getPYSMSAccountDAO();
        PYSMSAccount smsAccount = new PYSMSAccount();
        smsAccount.AccountID = in.AccountID;
        smsAccount = smsAccountDAO.find(smsAccount);
        
        //查询前月份短信使用量统计
        long SMSBalance = 0;//剩余短信数量
        
        //更新账户信息
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        switch(in.Ways){
        case SysDict.BILL_METHOD_USAGE://按量充值
            if(in.SMSNum>0){
                setCols.addSQL(" TotalSMSNum=TotalSMSNum+ "+in.SMSNum);
                smsAccount.TotalSMSNum += in.SMSNum;//累加充值的短信量
            }
            SMSBalance = smsAccount.TotalSMSNum;
            setCols.add("SMSCreditLimit", in.SMSCreditLimit);
            break;
        case SysDict.BILL_METHOD_MONTHLY://包月
        case SysDict.BILL_METHOD_YARELY://包年
            if(in.BillNum>0){
                int months = (int)in.BillNum;
                if(SysDict.BILL_METHOD_YARELY.equalsIgnoreCase(in.Ways)){
                    months = (int)in.BillNum*12;
                }
                java.sql.Date expDate =DateUtils.changeMonth(smsAccount.ExpirationDate, months);
                setCols.add("ExpirationDate", expDate);
            }
            SMSBalance = smsAccount.WarningLimit-smsAccount.LastSMSNum;
            break;
        default:
            throw new EduException(ErrorCode.ERR_TOPUPFAILWAYNOTEXISTS);
        }
        if(!SysDict.SMS_ACCOUNT_STATUS_NORMAL.equalsIgnoreCase(smsAccount.AccountStatus)){
            setCols.add("AccountStatus", SysDict.SMS_ACCOUNT_STATUS_NORMAL);
        }
        setCols.add("WarningLimit", in.WarningLimit);
        setCols.add("WarningFlag", "0");
        setCols.add("Ways", in.Ways);
        setCols.add("LastModifyTime", sysDateTime);
        whereCols.add("AccountID", in.AccountID);
        smsAccountDAO.update(setCols, whereCols);

        //记录充值流水
        PYSMSBillWaterDAO billWaterDAO = daoFactory.getPYSMSBillWaterDAO(); 
        PYSMSBillWater billWater = new PYSMSBillWater();
        billWater.AccountID   = in.AccountID;
        billWater.BillWaterID = StringUtils.getUUID();
        billWater.BillTime    = sysDateTime;
        billWater.BillType    = SysDict.BILL_TYPE_TOPUP;
        billWater.Ways        = in.Ways;
        billWater.Amount = in.Amount;
        billWater.BillNum = (int)in.BillNum;
        billWater.Units = in.Ways;
        billWater.SMSNum = in.SMSNum;
        billWater.SMSBalance = SMSBalance;
        billWater.ServiceID = in.ServiceID;
        billWater.Remark = in.Remark;
        billWaterDAO.insert(billWater);
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = billWater.BillWaterID;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
