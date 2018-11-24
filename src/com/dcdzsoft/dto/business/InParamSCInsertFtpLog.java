package com.dcdzsoft.dto.business;

/**
* 插入对账文件上传日志
*/

public class InParamSCInsertFtpLog implements java.io.Serializable
{
	public String FunctionID = "510112"; //功能编号

	public String TerminalNo = ""; //设备号
	public java.sql.Date StoredDate; //存物日期
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510112";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510112";
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

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}