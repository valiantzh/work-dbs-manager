package com.dcdzsoft.dto.business;

/**
* 获取上传失败数据
*/

public class InParamMBGetUpFailureData implements java.io.Serializable
{
	public String FunctionID = "150338"; //功能编号

	public String OperID = ""; //操作员编号
	public String TerminalNo = ""; //设备编号
	public String UploadKey = ""; //上传Key

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150338";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150338";
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

}