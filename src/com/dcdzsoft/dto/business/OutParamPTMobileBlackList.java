package com.dcdzsoft.dto.business;

/**
* 验证手机是否在黑名单列表
*/

public class OutParamPTMobileBlackList implements java.io.Serializable
{
	public String InBlackList = ""; //是否在黑名单列表
	public String Remark = ""; //备注

	public String getInBlackList()
	{
		return InBlackList;
	}
	public void setInBlackList(String InBlackList)
	{
		this.InBlackList = InBlackList;
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