package com.dcdzsoft.business.ap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * <p>Description: 绑定合作方用户编号 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APCustomerBindOpenID extends ActionBean
{

    public OutParamAPCustomerBindOpenID doBusiness(InParamAPCustomerBindOpenID in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPCustomerBindOpenID out = new OutParamAPCustomerBindOpenID();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CustomerID)
			||StringUtils.isEmpty(in.UnionID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		//in.CustomerName = StringUtils.normalizeName(in.CustomerName);
		//
		CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
		CMCustomer customer = new CMCustomer();
		customer.CustomerID = in.CustomerID;
		try{
		    customer = customerDAO.find(customer);
		    
		    JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();
            if(StringUtils.isNotEmpty(customer.BindMobile)){
                setCols.add("CustomerStatus", SysDict.CUSTOMER_STATUS_NROMAL);
            }else{
                setCols.add("CustomerStatus", SysDict.CUSTOMER_STATUS_UNACTIVE);
            }
            
            setCols.checkAdd("CustomerName", in.CustomerName);
            setCols.add("UnionID", in.UnionID);
            setCols.add("LastModifyTime", sysDateTime);
            whereCols.add("CustomerID", in.CustomerID);
            customerDAO.update(setCols, whereCols);
		}catch(EduException e){
		    customer.CustomerName   = in.CustomerName;
            customer.CustomerStatus = SysDict.CUSTOMER_STATUS_UNACTIVE;
            customer.CustomerType   = SysDict.CUSTOMER_TYPE_NROMAL;
            //customer.UnionID        = in.UnionID;//
            customer.CreateTime     = sysDateTime;
            customer.LastModifyTime = sysDateTime;
            customerDAO.insert(customer);
		}

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.CustomerID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = "";
		log.Remark = "App"+in.UnionID;

		commonDAO.addOperatorLog(log);


        return out;
    }
    
}
