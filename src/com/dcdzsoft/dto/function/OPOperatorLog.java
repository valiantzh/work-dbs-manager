package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OPOperatorLog implements java.io.Serializable
{
	public long OperLogID;
	public String FunctionID = "";
	public String OperID = "";
	public int OperType;
	public String TerminalNo = "";
	public java.sql.Date OccurDate;
	public java.sql.Timestamp OccurTime;
	public String StationAddr = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}