package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OPFunction implements java.io.Serializable
{
	public String FunctionID = "";
	public String FunctionName = "";
	public String FunctionServiceName = "";
	public String LogFlag = "";
	public String OpenFlag = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}