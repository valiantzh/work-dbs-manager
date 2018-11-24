package com.dcdzsoft.dto.business;

/**
* 功能查询
*/

public class OutParamOPFunctionQry implements java.io.Serializable
{
	public String FunctionID = ""; //功能编号
	public String FunctionName = ""; //功能名称
	public String LogFlag = ""; //记录日志标志
	public String LogFlagName = ""; //记录日志标志名称
	public String OpenFlag = ""; //开通标志
	public String OpenFlagName = ""; //开通标志名称

	public String getFunctionID()
	{
		return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		this.FunctionID = FunctionID;
	}

	public String getFunctionName()
	{
		return FunctionName;
	}
	public void setFunctionName(String FunctionName)
	{
		this.FunctionName = FunctionName;
	}

	public String getLogFlag()
	{
		return LogFlag;
	}
	public void setLogFlag(String LogFlag)
	{
		this.LogFlag = LogFlag;
	}

	public String getLogFlagName()
	{
		return LogFlagName;
	}
	public void setLogFlagName(String LogFlagName)
	{
		this.LogFlagName = LogFlagName;
	}

	public String getOpenFlag()
	{
		return OpenFlag;
	}
	public void setOpenFlag(String OpenFlag)
	{
		this.OpenFlag = OpenFlag;
	}

	public String getOpenFlagName()
	{
		return OpenFlagName;
	}
	public void setOpenFlagName(String OpenFlagName)
	{
		this.OpenFlagName = OpenFlagName;
	}

}