package com.dcdzsoft.dto.business;

/**
* 查询短信服务套餐列表
*/

public class OutParamPYSMSServicesListQry implements java.io.Serializable
{
	public String ServiceID = ""; //服务编号
	public String ServiceName = ""; //服务名称

	public String getServiceID()
	{
		return ServiceID;
	}
	public void setServiceID(String ServiceID)
	{
		this.ServiceID = ServiceID;
	}

	public String getServiceName()
	{
		return ServiceName;
	}
	public void setServiceName(String ServiceName)
	{
		this.ServiceName = ServiceName;
	}

}