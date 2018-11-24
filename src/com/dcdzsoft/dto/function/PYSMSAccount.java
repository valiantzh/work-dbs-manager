package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PYSMSAccount implements java.io.Serializable
{
	public String AccountID = "";
	public String AccountName = "";
	public String AccountStatus = "";
	public String DepartmentID = "";
	public java.sql.Timestamp CreateTime;
	public String ContactPerson = "";
	public String Email = "";
	public String Phone = "";
	public String Ways = "";
	public long TotalSMSNum;
	public long LastSMSNum;
	public int SMSCreditLimit;
	public java.sql.Timestamp LastCountTime;
	public java.sql.Date EffectiveDate;
	public java.sql.Date ExpirationDate;
	public int WarningLimit;
	public String WarningFlag = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}