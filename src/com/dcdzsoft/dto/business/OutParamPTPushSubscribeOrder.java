package com.dcdzsoft.dto.business;

/**
* 推送预约订单
*/

public class OutParamPTPushSubscribeOrder implements java.io.Serializable
{
	public String PID = ""; //订单号
	public String BNO = ""; //箱门编号
	public java.sql.Timestamp OTM; //下单时间
	public java.sql.Timestamp ETM; //逾期时间
	public String CID = ""; //投递公司编号
	public String MID = ""; //投递员编号
	public String TID = ""; //取件人编号
	public String MOB = ""; //取件人手机
	public String FLG = ""; //支付标志
	public double AMT; //待支付金额
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

	public java.sql.Timestamp getOTM()
	{
		return OTM;
	}
	public void setOTM(java.sql.Timestamp OTM)
	{
		this.OTM = OTM;
	}

	public java.sql.Timestamp getETM()
	{
		return ETM;
	}
	public void setETM(java.sql.Timestamp ETM)
	{
		this.ETM = ETM;
	}

	public String getCID()
	{
		return CID;
	}
	public void setCID(String CID)
	{
		this.CID = CID;
	}

	public String getMID()
	{
		return MID;
	}
	public void setMID(String MID)
	{
		this.MID = MID;
	}

	public String getTID()
	{
		return TID;
	}
	public void setTID(String TID)
	{
		this.TID = TID;
	}

	public String getMOB()
	{
		return MOB;
	}
	public void setMOB(String MOB)
	{
		this.MOB = MOB;
	}

	public String getFLG()
	{
		return FLG;
	}
	public void setFLG(String FLG)
	{
		this.FLG = FLG;
	}

	public double getAMT()
	{
		return AMT;
	}
	public void setAMT(double AMT)
	{
		this.AMT = AMT;
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