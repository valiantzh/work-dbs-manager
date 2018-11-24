package com.dcdzsoft.dto.business;

/**
* 查询会员账单月报表
*/

public class OutParamPYCardBillReport4MonthlyQry implements java.io.Serializable
{
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String BillType = ""; //账单类型：1-充值；2-消费
	public String BillTypeName = ""; //账单类型：1-充值；2-消费
	public int BillAmt; //交易金额
	public String CompanyID = ""; //投递公司编号
	public String CompanyName = ""; //投递公司名称
	public String DepartmentID = ""; //网点编号
	public String DepartmentName = ""; //网点名称
	public int OccurYear; //发生年份
	public int OccurMonth; //发生月份
	public String Remark = ""; //备注

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

	public int getBillAmt()
	{
		return BillAmt;
	}
	public void setBillAmt(int BillAmt)
	{
		this.BillAmt = BillAmt;
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

	public int getOccurYear()
	{
		return OccurYear;
	}
	public void setOccurYear(int OccurYear)
	{
		this.OccurYear = OccurYear;
	}

	public int getOccurMonth()
	{
		return OccurMonth;
	}
	public void setOccurMonth(int OccurMonth)
	{
		this.OccurMonth = OccurMonth;
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