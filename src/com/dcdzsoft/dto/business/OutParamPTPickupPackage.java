package com.dcdzsoft.dto.business;

/**
* 用户取件
*/

public class OutParamPTPickupPackage implements java.io.Serializable
{
	public java.sql.Timestamp ServerTime; //服务器时间

	public java.sql.Timestamp getServerTime()
	{
		return ServerTime;
	}
	public void setServerTime(java.sql.Timestamp ServerTime)
	{
		this.ServerTime = ServerTime;
	}

}