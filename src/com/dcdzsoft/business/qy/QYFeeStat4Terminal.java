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
 * <p>Description: 逾期收费情况统计4柜体 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class QYFeeStat4Terminal extends ActionBean
{

    public RowSet doBusiness(InParamQYFeeStat4Terminal in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
		
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		String reportID = DateUtils.timestampToString(sysDateTime);
		
		if(in.BeginDate == null)
		{
			in.BeginDate = DateUtils.changeMonth(utilDAO.getCurrentDate(), -1);
		}
	    
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		whereSQL.checkAdd("StoredDate", ">=", in.BeginDate);
	    whereSQL.checkAdd("StoredDate", "<=", in.EndDate);   
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
	    if(StringUtils.isNotEmpty(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        //whereSQL.checkAdd("TerminalName", in.TerminalName);
        if(StringUtils.isNotEmpty(in.TerminalName))
           whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");

        String sql = "SELECT '" + reportID + "' AS ReportID,TerminalNo,TerminalName,SUM(PayedAmt) AS ExpiredNum FROM V_HistoryPackage a WHERE 1=1 ";
		sql = sql + whereSQL.getPreparedWhereSQL() + limitSQL + " GROUP BY TerminalNo,TerminalName ";
		
		java.util.LinkedList list = new java.util.LinkedList();
        list.add("TerminalNo");

        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);


        return rset;
    }
}
