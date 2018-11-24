package com.dcdzsoft.dto.business;

/**
* 删除软件更新包
*/

public class InParamSMUpgradePackDel implements java.io.Serializable
{
	public String FunctionID = "110503"; //功能编号

	public String OperID = ""; //操作员编号
	public String SoftwareID = ""; //软件编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110503";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110503";
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

	public String getSoftwareID()
	{
		return SoftwareID;
	}
	public void setSoftwareID(String SoftwareID)
	{
		this.SoftwareID = SoftwareID;
	}

}