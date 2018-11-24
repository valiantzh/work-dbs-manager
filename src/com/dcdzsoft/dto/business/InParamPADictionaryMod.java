package com.dcdzsoft.dto.business;

/**
* 修改系统字典。
*/

public class InParamPADictionaryMod implements java.io.Serializable
{
	public String FunctionID = "120036"; //功能编号

	public String OperID = ""; //操作员编号
	public int DictTypeID; //字典类别编号
	public String DictID = ""; //字典编号
	public String DictName = ""; //字典名称
	public String OtherDictName = ""; //对照字典名称
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "120036";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "120036";
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