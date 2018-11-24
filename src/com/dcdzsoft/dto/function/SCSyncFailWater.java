package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SCSyncFailWater implements java.io.Serializable
{
	public long WaterID;
	public String TerminalNo = "";
	public String ServiceName = "";
	public String PackageID = "";
	public String MsgContent = "";
	public String FailReason = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}