package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TBTerminal implements java.io.Serializable
{
	public String TerminalNo = "";
	public String DepartmentID = "";
	public String TerminalName = "";
	public String MBDeviceNo = "";
	public int DeskNum;
	public int BoxNum;
	public String MacAddr = "";
	public String Brand = "";
	public String Model = "";
	public String Location = "";
	public String Latlon = "";
	public double Longitude;
	public double Latitude;
	public String OfBureau = "";
	public String OfSegment = "";
	public String MachineSize = "";
	public int MainDeskAddress;
	public String RegisterFlag = "";
	public String TerminalStatus = "";
	public java.sql.Timestamp CreateTime;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}