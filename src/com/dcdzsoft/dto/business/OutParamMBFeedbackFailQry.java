package com.dcdzsoft.dto.business;

/**
* 投递信息反馈失败信息查询
*/

public class OutParamMBFeedbackFailQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String PackageID = ""; //订单号
	public java.sql.Timestamp StoredTime; //存物时间
	public String PackageStatus = ""; //包裹状态
	public String TradeWaterNo = ""; //交易流水号
	public String OfBureau = ""; //所属投递段
	public String BoxNo = ""; //箱门编号
	public String PostmanID = ""; //投递员编号
	public String CompanyID = ""; //投递公司编号
	public String CustomerMobile = ""; //取件人手机
	public String OfLogisticsID = ""; //运单所属物流公司
	public String OfLogisticsName = ""; //运单所属物流公司名称
	public String StaOrderID = ""; //电商运单号
	public String OpenBoxKey = ""; //开箱密码
	public String Remark = ""; //备注

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
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

	public String getPackageStatus()
	{
		return PackageStatus;
	}
	public void setPackageStatus(String PackageStatus)
	{
		this.PackageStatus = PackageStatus;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getOfBureau()
	{
		return OfBureau;
	}
	public void setOfBureau(String OfBureau)
	{
		this.OfBureau = OfBureau;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
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

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
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

	public String getStaOrderID()
	{
		return StaOrderID;
	}
	public void setStaOrderID(String StaOrderID)
	{
		this.StaOrderID = StaOrderID;
	}

	public String getOpenBoxKey()
	{
		return OpenBoxKey;
	}
	public void setOpenBoxKey(String OpenBoxKey)
	{
		this.OpenBoxKey = OpenBoxKey;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}