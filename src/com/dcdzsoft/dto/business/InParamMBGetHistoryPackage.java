package com.dcdzsoft.dto.business;

/**
* 获取设备历史投递记录信息
*/

public class InParamMBGetHistoryPackage implements java.io.Serializable
{
	public String FunctionID = "150332"; //功能编号

	public String OperID = ""; //操作员编号
	public String TerminalNo = ""; //设备编号
	public String UploadKey = ""; //上传Key
	public String AutoFlag = ""; //自动标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150332";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150332";
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

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getUploadKey()
	{
		return UploadKey;
	}
	public void setUploadKey(String UploadKey)
	{
		this.UploadKey = UploadKey;
	}

	public String getAutoFlag()
	{
		return AutoFlag;
	}
	public void setAutoFlag(String AutoFlag)
	{
		this.AutoFlag = AutoFlag;
	}

}