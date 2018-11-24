package com.dcdzsoft.dto.business;

/**
* 收件人未取包裹列表
*/

public class OutParamAPCustomerInboxPackage implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String Location = ""; //投递地址
	public String BoxNo = ""; //箱门编号
	public String PackageID = ""; //订单号
	public java.sql.Timestamp StoredTime; //存物时间
	public String TradeWaterNo = ""; //交易流水号
	public String OpenBoxKey = ""; //开箱密码
	public String OpenBoxKey2 = ""; //二维码字符串
	public String PostmanID = ""; //投递员编号
	public String PostmanName = ""; //投递员姓名
	public String CompanyName = ""; //快递公司名称
	public String PostmanMobile = ""; //投递员手机
	public double Longitude; //柜位置经度
	public double Latitude; //柜位置纬度
	public String CustomerMobile = ""; //取件人手机
	public String StaOrderID = ""; //电商运单号
	public String OfLogisticsID = ""; //运单所属物流公司
	public String OfLogisticsName = ""; //运单所属物流公司名称

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

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
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

	public java.sql.Timestamp getStoredTime()
	{
		return StoredTime;
	}
	public void setStoredTime(java.sql.Timestamp StoredTime)
	{
		this.StoredTime = StoredTime;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getOpenBoxKey()
	{
		return OpenBoxKey;
	}
	public void setOpenBoxKey(String OpenBoxKey)
	{
		this.OpenBoxKey = OpenBoxKey;
	}

	public String getOpenBoxKey2()
	{
		return OpenBoxKey2;
	}
	public void setOpenBoxKey2(String OpenBoxKey2)
	{
		this.OpenBoxKey2 = OpenBoxKey2;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getPostmanName()
	{
		return PostmanName;
	}
	public void setPostmanName(String PostmanName)
	{
		this.PostmanName = PostmanName;
	}

	public String getCompanyName()
	{
		return CompanyName;
	}
	public void setCompanyName(String CompanyName)
	{
		this.CompanyName = CompanyName;
	}

	public String getPostmanMobile()
	{
		return PostmanMobile;
	}
	public void setPostmanMobile(String PostmanMobile)
	{
		this.PostmanMobile = PostmanMobile;
	}

	public double getLongitude()
	{
		return Longitude;
	}
	public void setLongitude(double Longitude)
	{
		this.Longitude = Longitude;
	}

	public double getLatitude()
	{
		return Latitude;
	}
	public void setLatitude(double Latitude)
	{
		this.Latitude = Latitude;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getStaOrderID()
	{
		return StaOrderID;
	}
	public void setStaOrderID(String StaOrderID)
	{
		this.StaOrderID = StaOrderID;
	}

	public String getOfLogisticsID()
	{
		return OfLogisticsID;
	}
	public void setOfLogisticsID(String OfLogisticsID)
	{
		this.OfLogisticsID = OfLogisticsID;
	}

	public String getOfLogisticsName()
	{
		return OfLogisticsName;
	}
	public void setOfLogisticsName(String OfLogisticsName)
	{
		this.OfLogisticsName = OfLogisticsName;
	}

}