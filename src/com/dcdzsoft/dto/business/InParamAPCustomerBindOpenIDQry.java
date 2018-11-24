package com.dcdzsoft.dto.business;

/**
* 绑定合作方用户信息查询
*/

public class InParamAPCustomerBindOpenIDQry implements java.io.Serializable
{
	public String FunctionID = "650444"; //功能编号

	public String CustomerID = ""; //用户编号
	public String BindMobile = ""; //绑定的手机号码

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650444";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650444";
		else
			this.FunctionID = FunctionID;
	}

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getBindMobile()
	{
		return BindMobile;
	}
	public void setBindMobile(String BindMobile)
	{
		this.BindMobile = BindMobile;
	}

}