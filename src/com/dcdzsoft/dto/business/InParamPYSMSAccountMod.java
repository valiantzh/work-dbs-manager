package com.dcdzsoft.dto.business;

/**
* 修改短信服务账号信息
*/

public class InParamPYSMSAccountMod implements java.io.Serializable
{
	public String FunctionID = "410022"; //功能编号

	public String OperID = ""; //管理员编号
	public String AccountID = ""; //充值账户编号
	public String AccountName = ""; //充值账户名称
	public String AccountStatus = ""; //账户状态
	public String DepartmentID = ""; //运营网点编号
	public String Email = ""; //邮箱
	public String Phone = ""; //电话
	public String ContactPerson = ""; //联系人
	public int SMSCreditLimit; //短信额度
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410022";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410022";
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

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
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

	public int getSMSCreditLimit()
	{
		return SMSCreditLimit;
	}
	public void setSMSCreditLimit(int SMSCreditLimit)
	{
		this.SMSCreditLimit = SMSCreditLimit;
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