package com.dcdzsoft.dto.business;

/**
* 查询会员卡充值流水信息
*/

public class OutParamPYCustomerCardWaterQry implements java.io.Serializable
{
	public String CardNo = ""; //会员卡编号
	
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerMobile = ""; //会员手机
	public String CustomerType = ""; //会员类型（99-投递员）
	public String TradeWaterNo = ""; //交易流水号
	public String BillType = ""; //账单类型：1-充值；2-消费
	public String BillTypeName = ""; //账单类型：1-充值；2-消费
	public int TransactionAmt; //交易金额
	public String TransactionDate = ""; //交易日期
	public String TradeTerminalNo = ""; //交易终端号
	public String PackageID = ""; //订单号
	public String ReferenceCode = ""; //参考号
	public String TradeUserID = ""; //交易人员编号
	public String UploadFlag = ""; //上传标志
	public String UploadFlagName = ""; //上传标志
	public java.sql.Timestamp LastModifyTime; //最后修改时间
	public String DepartmentID = "";
	public String DepartmentName = "";
	public String CompanyID = "";
	public String Remark = ""; //备注

	public String getCardNo()
	{
		return CardNo;
	}
	public void setCardNo(String CardNo)
	{
		this.CardNo = CardNo;
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

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getBillType()
	{
		return BillType;
	}
	public void setBillType(String BillType)
	{
		this.BillType = BillType;
	}

	public String getBillTypeName()
	{
		return BillTypeName;
	}
	public void setBillTypeName(String BillTypeName)
	{
		this.BillTypeName = BillTypeName;
	}

	public int getTransactionAmt()
	{
		return TransactionAmt;
	}
	public void setTransactionAmt(int TransactionAmt)
	{
		this.TransactionAmt = TransactionAmt;
	}

	public String getTransactionDate()
	{
		return TransactionDate;
	}
	public void setTransactionDate(String TransactionDate)
	{
		this.TransactionDate = TransactionDate;
	}

	public String getTradeTerminalNo()
	{
		return TradeTerminalNo;
	}
	public void setTradeTerminalNo(String TradeTerminalNo)
	{
		this.TradeTerminalNo = TradeTerminalNo;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getReferenceCode()
	{
		return ReferenceCode;
	}
	public void setReferenceCode(String ReferenceCode)
	{
		this.ReferenceCode = ReferenceCode;
	}

	public String getTradeUserID()
	{
		return TradeUserID;
	}
	public void setTradeUserID(String TradeUserID)
	{
		this.TradeUserID = TradeUserID;
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

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}
	public String getDepartmentID() {
		return DepartmentID;
	}
	public void setDepartmentID(String departmentID) {
		DepartmentID = departmentID;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	public String getCompanyID() {
		return CompanyID;
	}
	public void setCompanyID(String companyID) {
		CompanyID = companyID;
	}

}