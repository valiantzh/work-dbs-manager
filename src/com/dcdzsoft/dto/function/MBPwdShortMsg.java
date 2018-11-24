package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBPwdShortMsg implements java.io.Serializable
{
	public long WaterID;
	public String TerminalNo = "";
	public String PackageID = "";
	public java.sql.Timestamp StoredTime;
	public java.sql.Date StoredDate;
	public String CustomerMobile = "";
	public String CustomerName = "";
	public String OpenBoxKey = "";
	public String MsgContent = "";
	public String SendStatus = "";
	public int ReSendNum;
	public java.sql.Timestamp LastModifyTime;
	public String PackageStatus = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}