package com.dcdzsoft.dto.business;

/**
* 删除系统字典。
*/

public class InParamPADictionaryDel implements java.io.Serializable
{
	public String FunctionID = "120037"; //功能编号

	public String OperID = ""; //操作员编号
	public int DictTypeID; //字典类别编号
	public String DictID = ""; //字典编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "120037";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "120037";
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

}