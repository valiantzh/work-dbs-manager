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
 * <p>Description: 增加短信服务账号信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSAccountAdd extends ActionBean
{

    public int doBusiness(InParamPYSMSAccountAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.AccountName))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		in.AccountName = StringUtils.normalizeName(in.AccountName);
        
        //////////////检查名称不重复//////////////////////
		PYSMSAccountDAO smsAccountDAO = daoFactory.getPYSMSAccountDAO();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("AccountName", in.AccountName);
        if (smsAccountDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_SMSACCOUNTNAMEEXISTS);

        ///////////////生成编号//////////////////////////
        String maxID = "";
        int iMaxID = 0;
        PYSMSAccount obj = new PYSMSAccount();
        
        if(StringUtils.isEmpty(in.AccountID))
        {
            //8位数字
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            maxID = smsAccountDAO.selectFunctions("MAX(AccountID)", whereCols1);
            if (StringUtils.isEmpty(maxID)) {
                obj.AccountID = "00000001";
            } else {
                iMaxID = NumberUtils.parseInt(maxID) + 1;
                obj.AccountID = StringUtils.leftPad(String.valueOf(iMaxID), 8, '0');
            }
        }else
        {
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            whereCols1.add("AccountID", in.AccountID);
            if (smsAccountDAO.isExist(whereCols1) > 0)
                throw new EduException(ErrorCode.ERR_SMSACCOUNTEXISTS);
            
            obj.AccountID = in.AccountID;
        }
        
        if(StringUtils.isEmpty(in.DepartmentID)){
            obj.DepartmentID = "";
        }else{
            //一个运营网点只能有一个短信账号
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            whereCols1.add("DepartmentID", in.DepartmentID);
            if (smsAccountDAO.isExist(whereCols1) > 0)
                throw new EduException(ErrorCode.ERR_DEPARTACCOUNTEXISTS);
            obj.DepartmentID = in.DepartmentID;
        }
        obj.AccountName = in.AccountName;
        obj.AccountStatus   = SysDict.SMS_ACCOUNT_STATUS_NORMAL;
        obj.Email  = in.Email;
        obj.Phone = in.Phone;
        obj.ContactPerson = in.ContactPerson;
        obj.Ways = SysDict.BILL_METHOD_USAGE;//默认按使用量计费
        obj.TotalSMSNum = 0;
        obj.SMSCreditLimit = 0;
        obj.LastSMSNum = 0;
        obj.EffectiveDate = sysDate;
        obj.ExpirationDate = sysDate;
        obj.WarningLimit = 0;
        obj.WarningFlag = "0";
        obj.LastModifyTime = sysDateTime;
        obj.CreateTime = sysDateTime;
        obj.Remark = in.Remark;

        result = smsAccountDAO.insert(obj);


		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.AccountName;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
