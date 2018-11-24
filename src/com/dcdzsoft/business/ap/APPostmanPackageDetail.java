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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递员单个包裹查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APPostmanPackageDetail extends ActionBean
{

    public OutParamAPPostmanPackageDetail doBusiness(InParamAPPostmanPackageDetail in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPPostmanPackageDetail out = new OutParamAPPostmanPackageDetail();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PostmanID)
			||StringUtils.isEmpty(in.CustomerMobile)
			||StringUtils.isEmpty(in.PackageID)
			||in.StoredTime == null 
			||StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);


		///////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
		PTInBoxPackage inboxPack = new PTInBoxPackage();
		inboxPack.TerminalNo = in.TerminalNo;
		inboxPack.PackageID = in.PackageID;
		
		String postmanid = "";
		String companyid = "";
		
		try
		{
			inboxPack = inboxPackDAO.find(inboxPack);
			
			out.TerminalNo = inboxPack.TerminalNo;
			out.BoxNo = inboxPack.BoxNo;
			out.PackageID = inboxPack.PackageID;
			out.CustomerMobile = inboxPack.CustomerMobile;
			out.StoredTime = inboxPack.StoredTime;
			out.PackageStatus = inboxPack.PackageStatus;
			out.TradeWaterNo = inboxPack.TradeWaterNo;
			
			postmanid = inboxPack.PostmanID;
			companyid = inboxPack.CompanyID;
		}catch(EduException e){
			PTDeliverHistoryDAO historyDAO = daoFactory.getPTDeliverHistoryDAO();
			PTDeliverHistory history = new PTDeliverHistory();
			
			history.PackageID = in.PackageID;
			history.TerminalNo = in.TerminalNo;
			history.StoredTime = in.StoredTime;
			
			history = historyDAO.find(history);
			
			out.TerminalNo = history.TerminalNo;
			out.BoxNo = history.BoxNo;
			out.PackageID = history.PackageID;
			out.CustomerMobile = history.CustomerMobile;
			out.StoredTime = history.StoredTime;
			out.TakedTime = history.TakedTime;
			out.PackageStatus = history.PackageStatus;
			out.TradeWaterNo = history.TradeWaterNo;
			
			postmanid = history.PostmanID;
			companyid = history.CompanyID;
		}
		
		///////////////////////////////////////////////////////////////////////////////////////
		if(!in.PostmanID.equals(postmanid) || !in.CustomerMobile.equals(out.CustomerMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
        PMCompany company = new PMCompany();
        company.CompanyID = companyid;
        
        company = companyDAO.find(company);
		
        out.CompanyName = company.CompanyName;
        
        /////////////////////////////////////////////////////////////////////////////////////////
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
        
        return out;
    }
}
