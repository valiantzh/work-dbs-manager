package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBTerminalAlertLog implements java.io.Serializable
{
	public String TerminalNo = "";
	public long ReportWaterID;
	public String AlertType = "";
	public String AlertLevel = "";
	public String AlertContent = "";
	public String BoxNo = "";
	public java.sql.Date LogDate;
	public java.sql.Timestamp LogTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}