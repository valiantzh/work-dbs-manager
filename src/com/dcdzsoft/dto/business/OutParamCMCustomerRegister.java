package com.dcdzsoft.dto.business;

/**
* 会员注册
*/

public class OutParamCMCustomerRegister implements java.io.Serializable
{
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerMobile = ""; //手机
	public String BindCardID = ""; //绑定卡号
	public String CheckCode = ""; //验证码
	public String Remark = ""; //备注

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

	public String getBindCardID()
	{
		return BindCardID;
	}
	public void setBindCardID(String BindCardID)
	{
		this.BindCardID = BindCardID;
	}

	public String getCheckCode()
	{
		return CheckCode;
	}
	public void setCheckCode(String CheckCode)
	{
		this.CheckCode = CheckCode;
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