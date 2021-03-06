package com.dcdzsoft.dto.business;

/**
* 设备安装初始化
*/

public class InParamMBInitialization implements java.io.Serializable
{
	public String FunctionID = "150401"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String SignIP = ""; //签到终端IP
	public String SignMac = ""; //签到终端MAC
	public String SoftwareVersion = ""; //软件版本号
	public String InitPasswd = ""; //安装密码
	public String RegisterFlag = ""; //注册标志
	public String TerminalInfo = ""; //柜体信息
	public String BoxInfo = ""; //箱体信息

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150401";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150401";
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

	public String getSignIP()
	{
		return SignIP;
	}
	public void setSignIP(String SignIP)
	{
		this.SignIP = SignIP;
	}

	public String getSignMac()
	{
		return SignMac;
	}
	public void setSignMac(String SignMac)
	{
		this.SignMac = SignMac;
	}

	public String getSoftwareVersion()
	{
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String SoftwareVersion)
	{
		this.SoftwareVersion = SoftwareVersion;
	}

	public String getInitPasswd()
	{
		return InitPasswd;
	}
	public void setInitPasswd(String InitPasswd)
	{
		this.InitPasswd = InitPasswd;
	}

	public String getRegisterFlag()
	{
		return RegisterFlag;
	}
	public void setRegisterFlag(String RegisterFlag)
	{
		this.RegisterFlag = RegisterFlag;
	}

	public String getTerminalInfo()
	{
		return TerminalInfo;
	}
	public void setTerminalInfo(String TerminalInfo)
	{
		this.TerminalInfo = TerminalInfo;
	}

	public String getBoxInfo()
	{
		return BoxInfo;
	}
	public void setBoxInfo(String BoxInfo)
	{
		this.BoxInfo = BoxInfo;
	}

}