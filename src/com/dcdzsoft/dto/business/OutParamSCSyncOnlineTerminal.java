package com.dcdzsoft.dto.business;

/**
* 同步在线终端信息
*/

public class OutParamSCSyncOnlineTerminal implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public int BoxNum; //箱总数量
	public int FreeBoxNum; //可用箱数量
	public String OnlineStatus = ""; //在线状态
	public String OnlineStatusName = ""; //在线状态名称
	public String Remark = ""; //备注

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public int getBoxNum()
	{
		return BoxNum;
	}
	public void setBoxNum(int BoxNum)
	{
		this.BoxNum = BoxNum;
	}

	public int getFreeBoxNum()
	{
		return FreeBoxNum;
	}
	public void setFreeBoxNum(int FreeBoxNum)
	{
		this.FreeBoxNum = FreeBoxNum;
	}

	public String getOnlineStatus()
	{
		return OnlineStatus;
	}
	public void setOnlineStatus(String OnlineStatus)
	{
		this.OnlineStatus = OnlineStatus;
	}

	public String getOnlineStatusName()
	{
		return OnlineStatusName;
	}
	public void setOnlineStatusName(String OnlineStatusName)
	{
		this.OnlineStatusName = OnlineStatusName;
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