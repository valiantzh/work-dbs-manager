package com.dcdzsoft.dto.business;

/**
* 本次投递汇总
*/

public class InParamPTDeliverySum implements java.io.Serializable
{
	public String FunctionID = "330061"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String PostmanID = ""; //投递员编号
	public String OrderList = ""; //投递订单列表
	public java.sql.Timestamp OccurTime; //发生时间

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330061";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330061";
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

	public String getOrderList()
	{
		return OrderList;
	}
	public void setOrderList(String OrderList)
	{
		this.OrderList = OrderList;
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