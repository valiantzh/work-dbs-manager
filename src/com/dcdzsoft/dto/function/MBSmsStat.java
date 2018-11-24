package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBSmsStat implements java.io.Serializable
{
	public String TerminalNo = "";
	public int OccurYear;
	public int OccurMonth;
	public int TotalNum;
	public int PwdNum;
	public int ExpireNum;
	public int ReminderNum;
	public int DynamicNum;
	public int PickupNum;
	public int OtherNum;
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}