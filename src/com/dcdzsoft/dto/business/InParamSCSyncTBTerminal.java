package com.dcdzsoft.dto.business;

/**
* 同步柜体信息
*/

public class InParamSCSyncTBTerminal implements java.io.Serializable
{
	public String FunctionID = "510401"; //功能编号

	public java.sql.Timestamp LastSyncTime; //上次同步时间

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510401";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510401";
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

}