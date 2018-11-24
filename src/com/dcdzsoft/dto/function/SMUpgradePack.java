package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SMUpgradePack implements java.io.Serializable
{
	public String SoftwareID = "";
	public String SystemID = "";
	public String SoftwareName = "";
	public String SoftwareType = "";
	public String SoftwareVersion = "";
	public String SoftSignMd5 = "";
	public String SystemType = "";
	public String DownloadUrl = "";
	public String UpdateContent = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}