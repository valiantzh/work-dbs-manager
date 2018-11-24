package com.dcdzsoft.dto.business;

/**
* 终端软件升级信息数量查询
*/

public class InParamMBTerminalUpgradeQryCount implements java.io.Serializable
{
	public String FunctionID = "150344"; //功能编号

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String OnlineStatus = ""; //在线状态
	public String TerminalStatus = ""; //柜体状态
	public String VersionFlag = ""; //版本标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150344";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150344";
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

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
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

	public String getTerminalStatus()
	{
		return TerminalStatus;
	}
	public void setTerminalStatus(String TerminalStatus)
	{
		this.TerminalStatus = TerminalStatus;
	}

	public String getVersionFlag()
	{
		return VersionFlag;
	}
	public void setVersionFlag(String VersionFlag)
	{
		this.VersionFlag = VersionFlag;
	}

}