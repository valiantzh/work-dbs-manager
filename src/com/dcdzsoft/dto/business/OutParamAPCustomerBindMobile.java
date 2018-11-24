package com.dcdzsoft.dto.business;

/**
* 修改绑定手机号
*/

public class OutParamAPCustomerBindMobile implements java.io.Serializable
{
	public String CustomerID = ""; //用户编号
	public String CustomerType = ""; //用户类型0-普通用户；99-投递人员
	public String BindMobile = ""; //绑定的手机号码

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

}