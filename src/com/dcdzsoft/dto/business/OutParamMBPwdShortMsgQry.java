package com.dcdzsoft.dto.business;

/**
* 取件密码短消息查询
*/

public class OutParamMBPwdShortMsgQry implements java.io.Serializable
{
	public long WaterID; //流水号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String PackageID = ""; //订单号
	public String CustomerMobile = ""; //取件人手机
	public String CustomerName = ""; //取件人姓名
	public String OpenBoxKey = ""; //开箱密码
	public String SendStatus = ""; //发送状态
	public String SendStatusName = ""; //发送状态名称
	public java.sql.Timestamp LastModifyTime; //最后修改时间
	public int ReSendNum; //重发次数
	public String PackageStatus = ""; //包裹状态
	public String PackageStatusName = ""; //包裹状态名称
	public String Remark = ""; //备注

	public long getWaterID()
	{
		return WaterID;
	}
	public void setWaterID(long WaterID)
	{
		this.WaterID = WaterID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getCustomerName()
	{
		return CustomerName;
	}
	public void setCustomerName(String CustomerName)
	{
		this.CustomerName = CustomerName;
	}

	public String getOpenBoxKey()
	{
		return OpenBoxKey;
	}
	public void setOpenBoxKey(String OpenBoxKey)
	{
		this.OpenBoxKey = OpenBoxKey;
	}

	public String getSendStatus()
	{
		return SendStatus;
	}
	public void setSendStatus(String SendStatus)
	{
		this.SendStatus = SendStatus;
	}

	public String getSendStatusName()
	{
		return SendStatusName;
	}
	public void setSendStatusName(String SendStatusName)
	{
		this.SendStatusName = SendStatusName;
	}

	public java.sql.Timestamp getLastModifyTime()
	{
		return LastModifyTime;
	}
	public void setLastModifyTime(java.sql.Timestamp LastModifyTime)
	{
		this.LastModifyTime = LastModifyTime;
	}

	public int getReSendNum()
	{
		return ReSendNum;
	}
	public void setReSendNum(int ReSendNum)
	{
		this.ReSendNum = ReSendNum;
	}

	public String getPackageStatus()
	{
		return PackageStatus;
	}
	public void setPackageStatus(String PackageStatus)
	{
		this.PackageStatus = PackageStatus;
	}

	public String getPackageStatusName()
	{
		return PackageStatusName;
	}
	public void setPackageStatusName(String PackageStatusName)
	{
		this.PackageStatusName = PackageStatusName;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}