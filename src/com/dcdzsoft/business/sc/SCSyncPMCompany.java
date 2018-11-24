package com.dcdzsoft.business.sc;

import java.util.LinkedList;
import java.util.List;

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
 * <p>Description: 同步投递公司信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncPMCompany extends ActionBean
{

    public java.util.List<OutParamSCSyncPMCompany> doBusiness(InParamSCSyncPMCompany in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
		//1.	验证输入参数是否有效，如果无效返回-1。
		if (in.LastSyncTime == null )
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		java.util.List<OutParamSCSyncPMCompany> outList = new java.util.LinkedList<OutParamSCSyncPMCompany>();
		
		PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		
		List<String> orderList = new LinkedList<String>();
		orderList.add("CreateTime");
		
		RowSet rset = companyDAO.select(whereCols,orderList);
		
		while(RowSetUtils.rowsetNext(rset)){
			OutParamSCSyncPMCompany outParam = new OutParamSCSyncPMCompany();
			
			outParam.companyNo = RowSetUtils.getStringValue(rset, "CompanyID");
			outParam.companyName = RowSetUtils.getStringValue(rset, "CompanyName");
			outParam.createTime = RowSetUtils.getTimestampValue(rset, "CreateTime");
			//outParam.LastModifyTime = RowSetUtils.getTimestampValue(rset, "LastModifyTime");
			
			outList.add(outParam);
		}
		
		return outList;

    }
}
