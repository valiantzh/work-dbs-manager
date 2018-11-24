package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CMCustomerCard implements java.io.Serializable
{
	public String CardID = "";
	public String DepartmentID = "";
	public String CustomerID = "";
	public String CardType = "";
	public String CardStatus = "";
	public String GetStatus = "";
	public String CardCode = "";
	public int Balance;
	public long PreFee;
	public long GiftBalane;
	public long RealBalance;
	public long PayTotalAmt;
	public long ConsumeTotalAmt;
	public long RatioTotalAmt;
	public long Degree;
	public String SellerID = "";
	public java.sql.Timestamp ActiveTime;
	public java.sql.Timestamp CreateTime;
	public java.sql.Timestamp LastModifyTime;
	public String ExtendFields = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}