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
 * <p>Description: 角色删除 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPRoleDel extends ActionBean {

    public int doBusiness(InParamOPRoleDel in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || in.RoleID <= 0)
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        if (in.RoleID == 1 || in.RoleID == 2 || in.RoleID == 3 || in.RoleID == 5)
            throw new EduException(ErrorCode.ERR_FORBIDMODSYSROLE);

        //4.	调用OPRoleDAO.delete(角色编号)删除记录。
        OPOperRoleDAO operRoleDAO = daoFactory.getOPOperRoleDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("RoleID", in.RoleID);

        if (operRoleDAO.isExist(whereCols) > 0)
            throw new EduException(ErrorCode.ERR_FORBIDDELROLE);

        OPRoleDAO roleDAO = daoFactory.getOPRoleDAO();

        OPRole opRole = new OPRole();
        opRole.RoleID = in.RoleID;

        roleDAO.delete(opRole);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = "RoleID=" + in.RoleID;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
