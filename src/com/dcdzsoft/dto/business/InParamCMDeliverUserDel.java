package com.dcdzsoft.dto.business;

/**
* 投递服务用户删除
*/

public class InParamCMDeliverUserDel implements java.io.Serializable
{
	public String FunctionID = "170203"; //功能编号

	public String OperID = ""; //操作员编号
	public String CustomerID = ""; //用户编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "170203";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "170203";
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

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

}