package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MBDepartment implements java.io.Serializable
{
	public String DepartmentID = "";
	public String DepartmentName = "";
	public String Area = "";
	public String City = "";
	public String Province = "";
	public String Address = "";
	public String Zip = "";
	public String OfficeTel = "";
	public String ContactPerson = "";
	public String ContactTel = "";
	public String ParentDepartID = "";
	public int DepartLevel;
	public int LeafFlag;
	public String Level1 = "";
	public String Level2 = "";
	public String Level3 = "";
	public String Level4 = "";
	public String Level5 = "";
	public String Level6 = "";
	public String WelcomeInfo = "";
	public java.sql.Timestamp CreateTime;
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}