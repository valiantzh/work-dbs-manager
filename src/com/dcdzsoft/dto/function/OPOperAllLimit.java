package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OPOperAllLimit implements java.io.Serializable
{
	public String OperID = "";
	public int LimitTypeID;
	public String LimitObject = "";
	public String LimitObjectName = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}