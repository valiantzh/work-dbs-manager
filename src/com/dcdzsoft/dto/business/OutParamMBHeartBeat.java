package com.dcdzsoft.dto.business;

/**
* 设备心跳包检测
*/

public class OutParamMBHeartBeat implements java.io.Serializable
{
	public java.sql.Timestamp ServerTime; //服务器时间
	public String SignKey = ""; //签到生成密钥
	public int BeatInterval; //心跳间隔
	public String NextCommand = ""; //指令代码

	public java.sql.Timestamp getServerTime()
	{
		return ServerTime;
	}
	public void setServerTime(java.sql.Timestamp ServerTime)
	{
		this.ServerTime = ServerTime;
	}

	public String getSignKey()
	{
		return SignKey;
	}
	public void setSignKey(String SignKey)
	{
		this.SignKey = SignKey;
	}

	public int getBeatInterval()
	{
		return BeatInterval;
	}
	public void setBeatInterval(int BeatInterval)
	{
		this.BeatInterval = BeatInterval;
	}

	public String getNextCommand()
	{
		return NextCommand;
	}
	public void setNextCommand(String NextCommand)
	{
		this.NextCommand = NextCommand;
	}

}