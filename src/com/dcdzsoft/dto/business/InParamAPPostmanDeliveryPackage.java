package com.dcdzsoft.dto.business;

/**
* 确认投递（发送到柜端）
*/

public class InParamAPPostmanDeliveryPackage implements java.io.Serializable
{
	public String FunctionID = "650033"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String PostmanID = ""; //投递员编号
	public String PackageID = ""; //订单号
	public String BoxNo = ""; //箱门编号
	public String CustomerID = ""; //取件人编号
	public String CustomerMobile = ""; //取件人手机
	public String LeftFlag = ""; //寄存标志
	public String OfLogisticsID = ""; //运单所属物流公司
	public String OfLogisticsName = ""; //运单所属物流公司名称
	public String StaOrderID = ""; //电商运单号
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650033";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650033";
		else
			this.FunctionID = FunctionID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
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

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getLeftFlag()
	{
		return LeftFlag;
	}
	public void setLeftFlag(String LeftFlag)
	{
		this.LeftFlag = LeftFlag;
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

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}
    @Override
    public String toString() {
        return "InParamAPPostmanDeliveryPackage [FunctionID=" + FunctionID + ", TerminalNo=" + TerminalNo
                + ", TradeWaterNo=" + TradeWaterNo + ", PostmanID=" + PostmanID + ", PackageID=" + PackageID
                + ", BoxNo=" + BoxNo + ", CustomerID=" + CustomerID + ", CustomerMobile=" + CustomerMobile
                + ", LeftFlag=" + LeftFlag + ", OfLogisticsID=" + OfLogisticsID + ", OfLogisticsName=" + OfLogisticsName
                + ", StaOrderID=" + StaOrderID + ", Remark=" + Remark + "]";
    }

}