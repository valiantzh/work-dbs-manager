package com.dcdzsoft.dto.business;

/**
* 运营网点列表查询
*/

public class InParamAPDepartListQry implements java.io.Serializable
{
	public String FunctionID = "650201"; //功能编号

	public String OperID = ""; //管理员编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650201";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650201";
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