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
 * <p>Title: 智能自助洗衣柜系统</p>
 * <p>Description: 获取投递员列表 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMGetPostmanList extends ActionBean
{

    public java.util.List<OutParamPMGetPostmanList> doBusiness(InParamPMGetPostmanList in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.util.List<OutParamPMGetPostmanList> outList = new java.util.LinkedList<OutParamPMGetPostmanList>();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//////////////////////////////////////////////////////////////////////
		PMPostmanDAO memberDAO = daoFactory.getPMPostmanDAO();
		JDBCFieldArray whereColsDummy = new JDBCFieldArray();
		
		rset = memberDAO.select(whereColsDummy);
		
		 while(RowSetUtils.rowsetNext(rset)){
			 OutParamPMGetPostmanList outParam = new OutParamPMGetPostmanList();
             outParam.PostmanID = RowSetUtils.getStringValue(rset,"PostmanID");
             outParam.CompanyID = RowSetUtils.getStringValue(rset,"CompanyID");
             outParam.PostmanName = RowSetUtils.getStringValue(rset,"PostmanName");
             outParam.PostmanType = RowSetUtils.getStringValue(rset,"PostmanType");
             outParam.Password = RowSetUtils.getStringValue(rset,"Password");
             outParam.OfficeTel = RowSetUtils.getStringValue(rset,"OfficeTel");
             outParam.Mobile = RowSetUtils.getStringValue(rset,"Mobile");
             outParam.Email = RowSetUtils.getStringValue(rset,"Email");
             outParam.ContactAddress = RowSetUtils.getStringValue(rset,"ContactAddress");
             outParam.InputMobileFlag = RowSetUtils.getStringValue(rset,"InputMobileFlag");
             outParam.Remark = RowSetUtils.getStringValue(rset,"Remark");

             outList.add(outParam);
         }


        return outList;
    }
}
