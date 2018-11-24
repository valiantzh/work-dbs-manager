package com.dcdzsoft.dto.business;

/**
* 会员列表查询
*/

public class OutParamCMCustomerListQry implements java.io.Serializable
{
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String BindCardID = ""; //绑定卡号
	public int Balance; //账户余额（单位：分）

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

	public String getBindCardID()
	{
		return BindCardID;
	}
	public void setBindCardID(String BindCardID)
	{
		this.BindCardID = BindCardID;
	}

	public int getBalance()
	{
		return Balance;
	}
	public void setBalance(int Balance)
	{
		this.Balance = Balance;
	}

}