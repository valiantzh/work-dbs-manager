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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递公司查询数量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMCompanyQryCount extends ActionBean
{

    public int doBusiness(InParamPMCompanyQryCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
		
		/////////////////////////////////////////////////////////////
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		whereSQL.checkAdd("CompanyID", in.CompanyID);
        if(StringUtils.isNotEmpty(in.CompanyName))
            whereSQL.add("CompanyName", " LIKE ", "%" + in.CompanyName + "%");
        
        whereSQL.checkAdd("LogisticsID", in.LogisticsID);
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        if(StringUtils.isNotBlank(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        String sql = "SELECT COUNT(CompanyID) FROM V_Company a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
        
        result = dbSession.executeQueryCount(sql,whereSQL);
        
        return result;
    }
}
