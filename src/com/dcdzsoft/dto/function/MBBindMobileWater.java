package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBBindMobileWater implements java.io.Serializable
{
	public String VerifyKey = "";
	public java.sql.Timestamp ExpiredTime;
	public String BindMobile = "";
	public String PostmanID = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}