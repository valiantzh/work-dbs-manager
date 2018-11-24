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
 * <p>Description: 管理员注销(启用) </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperatorModStatus extends ActionBean {

    public int doBusiness(InParamOPOperatorModStatus in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.ByOperID)
            || StringUtils.isEmpty(in.OperateFlag))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //4.	调用用OPOperatorDAO.find(被操作的操作员编号)查询操作员信息
        OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
        OPOperator userObj = new OPOperator();
        userObj.OperID = in.ByOperID;
        userObj = operatorDAO.find(userObj);

        String operStatus = "";
        //5.	如果操作标志 == “0” /*注销*/
        if (in.OperateFlag.equals("0")) {
            if (userObj.OperStatus.equals(Constant.USER_STATUS_LOGOUT))
                throw new EduException(ErrorCode.ERR_OPERSTATUSABNORMAL);
            else
                operStatus = Constant.USER_STATUS_LOGOUT;
        }

        //6.	否则 /*启用*/
        if (in.OperateFlag.equals("1")) {
            if (!userObj.OperStatus.equals(Constant.USER_STATUS_LOGOUT))
                throw new EduException(ErrorCode.ERR_OPERSTATUSABNORMAL);
            else
                operStatus = Constant.USER_STATUS_NORMAL;
        }

        //7.	调用OPOperatorDAO.update(JDBCFieldArray setCols,JDBCFieldArray wherecols)修改操作员信息。
        JDBCFieldArray whereCols = new JDBCFieldArray();
        JDBCFieldArray setCols = new JDBCFieldArray();
        setCols.add("OperStatus", operStatus);
        whereCols.add("OperID", in.ByOperID);

        operatorDAO.update(setCols, whereCols);

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
