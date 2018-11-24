package com.dcdzsoft.dto.business;

/**
* 管理员登陆
*/

public class InParamOPOperLogin implements java.io.Serializable
{
	public String FunctionID = "132051"; //功能编号

	public String OperID = ""; //管理员编号
	public String OperPassword = ""; //管理员密码
	public String NetCardAddr = ""; //网卡地址
	public String LoginIPAddr = ""; //登陆IP地址

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132051";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132051";
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

	public String getOperPassword()
	{
		return OperPassword;
	}
	public void setOperPassword(String OperPassword)
	{
		this.OperPassword = OperPassword;
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

}