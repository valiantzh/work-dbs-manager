package com.dcdzsoft.dto.business;

/**
* 柜体使用情况统计(按月)
*/

public class OutParamQYTerminalUsageMonthly implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String Location = ""; //安装地址
	public int BoxNum; //格口数量
	public int OccurYear; //发生年份
	public int OccurMonth; //发生月份
	public int Total; //总计
	public int JanCnt; //1月
	public int MarCnt; //2月
	public int FebCnt; //3月
	public int AprCnt; //4月
	public int MayCnt; //5月
	public int JunCnt; //6月
	public int JulCnt; //7月
	public int AugCnt; //8月
	public int SepCnt; //9月
	public int OctCnt; //10月
	public int NovCnt; //11月
	public int DecCnt; //12月
	public int DailyUsage; //日均使用率

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

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
	}

	public int getBoxNum()
	{
		return BoxNum;
	}
	public void setBoxNum(int BoxNum)
	{
		this.BoxNum = BoxNum;
	}

	public int getOccurYear()
	{
		return OccurYear;
	}
	public void setOccurYear(int OccurYear)
	{
		this.OccurYear = OccurYear;
	}

	public int getOccurMonth()
	{
		return OccurMonth;
	}
	public void setOccurMonth(int OccurMonth)
	{
		this.OccurMonth = OccurMonth;
	}

	public int getTotal()
	{
		return Total;
	}
	public void setTotal(int Total)
	{
		this.Total = Total;
	}

	public int getJanCnt()
	{
		return JanCnt;
	}
	public void setJanCnt(int JanCnt)
	{
		this.JanCnt = JanCnt;
	}

	public int getMarCnt()
	{
		return MarCnt;
	}
	public void setMarCnt(int MarCnt)
	{
		this.MarCnt = MarCnt;
	}

	public int getFebCnt()
	{
		return FebCnt;
	}
	public void setFebCnt(int FebCnt)
	{
		this.FebCnt = FebCnt;
	}

	public int getAprCnt()
	{
		return AprCnt;
	}
	public void setAprCnt(int AprCnt)
	{
		this.AprCnt = AprCnt;
	}

	public int getMayCnt()
	{
		return MayCnt;
	}
	public void setMayCnt(int MayCnt)
	{
		this.MayCnt = MayCnt;
	}

	public int getJunCnt()
	{
		return JunCnt;
	}
	public void setJunCnt(int JunCnt)
	{
		this.JunCnt = JunCnt;
	}

	public int getJulCnt()
	{
		return JulCnt;
	}
	public void setJulCnt(int JulCnt)
	{
		this.JulCnt = JulCnt;
	}

	public int getAugCnt()
	{
		return AugCnt;
	}
	public void setAugCnt(int AugCnt)
	{
		this.AugCnt = AugCnt;
	}

	public int getSepCnt()
	{
		return SepCnt;
	}
	public void setSepCnt(int SepCnt)
	{
		this.SepCnt = SepCnt;
	}

	public int getOctCnt()
	{
		return OctCnt;
	}
	public void setOctCnt(int OctCnt)
	{
		this.OctCnt = OctCnt;
	}

	public int getNovCnt()
	{
		return NovCnt;
	}
	public void setNovCnt(int NovCnt)
	{
		this.NovCnt = NovCnt;
	}

	public int getDecCnt()
	{
		return DecCnt;
	}
	public void setDecCnt(int DecCnt)
	{
		this.DecCnt = DecCnt;
	}

	public int getDailyUsage()
	{
		return DailyUsage;
	}
	public void setDailyUsage(int DailyUsage)
	{
		this.DailyUsage = DailyUsage;
	}

}