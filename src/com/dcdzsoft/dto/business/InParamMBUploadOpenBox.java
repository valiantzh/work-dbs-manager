package com.dcdzsoft.dto.business;

/**
 * 上传开箱消息
 */

public class InParamMBUploadOpenBox implements java.io.Serializable {
    public String FunctionID = "150321"; // 功能编号

    public String OperID = ""; // 操作员编号
    public String TerminalNo = ""; // 设备号
    public String BoxNo = ""; // 箱号
    public String OpenBoxType = ""; // 开箱类型
    public String TradeWaterNo = ""; // 交易流水号
    public String ArticleStatus = ""; // 物品状态
    public String DoorStatus = "";// 门状态： 0-关 1-开 9-未知
    public String OccurTime = ""; // 开箱时间

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "150321";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "150321";
        else
            this.FunctionID = FunctionID;
    }

    public String getOperID() {
        return OperID;
    }

    public void setOperID(String OperID) {
        this.OperID = OperID;
    }

    public String getTerminalNo() {
        return TerminalNo;
    }

    public void setTerminalNo(String TerminalNo) {
        this.TerminalNo = TerminalNo;
    }

    public String getBoxNo() {
        return BoxNo;
    }

    public void setBoxNo(String BoxNo) {
        this.BoxNo = BoxNo;
    }

    public String getOpenBoxType() {
        return OpenBoxType;
    }

    public void setOpenBoxType(String OpenBoxType) {
        this.OpenBoxType = OpenBoxType;
    }

    public String getTradeWaterNo() {
        return TradeWaterNo;
    }

    public void setTradeWaterNo(String TradeWaterNo) {
        this.TradeWaterNo = TradeWaterNo;
    }

    public String getArticleStatus() {
        return ArticleStatus;
    }

    public void setArticleStatus(String ArticleStatus) {
        this.ArticleStatus = ArticleStatus;
    }

    public String getOccurTime() {
        return OccurTime;
    }

    public void setOccurTime(String OccurTime) {
        this.OccurTime = OccurTime;
    }

    public String getDoorStatus() {
        return DoorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        DoorStatus = doorStatus;
    }

}