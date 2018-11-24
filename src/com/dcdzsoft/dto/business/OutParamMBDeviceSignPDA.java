package com.dcdzsoft.dto.business;

/**
* PDA设备签到
*/

public class OutParamMBDeviceSignPDA implements java.io.Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String RegisterFlag = ""; //PDA注册标志
	public String SoftwareVersion = ""; //软件版本号
	public String SignKey = ""; //签到生成密钥
	public java.sql.Timestamp ServerTime; //服务器时间

	public String getRegisterFlag()
	{
		return RegisterFlag;
	}
	public void setRegisterFlag(String RegisterFlag)
	{
		this.RegisterFlag = RegisterFlag;
	}

	public String getSoftwareVersion()
	{
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String SoftwareVersion)
	{
		this.SoftwareVersion = SoftwareVersion;
	}

	public String getSignKey()
	{
		return SignKey;
	}
	public void setSignKey(String SignKey)
	{
		this.SignKey = SignKey;
	}

	public java.sql.Timestamp getServerTime()
	{
		return ServerTime;
	}
	public void setServerTime(java.sql.Timestamp ServerTime)
	{
		this.ServerTime = ServerTime;
	}
}