package com.dcdzsoft.dto.business;

/**
* 投递员APP取回
*/

public class InParamAPPostmanAppTakeout implements java.io.Serializable
{
	public String FunctionID = "650038"; //功能编号

	public String PostmanID = ""; //投递员编号
	public String CustomerMobile = ""; //取件人手机
	public String PackageID = ""; //订单号
	public String PackageStatus = "";//包裹状态
	public java.sql.Timestamp StoredTime; //存物时间
	public String TerminalNo = ""; //设备号
	public String BoxNo = "";//格口号
    public String TradeWaterNo = ""; //交易流水号
    public String Remark = "";//备注
	
   
	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650038";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650038";
		else
			this.FunctionID = FunctionID;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
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
    public String getPackageStatus() {
        return PackageStatus;
    }
    public void setPackageStatus(String packageStatus) {
        PackageStatus = packageStatus;
    }
    public String getBoxNo() {
        return BoxNo;
    }
    public void setBoxNo(String boxNo) {
        BoxNo = boxNo;
    }
    public String getRemark() {
        return Remark;
    }
    public void setRemark(String remark) {
        Remark = remark;
    }

	
}