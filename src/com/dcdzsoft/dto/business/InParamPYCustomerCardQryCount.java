package com.dcdzsoft.dto.business;

/**
* 查询会员卡数量
*/

public class InParamPYCustomerCardQryCount implements java.io.Serializable
{
	public String FunctionID = "410205"; //功能编号

	public String OperID = ""; //管理员编号
	public String CardNo = ""; //会员卡编号
	public String CardType = ""; //会员卡类型
	public String CardStatus = ""; //会员卡状态
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerMobile = ""; //会员手机
	public String CustomerType = ""; //会员类型（99-投递员）
	public String CustomerStatus = ""; //会员状态（0-正常，1-注销，2-注册未激活）
	public String DepartmentID = ""; //运营网点编号
	public String CompanyID = ""; //投递公司编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410205";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410205";
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

	public String getCardNo()
	{
		return CardNo;
	}
	public void setCardNo(String CardNo)
	{
		this.CardNo = CardNo;
	}

	public String getCardType()
	{
		return CardType;
	}
	public void setCardType(String CardType)
	{
		this.CardType = CardType;
	}

	public String getCardStatus()
	{
		return CardStatus;
	}
	public void setCardStatus(String CardStatus)
	{
		this.CardStatus = CardStatus;
	}

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

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

	public String getCustomerStatus()
	{
		return CustomerStatus;
	}
	public void setCustomerStatus(String CustomerStatus)
	{
		this.CustomerStatus = CustomerStatus;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

}