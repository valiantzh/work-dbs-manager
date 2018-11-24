package com.dcdzsoft.dto.business;

/**
* 下载待投递订单列表
*/

public class OutParamPTReadPackageQry implements java.io.Serializable
{
	public String PID = ""; //订单号
	public String BNO = ""; //箱门编号
	public String MOB = ""; //取件人手机
	public String FlG = ""; //支付标志
	public String STS = ""; //包裹状态

	public String getPID()
	{
		return PID;
	}
	public void setPID(String PID)
	{
		this.PID = PID;
	}

	public String getBNO()
	{
		return BNO;
	}
	public void setBNO(String BNO)
	{
		this.BNO = BNO;
	}

	public String getMOB()
	{
		return MOB;
	}
	public void setMOB(String MOB)
	{
		this.MOB = MOB;
	}

	public String getFlG()
	{
		return FlG;
	}
	public void setFlG(String FlG)
	{
		this.FlG = FlG;
	}

	public String getSTS()
	{
		return STS;
	}
	public void setSTS(String STS)
	{
		this.STS = STS;
	}

}