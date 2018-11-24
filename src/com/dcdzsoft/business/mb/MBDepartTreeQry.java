package com.dcdzsoft.business.mb;

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
 * <p>Description: 查询运营网点信息(以树形结构返回) </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBDepartTreeQry extends ActionBean {

    public RowSet doBusiness(InParamMBDepartTreeQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用MBDepartmentDAO.select()查询运营网点信息。
        String sql = "SELECT * FROM V_MBDepart a WHERE 1=1 ";
        sql = sql + " AND a.DepartLevel = " + (in.DepartLevel + 1);

        if(StringUtils.isNotEmpty(in.DepartmentID) && in.DepartLevel != 0)
            sql = sql + " AND a.ParentDepartID = " + StringUtils.addQuote(in.DepartmentID);


        rset = dbSession.executeQuery(sql);

        return rset;
    }
}
