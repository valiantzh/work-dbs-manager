package com.dcdzsoft.dto.business;

/**
* 角色菜单信息增加
*/

public class InParamOPRoleToMenuAdd implements java.io.Serializable
{
	public String FunctionID = "130011"; //功能编号

	public String OperID = ""; //管理员编号
	public String MenuID = ""; //菜单号
	public int RoleID; //角色编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "130011";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "130011";
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

	public String getMenuID()
	{
		return MenuID;
	}
	public void setMenuID(String MenuID)
	{
		this.MenuID = MenuID;
	}

	public int getRoleID()
	{
		return RoleID;
	}
	public void setRoleID(int RoleID)
	{
		this.RoleID = RoleID;
	}

}