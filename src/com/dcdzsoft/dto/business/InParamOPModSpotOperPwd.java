package com.dcdzsoft.dto.business;

/**
* 修改现场管理员密码
*/

public class InParamOPModSpotOperPwd implements java.io.Serializable
{
	public String FunctionID = "133052"; //功能编号

	public String OperID = ""; //管理员编号
	public String ByOperID = ""; //内部用户编号
	public String OperPassword = ""; //管理员密码
	public String TerminalNoStr = ""; //设备列表

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "133052";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "133052";
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

	public String getByOperID()
	{
		return ByOperID;
	}
	public void setByOperID(String ByOperID)
	{
		this.ByOperID = ByOperID;
	}

	public String getOperPassword()
	{
		return OperPassword;
	}
	public void setOperPassword(String OperPassword)
	{
		this.OperPassword = OperPassword;
	}

	public String getTerminalNoStr()
	{
		return TerminalNoStr;
	}
	public void setTerminalNoStr(String TerminalNoStr)
	{
		this.TerminalNoStr = TerminalNoStr;
	}

}