package com.dcdzsoft.dto.business;

/**
* 待催领信息查询
*/

public class OutParamMBReminderMsgQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String PackageID = ""; //订单号

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

}