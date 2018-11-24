package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PMCompany implements java.io.Serializable
{
	public String CompanyID = "";
	public String LogisticsID = "";
	public String DepartmentID = "";
	public String CompanyName = "";
	public String PinyinCode = "";
	public String OfficeTel = "";
	public String ContactPerson = "";
	public String ContactTel = "";
	public String Province = "";
	public String City = "";
	public String Zip = "";
	public String Address = "";
	public java.sql.Timestamp CreateTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}