package com.dcdzsoft.dto.business;

/**
* 设备外设状态报告
*/

public class InParamMBReportPeripheralStatus implements java.io.Serializable
{
	public String FunctionID = "150403"; //功能编号

	public String TerminalNo = ""; //设备号
	public String TradeWaterNo = ""; //交易流水号
	public String TerminalStatus = ""; //智能信包箱状态
	public String CardReaderStatus = ""; //读卡器状态
	public String ICStatus = ""; //IC设备状态
	public String IDCardStatus = ""; //IDCard设备状态
	public String BarcodeStatus = ""; //条码设备状态
	public String PowerStatus = ""; //电源状态
	public String PasskeyBoardStatus = ""; //密码键盘状态
	public String PrinterStatus = ""; //打印机状态
	public String Camera1Status = ""; //摄像1状态
	public String Camera2Status = ""; //摄像2状态
	public String Camera3Status = ""; //摄像3状态
	public int FaultNum; //故障箱数量
	public int FreeNum; //空闲箱数量
	public java.sql.Timestamp LocalTime; //本地时间
	public String SoftwareVersion = ""; //软件版本
	public String HardwareVersion = ""; //硬件版本
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150403";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150403";
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

	public String getTradeWaterNo()
	{
		return TradeWaterNo;
	}
	public void setTradeWaterNo(String TradeWaterNo)
	{
		this.TradeWaterNo = TradeWaterNo;
	}

	public String getTerminalStatus()
	{
		return TerminalStatus;
	}
	public void setTerminalStatus(String TerminalStatus)
	{
		this.TerminalStatus = TerminalStatus;
	}

	public String getCardReaderStatus()
	{
		return CardReaderStatus;
	}
	public void setCardReaderStatus(String CardReaderStatus)
	{
		this.CardReaderStatus = CardReaderStatus;
	}

	public String getICStatus()
	{
		return ICStatus;
	}
	public void setICStatus(String ICStatus)
	{
		this.ICStatus = ICStatus;
	}

	public String getIDCardStatus()
	{
		return IDCardStatus;
	}
	public void setIDCardStatus(String IDCardStatus)
	{
		this.IDCardStatus = IDCardStatus;
	}

	public String getBarcodeStatus()
	{
		return BarcodeStatus;
	}
	public void setBarcodeStatus(String BarcodeStatus)
	{
		this.BarcodeStatus = BarcodeStatus;
	}

	public String getPowerStatus()
	{
		return PowerStatus;
	}
	public void setPowerStatus(String PowerStatus)
	{
		this.PowerStatus = PowerStatus;
	}

	public String getPasskeyBoardStatus()
	{
		return PasskeyBoardStatus;
	}
	public void setPasskeyBoardStatus(String PasskeyBoardStatus)
	{
		this.PasskeyBoardStatus = PasskeyBoardStatus;
	}

	public String getPrinterStatus()
	{
		return PrinterStatus;
	}
	public void setPrinterStatus(String PrinterStatus)
	{
		this.PrinterStatus = PrinterStatus;
	}

	public String getCamera1Status()
	{
		return Camera1Status;
	}
	public void setCamera1Status(String Camera1Status)
	{
		this.Camera1Status = Camera1Status;
	}

	public String getCamera2Status()
	{
		return Camera2Status;
	}
	public void setCamera2Status(String Camera2Status)
	{
		this.Camera2Status = Camera2Status;
	}

	public String getCamera3Status()
	{
		return Camera3Status;
	}
	public void setCamera3Status(String Camera3Status)
	{
		this.Camera3Status = Camera3Status;
	}

	public int getFaultNum()
	{
		return FaultNum;
	}
	public void setFaultNum(int FaultNum)
	{
		this.FaultNum = FaultNum;
	}

	public int getFreeNum()
	{
		return FreeNum;
	}
	public void setFreeNum(int FreeNum)
	{
		this.FreeNum = FreeNum;
	}

	public java.sql.Timestamp getLocalTime()
	{
		return LocalTime;
	}
	public void setLocalTime(java.sql.Timestamp LocalTime)
	{
		this.LocalTime = LocalTime;
	}

	public String getSoftwareVersion()
	{
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String SoftwareVersion)
	{
		this.SoftwareVersion = SoftwareVersion;
	}

	public String getHardwareVersion()
	{
		return HardwareVersion;
	}
	public void setHardwareVersion(String HardwareVersion)
	{
		this.HardwareVersion = HardwareVersion;
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