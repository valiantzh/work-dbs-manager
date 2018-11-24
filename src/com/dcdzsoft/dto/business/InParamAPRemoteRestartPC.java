package com.dcdzsoft.dto.business;

/**
* 管理员远程重启工控
*/

public class InParamAPRemoteRestartPC implements java.io.Serializable
{
	public String FunctionID = "650328"; //功能编号

	public String TerminalNo = ""; //设备号
	public String RestartPWD = ""; //重启密码
	public String OperID = ""; //管理员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650328";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650328";
		else
			this.FunctionID = FunctionID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getRestartPWD()
	{
		return RestartPWD;
	}
	public void setRestartPWD(String RestartPWD)
	{
		this.RestartPWD = RestartPWD;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

}