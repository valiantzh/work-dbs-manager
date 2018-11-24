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
 * <p>Description: 投递服务用户修改 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class CMDeliverUserMod extends ActionBean
{

    public int doBusiness(InParamCMDeliverUserMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.CustomerID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		
		//更新收件人信息
        CMDeliverServiceUserDAO deliverUserDAO = daoFactory.getCMDeliverServiceUserDAO();
        CMDeliverServiceUser deliverUser = new CMDeliverServiceUser();
        deliverUser.CustomerID=in.CustomerID;
        if(!deliverUserDAO.isExist(deliverUser)){
            throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
        }
        
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        
        if(StringUtils.isNotEmpty(in.TerminalNo)){
            TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
            TBTerminal terminal = new TBTerminal();
            terminal.TerminalNo=in.TerminalNo;
            if(terminalDAO.isExist(terminal)){
                terminal = terminalDAO.find(terminal);
                setCols.add("TerminalNo", terminal.TerminalNo);
                setCols.add("DepartmentID", terminal.DepartmentID);
            }
        }else{
            if(StringUtils.isNotEmpty(in.DepartmentID)){
                MBDepartmentDAO departDAO = daoFactory.getMBDepartmentDAO();
                MBDepartment depart = new MBDepartment();
                depart.DepartmentID=in.DepartmentID;
                if(departDAO.isExist(depart)){
                    setCols.add("DepartmentID", in.DepartmentID);
                }
            }
        }
        setCols.checkAdd("FirstName", in.FirstName);
        setCols.checkAdd("LastName", in.LastName);
        setCols.checkAdd("Address", in.Address);
        if(StringUtils.isNotEmpty(in.SpecialFlag)){
            if(SysDict.CUSTOMER_SPECIAL_FLAG_YES.equals(in.SpecialFlag)
                    || SysDict.CUSTOMER_SPECIAL_FLAG_NO.equals(in.SpecialFlag)){
                setCols.add("SpecialFlag", in.SpecialFlag);
            }
        }
        if(StringUtils.isNotEmpty(in.DeliverFailHandle)){
            if(SysDict.DELIVER_FAIL_CONTACTUSER.equals(in.DeliverFailHandle)
                    || SysDict.DELIVER_FAIL_GOTOOFFICE.equals(in.DeliverFailHandle)
                    || SysDict.DELIVER_FAIL_RETURNTOSENDER.equals(in.DeliverFailHandle)){
                setCols.add("DeliverFailHandle", in.DeliverFailHandle);
            }
        }
        if(StringUtils.isNotEmpty(in.ServiceStatus)){
            if(SysDict.DELIVER_SERVICE_STATUS_NROMAL.equals(in.ServiceStatus)
                    || SysDict.DELIVER_SERVICE_STATUS_CANCEL.equals(in.ServiceStatus)
                    || SysDict.DELIVER_SERVICE_STATUS_UNACTIVE.equals(in.ServiceStatus)){
                setCols.add("ServiceStatus", in.ServiceStatus);
            }
        }
        if(StringUtils.isNotEmpty(in.Email) 
                || StringUtils.isNotEmpty(in.CustomerMobile)){
            CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
            //Email Mobile保存在CMCustomer表中，更新会员信息表
            JDBCFieldArray setCols0 = new JDBCFieldArray();
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            
            setCols0.checkAdd("Email", in.Email);
            setCols0.checkAdd("CustomerMobile", in.CustomerMobile);
            whereCols0.add("CustomerID", in.CustomerID);
            customerDAO.update(setCols0, whereCols0);
        }
        
        setCols.add("Remark", in.Remark);
        setCols.add("LastModifyTime", sysDateTime);
        
        whereCols.add("CustomerID", in.CustomerID);
        result = deliverUserDAO.update(setCols, whereCols);
        

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
