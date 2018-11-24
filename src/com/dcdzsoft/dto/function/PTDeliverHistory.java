package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PTDeliverHistory implements java.io.Serializable
{
	public String TerminalNo = "";
	public String PackageID = "";
	public java.sql.Timestamp StoredTime;
	public String PostmanID = "";
	public java.sql.Date StoredDate;
	public java.sql.Timestamp ExpiredTime;
	public String CompanyID = "";
	public String DynamicCode = "";
	public String BoxNo = "";
	public String CustomerID = "";
	public String CustomerMobile = "";
	public String CustomerName = "";
	public String CustomerAddress = "";
	public String TakedPersonID = "";
	public String OpenBoxKey = "";
	public String BlankBoxKey = "";
	public double HireAmt;
	public String HireWhoPay = "";
	public double PayedAmt;
	public String PosPayFlag = "";
	public java.sql.Timestamp TakedTime;
	public String TakedWay = "";
	public String LeftFlag = "";
	public String PackageStatus = "";
	public String TradeWaterNo = "";
	public String OfLogisticsID = "";
	public String OfLogisticsName = "";
	public String StaOrderID = "";
	public java.sql.Timestamp LastModifyTime;
	public String UploadFlag = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}