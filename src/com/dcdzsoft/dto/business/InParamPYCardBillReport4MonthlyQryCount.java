package com.dcdzsoft.dto.business;

/**
* 查询会员账单月报表
*/

public class InParamPYCardBillReport4MonthlyQryCount implements java.io.Serializable
{
	public String FunctionID = "410235"; //功能编号

	public String OperID = ""; //管理员编号
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerType = ""; //会员类型（99-投递员）
	public String BillType = ""; //账单类型：1-充值；2-消费
	public String CompanyID = ""; //投递公司编号
	public String DepartmentID = ""; //运营网点编号
	public String StatFlag = ""; //统计标志：1-按投递员统计
	public int OccurYear; //发生年份
	public int OccurMonth; //发生月份

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410235";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410235";
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

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

	public String getBillType()
	{
		return BillType;
	}
	public void setBillType(String BillType)
	{
		this.BillType = BillType;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getStatFlag()
	{
		return StatFlag;
	}
	public void setStatFlag(String StatFlag)
	{
		this.StatFlag = StatFlag;
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

}