package com.dcdzsoft.dto.business;

/**
* 现场管理员查询
*/

public class InParamOPSpotOperQry implements java.io.Serializable
{
	public String FunctionID = "133054"; //功能编号

	public String OperID = ""; //管理员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "133054";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "133054";
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

}