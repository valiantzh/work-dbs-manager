package com.dcdzsoft.dto.business;

/**
* 查询终端软件版本信息
*/

public class OutParamSMTerminalSoftQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String Location = ""; //安装地址
	public String SoftwareID = ""; //软件编号
	public String SoftwareName = ""; //软件名称
	public String SoftwareType = ""; //软件类型：1-
	public String SoftwareTypeName = ""; //软件类型
	public String SoftwareVersion = ""; //软件版本号
	public String SoftSignMd5 = ""; //软件签名
	public String SystemID = ""; //系统编号
	public String SystemName = ""; //系统名称
	public String SystemType = ""; //系统类型
	public String SystemTypeName = ""; //系统类型
	public String DownloadUrl = ""; //下载地址
	public String UpdateContent = ""; //更新内容
	public java.sql.Timestamp LastModifyTime; //最后修改时间
	public String Remark = ""; //备注

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
	}

	public String getSoftwareID()
	{
		return SoftwareID;
	}
	public void setSoftwareID(String SoftwareID)
	{
		this.SoftwareID = SoftwareID;
	}

	public String getSoftwareName()
	{
		return SoftwareName;
	}
	public void setSoftwareName(String SoftwareName)
	{
		this.SoftwareName = SoftwareName;
	}

	public String getSoftwareType()
	{
		return SoftwareType;
	}
	public void setSoftwareType(String SoftwareType)
	{
		this.SoftwareType = SoftwareType;
	}

	public String getSoftwareTypeName()
	{
		return SoftwareTypeName;
	}
	public void setSoftwareTypeName(String SoftwareTypeName)
	{
		this.SoftwareTypeName = SoftwareTypeName;
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

	public String getSystemID()
	{
		return SystemID;
	}
	public void setSystemID(String SystemID)
	{
		this.SystemID = SystemID;
	}

	public String getSystemName()
	{
		return SystemName;
	}
	public void setSystemName(String SystemName)
	{
		this.SystemName = SystemName;
	}

	public String getSystemType()
	{
		return SystemType;
	}
	public void setSystemType(String SystemType)
	{
		this.SystemType = SystemType;
	}

	public String getSystemTypeName()
	{
		return SystemTypeName;
	}
	public void setSystemTypeName(String SystemTypeName)
	{
		this.SystemTypeName = SystemTypeName;
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

	public java.sql.Timestamp getLastModifyTime()
	{
		return LastModifyTime;
	}
	public void setLastModifyTime(java.sql.Timestamp LastModifyTime)
	{
		this.LastModifyTime = LastModifyTime;
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