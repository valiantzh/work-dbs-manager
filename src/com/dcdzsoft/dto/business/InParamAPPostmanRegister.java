package com.dcdzsoft.dto.business;

/**
* 投递员注册
*/

public class InParamAPPostmanRegister implements java.io.Serializable
{
	public String FunctionID = "650002"; //功能编号

	public String Mobile = ""; //手机号码
	public String PostmanName = ""; //投递员名称
	public String CompanyID = ""; //投递公司编号
	public String IDCard = ""; //身份证号
	public String Password = ""; //投递员密码

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650002";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650002";
		else
			this.FunctionID = FunctionID;
	}

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
	}

	public String getPostmanName()
	{
		return PostmanName;
	}
	public void setPostmanName(String PostmanName)
	{
		this.PostmanName = PostmanName;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getIDCard()
	{
		return IDCard;
	}
	public void setIDCard(String IDCard)
	{
		this.IDCard = IDCard;
	}
	
	public String getPassword()
	{
		return Password;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}

}