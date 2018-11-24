package com.dcdzsoft.business.sc;

import java.util.LinkedList;
import java.util.List;

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
 * <p>Description: 同步短信发送信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncShortMsgInfo extends ActionBean
{

    public java.util.List<OutParamSCSyncShortMsgInfo> doBusiness(InParamSCSyncShortMsgInfo in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
		//1.	验证输入参数是否有效，如果无效返回-1。
		if (in.LastSyncTime == null )
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.util.List<OutParamSCSyncShortMsgInfo> outList = new java.util.LinkedList<OutParamSCSyncShortMsgInfo>();

		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
	    whereSQL.checkAdd("LastModifyTime", ">", in.LastSyncTime);
	       
		String sql = "SELECT * FROM MBPwdShortMsg WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + " ORDER BY LastModifyTime DESC";
		
		RowSet rset = dbSession.executeQuery(sql,whereSQL);
		while(RowSetUtils.rowsetNext(rset)){
			OutParamSCSyncShortMsgInfo outParam = new OutParamSCSyncShortMsgInfo();
			
			if(StringUtils.isEmpty(RowSetUtils.getStringValue(rset, "Remark")))
				outParam.waterId = String.valueOf(RowSetUtils.getIntValue(rset, "WaterID"));
			else
				outParam.waterId = RowSetUtils.getStringValue(rset, "Remark");
				
			outParam.terminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.packageId = RowSetUtils.getStringValue(rset, "PackageID");
			outParam.customerMobile = RowSetUtils.getStringValue(rset, "CustomerMobile");
			outParam.openBoxKey = RowSetUtils.getStringValue(rset, "OpenBoxKey");
			outParam.packageStatus = RowSetUtils.getStringValue(rset, "PackageStatus");
			outParam.storedTime = RowSetUtils.getTimestampValue(rset, "StoredTime");
			outParam.updateTime = RowSetUtils.getTimestampValue(rset, "LastModifyTime");
			outParam.manufacturer = "hzdcdz";
			
			outList.add(outParam);
		}

        return outList;
    }
}
