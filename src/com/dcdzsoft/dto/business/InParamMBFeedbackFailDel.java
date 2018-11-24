package com.dcdzsoft.dto.business;

/**
* 反馈投递信息重新发送
*/

public class InParamMBFeedbackFailDel implements java.io.Serializable
{
	public String FunctionID = "150153"; //功能编号

	public String OperID = ""; //管理员编号
	public String WaterIDList = ""; //流水号
	public String Mode = ""; //标志：1-自动清除

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150153";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150153";
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

	public String getWaterIDList()
	{
		return WaterIDList;
	}
	public void setWaterIDList(String WaterIDList)
	{
		this.WaterIDList = WaterIDList;
	}

	public String getMode()
	{
		return Mode;
	}
	public void setMode(String Mode)
	{
		this.Mode = Mode;
	}

}