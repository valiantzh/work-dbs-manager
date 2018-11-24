package com.dcdzsoft.dto.business;

/**
* 会员信息查询
*/

public class OutParamCMCustomerQry implements java.io.Serializable
{
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerStatus = ""; //会员状态
	public String CustomerStatusName = ""; //会员状态名称
	public String CustomerType = ""; //会员类型
	public String CustomerTypeName = ""; //会员类型名称
	public String CustomerMobile = ""; //会员手机
	public String IDCard = ""; //身份证号
	public String BindCardID = ""; //绑定卡号
	public String BindMobile = ""; //绑定手机
	public String Address = ""; //地址
	public String Email = ""; //邮箱
	public String TerminalNo = ""; //设备号
	public String CreateOperName = ""; //创建人名称
	public java.sql.Timestamp CreateTime; //创建时间
	public java.sql.Timestamp LastModifyTime; //最后修改时间
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

	public String getCustomerStatus()
	{
		return CustomerStatus;
	}
	public void setCustomerStatus(String CustomerStatus)
	{
		this.CustomerStatus = CustomerStatus;
	}

	public String getCustomerStatusName()
	{
		return CustomerStatusName;
	}
	public void setCustomerStatusName(String CustomerStatusName)
	{
		this.CustomerStatusName = CustomerStatusName;
	}

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

	public String getCustomerTypeName()
	{
		return CustomerTypeName;
	}
	public void setCustomerTypeName(String CustomerTypeName)
	{
		this.CustomerTypeName = CustomerTypeName;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getIDCard()
	{
		return IDCard;
	}
	public void setIDCard(String IDCard)
	{
		this.IDCard = IDCard;
	}

	public String getBindCardID()
	{
		return BindCardID;
	}
	public void setBindCardID(String BindCardID)
	{
		this.BindCardID = BindCardID;
	}

	public String getBindMobile()
	{
		return BindMobile;
	}
	public void setBindMobile(String BindMobile)
	{
		this.BindMobile = BindMobile;
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

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getCreateOperName()
	{
		return CreateOperName;
	}
	public void setCreateOperName(String CreateOperName)
	{
		this.CreateOperName = CreateOperName;
	}

	public java.sql.Timestamp getCreateTime()
	{
		return CreateTime;
	}
	public void setCreateTime(java.sql.Timestamp CreateTime)
	{
		this.CreateTime = CreateTime;
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

}