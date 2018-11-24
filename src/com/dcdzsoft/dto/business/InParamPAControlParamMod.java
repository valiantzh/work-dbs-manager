package com.dcdzsoft.dto.business;

/**
* 修改控制参数信息。
*/

public class InParamPAControlParamMod implements java.io.Serializable
{
	public String FunctionID = "120002"; //功能编号

	public String OperID = ""; //操作员编号
	public int CtrlTypeID; //控制类别编号
	public String KeyString = ""; //关键词串
	public String DefaultValue = ""; //默认值
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "120002";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "120002";
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

	public String getDefaultValue()
	{
		return DefaultValue;
	}
	public void setDefaultValue(String DefaultValue)
	{
		this.DefaultValue = DefaultValue;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}