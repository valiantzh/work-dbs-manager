package com.dcdzsoft.dto.business;

/**
* 查询控制参数信息
*/

public class OutParamPAControlParamQry implements java.io.Serializable
{
	public int CtrlTypeID; //控制类别编号
	public String CtrlTypeName = ""; //控制类别名称
	public String KeyString = ""; //关键词串
	public String DefaultValue = ""; //默认值
	public String Remark = ""; //备注

	public int getCtrlTypeID()
	{
		return CtrlTypeID;
	}
	public void setCtrlTypeID(int CtrlTypeID)
	{
		this.CtrlTypeID = CtrlTypeID;
	}

	public String getCtrlTypeName()
	{
		return CtrlTypeName;
	}
	public void setCtrlTypeName(String CtrlTypeName)
	{
		this.CtrlTypeName = CtrlTypeName;
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