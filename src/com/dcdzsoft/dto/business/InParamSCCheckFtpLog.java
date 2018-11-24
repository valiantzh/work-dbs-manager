package com.dcdzsoft.dto.business;

/**
* 检查对账文件是否上传
*/

public class InParamSCCheckFtpLog implements java.io.Serializable
{
	public String FunctionID = "510111"; //功能编号

	public String TerminalNo = ""; //设备号
	public java.sql.Date StoredDate; //存物日期

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510111";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510111";
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

	public java.sql.Date getStoredDate()
	{
		return StoredDate;
	}
	public void setStoredDate(java.sql.Date StoredDate)
	{
		this.StoredDate = StoredDate;
	}

}