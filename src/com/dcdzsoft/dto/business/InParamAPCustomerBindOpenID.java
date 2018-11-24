package com.dcdzsoft.dto.business;

/**
* 绑定合作方用户编号
*/

public class InParamAPCustomerBindOpenID implements java.io.Serializable
{
	public String FunctionID = "650441"; //功能编号

	public String CustomerID = ""; //用户编号
	public String UnionID = ""; //用户在合作方的编号
	public String CustomerName = ""; //用户名称（昵称）

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650441";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650441";
		else
			this.FunctionID = FunctionID;
	}

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getUnionID()
	{
		return UnionID;
	}
	public void setUnionID(String UnionID)
	{
		this.UnionID = UnionID;
	}

	public String getCustomerName()
	{
		return CustomerName;
	}
	public void setCustomerName(String CustomerName)
	{
		this.CustomerName = CustomerName;
	}

}