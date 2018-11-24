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
 * <p>Description: 角色修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPRoleMod extends ActionBean {

    public int doBusiness(InParamOPRoleMod in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || in.RoleID <= 0
            || StringUtils.isEmpty(in.RoleName))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        if (in.RoleID == 1 || in.RoleID == 2 || in.RoleID == 3 || in.RoleID == 5)
            throw new EduException(ErrorCode.ERR_FORBIDMODSYSROLE);

        //4.	调用OPRoleDAO.update(JDBCFieldArray setCols,JDBCFieldArray whereCols)修改记录。
        OPRoleDAO roleDAO = daoFactory.getOPRoleDAO();

        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("RoleName", in.RoleName);
        whereCols1.add("RoleID", "<>", in.RoleID);
        if (roleDAO.isExist(whereCols1) > 0)
            throw new EduException(ErrorCode.ERR_ROLENAMEEXIST);

        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        setCols.add("RoleName", in.RoleName);
        whereCols.add("RoleID", in.RoleID);

        roleDAO.update(setCols, whereCols);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = "";

        commonDAO.addOperatorLog(log);

        return result;
    }
}
