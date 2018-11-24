package com.dcdzsoft.business.sm;

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
 * <p>Description: 系统配置查询 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author zxy
 * @version 1.0
 */

public class SMSysCleanAuto extends ActionBean {

    public int doBusiness(InParamSMSysCleanAuto in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        
        
        //设备告警日志保存30天
        MBTerminalAlertLogDAO alertLogDAO = daoFactory.getMBTerminalAlertLogDAO();
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("LogDate","<", DateUtils.addDate(sysDate, -30));
        alertLogDAO.delete(whereCols0);
        
        //操作员日志保存90天
        OPOperatorLogDAO operatorLogDAO = daoFactory.getOPOperatorLogDAO();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("OccurDate","<", DateUtils.addDate(sysDate, -90));
        operatorLogDAO.delete(whereCols1);
        
        //
        PTFeedbackFailDAO feedbackFailDAO = daoFactory.getPTFeedbackFailDAO();
        JDBCFieldArray whereCols2 = new JDBCFieldArray();
        whereCols2.add("FailureTime","<",  DateUtils.addTimeStamp(sysDateTime, -360));
        feedbackFailDAO.delete(whereCols2);
        
        return result;
    }
}
