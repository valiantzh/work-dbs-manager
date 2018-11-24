package com.dcdzsoft.dto.business;

/**
* 同步服务器时间
*/

public class OutParamSCSyncServerTime implements java.io.Serializable
{
	public java.sql.Timestamp ServerTime; //服务器时间
	public String SignKey = ""; //签到生成密钥

	public java.sql.Timestamp getServerTime()
	{
		return ServerTime;
	}
	public void setServerTime(java.sql.Timestamp ServerTime)
	{
		this.ServerTime = ServerTime;
	}

	public String getSignKey()
	{
		return SignKey;
	}
	public void setSignKey(String SignKey)
	{
		this.SignKey = SignKey;
	}

}