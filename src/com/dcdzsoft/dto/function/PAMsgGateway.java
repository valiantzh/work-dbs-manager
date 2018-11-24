package com.dcdzsoft.dto.function;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PAMsgGateway implements java.io.Serializable
{
	public String GatewayID = "";
	public String GatewayName = "";
	public String GatewayType = "";
	public String GatewayStatus = "";
	public String GatewayUrl = "";
	public int GatewayPort;
	public String UserID = "";
	public String UserPwd = "";
	public String UserName = "";
	public String GatewayToken = "";
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