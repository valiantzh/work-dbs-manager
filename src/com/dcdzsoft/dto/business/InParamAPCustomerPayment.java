package com.dcdzsoft.dto.business;

/**
* 用户付费开箱
*/

public class InParamAPCustomerPayment implements java.io.Serializable
{
	public String FunctionID = "650139"; //功能编号

	public String CustomerMobile = ""; //取件人手机
	public String PackageID = ""; //订单号
	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public double PayedAmt; //支付金额

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650139";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650139";
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

	public double getPayedAmt()
	{
		return PayedAmt;
	}
	public void setPayedAmt(double PayedAmt)
	{
		this.PayedAmt = PayedAmt;
	}

}