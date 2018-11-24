package com.dcdzsoft.business.pt;

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
 * <p>Description: 投递记录查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTDeliveryRecordQry extends ActionBean {

    public RowSet doBusiness(InParamPTDeliveryRecordQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。

        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        
        if(in.BeginDate == null){
            in.BeginDate = DateUtils.addDate(sysDate, -7);
        }

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        String limitSQL = "";
        if(StringUtils.isNotEmpty(in.OperID)){
            OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
            limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        }
        
        String viewName = "";
        if("1".equals(in.InboxFlag))
            viewName = "V_InboxPackage";
        else
        {
            if (StringUtils.isEmpty(in.PackageStatus))
                viewName = "V_AllOrder";
            else if (SysDict.PACKAGE_STATUS_NORMAL.equals(in.PackageStatus) ||
                     SysDict.PACKAGE_STATUS_LOCKED.equals(in.PackageStatus) ||
                     SysDict.PACKAGE_STATUS_RETURNGOODS.equals(in.PackageStatus) ||//泰和退货状态
                     SysDict.PACKAGE_STATUS_TIMEOUT.equals(in.PackageStatus))
                viewName = "V_InboxPackage";
            else
                viewName = "V_HistoryPackage";
        }

       PreparedWhereExpression whereSQL = new PreparedWhereExpression();
       if(StringUtils.isEmpty(in.PackageID)){//通过订单号查询，不受时间限制
           whereSQL.checkAdd("StoredDate", ">=", in.BeginDate);
           whereSQL.checkAdd("StoredDate", "<=", in.EndDate);
       }
       if(StringUtils.isNotEmpty(in.DepartmentID)){
           limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
       }
       whereSQL.checkAdd("TerminalNo", in.TerminalNo);
       whereSQL.checkAdd("BoxNo", in.BoxNo);
       //whereSQL.checkAdd("PackageID", in.PackageID);
       if(StringUtils.isNotEmpty(in.PackageID))
       {
           whereSQL.addSQL(" AND PackageID LIKE '" + in.PackageID + "%' ");
       }
       whereSQL.checkAdd("PackageStatus", in.PackageStatus);
       //whereSQL.checkAdd("PostmanID", in.PostmanID);
       if(StringUtils.isNotEmpty(in.PostmanID))
       {
    	   whereSQL.addSQL(" AND (PostmanID = " + StringUtils.addQuote(in.PostmanID) + " OR TakedPersonID = " + StringUtils.addQuote(in.PostmanID) + ")");
       }
       whereSQL.checkAdd("CompanyID", in.CompanyID);
       whereSQL.checkAdd("UploadFlag", in.UploadFlag);
       whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
       whereSQL.checkAdd("PosPayFlag", in.PosPayFlag);
       String sql = "";
       
       //if("V_AllOrder".equalsIgnoreCase(viewName)){
       //	   sql = "SELECT * FROM V_InboxPackage" + " a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
       //    sql = sql + " UNION ALL ";
       //    sql = sql + "SELECT * FROM V_HistoryPackage" + " a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
       //}else
       {
    	   sql = "SELECT * FROM " + viewName + " a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
       }
       
       java.util.List<String> orderByField = new java.util.LinkedList<String>();
       orderByField.add("StoredTime DESC");

       rset = dbSession.executeQuery(sql,orderByField,in.recordBegin,in.recordNum,whereSQL);
        return rset;
    }
}
