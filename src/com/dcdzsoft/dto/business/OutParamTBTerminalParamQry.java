package com.dcdzsoft.dto.business;

/**
* 柜体监控参数查询
*/

public class OutParamTBTerminalParamQry implements java.io.Serializable
{
	public String TerminalNo = ""; //设备号
	public String ScreensoundFlag = ""; //语音提示标志
	public int RefuseCloseDoor; //拒关箱门次数
	public String ExistSuperLargeBox = ""; //存在超大箱体
	public String ArticleInspectFlag = ""; //物品检测标志
	public String DoorInspectFlag = ""; //箱门检测标志
	public String PowerInspectFlag = ""; //电源检测标志
	public String ShockInspectFlag = ""; //震动防撬检测标志

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getScreensoundFlag()
	{
		return ScreensoundFlag;
	}
	public void setScreensoundFlag(String ScreensoundFlag)
	{
		this.ScreensoundFlag = ScreensoundFlag;
	}

	public int getRefuseCloseDoor()
	{
		return RefuseCloseDoor;
	}
	public void setRefuseCloseDoor(int RefuseCloseDoor)
	{
		this.RefuseCloseDoor = RefuseCloseDoor;
	}

	public String getExistSuperLargeBox()
	{
		return ExistSuperLargeBox;
	}
	public void setExistSuperLargeBox(String ExistSuperLargeBox)
	{
		this.ExistSuperLargeBox = ExistSuperLargeBox;
	}

	public String getArticleInspectFlag()
	{
		return ArticleInspectFlag;
	}
	public void setArticleInspectFlag(String ArticleInspectFlag)
	{
		this.ArticleInspectFlag = ArticleInspectFlag;
	}

	public String getDoorInspectFlag()
	{
		return DoorInspectFlag;
	}
	public void setDoorInspectFlag(String DoorInspectFlag)
	{
		this.DoorInspectFlag = DoorInspectFlag;
	}

	public String getPowerInspectFlag()
	{
		return PowerInspectFlag;
	}
	public void setPowerInspectFlag(String PowerInspectFlag)
	{
		this.PowerInspectFlag = PowerInspectFlag;
	}

	public String getShockInspectFlag()
	{
		return ShockInspectFlag;
	}
	public void setShockInspectFlag(String ShockInspectFlag)
	{
		this.ShockInspectFlag = ShockInspectFlag;
	}

}