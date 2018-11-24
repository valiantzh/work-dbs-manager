package com.dcdzsoft.dto.business;

/**
* 同步投递公司信息
*/

public class OutParamSCSyncPMCompany implements java.io.Serializable
{
	public String companyNo = ""; //物流公司编号
	public String companyName = ""; //物流公司名称
	public java.sql.Timestamp createTime; //创建时间
	public java.sql.Timestamp updateTime; //最后修改时间

	public String getcompanyNo()
	{
		return companyNo;
	}
	public void setcompanyNo(String companyNo)
	{
		this.companyNo = companyNo;
	}

	public String getcompanyName()
	{
		return companyName;
	}
	public void setcompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public java.sql.Timestamp getcreateTime()
	{
		return createTime;
	}
	public void setcreateTime(java.sql.Timestamp createTime)
	{
		this.createTime = createTime;
	}

	public java.sql.Timestamp getupdateTime()
	{
		return updateTime;
	}
	public void setupdateTime(java.sql.Timestamp updateTime)
	{
		this.updateTime = updateTime;
	}

}