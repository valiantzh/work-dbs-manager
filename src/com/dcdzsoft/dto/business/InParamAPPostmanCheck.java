package com.dcdzsoft.dto.business;

/**
* 投递员身份验证
*/

public class InParamAPPostmanCheck implements java.io.Serializable
{
	public String FunctionID = "650001"; //功能编号

	public String PostmanID = ""; //投递员编号
	public String Password = ""; //投递员密码

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650001";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650001";
		else
			this.FunctionID = FunctionID;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getPassword()
	{
		return Password;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}

}