package com.dcdzsoft.business.pm;

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
 * <p>Description: 物流公司查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMLogisticsQry extends ActionBean
{

    public RowSet doBusiness(InParamPMLogisticsQry in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        ////////////////////////////////////////////////////////////
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("LogisticsID", in.LogisticsID);
        if(StringUtils.isNotEmpty(in.LogisticsName))
            whereSQL.add("LogisticsName", " LIKE ", "%" + in.LogisticsName + "%");
        
        String sql = "SELECT * FROM PMLogistics WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + " ORDER BY LogisticsID";
        
        rset = dbSession.executeQuery(sql,whereSQL);

        return rset;
    }
}
