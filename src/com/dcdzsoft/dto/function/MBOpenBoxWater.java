package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBOpenBoxWater implements java.io.Serializable
{
	public String TradeWaterNo = "";
	public String OpenBoxUser = "";
	public String OpenBoxType = "";
	public java.sql.Date OpenBoxDate;
	public java.sql.Timestamp OpenBoxDateTime;
	public String OpenBoxStatus = "";
	public String TerminalNo = "";
	public String BoxNo = "";
	public String PackageID = "";
	public String PackageStatus = "";
	public java.sql.Timestamp LastModifyTime;
	public String UploadFlag = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}