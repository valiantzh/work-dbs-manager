package com.dcdzsoft.dto.business;

/**
 * 用户取件身份认证
 */

public class InParamAPCustomerVerify implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "650141"; // 功能编号

    public String TerminalNo = ""; // 设备号
    public String TradeWaterNo = ""; // 交易流水号
    public String CustomerID = ""; // 取件人身份标识
    public String OpenBoxKey = ""; // 开箱密码

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "650141";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "650141";
        else
            this.FunctionID = FunctionID;
    }

    public String getTerminalNo() {
        return TerminalNo;
    }

    public void setTerminalNo(String TerminalNo) {
        this.TerminalNo = TerminalNo;
    }

    public String getTradeWaterNo() {
        return TradeWaterNo;
    }

    public void setTradeWaterNo(String TradeWaterNo) {
        this.TradeWaterNo = TradeWaterNo;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getOpenBoxKey() {
        return OpenBoxKey;
    }

    public void setOpenBoxKey(String OpenBoxKey) {
        this.OpenBoxKey = OpenBoxKey;
    }
}