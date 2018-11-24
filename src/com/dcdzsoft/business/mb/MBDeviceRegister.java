package com.dcdzsoft.business.mb;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.constant.*;

import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 设备注册 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class MBDeviceRegister extends ActionBean
{

    public OutParamMBDeviceRegister doBusiness(InParamMBDeviceRegister in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamMBDeviceRegister out = new OutParamMBDeviceRegister();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.SignMac)
			//||StringUtils.isEmpty(in.SoftwareVersion)
			||StringUtils.isEmpty(in.InitPasswd))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//3.调用UtilDAO.getSysDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		//java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		MBSignInfoDAO signInfoDAO = daoFactory.getMBSignInfoDAO();
		JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("InitPasswd", in.InitPasswd);
        RowSet rset = signInfoDAO.select(whereCols0);
        if(RowSetUtils.rowsetNext(rset)){
            TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
            TBTerminal terminal = new TBTerminal();
            terminal.TerminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
            if(!terminalDAO.isExist(terminal)){
                throw new EduException(ErrorCode.ERR_TERMINALNOTEXISTS);
            }
            terminal = terminalDAO.find(terminal);
            
            SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
            SMSystemInfo systemInfo = new SMSystemInfo();
            systemInfo.SystemID = RowSetUtils.getStringValue(rset, "Remark");//Remark-保存系统编号：SystemID
            if(StringUtils.isEmpty(systemInfo.SystemID)){
                systemInfo.SystemID = Constant.DEFAULT_SYSTEM_ID;
            }
            systemInfo = systemInfoDAO.find(systemInfo);
            
            //记录签到MAC
            JDBCFieldArray setCols1 = new JDBCFieldArray();
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            setCols1.add("InitPasswd", systemInfo.InitPasswd);
            setCols1.add("SignMac", in.SignMac);
            whereCols1.add("TerminalNo", terminal.TerminalNo);
            signInfoDAO.update(setCols1, whereCols1);
            
            out.TerminalNo = terminal.TerminalNo;
            out.TerminalName = terminal.TerminalName;
            out.DepartmentID = terminal.DepartmentID;
            out.Location = terminal.Location;
            out.MBDeviceNo = terminal.MBDeviceNo;
            
            out.InitPasswd = systemInfo.InitPasswd;
            out.SystemID = systemInfo.SystemID;
            out.ServerUrl = "http://"+systemInfo.ServerIP+":"+systemInfo.ServerPort;
            out.ServerTime = sysDateTime;
            
            //TODO 设备信息、控制参数信息
            
        }else{
            throw new EduException(ErrorCode.ERR_TERMINALINITPWDINVALID);
        }

        return out;
    }
}
