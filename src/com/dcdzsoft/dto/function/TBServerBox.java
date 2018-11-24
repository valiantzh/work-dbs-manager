package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TBServerBox implements java.io.Serializable
{
	public String TerminalNo = "";
	public String BoxNo = "";
	public String BoxType = "";
	public String BoxName = "";
	public int DeskNo;
	public int DeskBoxNo;
	public String BoxStatus = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}