package com.dcdzsoft.dto.business;

/**
* 获取最新软件版本信息
*/

public class OutParamMBGetNewVersion implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String SoftwareVersion = ""; //软件版本号
	public String DownloadUrl = ""; //下载路径
	public String MD5Verify = ""; //下载升级包的文件完整性校验码
	public String Remark = ""; //备注

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

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}