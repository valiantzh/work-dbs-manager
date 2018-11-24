package com.dcdzsoft.business.qy;

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
 * <p>Description: 柜体使用情况统计数量(按月) </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class QYTerminalUsageMonthlyCount extends ActionBean
{

    public int doBusiness(InParamQYTerminalUsageMonthlyCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //////////////////////////
        String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        if(StringUtils.isNotEmpty(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        if(StringUtils.isNotEmpty(in.TerminalName))
           whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
       
        whereSQL.checkAdd("OccurYear", in.OccurYear);
        
        String viewName = "V_QYTerminalUsageMonthly";
        String sql = "SELECT TerminalNo  FROM "+viewName+" a WHERE 1=1 ";
        
        sql = sql + whereSQL.getPreparedWhereSQL() + limitSQL;

        RowSet rset = dbSession.executeQuery(sql,whereSQL);

        result = RowSetUtils.getCountOfRowSet(rset);

        return result;
    }
}
