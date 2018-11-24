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
 * <p>Description: 修改短信服务账号信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSAccountMod extends ActionBean
{

    public int doBusiness(InParamPYSMSAccountMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.AccountID)
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
        whereCols0.add("AccountID", "<>", in.AccountID);
        if (smsAccountDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_SERVICESNAMEEXISTS);

        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        setCols.add("AccountName", in.AccountName);
        setCols.checkAdd("Email", in.Email);
        setCols.checkAdd("Phone", in.Phone);
        setCols.checkAdd("ContactPerson", in.ContactPerson);
        setCols.checkAdd("Remark", in.Remark);
        if(StringUtils.isNotEmpty(in.DepartmentID)){
            //短信账号更换运营网点
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            whereCols1.add("DepartmentID", in.DepartmentID);
            whereCols1.add("AccountID","<>", in.AccountID);
            if (smsAccountDAO.isExist(whereCols1) > 0){//一个运营网点只能有一个短信账号
                throw new EduException(ErrorCode.ERR_DEPARTACCOUNTEXISTS);
            }
            setCols.add("DepartmentID", in.DepartmentID);
        }
        setCols.checkAdd("SMSCreditLimit", in.SMSCreditLimit);
        setCols.checkAdd("AccountStatus", in.AccountStatus);
        setCols.add("WarningFlag", "0");
        whereCols.add("AccountID", in.AccountID);

        smsAccountDAO.update(setCols, whereCols);
        
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
