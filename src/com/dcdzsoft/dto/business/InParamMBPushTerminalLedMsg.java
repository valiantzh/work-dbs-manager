package com.dcdzsoft.dto.business;

/**
* 推送LED消息
*/

public class InParamMBPushTerminalLedMsg implements java.io.Serializable
{
	public String FunctionID = "150452"; //功能编号

	public String OperID = ""; //操作员编号
	public String TerminalNo = ""; //设备编号
	public String LedMsg = ""; //LED信息
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

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150452";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150452";
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

	public String getLedMsg()
	{
		return LedMsg;
	}
	public void setLedMsg(String LedMsg)
	{
		this.LedMsg = LedMsg;
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