package com.dcdzsoft.dto.business;

/**
* 投递员修改密码
*/

public class InParamAPPostmanModPwd implements java.io.Serializable
{
	public String FunctionID = "650003"; //功能编号

	public String Mobile = ""; //手机号码
	public String NewPassword = ""; //新密码
	public String OldPassword = ""; //旧密码

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650003";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650003";
		else
			this.FunctionID = FunctionID;
	}

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
	}

	public String getNewPassword()
	{
		return NewPassword;
	}
	public void setNewPassword(String NewPassword)
	{
		this.NewPassword = NewPassword;
	}

	public String getOldPassword()
	{
		return OldPassword;
	}
	public void setOldPassword(String OldPassword)
	{
		this.OldPassword = OldPassword;
	}

}