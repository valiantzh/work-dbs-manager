package com.dcdzsoft.dto.business;

/**
* 查询控制参数类别
*/

public class OutParamPAControlTypeQry implements java.io.Serializable
{
	public int CtrlTypeID; //控制类别编号
	public String CtrlTypeName = ""; //控制类别名称

	public int getCtrlTypeID()
	{
		return CtrlTypeID;
	}
	public void setCtrlTypeID(int CtrlTypeID)
	{
		this.CtrlTypeID = CtrlTypeID;
	}

	public String getCtrlTypeName()
	{
		return CtrlTypeName;
	}
	public void setCtrlTypeName(String CtrlTypeName)
	{
		this.CtrlTypeName = CtrlTypeName;
	}

}