package com.dcdzsoft.dto.business;

/**
* 设备签到预处理
*/

public class OutParamMBDeviceSign0 implements java.io.Serializable
{
	public String SoftwareVersion = ""; //软件版本号
	public String SignKey = ""; //签到生成密钥
	public java.sql.Timestamp ServerTime; //服务器时间
	public String RegisterFlag = ""; //注册标志
	public String TerminalName = ""; //设备名称
	public String MBDeviceNo = ""; //运营商设备编号
	public String OfBureau = ""; //所属投递局
	public String InitPasswd = ""; //安装密码
	public String UpdateFlag1 = ""; //柜体更新标志
	public String UpdateFlag2 = ""; //箱体更新标志

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

	public String getRegisterFlag()
	{
		return RegisterFlag;
	}
	public void setRegisterFlag(String RegisterFlag)
	{
		this.RegisterFlag = RegisterFlag;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getMBDeviceNo()
	{
		return MBDeviceNo;
	}
	public void setMBDeviceNo(String MBDeviceNo)
	{
		this.MBDeviceNo = MBDeviceNo;
	}

	public String getOfBureau()
	{
		return OfBureau;
	}
	public void setOfBureau(String OfBureau)
	{
		this.OfBureau = OfBureau;
	}

	public String getInitPasswd()
	{
		return InitPasswd;
	}
	public void setInitPasswd(String InitPasswd)
	{
		this.InitPasswd = InitPasswd;
	}

	public String getUpdateFlag1()
	{
		return UpdateFlag1;
	}
	public void setUpdateFlag1(String UpdateFlag1)
	{
		this.UpdateFlag1 = UpdateFlag1;
	}

	public String getUpdateFlag2()
	{
		return UpdateFlag2;
	}
	public void setUpdateFlag2(String UpdateFlag2)
	{
		this.UpdateFlag2 = UpdateFlag2;
	}

}