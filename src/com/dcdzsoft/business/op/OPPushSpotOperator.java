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
 * <p>Description: 推送现场管理员 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPPushSpotOperator extends ActionBean {

    public int doBusiness(InParamOPPushSpotOperator in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.ByOperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
        OPOperator oper = new OPOperator();
        oper.OperID = in.ByOperID;

        oper = operDAO.find(oper);

        in.UserID = oper.UserID;
        in.OperName = oper.OperName;
        in.OperPassword = oper.OperPassword;
        in.OperType = oper.OperType;
        in.DepartmentID = oper.DepartmentID;
        in.OfficeTel = oper.OfficeTel;
        in.Mobile = oper.Mobile;
        in.Email = oper.Email;
        in.OperStatus = oper.OperStatus;

        ///推送到设备端
        com.dcdzsoft.businessproxy.PushBusinessProxy.doBusiness(in,in.TerminalNoStr);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = oper.UserID;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
