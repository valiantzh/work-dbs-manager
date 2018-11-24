package com.dcdzsoft.dto.business;

/**
* 终端软件升级信息查询
*/

public class OutParamMBTerminalUpgradeQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String Location = ""; //安装地址
	public java.sql.Timestamp CreateTime; //创建时间
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
	public String DownloadUrl = ""; //下载路径
	public String MD5Verify = ""; //校验码
	public String UpgradeDesc = ""; //升级描述
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

	public java.sql.Timestamp getCreateTime()
	{
		return CreateTime;
	}
	public void setCreateTime(java.sql.Timestamp CreateTime)
	{
		this.CreateTime = CreateTime;
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

	public String getDownloadUrl()
	{
		return DownloadUrl;
	}
	public void setDownloadUrl(String DownloadUrl)
	{
		this.DownloadUrl = DownloadUrl;
	}

	public String getMD5Verify()
	{
		return MD5Verify;
	}
	public void setMD5Verify(String MD5Verify)
	{
		this.MD5Verify = MD5Verify;
	}

	public String getUpgradeDesc()
	{
		return UpgradeDesc;
	}
	public void setUpgradeDesc(String UpgradeDesc)
	{
		this.UpgradeDesc = UpgradeDesc;
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