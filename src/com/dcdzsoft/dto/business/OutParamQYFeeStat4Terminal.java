package com.dcdzsoft.dto.business;

/**
* 逾期收费情况统计4柜体
*/

public class OutParamQYFeeStat4Terminal implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public int ExpiredNum; //逾期取回数量

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public int getExpiredNum()
	{
		return ExpiredNum;
	}
	public void setExpiredNum(int ExpiredNum)
	{
		this.ExpiredNum = ExpiredNum;
	}

}