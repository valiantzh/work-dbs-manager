package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OPOperator implements java.io.Serializable
{
	public String OperID = "";
	public String DepartmentID = "";
	public String UserID = "";
	public int OperType;
	public String OperName = "";
	public String OperPassword = "";
	public String PlainPassword = "";
	public String BindCardID = "";
	public String BindMobile = "";
	public String OfficeTel = "";
	public String Mobile = "";
	public String Email = "";
	public java.sql.Date CreateDate;
	public java.sql.Timestamp CreateTime;
	public String UpperUserID = "";
	public String OperStatus = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}