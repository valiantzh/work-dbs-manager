package com.dcdzsoft.dto.business;

/**
* 查询会员卡信息
*/

public class OutParamPYCustomerCardQry implements java.io.Serializable
{
	public String CardNo = ""; //会员卡编号
	public String CardType = ""; //会员卡类型
	public String CardTypeName = ""; //会员卡类型
	public String CardStatus = ""; //会员卡状态
	public String CardStatusName = ""; //会员卡状态
	public int Balance; //会员卡余额
	public String BalanceUnit = ""; //余额单位
	public String BalanceUnitName = ""; //余额单位
	public int CreditLimit; //信用额度
	public java.sql.Date EffectiveDate; //生效日期
	public java.sql.Date ExpirationDate; //失效日期
	public int WarningLimit; //余额预警值
	public String WarningFlag = ""; //预警通知标志
	public int Discount; //折扣(%)
	public String CustomerID = ""; //会员编号
	public String CustomerName = ""; //会员名称
	public String CustomerMobile = ""; //会员手机
	public String CustomerType = ""; //会员类型（99-投递员）
	public java.sql.Timestamp CreateTime; //创建时间
	public java.sql.Timestamp LastModifyTime; //最后修改时间
	public String Remark = ""; //备注

	public String getCardNo()
	{
		return CardNo;
	}
	public void setCardNo(String CardNo)
	{
		this.CardNo = CardNo;
	}

	public String getCardType()
	{
		return CardType;
	}
	public void setCardType(String CardType)
	{
		this.CardType = CardType;
	}

	public String getCardTypeName()
	{
		return CardTypeName;
	}
	public void setCardTypeName(String CardTypeName)
	{
		this.CardTypeName = CardTypeName;
	}

	public String getCardStatus()
	{
		return CardStatus;
	}
	public void setCardStatus(String CardStatus)
	{
		this.CardStatus = CardStatus;
	}

	public String getCardStatusName()
	{
		return CardStatusName;
	}
	public void setCardStatusName(String CardStatusName)
	{
		this.CardStatusName = CardStatusName;
	}

	public int getBalance()
	{
		return Balance;
	}
	public void setBalance(int Balance)
	{
		this.Balance = Balance;
	}

	public String getBalanceUnit()
	{
		return BalanceUnit;
	}
	public void setBalanceUnit(String BalanceUnit)
	{
		this.BalanceUnit = BalanceUnit;
	}

	public String getBalanceUnitName()
	{
		return BalanceUnitName;
	}
	public void setBalanceUnitName(String BalanceUnitName)
	{
		this.BalanceUnitName = BalanceUnitName;
	}

	public int getCreditLimit()
	{
		return CreditLimit;
	}
	public void setCreditLimit(int CreditLimit)
	{
		this.CreditLimit = CreditLimit;
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

	public int getWarningLimit()
	{
		return WarningLimit;
	}
	public void setWarningLimit(int WarningLimit)
	{
		this.WarningLimit = WarningLimit;
	}

	public String getWarningFlag()
	{
		return WarningFlag;
	}
	public void setWarningFlag(String WarningFlag)
	{
		this.WarningFlag = WarningFlag;
	}

	public int getDiscount()
	{
		return Discount;
	}
	public void setDiscount(int Discount)
	{
		this.Discount = Discount;
	}

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getCustomerName()
	{
		return CustomerName;
	}
	public void setCustomerName(String CustomerName)
	{
		this.CustomerName = CustomerName;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

	public java.sql.Timestamp getCreateTime()
	{
		return CreateTime;
	}
	public void setCreateTime(java.sql.Timestamp CreateTime)
	{
		this.CreateTime = CreateTime;
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