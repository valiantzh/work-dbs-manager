package com.dcdzsoft.dto.business;

/**
* 查询系统字典。
*/

public class OutParamPADictionaryQry implements java.io.Serializable
{
	public int DictTypeID; //字典类别编号
	public String DictTypeName = ""; //字典类别名称
	public String DictID = ""; //字典编号
	public String DictName = ""; //字典名称
	public String OtherDictName = ""; //对照字典名称
	public String Remark = ""; //备注

	public int getDictTypeID()
	{
		return DictTypeID;
	}
	public void setDictTypeID(int DictTypeID)
	{
		this.DictTypeID = DictTypeID;
	}

	public String getDictTypeName()
	{
		return DictTypeName;
	}
	public void setDictTypeName(String DictTypeName)
	{
		this.DictTypeName = DictTypeName;
	}

	public String getDictID()
	{
		return DictID;
	}
	public void setDictID(String DictID)
	{
		this.DictID = DictID;
	}

	public String getDictName()
	{
		return DictName;
	}
	public void setDictName(String DictName)
	{
		this.DictName = DictName;
	}

	public String getOtherDictName()
	{
		return OtherDictName;
	}
	public void setOtherDictName(String OtherDictName)
	{
		this.OtherDictName = OtherDictName;
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