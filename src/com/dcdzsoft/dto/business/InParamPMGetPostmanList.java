package com.dcdzsoft.dto.business;

/**
* 获取投递员列表
*/

public class InParamPMGetPostmanList implements java.io.Serializable
{
	public String FunctionID = "311041"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "311041";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "311041";
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

}