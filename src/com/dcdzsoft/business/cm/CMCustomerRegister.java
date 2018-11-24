package com.dcdzsoft.business.cm;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.email.EmailSenderManager;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 会员注册 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class CMCustomerRegister extends ActionBean
{

    public OutParamCMCustomerRegister doBusiness(InParamCMCustomerRegister in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamCMCustomerRegister out = new OutParamCMCustomerRegister();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.CustomerMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//==泰和测试用-20161123
		if(StringUtils.isNotEmpty(in.BindCardID))
        {
		    //查卡是否存在
            PYConsumedCard card = new PYConsumedCard();
            card.CardNo = in.BindCardID;
            PYConsumedCardDAO cardDAO = daoFactory.getPYConsumedCardDAO();
            if(!cardDAO.isExist(card)){
                throw new EduException(ErrorCode.ERR_CUSTOMERCARDNOTEXISTS);
            }
        }
		CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
		CMCustomer customer = new CMCustomer();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("CustomerMobile",in.CustomerMobile);
        RowSet rset = customerDAO.select(whereCols1);
        if(RowSetUtils.rowsetNext(rset)){
            customer.CustomerID = RowSetUtils.getStringValue(rset, "CustomerID");
            customer.BindCardID = RowSetUtils.getStringValue(rset, "BindCardID");
        }else{
            throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
        }
        
        if(StringUtils.isNotEmpty(customer.BindCardID)){
            throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
        }
		if(StringUtils.isNotEmpty(in.BindCardID))
        {
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID",in.BindCardID);
            
            if(customerDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
            
            JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();
            setCols.add("CustomerMobile", in.CustomerMobile);
            setCols.add("TerminalNo", in.TerminalNo);
            setCols.add("BindCardID", in.BindCardID);
            whereCols.add("CustomerID", customer.CustomerID);
            customerDAO.update(setCols, whereCols);
        }
        //==测试end
		
        out.BindCardID = in.BindCardID;
        out.CheckCode   = in.CheckCode;
        out.CustomerMobile = in.CustomerMobile;
        out.CustomerID     = customer.CustomerID;
        out.CustomerName   = in.CustomerName;
        customer = customerDAO.find(customer);
        customer.CustomerMobile = in.CustomerMobile;
        customer.TerminalNo = in.TerminalNo;
        customer.BindCardID = in.BindCardID;
		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //发送注册邮件
        if("1".equals(ControlParam.getInstance().getSendRegisterEmailToPM())
        	&& StringUtils.isNotEmpty(customer.Email)){
			EmailSenderManager.getInstance().sendRegisterEmailToCM(customer);
        }
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.CustomerMobile;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = ""+in.TerminalNo;
		log.Remark = ""+in.BindCardID;

		commonDAO.addOperatorLog(log);


        return out;
    }
}
