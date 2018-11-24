package com.dcdzsoft.business.ap;

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
 * <p>Description: 投递员未取包裹列表 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APPostmanInboxPackage extends ActionBean
{

	public java.util.List<OutParamAPPostmanInboxPackage> doBusiness(InParamAPPostmanInboxPackage in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        java.util.List<OutParamAPPostmanInboxPackage> outList = new java.util.LinkedList<OutParamAPPostmanInboxPackage>();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PostmanID))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		whereSQL.add("PostmanID", in.PostmanID);
		whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
		whereSQL.checkAdd("PackageID", in.PackageID);
		whereSQL.checkAdd("TerminalNo", in.TerminalNo);

		String sql = "SELECT * FROM V_InboxPackage1 a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
		
		RowSet rset = dbSession.executeQuery(sql, whereSQL);

		while (RowSetUtils.rowsetNext(rset)) {
			OutParamAPPostmanInboxPackage outParam = new OutParamAPPostmanInboxPackage();

			outParam.TerminalNo = RowSetUtils.getStringValue(rset, "TerminalNo");
			outParam.TerminalName = RowSetUtils.getStringValue(rset, "TerminalName");
			outParam.Location = RowSetUtils.getStringValue(rset, "Location");
			outParam.PackageID = RowSetUtils.getStringValue(rset, "PackageID");
			outParam.StoredTime = RowSetUtils.getTimestampValue(rset, "StoredTime");
			outParam.TradeWaterNo = RowSetUtils.getStringValue(rset, "TradeWaterNo");
			outParam.CustomerMobile = RowSetUtils.getStringValue(rset, "CustomerMobile");
			outParam.BoxNo = RowSetUtils.getStringValue(rset, "BoxNo");

			outList.add(outParam);
		}

        return outList;
    }
}
