package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SMSystemInfo implements java.io.Serializable
{
	public String SystemID = "";
	public String SoftwareVersion = "";
	public String UpdateContent = "";
	public String InitPasswd = "";
	public String LastInitPasswd = "";
	public String TerminalPasswd = "";
	public String ServerIP = "";
	public int ServerPort;
	public String ServerSSL = "";
	public String MonServerIP = "";
	public int MonServerPort;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}