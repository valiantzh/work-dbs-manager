package com.dcdzsoft.dto.business;

/**
* 设备格口状态报告
*/

public class InParamMBReportBoxStatus implements java.io.Serializable
{
	public String FunctionID = "150402"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String BoxInfo = ""; //箱体信息

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150402";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150402";
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

	public String getBoxInfo()
	{
		return BoxInfo;
	}
	public void setBoxInfo(String BoxInfo)
	{
		this.BoxInfo = BoxInfo;
	}

}