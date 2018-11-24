package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SMTerminalSoft implements java.io.Serializable
{
	public String TerminalNo = "";
	public String SoftwareType = "";
	public String SoftwareID = "";
	public String LastVersion = "";
	public String UpdateStatus = "";
	public String SystemID = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}