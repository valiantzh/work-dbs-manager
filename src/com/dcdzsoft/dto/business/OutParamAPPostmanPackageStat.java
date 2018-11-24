package com.dcdzsoft.dto.business;

/**
* 投递员包裹统计
*/

public class OutParamAPPostmanPackageStat implements java.io.Serializable
{
	public String PackageStatus = ""; //包裹状态
	public int PackageNum; //包裹数量

	public String getPackageStatus()
	{
		return PackageStatus;
	}
	public void setPackageStatus(String PackageStatus)
	{
		this.PackageStatus = PackageStatus;
	}

	public int getPackageNum()
	{
		return PackageNum;
	}
	public void setPackageNum(int PackageNum)
	{
		this.PackageNum = PackageNum;
	}

}