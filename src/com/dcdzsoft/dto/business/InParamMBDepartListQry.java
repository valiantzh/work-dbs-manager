package com.dcdzsoft.dto.business;

/**
* 查询运营网点信息（列表结构返回）
*/

public class InParamMBDepartListQry implements java.io.Serializable
{
	public String FunctionID = "150006"; //功能编号

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public int DepartLevel; //运营网点级别
	public String ParentDepartID = ""; //父级运营网点编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150006";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150006";
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

	public int getDepartLevel()
	{
		return DepartLevel;
	}
	public void setDepartLevel(int DepartLevel)
	{
		this.DepartLevel = DepartLevel;
	}

	public String getParentDepartID()
	{
		return ParentDepartID;
	}
	public void setParentDepartID(String ParentDepartID)
	{
		this.ParentDepartID = ParentDepartID;
	}

}