package com.dcdzsoft.dto.business;

/**
* 投递情况统计4投递公司
*/

public class OutParamQYStat4Company implements java.io.Serializable
{
	public String CompanyID = ""; //投递公司编号
	public String CompanyName = ""; //投递公司名称
	public String LogisticsID = ""; //物流公司编号
	public String LogisticsName = ""; //物流公司名称
	public int InBoxNum; //在箱快件数量
	public int TakeOutNum; //用户领取数量
	public int TakeBackNum; //投递员取回数量
	public int ExpiredNum; //投递员逾期取回数量
	public int ManagerOutNum; //管理员取回件数量

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getCompanyName()
	{
		return CompanyName;
	}
	public void setCompanyName(String CompanyName)
	{
		this.CompanyName = CompanyName;
	}

	public String getLogisticsID()
	{
		return LogisticsID;
	}
	public void setLogisticsID(String LogisticsID)
	{
		this.LogisticsID = LogisticsID;
	}

	public String getLogisticsName()
	{
		return LogisticsName;
	}
	public void setLogisticsName(String LogisticsName)
	{
		this.LogisticsName = LogisticsName;
	}

	public int getInBoxNum()
	{
		return InBoxNum;
	}
	public void setInBoxNum(int InBoxNum)
	{
		this.InBoxNum = InBoxNum;
	}

	public int getTakeOutNum()
	{
		return TakeOutNum;
	}
	public void setTakeOutNum(int TakeOutNum)
	{
		this.TakeOutNum = TakeOutNum;
	}

	public int getTakeBackNum()
	{
		return TakeBackNum;
	}
	public void setTakeBackNum(int TakeBackNum)
	{
		this.TakeBackNum = TakeBackNum;
	}

	public int getExpiredNum()
	{
		return ExpiredNum;
	}
	public void setExpiredNum(int ExpiredNum)
	{
		this.ExpiredNum = ExpiredNum;
	}

	public int getManagerOutNum()
	{
		return ManagerOutNum;
	}
	public void setManagerOutNum(int ManagerOutNum)
	{
		this.ManagerOutNum = ManagerOutNum;
	}

}