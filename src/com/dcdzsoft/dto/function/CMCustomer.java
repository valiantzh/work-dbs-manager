package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CMCustomer implements java.io.Serializable
{
	public String CustomerID = "";
	public String CustomerName = "";
	public String CustomerType = "";
	public String CustomerStatus = "";
	public String CustomerMobile = "";
	public String IDCard = "";
	public String Password = "";
	public String PlainPassword = "";
	public String BindCardID = "";
	public String BindMobile = "";
	public String Address = "";
	public String Email = "";
	public String TerminalNo = "";
	public String CreateOperName = "";
	public java.sql.Timestamp CreateTime;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}