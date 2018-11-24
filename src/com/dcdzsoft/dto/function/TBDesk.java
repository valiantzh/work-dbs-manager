package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TBDesk implements java.io.Serializable
{
	public int DeskNo;
	public int DialupNo;
	public int BoxNum;
	public double DeskHeight;
	public double DeskWidth;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}