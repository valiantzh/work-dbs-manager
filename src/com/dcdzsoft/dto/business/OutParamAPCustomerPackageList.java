package com.dcdzsoft.dto.business;

/**
* 收件人包裹列表
*/

public class OutParamAPCustomerPackageList implements java.io.Serializable
{
	public String TNO = ""; //设备号
	public String TNE = ""; //设备名称
	public String LTN = ""; //投递地址
	public String BXN = ""; //箱门编号
	public String PID = ""; //订单号
	public String MID = ""; //投递员编号
	public java.sql.Timestamp STM; //存物时间
	public java.sql.Timestamp TTM; //取物时间
	public String STS = ""; //包裹状态

	public String getTNO()
	{
		return TNO;
	}
	public void setTNO(String TNO)
	{
		this.TNO = TNO;
	}

	public String getTNE()
	{
		return TNE;
	}
	public void setTNE(String TNE)
	{
		this.TNE = TNE;
	}

	public String getLTN()
	{
		return LTN;
	}
	public void setLTN(String LTN)
	{
		this.LTN = LTN;
	}

	public String getBXN()
	{
		return BXN;
	}
	public void setBXN(String BXN)
	{
		this.BXN = BXN;
	}

	public String getPID()
	{
		return PID;
	}
	public void setPID(String PID)
	{
		this.PID = PID;
	}

	public String getMID()
	{
		return MID;
	}
	public void setMID(String MID)
	{
		this.MID = MID;
	}

	public java.sql.Timestamp getSTM()
	{
		return STM;
	}
	public void setSTM(java.sql.Timestamp STM)
	{
		this.STM = STM;
	}

	public java.sql.Timestamp getTTM()
	{
		return TTM;
	}
	public void setTTM(java.sql.Timestamp TTM)
	{
		this.TTM = TTM;
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