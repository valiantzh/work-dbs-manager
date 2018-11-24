package com.dcdzsoft.dto.business;

/**
* LED消息查询
*/

public class OutParamMBTerminalLedMsgQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String TerminalName = ""; //设备名称
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String Location = ""; //安装地址
	public String OnlineStatus = ""; //在线状态
	public String OnlineStatusName = ""; //在线状态名称
	public int StartPointX; //起始X位置
	public int StartPointY; //起始Y位置
	public int LedWidth; //屏宽
	public int LedHeight; //屏高
	public int DisplayWay; //显示方式
	public int QuitWay; //退场方式
	public int RunSpeed; //运行速度
	public int StopTime; //停留时间
	public int FontSize; //字体大小
	public int FontColor; //字体颜色
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

	public String getLocation()
	{
		return Location;
	}
	public void setLocation(String Location)
	{
		this.Location = Location;
	}

	public String getOnlineStatus()
	{
		return OnlineStatus;
	}
	public void setOnlineStatus(String OnlineStatus)
	{
		this.OnlineStatus = OnlineStatus;
	}

	public String getOnlineStatusName()
	{
		return OnlineStatusName;
	}
	public void setOnlineStatusName(String OnlineStatusName)
	{
		this.OnlineStatusName = OnlineStatusName;
	}

	public int getStartPointX()
	{
		return StartPointX;
	}
	public void setStartPointX(int StartPointX)
	{
		this.StartPointX = StartPointX;
	}

	public int getStartPointY()
	{
		return StartPointY;
	}
	public void setStartPointY(int StartPointY)
	{
		this.StartPointY = StartPointY;
	}

	public int getLedWidth()
	{
		return LedWidth;
	}
	public void setLedWidth(int LedWidth)
	{
		this.LedWidth = LedWidth;
	}

	public int getLedHeight()
	{
		return LedHeight;
	}
	public void setLedHeight(int LedHeight)
	{
		this.LedHeight = LedHeight;
	}

	public int getDisplayWay()
	{
		return DisplayWay;
	}
	public void setDisplayWay(int DisplayWay)
	{
		this.DisplayWay = DisplayWay;
	}

	public int getQuitWay()
	{
		return QuitWay;
	}
	public void setQuitWay(int QuitWay)
	{
		this.QuitWay = QuitWay;
	}

	public int getRunSpeed()
	{
		return RunSpeed;
	}
	public void setRunSpeed(int RunSpeed)
	{
		this.RunSpeed = RunSpeed;
	}

	public int getStopTime()
	{
		return StopTime;
	}
	public void setStopTime(int StopTime)
	{
		this.StopTime = StopTime;
	}

	public int getFontSize()
	{
		return FontSize;
	}
	public void setFontSize(int FontSize)
	{
		this.FontSize = FontSize;
	}

	public int getFontColor()
	{
		return FontColor;
	}
	public void setFontColor(int FontColor)
	{
		this.FontColor = FontColor;
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