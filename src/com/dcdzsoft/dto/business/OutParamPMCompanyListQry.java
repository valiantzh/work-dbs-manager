package com.dcdzsoft.dto.business;

/**
* 投递公司列表查询
*/

public class OutParamPMCompanyListQry implements java.io.Serializable
{
	public String CompanyID = ""; //投递公司编号
	public String CompanyName = ""; //投递公司名称

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getCompanyName()
	{
		return CompanyName;
	}
	public void setCompanyName(String CompanyName)
	{
		this.CompanyName = CompanyName;
	}

}