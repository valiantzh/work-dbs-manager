package com.dcdzsoft.dto.business;

/**
* 同步投递信息
*/

public class OutParamSCSyncDeliveryInfo implements java.io.Serializable
{
	public String packageId = ""; //订单号
	public String terminalNo = ""; //设备号
	public String terminalName = ""; //设备名称
	public String location = ""; //安装地址
	public java.sql.Timestamp storedTime; //存物时间
	public String boxNo = ""; //箱门编号
	public String postManId = ""; //投递员编号
	public String postManName = ""; //快递员姓名
	public String companyNo = ""; //投递公司编号
	public String companyName = ""; //快递公司名称
	public java.sql.Timestamp pickupTime; //取物时间
	public String customerMobile = ""; //取件人手机
	public String customerName = ""; //收件人姓名
	public String packageStatus = ""; //包裹状态
	public java.sql.Timestamp updateTime; //最后修改时间
	public String tradeWaterNo = ""; //流水号
	public String manufacturer = ""; //厂商标识

	public String getpackageId()
	{
		return packageId;
	}
	public void setpackageId(String packageId)
	{
		this.packageId = packageId;
	}

	public String getterminalNo()
	{
		return terminalNo;
	}
	public void setterminalNo(String terminalNo)
	{
		this.terminalNo = terminalNo;
	}

	public String getterminalName()
	{
		return terminalName;
	}
	public void setterminalName(String terminalName)
	{
		this.terminalName = terminalName;
	}

	public String getlocation()
	{
		return location;
	}
	public void setlocation(String location)
	{
		this.location = location;
	}

	public java.sql.Timestamp getstoredTime()
	{
		return storedTime;
	}
	public void setstoredTime(java.sql.Timestamp storedTime)
	{
		this.storedTime = storedTime;
	}

	public String getboxNo()
	{
		return boxNo;
	}
	public void setboxNo(String boxNo)
	{
		this.boxNo = boxNo;
	}

	public String getpostManId()
	{
		return postManId;
	}
	public void setpostManId(String postManId)
	{
		this.postManId = postManId;
	}

	public String getpostManName()
	{
		return postManName;
	}
	public void setpostManName(String postManName)
	{
		this.postManName = postManName;
	}

	public String getcompanyNo()
	{
		return companyNo;
	}
	public void setcompanyNo(String companyNo)
	{
		this.companyNo = companyNo;
	}

	public String getcompanyName()
	{
		return companyName;
	}
	public void setcompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public java.sql.Timestamp getpickupTime()
	{
		return pickupTime;
	}
	public void setpickupTime(java.sql.Timestamp pickupTime)
	{
		this.pickupTime = pickupTime;
	}

	public String getcustomerMobile()
	{
		return customerMobile;
	}
	public void setcustomerMobile(String customerMobile)
	{
		this.customerMobile = customerMobile;
	}

	public String getcustomerName()
	{
		return customerName;
	}
	public void setcustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getpackageStatus()
	{
		return packageStatus;
	}
	public void setpackageStatus(String packageStatus)
	{
		this.packageStatus = packageStatus;
	}

	public java.sql.Timestamp getupdateTime()
	{
		return updateTime;
	}
	public void setupdateTime(java.sql.Timestamp updateTime)
	{
		this.updateTime = updateTime;
	}

	public String gettradeWaterNo()
	{
		return tradeWaterNo;
	}
	public void settradeWaterNo(String tradeWaterNo)
	{
		this.tradeWaterNo = tradeWaterNo;
	}

	public String getmanufacturer()
	{
		return manufacturer;
	}
	public void setmanufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}

}