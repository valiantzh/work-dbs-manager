package com.dcdzsoft.dto.business;

/**
* 查询所有的数据字典
*/

public class OutParamPAAllDictQry implements java.io.Serializable
{
	public int DictTypeID; //字典类别编号
	public String DictID = ""; //字典编号
	public String DictName = ""; //字典名称
	public String Remark = ""; //备注

	public int getDictTypeID()
	{
		return DictTypeID;
	}
	public void setDictTypeID(int DictTypeID)
	{
		this.DictTypeID = DictTypeID;
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

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}