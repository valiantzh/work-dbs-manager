package com.dcdzsoft.dto.business;

/**
* 发送设备升级通知
*/

public class InParamMBSendUpgradeInform implements java.io.Serializable
{
	public String FunctionID = "150345"; //功能编号

	public String OperID = ""; //操作员编号
	public String TerminalNoStr = ""; //设备列表
	public String UploadKey = ""; //上传Key

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150345";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150345";
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

	public String getTerminalNoStr()
	{
		return TerminalNoStr;
	}
	public void setTerminalNoStr(String TerminalNoStr)
	{
		this.TerminalNoStr = TerminalNoStr;
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