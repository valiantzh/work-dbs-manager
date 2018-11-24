package com.dcdzsoft.dto.business;

/**
* 管理员特殊权限设置查询
*/

public class OutParamOPOperSpeRightQry implements java.io.Serializable
{
	public int SpePrivID; //特殊权限号
	public String OperID = ""; //管理员内部编号
	public String OperName = ""; //管理员姓名
	public String SpePrivName = ""; //特殊权限名称

	public int getSpePrivID()
	{
		return SpePrivID;
	}
	public void setSpePrivID(int SpePrivID)
	{
		this.SpePrivID = SpePrivID;
	}

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

	public String getSpePrivName()
	{
		return SpePrivName;
	}
	public void setSpePrivName(String SpePrivName)
	{
		this.SpePrivName = SpePrivName;
	}

}