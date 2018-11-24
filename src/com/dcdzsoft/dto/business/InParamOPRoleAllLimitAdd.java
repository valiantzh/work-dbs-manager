package com.dcdzsoft.dto.business;

/**
* 角色总体限制权限增加
*/

public class InParamOPRoleAllLimitAdd implements java.io.Serializable
{
	public String FunctionID = "130016"; //功能编号

	public String OperID = ""; //管理员编号
	public int RoleID; //角色编号
	public int LimitTypeID; //限制类别编号
	public String LimitObject = ""; //限制的对象
	public String LimitObjectName = ""; //限制的对象名称

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "130016";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "130016";
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

	public int getRoleID()
	{
		return RoleID;
	}
	public void setRoleID(int RoleID)
	{
		this.RoleID = RoleID;
	}

	public int getLimitTypeID()
	{
		return LimitTypeID;
	}
	public void setLimitTypeID(int LimitTypeID)
	{
		this.LimitTypeID = LimitTypeID;
	}

	public String getLimitObject()
	{
		return LimitObject;
	}
	public void setLimitObject(String LimitObject)
	{
		this.LimitObject = LimitObject;
	}

	public String getLimitObjectName()
	{
		return LimitObjectName;
	}
	public void setLimitObjectName(String LimitObjectName)
	{
		this.LimitObjectName = LimitObjectName;
	}

}