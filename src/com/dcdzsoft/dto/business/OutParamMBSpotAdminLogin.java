package com.dcdzsoft.dto.business;

/**
* 现场管理员登录
*/

public class OutParamMBSpotAdminLogin implements java.io.Serializable
{
	public String SpotAdminID = ""; //现场管理员编号
	public String Remark = ""; //备注

	public String getSpotAdminID()
	{
		return SpotAdminID;
	}
	public void setSpotAdminID(String SpotAdminID)
	{
		this.SpotAdminID = SpotAdminID;
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