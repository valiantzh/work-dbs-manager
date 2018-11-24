package com.dcdzsoft.dto.business;

/**
* 同步箱体信息
*/

public class OutParamSCSyncTBBox implements java.io.Serializable
{
	public String terminalNo = ""; //设备号
	public String terminalName = ""; //设备名称
	public String boxNo = ""; //箱门编号
	public String boxName = ""; //箱门名称
	public String bSize = ""; //箱类型编号
	public String status = ""; //箱状态
	public java.sql.Timestamp createTime; //创建时间
	public java.sql.Timestamp updateTime; //最后修改时间

	public String getterminalNo()
	{
		return terminalNo;
	}
	public void setterminalNo(String terminalNo)
	{
		this.terminalNo = terminalNo;
	}

	public String getterminalName()
	{
		return terminalName;
	}
	public void setterminalName(String terminalName)
	{
		this.terminalName = terminalName;
	}

	public String getboxNo()
	{
		return boxNo;
	}
	public void setboxNo(String boxNo)
	{
		this.boxNo = boxNo;
	}

	public String getboxName()
	{
		return boxName;
	}
	public void setboxName(String boxName)
	{
		this.boxName = boxName;
	}

	public String getbSize()
	{
		return bSize;
	}
	public void setbSize(String bSize)
	{
		this.bSize = bSize;
	}

	public String getstatus()
	{
		return status;
	}
	public void setstatus(String status)
	{
		this.status = status;
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