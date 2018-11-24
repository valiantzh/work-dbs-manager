package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PADictionary implements java.io.Serializable
{
	public int DictTypeID;
	public String DictTypeName = "";
	public String DictID = "";
	public String DictName = "";
	public String OtherDictName = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}