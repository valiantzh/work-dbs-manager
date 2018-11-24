package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OPOperOnline implements java.io.Serializable
{
	public String OperID = "";
	public int OperType;
	public String DepartmentID = "";
	public java.sql.Timestamp LoginInTime;
	public java.sql.Timestamp LoginOutTime;
	public String NetCardAddr = "";
	public String LoginIPAddr = "";
	public java.sql.Timestamp LastLoginTime;
	public String LastLoginIP = "";
	public String PreVersion = "";
	public java.sql.Timestamp LastQueryTime;
	public String CurrentVersion = "";
	public String LoginTerminal = "";
	public String PreClientVession = "";
	public String CurrentClientVersion = "";
	public String OnlineStatus = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}