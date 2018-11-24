package com.dcdzsoft.dto.business;

/**
* 箱体故障状态维护
*/

public class InParamTBFaultStatusMod implements java.io.Serializable
{
	public String FunctionID = "210209"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String BoxNo = ""; //箱门编号
	public String FaultStatus = ""; //故障状态
	public String BoxType = ""; //箱类型编号
	public String OpenStatus = ""; //门开状态
	public String ArticleStatus = ""; //物品状态
	public String InboxStatus = ""; //在箱状态
	public String RemoteFlag = ""; //远程操作标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "210209";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "210209";
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

	public String getFaultStatus()
	{
		return FaultStatus;
	}
	public void setFaultStatus(String FaultStatus)
	{
		this.FaultStatus = FaultStatus;
	}

	public String getBoxType()
	{
		return BoxType;
	}
	public void setBoxType(String BoxType)
	{
		this.BoxType = BoxType;
	}

	public String getOpenStatus()
	{
		return OpenStatus;
	}
	public void setOpenStatus(String OpenStatus)
	{
		this.OpenStatus = OpenStatus;
	}

	public String getArticleStatus()
	{
		return ArticleStatus;
	}
	public void setArticleStatus(String ArticleStatus)
	{
		this.ArticleStatus = ArticleStatus;
	}

	public String getInboxStatus()
	{
		return InboxStatus;
	}
	public void setInboxStatus(String InboxStatus)
	{
		this.InboxStatus = InboxStatus;
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