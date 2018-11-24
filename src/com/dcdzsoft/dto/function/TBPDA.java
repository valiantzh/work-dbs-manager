package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TBPDA implements java.io.Serializable
{
	public String PDANo = "";
	public String TerminalNo = "";
	public String PDAName = "";
	public String DeviceID = "";
	public String RegisterFlag = "";
	public String SIMCard = "";
	public String MobileNum = "";
	public String PDAStatus = "";
	public java.sql.Timestamp UpdateTime;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}