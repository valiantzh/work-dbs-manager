package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PYSMSServices implements java.io.Serializable
{
	public String ServiceID = "";
	public String ServiceName = "";
	public java.sql.Timestamp CreateTime;
	public String Active = "";
	public String Ways = "";
	public double Amount;
	public double Price;
	public long SMSNum;
	public String Units = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}