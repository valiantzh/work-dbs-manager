package com.dcdzsoft.dto.business;

/**
* 查询短信账单流水信息
*/

public class OutParamPYSMSBillWaterQry implements java.io.Serializable
{
	public String AccountID = ""; //短信充值账户编号
	public String AccountName = ""; //短信充值账户名称
	public String BillWaterID = ""; //账单流水号
	public java.sql.Timestamp BillTime; //账单日期
	public String BillType = ""; //账单类型：1-充值；2-消费
	public String BillTypeName = ""; //账单类型：1-充值；2-消费
	public String Ways = ""; //计费方式（1-按条计费;
	public String WaysName = ""; //计费方式（1-按条计费;
	public double Amount; //金额
	public long BillNum; //充值数量
	public String Units = ""; //计费单位（1-次;
	public String UnitsName = ""; //计费单位（1-次;
	public long SMSNum; //短信数量
	public long SMSBalance; //短信余量
	public String ServiceID = ""; //服务编号
	public String ServiceName = ""; //服务名称
	public String Remark = ""; //备注

	public String getAccountID()
	{
		return AccountID;
	}
	public void setAccountID(String AccountID)
	{
		this.AccountID = AccountID;
	}

	public String getAccountName()
	{
		return AccountName;
	}
	public void setAccountName(String AccountName)
	{
		this.AccountName = AccountName;
	}

	public String getBillWaterID()
	{
		return BillWaterID;
	}
	public void setBillWaterID(String BillWaterID)
	{
		this.BillWaterID = BillWaterID;
	}

	public java.sql.Timestamp getBillTime()
	{
		return BillTime;
	}
	public void setBillTime(java.sql.Timestamp BillTime)
	{
		this.BillTime = BillTime;
	}

	public String getBillType()
	{
		return BillType;
	}
	public void setBillType(String BillType)
	{
		this.BillType = BillType;
	}

	public String getBillTypeName()
	{
		return BillTypeName;
	}
	public void setBillTypeName(String BillTypeName)
	{
		this.BillTypeName = BillTypeName;
	}

	public String getWays()
	{
		return Ways;
	}
	public void setWays(String Ways)
	{
		this.Ways = Ways;
	}

	public String getWaysName()
	{
		return WaysName;
	}
	public void setWaysName(String WaysName)
	{
		this.WaysName = WaysName;
	}

	public double getAmount()
	{
		return Amount;
	}
	public void setAmount(double Amount)
	{
		this.Amount = Amount;
	}

	public long getBillNum()
	{
		return BillNum;
	}
	public void setBillNum(long BillNum)
	{
		this.BillNum = BillNum;
	}

	public String getUnits()
	{
		return Units;
	}
	public void setUnits(String Units)
	{
		this.Units = Units;
	}

	public String getUnitsName()
	{
		return UnitsName;
	}
	public void setUnitsName(String UnitsName)
	{
		this.UnitsName = UnitsName;
	}

	public long getSMSNum()
	{
		return SMSNum;
	}
	public void setSMSNum(long SMSNum)
	{
		this.SMSNum = SMSNum;
	}

	public long getSMSBalance()
	{
		return SMSBalance;
	}
	public void setSMSBalance(long SMSBalance)
	{
		this.SMSBalance = SMSBalance;
	}

	public String getServiceID()
	{
		return ServiceID;
	}
	public void setServiceID(String ServiceID)
	{
		this.ServiceID = ServiceID;
	}

	public String getServiceName()
	{
		return ServiceName;
	}
	public void setServiceName(String ServiceName)
	{
		this.ServiceName = ServiceName;
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