package com.dcdzsoft.dto.business;

/**
* 设备列表查询
*/

public class InParamAPTerminalListQry implements java.io.Serializable
{
	public String FunctionID = "650207"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String TerminalStatus = ""; //柜体状态

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650207";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650207";
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

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getTerminalStatus()
	{
		return TerminalStatus;
	}
	public void setTerminalStatus(String TerminalStatus)
	{
		this.TerminalStatus = TerminalStatus;
	}

}