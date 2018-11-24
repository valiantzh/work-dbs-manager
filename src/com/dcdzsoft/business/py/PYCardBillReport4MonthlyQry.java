package com.dcdzsoft.business.py;

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

public class PYCardBillReport4MonthlyQry extends ActionBean
{

    public RowSet doBusiness(InParamPYCardBillReport4MonthlyQry in) throws EduException
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
        
        String limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("OccurMonth", in.OccurMonth);
        whereSQL.checkAdd("OccurYear", in.OccurYear);   
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        if(StringUtils.isNotEmpty(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("CompanyID", in.CompanyID);
        whereSQL.checkAdd("CustomerID", in.CustomerID);
        if(StringUtils.isNotEmpty(in.CustomerName))
            whereSQL.add("CustomerName", " LIKE ", "%" + in.CustomerName + "%");
        
        
        whereSQL.add("BillType", in.BillType);
        
        //////////////////
        String viewName = "V_PYCardBill4Post";
        if("99".equals(in.CustomerType)){
            viewName = "V_PYCardBill4Post";
        }
        String sql = "SELECT '" + reportID + "' AS ReportID,BillType,OccurYear,OccurMonth,";
        
        String groupby = " GROUP BY BillType,OccurYear,OccurMonth";
        
        if(SysDict.BILL_TYPE_TOPUP.equals(in.BillType)){
            sql += " '充值' AS BillTypeName, ";
        }else if(SysDict.BILL_TYPE_CANCEL.equals(in.BillType)){
            sql += " '取消充值' AS BillTypeName, ";
        }else{
            sql += " '消费' AS BillTypeName, ";
        }
        if("1".equals(in.StatFlag)){//1按投递公司统计
            sql += " '' AS DepartmentName, CompanyName,'' AS CustomerID, '' AS CustomerName,";
            groupby += ",CompanyName";
            
        }else if("2".equals(in.StatFlag)){//2按投递员统计
            sql += " DepartmentName, CompanyName, CustomerID, CustomerName,";
            groupby += ",DepartmentName,CompanyName,CustomerID, CustomerName";
        }else{
            sql += " DepartmentName,'' AS CompanyName,'' AS CustomerID,'' AS CustomerName, ";
            groupby += ",DepartmentName";
        }
        
        sql += " SUM(a.BillAmt) AS BillAmt, '' AS Remark"
                + " FROM "+viewName+" a WHERE 1=1 ";
        
        sql = sql + whereSQL.getPreparedWhereSQL() + limitSQL + groupby;
        
        java.util.LinkedList list = new java.util.LinkedList();
        list.add("OccurYear,OccurMonth");
        
        rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);
        
        return rset;
    }
}
