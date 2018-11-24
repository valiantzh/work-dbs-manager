package com.dcdzsoft.business.ap;

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
 * Description: 投递员包裹列表
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

public class APPostmanPackageList extends ActionBean {

	public java.util.List<OutParamAPPostmanPackageList> doBusiness(
			InParamAPPostmanPackageList in) throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		RowSet rset = null;

		// 1. 验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PostmanID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.util.List<OutParamAPPostmanPackageList> outList = new java.util.LinkedList<OutParamAPPostmanPackageList>();

		String viewName = "";

		if (StringUtils.isEmpty(in.PackageStatus))
			viewName = "V_AllOrder";
		else if ("0".equalsIgnoreCase(in.PackageStatus)
				|| "12".equalsIgnoreCase(in.PackageStatus))
			viewName = "V_InboxPackage";
		else
			viewName = "V_HistoryPackage";

		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		if(in.BeginDate == null){
			in.BeginDate = DateUtils.addDate(utilDAO.getCurrentDate(), -30);
		}
		
		whereSQL.checkAdd("StoredDate", ">=", in.BeginDate);
		whereSQL.checkAdd("StoredDate", "<=", in.EndDate);
		whereSQL.add("PostmanID", in.PostmanID);
		whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
		whereSQL.checkAdd("PackageID", in.PackageID);
		whereSQL.checkAdd("TerminalNo", in.TerminalNo);

		if (StringUtils.isNotEmpty(in.PackageStatus)) {
			if ("12".equalsIgnoreCase(in.PackageStatus)) // 逾期件未取回
			{
				java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
				ControlParam ctrlParam = ControlParam.getInstance();

				int days = NumberUtils.parseInt(ctrlParam.recoveryTimeout);

				whereSQL.add("StoredTime", "<=",
						DateUtils.addTimeStamp(sysDateTime, -days));
			} else if ("11".equalsIgnoreCase(in.PackageStatus)) // 投递员取回(投递员取回,逾期回收)
			{
				whereSQL.addSQL(utilDAO.getFlagInSQL("PackageStatus", "5,6"));
			} else {
				whereSQL.add("PackageStatus", in.PackageStatus);
			}
		}

		String sql = "SELECT * FROM " + viewName + " a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
		java.util.List<String> orderByField = new java.util.LinkedList<String>();
		orderByField.add("LastModifyTime DESC");

		rset = dbSession.executeQuery(sql, orderByField, in.recordBegin,in.recordNum, whereSQL);

		while (RowSetUtils.rowsetNext(rset)) {
			OutParamAPPostmanPackageList outParam = new OutParamAPPostmanPackageList();

			outParam.TNO = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.TNE = RowSetUtils.getStringValue(rset, "TerminalName");
			outParam.PID = RowSetUtils.getStringValue(rset, "PackageID");
			outParam.MOB = RowSetUtils.getStringValue(rset, "CustomerMobile");
			outParam.STM = RowSetUtils.getTimestampValue(rset, "StoredTime");
			outParam.BXN = RowSetUtils.getStringValue(rset, "BoxNo");
			outParam.LTN = RowSetUtils.getStringValue(rset, "Location");
			
			if(!viewName.equalsIgnoreCase("V_InboxPackage"))
				outParam.TTM = RowSetUtils.getTimestampValue(rset, "TakedTime");
			
			outParam.STS = RowSetUtils.getStringValue(rset, "PackageStatus");

			outList.add(outParam);
		}

		return outList;
	}
}
