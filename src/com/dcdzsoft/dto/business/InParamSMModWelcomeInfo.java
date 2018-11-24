package com.dcdzsoft.dto.business;

/**
* 修改欢迎词
*/

public class InParamSMModWelcomeInfo implements java.io.Serializable
{
	public String FunctionID = "110115"; //功能编号

	public String OperID = ""; //操作员编号
	public String SystemID = ""; //系统编号
	public String WelcomeInfo = ""; //欢迎词

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110115";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110115";
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

	public String getWelcomeInfo()
	{
		return WelcomeInfo;
	}
	public void setWelcomeInfo(String WelcomeInfo)
	{
		this.WelcomeInfo = WelcomeInfo;
	}

}