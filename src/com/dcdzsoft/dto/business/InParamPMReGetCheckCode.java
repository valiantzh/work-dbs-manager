package com.dcdzsoft.dto.business;

/**
* 重新获取验证码
*/

public class InParamPMReGetCheckCode implements java.io.Serializable
{
	public String FunctionID = "311012"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String Mobile = ""; //手机
	public String ModPwdFlag = "1";//1-修改密码；其他-注册

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "311012";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "311012";
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

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
	}
    public String getModPwdFlag() {
        return ModPwdFlag;
    }
    public void setModPwdFlag(String modPwdFlag) {
        ModPwdFlag = modPwdFlag;
    }

}