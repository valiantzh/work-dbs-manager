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
 * <p>Description: 管理员菜单信息增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperToMenuAdd extends ActionBean {

    public int doBusiness(InParamOPOperToMenuAdd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.MenuID)
            || StringUtils.isEmpty(in.ByOperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //4.	调用OPOperatorDAO.isExist(被操作的操作员编号)判断操作员是否存在，如果不存在返回ERR_OPOPERATORNODATA。
        OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
        OPOperator opOperator = new OPOperator();
        opOperator.OperID = in.ByOperID;
        if (!operatorDAO.isExist(opOperator))
            throw new EduException(ErrorCode.ERR_OPOPERATORNODATA);

        //5.	调用OPMenuDAO.isExist(菜单号)判断菜单是否存在，如果不存在返回ERR_OPMENUNODATA
        /*OPMenuDAO menuDAO = daoFactory.getOPMenuDAO();
                 OPMenu opMenu = new OPMenu();
                 opMenu.MenuID = in.MenuID;
                 if (!menuDAO.isExist(opMenu))
            throw new EduException(ErrorCode.ERR_OPMENUNODATA);*/

        //6.	调用OPOperToMenuDAO.insert(菜单号, 被操作的操作员编号)增加记录。
        OPOperToMenuDAO operToMenuDAO = daoFactory.getOPOperToMenuDAO();
        OPOperToMenu opMenu = new OPOperToMenu();
        opMenu.OperID = in.ByOperID;

        String[] menuIDs = StringUtils.tokenize(in.MenuID, ",");
        if (menuIDs != null && menuIDs.length > 0) {
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("OperID", in.ByOperID);
            operToMenuDAO.delete(whereCols);

            for (int i = 0; i < menuIDs.length; i++) {
                opMenu.MenuID = menuIDs[i];
                operToMenuDAO.insert(opMenu);
            }
        }else{
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("OperID", in.ByOperID);
            operToMenuDAO.delete(whereCols);
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
