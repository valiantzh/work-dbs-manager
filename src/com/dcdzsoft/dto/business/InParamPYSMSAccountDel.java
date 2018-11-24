package com.dcdzsoft.dto.business;

/**
* 删除运营网点信息
*/

public class InParamPYSMSAccountDel implements java.io.Serializable
{
	public String FunctionID = "410023"; //功能编号

	public String OperID = ""; //管理员编号
	public String AccountID = ""; //短信充值账户编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410023";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410023";
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

}