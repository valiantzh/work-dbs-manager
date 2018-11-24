package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OPRoleAllLimit implements java.io.Serializable
{
	public int RoleID;
	public int LimitTypeID;
	public String LimitObject = "";
	public String LimitObjectName = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}