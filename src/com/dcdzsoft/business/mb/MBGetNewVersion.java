package com.dcdzsoft.business.mb;

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
 * <p>Description: 获取最新软件版本信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBGetNewVersion extends ActionBean
{

    public OutParamMBGetNewVersion doBusiness(InParamMBGetNewVersion in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamMBGetNewVersion out = new OutParamMBGetNewVersion();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		TBTerminal terminal = new TBTerminal();
		terminal.TerminalNo  = in.TerminalNo;
		if(!terminalDAO.isExist(terminal)){
		    return out;
		    
		}
		terminal = terminalDAO.find(terminal);

		SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
		JDBCFieldArray whereColsDummy = new JDBCFieldArray();

		RowSet rset = systemInfoDAO.select(whereColsDummy);
		if(RowSetUtils.rowsetNext(rset)){
		    String MonServerIP = RowSetUtils.getStringValue(rset, "MonServerIP");
		    int MonServerPort = RowSetUtils.getIntValue(rset, "MonServerPort");
		    //out.DownloadUrl = "http://"+MonServerIP+":"+MonServerPort+"/dbsupdate/";
		    out.DownloadUrl = "http://115.236.1.210:8096/dbsupdate/apk/elocker-cainiao.apk";
		    out.TerminalNo  = in.TerminalNo;
		    out.SoftwareVersion = "1.0.01";
		    out.MD5Verify = "3F8975A901347A7F5815B3E94CD28EEC";
		}
		
		MBSignInfoDAO signInfoDAO = daoFactory.getMBSignInfoDAO();
		MBSignInfo signInfo = new MBSignInfo();
		signInfo.TerminalNo = in.TerminalNo;
		signInfo = signInfoDAO.find(signInfo);
		
        return out;
    }
}
