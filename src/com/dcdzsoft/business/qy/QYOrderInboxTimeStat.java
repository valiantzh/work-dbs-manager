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
 * <p>Description: 订单在箱时间统计 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class QYOrderInboxTimeStat extends ActionBean
{

    public RowSet doBusiness(InParamQYOrderInboxTimeStat in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		String reportID = DateUtils.timestampToString(sysDateTime);

		//////////////////////////
		if(in.EndDate != null && in.BeginDate != null)
        {
            if(in.EndDate.getTime() < in.BeginDate.getTime())
                in.BeginDate.setTime(in.EndDate.getTime());
        }
		
        String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        if(StringUtils.isNotEmpty(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("CompanyID", in.CompanyID);
        whereSQL.checkAdd("LogisticsID", in.LogisticsID);
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        if(StringUtils.isNotEmpty(in.TerminalName))
           whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
       
        whereSQL.checkAdd("StoredDate", ">=", in.BeginDate);
        whereSQL.checkAdd("StoredDate", "<=", in.EndDate);
        
        String sql = "SELECT '" + reportID + "' AS ReportID,"
                + "PackageID,TerminalNo,TerminalName,BoxNo,InBoxTime,"
                + "DepartmentID,DepartmentName,"
                + "CompanyID,CompanyName,CustomerMobile,"
                + "StoredTime,TakedTime,"
                + "PackageStatus,PackageStatusName FROM V_OrderStatInBoxTime a WHERE 1=1 ";
        sql = sql + whereSQL.getPreparedWhereSQL() + limitSQL + "";
        
        java.util.LinkedList list = new java.util.LinkedList();
        list.add("StoredTime");

        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);

        return rset;
    }
}
