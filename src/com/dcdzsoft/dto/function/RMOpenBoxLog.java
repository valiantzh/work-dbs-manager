package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RMOpenBoxLog implements java.io.Serializable
{
	public long WaterID;
	public String OpenBoxUser = "";
	public String OpenBoxType = "";
	public String BoxNo = "";
	public String TerminalNo = "";
	public String PackageID = "";
	public String CustomerMobile = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}