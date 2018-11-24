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
 * <p>Description: 管理员总体限制权限增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperAllLimitAdd extends ActionBean {

    public int doBusiness(InParamOPOperAllLimitAdd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.ByOperID)
            || in.LimitTypeID <= 0
            || StringUtils.isEmpty(in.LimitObject)
            || StringUtils.isEmpty(in.LimitObjectName))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //4.	调用OPOperatorDAO.isExist(被操作的管理员编号)判断管理员是否存在，如果不存在返回ERR_OPOPERATORNODATA。
        OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();

        //5.	调用PADictionaryDAO.isExist()判断限制类别编号是否存在，如果不存在返回ERR_PADICTIONARYNODATA
        PADictionaryDAO dictionaryDAO = daoFactory.getPADictionaryDAO();


        //6.	调用OPOperAllLimitDAO.insert(被操作的管理员编号,限制类别编号, 限制的对象, 限制的对象名称)插入记录。
        OPOperAllLimitDAO operAllLimitDAO = daoFactory.getOPOperAllLimitDAO();

        //7.	调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)。
        CommonDAO mmonDAO = daoFactory.getCommonDAO();

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
