package com.dcdzsoft.business.py;

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
 * <p>Description: 查询短信服务账号数量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYSMSAccountQryCount extends ActionBean
{

    public int doBusiness(InParamPYSMSAccountQryCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		String limitSQL = "";
        /////////////////////////////////////////////////////////////
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("AccountID", in.AccountID);
        if(StringUtils.isNotEmpty(in.AccountName))
            whereSQL.add("AccountName", " LIKE ", "%" + in.AccountName + "%");
        whereSQL.checkAdd("AccountStatus", in.AccountStatus);
        whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        
        String sql = "SELECT COUNT(AccountID) FROM V_PYSMSAccount a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;
        
        result = dbSession.executeQueryCount(sql, whereSQL);

        return result;
    }
}
