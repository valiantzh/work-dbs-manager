package com.dcdzsoft.dto.business;

/**
* 查询系统字典类别。
*/

public class OutParamPADictTypeQry implements java.io.Serializable
{
	public int DictTypeID; //字典类别编号
	public String DictTypeName = ""; //字典类别名称

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

}