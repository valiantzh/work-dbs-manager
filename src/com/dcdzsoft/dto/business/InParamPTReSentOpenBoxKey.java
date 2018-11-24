package com.dcdzsoft.dto.business;

/**
* 重新发送用户取件密码
*/

public class InParamPTReSentOpenBoxKey implements java.io.Serializable
{
	public String FunctionID = "330515"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String PostmanID = ""; //投递员编号
	public String DynamicCode = ""; //动态码
	public String CompanyID = ""; //投递公司编号
	public String PackageID = ""; //订单号
	public String CustomerMobile = ""; //取件人手机
	public java.sql.Timestamp OccurTime; //发生时间

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330515";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330515";
		else
			this.FunctionID = FunctionID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getDynamicCode()
	{
		return DynamicCode;
	}
	public void setDynamicCode(String DynamicCode)
	{
		this.DynamicCode = DynamicCode;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public java.sql.Timestamp getOccurTime()
	{
		return OccurTime;
	}
	public void setOccurTime(java.sql.Timestamp OccurTime)
	{
		this.OccurTime = OccurTime;
	}

}