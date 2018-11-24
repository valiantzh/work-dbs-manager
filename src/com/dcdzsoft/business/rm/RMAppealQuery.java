package com.dcdzsoft.business.rm;

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
 * <p>Description: 远程求助查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class RMAppealQuery extends ActionBean
{

    public RowSet doBusiness(InParamRMAppealQuery in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		 PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		 
		 if(in.EndDate != null)
			 in.EndDate = DateUtils.addDate(in.EndDate, 1);
		 
		 whereSQL.checkAdd("AppealTime", ">=", in.BeginDate);
		 whereSQL.checkAdd("AppealTime", "<=", in.EndDate);
		 whereSQL.checkAdd("TerminalNo", in.TerminalNo);
		 if(StringUtils.isNotEmpty(in.TerminalName))
		       whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
		 
		 whereSQL.checkAdd("BoxNo", in.BoxNo);
		 whereSQL.checkAdd("PackageID", in.PackageID);
		 whereSQL.checkAdd("PostmanID", in.PostmanID);
		 whereSQL.checkAdd("CustomerMobile", in.CustomerMobile);
		 whereSQL.checkAdd("AppealType", in.AppealType);
		 whereSQL.checkAdd("AppealUser", in.AppealUser);
		 
		String sql = "SELECT * FROM V_ApplealLog a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
		
		java.util.LinkedList list = new java.util.LinkedList();
		list.add("AppealTime DESC");
		
		rset = dbSession.executeQuery(sql, list, in.recordBegin, in.recordNum, whereSQL);
		 
        return rset;
    }
}
