package com.dcdzsoft.dto.business;

/**
 * 上传关箱消息
 */

public class InParamMBUploadCloseBox implements java.io.Serializable {
    public String FunctionID = "150322"; // 功能编号

    public String OperID = ""; // 操作员编号
    public String TerminalNo = ""; // 设备号
    public String BoxNo = ""; // 箱号
    public String CloseBoxType = ""; // 关箱类型
    public String TradeWaterNo = ""; // 交易流水号
    public String ArticleStatus = ""; // 物品状态
    public String DoorStatus = "";// 门状态： 0-关 1-开 9-未知
    public String OccurTime = ""; // 关箱时间

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "150322";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "150322";
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

    public String getCloseBoxType() {
        return CloseBoxType;
    }

    public void setCloseBoxType(String CloseBoxType) {
        this.CloseBoxType = CloseBoxType;
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