package com.dcdzsoft.dto.business;

/**
* 功能查询
*/

public class InParamOPFunctionQry implements java.io.Serializable
{
	public String FunctionID = "135004"; //功能编号

	public String OperID = ""; //操作员编号
	public String FactFunctionID = ""; //功能编号
	public String LogFlag = ""; //记录日志标志
	public String OpenFlag = ""; //开通标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "135004";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "135004";
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

	public String getFactFunctionID()
	{
		return FactFunctionID;
	}
	public void setFactFunctionID(String FactFunctionID)
	{
		this.FactFunctionID = FactFunctionID;
	}

	public String getLogFlag()
	{
		return LogFlag;
	}
	public void setLogFlag(String LogFlag)
	{
		this.LogFlag = LogFlag;
	}

	public String getOpenFlag()
	{
		return OpenFlag;
	}
	public void setOpenFlag(String OpenFlag)
	{
		this.OpenFlag = OpenFlag;
	}

}