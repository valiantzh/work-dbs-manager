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
 * <p>Description: 管理员修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperatorMod extends ActionBean {

    public int doBusiness(InParamOPOperatorMod in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.ByOperID)
            || StringUtils.isEmpty(in.OperName)
            || in.OperType <= 0)
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断管理员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //4.	调用OPOperatorDAO.isExist(被操作的操作员编号)判断操作员是否存在，如果不存在返回ERR_OPOPERATORNODATA。
        OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
        OPOperator operObj = new OPOperator();
        operObj.OperID = in.ByOperID;

        operObj = operatorDAO.find(operObj);

        in.OperName = StringUtils.normalizeName(in.OperName);

        if(StringUtils.isNotEmpty(in.DepartmentID) && !in.DepartmentID.equalsIgnoreCase(operObj.DepartmentID)){
            MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("DepartmentID", in.DepartmentID);
            if(departmentDAO.isExist(whereCols)<1){
                in.DepartmentID = "";
            }
        }else{
            in.DepartmentID = "";
        }
        
        //修改状态
        if (StringUtils.isNotEmpty(in.OperStatus) && !operObj.OperStatus.equals(in.OperStatus)) {
            JDBCFieldArray whereCols = new JDBCFieldArray();
            JDBCFieldArray setCols = new JDBCFieldArray();
            setCols.add("OperStatus", in.OperStatus);
            whereCols.add("OperID", in.ByOperID);

            operatorDAO.update(setCols, whereCols);
        } else {
            //5.	调用OPOperatorDAO.update(JDBCFieldArray setCols,JDBCFieldArray wherecols)修改操作员信息。
            JDBCFieldArray setCols1 = new JDBCFieldArray();
            JDBCFieldArray whereCols1 = new JDBCFieldArray();

            setCols1.add("OperName", in.OperName);
            setCols1.add("OfficeTel", in.OfficeTel);
            setCols1.add("Mobile", in.Mobile);
            setCols1.checkAdd("DepartmentID", in.DepartmentID);
            setCols1.add("Email", in.Email);
            setCols1.add("Remark", in.Remark);

            whereCols1.add("OperID", in.ByOperID);

            operatorDAO.update(setCols1, whereCols1);
        }

        if(StringUtils.isNotEmpty(in.DepartmentID)){
            OPOperOnlineDAO operOnlineDAO = daoFactory.getOPOperOnlineDAO();
            JDBCFieldArray whereCols = new JDBCFieldArray();
            JDBCFieldArray setCols = new JDBCFieldArray();
            setCols.add("DepartmentID", in.DepartmentID);
            whereCols.add("OperID", in.ByOperID);

            operOnlineDAO.update(setCols, whereCols);
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
