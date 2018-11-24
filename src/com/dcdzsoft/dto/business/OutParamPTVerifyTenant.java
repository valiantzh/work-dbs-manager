package com.dcdzsoft.dto.business;

/**
* 用户验证-住户账号验证
*/

public class OutParamPTVerifyTenant implements java.io.Serializable
{
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerMobile = ""; //手机
	public String FirstName = ""; //用户名
	public String LastName = ""; //用户姓
	public String SpecialFlag = ""; //特殊标志
	public String Address = ""; //地址
	public String Email = ""; //邮箱

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getCustomerName()
	{
		return CustomerName;
	}
	public void setCustomerName(String CustomerName)
	{
		this.CustomerName = CustomerName;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getFirstName()
	{
		return FirstName;
	}
	public void setFirstName(String FirstName)
	{
		this.FirstName = FirstName;
	}

	public String getLastName()
	{
		return LastName;
	}
	public void setLastName(String LastName)
	{
		this.LastName = LastName;
	}

	public String getSpecialFlag()
	{
		return SpecialFlag;
	}
	public void setSpecialFlag(String SpecialFlag)
	{
		this.SpecialFlag = SpecialFlag;
	}

	public String getAddress()
	{
		return Address;
	}
	public void setAddress(String Address)
	{
		this.Address = Address;
	}

	public String getEmail()
	{
		return Email;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}

}