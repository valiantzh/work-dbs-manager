package com.dcdzsoft.dto.business;

/**
* 短信使用情况统计
*/

public class InParamQYStat4SMS implements java.io.Serializable
{
	public String FunctionID = "350034"; //功能编号

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public int OccurYear; //发生年份
	public int OccurMonth; //发生月份

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "350034";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "350034";
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

}