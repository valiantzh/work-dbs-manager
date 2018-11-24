package com.dcdzsoft.dto.business;

/**
* 用户APP开箱
*/

public class InParamAPCustomerAppOpenBox implements java.io.Serializable
{
	public String FunctionID = "650138"; //功能编号

	public String CustomerMobile = ""; //取件人手机
	public String PackageID = ""; //订单号
	public java.sql.Timestamp StoredTime; //存物时间
	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String OpenBoxKey = ""; //开箱密码

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650138";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650138";
		else
			this.FunctionID = FunctionID;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public java.sql.Timestamp getStoredTime()
	{
		return StoredTime;
	}
	public void setStoredTime(java.sql.Timestamp StoredTime)
	{
		this.StoredTime = StoredTime;
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
	
	public String getOpenBoxKey() {
		return OpenBoxKey;
	}
	
	public void setOpenBoxKey(String openBoxKey) {
		OpenBoxKey = openBoxKey;
	}
}