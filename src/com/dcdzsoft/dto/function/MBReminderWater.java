package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBReminderWater implements java.io.Serializable
{
	public long WaterID;
	public String TerminalNo = "";
	public String CustomerMobile = "";
	public String PackageID = "";
	public String PostmanID = "";
	public java.sql.Date StoredDate;
	public java.sql.Timestamp StoredTime;
	public int ReminderNum;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}