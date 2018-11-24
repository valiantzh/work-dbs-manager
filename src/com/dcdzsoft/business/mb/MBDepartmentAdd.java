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
 * <p>Description: 增加运营网点信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBDepartmentAdd extends ActionBean {

    public int doBusiness(InParamMBDepartmentAdd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.DepartmentName)
            || StringUtils.isEmpty(in.ParentDepartID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用MBDepartmentDAO.insert ()插入运营网点信息。
        MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
        MBDepartment department = new MBDepartment();

        //4.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //产生运营网点编号
        String departmentid = "";
        if(StringUtils.isEmpty(in.DepartmentID)){
            String maxDepartid = departmentDAO.selectFunctions("MAX(DepartmentID)",null);
            if(StringUtils.isEmpty(maxDepartid))
                maxDepartid = "100000";

           departmentid = StringUtils.rightPad(NumberUtils.parseInt(maxDepartid)+1,6,'0');
        }else{
            departmentid = in.DepartmentID;

            //判断编号是否存在
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("DepartmentID",in.DepartmentID);

            if (departmentDAO.isExist(whereCols) > 0)
                throw new EduException(ErrorCode.ERR_DEPARTMENTEXISTS);
        }

        ///名称不能重复
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("DepartmentName", in.DepartmentName);
        if (departmentDAO.isExist(whereCols) > 0)
            throw new EduException(ErrorCode.ERR_DEPARTMENTEXISTS);

        /*根据输入父管理部门编号，从管理部门信息表中取出父管理部门的级别、父管理部门的各上级管理部门：Level1、Level2、…，
        若找不到返回父管理部门编号不存在*/
       MBDepartment objParent = new MBDepartment();
       objParent.DepartmentID = in.ParentDepartID;

       objParent = departmentDAO.find(objParent);

       /*判断父管理部门是否为最低级,最多6级*/
       if (objParent.DepartLevel > 5)
           throw new EduException(ErrorCode.ERR_OVERDEPARTLEVEL);

       /*判断父级管理部门是否和自己相同*/
       if(in.DepartmentID.equalsIgnoreCase(in.ParentDepartID))
           throw new EduException(ErrorCode.ERR_FORBIDDEPSAMEPARENT);

       /*取父管理部门级别加一作为新增管理部门级别、取父管理部门的各上级管理部门及父管理部门作为新管理部门各相应级别上级管理部门，
              向管理部门信息表中增加一条记录*/
       
        /////////////////////////////////////////////
        department.DepartmentID = departmentid;
        department.DepartmentName = in.DepartmentName;
        department.ParentDepartID = in.ParentDepartID;
        department.DepartLevel = objParent.DepartLevel + 1;
        department.Area = in.Area;
        department.City = in.City;
        department.Province = in.Province;
        department.Address = in.Address;
        department.Zip = in.Zip;
        department.OfficeTel = in.OfficeTel;
        department.ContactPerson = in.ContactPerson;
        department.ContactTel = in.ContactTel;
        department.WelcomeInfo = in.WelcomeInfo;
        department.CreateTime = sysDateTime;
        department.Remark = in.Remark;

        setDepartLevel(department, objParent);

        //插入department
        departmentDAO.insert(department);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = department.DepartmentName;

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
