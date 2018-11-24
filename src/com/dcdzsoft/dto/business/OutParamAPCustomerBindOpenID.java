package com.dcdzsoft.dto.business;

/**
* 绑定合作方用户编号
*/

public class OutParamAPCustomerBindOpenID implements java.io.Serializable
{
	public String CustomerID = ""; //用户编号
	public String UnionID = ""; //用户在合作方的编号
	public String CustomerType = ""; //用户类型0-普通用户；99-投递人员

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

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

}