package com.dcdzsoft.business.pt;

import java.util.ArrayList;
import java.util.LinkedList;

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
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 下载用户信息详情 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class PTTenantDetail extends ActionBean
{

    public java.util.List<OutParamPTTenantDetail> doBusiness(InParamPTTenantDetail in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
        java.util.List<OutParamPTTenantDetail> outList = new LinkedList<OutParamPTTenantDetail>();
        
		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.getTerminalNo()))
			throw new EduException(ErrorCode.ERR_PARMERR);

		RowSet rset = null;

		//String limitSQL = "";//utilDAO.getLimitSQL(operOnline.DepartmentID);
        //PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        //whereSQL.checkAdd("TerminalNo", in.getTerminalNo());
        //String sql = "SELECT * FROM V_CMDeliverServiceUser2pc a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
		//rset = dbSession.executeQuery(sql, whereSQL);
		
		JDBCFieldArray whereCols = new JDBCFieldArray();
		whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.getTerminalNo()) + "");      
        String sql = "SELECT * FROM V_CMDeliverServiceUser2pc a WHERE 1=1 " + whereCols.toWhereSQL();
        rset = dbSession.executeQuery(sql);
        while (RowSetUtils.rowsetNext(rset)) {
            OutParamPTTenantDetail out = new OutParamPTTenantDetail();
            out.setCustomerID(RowSetUtils.getStringValue(rset, "CustomerID"));
            out.setCustomerName(RowSetUtils.getStringValue(rset, "CustomerName"));
            out.setFirstName(RowSetUtils.getStringValue(rset, "FirstName"));
            out.setLastName(RowSetUtils.getStringValue(rset, "LastName"));
            out.setSpecialFlag(RowSetUtils.getStringValue(rset, "SpecialFlag"));
            out.setServiceStatus(RowSetUtils.getStringValue(rset, "ServiceStatus"));
            out.setDeliverFailHandle(RowSetUtils.getStringValue(rset, "DeliverFailHandle"));
            out.setAddress(RowSetUtils.getStringValue(rset, "Address"));
            out.setEmail(RowSetUtils.getStringValue(rset, "Email"));
            outList.add(out);
        }
        return outList;
    }
}
