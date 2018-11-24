package com.dcdzsoft.dto.business;

/**
* 修改软件版本号
*/

public class InParamSMModSysVersion implements java.io.Serializable
{
	public String FunctionID = "110112"; //功能编号

	public String OperID = ""; //操作员编号
	public String SystemID = ""; //系统编号
	public String SoftwareVersion = ""; //软件版本号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110112";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110112";
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

	public String getSystemID()
	{
		return SystemID;
	}
	public void setSystemID(String SystemID)
	{
		this.SystemID = SystemID;
	}

	public String getSoftwareVersion()
	{
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String SoftwareVersion)
	{
		this.SoftwareVersion = SoftwareVersion;
	}

}