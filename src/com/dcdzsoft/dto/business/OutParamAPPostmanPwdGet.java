package com.dcdzsoft.dto.business;

/**
* 获取投递员登录凭证
*/

public class OutParamAPPostmanPwdGet implements java.io.Serializable
{
	public String Password = ""; //登录凭证
	public String Mobile = ""; //手机
	public String Remark = ""; //备注

	public String getPassword()
	{
		return Password;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
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