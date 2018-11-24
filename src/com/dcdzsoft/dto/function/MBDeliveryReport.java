package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBDeliveryReport implements java.io.Serializable
{
	public String DepartmentID = "";
	public java.sql.Date OccurDate;
	public int TotalCount;
	public int YzCount;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}