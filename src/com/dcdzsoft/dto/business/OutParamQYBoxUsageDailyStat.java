package com.dcdzsoft.dto.business;

/**
* 格口使用情况统计(每天)
*/

public class OutParamQYBoxUsageDailyStat implements java.io.Serializable
{
	public java.sql.Date StatDate; //统计日期
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public int BoxNum; //格口数量
	public int SmallNum; //小格口数
	public int MediumNum; //中格口数
	public int LargeNum; //大格口数
	public int SuperNum; //超大格口数
	public int SuperSmallNum; //超小格口数
	public int FreshNum; //生鲜格口数
	public int UsedBoxNum; //占用格口数
	public int UsedSmallNum; //占用小格口数
	public int UsedMediumNum; //占用中格口数
	public int UsedLargeNum; //占用大格口数
	public int UsedSuperNum; //占用超大格口数
	public int UsedSuperSmallNum; //占用超小格口数
	public int UsedFreshNum; //占用生鲜格口数
	public double UsedBoxFreq; //格口使用率
	public double UsedSmallFreq; //小格口使用率
	public double UsedMediumFreq; //中格口使用率
	public double UsedLargeFreq; //大格口使用率
	public double UsedSuperFreq; //超大格口使用率
	public double UsedSuperSmallFreq; //超小格口使用率
	public double UsedFreshFreq; //生鲜格口使用率

	public java.sql.Date getStatDate()
	{
		return StatDate;
	}
	public void setStatDate(java.sql.Date StatDate)
	{
		this.StatDate = StatDate;
	}

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

	public int getSmallNum()
	{
		return SmallNum;
	}
	public void setSmallNum(int SmallNum)
	{
		this.SmallNum = SmallNum;
	}

	public int getMediumNum()
	{
		return MediumNum;
	}
	public void setMediumNum(int MediumNum)
	{
		this.MediumNum = MediumNum;
	}

	public int getLargeNum()
	{
		return LargeNum;
	}
	public void setLargeNum(int LargeNum)
	{
		this.LargeNum = LargeNum;
	}

	public int getSuperNum()
	{
		return SuperNum;
	}
	public void setSuperNum(int SuperNum)
	{
		this.SuperNum = SuperNum;
	}

	public int getSuperSmallNum()
	{
		return SuperSmallNum;
	}
	public void setSuperSmallNum(int SuperSmallNum)
	{
		this.SuperSmallNum = SuperSmallNum;
	}

	public int getFreshNum()
	{
		return FreshNum;
	}
	public void setFreshNum(int FreshNum)
	{
		this.FreshNum = FreshNum;
	}

	public int getUsedBoxNum()
	{
		return UsedBoxNum;
	}
	public void setUsedBoxNum(int UsedBoxNum)
	{
		this.UsedBoxNum = UsedBoxNum;
	}

	public int getUsedSmallNum()
	{
		return UsedSmallNum;
	}
	public void setUsedSmallNum(int UsedSmallNum)
	{
		this.UsedSmallNum = UsedSmallNum;
	}

	public int getUsedMediumNum()
	{
		return UsedMediumNum;
	}
	public void setUsedMediumNum(int UsedMediumNum)
	{
		this.UsedMediumNum = UsedMediumNum;
	}

	public int getUsedLargeNum()
	{
		return UsedLargeNum;
	}
	public void setUsedLargeNum(int UsedLargeNum)
	{
		this.UsedLargeNum = UsedLargeNum;
	}

	public int getUsedSuperNum()
	{
		return UsedSuperNum;
	}
	public void setUsedSuperNum(int UsedSuperNum)
	{
		this.UsedSuperNum = UsedSuperNum;
	}

	public int getUsedSuperSmallNum()
	{
		return UsedSuperSmallNum;
	}
	public void setUsedSuperSmallNum(int UsedSuperSmallNum)
	{
		this.UsedSuperSmallNum = UsedSuperSmallNum;
	}

	public int getUsedFreshNum()
	{
		return UsedFreshNum;
	}
	public void setUsedFreshNum(int UsedFreshNum)
	{
		this.UsedFreshNum = UsedFreshNum;
	}

	public double getUsedBoxFreq()
	{
		return UsedBoxFreq;
	}
	public void setUsedBoxFreq(double UsedBoxFreq)
	{
		this.UsedBoxFreq = UsedBoxFreq;
	}

	public double getUsedSmallFreq()
	{
		return UsedSmallFreq;
	}
	public void setUsedSmallFreq(double UsedSmallFreq)
	{
		this.UsedSmallFreq = UsedSmallFreq;
	}

	public double getUsedMediumFreq()
	{
		return UsedMediumFreq;
	}
	public void setUsedMediumFreq(double UsedMediumFreq)
	{
		this.UsedMediumFreq = UsedMediumFreq;
	}

	public double getUsedLargeFreq()
	{
		return UsedLargeFreq;
	}
	public void setUsedLargeFreq(double UsedLargeFreq)
	{
		this.UsedLargeFreq = UsedLargeFreq;
	}

	public double getUsedSuperFreq()
	{
		return UsedSuperFreq;
	}
	public void setUsedSuperFreq(double UsedSuperFreq)
	{
		this.UsedSuperFreq = UsedSuperFreq;
	}

	public double getUsedSuperSmallFreq()
	{
		return UsedSuperSmallFreq;
	}
	public void setUsedSuperSmallFreq(double UsedSuperSmallFreq)
	{
		this.UsedSuperSmallFreq = UsedSuperSmallFreq;
	}

	public double getUsedFreshFreq()
	{
		return UsedFreshFreq;
	}
	public void setUsedFreshFreq(double UsedFreshFreq)
	{
		this.UsedFreshFreq = UsedFreshFreq;
	}

}