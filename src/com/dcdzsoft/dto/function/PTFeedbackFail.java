package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PTFeedbackFail implements java.io.Serializable
{
	public String TradeWaterNo = "";
	public String PackageID = "";
	public String TerminalNo = "";
	public String BoxNo = "";
	public java.sql.Timestamp StoredTime;
	public java.sql.Date StoredDate;
	public String PostmanID = "";
	public String CompanyID = "";
	public String OfBureau = "";
	public String CustomerMobile = "";
	public String BlankBoxKey = "";
	public String TakedWay = "";
	public java.sql.Timestamp TakedTime;
	public String LeftFlag = "";
	public String PackageStatus = "";
	public String OfLogisticsID = "";
	public String OfLogisticsName = "";
	public String StaOrderID = "";
	public java.sql.Timestamp FailureTime;
	public int FailureCount;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}