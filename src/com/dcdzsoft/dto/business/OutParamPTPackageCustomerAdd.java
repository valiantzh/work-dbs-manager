package com.dcdzsoft.dto.business;

/**
* 投递订单添加用户信息
*/

public class OutParamPTPackageCustomerAdd implements java.io.Serializable
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