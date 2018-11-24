package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBMsgSendWater implements java.io.Serializable
{
	public long WaterID;
	public String Mobile = "";
	public String UMID = "";
	public String MsgContent = "";
	public String SendUri = "";
	public String SendResult = "";
	public String SendStatus = "";
	public java.sql.Date SendDate;
	public java.sql.Timestamp SendTime;
	public String PackageID = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}