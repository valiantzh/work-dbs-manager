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
 * Description: 收件人单个包裹查询
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

public class APCustomerPackageDetail extends ActionBean {

	public OutParamAPCustomerPackageDetail doBusiness(
			InParamAPCustomerPackageDetail in) throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		OutParamAPCustomerPackageDetail out = new OutParamAPCustomerPackageDetail();

		// 1. 验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CustomerMobile)
				|| StringUtils.isEmpty(in.PackageID) 
				|| in.StoredTime == null
				|| StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		// /////////////////////////////////////////////////////////////////////////
		// ////////////////////////////////////////////////////////////////////////
		PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
		PTInBoxPackage inboxPack = new PTInBoxPackage();
		inboxPack.TerminalNo = in.TerminalNo;
		inboxPack.PackageID = in.PackageID;

		String companyid = "";
		String customerMobile = "";
		
		try {
			inboxPack = inboxPackDAO.find(inboxPack);

			out.TerminalNo = inboxPack.TerminalNo;
			out.PostmanID = inboxPack.PostmanID;
			out.PackageID = inboxPack.PackageID;
			out.StoredTime = inboxPack.StoredTime;
			out.PackageStatus = inboxPack.PackageStatus;
			out.TradeWaterNo = inboxPack.TradeWaterNo;
			
			customerMobile = inboxPack.CustomerMobile;
			companyid = inboxPack.CompanyID;
		} catch (EduException e) {
			PTDeliverHistoryDAO historyDAO = daoFactory.getPTDeliverHistoryDAO();
			PTDeliverHistory history = new PTDeliverHistory();

			history.PackageID = in.PackageID;
			history.TerminalNo = in.TerminalNo;
			history.StoredTime = in.StoredTime;

			history = historyDAO.find(history);

			out.TerminalNo = history.TerminalNo;
			out.PostmanID = history.PostmanID;
			out.PackageID = history.PackageID;
			out.StoredTime = history.StoredTime;
			out.TakedTime = history.TakedTime;
			out.PackageStatus = history.PackageStatus;
			out.TradeWaterNo = history.TradeWaterNo;
			
			customerMobile = history.CustomerMobile;
			companyid = history.CompanyID;
		}

		// /////////////////////////////////////////////////////////////////////////////////////
		if (!in.CustomerMobile.equals(customerMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);

		////////////////////////////////////////////////////////////////////////////////////////
		PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
		PMPostman postman = new PMPostman();
		postman.PostmanID = out.PostmanID;
		
		postman = postmanDAO.find(postman);
		
		out.PostmanName = postman.PostmanName;
		
		////////////////////////////////////////////////////////////////////////////////////////////
		PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
		PMCompany company = new PMCompany();
		company.CompanyID = companyid;

		company = companyDAO.find(company);

		out.CompanyName = company.CompanyName;

		// ///////////////////////////////////////////////////////////////////////////////////////
		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		TBTerminal terminal = new TBTerminal();
		terminal.TerminalNo = in.TerminalNo;

		terminal = terminalDAO.find(terminal);

		out.Location = terminal.Location;
		
		////////////////////////////////////////////////////////////////////////////////////
		PADictionaryDAO dictionaryDAO = daoFactory.getPADictionaryDAO();
		PADictionary dict = new PADictionary();
		dict.DictTypeID = 33001;
		dict.DictID = out.PackageStatus;

		dict = dictionaryDAO.find(dict);

		out.PackageStatusName = dict.DictName;
		
		////////////////////////////////////////////////////////////////////////////////////////
		MBPwdShortMsgDAO shortMsgDAO = daoFactory.getMBPwdShortMsgDAO();
		JDBCFieldArray whereCols2 = new JDBCFieldArray();
		whereCols2.add("PackageID", out.PackageID);
		whereCols2.add("TerminalNo", out.TerminalNo);
		whereCols2.add("StoredTime", out.StoredTime);
		
		RowSet rset = shortMsgDAO.select(whereCols2);
		while(RowSetUtils.rowsetNext(rset)){
			out.OpenBoxKey = RowSetUtils.getStringValue(rset, "OpenBoxKey");
		}

		return out;
	}
}
