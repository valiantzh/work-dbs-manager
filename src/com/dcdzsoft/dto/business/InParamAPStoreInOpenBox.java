package com.dcdzsoft.dto.business;

/**
 * 投递开箱（新接口）
 */

public class InParamAPStoreInOpenBox implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "650501"; // 功能编号

    public String TradeWaterNo = ""; // 交易流水号
    public String OperID = ""; // 操作员编号
    public String TerminalNo = ""; // 设备号
    public String BoxNo = ""; // 箱号
    public String ForceOpen = ""; // 强制开箱,1 终端有物品也开
    public String StoreType = ""; // 存储类型(开门类型)：1 寄存 2 投递 3 售卖上架 9 其他
    public String OccurTime = ""; // 请求时间
    public String UpdateDoorStatusFlag = "";// 上传门状态标志：0-不上传；1-上传开门状态；2-上传关门状态；3-上传开&关门状态

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "650501";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "650501";
        else
            this.FunctionID = FunctionID;
    }

    public String getTradeWaterNo() {
        return TradeWaterNo;
    }

    public void setTradeWaterNo(String TradeWaterNo) {
        this.TradeWaterNo = TradeWaterNo;
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

    public String getForceOpen() {
        return ForceOpen;
    }

    public void setForceOpen(String ForceOpen) {
        this.ForceOpen = ForceOpen;
    }

    public String getStoreType() {
        return StoreType;
    }

    public void setStoreType(String StoreType) {
        this.StoreType = StoreType;
    }

    public String getOccurTime() {
        return OccurTime;
    }

    public void setOccurTime(String OccurTime) {
        this.OccurTime = OccurTime;
    }

    public String getUpdateDoorStatusFlag() {
        return UpdateDoorStatusFlag;
    }

    public void setUpdateDoorStatusFlag(String updateDoorStatusFlag) {
        UpdateDoorStatusFlag = updateDoorStatusFlag;
    }

}