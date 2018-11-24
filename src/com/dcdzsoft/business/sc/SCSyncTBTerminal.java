package com.dcdzsoft.business.sc;

import java.util.LinkedList;

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
 * <p>Description: 同步柜体信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncTBTerminal extends ActionBean
{

    public java.util.List<OutParamSCSyncTBTerminal> doBusiness(InParamSCSyncTBTerminal in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
		//1.	验证输入参数是否有效，如果无效返回-1。
		if (in.LastSyncTime == null )
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.util.List<OutParamSCSyncTBTerminal> outList = new java.util.LinkedList<OutParamSCSyncTBTerminal>();
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
	    whereSQL.checkAdd("LastModifyTime", ">", in.LastSyncTime);
	       
		String sql = "SELECT * FROM V_TBTerminal1 WHERE 1=1" + whereSQL.getPreparedWhereSQL() + " ORDER BY LastModifyTime  DESC";
		
		RowSet rset = dbSession.executeQuery(sql,whereSQL);
		
		while(RowSetUtils.rowsetNext(rset)){
			OutParamSCSyncTBTerminal outParam = new OutParamSCSyncTBTerminal();
			
			outParam.terminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.terminalName = RowSetUtils.getStringValue(rset, "TerminalName");
			outParam.location = RowSetUtils.getStringValue(rset, "Location");
			outParam.boxCount = RowSetUtils.getIntValue(rset, "BoxNum");
			outParam.smallBoxCount = RowSetUtils.getIntValue(rset, "SmallNum");
			outParam.middleBoxCount = RowSetUtils.getIntValue(rset, "MediumNum");
			outParam.largeBoxCount = RowSetUtils.getIntValue(rset, "LargeNum");
			outParam.hugeBoxCount = RowSetUtils.getIntValue(rset, "SuperNum");
			outParam.miniBoxCount = RowSetUtils.getIntValue(rset, "SuperSmallNum");
			outParam.status = RowSetUtils.getStringValue(rset, "TerminalStatus");
			outParam.createTime = RowSetUtils.getTimestampValue(rset, "CreateTime");
			outParam.usedTime = RowSetUtils.getTimestampValue(rset, "CreateTime");
			outParam.updateTime = RowSetUtils.getTimestampValue(rset, "LastModifyTime");
			outParam.manufacturer = "hzdcdz";
			outParam.province = RowSetUtils.getStringValue(rset, "PParentDepartID");
			outParam.city = RowSetUtils.getStringValue(rset, "ParentDepartID");
			outParam.area = RowSetUtils.getStringValue(rset, "DepartmentID");
			outParam.websiteType = RowSetUtils.getStringValue(rset, "LocationType");
			
			outParam.longitude = "";
			outParam.latitude = "";
			
			String Latlon = RowSetUtils.getStringValue(rset, "Latlon");
			
			if(StringUtils.isNotEmpty(Latlon)){
				String[] latLonList = Latlon.split(",");
				
				outParam.longitude = latLonList[0];
				outParam.latitude = latLonList.length > 1 ? latLonList[1] : "";
			}
			
			outList.add(outParam);
		}
		
		return outList;
    }
}
