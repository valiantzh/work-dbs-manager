package com.dcdzsoft.dto.business;

/**
* 远程求助查询
*/

public class OutParamRMAppealQuery implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String BoxNo = ""; //箱门编号
	public String BoxStatus = ""; //箱状态
	public String BoxStatusName = ""; //箱状态名称
	public String FaultStatus = ""; //故障状态
	public String PackageID = ""; //订单号
	public String PostmanID = ""; //投递员编号
	public String CustomerMobile = ""; //取件人手机
	public String AppealUser = ""; //求助用户
	public String AppealType = ""; //求助类型
	public String AppealTypeName = ""; //求助类型名称
	public String AppealNo = ""; //求助编号
	public java.sql.Timestamp AppealTime; //求助时间
	public String AppealStatus = ""; //求助状态
	public String AppealStatusName = ""; //求助状态名称
	public String OpenBoxKey = ""; //开箱密码
	public String AppealContent = ""; //求助内容
	public String FaultReason = ""; //故障原因分析

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getBoxStatus()
	{
		return BoxStatus;
	}
	public void setBoxStatus(String BoxStatus)
	{
		this.BoxStatus = BoxStatus;
	}

	public String getBoxStatusName()
	{
		return BoxStatusName;
	}
	public void setBoxStatusName(String BoxStatusName)
	{
		this.BoxStatusName = BoxStatusName;
	}

	public String getFaultStatus()
	{
		return FaultStatus;
	}
	public void setFaultStatus(String FaultStatus)
	{
		this.FaultStatus = FaultStatus;
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

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
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

	public String getAppealTypeName()
	{
		return AppealTypeName;
	}
	public void setAppealTypeName(String AppealTypeName)
	{
		this.AppealTypeName = AppealTypeName;
	}

	public String getAppealNo()
	{
		return AppealNo;
	}
	public void setAppealNo(String AppealNo)
	{
		this.AppealNo = AppealNo;
	}

	public java.sql.Timestamp getAppealTime()
	{
		return AppealTime;
	}
	public void setAppealTime(java.sql.Timestamp AppealTime)
	{
		this.AppealTime = AppealTime;
	}

	public String getAppealStatus()
	{
		return AppealStatus;
	}
	public void setAppealStatus(String AppealStatus)
	{
		this.AppealStatus = AppealStatus;
	}

	public String getAppealStatusName()
	{
		return AppealStatusName;
	}
	public void setAppealStatusName(String AppealStatusName)
	{
		this.AppealStatusName = AppealStatusName;
	}

	public String getOpenBoxKey()
	{
		return OpenBoxKey;
	}
	public void setOpenBoxKey(String OpenBoxKey)
	{
		this.OpenBoxKey = OpenBoxKey;
	}

	public String getAppealContent()
	{
		return AppealContent;
	}
	public void setAppealContent(String AppealContent)
	{
		this.AppealContent = AppealContent;
	}

	public String getFaultReason()
	{
		return FaultReason;
	}
	public void setFaultReason(String FaultReason)
	{
		this.FaultReason = FaultReason;
	}

}