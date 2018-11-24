package com.dcdzsoft.dto.business;

/**
 * 获取可用箱列表
 */

public class OutParamAPPostmanFreeBoxList implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String TerminalNo = ""; // 柜体编号
    public String FreeBoxList = ""; // 可用格口信息
    public String TradeWaterNo = ""; // 交易流水号

	public String TerminalName = ""; //设备名称
	public int BoxNum; //箱总数量
	public int DeskNum; //扩展柜数量
	public String MBDeviceNo = ""; //运营商柜号
	public String Location = ""; //安装地址
	public String Latlon = ""; //柜位置经纬度
	
    public String getTerminalNo() {
        return TerminalNo;
    }

    public void setTerminalNo(String TerminalNo) {
        this.TerminalNo = TerminalNo;
    }

    public String getFreeBoxList() {
        return FreeBoxList;
    }

    public void setFreeBoxList(String FreeBoxList) {
        this.FreeBoxList = FreeBoxList;
    }

    public String getTradeWaterNo() {
        return TradeWaterNo;
    }

    public void setTradeWaterNo(String tradeWaterNo) {
        TradeWaterNo = tradeWaterNo;
    }

	public String getTerminalName() {
		return TerminalName;
	}

	public void setTerminalName(String terminalName) {
		TerminalName = terminalName;
	}

	public int getBoxNum() {
		return BoxNum;
	}

	public void setBoxNum(int boxNum) {
		BoxNum = boxNum;
	}

	public int getDeskNum() {
		return DeskNum;
	}

	public void setDeskNum(int deskNum) {
		DeskNum = deskNum;
	}

	public String getMBDeviceNo() {
		return MBDeviceNo;
	}

	public void setMBDeviceNo(String mBDeviceNo) {
		MBDeviceNo = mBDeviceNo;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getLatlon() {
		return Latlon;
	}

	public void setLatlon(String latlon) {
		Latlon = latlon;
	}
    
    

}