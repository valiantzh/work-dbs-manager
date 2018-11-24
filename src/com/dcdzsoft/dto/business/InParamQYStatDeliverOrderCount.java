package com.dcdzsoft.dto.business;

/**
* 投递量统计数量
*/

public class InParamQYStatDeliverOrderCount implements java.io.Serializable
{
	public String FunctionID = "350095"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String LogisticsID = ""; //物流公司编号
	public String CompanyID = ""; //投递公司编号
	public String DepartmentID = ""; //运营网点编号
	public String PostmanID = ""; //投递员编号
	public String PostmanMobile = ""; //投递员手机
	public String MasterFlag = ""; //主运营标志
	public int OccurYear; //发生年份
	public int OccurMonth; //发生月份
	public String StatPeriod = ""; //统计范围
	public String StatFlag = ""; //统计查询标志
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "350095";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "350095";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
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

	public String getLogisticsID()
	{
		return LogisticsID;
	}
	public void setLogisticsID(String LogisticsID)
	{
		this.LogisticsID = LogisticsID;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getPostmanMobile()
	{
		return PostmanMobile;
	}
	public void setPostmanMobile(String PostmanMobile)
	{
		this.PostmanMobile = PostmanMobile;
	}

	public String getMasterFlag()
	{
		return MasterFlag;
	}
	public void setMasterFlag(String MasterFlag)
	{
		this.MasterFlag = MasterFlag;
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

	public String getStatPeriod()
	{
		return StatPeriod;
	}
	public void setStatPeriod(String StatPeriod)
	{
		this.StatPeriod = StatPeriod;
	}

	public String getStatFlag()
	{
		return StatFlag;
	}
	public void setStatFlag(String StatFlag)
	{
		this.StatFlag = StatFlag;
	}

	public java.sql.Date getBeginDate()
	{
		return BeginDate;
	}
	public void setBeginDate(java.sql.Date BeginDate)
	{
		this.BeginDate = BeginDate;
	}

	public java.sql.Date getEndDate()
	{
		return EndDate;
	}
	public void setEndDate(java.sql.Date EndDate)
	{
		this.EndDate = EndDate;
	}

}