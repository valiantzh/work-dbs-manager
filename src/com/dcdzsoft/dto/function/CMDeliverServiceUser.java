package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CMDeliverServiceUser implements java.io.Serializable
{
	public String CustomerID = "";
	public String FirstName = "";
	public String LastName = "";
	public String TerminalNo = "";
	public String ServiceStatus = "";
	public String DepartmentID = "";
	public String Address = "";
	public String SpecialFlag = "";
	public String DeliverFailHandle = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}