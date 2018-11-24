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
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * <p>
 * Description: 现场管理员登录
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

public class MBSpotAdminLogin extends ActionBean {

	public OutParamMBSpotAdminLogin doBusiness(InParamMBSpotAdminLogin in) throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		OutParamMBSpotAdminLogin out = new OutParamMBSpotAdminLogin();

		// 1. 验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		// ///////////////////////////////////////////////////////////////////
		OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
		OPOperator oper = new OPOperator();
		oper.OperID = in.SpotAdminID;

		try {
			oper = operDAO.find(oper);
		} catch (EduException e) {
			JDBCFieldArray whereCols = new JDBCFieldArray();
			whereCols.add("UserID", in.SpotAdminID);
			RowSet rset1 = operDAO.select(whereCols);
			if (RowSetUtils.rowsetNext(rset1)) {
				oper.OperID = RowSetUtils.getStringValue(rset1, "OperID");
				oper = operDAO.find(oper);
			} else {
				throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
			}

		}

		if (oper.OperStatus.compareTo("0") != 0)
			throw new EduException(ErrorCode.ERR_OPERSTATUSABNORMAL);

		if (oper.OperPassword.compareTo(in.SpotAdminPwd) != 0)
			throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);

		/////////////////////////////////////////////////////////////////
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = oper.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = null;
		log.StationAddr = "";
		log.Remark = in.SpotAdminID;

		commonDAO.addOperatorLog(log);
		
		///////////////////////////////////////////////////
		out.SpotAdminID = oper.OperID;
		
		return out;
	}
}
