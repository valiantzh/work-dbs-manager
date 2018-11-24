package com.dcdzsoft.dto.business;

/**
* 订单在箱时间统计
*/

public class InParamQYOrderInboxTimeStat implements java.io.Serializable
{
	public String FunctionID = "350074"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String DepartmentID = ""; //运营网点编号
	public String CompanyID = ""; //投递公司编号
	public String LogisticsID = ""; //物流公司编号
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期
	public String StatFlag = ""; //统计查询标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "350074";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "350074";
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

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getLogisticsID()
	{
		return LogisticsID;
	}
	public void setLogisticsID(String LogisticsID)
	{
		this.LogisticsID = LogisticsID;
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

	public String getStatFlag()
	{
		return StatFlag;
	}
	public void setStatFlag(String StatFlag)
	{
		this.StatFlag = StatFlag;
	}

}