package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PAMsgTemplate implements java.io.Serializable
{
	public String TemplateID = "";
	public String TemplateType = "";
	public String TemplateName = "";
	public String TemplateContent = "";
	public String TemplateStatus = "";
	public String ReserveField1 = "";
	public String ReserveField2 = "";
	public String ReserveField3 = "";
	public String ReserveField4 = "";
	public java.sql.Timestamp LastModifyTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}