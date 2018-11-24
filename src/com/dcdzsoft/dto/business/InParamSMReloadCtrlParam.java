package com.dcdzsoft.dto.business;

/**
* 重新加载控制参数
*/

public class InParamSMReloadCtrlParam implements java.io.Serializable
{
	public String FunctionID = "110748"; //功能编号

	public String OperID = ""; //操作员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110748";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110748";
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