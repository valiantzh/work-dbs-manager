package com.dcdzsoft.dto.business;

/**
* 请求服务器分配可投递箱门
*/

public class OutParamPTAllocateeBox implements java.io.Serializable
{
	public String InBlackList = ""; //是否在黑名单列表
	public String BoxNo = ""; //箱门编号
	public String Remark = ""; //备注

	public String getInBlackList()
	{
		return InBlackList;
	}
	public void setInBlackList(String InBlackList)
	{
		this.InBlackList = InBlackList;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
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