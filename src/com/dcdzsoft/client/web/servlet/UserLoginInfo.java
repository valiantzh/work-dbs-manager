package com.dcdzsoft.client.web.servlet;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author wangzl
 * @version 1.0
 */

public class UserLoginInfo
{
    public String OperID = ""; //操作员编号
    public String OperName = ""; //操作员姓名
    public int OperType; //操作员类型
    public String DepartmentID = ""; //所在网点编号
    public String LoginInTime = ""; //上次登录时间
    public String LoginOutTime = ""; //上次退出时间
    public String LoginIPAddr = ""; //上次登录IP

   public String getDepartmentID()
    {
        return DepartmentID;
    }

    public String getLoginInTime()
    {
        return LoginInTime;
    }

    public String getLoginIPAddr()
    {
        return LoginIPAddr;
    }

    public String getLoginOutTime()
    {
        return LoginOutTime;
    }

    public String getOperID()
    {
        return OperID;
    }

    public String getOperName()
    {
        return OperName;
    }

    public int getOperType()
    {
        return OperType;
    }

    public void setOperType(int OperType)
    {
        this.OperType = OperType;
    }

    public void setOperName(String OperName)
    {
        this.OperName = OperName;
    }

    public void setOperID(String OperID)
    {
        this.OperID = OperID;
    }

    public void setLoginOutTime(String LoginOutTime)
    {
        this.LoginOutTime = LoginOutTime;
    }

    public void setLoginIPAddr(String LoginIPAddr)
    {
        this.LoginIPAddr = LoginIPAddr;
    }

    public void setLoginInTime(String LoginInTime)
    {
        this.LoginInTime = LoginInTime;
    }

    public void setDepartmentID(String DepartmentID)
    {
        this.DepartmentID = DepartmentID;
    }


}
