package com.dcdzsoft.dto.business;

/**
* 删除软件更新包
*/

public class InParamSMSystemInfoDel implements java.io.Serializable
{
	public String FunctionID = "110303"; //功能编号

	public String OperID = ""; //操作员编号
	public String SystemID = ""; //系统编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110303";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110303";
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