package com.dcdzsoft.dto.business;

/**
* 箱体信息列表查询
*/

public class OutParamTBBoxListQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String BoxNo = ""; //箱门编号
	public String BoxName = ""; //箱门名称

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

	public String getBoxName()
	{
		return BoxName;
	}
	public void setBoxName(String BoxName)
	{
		this.BoxName = BoxName;
	}

}