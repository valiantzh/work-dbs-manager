package com.dcdzsoft.dto.business;

/**
* PDA投递授权
*/

public class InParamPTDeliveryAuth4PDA implements java.io.Serializable
{
	public String FunctionID = "330004"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String PostmanID = ""; //投递员编号
	public String PDANo = ""; //PDA编号
	public String FreeBoxList = ""; //可用格口信息
	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330004";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330004";
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

	public String getPDANo()
	{
		return PDANo;
	}
	public void setPassword(String PDANo)
	{
		this.PDANo = PDANo;
	}

	public String getFreeBoxList()
	{
		return FreeBoxList;
	}
	public void setFreeBoxList(String FreeBoxList)
	{
		this.FreeBoxList = FreeBoxList;
	}
}