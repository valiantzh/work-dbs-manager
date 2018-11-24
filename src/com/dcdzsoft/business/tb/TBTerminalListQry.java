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
import com.dcdzsoft.config.ApplicationConfig;

/**
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * <p>
 * Description: 柜体信息列表查询
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

public class TBTerminalListQry extends ActionBean {

	public RowSet doBusiness(InParamTBTerminalListQry in) throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		RowSet rset = null;

		// 1. 验证输入参数是否有效，如果无效返回-1。
		//if (StringUtils.isEmpty(in.OperID))
		//	throw new EduException(ErrorCode.ERR_PARMERR);

		// 2. 调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		String limitSQL = "";
		String operOnlineDepartmentID = "";
		
		if(StringUtils.isNotEmpty(in.OperID))
		{
			OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
			limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
			
			operOnlineDepartmentID = operOnline.DepartmentID;
		}
		
		// ///////////////////////////////////////////////////////////
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		
		if("1".equalsIgnoreCase(in.IncludeFlag)){
			MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
			
			if(StringUtils.isNotEmpty(in.DepartmentID)){
				if(StringUtils.isNotEmpty(in.OperID)){
					MBDepartment department1 = new MBDepartment();
					department1.DepartmentID = operOnlineDepartmentID;
					
					department1 = departmentDAO.find(department1);
					
					MBDepartment department2 = new MBDepartment();
					department2.DepartmentID = in.DepartmentID;
					
					department2 = departmentDAO.find(department2);
					
					if(department1.DepartLevel >= department2.DepartLevel){
						limitSQL = utilDAO.getLimitSQL(in.DepartmentID);
					}
				}else{
					limitSQL = utilDAO.getLimitSQL(in.DepartmentID);
				}
			}
		}else{
			whereSQL.checkAdd("DepartmentID", in.DepartmentID);
		}
		
		String headerDepartmentID = ApplicationConfig.getInstance().getHeaderDepartmentID();
		if(StringUtils.isNotEmpty(headerDepartmentID)){
		    limitSQL += " "+utilDAO.getLimitSQL(headerDepartmentID);
		}
		whereSQL.checkAdd("TerminalStatus", in.TerminalStatus);

		String sql = "SELECT TerminalNo,TerminalName,DepartmentID,TerminalStatus FROM TBTerminal a WHERE 1=1 "
				+ whereSQL.getPreparedWhereSQL() + limitSQL
				+ " ORDER BY TerminalNo";

		rset = dbSession.executeQuery(sql, whereSQL);

		return rset;
	}
}
