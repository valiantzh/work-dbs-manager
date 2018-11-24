package com.dcdzsoft.dto.business;

/**
* 查询系统信息数量
*/

public class InParamSMSystemInfoQryCount implements java.io.Serializable
{
	public String FunctionID = "110305"; //功能编号

	public String OperID = ""; //操作员编号
	public String SystemID = ""; //系统编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110305";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110305";
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

}