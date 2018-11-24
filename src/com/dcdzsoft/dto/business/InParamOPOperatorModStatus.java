package com.dcdzsoft.dto.business;

/**
* 管理员注销(启用)
*/

public class InParamOPOperatorModStatus implements java.io.Serializable
{
	public String FunctionID = "132008"; //功能编号

	public String OperID = ""; //管理员编号
	public String ByOperID = ""; //被操作的管理员编号
	public String OperateFlag = ""; //操作标志（0:注销1:启用）

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132008";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132008";
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

	public String getOperateFlag()
	{
		return OperateFlag;
	}
	public void setOperateFlag(String OperateFlag)
	{
		this.OperateFlag = OperateFlag;
	}

}