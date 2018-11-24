package com.dcdzsoft.dto.business;

/**
* 用户验证-投递账号验证
*/

public class InParamPTVerifyDeliver implements java.io.Serializable
{
	public String FunctionID = "330251"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String LoginPwd = ""; //登录密码
	public String LoginType = ""; //登录类型

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330251";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330251";
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

	public String getLoginPwd()
	{
		return LoginPwd;
	}
	public void setLoginPwd(String LoginPwd)
	{
		this.LoginPwd = LoginPwd;
	}

	public String getLoginType()
	{
		return LoginType;
	}
	public void setLoginType(String LoginType)
	{
		this.LoginType = LoginType;
	}

}