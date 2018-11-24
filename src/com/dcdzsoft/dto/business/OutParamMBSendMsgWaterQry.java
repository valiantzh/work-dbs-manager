package com.dcdzsoft.dto.business;

/**
* 消息发送流水查询
*/

public class OutParamMBSendMsgWaterQry implements java.io.Serializable
{
	public long WaterID; //流水号
	public String Mobile = ""; //手机号
	public String UMID = ""; //消息ID
	public String MsgContent = ""; //消息内容
	public String SendUri = ""; //发送地址
	public String SendResult = ""; //发送结果
	public String SendStatus = ""; //发送状态
	public String SendStatusName = ""; //发送状态名称
	public java.sql.Date SendDate; //发送日期
	public java.sql.Timestamp SendTime; //发送时间
	public String PackageID = ""; //订单号
	public java.sql.Timestamp LastModifyTime; //最后修改时间
	public String Remark = ""; //备注

	public long getWaterID()
	{
		return WaterID;
	}
	public void setWaterID(long WaterID)
	{
		this.WaterID = WaterID;
	}

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
	}

	public String getUMID()
	{
		return UMID;
	}
	public void setUMID(String UMID)
	{
		this.UMID = UMID;
	}

	public String getMsgContent()
	{
		return MsgContent;
	}
	public void setMsgContent(String MsgContent)
	{
		this.MsgContent = MsgContent;
	}

	public String getSendUri()
	{
		return SendUri;
	}
	public void setSendUri(String SendUri)
	{
		this.SendUri = SendUri;
	}

	public String getSendResult()
	{
		return SendResult;
	}
	public void setSendResult(String SendResult)
	{
		this.SendResult = SendResult;
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

	public java.sql.Date getSendDate()
	{
		return SendDate;
	}
	public void setSendDate(java.sql.Date SendDate)
	{
		this.SendDate = SendDate;
	}

	public java.sql.Timestamp getSendTime()
	{
		return SendTime;
	}
	public void setSendTime(java.sql.Timestamp SendTime)
	{
		this.SendTime = SendTime;
	}

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public java.sql.Timestamp getLastModifyTime()
	{
		return LastModifyTime;
	}
	public void setLastModifyTime(java.sql.Timestamp LastModifyTime)
	{
		this.LastModifyTime = LastModifyTime;
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