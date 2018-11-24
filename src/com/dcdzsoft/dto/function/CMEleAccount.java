package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CMEleAccount implements java.io.Serializable
{
	public String AccountID = "";
	public String CustomerID = "";
	public String AccountName = "";
	public String AccountType = "";
	public String UserName = "";
	public String Password = "";
	public String UnionID = "";
	public String BindCardID = "";
	public String BindMobile = "";
	public String Email = "";
	public String ActiveFlag = "";
	public String ActiveCode = "";
	public java.sql.Date Validity;
	public String NickName = "";
	public String Language = "";
	public String HeadImgUrl = "";
	public java.sql.Date CustomerBirthday;
	public String CustomerSex = "";
	public String Province = "";
	public String City = "";
	public String Country = "";
	public String Area = "";
	public int Balance;
	public String TerminalNo = "";
	public java.sql.Timestamp LastModifyTime;
	public String ExtendFields = "";
	public String Remark = "";
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}