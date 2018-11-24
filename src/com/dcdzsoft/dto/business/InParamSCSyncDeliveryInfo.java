package com.dcdzsoft.dto.business;

/**
* 同步投递信息
*/

public class InParamSCSyncDeliveryInfo implements java.io.Serializable
{
	public String FunctionID = "510431"; //功能编号

	public java.sql.Timestamp LastSyncTime; //上次同步时间
	public String TerminalNo = ""; //设备号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510431";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510431";
		else
			this.FunctionID = FunctionID;
	}

	public java.sql.Timestamp getLastSyncTime()
	{
		return LastSyncTime;
	}
	public void setLastSyncTime(java.sql.Timestamp LastSyncTime)
	{
		this.LastSyncTime = LastSyncTime;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

}