package com.dcdzsoft.dto.business;

/**
* 重新获取验证码
*/

public class InParamAPGetCheckCode implements java.io.Serializable
{
	public String FunctionID = "650401"; //功能编号

	public String TradeWaterNo = ""; //交易流水号
	public String Mobile = ""; //手机

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650401";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650401";
		else
			this.FunctionID = FunctionID;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
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