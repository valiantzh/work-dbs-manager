package com.dcdzsoft.dto.business;

/**
* 同步在线终端信息
*/

public class InParamSCSyncOnlineTerminal implements java.io.Serializable
{
	public String FunctionID = "510442"; //功能编号

	public String OperID = ""; //操作员编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String OnlineStatus = ""; //在线状态
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510442";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510442";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

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

	public String getOnlineStatus()
	{
		return OnlineStatus;
	}
	public void setOnlineStatus(String OnlineStatus)
	{
		this.OnlineStatus = OnlineStatus;
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