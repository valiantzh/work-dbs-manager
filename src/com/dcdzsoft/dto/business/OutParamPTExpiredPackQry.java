package com.dcdzsoft.dto.business;

/**
* 下载逾期包裹单列表
*/

public class OutParamPTExpiredPackQry implements java.io.Serializable
{
	public String PID = ""; //订单号
	public java.sql.Timestamp STM; //存物时间
	public String BNO = ""; //箱门编号
	public String MOB = ""; //取件人手机
	public String STS = ""; //包裹状态

	public String getPID()
	{
		return PID;
	}
	public void setPID(String PID)
	{
		this.PID = PID;
	}

	public java.sql.Timestamp getSTM()
	{
		return STM;
	}
	public void setSTM(java.sql.Timestamp STM)
	{
		this.STM = STM;
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

	public String getSTS()
	{
		return STS;
	}
	public void setSTS(String STS)
	{
		this.STS = STS;
	}

}