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
 * <p>Description: 同步投递信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncDeliveryInfo extends ActionBean
{

    public java.util.List<OutParamSCSyncDeliveryInfo> doBusiness(InParamSCSyncDeliveryInfo in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (in.LastSyncTime == null )
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.util.List<OutParamSCSyncDeliveryInfo> outList = new java.util.LinkedList<OutParamSCSyncDeliveryInfo>();

		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
	    whereSQL.checkAdd("LastModifyTime", ">", in.LastSyncTime);
	       
		String sql = "SELECT * FROM V_OrderStat4 WHERE 1=1" + whereSQL.getPreparedWhereSQL() + " ORDER BY LastModifyTime DESC";
		
		RowSet rset = dbSession.executeQuery(sql,whereSQL);
		
		while(RowSetUtils.rowsetNext(rset)){
			OutParamSCSyncDeliveryInfo outParam = new OutParamSCSyncDeliveryInfo();
			
			outParam.terminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.terminalName = RowSetUtils.getStringValue(rset, "TerminalName");
			outParam.location = RowSetUtils.getStringValue(rset, "Location");
			outParam.boxNo = RowSetUtils.getStringValue(rset, "BoxNo");
			outParam.packageId = RowSetUtils.getStringValue(rset, "PackageID");
			outParam.postManId = RowSetUtils.getStringValue(rset, "PostmanID");
			outParam.postManName = RowSetUtils.getStringValue(rset, "PostmanName");
			outParam.companyNo = RowSetUtils.getStringValue(rset, "CompanyID");
			outParam.companyName = RowSetUtils.getStringValue(rset, "CompanyName");
			outParam.customerMobile = RowSetUtils.getStringValue(rset, "CustomerMobile");
			outParam.customerName = RowSetUtils.getStringValue(rset, "CustomerName");
			outParam.packageStatus = RowSetUtils.getStringValue(rset, "PackageStatus");
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			if(SysDict.PACKAGE_STATUS_NORMAL.equals(outParam.packageStatus) ||
				SysDict.PACKAGE_STATUS_TIMEOUT.equals(outParam.packageStatus)){
				outParam.tradeWaterNo = RowSetUtils.getStringValue(rset, "TradeWaterNo");
			}else{
				if(StringUtils.isNotEmpty(RowSetUtils.getStringValue(rset, "Remark"))){
					outParam.tradeWaterNo = RowSetUtils.getStringValue(rset, "Remark");
				}else{
					outParam.tradeWaterNo = RowSetUtils.getStringValue(rset, "TradeWaterNo");
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////
			
			outParam.manufacturer = "hzdcdz";
			
			outParam.storedTime = RowSetUtils.getTimestampValue(rset, "StoredTime");
			outParam.pickupTime = RowSetUtils.getTimestampValue(rset, "TakedTime");
			outParam.updateTime = RowSetUtils.getTimestampValue(rset, "LastModifyTime");
			
			outList.add(outParam);
		}

        return outList;
    }
}
