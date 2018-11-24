package com.dcdzsoft.dto.business;

/**
* 申请开箱密码
*/

public class InParamRMRequestOpenBoxKey implements java.io.Serializable
{
	public String FunctionID = "550021"; //功能编号

	public String AppealUser = ""; //求助用户
	public String AppealType = ""; //求助类型
	public String BoxNo = ""; //箱门编号
	public String PackageID = ""; //订单号
	public String CustomerMobile = ""; //取件人手机
	public java.sql.Timestamp StoredTime; //存物时间
	public java.sql.Timestamp TakedTime; //取件时间
	public String AppealNo = ""; //求助编号
	public String TerminalNo = ""; //设备号
	public String PostmanID = ""; //投递员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "550021";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "550021";
		else
			this.FunctionID = FunctionID;
	}

	public String getAppealUser()
	{
		return AppealUser;
	}
	public void setAppealUser(String AppealUser)
	{
		this.AppealUser = AppealUser;
	}

	public String getAppealType()
	{
		return AppealType;
	}
	public void setAppealType(String AppealType)
	{
		this.AppealType = AppealType;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
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

	public java.sql.Timestamp getStoredTime()
	{
		return StoredTime;
	}
	public void setStoredTime(java.sql.Timestamp StoredTime)
	{
		this.StoredTime = StoredTime;
	}

	public java.sql.Timestamp getTakedTime()
	{
		return TakedTime;
	}
	public void setTakedTime(java.sql.Timestamp TakedTime)
	{
		this.TakedTime = TakedTime;
	}

	public String getAppealNo()
	{
		return AppealNo;
	}
	public void setAppealNo(String AppealNo)
	{
		this.AppealNo = AppealNo;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

}