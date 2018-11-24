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
 * <p>Description: 同步箱体信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncTBBox extends ActionBean
{

    public java.util.List<OutParamSCSyncTBBox> doBusiness(InParamSCSyncTBBox in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
       
		//1.	验证输入参数是否有效，如果无效返回-1。
		if (in.LastSyncTime == null )
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		java.util.List<OutParamSCSyncTBBox> outList = new java.util.LinkedList<OutParamSCSyncTBBox>();
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
	    whereSQL.checkAdd("LastModifyTime", ">", in.LastSyncTime);
	       
		String sql = "SELECT * FROM V_TBBox WHERE 1=1" + whereSQL.getPreparedWhereSQL() + " ORDER BY LastModifyTime DESC";
		
		RowSet rset = dbSession.executeQuery(sql,whereSQL);
		
		while(RowSetUtils.rowsetNext(rset)){
			OutParamSCSyncTBBox outParam = new OutParamSCSyncTBBox();
			
			outParam.terminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.terminalName = RowSetUtils.getStringValue(rset, "TerminalName");
			outParam.boxNo = RowSetUtils.getStringValue(rset, "BoxNo");
			outParam.boxName = RowSetUtils.getStringValue(rset, "BoxName");
			outParam.bSize = RowSetUtils.getStringValue(rset, "BoxType");
			outParam.status = RowSetUtils.getStringValue(rset, "BoxStatus");
			outParam.createTime = RowSetUtils.getTimestampValue(rset, "CreateTime");
			outParam.updateTime = RowSetUtils.getTimestampValue(rset, "LastModifyTime");
			
			outList.add(outParam);
		}
		
		return outList;
    }
}
