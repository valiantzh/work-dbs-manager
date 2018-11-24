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
 * <p>Description: 管理员个数查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperatorQryCount extends ActionBean {

    public int doBusiness(InParamOPOperatorQryCount in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        WhereExpression where = new WhereExpression();
        if (StringUtils.isNotEmpty(in.UserID))
            where.add("UserID", in.UserID, true);

        if (StringUtils.isNotEmpty(in.OperName))
            where.addSQL(" AND " + utilDAO.getUpperSQL("OperName") + " LIKE " + StringUtils.addQuote("%" + in.OperName.toUpperCase() + "%"));

        //超级管理员账号，不允许其他账号查看
        if(!Constant.DEFAULT_SUPEROPERID.equalsIgnoreCase(in.OperID)){
            where.add("OperID", "<>", Constant.DEFAULT_SUPEROPERID, true);
        }
        
        where.add("OperType", in.OperType, true);
        where.add("DepartmentID", in.DepartmentID, true);
        where.add("OperStatus", in.OperStatus, true);

        if (StringUtils.isNotEmpty(in.Email))
            where.add("UpperEmail", in.Email.toUpperCase(), true);

        String sql = "SELECT COUNT(*) FROM V_Operator WHERE 1=1 " + where.getWhereSQL();

        result = dbSession.executeQueryCount(sql);


        return result;
    }
}
