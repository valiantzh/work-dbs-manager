package com.dcdzsoft.dto.business;

/**
* 设备端控制参数信息查询
*/

public class InParamPATerminalCtrlParamQry implements java.io.Serializable
{
	public String FunctionID = "120014"; //功能编号

	public String OperID = ""; //操作员编号
	public int CtrlTypeID; //控制类别编号
	public String KeyString = ""; //关键词串

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "120014";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "120014";
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

	public int getCtrlTypeID()
	{
		return CtrlTypeID;
	}
	public void setCtrlTypeID(int CtrlTypeID)
	{
		this.CtrlTypeID = CtrlTypeID;
	}

	public String getKeyString()
	{
		return KeyString;
	}
	public void setKeyString(String KeyString)
	{
		this.KeyString = KeyString;
	}

}