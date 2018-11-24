package com.dcdzsoft.dto.business;

/**
* 设备列表查询
*/

public class OutParamAPTerminalListQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public int BoxNum; //箱总数量
	public int DeskNum; //扩展柜数量
	public String MBDeviceNo = ""; //运营商柜号
	public String Location = ""; //安装地址
	public String Latlon = ""; //柜位置经纬度
	public String TerminalStatus = ""; //柜体状态
	public String CreateTime = ""; //创建时间
	public String Remark = ""; //备注

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

	public int getBoxNum()
	{
		return BoxNum;
	}
	public void setBoxNum(int BoxNum)
	{
		this.BoxNum = BoxNum;
	}

	public int getDeskNum()
	{
		return DeskNum;
	}
	public void setDeskNum(int DeskNum)
	{
		this.DeskNum = DeskNum;
	}

	public String getMBDeviceNo()
	{
		return MBDeviceNo;
	}
	public void setMBDeviceNo(String MBDeviceNo)
	{
		this.MBDeviceNo = MBDeviceNo;
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

	public String getTerminalStatus()
	{
		return TerminalStatus;
	}
	public void setTerminalStatus(String TerminalStatus)
	{
		this.TerminalStatus = TerminalStatus;
	}

	public String getCreateTime()
	{
		return CreateTime;
	}
	public void setCreateTime(String CreateTime)
	{
		this.CreateTime = CreateTime;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}