package com.dcdzsoft.dto.business;

/**
* 同步短信发送信息
*/

public class OutParamSCSyncShortMsgInfo implements java.io.Serializable
{
	public String waterId = ""; //流水号
	public String packageId = ""; //订单号
	public String terminalNo = ""; //设备号
	public java.sql.Timestamp storedTime; //存物时间
	public String customerMobile = ""; //取件人手机
	public String packageStatus = ""; //包裹状态
	public String openBoxKey = ""; //开箱密码
	public java.sql.Timestamp updateTime; //最后修改时间
	public String manufacturer = ""; //厂商标识

	public String getwaterId()
	{
		return waterId;
	}
	public void setwaterId(String waterId)
	{
		this.waterId = waterId;
	}

	public String getpackageId()
	{
		return packageId;
	}
	public void setpackageId(String packageId)
	{
		this.packageId = packageId;
	}

	public String getterminalNo()
	{
		return terminalNo;
	}
	public void setterminalNo(String terminalNo)
	{
		this.terminalNo = terminalNo;
	}

	public java.sql.Timestamp getstoredTime()
	{
		return storedTime;
	}
	public void setstoredTime(java.sql.Timestamp storedTime)
	{
		this.storedTime = storedTime;
	}

	public String getcustomerMobile()
	{
		return customerMobile;
	}
	public void setcustomerMobile(String customerMobile)
	{
		this.customerMobile = customerMobile;
	}

	public String getopenBoxKey()
	{
		return openBoxKey;
	}
	public void setopenBoxKey(String openBoxKey)
	{
		this.openBoxKey = openBoxKey;
	}
	
	public String gepackageStatus()
	{
		return packageStatus;
	}
	public void setpackageStatus(String packageStatus)
	{
		this.packageStatus = packageStatus;
	}

	public java.sql.Timestamp getupdateTime()
	{
		return updateTime;
	}
	public void setupdateTime(java.sql.Timestamp updateTime)
	{
		this.updateTime = updateTime;
	}

	public String getmanufacturer()
	{
		return manufacturer;
	}
	public void setmanufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}
}