package com.dcdzsoft.dto.business;

/**
* 绑定合作方用户信息查询
*/

public class OutParamAPCustomerBindOpenIDQry implements java.io.Serializable
{
	public String CustomerID = ""; //用户编号
	public String CustomerType = ""; //用户类型0-普通用户；99-投递人员
	public String BindMobile = ""; //绑定的手机号码
	public String CustomerName = ""; //用户姓名
	public String UnionID = ""; //用户在合作方的编号

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

	public String getBindMobile()
	{
		return BindMobile;
	}
	public void setBindMobile(String BindMobile)
	{
		this.BindMobile = BindMobile;
	}

	public String getCustomerName()
	{
		return CustomerName;
	}
	public void setCustomerName(String CustomerName)
	{
		this.CustomerName = CustomerName;
	}

	public String getUnionID()
	{
		return UnionID;
	}
	public void setUnionID(String UnionID)
	{
		this.UnionID = UnionID;
	}

}