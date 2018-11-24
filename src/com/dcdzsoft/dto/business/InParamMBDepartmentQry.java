package com.dcdzsoft.dto.business;

/**
* 查询运营网点信息
*/

public class InParamMBDepartmentQry implements java.io.Serializable
{
	public String FunctionID = "150004"; //功能编号

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String ParentDepartID = ""; //父级运营网点编号
	public int DepartLevel; //运营网点级别
	public String Province = ""; //所属省直辖市
	public String City = ""; //所属市
	public String Area = ""; //所属县或地区

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150004";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150004";
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

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
	}

	public String getParentDepartID()
	{
		return ParentDepartID;
	}
	public void setParentDepartID(String ParentDepartID)
	{
		this.ParentDepartID = ParentDepartID;
	}

	public int getDepartLevel()
	{
		return DepartLevel;
	}
	public void setDepartLevel(int DepartLevel)
	{
		this.DepartLevel = DepartLevel;
	}

	public String getProvince()
	{
		return Province;
	}
	public void setProvince(String Province)
	{
		this.Province = Province;
	}

	public String getCity()
	{
		return City;
	}
	public void setCity(String City)
	{
		this.City = City;
	}

	public String getArea()
	{
		return Area;
	}
	public void setArea(String Area)
	{
		this.Area = Area;
	}

}