package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBSignInfo implements java.io.Serializable
{
	public String TerminalNo = "";
	public java.sql.Timestamp SignTime;
	public String SignIP = "";
	public String SignMac = "";
	public String SignKey = "";
	public String SoftwareVersion = "";
	public String InitPasswd = "";
	public String OnlineStatus = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}