package com.dcdzsoft.dto.business;

/**
* 下载逾期包裹单列表
*/

public class InParamPTExpiredPackQry implements java.io.Serializable
{
	public String FunctionID = "330031"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String PostmanID = ""; //投递员编号
	public String CompanyID = ""; //投递公司编号
	public java.sql.Date ExpiredDate; //逾期日期

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330031";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330031";
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

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public java.sql.Date getExpiredDate()
	{
		return ExpiredDate;
	}
	public void setExpiredDate(java.sql.Date ExpiredDate)
	{
		this.ExpiredDate = ExpiredDate;
	}

}