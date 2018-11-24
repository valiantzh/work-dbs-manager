package com.dcdzsoft.dto.business;

/**
* 设备信息更新
*/

public class InParamAPTerminalUpdate implements java.io.Serializable
{
	public String FunctionID = "650209"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String Location = ""; //安装地址
	public String Latlon = ""; //柜位置经纬度
	public String QRCodeStr = ""; //二维码信息

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "650209";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "650209";
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

	public String getTerminalName()
	{
		return TerminalName;
	}
	public void setTerminalName(String TerminalName)
	{
		this.TerminalName = TerminalName;
	}

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
	}

	public String getLatlon()
	{
		return Latlon;
	}
	public void setLatlon(String Latlon)
	{
		this.Latlon = Latlon;
	}

	public String getQRCodeStr()
	{
		return QRCodeStr;
	}
	public void setQRCodeStr(String QRCodeStr)
	{
		this.QRCodeStr = QRCodeStr;
	}

}