package com.dcdzsoft.dto.business;

/**
* 管理员日志查询
*/

public class InParamOPOperLogQry implements java.io.Serializable
{
	public String FunctionID = "132061"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //管理员编号
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期
	public String UserID = ""; //管理员编号
	public int OperType; //管理员类型
	public String OperName = ""; //管理员姓名
	public String FactFunctionID = ""; //功能编号
	public String TerminalNo = ""; //设备号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132061";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132061";
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

	public String getUserID()
	{
		return UserID;
	}
	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}

	public int getOperType()
	{
		return OperType;
	}
	public void setOperType(int OperType)
	{
		this.OperType = OperType;
	}

	public String getOperName()
	{
		return OperName;
	}
	public void setOperName(String OperName)
	{
		this.OperName = OperName;
	}

	public String getFactFunctionID()
	{
		return FactFunctionID;
	}
	public void setFactFunctionID(String FactFunctionID)
	{
		this.FactFunctionID = FactFunctionID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

}