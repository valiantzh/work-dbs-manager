package com.dcdzsoft.dto.business;

/**
* 下载在箱包裹信息
*/

public class InParamPTDownloadInboxInfo implements java.io.Serializable
{
	public String FunctionID = "330511"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String DeskNo = ""; //副柜编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330511";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330511";
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

	public String getDeskNo()
	{
		return DeskNo;
	}
	public void setDeskNo(String DeskNo)
	{
		this.DeskNo = DeskNo;
	}

}