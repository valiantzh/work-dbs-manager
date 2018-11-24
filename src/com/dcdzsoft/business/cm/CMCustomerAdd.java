package com.dcdzsoft.business.cm;

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
 * <p>Title: 智能自提柜运营系统</p>
 * <p>Description: 会员信息增加 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class CMCustomerAdd extends ActionBean
{

    public int doBusiness(InParamCMCustomerAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.CustomerName))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		in.CustomerName = StringUtils.normalizeName(in.CustomerName);
		if(StringUtils.isEmpty(in.CustomerID)){
            in.CustomerID = RandUtils.generateString(10).toUpperCase();
        }

		
        //判断编号是否存在
		CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
		CMCustomer customer = new CMCustomer();
		customer.CustomerID = in.CustomerID;

        if (customerDAO.isExist(customer))
            throw new EduException(ErrorCode.ERR_CUSTOMEREXISTS);

        if(StringUtils.isNotEmpty(in.BindCardID))
        {
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID",in.BindCardID);

            if(customerDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
        }
        
        //插入用户信息
        customer.CustomerName   = in.CustomerName;
        customer.CustomerMobile = in.CustomerMobile;
        customer.CustomerStatus = SysDict.CUSTOMER_STATUS_NROMAL;
        customer.CustomerType   = SysDict.CUSTOMER_TYPE_NROMAL;//0-普通用户
        customer.IDCard         = in.IDCard;
        customer.BindCardID     = in.BindCardID;
        customer.Email          = in.Email;
        customer.Address        = in.Address;
        customer.TerminalNo     = in.TerminalNo;
        customer.CreateTime     = sysDateTime;
        customer.LastModifyTime = sysDateTime;
        customer.Remark         = in.Remark;
        customerDAO.insert(customer);
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.CustomerID;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
