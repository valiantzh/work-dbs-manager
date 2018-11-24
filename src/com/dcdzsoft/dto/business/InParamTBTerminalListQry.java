package com.dcdzsoft.dto.business;

/**
* 柜体信息列表查询
*/

public class InParamTBTerminalListQry implements java.io.Serializable
{
	public String FunctionID = "210016"; //功能编号

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public String TerminalStatus = ""; //柜体状态
	public String IncludeFlag = ""; //包含子网点编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210016";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210016";
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

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getTerminalStatus()
	{
		return TerminalStatus;
	}
	public void setTerminalStatus(String TerminalStatus)
	{
		this.TerminalStatus = TerminalStatus;
	}
	
	public String getIncludeFlag()
	{
		return IncludeFlag;
	}
	public void setIncludeFlag(String IncludeFlag)
	{
		this.IncludeFlag = IncludeFlag;
	}

}