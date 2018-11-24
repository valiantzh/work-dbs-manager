package com.dcdzsoft.dto.business;

/**
* 查询短信服务套餐列表
*/

public class InParamPYSMSServicesListQry implements java.io.Serializable
{
	public String FunctionID = "410006"; //功能编号

	public String OperID = ""; //管理员编号
	public String ServiceID = ""; //服务编号
	public String Ways = ""; //计费方式
	public String Active = ""; //激活标识

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410006";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410006";
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

	public String getWays()
	{
		return Ways;
	}
	public void setWays(String Ways)
	{
		this.Ways = Ways;
	}

	public String getActive()
	{
		return Active;
	}
	public void setActive(String Active)
	{
		this.Active = Active;
	}

}