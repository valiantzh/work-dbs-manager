package com.dcdzsoft.dto.business;

/**
* 设备检测箱体查询
*/

public class InParamTBBoxQry4Detect implements java.io.Serializable
{
	public String FunctionID = "210262"; //功能编号

	public String OperID = ""; //管理员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210262";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210262";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

}