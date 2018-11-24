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
 * <p>Description: 投递量统计 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class QYStatDeliverOrder extends ActionBean
{

    public RowSet doBusiness(InParamQYStatDeliverOrder in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		if(StringUtils.isEmpty(in.StatPeriod)){
            in.StatPeriod = "3";
        }
		
		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
        String reportID = DateUtils.timestampToString(sysDateTime);
        
        String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        String OccurMonth = "";
        if(in.OccurMonth>0 && in.OccurYear>0){
            if(in.OccurMonth<10){
                OccurMonth = ""+in.OccurYear+"0"+in.OccurMonth;
            }else{
                OccurMonth = ""+in.OccurYear+in.OccurMonth;
            }
        }
        whereSQL.checkAdd("OccurDate", ">=", in.BeginDate);
        whereSQL.checkAdd("OccurDate", "<=", in.EndDate);
        
        whereSQL.checkAdd("OccurMonth", OccurMonth);
        whereSQL.checkAdd("OccurYear", in.OccurYear);   
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        if(StringUtils.isNotEmpty(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("LogisticsID", in.LogisticsID);
        whereSQL.checkAdd("CompanyID", in.CompanyID);
        whereSQL.checkAdd("PostmanID", in.PostmanID);
        whereSQL.checkAdd("PostmanMobile", in.PostmanMobile);
        whereSQL.checkAdd("CompanyID", in.CompanyID);
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        //whereSQL.checkAdd("TerminalName", in.TerminalName);
        if(StringUtils.isNotEmpty(in.TerminalName))
           whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
        
        ////////////////////////////////////////////////////////////////////////
        String yzLogisticsID = "0001";
        if("1".equals(in.MasterFlag))
            whereSQL.add("LogisticsID", yzLogisticsID);
        else if("0".equals(in.MasterFlag))
            whereSQL.add("LogisticsID","<>", yzLogisticsID);

        
        String period  = "";
        switch(in.StatPeriod){
        case "1"://每天统计
            period = "OccurDate";
            break;
        //case "2"://每周统计
        //    break;
        case "3"://每月统计
            period ="OccurMonth";
            break;
        //case "4"://每季统计
        //    break;
        case "5"://每年统计
            period ="OccurYear";
            break;
        default://每年统计
            period ="OccurDate";
            break;
        }
        //////////////////
        String viewName = "";
        String sql = "SELECT '" + reportID + "' AS ReportID,TerminalNo,TerminalName,Location,BoxNum,";
        
        String groupby = " GROUP BY TerminalNo,TerminalName,Location";
        
        if("1".equals(in.StatFlag)){//1按投递公司统计
            sql += " CompanyID,CompanyName,'' AS PostmanID,'' AS PostmanName,";
            groupby += ",CompanyID,CompanyName";
            viewName = "V_QYTerminalUsage1";
        }else if("2".equals(in.StatFlag)){//2按投递员统计
            sql += " PostmanID,PostmanName,CompanyID,CompanyName,";
            groupby += ",CompanyID,CompanyName,PostmanID,PostmanName";
            viewName = "V_QYTerminalUsage1";
        }else{
            sql += " '' AS CompanyID,'' AS CompanyName,'' AS PostmanID,'' AS PostmanName,";
            viewName = "V_QYTerminalUsage";
        }
        
        sql += period +" AS Period,"
                + " SUM(DropNum) AS DropNum,"
                + " SUM(TakeOutNum) AS TakeOutNum,"
                + " SUM(TakeBackNum) AS TakeBackNum,"
                + " SUM(ExpiredNum) AS ExpiredNum,"
                + " SUM(ManagerOutNum) AS ManagerOutNum"
                + " FROM "+viewName+" a WHERE 1=1 ";
        
        if(StringUtils.isNotEmpty(period)){
            groupby += ","+period;
        }
        sql = sql + whereSQL.getPreparedWhereSQL() + limitSQL + groupby;
        
        java.util.LinkedList list = new java.util.LinkedList();
        list.add("TerminalNo");
        
        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);
        
        return rset;
    }
}
