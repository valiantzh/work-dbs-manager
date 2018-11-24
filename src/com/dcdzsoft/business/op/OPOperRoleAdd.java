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
 * <p>Description: 管理员角色增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperRoleAdd extends ActionBean {

    public int doBusiness(InParamOPOperRoleAdd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.ByOperID)
            || in.RoleID <= 0)
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //4.	调用OPOperatorDAO.isExist(被操作的操作员编号)判断操作员是否存在，如果不存在返回ERR_OPOPERATORNODATA。
        OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
        OPOperator opOperator = new OPOperator();
        opOperator.OperID = in.ByOperID;
        if (!operatorDAO.isExist(opOperator))
            throw new EduException(ErrorCode.ERR_OPOPERATORNODATA);

        //5.	调用OPRoleDAO.isExist(角色编号)判断角色是否存在，如果不存在返回ERR_OPROLENODATA。
        OPRoleDAO roleDAO = daoFactory.getOPRoleDAO();
        OPRole opRole = new OPRole();
        opRole.RoleID = in.RoleID;
        if(!roleDAO.isExist(opRole))
            throw new EduException(ErrorCode.ERR_OPROLENODATA);

        //6.	调用OPOperRoleDAO.isExist(被操作的操作员编号, 角色编号)判断记录是否存在，如果存在返回ERR_OPERROLEHAVEEXIST
        OPOperRoleDAO operRoleDAO = daoFactory.getOPOperRoleDAO();
        OPOperRole opOperRole = new OPOperRole();
        opOperRole.OperID = in.ByOperID;
        opOperRole.RoleID = in.RoleID;

        if(operRoleDAO.isExist(opOperRole))
            throw new EduException(ErrorCode.ERR_OPERROLEHAVEEXIST);

        //7.	插入操作员菜单信息
        String whereSQL = "";

        OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
        OPOperator operSuperObj = new OPOperator();
        operSuperObj.OperID = in.OperID;
        operSuperObj = operDAO.find(operSuperObj);

        if (operSuperObj.UserID.equalsIgnoreCase(Constant.DEFAULT_SUPEROPERID) == false) {
            String subModuleID = utilDAO.getSubstringSQL("a.MenuID", 1, 2);
            whereSQL = " AND " + subModuleID + "<>" + StringUtils.addQuote(Constant.MODULE_ID_OP);
        }

        String sql = "INSERT INTO OPOperToMenu(MenuID,OperID)"
            + " SELECT a.MenuID," + StringUtils.addQuote(in.ByOperID)
            + " FROM OPRoleToMenu a"
            + " WHERE a.RoleID = " + in.RoleID
            + whereSQL
            + " AND NOT EXISTS(SELECT * FROM OPOperToMenu b "
            + " WHERE b.MenuID = a.MenuID"
            + " AND b.OperID = " + StringUtils.addQuote(in.ByOperID) + ")";

        dbSession.executeUpdate(sql);

        //8.	调有OPOperRoleDAO.insertOPOperRole()插入操作员角色信息
        operRoleDAO.insert(opOperRole);


        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
         log.Remark = "UserID=" + in.ByOperID;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
