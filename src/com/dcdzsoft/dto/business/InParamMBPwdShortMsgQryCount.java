package com.dcdzsoft.dto.business;

/**
* 取件密码短消息查询数量
*/

public class InParamMBPwdShortMsgQryCount implements java.io.Serializable
{
	public String FunctionID = "150115"; //功能编号

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public String TerminalNo = ""; //设备号
	public String PackageID = ""; //订单号
	public String CustomerMobile = ""; //取件人手机
	public String SendStatus = ""; //发送状态
	public String PackageStatus = ""; //包裹状态
	public java.sql.Date BeginDate; //开始日期
	public java.sql.Date EndDate; //结束日期

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150115";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150115";
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

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
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

	public String getSendStatus()
	{
		return SendStatus;
	}
	public void setSendStatus(String SendStatus)
	{
		this.SendStatus = SendStatus;
	}

	public String getPackageStatus()
	{
		return PackageStatus;
	}
	public void setPackageStatus(String PackageStatus)
	{
		this.PackageStatus = PackageStatus;
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