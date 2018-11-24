package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PYTransactionWater implements java.io.Serializable
{
	public String TradeWaterNo = "";
	public String TransactionType = "";
	public int TransactionAmt;
	public int TransactionNum;
	public String ChargeType = "";
	public String CustomerID = "";
	public String CardNo = "";
	public String TransactionDate = "";
	public String PackageID = "";
	public String TradeTerminalNo = "";
	public String BoxNo = "";
	public String ReferenceCode = "";
	public String TradeUserID = "";
	public String UploadFlag = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}