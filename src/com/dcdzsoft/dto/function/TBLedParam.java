package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TBLedParam implements java.io.Serializable
{
	public String TerminalNo = "";
	public String LedMsg = "";
	public int StartPointX;
	public int StartPointY;
	public int LedWidth;
	public int LedHeight;
	public int DisplayWay;
	public int QuitWay;
	public int RunSpeed;
	public int StopTime;
	public int FontSize;
	public int FontColor;
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}