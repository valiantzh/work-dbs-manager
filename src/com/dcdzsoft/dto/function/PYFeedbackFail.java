package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PYFeedbackFail implements java.io.Serializable
{
	public String TradeWaterNo = "";
	public String PackageID = "";
	public String TerminalNo = "";
	public String BoxNo = "";
	public java.sql.Timestamp StoredTime;
	public java.sql.Date StoredDate;
	public String PartnerID = "";
	public String PartnerName = "";
	public String CardType = "";
	public String CardNo = "";
	public String BatchID = "";
	public String ReferenceCode = "";
	public String TransactionType = "";
	public int TransactionAmt;
	public String TransactionDate = "";
	public java.sql.Timestamp FailureTime;
	public int FailureCount;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}