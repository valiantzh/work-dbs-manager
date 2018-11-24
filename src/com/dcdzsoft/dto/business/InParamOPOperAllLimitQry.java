package com.dcdzsoft.dto.business;

/**
* 管理员总体限制权限查询
*/

public class InParamOPOperAllLimitQry implements java.io.Serializable
{
	public String FunctionID = "132019"; //功能编号

	public String OperID = ""; //管理员编号
	public String ByOperID = ""; //被操作的管理员编号
	public int LimitTypeID; //限制类别编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132019";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132019";
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

	public int getLimitTypeID()
	{
		return LimitTypeID;
	}
	public void setLimitTypeID(int LimitTypeID)
	{
		this.LimitTypeID = LimitTypeID;
	}

}