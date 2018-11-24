package com.dcdzsoft.dto.business;

/**
* 重置提货码
*/

public class InParamPTResetOpenBoxKey implements java.io.Serializable
{
	public String FunctionID = "330303"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String PackageID = ""; //订单号
	public String CustomerMobile = ""; //取件人手机
	public String OpenBoxKey = ""; //开箱密码
	public String RemoteFlag = ""; //远程操作标志
	public String TradeWaterNo = "";//baimi需求：存储创建时间

	public String getTradeWaterNo() {
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String tradeWaterNo) {
		TradeWaterNo = tradeWaterNo;
	}
	@Override
	public String toString() {
		return "InParamPTResetOpenBoxKey [FunctionID=" + FunctionID
				+ ", OperID=" + OperID + ", TerminalNo=" + TerminalNo
				+ ", PackageID=" + PackageID + ", CustomerMobile="
				+ CustomerMobile + ", OpenBoxKey=" + OpenBoxKey
				+ ", RemoteFlag=" + RemoteFlag + ", TradeWaterNo="
				+ TradeWaterNo + "]";
	}
	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330303";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330303";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

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

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getOpenBoxKey()
	{
		return OpenBoxKey;
	}
	public void setOpenBoxKey(String OpenBoxKey)
	{
		this.OpenBoxKey = OpenBoxKey;
	}

	public String getRemoteFlag()
	{
		return RemoteFlag;
	}
	public void setRemoteFlag(String RemoteFlag)
	{
		this.RemoteFlag = RemoteFlag;
	}

}