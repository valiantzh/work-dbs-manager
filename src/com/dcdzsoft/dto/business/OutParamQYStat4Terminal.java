package com.dcdzsoft.dto.business;

/**
* 投递情况统计4柜体
*/

public class OutParamQYStat4Terminal implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public int InBoxNum; //在箱快件数量
	public int TakeOutNum; //用户领取数量
	public int TakeBackNum; //投递员取回数量
	public int ExpiredNum; //投递员逾期取回数量
	public int ManagerOutNum; //管理员取回件数量

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

	public int getInBoxNum()
	{
		return InBoxNum;
	}
	public void setInBoxNum(int InBoxNum)
	{
		this.InBoxNum = InBoxNum;
	}

	public int getTakeOutNum()
	{
		return TakeOutNum;
	}
	public void setTakeOutNum(int TakeOutNum)
	{
		this.TakeOutNum = TakeOutNum;
	}

	public int getTakeBackNum()
	{
		return TakeBackNum;
	}
	public void setTakeBackNum(int TakeBackNum)
	{
		this.TakeBackNum = TakeBackNum;
	}

	public int getExpiredNum()
	{
		return ExpiredNum;
	}
	public void setExpiredNum(int ExpiredNum)
	{
		this.ExpiredNum = ExpiredNum;
	}

	public int getManagerOutNum()
	{
		return ManagerOutNum;
	}
	public void setManagerOutNum(int ManagerOutNum)
	{
		this.ManagerOutNum = ManagerOutNum;
	}

}