package com.dcdzsoft.dto.business;

/**
* 管理员登陆
*/

public class OutParamOPOperLogin implements java.io.Serializable
{
	public String OperID = ""; //管理员编号
	public String MenuID = ""; //菜单号
	public String FunctionID = ""; //功能编号
	public String MenuName = ""; //菜单中文名称
	public int MenuLevel; //菜单级别
	public String MenuEngName = ""; //菜单英文文名称
	public String ShortName = ""; //菜单快捷方式
	public String Description = ""; //菜单说明
	public String Action = ""; //相应的操作
	public int HotKey; //热键
	public String Icon = ""; //图标
	public String HelpContext = ""; //帮助文件
	public int MenuType; //菜单类别
	public int LeafFlag; //叶结点标志
	public String Remark = ""; //备注

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

	public String getFunctionID()
	{
		return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		this.FunctionID = FunctionID;
	}

	public String getMenuName()
	{
		return MenuName;
	}
	public void setMenuName(String MenuName)
	{
		this.MenuName = MenuName;
	}

	public int getMenuLevel()
	{
		return MenuLevel;
	}
	public void setMenuLevel(int MenuLevel)
	{
		this.MenuLevel = MenuLevel;
	}

	public String getMenuEngName()
	{
		return MenuEngName;
	}
	public void setMenuEngName(String MenuEngName)
	{
		this.MenuEngName = MenuEngName;
	}

	public String getShortName()
	{
		return ShortName;
	}
	public void setShortName(String ShortName)
	{
		this.ShortName = ShortName;
	}

	public String getDescription()
	{
		return Description;
	}
	public void setDescription(String Description)
	{
		this.Description = Description;
	}

	public String getAction()
	{
		return Action;
	}
	public void setAction(String Action)
	{
		this.Action = Action;
	}

	public int getHotKey()
	{
		return HotKey;
	}
	public void setHotKey(int HotKey)
	{
		this.HotKey = HotKey;
	}

	public String getIcon()
	{
		return Icon;
	}
	public void setIcon(String Icon)
	{
		this.Icon = Icon;
	}

	public String getHelpContext()
	{
		return HelpContext;
	}
	public void setHelpContext(String HelpContext)
	{
		this.HelpContext = HelpContext;
	}

	public int getMenuType()
	{
		return MenuType;
	}
	public void setMenuType(int MenuType)
	{
		this.MenuType = MenuType;
	}

	public int getLeafFlag()
	{
		return LeafFlag;
	}
	public void setLeafFlag(int LeafFlag)
	{
		this.LeafFlag = LeafFlag;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}