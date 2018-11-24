package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SCPushDataQueue implements java.io.Serializable
{
	public String MsgUid = "";
	public String TerminalNo = "";
	public String ServiceName = "";
	public String MsgContent = "";
	public int FailureCount;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}