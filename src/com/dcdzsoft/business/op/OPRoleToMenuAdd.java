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
 * <p>Description: 角色菜单信息增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPRoleToMenuAdd extends ActionBean {

    public int doBusiness(InParamOPRoleToMenuAdd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.MenuID)
            || in.RoleID <= 0)
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //4.	调用OPRoleDAO.isExist(角色编号)判断角色是否存在，如果不存在返回ERR_OPROLENODATA。
        OPRoleDAO roleDAO = daoFactory.getOPRoleDAO();
        OPRole role = new OPRole();
        role.RoleID = in.RoleID;
        if (!roleDAO.isExist(role))
            throw new EduException(ErrorCode.ERR_OPROLENODATA);

        //5.	调用OPMenuDAO.isExist(菜单号)判断菜单是否存在，如果不存在返回ERR_OPMENUNODATA
        //OPMenuDAO menuDAO = daoFactory.getOPMenuDAO();
        //String menuidInFlagSQL = utilDAO.getFlagInSQL("MenuID",in.MenuID);

        //     throw new EduException(ErrorCode.ERR_OPMENUNODATA);

        //6.	调用OPRoleToMenuDAO.insert(菜单号,角色编号)增加记录。
        OPRoleToMenuDAO roleToMenuDAO = daoFactory.getOPRoleToMenuDAO();
        OPRoleToMenu roleMenu = new OPRoleToMenu();
        roleMenu.RoleID = in.RoleID;
        //opRole.MenuID = in.MenuID;

        String[] menuIDs = StringUtils.tokenize(in.MenuID, ",");
        if (menuIDs != null && menuIDs.length > 0) {
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("RoleID", in.RoleID);
            roleToMenuDAO.delete(whereCols);

            for (int i = 0; i < menuIDs.length; i++) {
                roleMenu.MenuID = menuIDs[i];
                roleToMenuDAO.insert(roleMenu);
            }
        }else{
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("RoleID", in.RoleID);
            roleToMenuDAO.delete(whereCols);
        }

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
