package com.dcdzsoft.dto.business;

/**
* 现场管理员查询
*/

public class OutParamOPSpotOperQry implements java.io.Serializable
{
	public String OperID = ""; //管理员内部编号
	public String OperName = ""; //管理员姓名

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getOperName()
	{
		return OperName;
	}
	public void setOperName(String OperName)
	{
		this.OperName = OperName;
	}

}