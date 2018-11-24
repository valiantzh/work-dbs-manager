package com.dcdzsoft.dto.business;

/**
* 删除短信服务套餐信息
*/

public class InParamPYSMSServicesDel implements java.io.Serializable
{
	public String FunctionID = "410003"; //功能编号

	public String OperID = ""; //管理员编号
	public String ServiceID = ""; //服务编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410003";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410003";
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

	public String getServiceID()
	{
		return ServiceID;
	}
	public void setServiceID(String ServiceID)
	{
		this.ServiceID = ServiceID;
	}

}