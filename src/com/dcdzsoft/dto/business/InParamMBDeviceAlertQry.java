package com.dcdzsoft.dto.business;

/**
* 设备警报信息查询
*/

public class InParamMBDeviceAlertQry implements java.io.Serializable
{
	public String FunctionID = "150314"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String AlertType = ""; //报警种类
	public String AlertLevel = ""; //报警等级
	public java.sql.Timestamp LogTime; //报告时间

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150314";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150314";
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

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
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

	public String getAlertType()
	{
		return AlertType;
	}
	public void setAlertType(String AlertType)
	{
		this.AlertType = AlertType;
	}

	public String getAlertLevel()
	{
		return AlertLevel;
	}
	public void setAlertLevel(String AlertLevel)
	{
		this.AlertLevel = AlertLevel;
	}

	public java.sql.Timestamp getLogTime()
	{
		return LogTime;
	}
	public void setLogTime(java.sql.Timestamp LogTime)
	{
		this.LogTime = LogTime;
	}

}