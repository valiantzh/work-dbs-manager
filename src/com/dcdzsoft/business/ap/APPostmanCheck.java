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
 * <p>Description: 投递员身份验证 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APPostmanCheck extends ActionBean
{

    public OutParamAPPostmanCheck doBusiness(InParamAPPostmanCheck in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPPostmanCheck out = new OutParamAPPostmanCheck();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PostmanID)
			||StringUtils.isEmpty(in.Password))
			throw new EduException(ErrorCode.ERR_PARMERR);


		String postmanid = "";
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();

        //////////////////////////////////////////////////////////
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("PostmanID", in.PostmanID);
        RowSet rset = postmanDAO.select(whereCols0);
        if (RowSetUtils.rowsetNext(rset)) {
            postmanid = RowSetUtils.getStringValue(rset, "PostmanID");
        } else {
            whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID", in.PostmanID);
            rset = postmanDAO.select(whereCols0);

            if (RowSetUtils.rowsetNext(rset)) {
                postmanid = RowSetUtils.getStringValue(rset, "PostmanID");
            } else {
            	//用户名或密码错误
            	 throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
            }
        }
        
        PMPostman postman = new PMPostman();
        postman.PostmanID = postmanid;
        postman = postmanDAO.find(postman);
        
        if(!postman.PostmanStatus.equals(SysDict.POSTMAN_STATUS_NORMAL))
        {
        	throw new EduException(ErrorCode.ERR_OPERHAVECANCEL);
        }
        
        if (!postman.Password.equalsIgnoreCase(in.Password))
            throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);

        ///////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////
        PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
        PMCompany company = new PMCompany();
        company.CompanyID = postman.CompanyID;
        
        company = companyDAO.find(company);
        
        //////////////////////////////////////////////////////////
        out.PostmanID = postman.PostmanID;
        out.PostmanName = postman.PostmanName;
        out.Mobile = postman.Mobile;
        out.CompanyName = company.CompanyName;
        out.CreateTime = postman.CreateTime;

        return out;
    }
}
