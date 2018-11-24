package com.dcdzsoft.business.mb;

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
 * <p>Description: 删除运营网点信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBDepartmentDel extends ActionBean {

    public int doBusiness(InParamMBDepartmentDel in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.DepartmentID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //4.	调用MBDepartmentDAO.delete ()删除运营网点信息。
        MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
        MBDepartment department = new MBDepartment();
        department.DepartmentID = in.DepartmentID;

        department = departmentDAO.find(department);

        //不能删除最最上级管理部门
        if (department.DepartLevel == 1)
            throw new EduException(ErrorCode.ERR_FORBIDDELDEPART);

        //从管理部门信息表中检查被删管理部门编号是否还有其它管理部门以它为父管理部门编号，若有置错误码还有子管理部门存在，不允许删除
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("ParentDepartID", in.DepartmentID);

        if (departmentDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_FORBIDDELDEPART);

        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("DepartmentID",in.DepartmentID);

        //判断是否存在管理员
        OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
        if(operDAO.isExist(whereCols) > 0)
            throw new EduException(ErrorCode.ERR_FORBIDDELDEPART);

        //判断是否存在设备
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        if(terminalDAO.isExist(whereCols) > 0)
            throw new EduException(ErrorCode.ERR_FORBIDDELDEPART);

        //删除
        result = departmentDAO.delete(department);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = in.DepartmentID;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
