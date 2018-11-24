package com.dcdzsoft.dto.business;

/**
* 角色增加
*/

public class InParamOPRoleAdd implements java.io.Serializable
{
	public String FunctionID = "130001"; //功能编号

	public String OperID = ""; //管理员编号
	public String RoleName = ""; //角色名称

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "130001";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "130001";
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

	public String getRoleName()
	{
		return RoleName;
	}
	public void setRoleName(String RoleName)
	{
		this.RoleName = RoleName;
	}

}