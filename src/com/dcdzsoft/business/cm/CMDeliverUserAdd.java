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
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 投递服务用户增加 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class CMDeliverUserAdd extends ActionBean
{

    public int doBusiness(InParamCMDeliverUserAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        String fullName = in.getFirstName()+" "+ in.getLastName();
        
		//1.验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isBlank(fullName)
                || StringUtils.isEmpty(in.TerminalNo))
            throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		//检查设备号
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        TBTerminal terminal = new TBTerminal();
        terminal.TerminalNo=in.TerminalNo;
        if(!terminalDAO.isExist(terminal)){
            throw new EduException(ErrorCode.ERR_TERMINALNOTEXISTS); 
        }
        terminal = terminalDAO.find(terminal);
        
        fullName = StringUtils.normalizeName(fullName);
        //检查会员名称是否存在
        CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("CustomerName", fullName);
        /*if (customerDAO.isExist(whereCols0) > 0){
            throw new EduException(ErrorCode.ERR_CUSTOMERNAMEEXISTS);
        }*/
        RowSet rset = customerDAO.select(whereCols0);
        if (RowSetUtils.rowsetNext(rset)) {
            in.CustomerID=RowSetUtils.getStringValue(rset, "CustomerID");
        }
        
        if(StringUtils.isBlank(in.CustomerID)){
            in.CustomerID=RandUtils.generateString(10).toUpperCase();
        }
        
        //检查会员编号
        CMCustomer customer = new CMCustomer();
        customer.CustomerID=in.CustomerID;
        if (customerDAO.isExist(customer)){
            //查询会员信息
            customer = customerDAO.find(customer);
            if(!SysDict.CUSTOMER_STATUS_CANCEL.equals(customer.CustomerStatus)){
                throw new EduException(ErrorCode.ERR_CUSTOMEREXISTS);
            }
            //更新会员信息
            JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();
            setCols.add("CustomerStatus", SysDict.CUSTOMER_STATUS_NROMAL);
            setCols.add("CustomerType", SysDict.CUSTOMER_TYPE_DELIVERUSER);
            setCols.add("CustomerName", fullName);
            setCols.checkAdd("Address", in.Address);
            setCols.checkAdd("Email", in.Email);
            setCols.checkAdd("CustomerMobile", in.CustomerMobile);
            setCols.add("TerminalNo", in.TerminalNo);
            setCols.add("LastModifyTime", sysDateTime);
            whereCols.add("CustomerID", in.CustomerID);
            customerDAO.update(setCols, whereCols);
        }else{
            //记录会员信息
            customer.CustomerName=fullName;
            customer.CustomerStatus=SysDict.CUSTOMER_STATUS_NROMAL;
            customer.CustomerType=SysDict.CUSTOMER_TYPE_DELIVERUSER;
            customer.CustomerMobile=in.CustomerMobile;
            customer.CreateTime=sysDateTime;
            customer.LastModifyTime=sysDateTime;
            customer.Address=in.Address;
            customer.Email=in.Email;
            customer.TerminalNo=in.TerminalNo;
            customerDAO.insert(customer);
        }
            
        //记录收件人信息
        CMDeliverServiceUserDAO deliverUserDAO = daoFactory.getCMDeliverServiceUserDAO();
        CMDeliverServiceUser deliverUser = new CMDeliverServiceUser();
        deliverUser.CustomerID=in.CustomerID;
        if(deliverUserDAO.isExist(deliverUser)){
            deliverUserDAO.delete(deliverUser);
        }
        deliverUser.FirstName=in.FirstName;
        deliverUser.LastName=in.LastName;
        deliverUser.LastModifyTime=sysDateTime;
        deliverUser.TerminalNo=terminal.TerminalNo;
        deliverUser.DepartmentID=terminal.DepartmentID;
        deliverUser.ServiceStatus=SysDict.DELIVER_SERVICE_STATUS_NROMAL;
        if(SysDict.CUSTOMER_SPECIAL_FLAG_YES.equals(in.SpecialFlag)){
            deliverUser.SpecialFlag=in.SpecialFlag;
        }else{
            deliverUser.SpecialFlag=SysDict.CUSTOMER_SPECIAL_FLAG_NO;
        }
        
        if(SysDict.DELIVER_FAIL_GOTOOFFICE.equals(in.getDeliverFailHandle())){
            deliverUser.DeliverFailHandle=in.DeliverFailHandle;
        }else{
            deliverUser.DeliverFailHandle=SysDict.DELIVER_FAIL_CONTACTUSER;
        }
        result = deliverUserDAO.insert(deliverUser);
		

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
