package com.dcdzsoft.dto.business;

/**
* 取件开箱（新接口-PDA）
*/

public class InParamAPTakeOutOpenBox implements java.io.Serializable
{
	public String FunctionID = "650502"; //功能编号

	public String TradeWaterNo = ""; //交易流水号
	public String OperID = ""; //操作员编号
	public String TerminalNo = ""; //设备号
	public String BoxNo = ""; //箱号
	public String ForceOpen = ""; //强制开箱,
	public String TakeType = ""; //取出类型：
	public String OccurTime = ""; //请求时间

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650502";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650502";
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

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getForceOpen()
	{
		return ForceOpen;
	}
	public void setForceOpen(String ForceOpen)
	{
		this.ForceOpen = ForceOpen;
	}

	public String getTakeType()
	{
		return TakeType;
	}
	public void setTakeType(String TakeType)
	{
		this.TakeType = TakeType;
	}

	public String getOccurTime()
	{
		return OccurTime;
	}
	public void setOccurTime(String OccurTime)
	{
		this.OccurTime = OccurTime;
	}

}