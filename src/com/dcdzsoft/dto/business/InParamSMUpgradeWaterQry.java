package com.dcdzsoft.dto.business;

/**
* 查询软件更新流水信息
*/

public class InParamSMUpgradeWaterQry implements java.io.Serializable
{
	public String FunctionID = "110524"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //操作员编号
	public String SoftwareID = ""; //软件编号
	public String SoftwareName = ""; //软件名称
	public String SoftwareType = ""; //软件类型：1-
	public String SystemID = ""; //软件编号
	public String TerminalNo = ""; //设备编号
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110524";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110524";
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

	public String getSoftwareID()
	{
		return SoftwareID;
	}
	public void setSoftwareID(String SoftwareID)
	{
		this.SoftwareID = SoftwareID;
	}

	public String getSoftwareName()
	{
		return SoftwareName;
	}
	public void setSoftwareName(String SoftwareName)
	{
		this.SoftwareName = SoftwareName;
	}

	public String getSoftwareType()
	{
		return SoftwareType;
	}
	public void setSoftwareType(String SoftwareType)
	{
		this.SoftwareType = SoftwareType;
	}

	public String getSystemID()
	{
		return SystemID;
	}
	public void setSystemID(String SystemID)
	{
		this.SystemID = SystemID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
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