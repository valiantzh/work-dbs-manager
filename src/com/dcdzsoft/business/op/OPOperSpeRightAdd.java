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
import com.dcdzsoft.sda.db.JDBCFieldArray;
import com.dcdzsoft.util.StringUtils;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 管理员特殊权限设置增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperSpeRightAdd extends ActionBean {

    public int doBusiness(InParamOPOperSpeRightAdd in) throws EduException {
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

        //5.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //3.	调用OPOperatorDAO.isExist (操作员编号)查询操作员信息是否存在，如果不存在返回ERR_OPOPERATORNODATA。
        OPOperSpeRightDAO operSpeRightDAO = daoFactory.getOPOperSpeRightDAO();

        OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
        OPOperator operator = new OPOperator();
        operator.OperID = in.ByOperID;

        operator = operatorDAO.find(operator);

        if (StringUtils.isEmpty(in.SpePrivID)) {
            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("OperID", in.ByOperID);
            operSpeRightDAO.delete(whereCols0);
        }

        //4.	调用OPOperSpeRightDAO.insert()插入操作员特殊权限设置信息。
        OPOperSpeRight obj = new OPOperSpeRight();
        obj.OperID = in.ByOperID;

        String[] speRightIDs = StringUtils.tokenize(in.SpePrivID, ",");
        if (speRightIDs != null && speRightIDs.length > 0) {
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("OperID", in.ByOperID);
            operSpeRightDAO.delete(whereCols);

            for (int i = 0; i < speRightIDs.length; i++) {
                obj.SpePrivID = NumberUtils.parseInt(speRightIDs[i]);
                operSpeRightDAO.insert(obj);
            }
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
