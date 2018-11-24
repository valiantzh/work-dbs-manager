package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBTerminalUpgrade implements java.io.Serializable
{
	public String TerminalNo = "";
	public String SoftwareVersion = "";
	public String DownloadUrl = "";
	public String MD5Verify = "";
	public String UpgradeDesc = "";
	public java.sql.Timestamp LastModifyTme;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}