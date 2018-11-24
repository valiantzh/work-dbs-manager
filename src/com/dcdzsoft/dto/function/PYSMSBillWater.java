package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PYSMSBillWater implements java.io.Serializable
{
	public String AccountID = "";
	public String BillWaterID = "";
	public java.sql.Timestamp BillTime;
	public String BillType = "";
	public String Ways = "";
	public double Amount;
	public int BillNum;
	public String Units = "";
	public long SMSNum;
	public long SMSBalance;
	public String ServiceID = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}