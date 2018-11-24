package com.dcdzsoft.dto.business;

/**
* 短信使用情况统计
*/

public class OutParamQYStat4SMS implements java.io.Serializable
{
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public int OccurYear; //发生年份
	public int OccurMonth; //发生月份
	public int TotalNum; //短信总数量
	public int PwdNum; //取件密码短信
	public int ExpireNum; //逾期取回短信
	public int ReminderNum; //催领短信

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
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

	public int getTotalNum()
	{
		return TotalNum;
	}
	public void setTotalNum(int TotalNum)
	{
		this.TotalNum = TotalNum;
	}

	public int getPwdNum()
	{
		return PwdNum;
	}
	public void setPwdNum(int PwdNum)
	{
		this.PwdNum = PwdNum;
	}

	public int getExpireNum()
	{
		return ExpireNum;
	}
	public void setExpireNum(int ExpireNum)
	{
		this.ExpireNum = ExpireNum;
	}

	public int getReminderNum()
	{
		return ReminderNum;
	}
	public void setReminderNum(int ReminderNum)
	{
		this.ReminderNum = ReminderNum;
	}

}