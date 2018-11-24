package com.dcdzsoft.dto.business;

/**
* 取件密码短消息重新发送
*/

public class InParamMBPwdShortMsgReSend implements java.io.Serializable
{
	public String FunctionID = "150112"; //功能编号

	public String OperID = ""; //管理员编号
	public String WaterID = ""; //流水号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150112";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150112";
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

	public String getWaterID()
	{
		return WaterID;
	}
	public void setWaterID(String WaterID)
	{
		this.WaterID = WaterID;
	}

}