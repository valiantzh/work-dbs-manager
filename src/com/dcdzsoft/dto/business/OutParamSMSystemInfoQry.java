package com.dcdzsoft.dto.business;

/**
* 查询系统信息
*/

public class OutParamSMSystemInfoQry implements java.io.Serializable
{
	public String SystemID = ""; //系统编号
	public String InitPasswd = ""; //安装密码
	public String TerminalPasswd = ""; //终端密码
	public String ServerIP = ""; //服务器IP地址
	public int ServerPort; //服务器端口号
	public String ServerSSL = ""; //服务器通讯
	public String MonServerIP = ""; //监控服务器IP地址
	public int MonServerPort; //监控服务器端口号
	public java.sql.Timestamp LastModifyTime; //最后修改时间
	public String SoftwareVersion = ""; //软件版本
	public String UpdateContent = ""; //更新内容
	public String Remark = ""; //备注

	public String getSystemID()
	{
		return SystemID;
	}
	public void setSystemID(String SystemID)
	{
		this.SystemID = SystemID;
	}

	public String getInitPasswd()
	{
		return InitPasswd;
	}
	public void setInitPasswd(String InitPasswd)
	{
		this.InitPasswd = InitPasswd;
	}

	public String getTerminalPasswd()
	{
		return TerminalPasswd;
	}
	public void setTerminalPasswd(String TerminalPasswd)
	{
		this.TerminalPasswd = TerminalPasswd;
	}

	public String getServerIP()
	{
		return ServerIP;
	}
	public void setServerIP(String ServerIP)
	{
		this.ServerIP = ServerIP;
	}

	public int getServerPort()
	{
		return ServerPort;
	}
	public void setServerPort(int ServerPort)
	{
		this.ServerPort = ServerPort;
	}

	public String getServerSSL()
	{
		return ServerSSL;
	}
	public void setServerSSL(String ServerSSL)
	{
		this.ServerSSL = ServerSSL;
	}

	public String getMonServerIP()
	{
		return MonServerIP;
	}
	public void setMonServerIP(String MonServerIP)
	{
		this.MonServerIP = MonServerIP;
	}

	public int getMonServerPort()
	{
		return MonServerPort;
	}
	public void setMonServerPort(int MonServerPort)
	{
		this.MonServerPort = MonServerPort;
	}

	public java.sql.Timestamp getLastModifyTime()
	{
		return LastModifyTime;
	}
	public void setLastModifyTime(java.sql.Timestamp LastModifyTime)
	{
		this.LastModifyTime = LastModifyTime;
	}

	public String getSoftwareVersion()
	{
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String SoftwareVersion)
	{
		this.SoftwareVersion = SoftwareVersion;
	}

	public String getUpdateContent()
	{
		return UpdateContent;
	}
	public void setUpdateContent(String UpdateContent)
	{
		this.UpdateContent = UpdateContent;
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