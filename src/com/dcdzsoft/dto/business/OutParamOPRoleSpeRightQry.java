package com.dcdzsoft.dto.business;

/**
* 角色特殊权限设置查询
*/

public class OutParamOPRoleSpeRightQry implements java.io.Serializable
{
	public int RoleID; //角色编号
	public int SpePrivID; //特殊权限号
	public String RoleName = ""; //角色名称
	public String SpePrivName = ""; //特殊权限名称

	public int getRoleID()
	{
		return RoleID;
	}
	public void setRoleID(int RoleID)
	{
		this.RoleID = RoleID;
	}

	public int getSpePrivID()
	{
		return SpePrivID;
	}
	public void setSpePrivID(int SpePrivID)
	{
		this.SpePrivID = SpePrivID;
	}

	public String getRoleName()
	{
		return RoleName;
	}
	public void setRoleName(String RoleName)
	{
		this.RoleName = RoleName;
	}

	public String getSpePrivName()
	{
		return SpePrivName;
	}
	public void setSpePrivName(String SpePrivName)
	{
		this.SpePrivName = SpePrivName;
	}

}