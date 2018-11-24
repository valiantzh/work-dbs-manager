package com.dcdzsoft.dto.business;

/**
* 短信账户充值
*/

public class InParamPYSMSAccountTopup implements java.io.Serializable
{
	public String FunctionID = "410051"; //功能编号

	public String OperID = ""; //管理员编号
	public String AccountID = ""; //充值账户编号
	public String ServiceID = ""; //服务编号
	public String Ways = ""; //计费方式
	public double Amount; //金额
	public long SMSNum; //短信数量
	public long BillNum; //充值数量
	public java.sql.Date EffectiveDate; //生效日期
	public java.sql.Date ExpirationDate; //失效日期
	public int SMSCreditLimit; //短信额度
	public int WarningLimit; //短信预警值
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410051";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410051";
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

	public String getAccountID()
	{
		return AccountID;
	}
	public void setAccountID(String AccountID)
	{
		this.AccountID = AccountID;
	}

	public String getServiceID()
	{
		return ServiceID;
	}
	public void setServiceID(String ServiceID)
	{
		this.ServiceID = ServiceID;
	}

	public String getWays()
	{
		return Ways;
	}
	public void setWays(String Ways)
	{
		this.Ways = Ways;
	}

	public double getAmount()
	{
		return Amount;
	}
	public void setAmount(double Amount)
	{
		this.Amount = Amount;
	}

	public long getSMSNum()
	{
		return SMSNum;
	}
	public void setSMSNum(long SMSNum)
	{
		this.SMSNum = SMSNum;
	}

	public long getBillNum()
	{
		return BillNum;
	}
	public void setBillNum(long BillNum)
	{
		this.BillNum = BillNum;
	}

	public java.sql.Date getEffectiveDate()
	{
		return EffectiveDate;
	}
	public void setEffectiveDate(java.sql.Date EffectiveDate)
	{
		this.EffectiveDate = EffectiveDate;
	}

	public java.sql.Date getExpirationDate()
	{
		return ExpirationDate;
	}
	public void setExpirationDate(java.sql.Date ExpirationDate)
	{
		this.ExpirationDate = ExpirationDate;
	}

	public int getSMSCreditLimit()
	{
		return SMSCreditLimit;
	}
	public void setSMSCreditLimit(int SMSCreditLimit)
	{
		this.SMSCreditLimit = SMSCreditLimit;
	}

	public int getWarningLimit()
	{
		return WarningLimit;
	}
	public void setWarningLimit(int WarningLimit)
	{
		this.WarningLimit = WarningLimit;
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