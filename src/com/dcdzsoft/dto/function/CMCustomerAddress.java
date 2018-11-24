package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CMCustomerAddress implements java.io.Serializable
{
	public String CustomerID = "";
	public String AddressID = "";
	public String Address = "";
	public String DefaultFLag = "";
	public String TerminalNo = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}