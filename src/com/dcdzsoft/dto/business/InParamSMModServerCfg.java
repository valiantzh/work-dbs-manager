package com.dcdzsoft.dto.business;

/**
* 修改服务器连接设置
*/

public class InParamSMModServerCfg implements java.io.Serializable
{
	public String FunctionID = "110114"; //功能编号

	public String OperID = ""; //操作员编号
	public String SystemID = ""; //系统编号
	public String ServerIP = ""; //服务器IP地址
	public int ServerPort; //服务器端口号
	public String ServerSSL = ""; //采用加密通讯
	public String MonServerIP = ""; //监控服务器IP地址
	public int MonServerPort; //监控服务器端口号
	public String RemoteFlag = ""; //远程操作标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110114";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110114";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getSystemID()
	{
		return SystemID;
	}
	public void setSystemID(String SystemID)
	{
		this.SystemID = SystemID;
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

	public String getRemoteFlag()
	{
		return RemoteFlag;
	}
	public void setRemoteFlag(String RemoteFlag)
	{
		this.RemoteFlag = RemoteFlag;
	}

}