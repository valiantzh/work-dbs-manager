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
 * <p>Description: 查询系统字典。 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PADictionaryQry extends ActionBean {

    public RowSet doBusiness(InParamPADictionaryQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。


        PADictionaryDAO dictionaryDAO = daoFactory.getPADictionaryDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        if (in.DictTypeID > 0)
            whereCols.add("DictTypeID", in.DictTypeID);
        if (StringUtils.isNotEmpty(in.DictID))
            whereCols.add("DictID", in.DictID);

        rset = dictionaryDAO.select(whereCols);

        return rset;
    }
}
