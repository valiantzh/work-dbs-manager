package com.dcdzsoft.dto.business;

/**
* 设备外设状态报告
*/

public class OutParamMBReportPeripheralStatus implements java.io.Serializable
{
	public java.sql.Timestamp ServerTime; //服务器时间
	public String SoftwareVersion = ""; //软件版本号

	public java.sql.Timestamp getServerTime()
	{
		return ServerTime;
	}
	public void setServerTime(java.sql.Timestamp ServerTime)
	{
		this.ServerTime = ServerTime;
	}

	public String getSoftwareVersion()
	{
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String SoftwareVersion)
	{
		this.SoftwareVersion = SoftwareVersion;
	}

}