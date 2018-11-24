package com.dcdzsoft.dto.business;

/**
* 上传设备警报信息
*/

public class InParamMBUploadDeviceAlert implements java.io.Serializable
{
	public String FunctionID = "150352"; //功能编号

	public String TerminalNo = ""; //设备号
	public String AlertType = ""; //报警种类
	public String AlertLevel = ""; //报警等级
	public String BoxNo = ""; //箱门编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150352";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150352";
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

	public String getAlertType()
	{
		return AlertType;
	}
	public void setAlertType(String AlertType)
	{
		this.AlertType = AlertType;
	}

	public String getAlertLevel()
	{
		return AlertLevel;
	}
	public void setAlertLevel(String AlertLevel)
	{
		this.AlertLevel = AlertLevel;
	}

	public String getBoxNo()
	{
		return BoxNo;
	}
	public void setBoxNo(String BoxNo)
	{
		this.BoxNo = BoxNo;
	}

}