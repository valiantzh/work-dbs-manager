package com.dcdzsoft.dto.business;

/**
* 角色查询
*/

public class OutParamOPRoleQry implements java.io.Serializable
{
	public int RoleID; //角色编号
	public String RoleName = ""; //角色名称

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