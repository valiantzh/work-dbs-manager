package com.dcdzsoft.dto.business;

/**
* 管理员菜单信息增加
*/

public class InParamOPOperToMenuAdd implements java.io.Serializable
{
	public String FunctionID = "132011"; //功能编号

	public String OperID = ""; //管理员编号
	public String MenuID = ""; //菜单号
	public String ByOperID = ""; //被操作的管理员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132011";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132011";
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

	public String getByOperID()
	{
		return ByOperID;
	}
	public void setByOperID(String ByOperID)
	{
		this.ByOperID = ByOperID;
	}

}