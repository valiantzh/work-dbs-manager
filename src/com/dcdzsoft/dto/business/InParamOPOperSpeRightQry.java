package com.dcdzsoft.dto.business;

/**
* 管理员特殊权限设置查询
*/

public class InParamOPOperSpeRightQry implements java.io.Serializable
{
	public String FunctionID = "133004"; //功能编号

	public String OperID = ""; //管理员编号
	public String ByOperID = ""; //被操作的管理员编号
	public int SpePrivID; //特殊权限号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "133004";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "133004";
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

	public int getSpePrivID()
	{
		return SpePrivID;
	}
	public void setSpePrivID(int SpePrivID)
	{
		this.SpePrivID = SpePrivID;
	}

}