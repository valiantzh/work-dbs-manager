package com.dcdzsoft.dto.business;

/**
* 投递开箱
*/

public class InParamAPPostmanAppOpenBox implements java.io.Serializable
{
	public String FunctionID = "650027"; //功能编号

	public String TerminalNo = ""; //设备号
	public String PackageID = ""; //订单号
	public String PostmanID = ""; //投递员编号
	public String BoxNo = ""; //箱门编号
	public String TradeWaterNo = ""; //交易流水号
	public String Remark = ""; //备注
	//baimi
	public String SessionID = "";//会话ID
	public String StartNumber = "";//开始副柜编号
	public String EndNumber = "";//结束副柜编号
	public String BoxType = "";//箱子类型(1:小箱 2:中箱 3:大箱)
	public String CustomerMobile = "";//取件人手机号
	public double TimeOut;//快递超时时间（小时）
	public String CompanyID = "";//快递公司编号
	
	
	public String getCustomerMobile() {
		return CustomerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		CustomerMobile = customerMobile;
	}
	public double getTimeOut() {
		return TimeOut;
	}
	public void setTimeOut(double timeOut) {
		TimeOut = timeOut;
	}
	public String getCompanyID() {
		return CompanyID;
	}
	public void setCompanyID(String companyID) {
		CompanyID = companyID;
	}
	public String getSessionID() {
		return SessionID;
	}
	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}
	public String getStartNumber() {
		return StartNumber;
	}
	public void setStartNumber(String startNumber) {
		StartNumber = startNumber;
	}
	public String getEndNumber() {
		return EndNumber;
	}
	public void setEndNumber(String endNumber) {
		EndNumber = endNumber;
	}
	public String getBoxType() {
		return BoxType;
	}
	public void setBoxType(String boxType) {
		BoxType = boxType;
	}
	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650027";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650027";
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

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
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
		return "InParamAPPostmanAppOpenBox [FunctionID=" + FunctionID
				+ ", TerminalNo=" + TerminalNo + ", PackageID=" + PackageID
				+ ", PostmanID=" + PostmanID + ", BoxNo=" + BoxNo
				+ ", TradeWaterNo=" + TradeWaterNo + ", Remark=" + Remark
				+ ", SessionID=" + SessionID + ", StartNumber=" + StartNumber
				+ ", EndNumber=" + EndNumber + ", BoxType=" + BoxType
				+ ", CustomerMobile=" + CustomerMobile + ", TimeOut=" + TimeOut
				+ ", CompanyID=" + CompanyID + "]";
	}

}