package com.dcdzsoft.dto.business;

/**
* 获取软件升级信息
*/

public class InParamSMGetUpgradeInfo implements java.io.Serializable
{
	public String FunctionID = "110541"; //功能编号

	public String TerminalNo = ""; //设备号
	public String SoftwareType = ""; //软件类型：1-驱动软件 2-终端软件
	public String SoftwareVersion = ""; //软件版本号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110541";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110541";
		else
			this.FunctionID = FunctionID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getSoftwareType()
	{
		return SoftwareType;
	}
	public void setSoftwareType(String SoftwareType)
	{
		this.SoftwareType = SoftwareType;
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