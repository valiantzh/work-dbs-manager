package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PYConsumedCard implements java.io.Serializable
{
	public String CardNo = "";
	public String CardType = "";
	public String CardStatus = "";
	public int Balance;
	public String BalanceUnit = "";
	public String CustomerID = "";
	public int CreditLimit;
	public int Discount;
	public java.sql.Date EffectiveDate;
	public java.sql.Date ExpirationDate;
	public int WarningLimit;
	public String WarningFlag = "";
	public java.sql.Timestamp CreateTime;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}