package com.dcdzsoft.dto.business;

/**
* 用户取件关门状态上传
*/

public class InParamPTUploadDoorStatus implements java.io.Serializable
{
	public String FunctionID = "330513"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String BoxNo = ""; //箱门编号
	public String PackageID = ""; //订单号
	public String ArticleStatus = ""; //物品状态
	public String DoorStatus = "";//箱门状态，3分钟以内，0：打开，1：关闭
	
	@Override
	public String toString() {
		return "InParamPTUploadDoorStatus [FunctionID=" + FunctionID
				+ ", TerminalNo=" + TerminalNo + ", TradeWaterNo="
				+ TradeWaterNo + ", BoxNo=" + BoxNo + ", PackageID="
				+ PackageID + ", ArticleStatus=" + ArticleStatus
				+ ", DoorStatus=" + DoorStatus + "]";
	}
	public String getDoorStatus() {
		return DoorStatus;
	}
	public void setDoorStatus(String doorStatus) {
		DoorStatus = doorStatus;
	}
	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330513";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330513";
		else
			this.FunctionID = FunctionID;
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

	public String getPackageID()
	{
		return PackageID;
	}
	public void setPackageID(String PackageID)
	{
		this.PackageID = PackageID;
	}

	public String getArticleStatus()
	{
		return ArticleStatus;
	}
	public void setArticleStatus(String ArticleStatus)
	{
		this.ArticleStatus = ArticleStatus;
	}

}