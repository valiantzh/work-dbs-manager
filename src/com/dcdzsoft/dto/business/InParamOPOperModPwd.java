package com.dcdzsoft.dto.business;

/**
* 修改个人密码
*/

public class InParamOPOperModPwd implements java.io.Serializable
{
	public String FunctionID = "132053"; //功能编号

	public String OperID = ""; //操作员编号
	public String OperOldPassword = ""; //操作员旧密码
	public String OperNewPassword = ""; //操作员新密码

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "132053";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "132053";
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

	public String getOperOldPassword()
	{
		return OperOldPassword;
	}
	public void setOperOldPassword(String OperOldPassword)
	{
		this.OperOldPassword = OperOldPassword;
	}

	public String getOperNewPassword()
	{
		return OperNewPassword;
	}
	public void setOperNewPassword(String OperNewPassword)
	{
		this.OperNewPassword = OperNewPassword;
	}

}