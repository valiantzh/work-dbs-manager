package com.dcdzsoft.dto.business;

/**
* 申请开箱密码
*/

public class OutParamRMRequestOpenBoxKey implements java.io.Serializable
{
	public String AppealNo = ""; //求助编号
	public String OpenBoxKey = ""; //提货码
	public String ContactTel = ""; //咨询电话

	public String getAppealNo()
	{
		return AppealNo;
	}
	public void setAppealNo(String AppealNo)
	{
		this.AppealNo = AppealNo;
	}

	public String getOpenBoxKey()
	{
		return OpenBoxKey;
	}
	public void setOpenBoxKey(String OpenBoxKey)
	{
		this.OpenBoxKey = OpenBoxKey;
	}

	public String getContactTel()
	{
		return ContactTel;
	}
	public void setContactTel(String ContactTel)
	{
		this.ContactTel = ContactTel;
	}

}