package com.dcdzsoft.dto.business;

/**
* 会员取消充值
*/

public class InParamCMCustomerTopupCancel implements java.io.Serializable
{
	public String FunctionID = "170113"; //功能编号

	public String OperID = ""; //管理员编号
	public String CustomerID = ""; //会员编号
	public String CustomerType = ""; //会员类型（99-投递员）
	public String TradeWaterNo = ""; //交易流水号
	public String OperPassword = ""; //登录用户密码
	public String BindCardID = ""; //绑定卡号
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "170113";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "170113";
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

	public String getCustomerID()
	{
		return CustomerID;
	}
	public void setCustomerID(String CustomerID)
	{
		this.CustomerID = CustomerID;
	}

	public String getCustomerType()
	{
		return CustomerType;
	}
	public void setCustomerType(String CustomerType)
	{
		this.CustomerType = CustomerType;
	}

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getOperPassword()
	{
		return OperPassword;
	}
	public void setOperPassword(String OperPassword)
	{
		this.OperPassword = OperPassword;
	}

	public String getBindCardID()
	{
		return BindCardID;
	}
	public void setBindCardID(String BindCardID)
	{
		this.BindCardID = BindCardID;
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