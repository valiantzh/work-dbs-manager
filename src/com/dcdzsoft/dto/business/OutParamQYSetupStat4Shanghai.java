package com.dcdzsoft.dto.business;

/**
* 安装布放情况统计
*/

public class OutParamQYSetupStat4Shanghai implements java.io.Serializable
{
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public int TerminalCount; //已安装柜子数
	public int BoxCount; //总格口数
	public double YzDeliveryRate; //邮政投递率
	public int YzCount; //邮政投递量
	public int ShCount; //社会投递量
	public double UsedRate; //使用率
	public int YzManCount; //邮政注册数
	public int ShManCount; //社会注册数

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
	}

	public int getTerminalCount()
	{
		return TerminalCount;
	}
	public void setTerminalCount(int TerminalCount)
	{
		this.TerminalCount = TerminalCount;
	}

	public int getBoxCount()
	{
		return BoxCount;
	}
	public void setBoxCount(int BoxCount)
	{
		this.BoxCount = BoxCount;
	}

	public double getYzDeliveryRate()
	{
		return YzDeliveryRate;
	}
	public void setYzDeliveryRate(double YzDeliveryRate)
	{
		this.YzDeliveryRate = YzDeliveryRate;
	}

	public int getYzCount()
	{
		return YzCount;
	}
	public void setYzCount(int YzCount)
	{
		this.YzCount = YzCount;
	}

	public int getShCount()
	{
		return ShCount;
	}
	public void setShCount(int ShCount)
	{
		this.ShCount = ShCount;
	}

	public double getUsedRate()
	{
		return UsedRate;
	}
	public void setUsedRate(double UsedRate)
	{
		this.UsedRate = UsedRate;
	}

	public int getYzManCount()
	{
		return YzManCount;
	}
	public void setYzManCount(int YzManCount)
	{
		this.YzManCount = YzManCount;
	}

	public int getShManCount()
	{
		return ShManCount;
	}
	public void setShManCount(int ShManCount)
	{
		this.ShManCount = ShManCount;
	}

}