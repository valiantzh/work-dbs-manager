package com.dcdzsoft.dto.business;

/**
 * 获取可用箱列表
 */

public class InParamAPPostmanFreeBoxList implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "650025"; // 功能编号

    public String TerminalNo = ""; // 设备号
    public String PostmanID = ""; // 投递员编号
    public String TradeWaterNo = ""; // 交易流水号
    public String Remark = ""; // 备注

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "650025";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "650025";
        else
            this.FunctionID = FunctionID;
    }

    public String getTerminalNo() {
        return TerminalNo;
    }

    public void setTerminalNo(String TerminalNo) {
        this.TerminalNo = TerminalNo;
    }

    public String getPostmanID() {
        return PostmanID;
    }

    public void setPostmanID(String PostmanID) {
        this.PostmanID = PostmanID;
    }

    public String getTradeWaterNo() {
        return TradeWaterNo;
    }

    public void setTradeWaterNo(String tradeWaterNo) {
        TradeWaterNo = tradeWaterNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

}