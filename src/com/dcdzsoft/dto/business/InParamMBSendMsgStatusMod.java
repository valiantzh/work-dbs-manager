package com.dcdzsoft.dto.business;

/**
* 更新消息发送状态
*/

public class InParamMBSendMsgStatusMod implements java.io.Serializable
{
	public String FunctionID = "150161"; //功能编号

	public String OperID = ""; //管理员编号
	public String Mobile = ""; //手机号
	public String UMID = ""; //消息ID
	public String MsgContent = ""; //消息内容
	public String SendUri = ""; //发送地址
	public String SendResult = ""; //发送结果
	public String SendStatus = ""; //发送状态
	public java.sql.Timestamp SendTime; //发送时间
	public String PackageID = ""; //订单号
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150161";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150161";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
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

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}