package com.dcdzsoft.dto.business;

/**
* 修改软件更新包
*/

public class InParamSMUpgradePackMod implements java.io.Serializable
{
	public String FunctionID = "110502"; //功能编号

	public String OperID = ""; //操作员编号
	public String SoftwareID = ""; //软件编号
	public String SoftwareType = ""; //软件类型：1-
	public String SoftwareName = ""; //软件名称
	public String SoftwareVersion = ""; //软件版本号
	public String SoftSignMd5 = ""; //软件签名
	public String SystemType = ""; //系统类型
	public String DownloadUrl = ""; //下载地址
	public String UpdateContent = ""; //更新内容
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110502";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110502";
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

	public String getSoftwareType()
	{
		return SoftwareType;
	}
	public void setSoftwareType(String SoftwareType)
	{
		this.SoftwareType = SoftwareType;
	}

	public String getSoftwareName()
	{
		return SoftwareName;
	}
	public void setSoftwareName(String SoftwareName)
	{
		this.SoftwareName = SoftwareName;
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

	public String getSystemType()
	{
		return SystemType;
	}
	public void setSystemType(String SystemType)
	{
		this.SystemType = SystemType;
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