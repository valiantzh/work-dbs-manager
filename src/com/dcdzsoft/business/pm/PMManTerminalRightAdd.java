package com.dcdzsoft.business.pm;

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
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * <p>
 * Description: 投递员柜体权限增加
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: dcdzsoft
 * </p>
 * 
 * @author 王中立
 * @version 1.0
 */

public class PMManTerminalRightAdd extends ActionBean {

	public int doBusiness(InParamPMManTerminalRightAdd in) throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		int result = 0;

		// 1. 验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID) || StringUtils.isEmpty(in.PostmanID)
				|| StringUtils.isEmpty(in.TerminalNoList))
			throw new EduException(ErrorCode.ERR_PARMERR);

		// 2. 调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		// 3. 调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

		// ///////////////////////////////////////////////////////////////////
		PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
		PMPostman postman = new PMPostman();
		postman.PostmanID = in.PostmanID;

		postman = postmanDAO.find(postman);

		// ////////////////////////////////////////////////////////////////////
		PMManTerminalRightDAO manTerminalRightDAO = daoFactory.getPMManTerminalRightDAO();
		
		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		String[] lockerList = in.TerminalNoList.split(",");
		for (String TerminalNo : lockerList) {
			TBTerminal terminal = new TBTerminal();
			terminal.TerminalNo = TerminalNo;
			if (!terminalDAO.isExist(terminal)) {
				throw new EduException(ErrorCode.ERR_TBTERMINALNODATA);
			}

			PMManTerminalRight manTerminalRight = new PMManTerminalRight();
			manTerminalRight.TerminalNo = TerminalNo;
			manTerminalRight.PostmanID = in.PostmanID;

			if (!manTerminalRightDAO.isExist(manTerminalRight)) {
				manTerminalRightDAO.insert(manTerminalRight);
			}
		}

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
