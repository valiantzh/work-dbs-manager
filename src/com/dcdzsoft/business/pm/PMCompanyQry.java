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
 * <p>Description: 投递公司查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMCompanyQry extends ActionBean {

    public RowSet doBusiness(InParamPMCompanyQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。
        //if (StringUtils.isEmpty(in.OperID))
        //    throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        String limitSQL = "";//utilDAO.getLimitSQL(operOnline.DepartmentID);
        if(StringUtils.isNotEmpty(in.OperID)){
            OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
            limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        }
        
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
        
        String sql = "SELECT * FROM V_Company a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
        
        java.util.LinkedList list = new java.util.LinkedList();
        list.add("CompanyID");

        rset = dbSession.executeQuery(sql,list,in.recordBegin,in.recordNum,whereSQL);

        return rset;
    }
}
