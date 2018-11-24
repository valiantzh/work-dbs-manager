package com.dcdzsoft.dto.business;

/**
* 短信账户充值取消
*/

public class InParamPYSMSAccountTopupCancel implements java.io.Serializable
{
	public String FunctionID = "410053"; //功能编号

	public String OperID = ""; //管理员编号
	public String AccountID = ""; //充值账户编号
	public String BillWaterID = ""; //账单流水号
	public String OperPassword = ""; //登录用户密码
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410053";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410053";
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

	public String getBillWaterID()
	{
		return BillWaterID;
	}
	public void setBillWaterID(String BillWaterID)
	{
		this.BillWaterID = BillWaterID;
	}

	public String getOperPassword()
	{
		return OperPassword;
	}
	public void setOperPassword(String OperPassword)
	{
		this.OperPassword = OperPassword;
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