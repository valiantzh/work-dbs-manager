package com.dcdzsoft.dto.business;

/**
* 查询短信服务账号信息
*/

public class OutParamPYSMSAccountQry implements java.io.Serializable
{
	public String AccountID = ""; //短信充值账户编号
	public String AccountName = ""; //短信充值账户名称
	public String AccountStatus = ""; //账户状态
	public String AccountStatusName = ""; //账户状态
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String Email = ""; //邮箱
	public String Phone = ""; //电话
	public String ContactPerson = ""; //联系人
	public String Ways = ""; //计费方式（1-按条计费;
	public String WaysName = ""; //计费方式（1-按条计费;
	public long TotalSMSNum; //短信总条数
	public int SMSCreditLimit; //短信额度
	public long LastSMSNum; //已使用的短信条数
	public long LeftSMSNum; //可用短信数
	public java.sql.Date EffectiveDate; //生效日期
	public java.sql.Date ExpirationDate; //失效日期
	public int WarningLimit; //短信预警阀值
	public String WarningFlag = ""; //预警通知标志
	public String WarningFlagName = ""; //预警通知标志
	public java.sql.Timestamp LastModifyTime; //最后修改时间
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

	public String getAccountStatus()
	{
		return AccountStatus;
	}
	public void setAccountStatus(String AccountStatus)
	{
		this.AccountStatus = AccountStatus;
	}

	public String getAccountStatusName()
	{
		return AccountStatusName;
	}
	public void setAccountStatusName(String AccountStatusName)
	{
		this.AccountStatusName = AccountStatusName;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
	}

	public String getEmail()
	{
		return Email;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}

	public String getPhone()
	{
		return Phone;
	}
	public void setPhone(String Phone)
	{
		this.Phone = Phone;
	}

	public String getContactPerson()
	{
		return ContactPerson;
	}
	public void setContactPerson(String ContactPerson)
	{
		this.ContactPerson = ContactPerson;
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

	public long getTotalSMSNum()
	{
		return TotalSMSNum;
	}
	public void setTotalSMSNum(long TotalSMSNum)
	{
		this.TotalSMSNum = TotalSMSNum;
	}

	public int getSMSCreditLimit()
	{
		return SMSCreditLimit;
	}
	public void setSMSCreditLimit(int SMSCreditLimit)
	{
		this.SMSCreditLimit = SMSCreditLimit;
	}

	public long getLastSMSNum()
	{
		return LastSMSNum;
	}
	public void setLastSMSNum(long LastSMSNum)
	{
		this.LastSMSNum = LastSMSNum;
	}

	public long getLeftSMSNum()
	{
		return LeftSMSNum;
	}
	public void setLeftSMSNum(long LeftSMSNum)
	{
		this.LeftSMSNum = LeftSMSNum;
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

	public String getWarningFlagName()
	{
		return WarningFlagName;
	}
	public void setWarningFlagName(String WarningFlagName)
	{
		this.WarningFlagName = WarningFlagName;
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