package com.dcdzsoft.dto.business;

/**
* 修改系统信息
*/

public class InParamSMSystemInfoMod implements java.io.Serializable
{
	public String FunctionID = "110302"; //功能编号

	public String OperID = ""; //操作员编号
	public String SystemID = ""; //系统编号
	public String InitPasswd = ""; //安装密码
	public String TerminalPasswd = ""; //终端密码
	public String ServerIP = ""; //服务器IP地址
	public int ServerPort; //服务器端口号
	public String MonServerIP = ""; //监控服务器IP地址
	public int MonServerPort; //监控服务器端口号
	public String UpdateContent = ""; //更新内容
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110302";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110302";
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