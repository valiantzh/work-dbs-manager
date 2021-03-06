package com.dcdzsoft.dto.business;

/**
* 修改短信服务套餐信息
*/

public class InParamPYSMSServicesMod implements java.io.Serializable
{
	public String FunctionID = "410002"; //功能编号

	public String OperID = ""; //管理员编号
	public String ServiceID = ""; //服务编号
	public String ServiceName = ""; //服务名称
	public double Amount; //金额
	public long SMSNum; //短信数量
	public String Active = ""; //激活标识
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "410002";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "410002";
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

	public String getServiceID()
	{
		return ServiceID;
	}
	public void setServiceID(String ServiceID)
	{
		this.ServiceID = ServiceID;
	}

	public String getServiceName()
	{
		return ServiceName;
	}
	public void setServiceName(String ServiceName)
	{
		this.ServiceName = ServiceName;
	}

	public double getAmount()
	{
		return Amount;
	}
	public void setAmount(double Amount)
	{
		this.Amount = Amount;
	}

	public long getSMSNum()
	{
		return SMSNum;
	}
	public void setSMSNum(long SMSNum)
	{
		this.SMSNum = SMSNum;
	}

	public String getActive()
	{
		return Active;
	}
	public void setActive(String Active)
	{
		this.Active = Active;
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