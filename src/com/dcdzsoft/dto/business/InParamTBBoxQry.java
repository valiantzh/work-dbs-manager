package com.dcdzsoft.dto.business;

/**
* 箱体信息查询
*/

public class InParamTBBoxQry implements java.io.Serializable
{
	public String FunctionID = "210214"; //功能编号

	public int recordBegin; 
	public int recordNum; 

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public int DeskNo; //副柜编号
	public String BoxNo = ""; //箱门编号
	public String BoxStatus = ""; //箱状态
	public String LockStatus = ""; //锁定状态
	public String FaultStatus = ""; //故障状态
	public String QryFlag = ""; //查询标志(1-带包裹信息查询;其他-默认查询)

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210214";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210214";
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

	public int getDeskNo()
	{
		return DeskNo;
	}
	public void setDeskNo(int DeskNo)
	{
		this.DeskNo = DeskNo;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getBoxStatus()
	{
		return BoxStatus;
	}
	public void setBoxStatus(String BoxStatus)
	{
		this.BoxStatus = BoxStatus;
	}

	public String getLockStatus()
	{
		return LockStatus;
	}
	public void setLockStatus(String LockStatus)
	{
		this.LockStatus = LockStatus;
	}

	public String getFaultStatus()
	{
		return FaultStatus;
	}
	public void setFaultStatus(String FaultStatus)
	{
		this.FaultStatus = FaultStatus;
	}

	public String getQryFlag()
	{
		return QryFlag;
	}
	public void setQryFlag(String QryFlag)
	{
		this.QryFlag = QryFlag;
	}

}