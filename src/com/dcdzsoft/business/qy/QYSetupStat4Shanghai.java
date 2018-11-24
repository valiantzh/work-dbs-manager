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
 * <p>Description: 安装布放情况统计 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class QYSetupStat4Shanghai extends ActionBean
{

    public RowSet doBusiness(InParamQYSetupStat4Shanghai in) throws EduException
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

		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		if(in.EndDate != null && in.BeginDate != null)
		{
			if(in.EndDate.getTime() < in.BeginDate.getTime())
				in.BeginDate.setTime(in.EndDate.getTime());
		}
		
		int diffDays = DateUtils.diffDate(in.EndDate, in.BeginDate) + 1;
		String reportID = DateUtils.timestampToString(sysDateTime);
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		whereSQL.checkAdd("StoredDate", ">=", in.BeginDate);
	    whereSQL.checkAdd("StoredDate", "<=", in.EndDate);   
	    
	    String sql = "";
	    
	    if(StringUtils.isEmpty(in.ParentDepartID) && StringUtils.isEmpty(in.DepartmentID))
	    {
	    	sql = "SELECT '" + reportID + "' AS ReportID ,a.ParentDepartID AS DepartmentID,a.ParentDepartName AS DepartmentName,";
			sql+=" COUNT(*) AS TerminalCount,SUM(a.BoxNum) AS BoxCount,";
			sql+=" IFNULL(b.YzManCount,0) AS YzManCount,IFNULL(b.ShManCount,0) AS ShManCount,";
			sql+=" IFNULL(c.YzCount,0) AS YzCount,IFNULL(c.ShCount,0) AS ShCount,IFNULL(c.TotalCount,0) AS TotalCount,";
			sql+=" TRUNCATE((IFNULL(c.YzCount,0)/IFNULL(c.TotalCount,1))*100,2) AS YzDeliveryRate,";
			sql+=" TRUNCATE((IFNULL(c.TotalCount,0)/SUM(a.BoxNum)/" + diffDays + ")*100,2) AS UsedRate";
			sql+=" FROM V_TerminalStat00 a ";
			sql+="     LEFT OUTER JOIN V_PostmanStat11 b ON(b.ParentDepartID=a.ParentDepartID)";
			sql+="     LEFT OUTER JOIN (SELECT ParentDepartID,ParentDepartName,SUM(YzCount) AS YzCount,SUM(ShCount) AS ShCount,SUM(TotalCount) AS TotalCount ";
			sql+="     FROM V_DeliveryStat2 WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
			sql+="     GROUP BY ParentDepartID,ParentDepartName) c ON(c.ParentDepartID=a.ParentDepartID)";
			sql+=" WHERE 1=1 ";
			
			if(StringUtils.isNotEmpty(in.LocationType))
				sql+=" AND a.LocationType = " + StringUtils.addQuote(in.LocationType);
			
			sql+=limitSQL;
			sql+=" GROUP BY a.ParentDepartID,a.ParentDepartName";
	    }else
	    {
			sql = "SELECT '" + reportID + "' AS ReportID ,a.DepartmentID,a.DepartmentName,";
			sql+=" COUNT(*) AS TerminalCount,SUM(a.BoxNum) AS BoxCount,";
			sql+=" IFNULL(b.YzManCount,0) AS YzManCount,IFNULL(b.ShManCount,0) AS ShManCount,";
			sql+=" IFNULL(c.YzCount,0) AS YzCount,IFNULL(c.ShCount,0) AS ShCount,IFNULL(c.TotalCount,0) AS TotalCount,";
			sql+=" TRUNCATE((IFNULL(c.YzCount,0)/IFNULL(c.TotalCount,1))*100,2) AS YzDeliveryRate,";
			sql+=" TRUNCATE((IFNULL(c.TotalCount,0)/SUM(a.BoxNum)/" + diffDays + ")*100,2) AS UsedRate";
			sql+=" FROM V_TerminalStat0 a ";
			sql+="     LEFT OUTER JOIN V_PostmanStat b ON(b.DepartmentID=a.DepartmentID)";
			sql+="     LEFT OUTER JOIN (SELECT DepartmentID,DepartmentName,SUM(YzCount) AS YzCount,SUM(ShCount) AS ShCount,SUM(TotalCount) AS TotalCount ";
			sql+="     FROM V_DeliveryStat2 WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
			sql+="     GROUP BY DepartmentID,DepartmentName) c ON(c.DepartmentID=a.DepartmentID)";
			sql+=" WHERE 1=1 ";
			
			if(StringUtils.isNotEmpty(in.ParentDepartID))
				sql+=" AND a.ParentDepartID = " + StringUtils.addQuote(in.ParentDepartID);
			
			if(StringUtils.isNotEmpty(in.DepartmentID))
				sql+=utilDAO.getFlagInSQL("a.DepartmentID", in.DepartmentID);
			
			if(StringUtils.isNotEmpty(in.LocationType))
				sql+=" AND a.LocationType = " + StringUtils.addQuote(in.LocationType);
			
			sql+=limitSQL;
			sql+=" GROUP BY a.DepartmentID,a.DepartmentName";
	    }
		
		rset = dbSession.executeQuery(sql,whereSQL);

        return rset;
    }
}
