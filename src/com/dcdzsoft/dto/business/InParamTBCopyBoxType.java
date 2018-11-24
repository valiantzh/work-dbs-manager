package com.dcdzsoft.dto.business;

/**
* 复制箱体类型
*/

public class InParamTBCopyBoxType implements java.io.Serializable
{
	public String FunctionID = "210273"; //功能编号

	public String OperID = ""; //管理员编号
	public int DeskNoSrc; //源副柜编号
	public int DeskNoDest; //目标副柜编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210273";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210273";
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

	public int getDeskNoSrc()
	{
		return DeskNoSrc;
	}
	public void setDeskNoSrc(int DeskNoSrc)
	{
		this.DeskNoSrc = DeskNoSrc;
	}

	public int getDeskNoDest()
	{
		return DeskNoDest;
	}
	public void setDeskNoDest(int DeskNoDest)
	{
		this.DeskNoDest = DeskNoDest;
	}

}