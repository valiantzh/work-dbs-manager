package com.dcdzsoft.dto.business;

/**
* 查询短信服务账号列表
*/

public class OutParamPYSMSAccountListQry implements java.io.Serializable
{
	public String AccountID = ""; //充值账户编号
	public String AccountName = ""; //充值账户名称

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

}