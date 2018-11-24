package com.dcdzsoft.dto.business;

/**
* 会员余额查询
*/

public class InParamCMCustomerBalanceQry implements java.io.Serializable
{
	public String FunctionID = "170114"; //功能编号

	public String CustomerID = ""; //会员编号（卡号、手机号）
	public String CustomerType = ""; //会员类型（99-投递员）
	public String BindCardID = ""; //绑定卡号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "170114";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "170114";
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

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

	public String getBindCardID()
	{
		return BindCardID;
	}
	public void setBindCardID(String BindCardID)
	{
		this.BindCardID = BindCardID;
	}

}