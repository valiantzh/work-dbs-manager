package com.dcdzsoft.dto.business;

/**
* 投递记录查询
*/

public class OutParamPTDeliveryRecordQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String BoxNo = ""; //箱门编号
	public String PackageID = ""; //订单号
	public String PostmanID = ""; //投递员编号
	public String CompanyID = ""; //投递公司编号
	public String CompanyName = ""; //投递公司名称
	public java.sql.Timestamp StoredTime; //存物时间
	public java.sql.Date StoredDate = null; //存物时间
	public java.sql.Timestamp TakedTime; //取物时间
	public java.sql.Timestamp ExpiredTime; //逾期时间
	public String CustomerMobile = ""; //取件人手机
	public String PackageStatus = ""; //包裹状态
	public String PackageStatusName = ""; //包裹状态名称
	public String UploadFlag = ""; //上传标志
	public String UploadFlagName = ""; //上传标志名称
	public java.sql.Timestamp LastModifyTime; //最后修改时间

	public java.sql.Date getStoredDate() {
		return StoredDate;
	}
	public void setStoredDate(java.sql.Date storedDate) {
		StoredDate = storedDate;
	}
	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getCompanyName()
	{
		return CompanyName;
	}
	public void setCompanyName(String CompanyName)
	{
		this.CompanyName = CompanyName;
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

	public java.sql.Timestamp getExpiredTime()
	{
		return ExpiredTime;
	}
	public void setExpiredTime(java.sql.Timestamp ExpiredTime)
	{
		this.ExpiredTime = ExpiredTime;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
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

	public String getUploadFlag()
	{
		return UploadFlag;
	}
	public void setUploadFlag(String UploadFlag)
	{
		this.UploadFlag = UploadFlag;
	}

	public String getUploadFlagName()
	{
		return UploadFlagName;
	}
	public void setUploadFlagName(String UploadFlagName)
	{
		this.UploadFlagName = UploadFlagName;
	}

	public java.sql.Timestamp getLastModifyTime()
	{
		return LastModifyTime;
	}
	public void setLastModifyTime(java.sql.Timestamp LastModifyTime)
	{
		this.LastModifyTime = LastModifyTime;
	}

}