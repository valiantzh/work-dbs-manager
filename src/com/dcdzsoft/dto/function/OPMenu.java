package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OPMenu implements java.io.Serializable
{
	public String MenuID = "";
	public String MenuName = "";
	public int MenuLevel;
	public String MenuEngName = "";
	public String CorpShortName = "";
	public String Description = "";
	public String Action = "";
	public int HotKey;
	public String Icon = "";
	public String HelpContext = "";
	public int MenuType;
	public int LeafFlag;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}