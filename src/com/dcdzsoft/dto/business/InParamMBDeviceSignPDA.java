package com.dcdzsoft.dto.business;

/**
* PDA设备签到
*/

public class InParamMBDeviceSignPDA implements java.io.Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "150313"; //功能编号

	public String PDANo = ""; //PDA编号
	public String PDAName = "";//PDA名称
	public String DeviceID = ""; //设备识别号
	public String SIMCard = ""; //SIM卡号
	public String Mobile = ""; //手机号
	public String SoftwareVersion = ""; //软件版本号
	public String InitPasswd = ""; //安装密码
	public String RegisterFlag = ""; //PDA注册标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150313";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150313";
		else
			this.FunctionID = FunctionID;
	}

	public String getPDANo()
	{
		return PDANo;
	}
	public void setPDANo(String PDANo)
	{
		this.PDANo = PDANo;
	}

	public String getDeviceID()
	{
		return DeviceID;
	}
	public void setDeviceID(String DeviceID)
	{
		this.DeviceID = DeviceID;
	}

	public String getSIMCard()
	{
		return SIMCard;
	}
	public void setSIMCard(String SIMCard)
	{
		this.SIMCard = SIMCard;
	}

	public String getMobile()
	{
		return Mobile;
	}
	public void setMobile(String Mobile)
	{
		this.Mobile = Mobile;
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
}