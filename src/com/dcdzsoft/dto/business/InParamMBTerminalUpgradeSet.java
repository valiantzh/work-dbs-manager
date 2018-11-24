package com.dcdzsoft.dto.business;

/**
* 设置终端软件升级版本
*/

public class InParamMBTerminalUpgradeSet implements java.io.Serializable
{
	public String FunctionID = "150341"; //功能编号

	public String OperID = ""; //操作员编号
	public String TerminalNo = ""; //设备编号
	public String SoftwareVersion = ""; //软件版本号
	public String DownloadUrl = ""; //下载路径
	public String MD5Verify = ""; //校验码
	public String UpgradeDesc = ""; //升级描述
	public String UpgradeFlag = ""; //升级标志，1-全部升级

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150341";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150341";
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

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getSoftwareVersion()
	{
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String SoftwareVersion)
	{
		this.SoftwareVersion = SoftwareVersion;
	}

	public String getDownloadUrl()
	{
		return DownloadUrl;
	}
	public void setDownloadUrl(String DownloadUrl)
	{
		this.DownloadUrl = DownloadUrl;
	}

	public String getMD5Verify()
	{
		return MD5Verify;
	}
	public void setMD5Verify(String MD5Verify)
	{
		this.MD5Verify = MD5Verify;
	}

	public String getUpgradeDesc()
	{
		return UpgradeDesc;
	}
	public void setUpgradeDesc(String UpgradeDesc)
	{
		this.UpgradeDesc = UpgradeDesc;
	}

	public String getUpgradeFlag()
	{
		return UpgradeFlag;
	}
	public void setUpgradeFlag(String UpgradeFlag)
	{
		this.UpgradeFlag = UpgradeFlag;
	}

}