package com.dcdzsoft.dto.business;

/**
* 获取投递员列表
*/

public class OutParamPMGetPostmanList implements java.io.Serializable
{
	public String PostmanID = ""; //投递员编号
	public String CompanyID = ""; //投递公司编号
	public String PostmanName = ""; //投递员名称
	public String PostmanType = ""; //投递员类型
	public String Password = ""; //投递员密码
	public String OfficeTel = ""; //办公电话
	public String Mobile = ""; //手机
	public String Email = ""; //电子邮件
	public String ContactAddress = ""; //联系地址
	public String InputMobileFlag = ""; //转入标志
	public String Remark = ""; //备注

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getPostmanName()
	{
		return PostmanName;
	}
	public void setPostmanName(String PostmanName)
	{
		this.PostmanName = PostmanName;
	}

	public String getPostmanType()
	{
		return PostmanType;
	}
	public void setPostmanType(String PostmanType)
	{
		this.PostmanType = PostmanType;
	}

	public String getPassword()
	{
		return Password;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}

	public String getOfficeTel()
	{
		return OfficeTel;
	}
	public void setOfficeTel(String OfficeTel)
	{
		this.OfficeTel = OfficeTel;
	}

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
	}

	public String getEmail()
	{
		return Email;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}

	public String getContactAddress()
	{
		return ContactAddress;
	}
	public void setContactAddress(String ContactAddress)
	{
		this.ContactAddress = ContactAddress;
	}

	public String getInputMobileFlag()
	{
		return InputMobileFlag;
	}
	public void setInputMobileFlag(String InputMobileFlag)
	{
		this.InputMobileFlag = InputMobileFlag;
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