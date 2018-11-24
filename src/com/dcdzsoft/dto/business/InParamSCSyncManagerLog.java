package com.dcdzsoft.dto.business;

/**
* 同步管理端操作日志
*/

public class InParamSCSyncManagerLog implements java.io.Serializable
{
	public String FunctionID = "510301"; //功能编号

	public String OperID = ""; //操作员编号
	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String FactFunctionID = ""; //实际功能编号
	public java.sql.Timestamp OccurTime; //交易时间
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510301";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510301";
		else
			this.FunctionID = FunctionID;
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

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getFactFunctionID()
	{
		return FactFunctionID;
	}
	public void setFactFunctionID(String FactFunctionID)
	{
		this.FactFunctionID = FactFunctionID;
	}

	public java.sql.Timestamp getOccurTime()
	{
		return OccurTime;
	}
	public void setOccurTime(java.sql.Timestamp OccurTime)
	{
		this.OccurTime = OccurTime;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}