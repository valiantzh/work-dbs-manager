package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBLockUserTime implements java.io.Serializable
{
	public String PackageID = "";
	public java.sql.Date LockedDate;
	public int WrongCount;
	public String LockStatus = "";
	public int LockTimeRange;
	public java.sql.Timestamp LastModifyTime;
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}