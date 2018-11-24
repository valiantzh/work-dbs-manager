package com.dcdzsoft.dto.business;

/**
* 单个投递记录查询
*/

public class OutParamPTOneDeliveryRecordQry implements java.io.Serializable
{
	public String PackageID = ""; //订单号
	public java.sql.Timestamp StoredTime; //存物时间
	public java.sql.Timestamp TakedTime; //取物时间
	public String PackageStatus = ""; //包裹状态
	public String PackageStatusName = ""; //包裹状态名称

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public java.sql.Timestamp getStoredTime()
	{
		return StoredTime;
	}
	public void setStoredTime(java.sql.Timestamp StoredTime)
	{
		this.StoredTime = StoredTime;
	}

	public java.sql.Timestamp getTakedTime()
	{
		return TakedTime;
	}
	public void setTakedTime(java.sql.Timestamp TakedTime)
	{
		this.TakedTime = TakedTime;
	}

	public String getPackageStatus()
	{
		return PackageStatus;
	}
	public void setPackageStatus(String PackageStatus)
	{
		this.PackageStatus = PackageStatus;
	}

	public String getPackageStatusName()
	{
		return PackageStatusName;
	}
	public void setPackageStatusName(String PackageStatusName)
	{
		this.PackageStatusName = PackageStatusName;
	}

}