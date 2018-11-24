package com.dcdzsoft.dto.business;

/**
* 查询短信服务账号数量
*/

public class InParamPYSMSAccountQryCount implements java.io.Serializable
{
	public String FunctionID = "410025"; //功能编号

	public String OperID = ""; //管理员编号
	public String AccountID = ""; //短信充值账户编号
	public String AccountName = ""; //短信充值账户名称
	public String AccountStatus = ""; //账户状态
	public String DepartmentID = ""; //运营网点编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410025";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410025";
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

	public String getAccountID()
	{
		return AccountID;
	}
	public void setAccountID(String AccountID)
	{
		this.AccountID = AccountID;
	}

	public String getAccountName()
	{
		return AccountName;
	}
	public void setAccountName(String AccountName)
	{
		this.AccountName = AccountName;
	}

	public String getAccountStatus()
	{
		return AccountStatus;
	}
	public void setAccountStatus(String AccountStatus)
	{
		this.AccountStatus = AccountStatus;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

}