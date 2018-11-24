package com.dcdzsoft.dto.business;

/**
* 格口使用统计
*/

public class OutParamQYBoxUsedStat implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public int BoxNum; //格口数量
	public int UsedNum; //占用格口数
	public int FreeSmallNum; //空闲小格口数
	public int FreeMediumNum; //空闲中格口数
	public int FreeLargeNum; //空闲大格口数
	public int FreeSuperNum; //空闲超大格口数
	public int FreeFreshNum; //空闲生鲜格口数
	public int FaultNum; //故障格口数

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

	public int getBoxNum()
	{
		return BoxNum;
	}
	public void setBoxNum(int BoxNum)
	{
		this.BoxNum = BoxNum;
	}

	public int getUsedNum()
	{
		return UsedNum;
	}
	public void setUsedNum(int UsedNum)
	{
		this.UsedNum = UsedNum;
	}

	public int getFreeSmallNum()
	{
		return FreeSmallNum;
	}
	public void setFreeSmallNum(int FreeSmallNum)
	{
		this.FreeSmallNum = FreeSmallNum;
	}

	public int getFreeMediumNum()
	{
		return FreeMediumNum;
	}
	public void setFreeMediumNum(int FreeMediumNum)
	{
		this.FreeMediumNum = FreeMediumNum;
	}

	public int getFreeLargeNum()
	{
		return FreeLargeNum;
	}
	public void setFreeLargeNum(int FreeLargeNum)
	{
		this.FreeLargeNum = FreeLargeNum;
	}

	public int getFreeSuperNum()
	{
		return FreeSuperNum;
	}
	public void setFreeSuperNum(int FreeSuperNum)
	{
		this.FreeSuperNum = FreeSuperNum;
	}

	public int getFreeFreshNum()
	{
		return FreeFreshNum;
	}
	public void setFreeFreshNum(int FreeFreshNum)
	{
		this.FreeFreshNum = FreeFreshNum;
	}

	public int getFaultNum()
	{
		return FaultNum;
	}
	public void setFaultNum(int FaultNum)
	{
		this.FaultNum = FaultNum;
	}

}