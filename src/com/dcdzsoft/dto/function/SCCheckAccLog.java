package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SCCheckAccLog implements java.io.Serializable
{
	public java.sql.Date OccurDate;
	public String TerminalNo = "";
	public String HandleResult = "";
	public String CheckResult = "";
	public int FileCount;
	public int SameCount;
	public int NotMeCount;
	public int MeNotCount;
	public int NotSameCount;
	public int InvalidCount;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}