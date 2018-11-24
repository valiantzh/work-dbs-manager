package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBTerminalStatusLog implements java.io.Serializable
{
	public String TerminalNo = "";
	public String TerminalStatus = "";
	public String CardReaderStatus = "";
	public String ICStatus = "";
	public String IDCardStatus = "";
	public String BarcodeStatus = "";
	public String PowerStatus = "";
	public String PasskeyBoardStatus = "";
	public String PrinterStatus = "";
	public String Camera1Status = "";
	public String Camera2Status = "";
	public String Camera3Status = "";
	public java.sql.Timestamp TerminalTime;
	public String SoftwareVersion = "";
	public java.sql.Date LogDate;
	public java.sql.Timestamp LogTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}