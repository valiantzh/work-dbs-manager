package com.dcdzsoft.dto.business;

/**
* 设备签到信息查询
*/

public class OutParamMBDeviceSignQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String Location = ""; //安装地址
	public String SignIP = ""; //签到终端IP
	public String SignMac = ""; //签到终端MAC
	public String SoftwareVersion = ""; //终端软件版本号
	public String ServerVersion = ""; //服务端软件版本号
	public String InitPasswd = ""; //安装密码
	public java.sql.Timestamp SignTime; //签到时间
	public String OnlineStatus = ""; //在线状态
	public String OnlineStatusName = ""; //在线状态名称
	public String TerminalStatus = ""; //柜体状态
	public String TerminalStatusName = ""; //柜体状态名称
	public java.sql.Timestamp LastModifyTime; //最后修改时间
	public String Remark = ""; //备注

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
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

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
	}

	public String getSignIP()
	{
		return SignIP;
	}
	public void setSignIP(String SignIP)
	{
		this.SignIP = SignIP;
	}

	public String getSignMac()
	{
		return SignMac;
	}
	public void setSignMac(String SignMac)
	{
		this.SignMac = SignMac;
	}

	public String getSoftwareVersion()
	{
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String SoftwareVersion)
	{
		this.SoftwareVersion = SoftwareVersion;
	}

	public String getServerVersion()
	{
		return ServerVersion;
	}
	public void setServerVersion(String ServerVersion)
	{
		this.ServerVersion = ServerVersion;
	}

	public String getInitPasswd()
	{
		return InitPasswd;
	}
	public void setInitPasswd(String InitPasswd)
	{
		this.InitPasswd = InitPasswd;
	}

	public java.sql.Timestamp getSignTime()
	{
		return SignTime;
	}
	public void setSignTime(java.sql.Timestamp SignTime)
	{
		this.SignTime = SignTime;
	}

	public String getOnlineStatus()
	{
		return OnlineStatus;
	}
	public void setOnlineStatus(String OnlineStatus)
	{
		this.OnlineStatus = OnlineStatus;
	}

	public String getOnlineStatusName()
	{
		return OnlineStatusName;
	}
	public void setOnlineStatusName(String OnlineStatusName)
	{
		this.OnlineStatusName = OnlineStatusName;
	}

	public String getTerminalStatus()
	{
		return TerminalStatus;
	}
	public void setTerminalStatus(String TerminalStatus)
	{
		this.TerminalStatus = TerminalStatus;
	}

	public String getTerminalStatusName()
	{
		return TerminalStatusName;
	}
	public void setTerminalStatusName(String TerminalStatusName)
	{
		this.TerminalStatusName = TerminalStatusName;
	}

	public java.sql.Timestamp getLastModifyTime()
	{
		return LastModifyTime;
	}
	public void setLastModifyTime(java.sql.Timestamp LastModifyTime)
	{
		this.LastModifyTime = LastModifyTime;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}