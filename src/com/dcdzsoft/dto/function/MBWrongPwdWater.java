package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBWrongPwdWater implements java.io.Serializable
{
	public long WaterID;
	public String CustomerID = "";
	public String CustomerName = "";
	public String WrongPwd = "";
	public String PackageID = "";
	public java.sql.Timestamp TakedTime;
	public String UploadFlag = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}