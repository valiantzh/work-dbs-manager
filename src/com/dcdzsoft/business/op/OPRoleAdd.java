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
 * <p>Description: 角色增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPRoleAdd extends ActionBean {

    public int doBusiness(InParamOPRoleAdd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.RoleName))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //4.	查询角色编号的最大值：
        OPRoleDAO roleDAO = daoFactory.getOPRoleDAO();
        OPRole obj = new OPRole();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("RoleID",">",10);

        String maxRoleID = roleDAO.selectFunctions("MAX(RoleID)", whereCols0);
        if(StringUtils.isEmpty(maxRoleID))
            maxRoleID = "10";

        obj.RoleID = NumberUtils.parseInt(maxRoleID) + 1;

        //5.	调用OPRoleDAO.insert(角色编号,角色名称,操作员编号)插入记录。
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("RoleName", in.RoleName);

        if (roleDAO.isExist(whereCols) > 0)
            throw new EduException(ErrorCode.ERR_ROLENAMEEXIST);

        obj.RoleName = in.RoleName;
        obj.OperID = in.OperID;

        roleDAO.insert(obj);

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
