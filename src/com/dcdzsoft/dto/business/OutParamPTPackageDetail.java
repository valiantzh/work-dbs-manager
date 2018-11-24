package com.dcdzsoft.dto.business;

/**
* 单个包裹查询
*/

public class OutParamPTPackageDetail implements java.io.Serializable
{
	public String PackageID = ""; //订单号
	public java.sql.Timestamp OrderTime; //下单时间
	public java.sql.Timestamp ExpiredTime; //逾期时间
	public String CompanyID = ""; //投递公司编号
	public String BoxNo = ""; //箱门编号
	public String BoxType = "";//箱类型
	public String CustomerID = ""; //取件人编号
	public String CustomerName = ""; //取件人姓名
	public String CustomerAddress = ""; //取件人地址
	public String CustomerMobile = ""; //取件人手机
	public String PosPayFlag = ""; //支付标志
	public String PackageStatus = ""; //包裹状态
	public String Remark = ""; //备注

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public java.sql.Timestamp getOrderTime()
	{
		return OrderTime;
	}
	public void setOrderTime(java.sql.Timestamp OrderTime)
	{
		this.OrderTime = OrderTime;
	}

	public java.sql.Timestamp getExpiredTime()
	{
		return ExpiredTime;
	}
	public void setExpiredTime(java.sql.Timestamp ExpiredTime)
	{
		this.ExpiredTime = ExpiredTime;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getCustomerName()
	{
		return CustomerName;
	}
	public void setCustomerName(String CustomerName)
	{
		this.CustomerName = CustomerName;
	}

	public String getCustomerAddress()
	{
		return CustomerAddress;
	}
	public void setCustomerAddress(String CustomerAddress)
	{
		this.CustomerAddress = CustomerAddress;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getPosPayFlag()
	{
		return PosPayFlag;
	}
	public void setPosPayFlag(String PosPayFlag)
	{
		this.PosPayFlag = PosPayFlag;
	}

	public String getPackageStatus()
	{
		return PackageStatus;
	}
	public void setPackageStatus(String PackageStatus)
	{
		this.PackageStatus = PackageStatus;
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