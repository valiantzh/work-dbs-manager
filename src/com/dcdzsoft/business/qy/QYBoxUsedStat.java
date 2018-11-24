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
 * <p>Description: 格口使用统计 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class QYBoxUsedStat extends ActionBean
{

    public RowSet doBusiness(InParamQYBoxUsedStat in) throws EduException
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
		
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		String reportID = DateUtils.timestampToString(sysDateTime);

		String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
	    
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
		if(StringUtils.isNotEmpty(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        //whereSQL.checkAdd("TerminalName", in.TerminalName);
        if(StringUtils.isNotEmpty(in.TerminalName))
           whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
        
		String sql = "SELECT '" + reportID + "' AS ReportID,TerminalNo,TerminalName,COUNT(*) AS BoxNum,SUM(UsedNum) AS UsedNum,SUM(FreeSmallNum) AS FreeSmallNum,SUM(FreeMediumNum) AS FreeMediumNum,SUM(FreeLargeNum) AS FreeLargeNum,SUM(FreeSuperNum) AS FreeSuperNum,SUM(FreeFreshNum) AS FreeFreshNum,SUM(FaultNum) AS FaultNum FROM V_TBBoxStat a WHERE 1=1 ";
		sql = sql + whereSQL.getPreparedWhereSQL() + limitSQL + "GROUP BY TerminalNo,TerminalName ";
		
		java.util.LinkedList list = new java.util.LinkedList();
        list.add("TerminalNo");

        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);

        return rset;
    }
}
