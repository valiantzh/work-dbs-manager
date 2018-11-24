package com.dcdzsoft.dto.business;

/**
* 下载用户信息详情
*/

public class OutParamPTTenantDetail implements java.io.Serializable
{
	
    public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerMobile = ""; //手机
	public String FirstName = ""; //用户名
	public String LastName = ""; //用户姓
	public String SpecialFlag = ""; //特殊标志：0-正常
	public String ServiceStatus = ""; //服务状态：0-正常;1-注销;2-未激活
	public String DeliverFailHandle = ""; //投递失败处理：0-通知收件人；1-放收发室
	public String Address = ""; //地址
	public String Email = ""; //邮箱

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

	public String getServiceStatus()
	{
		return ServiceStatus;
	}
	public void setServiceStatus(String ServiceStatus)
	{
		this.ServiceStatus = ServiceStatus;
	}

	public String getDeliverFailHandle()
	{
		return DeliverFailHandle;
	}
	public void setDeliverFailHandle(String DeliverFailHandle)
	{
		this.DeliverFailHandle = DeliverFailHandle;
	}

	public String getAddress()
	{
		return Address;
	}
	public void setAddress(String Address)
	{
		this.Address = Address;
	}

	public String getEmail()
	{
		return Email;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}

}