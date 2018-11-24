package com.dcdzsoft.dto.business;

/**
* 投递公司柜体权限查询
*/

public class InParamPMCorpTerminalRightQry implements java.io.Serializable
{
	public String FunctionID = "310104"; //功能编号

	public String OperID = ""; //管理员编号
	public String CompanyID = ""; //投递公司编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "310104";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "310104";
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

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

}