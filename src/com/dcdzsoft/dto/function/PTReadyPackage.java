package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PTReadyPackage implements java.io.Serializable
{
	public String TerminalNo = "";
	public String PackageID = "";
	public java.sql.Timestamp OrderTime;
	public java.sql.Timestamp ExpiredTime;
	public String PostmanID = "";
	public String CompanyID = "";
	public String BoxNo = "";
	public String CustomerID = "";
	public String CustomerMobile = "";
	public String CustomerName = "";
	public String CustomerAddress = "";
	public double HireAmt;
	public String HireWhoPay = "";
	public String PosPayFlag = "";
	public double PayAmt;
	public String PackageStatus = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}