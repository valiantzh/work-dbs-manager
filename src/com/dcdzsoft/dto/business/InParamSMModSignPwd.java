package com.dcdzsoft.dto.business;

/**
* 修改签到密码
*/

public class InParamSMModSignPwd implements java.io.Serializable
{
	public String FunctionID = "110113"; //功能编号

	public String OperID = ""; //操作员编号
	public String SystemID = ""; //系统编号
	public String InitPasswd = ""; //安装密码
	public String TerminalPasswd = ""; //设备密码
	public String RemoteFlag = ""; //远程操作标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110113";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110113";
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

	public String getRemoteFlag()
	{
		return RemoteFlag;
	}
	public void setRemoteFlag(String RemoteFlag)
	{
		this.RemoteFlag = RemoteFlag;
	}

}