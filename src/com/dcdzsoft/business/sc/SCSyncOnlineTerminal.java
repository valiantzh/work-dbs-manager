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
 * <p>Description: 同步在线终端信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncOnlineTerminal extends ActionBean
{

    public java.util.List<OutParamSCSyncOnlineTerminal> doBusiness(InParamSCSyncOnlineTerminal in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
        java.util.List<OutParamSCSyncOnlineTerminal> outList = new java.util.LinkedList<OutParamSCSyncOnlineTerminal>();
        
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("TerminalName", in.TerminalName);
        whereSQL.checkAdd("OnlineStatus", in.OnlineStatus);
	       
		String sql = "SELECT * FROM V_OnlineTerminal WHERE 1=1" + whereSQL.getPreparedWhereSQL();
		
		RowSet rset = dbSession.executeQuery(sql,whereSQL);
		
		while(RowSetUtils.rowsetNext(rset)){
			OutParamSCSyncOnlineTerminal outParam = new OutParamSCSyncOnlineTerminal();
			
			outParam.TerminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.TerminalName = RowSetUtils.getStringValue(rset, "TerminalName");
			outParam.BoxNum = RowSetUtils.getIntValue(rset, "BoxNum");
			outParam.Remark = RowSetUtils.getStringValue(rset, "Remark");
			outParam.OnlineStatus = RowSetUtils.getStringValue(rset, "OnlineStatus");
					
			if("0".equals(RowSetUtils.getStringValue(rset, "OnlineStatus")))
				outParam.OnlineStatusName = "离线";
			else
				outParam.OnlineStatusName = "在线";
				
			outParam.FreeBoxNum = outParam.BoxNum - RowSetUtils.getIntValue(rset, "UsedBoxNum");
			
			outList.add(outParam);
		}

        return outList;
    }
}
