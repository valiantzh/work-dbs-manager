package com.dcdzsoft.dto.business;

/**
 * 获取设备系统日志
 */

public class InParamMBGetDeviceSysLog implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "150336"; // 功能编号

    public String OperID = ""; // 操作员编号
    public String TerminalNo = ""; // 设备编号
    public int LogID; // 日志序号|
    public String UploadKey = ""; // 上传Key
    public String LogDate = "";// 日志日期，格式：2018-07-30
    public int LogLevel;// 日志级别：0-所有级别日志，1-普通日志，2-crash日志
    public int LogType;// 日志类型 ：0-所有类型日志，1-驱动软件日志，2-应用软件日志

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "150336";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "150336";
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

    public int getLogID() {
        return LogID;
    }

    public void setLogID(int LogID) {
        this.LogID = LogID;
    }

    public String getUploadKey() {
        return UploadKey;
    }

    public void setUploadKey(String UploadKey) {
        this.UploadKey = UploadKey;
    }

    public String getLogDate() {
        return LogDate;
    }

    public void setLogDate(String logDate) {
        LogDate = logDate;
    }

    public int getLogLevel() {
        return LogLevel;
    }

    public void setLogLevel(int logLevel) {
        LogLevel = logLevel;
    }

    public int getLogType() {
        return LogType;
    }

    public void setLogType(int logType) {
        LogType = logType;
    }

}