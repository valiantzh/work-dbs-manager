package com.dcdzsoft.dto.business;

/**
* 充值流水状态修改
*/

public class InParamPYCustomerCardWaterMod implements java.io.Serializable
{
	public String FunctionID = "410223"; //功能编号

	public String OperID = ""; //管理员编号
	public String CardNo = ""; //会员卡编号
	public String CustomerID = ""; //会员编号
	public String TradeWaterNo = ""; //交易流水号
	public String UploadFlag = ""; //上传标志
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410223";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410223";
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

	public String getCardNo()
	{
		return CardNo;
	}
	public void setCardNo(String CardNo)
	{
		this.CardNo = CardNo;
	}

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getUploadFlag()
	{
		return UploadFlag;
	}
	public void setUploadFlag(String UploadFlag)
	{
		this.UploadFlag = UploadFlag;
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