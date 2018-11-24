package com.dcdzsoft.dto.business;

/**
* 会员注册
*/

public class InParamCMCustomerRegister implements java.io.Serializable
{
	public String FunctionID = "170111"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String CustomerMobile = ""; //手机
	public String BindCardID = ""; //绑定卡号
	public String CustomerName = ""; //会员名称
	public String CheckCode = ""; //验证码
	public String Password = ""; //登陆密码

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "170111";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "170111";
		else
			this.FunctionID = FunctionID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getCustomerMobile()
	{
		return CustomerMobile;
	}
	public void setCustomerMobile(String CustomerMobile)
	{
		this.CustomerMobile = CustomerMobile;
	}

	public String getBindCardID()
	{
		return BindCardID;
	}
	public void setBindCardID(String BindCardID)
	{
		this.BindCardID = BindCardID;
	}

	public String getCustomerName()
	{
		return CustomerName;
	}
	public void setCustomerName(String CustomerName)
	{
		this.CustomerName = CustomerName;
	}

	public String getCheckCode()
	{
		return CheckCode;
	}
	public void setCheckCode(String CheckCode)
	{
		this.CheckCode = CheckCode;
	}

	public String getPassword()
	{
		return Password;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}

}