package com.dcdzsoft.dto.business;

/**
* 查询短信账单流水数量
*/

public class InParamPYSMSBillWaterQryCount implements java.io.Serializable
{
	public String FunctionID = "410065"; //功能编号

	public String OperID = ""; //管理员编号
	public String AccountID = ""; //短信充值账户编号
	public String AccountName = ""; //短信充值账户名称
	public String BillWaterID = ""; //账单流水号
	public String BillType = ""; //账单类型
	public String Ways = ""; //计费方式
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410065";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410065";
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

	public String getAccountID()
	{
		return AccountID;
	}
	public void setAccountID(String AccountID)
	{
		this.AccountID = AccountID;
	}

	public String getAccountName()
	{
		return AccountName;
	}
	public void setAccountName(String AccountName)
	{
		this.AccountName = AccountName;
	}

	public String getBillWaterID()
	{
		return BillWaterID;
	}
	public void setBillWaterID(String BillWaterID)
	{
		this.BillWaterID = BillWaterID;
	}

	public String getBillType()
	{
		return BillType;
	}
	public void setBillType(String BillType)
	{
		this.BillType = BillType;
	}

	public String getWays()
	{
		return Ways;
	}
	public void setWays(String Ways)
	{
		this.Ways = Ways;
	}

	public java.sql.Date getBeginDate()
	{
		return BeginDate;
	}
	public void setBeginDate(java.sql.Date BeginDate)
	{
		this.BeginDate = BeginDate;
	}

	public java.sql.Date getEndDate()
	{
		return EndDate;
	}
	public void setEndDate(java.sql.Date EndDate)
	{
		this.EndDate = EndDate;
	}

}