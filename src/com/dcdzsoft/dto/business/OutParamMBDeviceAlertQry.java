package com.dcdzsoft.dto.business;

/**
* 设备警报信息查询
*/

public class OutParamMBDeviceAlertQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String Location = ""; //安装地址
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public long ReportWaterID; //报告流水号
	public String AlertType = ""; //报警种类
	public String AlertTypeName = ""; //报警种类名称
	public String AlertLevel = ""; //报警等级
	public String AlertLevelName = ""; //报警等级名称
	public String AlertContent = ""; //报警内容
	public String BoxNo = ""; //箱门编号
	public java.sql.Date LogDate; //报告日期
	public java.sql.Timestamp LogTime; //报告时间
	public String Remark = ""; //备注

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

	public long getReportWaterID()
	{
		return ReportWaterID;
	}
	public void setReportWaterID(long ReportWaterID)
	{
		this.ReportWaterID = ReportWaterID;
	}

	public String getAlertType()
	{
		return AlertType;
	}
	public void setAlertType(String AlertType)
	{
		this.AlertType = AlertType;
	}

	public String getAlertTypeName()
	{
		return AlertTypeName;
	}
	public void setAlertTypeName(String AlertTypeName)
	{
		this.AlertTypeName = AlertTypeName;
	}

	public String getAlertLevel()
	{
		return AlertLevel;
	}
	public void setAlertLevel(String AlertLevel)
	{
		this.AlertLevel = AlertLevel;
	}

	public String getAlertLevelName()
	{
		return AlertLevelName;
	}
	public void setAlertLevelName(String AlertLevelName)
	{
		this.AlertLevelName = AlertLevelName;
	}

	public String getAlertContent()
	{
		return AlertContent;
	}
	public void setAlertContent(String AlertContent)
	{
		this.AlertContent = AlertContent;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public java.sql.Date getLogDate()
	{
		return LogDate;
	}
	public void setLogDate(java.sql.Date LogDate)
	{
		this.LogDate = LogDate;
	}

	public java.sql.Timestamp getLogTime()
	{
		return LogTime;
	}
	public void setLogTime(java.sql.Timestamp LogTime)
	{
		this.LogTime = LogTime;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}