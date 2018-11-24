package com.dcdzsoft.dto.business;

/**
* 现场管理员登录
*/

public class InParamMBSpotAdminLogin implements java.io.Serializable
{
	public String FunctionID = "150444"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String SpotAdminID = ""; //现场管理员编号
	public String SpotAdminPwd = ""; //现场管理员密码

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150444";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150444";
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

	public String getSpotAdminID()
	{
		return SpotAdminID;
	}
	public void setSpotAdminID(String SpotAdminID)
	{
		this.SpotAdminID = SpotAdminID;
	}

	public String getSpotAdminPwd()
	{
		return SpotAdminPwd;
	}
	public void setSpotAdminPwd(String SpotAdminPwd)
	{
		this.SpotAdminPwd = SpotAdminPwd;
	}

}