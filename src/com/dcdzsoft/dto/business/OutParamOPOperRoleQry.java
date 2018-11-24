package com.dcdzsoft.dto.business;

/**
* 管理员角色查询
*/

public class OutParamOPOperRoleQry implements java.io.Serializable
{
	public String OperID = ""; //管理员编号
	public int RoleID; //角色编号
	public String RoleName = ""; //角色名称

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

	public String getRoleName()
	{
		return RoleName;
	}
	public void setRoleName(String RoleName)
	{
		this.RoleName = RoleName;
	}

}