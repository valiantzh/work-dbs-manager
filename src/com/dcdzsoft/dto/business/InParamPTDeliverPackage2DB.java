package com.dcdzsoft.dto.business;

/**
* 投递记录入库
*/

public class InParamPTDeliverPackage2DB implements java.io.Serializable
{
	public String FunctionID = "330502"; //功能编号

	public String TerminalNo = ""; //设备号
	public String FileName = ""; //文件名称

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "330502";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "330502";
		else
			this.FunctionID = FunctionID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getFileName()
	{
		return FileName;
	}
	public void setFileName(String FileName)
	{
		this.FileName = FileName;
	}

}