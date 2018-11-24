package com.dcdzsoft.business.sc;

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
 * <p>Description: 同步包裹信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncDeliveryInfo4JXYZ extends ActionBean
{

    public java.util.List<OutParamSCSyncDeliveryInfo4JXYZ> doBusiness(InParamSCSyncDeliveryInfo4JXYZ in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
        java.util.List<OutParamSCSyncDeliveryInfo4JXYZ> outList = new java.util.LinkedList<OutParamSCSyncDeliveryInfo4JXYZ>();
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("StoredDate", ">=", in.BeginDate);
        whereSQL.checkAdd("StoredDate", "<=", in.EndDate);
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("TerminalName", in.TerminalName);
        whereSQL.checkAdd("BoxNo", in.BoxNo);
        whereSQL.checkAdd("PackageID", in.PackageID);
        whereSQL.checkAdd("PackageStatus", in.PackageStatus);
        if(StringUtils.isNotEmpty(in.PostmanID))
        {
     	   whereSQL.addSQL(" AND (PostmanID = " + StringUtils.addQuote(in.PostmanID) + " OR TakedPersonID = " + StringUtils.addQuote(in.PostmanID) + ")");
        }
        whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
	       
		String sql = "SELECT * FROM V_AllOrder WHERE 1=1" + whereSQL.getPreparedWhereSQL() + " ORDER BY LastModifyTime DESC";
		
		RowSet rset = dbSession.executeQuery(sql,whereSQL);
		
		while(RowSetUtils.rowsetNext(rset)){
			OutParamSCSyncDeliveryInfo4JXYZ outParam = new OutParamSCSyncDeliveryInfo4JXYZ();
			
			outParam.TerminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.TerminalName = RowSetUtils.getStringValue(rset, "TerminalName");
			outParam.BoxNo = RowSetUtils.getStringValue(rset, "BoxNo");
			outParam.PackageID = RowSetUtils.getStringValue(rset, "PackageID");
			outParam.PostmanID = RowSetUtils.getStringValue(rset, "PostmanID");
			outParam.CustomerMobile = RowSetUtils.getStringValue(rset, "CustomerMobile");
			outParam.StoredTime = RowSetUtils.getTimestampValue(rset, "StoredTime");
			outParam.TakedTime = RowSetUtils.getTimestampValue(rset, "TakedTime");
			outParam.PackageStatus = RowSetUtils.getStringValue(rset, "PackageStatus");
			outParam.OpenBoxKey = "";
			outParam.Remark = RowSetUtils.getStringValue(rset, "Remark");
			
			outList.add(outParam);
		}

        return outList;
    }
}
