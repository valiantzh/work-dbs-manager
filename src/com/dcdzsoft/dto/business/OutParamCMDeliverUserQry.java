package com.dcdzsoft.dto.business;

/**
* 投递服务用户查询
*/

public class OutParamCMDeliverUserQry implements java.io.Serializable
{
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String FirstName = ""; //用户名
	public String LastName = ""; //用户姓
	public String SpecialFlag = ""; //特殊标志
	public String SpecialFlagName = ""; //特殊标志名称
	public String ServiceStatus = ""; //服务状态
	public String ServiceStatusName = ""; //服务状态名称
	public String DeliverFailHandle = ""; //投递失败处理
	public String DeliverFailHandleName = ""; //投递失败处理
	public String Address = ""; //地址
	public String TerminalNo = ""; //设备号
	public String DepartmentID = ""; //网点编号
	public String LastModifyTime = ""; //最后修改时间
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

	public String getFirstName()
	{
		return FirstName;
	}
	public void setFirstName(String FirstName)
	{
		this.FirstName = FirstName;
	}

	public String getLastName()
	{
		return LastName;
	}
	public void setLastName(String LastName)
	{
		this.LastName = LastName;
	}

	public String getSpecialFlag()
	{
		return SpecialFlag;
	}
	public void setSpecialFlag(String SpecialFlag)
	{
		this.SpecialFlag = SpecialFlag;
	}

	public String getSpecialFlagName()
	{
		return SpecialFlagName;
	}
	public void setSpecialFlagName(String SpecialFlagName)
	{
		this.SpecialFlagName = SpecialFlagName;
	}

	public String getServiceStatus()
	{
		return ServiceStatus;
	}
	public void setServiceStatus(String ServiceStatus)
	{
		this.ServiceStatus = ServiceStatus;
	}

	public String getServiceStatusName()
	{
		return ServiceStatusName;
	}
	public void setServiceStatusName(String ServiceStatusName)
	{
		this.ServiceStatusName = ServiceStatusName;
	}

	public String getDeliverFailHandle()
	{
		return DeliverFailHandle;
	}
	public void setDeliverFailHandle(String DeliverFailHandle)
	{
		this.DeliverFailHandle = DeliverFailHandle;
	}

	public String getDeliverFailHandleName()
	{
		return DeliverFailHandleName;
	}
	public void setDeliverFailHandleName(String DeliverFailHandleName)
	{
		this.DeliverFailHandleName = DeliverFailHandleName;
	}

	public String getAddress()
	{
		return Address;
	}
	public void setAddress(String Address)
	{
		this.Address = Address;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getLastModifyTime()
	{
		return LastModifyTime;
	}
	public void setLastModifyTime(String LastModifyTime)
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

}