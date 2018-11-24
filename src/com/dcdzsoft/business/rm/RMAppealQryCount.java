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
 * <p>Description: 远程求助查询数量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class RMAppealQryCount extends ActionBean
{

    public int doBusiness(InParamRMAppealQryCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		if(in.EndDate != null)
			 in.EndDate = DateUtils.addDate(in.EndDate, 1);
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
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
		 
		String sql = "SELECT COUNT(AppealNo) FROM V_ApplealLog a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();
		
		result = dbSession.executeQueryCount(sql,whereSQL);

        return result;
    }
}
