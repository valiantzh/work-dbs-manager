package com.dcdzsoft.business.op;

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
 * <p>Description: 功能查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPFunctionQry extends ActionBean {

    public RowSet doBusiness(InParamOPFunctionQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        WhereExpression where = new WhereExpression();

        where.add("FunctionID", in.FactFunctionID, true);
        where.add("LogFlag", in.LogFlag, true);
        where.add("OpenFlag", in.OpenFlag, true);

        String sql = "SELECT * FROM OPFunction WHERE 1=1 " + where.getWhereSQL();

        rset = dbSession.executeQuery(sql);

        return rset;
    }
}
