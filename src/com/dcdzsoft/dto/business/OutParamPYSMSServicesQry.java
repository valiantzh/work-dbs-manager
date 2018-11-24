package com.dcdzsoft.dto.business;

/**
* 查询短信服务套餐信息
*/

public class OutParamPYSMSServicesQry implements java.io.Serializable
{
	public String ServiceID = ""; //服务编号
	public String ServiceName = ""; //服务名称
	public String Ways = ""; //计费方式
	public String WaysName = ""; //计费方式
	public double Amount; //金额
	public String Units = ""; //计费单位（1-次;
	public String UnitsName = ""; //计费单位（1-次;
	public long SMSNum; //短信数量
	public String Active = ""; //激活标识
	public String ActiveName = ""; //激活标识
	public String Remark = ""; //备注

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

	public String getWays()
	{
		return Ways;
	}
	public void setWays(String Ways)
	{
		this.Ways = Ways;
	}

	public String getWaysName()
	{
		return WaysName;
	}
	public void setWaysName(String WaysName)
	{
		this.WaysName = WaysName;
	}

	public double getAmount()
	{
		return Amount;
	}
	public void setAmount(double Amount)
	{
		this.Amount = Amount;
	}

	public String getUnits()
	{
		return Units;
	}
	public void setUnits(String Units)
	{
		this.Units = Units;
	}

	public String getUnitsName()
	{
		return UnitsName;
	}
	public void setUnitsName(String UnitsName)
	{
		this.UnitsName = UnitsName;
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

	public String getActiveName()
	{
		return ActiveName;
	}
	public void setActiveName(String ActiveName)
	{
		this.ActiveName = ActiveName;
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