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
import com.dcdzsoft.businessproxy.MonitorProxy;
import com.dcdzsoft.businessproxy.PushBusinessProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 柜体状态修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class TBTerminalModStatus extends ActionBean {

    public String doBusiness(InParamTBTerminalModStatus in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = in.TerminalStatus;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalStatus))
            throw new EduException(ErrorCode.ERR_PARMERR);

        if (StringUtils.isEmpty(in.RemoteFlag))
            in.RemoteFlag = SysDict.OPER_FLAG_REMOTE;

        if (in.RemoteFlag.equalsIgnoreCase(SysDict.OPER_FLAG_REMOTE)) {
            //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
            OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
        }

        ///判断状态
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        TBTerminal terminal = new TBTerminal();
        terminal.TerminalNo = in.TerminalNo;
        
        terminal = terminalDAO.find(terminal);
        
        if(terminal.TerminalStatus.equalsIgnoreCase(in.TerminalStatus))
        	return result;
        
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        
        ///修改状态
        JDBCFieldArray setCols1 = new JDBCFieldArray();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();

        switch(in.TerminalStatus){
        case SysDict.TERMINAL_STATUS_NORMAL:
        case SysDict.TERMINAL_STATUS_LOCKED:
        case SysDict.TERMINAL_STATUS_FAULT:
        case SysDict.TERMINAL_STATUS_FAULTLOCKED:
        case SysDict.TERMINAL_STATUS_FAULT_POWERFAIL:
        case SysDict.TERMINAL_STATUS_CANCEL:
            //允许人工修改的柜体状态
            setCols1.add("TerminalStatus", in.TerminalStatus);
            setCols1.add("LastModifyTime", sysDateTime);
            break;
        case SysDict.TERMINAL_STATUS_FAULT_OFFLINE:
        case SysDict.TERMINAL_STATUS_FAULT_BOX:
            //离线状态和柜体箱故障不能由人工设置
            return result;
        default:
            return result;
        }
        
        whereCols1.add("TerminalNo", in.TerminalNo);

        terminalDAO.update(setCols1, whereCols1);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = null;
        log.StationAddr = "";
        log.Remark = in.RemoteFlag;

        commonDAO.addOperatorLog(log);

        if (in.RemoteFlag.equalsIgnoreCase(SysDict.OPER_FLAG_REMOTE))
        {
            ///推送到设备端
        	try
        	{
        		PushBusinessProxy.doBusiness(in);
        	}catch(EduException e)
        	{
        		
        	}
        }
        
        //广播消息
        if(!SysDict.TERMINAL_STATUS_NORMAL.equals(in.TerminalStatus))
        {
        	//插入异常消息
        	commonDAO.insertAlert(in.TerminalNo, SysDict.ALERT_TYPE_DEVICEFAULT, SysDict.ALERT_LEVEL_COMMON, "", "");
        	//广播消息
        	MonitorProxy.broadcastAlert(in.TerminalNo, SysDict.ALERT_TYPE_DEVICEFAULT, SysDict.ALERT_LEVEL_COMMON, "", "");
        }

        return result;
    }
}
