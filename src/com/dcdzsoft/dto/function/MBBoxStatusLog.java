package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBBoxStatusLog implements java.io.Serializable
{
	public String TerminalNo = "";
	public String BoxNo = "";
	public String BoxStatus = "";
	public String BoxUsedStatus = "";
	public String BoxArticleStatus = "";
	public String BoxDoorStatus = "";
	public java.sql.Date LogDate;
	public java.sql.Timestamp LogTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}