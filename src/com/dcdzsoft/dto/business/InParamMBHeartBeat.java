package com.dcdzsoft.dto.business;

/**
* 设备心跳包检测
*/

public class InParamMBHeartBeat implements java.io.Serializable
{
	public String FunctionID = "150421"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String MBDeviceNo = ""; //运营商设备编号
	public String SignIP = ""; //签到终端IP
	public String SignMac = ""; //签到终端MAC

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150421";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150421";
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

	public String getMBDeviceNo()
	{
		return MBDeviceNo;
	}
	public void setMBDeviceNo(String MBDeviceNo)
	{
		this.MBDeviceNo = MBDeviceNo;
	}

	public String getSignIP()
	{
		return SignIP;
	}
	public void setSignIP(String SignIP)
	{
		this.SignIP = SignIP;
	}

	public String getSignMac()
	{
		return SignMac;
	}
	public void setSignMac(String SignMac)
	{
		this.SignMac = SignMac;
	}

}