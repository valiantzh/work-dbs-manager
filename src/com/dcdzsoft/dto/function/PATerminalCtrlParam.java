package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PATerminalCtrlParam implements java.io.Serializable
{
	public String TerminalNo = "";
	public int CtrlTypeID;
	public String KeyString = "";
	public String CtrlTypeName = "";
	public String DefaultValue = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}