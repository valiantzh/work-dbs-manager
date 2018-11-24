package com.dcdzsoft.dto.business;

/**
* 会员余额查询
*/

public class OutParamCMCustomerBalanceQry implements java.io.Serializable
{
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerType = ""; //会员类型（99-投递员）
	public String CustomerMobile = ""; //手机
	public String BindCardID = ""; //绑定卡号
	public double Balance; //账户余额
	public int Discount; //折扣(%)

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

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
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

	public double getBalance()
	{
		return Balance;
	}
	public void setBalance(double Balance)
	{
		this.Balance = Balance;
	}

	public int getDiscount()
	{
		return Discount;
	}
	public void setDiscount(int Discount)
	{
		this.Discount = Discount;
	}

}