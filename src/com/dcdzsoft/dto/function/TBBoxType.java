package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TBBoxType implements java.io.Serializable
{
	public String BoxType = "";
	public String BoxTypeName = "";
	public String MBBoxType = "";
	public double BoxHeight;
	public double BoxWidth;
	public double BoxDepth;
	public double BoxInterval;
	public double ChargeAmt;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}