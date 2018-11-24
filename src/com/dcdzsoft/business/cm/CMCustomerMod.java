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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 会员信息修改 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class CMCustomerMod extends ActionBean
{

    public int doBusiness(InParamCMCustomerMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.CustomerID)
			||StringUtils.isEmpty(in.CustomerName))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		in.CustomerName = StringUtils.normalizeName(in.CustomerName);
		
		CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
		CMCustomer customer = new CMCustomer();
		customer.CustomerID = in.CustomerID;
		customer = customerDAO.find(customer);
		
        
        if(StringUtils.isNotEmpty(in.BindCardID))
        {
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID",in.BindCardID);
            whereCols0.add("CustomerID","<>",in.CustomerID);

            if(customerDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
            
        }
        if(StringUtils.isNotEmpty(in.BindMobile) && !in.BindMobile.equals(customer.BindMobile))
        {
            if(!commonDAO.isPhoneNumber(in.BindMobile))
                throw new EduException(ErrorCode.ERR_BUSINESS_MOBILEFORMATERROR);
            
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindMobile",in.BindMobile);

            if(customerDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDMOBILE);
            
            if(StringUtils.isEmpty(in.CustomerMobile) && StringUtils.isEmpty(customer.CustomerMobile)){
                in.CustomerMobile = in.BindMobile;
            }
        }
        
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        
        setCols.add("CustomerName", in.CustomerName);
        setCols.checkAdd("CustomerStatus", in.CustomerStatus);
        setCols.add("CustomerMobile", in.CustomerMobile);
        setCols.add("TerminalNo", in.TerminalNo);
        setCols.add("IDCard", in.IDCard);
        setCols.add("BindCardID", in.BindCardID);
        setCols.add("BindMobile", in.BindMobile);
        setCols.add("Email", in.Email);
        setCols.add("Address", in.Address);
        setCols.add("Remark", in.Remark);
        whereCols.add("CustomerID", in.CustomerID);
        customerDAO.update(setCols, whereCols);
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = "";

		commonDAO.addOperatorLog(log);


        return result;
    }
}
