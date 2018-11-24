package com.dcdzsoft.dto.business;

/**
* 订单在箱时间统计
*/

public class OutParamQYOrderInboxTimeStat implements java.io.Serializable
{
	public String PackageID = ""; //订单号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String CompanyID = ""; //投递公司编号
	public String CompanyName = ""; //投递公司名称
	public String CustomerMobile = ""; //取件人手机
	public String BoxNo = ""; //箱门编号
	public java.sql.Timestamp StoredTime; //存物时间
	public java.sql.Timestamp TakedTime; //取物时间
	public String PackageStatus = ""; //包裹状态
	public String PackageStatusName = ""; //包裹状态名称
	public int InBoxTime; //包裹在箱时间（小时）

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
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

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
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

	public int getInBoxTime()
	{
		return InBoxTime;
	}
	public void setInBoxTime(int InBoxTime)
	{
		this.InBoxTime = InBoxTime;
	}

}