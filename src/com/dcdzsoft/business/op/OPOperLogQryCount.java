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
 * <p>Description: 查询管理员日志数量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperLogQryCount extends ActionBean {

    public int doBusiness(InParamOPOperLogQryCount in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //4.	调用OPOperatorLogDAO.isExist(JDBCFieldArray whereCols)查询记录数量
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        //超级管理员账号，不允许其他账号查看
        if(!Constant.DEFAULT_SUPEROPERID.equalsIgnoreCase(in.OperID)){
            whereSQL.add("OperID", "<>", Constant.DEFAULT_SUPEROPERID);
        }
        whereSQL.checkAdd("UserID", in.UserID);
        whereSQL.checkAdd("OperName", in.OperName);
        whereSQL.checkAdd("OperType", in.OperType);
        whereSQL.checkAdd("FunctionID", in.FactFunctionID);
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("OccurDate", ">=", in.BeginDate);
        whereSQL.checkAdd("OccurDate", "<=", in.EndDate);

        String sql = "SELECT count(*) FROM V_OperatorLog a WHERE 1=1 " + whereSQL.getPreparedWhereSQL();

        result = dbSession.executeQueryCount(sql, whereSQL);

        return result;
    }
}
