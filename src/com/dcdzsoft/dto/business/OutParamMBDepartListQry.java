package com.dcdzsoft.dto.business;

/**
* 查询运营网点信息（列表结构返回）
*/

public class OutParamMBDepartListQry implements java.io.Serializable
{
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称

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

}