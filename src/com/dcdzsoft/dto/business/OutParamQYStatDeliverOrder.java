package com.dcdzsoft.dto.business;

/**
* 投递量统计
*/

public class OutParamQYStatDeliverOrder implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String CompanyID = ""; //投递公司编号
	public String CompanyName = ""; //投递公司名称
	public String PostmanID = ""; //投递员编号
	public String PostmanName = ""; //投递员名称
	public String Location = ""; //安装地址
	public int BoxNum; //格口数量
	public String Period = ""; //统计区间
	public int DropNum; //投递量
	public int TakeOutNum; //用户领取数量
	public int TakeBackNum; //投递员取回数量
	public int ExpiredNum; //投递员逾期取回数量
	public int ManagerOutNum; //管理员取回件数量

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

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

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

	public String getPostmanName()
	{
		return PostmanName;
	}
	public void setPostmanName(String PostmanName)
	{
		this.PostmanName = PostmanName;
	}

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
	}

	public int getBoxNum()
	{
		return BoxNum;
	}
	public void setBoxNum(int BoxNum)
	{
		this.BoxNum = BoxNum;
	}

	public String getPeriod()
	{
		return Period;
	}
	public void setPeriod(String Period)
	{
		this.Period = Period;
	}

	public int getDropNum()
	{
		return DropNum;
	}
	public void setDropNum(int DropNum)
	{
		this.DropNum = DropNum;
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