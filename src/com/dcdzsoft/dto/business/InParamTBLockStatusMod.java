package com.dcdzsoft.dto.business;

/**
* 箱体锁定状态维护
*/

public class InParamTBLockStatusMod implements java.io.Serializable
{
	public String FunctionID = "210210"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String BoxNo = ""; //箱门编号
	public String LockStatus = ""; //锁定状态
	public String BoxType = ""; //箱类型编号
	public String RemoteFlag = ""; //远程操作标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210210";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210210";
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

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

	public String getLockStatus()
	{
		return LockStatus;
	}
	public void setLockStatus(String LockStatus)
	{
		this.LockStatus = LockStatus;
	}

	public String getBoxType()
	{
		return BoxType;
	}
	public void setBoxType(String BoxType)
	{
		this.BoxType = BoxType;
	}

	public String getRemoteFlag()
	{
		return RemoteFlag;
	}
	public void setRemoteFlag(String RemoteFlag)
	{
		this.RemoteFlag = RemoteFlag;
	}

}