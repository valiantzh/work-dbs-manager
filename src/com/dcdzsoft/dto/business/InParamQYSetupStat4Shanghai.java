package com.dcdzsoft.dto.business;

/**
* 安装布放情况统计
*/

public class InParamQYSetupStat4Shanghai implements java.io.Serializable
{
	public String FunctionID = "350054"; //功能编号

	public String OperID = ""; //管理员编号
	public String ParentDepartID = ""; //二级运营网点
	public String DepartmentID = ""; //运营网点编号
	public String LocationType = ""; //安装地址类型
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "350054";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "350054";
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

	public String getParentDepartID()
	{
		return ParentDepartID;
	}
	public void setParentDepartID(String ParentDepartID)
	{
		this.ParentDepartID = ParentDepartID;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getLocationType()
	{
		return LocationType;
	}
	public void setLocationType(String LocationType)
	{
		this.LocationType = LocationType;
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