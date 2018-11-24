package com.dcdzsoft.dto.business;

/**
* 投递员柜体权限查询
*/

public class InParamPMManTerminalRightQry implements java.io.Serializable
{
	public String FunctionID = "311104"; //功能编号

	public String OperID = ""; //管理员编号
	public String PostmanID = ""; //投递员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "311104";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "311104";
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

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

}