package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PASequence implements java.io.Serializable
{
	public String SeqName = "";
	public long SeqValue;
	public int CacheSize;
	public int SeqMaxValue;
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}