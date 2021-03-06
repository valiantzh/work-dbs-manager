package com.dcdzsoft.dto.business;

/**
* 黑名单查询数量
*/

public class InParamMBMobileBlackListQryCount implements java.io.Serializable
{
	public String FunctionID = "150205"; //功能编号

	public String OperID = ""; //管理员编号
	public String CustomerMobile = ""; //取件人手机

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150205";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150205";
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

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

}