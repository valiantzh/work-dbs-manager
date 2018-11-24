package com.dcdzsoft.business.mb;

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
 * <p>Description: 查询运营网点信息（列表结构返回） </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBDepartListQry extends ActionBean {

    public RowSet doBusiness(InParamMBDepartListQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

      //1.	验证输入参数是否有效，如果无效返回-1。
        String limitSQL = "";
        
        if (StringUtils.isNotEmpty(in.OperID))
        {
        	//2.	调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
        	OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
        	limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        }
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        if(StringUtils.isNotBlank(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        
        whereSQL.checkAdd("ParentDepartID", in.ParentDepartID);
        whereSQL.checkAdd("DepartLevel", in.DepartLevel);
        
        String sql = "SELECT DepartmentID,DepartmentName,DepartLevel,Level1,Level2,Level3,Level4 FROM MBDepartment a WHERE 1=1 "
                + whereSQL.getPreparedWhereSQL() + limitSQL + " ORDER BY DepartmentID";

        rset = dbSession.executeQuery(sql,whereSQL);

        return rset;
    }
}
