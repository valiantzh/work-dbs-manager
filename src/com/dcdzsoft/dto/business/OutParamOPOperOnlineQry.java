package com.dcdzsoft.dto.business;

/**
* 管理员在线信息查询
*/

public class OutParamOPOperOnlineQry implements java.io.Serializable
{
	public String OperID = ""; //管理员内部编号
	public String UserID = ""; //用户编号
	public String OperName = ""; //管理员姓名
	public int OperType; //管理员类别
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public java.sql.Timestamp LoginInTime; //登入时间
	public java.sql.Timestamp LoginOutTime; //登出时间
	public String NetCardAddr = ""; //网卡地址
	public String LoginIPAddr = ""; //登陆IP地址
	public java.sql.Timestamp LastLoginTime; //上一次登入时间
	public String LastLoginIP = ""; //上一次登陆IP地址
	public String PreVersion = ""; //上次登录版本号
	public String CurrentVersion = ""; //当前版本号

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getUserID()
	{
		return UserID;
	}
	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}

	public String getOperName()
	{
		return OperName;
	}
	public void setOperName(String OperName)
	{
		this.OperName = OperName;
	}

	public int getOperType()
	{
		return OperType;
	}
	public void setOperType(int OperType)
	{
		this.OperType = OperType;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
	}

	public java.sql.Timestamp getLoginInTime()
	{
		return LoginInTime;
	}
	public void setLoginInTime(java.sql.Timestamp LoginInTime)
	{
		this.LoginInTime = LoginInTime;
	}

	public java.sql.Timestamp getLoginOutTime()
	{
		return LoginOutTime;
	}
	public void setLoginOutTime(java.sql.Timestamp LoginOutTime)
	{
		this.LoginOutTime = LoginOutTime;
	}

	public String getNetCardAddr()
	{
		return NetCardAddr;
	}
	public void setNetCardAddr(String NetCardAddr)
	{
		this.NetCardAddr = NetCardAddr;
	}

	public String getLoginIPAddr()
	{
		return LoginIPAddr;
	}
	public void setLoginIPAddr(String LoginIPAddr)
	{
		this.LoginIPAddr = LoginIPAddr;
	}

	public java.sql.Timestamp getLastLoginTime()
	{
		return LastLoginTime;
	}
	public void setLastLoginTime(java.sql.Timestamp LastLoginTime)
	{
		this.LastLoginTime = LastLoginTime;
	}

	public String getLastLoginIP()
	{
		return LastLoginIP;
	}
	public void setLastLoginIP(String LastLoginIP)
	{
		this.LastLoginIP = LastLoginIP;
	}

	public String getPreVersion()
	{
		return PreVersion;
	}
	public void setPreVersion(String PreVersion)
	{
		this.PreVersion = PreVersion;
	}

	public String getCurrentVersion()
	{
		return CurrentVersion;
	}
	public void setCurrentVersion(String CurrentVersion)
	{
		this.CurrentVersion = CurrentVersion;
	}

}