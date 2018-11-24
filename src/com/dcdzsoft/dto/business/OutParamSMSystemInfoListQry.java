package com.dcdzsoft.dto.business;

/**
* 查询系统信息列表
*/

public class OutParamSMSystemInfoListQry implements java.io.Serializable
{
	public String SystemID = ""; //系统编号
	public String SystemName = ""; //系统名称

	public String getSystemID()
	{
		return SystemID;
	}
	public void setSystemID(String SystemID)
	{
		this.SystemID = SystemID;
	}

	public String getSystemName()
	{
		return SystemName;
	}
	public void setSystemName(String SystemName)
	{
		this.SystemName = SystemName;
	}

}