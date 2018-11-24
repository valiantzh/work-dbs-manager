package com.dcdzsoft.dto.business;

/**
* 投递员APP扫码登录
*/

public class OutParamAPPostmanLogin implements java.io.Serializable
{
	public String PostmanID = ""; //投递员编号
	public String PostmanName = ""; //投递员名称
	public String CompanyID = ""; //投递公司编号
	public String Mobile = ""; //手机号码

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
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

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
	}

}