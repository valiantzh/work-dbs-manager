package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SCTimestamp implements java.io.Serializable
{
	public long TimestampID;
	public java.sql.Timestamp TerminalTimestamp;
	public java.sql.Timestamp BoxTimestamp;
	public java.sql.Timestamp LogTimestamp;
	public java.sql.Timestamp WrongpwdTimestamp;
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}