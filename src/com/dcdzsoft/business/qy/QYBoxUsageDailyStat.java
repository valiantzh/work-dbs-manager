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
 * <p>Description: 格口使用情况统计(每天) </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class QYBoxUsageDailyStat extends ActionBean
{

    public RowSet doBusiness(InParamQYBoxUsageDailyStat in) throws EduException
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
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

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
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        if(StringUtils.isNotEmpty(in.TerminalName))
           whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
       
        whereSQL.checkAdd("StatDate", ">=", in.BeginDate);
        whereSQL.checkAdd("StatDate", "<=", in.EndDate);
        
        String viewName = "V_QYTerminalUsage";
        
        String sql = "";
        String groupBy = "";
        
        if("1".equalsIgnoreCase(in.StatFlag)){//按运营网点统计
            sql = "SELECT StatDate,DepartmentID,"
                    + "SUM(BoxNum) AS BoxNum, "
                    + "CASE WHEN BoxNum IS NOT NULL AND SUM(BoxNum)>0 THEN TRUNCATE((SUM(UsedBoxNum)/SUM(BoxNum))*100,2) ELSE 0 "
                    + " END AS UsedBoxFreq,"
                    + "SUM(SmallNum) AS SmallNum,"
                    + "SUM(MediumNum) AS MediumNum,"
                    + "SUM(LargeNum) AS LargeNum,"
                    + "SUM(SuperNum) AS SuperNum,"
                    + "SUM(SuperSmallNum) AS SuperSmallNum,"
                    + "SUM(UsedBoxNum) AS UsedBoxNum, "
                    + "SUM(UsedSmallNum) AS UsedSmallNum,"
                    + "SUM(UsedMediumNum) AS UsedMediumNum,"
                    + "SUM(UsedLargeNum) AS UsedLargeNum,"
                    + "SUM(UsedSuperNum) AS UsedSuperNum,"
                    + "SUM(UsedSuperSmallNum) AS UsedSuperSmallNum"
                    + " FROM "+viewName+" a WHERE 1=1 ";
            groupBy = "GROUP BY StatDate,DepartmentID";
        }else{
            sql = "SELECT '" + reportID + "' AS ReportID,"
                    + "a.StatDate,a.TerminalNo,a.TerminalName,"
                    + "a.DepartmentID,"
                    + "BoxNum,"
                    + "UsedBoxNum,UsedBoxFreq,"
                    + "SmallNum,MediumNum,LargeNum,SuperNum,SuperSmallNum,FreshNum, "
                    + "UsedSmallNum,UsedMediumNum,UsedLargeNum,UsedSuperNum,UsedSuperSmallNum,UsedFreshNum "
                    + " FROM "+viewName+" a WHERE 1=1 ";
        }
        sql = sql + whereSQL.getPreparedWhereSQL() + limitSQL + " "+ groupBy;
        
        java.util.LinkedList list = new java.util.LinkedList();
        list.add("TerminalNo,StatDate");

        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);

        return rset;
    }
}
