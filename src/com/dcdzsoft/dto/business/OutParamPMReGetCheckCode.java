package com.dcdzsoft.dto.business;

/**
* 重新获取验证码
*/

public class OutParamPMReGetCheckCode implements java.io.Serializable
{
	public String CheckCode = ""; //验证码
	public String Remark = ""; //备注

	public String getCheckCode()
	{
		return CheckCode;
	}
	public void setCheckCode(String CheckCode)
	{
		this.CheckCode = CheckCode;
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