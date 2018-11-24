package com.dcdzsoft.dto.business;

/**
* 管理员菜单信息查询
*/

public class InParamOPOperToMenuQry implements java.io.Serializable
{
	public String FunctionID = "132014"; //功能编号

	public String OperID = ""; //管理员编号
	public String ByOperID = ""; //被操作的管理员编号
	public String ModuleID = ""; //模块编号
	public String MenuID = ""; //菜单编号
	public int MenuLevel; //菜单级别

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132014";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132014";
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

	public String getByOperID()
	{
		return ByOperID;
	}
	public void setByOperID(String ByOperID)
	{
		this.ByOperID = ByOperID;
	}

	public String getModuleID()
	{
		return ModuleID;
	}
	public void setModuleID(String ModuleID)
	{
		this.ModuleID = ModuleID;
	}

	public String getMenuID()
	{
		return MenuID;
	}
	public void setMenuID(String MenuID)
	{
		this.MenuID = MenuID;
	}

	public int getMenuLevel()
	{
		return MenuLevel;
	}
	public void setMenuLevel(int MenuLevel)
	{
		this.MenuLevel = MenuLevel;
	}

}