package com.dcdzsoft.dto.business;

/**
* 角色特殊权限设置增加
*/

public class InParamOPRoleSpeRightAdd implements java.io.Serializable
{
	public String FunctionID = "133011"; //功能编号

	public String OperID = ""; //管理员编号
	public int RoleID; //角色编号
	public String SpePrivID = ""; //特殊权限号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "133011";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "133011";
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

	public String getSpePrivID()
	{
		return SpePrivID;
	}
	public void setSpePrivID(String SpePrivID)
	{
		this.SpePrivID = SpePrivID;
	}

}