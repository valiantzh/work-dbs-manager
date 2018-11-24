package com.dcdzsoft.business.pa;

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
 * <p>Description: 查询所有的数据字典 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PAAllDictQry extends ActionBean
{

    public RowSet doBusiness(InParamPAAllDictQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

		//1.	验证输入参数是否有效，如果无效返回-1。


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		
		String sql = "SELECT * FROM V_AllDict a WHERE 1=1 ";
		
		PreparedWhereExpression whereSQL = new PreparedWhereExpression();
		whereSQL.checkAdd("DictTypeID",in.DictTypeID);
		whereSQL.checkAdd("DictID",in.DictID);
		
		sql = sql + whereSQL.getPreparedWhereSQL();
		sql = sql + " ORDER BY DictTypeID,DictID";
		
		rset = dbSession.executeQuery(sql,whereSQL);

        return rset;
    }
}
