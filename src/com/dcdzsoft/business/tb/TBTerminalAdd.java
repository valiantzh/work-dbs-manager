package com.dcdzsoft.business.tb;

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
 * <p>Description: 柜体信息增加 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class TBTerminalAdd extends ActionBean
{

    public int doBusiness(InParamTBTerminalAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.DepartmentID)
			||StringUtils.isEmpty(in.InitPasswd))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		//安装密码唯一
		MBSignInfoDAO signInfoDAO = daoFactory.getMBSignInfoDAO();
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("InitPasswd", in.InitPasswd);
        whereCols0.add("TerminalNo", "<>", in.TerminalNo);
        if(signInfoDAO.isExist(whereCols0)>0){
            throw new EduException(ErrorCode.ERR_TERMINALINITPWDEXISTS);
        }
        //检查设备号
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        TBTerminal terminal = new TBTerminal();
        terminal.TerminalNo = in.TerminalNo;
        if(terminalDAO.isExist(terminal)){
            throw new EduException(ErrorCode.ERR_TERMINALNOEXISTS);
        }
        
        //检查系统编号
        SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
        SMSystemInfo systemInfo = new SMSystemInfo();
        systemInfo.SystemID = in.SystemID;
        if(!systemInfoDAO.isExist(systemInfo)){
            throw new EduException(ErrorCode.ERR_PARMERR);
        }
        
        
        //检查运营网点
        MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
        MBDepartment department = new MBDepartment();
        department.DepartmentID = in.DepartmentID;
        if(!departmentDAO.isExist(department)){
            throw new EduException(ErrorCode.ERR_DEPARTMENTNOTEXISTS);
        }
            
        
        //记录安装密码
        MBSignInfo signInfo = new MBSignInfo();
        signInfo.TerminalNo = in.TerminalNo;
        signInfo.InitPasswd = in.InitPasswd;
        signInfo.LastModifyTime = sysDateTime;
        signInfo.SignTime = sysDateTime;
        signInfo.OnlineStatus = "0";
        signInfo.Remark = in.SystemID;//Remark-保存系统编号：SystemID
        signInfoDAO.insert(signInfo);
        

		//记录设备信息
		terminal.TerminalName = in.TerminalName;
		terminal.DepartmentID = in.DepartmentID;
		terminal.MBDeviceNo = in.MBDeviceNo;
		terminal.BoxNum = in.BoxNum;
		terminal.DeskNum = in.DeskNum;
		terminal.Location = in.Location;
		terminal.Latlon = in.Latlon;
		terminal.OfBureau = in.OfBureau;
		terminal.OfSegment = in.OfSegment;
		terminal.CreateTime = sysDateTime;
		terminal.TerminalStatus = SysDict.TERMINAL_STATUS_NORMAL;
		terminal.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_NO;
		terminal.LastModifyTime = sysDateTime;
		terminalDAO.insert(terminal);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = ""+in.TerminalNo;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
