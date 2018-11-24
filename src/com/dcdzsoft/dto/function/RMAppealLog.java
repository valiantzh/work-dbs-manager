package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RMAppealLog implements java.io.Serializable
{
	public String AppealNo = "";
	public String AppealUser = "";
	public String AppealType = "";
	public String TerminalNo = "";
	public String BoxNo = "";
	public String PackageID = "";
	public String CustomerMobile = "";
	public String OpenBoxKey = "";
	public String AppealContent = "";
	public java.sql.Timestamp AppealTime;
	public String AppealStatus = "";
	public String FaultReason = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}