package com.dcdzsoft.dto.business;

/**
* 管理员总体限制权限查询
*/

public class OutParamOPOperAllLimitQry implements java.io.Serializable
{
	public String OperID = ""; //管理员编号
	public int LimitTypeID; //限制类别编号
	public String LimitObject = ""; //限制的对象
	public String LimitObjectName = ""; //限制的对象名称

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public int getLimitTypeID()
	{
		return LimitTypeID;
	}
	public void setLimitTypeID(int LimitTypeID)
	{
		this.LimitTypeID = LimitTypeID;
	}

	public String getLimitObject()
	{
		return LimitObject;
	}
	public void setLimitObject(String LimitObject)
	{
		this.LimitObject = LimitObject;
	}

	public String getLimitObjectName()
	{
		return LimitObjectName;
	}
	public void setLimitObjectName(String LimitObjectName)
	{
		this.LimitObjectName = LimitObjectName;
	}

}