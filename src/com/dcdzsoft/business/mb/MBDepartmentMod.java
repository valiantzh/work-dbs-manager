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
 * <p>Description: 修改运营网点信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBDepartmentMod extends ActionBean {

    public int doBusiness(InParamMBDepartmentMod in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.DepartmentID)
            || StringUtils.isEmpty(in.DepartmentName)
            || StringUtils.isEmpty(in.ParentDepartID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        
        ControlParam ctrlParam = ControlParam.getInstance();
        
        MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
        MBDepartment department = new MBDepartment();
        department.DepartmentID = in.DepartmentID;

       department = departmentDAO.find(department);

        /**顶级管理部门不能修改自己的父管理部门*/
        if (ctrlParam.headerDepartmentID.equals(in.DepartmentID))
        	in.ParentDepartID = department.ParentDepartID;

        //验收名称是否存在
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("DepartmentID", "<>", in.DepartmentID);
        whereCols1.add("DepartmentName", in.DepartmentName);

        if (departmentDAO.isExist(whereCols1) > 0)
            throw new EduException(ErrorCode.ERR_DEPARTMENTEXISTS);

        /*根据输入父管理部门编号，从管理部门信息表中取出父管理部门的级别、父管理部门的各上级管理部门：Level1、Level2、…，
         若找不到返回父管理部门编号不存在*/
        MBDepartment objParent = new MBDepartment();
        objParent.DepartmentID = in.ParentDepartID;

        if (ctrlParam.headerDepartmentID.equals(in.DepartmentID) == false) {
            objParent = departmentDAO.find(objParent);

            /*判断父管理部门是否为最低级*/
            if (objParent.DepartLevel > 5)
                throw new EduException(ErrorCode.ERR_OVERDEPARTLEVEL);

            /*判断父级管理部门是否和自己相同*/
            if (in.DepartmentID.equalsIgnoreCase(in.ParentDepartID))
                throw new EduException(ErrorCode.ERR_FORBIDDEPSAMEPARENT);

            /*取父管理部门级别加一作为新增管理部门级别、取父管理部门的各上级管理部门及父管理部门作为新管理部门各相应级别上级管理部门*/
            department.DepartLevel = objParent.DepartLevel + 1;
            setDepartLevel(department, objParent);
        }

        //4.	调用PAShipDepartmentDAO.update (JDBCFieldArray setCols,JDBCFieldArray whereCols)修改航管部门信息。
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        setCols.add("DepartmentName", in.DepartmentName);
        setCols.add("Area", in.Area);
        setCols.add("City", in.City);
        setCols.add("Province", in.Province);
        setCols.add("Address", in.Address);
        setCols.add("Zip", in.Zip);
        setCols.add("OfficeTel", in.OfficeTel);
        setCols.add("ContactPerson", in.ContactPerson);
        setCols.add("ContactTel", in.ContactTel);
        setCols.add("Remark", in.Remark);

        if (ctrlParam.headerDepartmentID.equals(in.ParentDepartID) == false) {
            setCols.add("ParentDepartID", in.ParentDepartID);
            setCols.add("DepartLevel", department.DepartLevel);
            setCols.checkAdd("Level1", department.Level1);
            setCols.checkAdd("Level2", department.Level2);
            setCols.checkAdd("Level3", department.Level3);
            setCols.checkAdd("Level4", department.Level4);
            setCols.checkAdd("Level5", department.Level5);
            setCols.checkAdd("Level6", department.Level6);
        }

        whereCols.add("DepartmentID", in.DepartmentID);
        departmentDAO.update(setCols, whereCols);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = in.DepartmentName;

        commonDAO.addOperatorLog(log);

        return result;
    }

    private void setDepartLevel(MBDepartment obj, MBDepartment objParent)
   {
       obj.Level1 = StringUtils.isNotEmpty(objParent.Level1) ? objParent.Level1 : "";
       obj.Level2 = StringUtils.isNotEmpty(objParent.Level2) ? objParent.Level2 : "";
       obj.Level3 = StringUtils.isNotEmpty(objParent.Level3) ? objParent.Level3 : "";;
       obj.Level4 = StringUtils.isNotEmpty(objParent.Level4) ? objParent.Level4 : "";
       obj.Level5 = StringUtils.isNotEmpty(objParent.Level5) ? objParent.Level5 : "";
       obj.Level6 = StringUtils.isNotEmpty(objParent.Level6) ? objParent.Level6 : "";

       if (obj.DepartLevel == 1)
           obj.Level1 = obj.DepartmentID;
       else if (obj.DepartLevel == 2)
           obj.Level2 = obj.DepartmentID;
       else if (obj.DepartLevel == 3)
           obj.Level3 = obj.DepartmentID;
       else if (obj.DepartLevel == 4)
           obj.Level4 = obj.DepartmentID;
       else if (obj.DepartLevel == 5)
           obj.Level5 = obj.DepartmentID;
       else if (obj.DepartLevel == 6)
           obj.Level6 = obj.DepartmentID;
   }

}
