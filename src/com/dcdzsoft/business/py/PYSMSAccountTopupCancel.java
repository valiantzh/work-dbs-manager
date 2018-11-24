package com.dcdzsoft.business.py;

import javax.sql.RowSet;

import net.sf.json.JSONObject;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 短信账户充值取消 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSAccountTopupCancel extends ActionBean
{

    public int doBusiness(InParamPYSMSAccountTopupCancel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.AccountID)
			||StringUtils.isEmpty(in.OperPassword)
			||StringUtils.isEmpty(in.BillWaterID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		//5.    调用OPOperatorDAO.find(操作员编号)查询操作员密码
		OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
        OPOperator opOperator = new OPOperator();
        opOperator.OperID = in.OperID;
        opOperator = operatorDAO.find(opOperator);
        if (opOperator.OperPassword.compareTo(SecurityUtils.md5(in.OperPassword)) != 0) {
            throw new EduException(ErrorCode.ERR_OPERPWDWRONG);
        }

        //检查充值流水号：
        PYSMSBillWaterDAO billWaterDAO = daoFactory.getPYSMSBillWaterDAO(); 
        PYSMSBillWater billWater = new PYSMSBillWater();
        billWater.AccountID = in.AccountID;
        billWater.BillWaterID = in.BillWaterID;
        if(!billWaterDAO.isExist(billWater)){
            throw new EduException(ErrorCode.ERR_BILLWATERIDNOTEXISTS);
        }
        billWater = billWaterDAO.find(billWater);
        
        //非充值流水，不允许取消
        if(!SysDict.BILL_TYPE_TOPUP.equalsIgnoreCase(billWater.BillType)){
            throw new EduException(ErrorCode.ERR_FORBIDDELBILLWATER);
        }
        
        //账户信息
        PYSMSAccountDAO smsAccountDAO = daoFactory.getPYSMSAccountDAO();
        PYSMSAccount smsAccount = new PYSMSAccount();
        smsAccount.AccountID = in.AccountID;
        smsAccount = smsAccountDAO.find(smsAccount);
        
        //账单类型的计费方式与当前账号的计费方式不同，不允许取消 
        if(!billWater.Ways.equalsIgnoreCase(smsAccount.Ways)){
            throw new EduException(ErrorCode.ERR_FORBIDDELBILLWATER);
        }
        
        //更新账户信息
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        switch(billWater.Ways){
        case SysDict.BILL_METHOD_USAGE://按量充值
            if(billWater.SMSNum>0){
                setCols.addSQL(" TotalSMSNum=TotalSMSNum-"+billWater.SMSNum);
                smsAccount.TotalSMSNum -= billWater.SMSNum;
            }
            break;
        case SysDict.BILL_METHOD_MONTHLY://包月
        case SysDict.BILL_METHOD_YARELY://包年
            if(billWater.BillNum>0){
                int months = (int)billWater.BillNum;
                if(SysDict.BILL_METHOD_YARELY.equalsIgnoreCase(billWater.Ways)){
                    months = (int)billWater.BillNum*12;
                }
                java.sql.Date expDate =DateUtils.changeMonth(smsAccount.ExpirationDate, -months);
                setCols.add("ExpirationDate", expDate);
            }
            break;
        }
        setCols.add("LastModifyTime", sysDateTime);
        whereCols.add("AccountID", in.AccountID);
        smsAccountDAO.update(setCols, whereCols);
        
        //删除充值流水
        billWaterDAO.delete(billWater);
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = ""+JSONObject.fromObject(billWater).toString();

		commonDAO.addOperatorLog(log);


        return result;
    }
}
