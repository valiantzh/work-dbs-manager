package com.dcdzsoft.dto.business;

/**
* 物流公司查询
*/

public class InParamPMLogisticsQry implements java.io.Serializable
{
	public String FunctionID = "310014"; //功能编号

	public String OperID = ""; //操作员编号
	public String LogisticsID = ""; //物流公司编号
	public String LogisticsName = ""; //物流公司名称

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "310014";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "310014";
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

}