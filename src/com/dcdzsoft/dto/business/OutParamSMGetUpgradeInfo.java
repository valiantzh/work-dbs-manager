package com.dcdzsoft.dto.business;

/**
* 获取软件升级信息
*/

public class OutParamSMGetUpgradeInfo implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String SoftwareType = ""; //软件类型：1-驱动软件 2-终端软件
	public String SoftwareVersion = ""; //软件版本号
	public String SoftSignMd5 = ""; //软件签名
	public String DownloadUrl = ""; //下载地址
	public String UpdateContent = ""; //更新内容
	public String Remark = ""; //备注

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

	public String getSoftSignMd5()
	{
		return SoftSignMd5;
	}
	public void setSoftSignMd5(String SoftSignMd5)
	{
		this.SoftSignMd5 = SoftSignMd5;
	}

	public String getDownloadUrl()
	{
		return DownloadUrl;
	}
	public void setDownloadUrl(String DownloadUrl)
	{
		this.DownloadUrl = DownloadUrl;
	}

	public String getUpdateContent()
	{
		return UpdateContent;
	}
	public void setUpdateContent(String UpdateContent)
	{
		this.UpdateContent = UpdateContent;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}