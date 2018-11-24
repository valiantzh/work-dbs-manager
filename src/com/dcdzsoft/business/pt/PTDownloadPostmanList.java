package com.dcdzsoft.business.pt;

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
 * <p>Description: 下载投递员列表信息 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PTDownloadPostmanList extends ActionBean
{

    public java.util.List<OutParamPTDownloadPostmanList> doBusiness(InParamPTDownloadPostmanList in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
        java.util.List<OutParamPTDownloadPostmanList> outList = new java.util.LinkedList<OutParamPTDownloadPostmanList> ();
        RowSet rset = null;
        
		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.TradeWaterNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		///////
		PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		whereCols.add("PostmanStatus", "0");//下载正常在用的投递员
		
		rset = postmanDAO.select(whereCols);
		while(RowSetUtils.rowsetNext(rset)){
			OutParamPTDownloadPostmanList out = new OutParamPTDownloadPostmanList();
			out.PostmanID = RowSetUtils.getStringValue(rset,"PostmanID");
			out.CompanyID = RowSetUtils.getStringValue(rset,"CompanyID");
			out.DepartmentID = RowSetUtils.getStringValue(rset,"DepartmentID");
			out.PostmanName = RowSetUtils.getStringValue(rset,"PostmanName");
			out.PostmanType = RowSetUtils.getStringValue(rset,"PostmanType");
			out.Password = RowSetUtils.getStringValue(rset,"Password");
			out.BindCardID = RowSetUtils.getStringValue(rset,"BindCardID");
			out.BindMobile = RowSetUtils.getStringValue(rset,"BindMobile");
			out.IDCard = RowSetUtils.getStringValue(rset,"IDCard");
			out.OfficeTel = RowSetUtils.getStringValue(rset,"OfficeTel");
			out.Mobile = RowSetUtils.getStringValue(rset,"Mobile");
			out.Email = RowSetUtils.getStringValue(rset,"Email");
			out.ContactAddress = RowSetUtils.getStringValue(rset,"ContactAddress");
			out.InputMobileFlag = RowSetUtils.getStringValue(rset,"InputMobileFlag");
			outList.add(out);
		}

        return outList;
    }
}
