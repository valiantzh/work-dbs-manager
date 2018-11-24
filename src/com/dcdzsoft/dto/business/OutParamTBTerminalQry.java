package com.dcdzsoft.dto.business;

/**
* 柜体信息查询
*/

public class OutParamTBTerminalQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public int BoxNum; //箱总数量
	public int DeskNum; //扩展柜数量
	public String MBDeviceNo = ""; //运营商设备编号
	public String OfBureau = ""; //所属投递局
	public String OfSegment = ""; //所属投递段
	public String Location = ""; //安装地址
	public String Latlon = ""; //柜位置经纬度
	public String RegisterFlag = ""; //注册标志
	public String RegisterFlagName = ""; //注册标志名称
	public String TerminalStatus = ""; //柜体状态
	public String TerminalStatusName = ""; //柜体状态名称
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

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
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

	public String getOfBureau()
	{
		return OfBureau;
	}
	public void setOfBureau(String OfBureau)
	{
		this.OfBureau = OfBureau;
	}

	public String getOfSegment()
	{
		return OfSegment;
	}
	public void setOfSegment(String OfSegment)
	{
		this.OfSegment = OfSegment;
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

	public String getRegisterFlag()
	{
		return RegisterFlag;
	}
	public void setRegisterFlag(String RegisterFlag)
	{
		this.RegisterFlag = RegisterFlag;
	}

	public String getRegisterFlagName()
	{
		return RegisterFlagName;
	}
	public void setRegisterFlagName(String RegisterFlagName)
	{
		this.RegisterFlagName = RegisterFlagName;
	}

	public String getTerminalStatus()
	{
		return TerminalStatus;
	}
	public void setTerminalStatus(String TerminalStatus)
	{
		this.TerminalStatus = TerminalStatus;
	}

	public String getTerminalStatusName()
	{
		return TerminalStatusName;
	}
	public void setTerminalStatusName(String TerminalStatusName)
	{
		this.TerminalStatusName = TerminalStatusName;
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