package com.dcdzsoft.dto.business;

/**
* 投递开箱
*/

public class OutParamAPPostmanAppOpenBox implements java.io.Serializable
{
    public String TerminalNo;
    public String PackageID;
    public String BoxNo;
    public String TradeWaterNo;
	public String Remark = ""; //备注

	
	public String getTerminalNo() {
        return TerminalNo;
    }
    public void setTerminalNo(String terminalNo) {
        TerminalNo = terminalNo;
    }
    public String getPackageID() {
        return PackageID;
    }
    public void setPackageID(String packageID) {
        PackageID = packageID;
    }
    public String getBoxNo() {
        return BoxNo;
    }
    public void setBoxNo(String boxNo) {
        BoxNo = boxNo;
    }
    public String getTradeWaterNo() {
        return TradeWaterNo;
    }
    public void setTradeWaterNo(String tradeWaterNo) {
        TradeWaterNo = tradeWaterNo;
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