package com.dcdzsoft.dto.business;

/**
* 管理员在线信息查询
*/

public class InParamOPOperOnlineQry implements java.io.Serializable
{
	public String FunctionID = "132065"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //管理员编号
	public String UserID = ""; //管理员编号
	public String OperName = ""; //管理员姓名
	public int OperType; //管理员类型
	public String DepartmentID = ""; //运营网点编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132065";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132065";
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

	public String getUserID()
	{
		return UserID;
	}
	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}

	public String getOperName()
	{
		return OperName;
	}
	public void setOperName(String OperName)
	{
		this.OperName = OperName;
	}

	public int getOperType()
	{
		return OperType;
	}
	public void setOperType(int OperType)
	{
		this.OperType = OperType;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

}