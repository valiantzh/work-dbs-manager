package com.dcdzsoft.dto.business;

/**
 * 设备注册
 */

public class InParamMBDeviceRegister implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "150308"; // 功能编号

    public String SignIP = ""; // 签到终端IP
    public String SignMac = ""; // 签到终端MAC
    public String SoftwareVersion = ""; // 软件版本号
    public String InitPasswd = ""; // 安装密码
    public String DeviceInfo = ""; // 柜体信息
    public String SystemInfo = "";// 系统信息

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "150308";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "150308";
        else
            this.FunctionID = FunctionID;
    }

    public String getSignIP() {
        return SignIP;
    }

    public void setSignIP(String SignIP) {
        this.SignIP = SignIP;
    }

    public String getSignMac() {
        return SignMac;
    }

    public void setSignMac(String SignMac) {
        this.SignMac = SignMac;
    }

    public String getSoftwareVersion() {
        return SoftwareVersion;
    }

    public void setSoftwareVersion(String SoftwareVersion) {
        this.SoftwareVersion = SoftwareVersion;
    }

    public String getInitPasswd() {
        return InitPasswd;
    }

    public void setInitPasswd(String InitPasswd) {
        this.InitPasswd = InitPasswd;
    }

    public String getDeviceInfo() {
        return DeviceInfo;
    }

    public void setDeviceInfo(String DeviceInfo) {
        this.DeviceInfo = DeviceInfo;
    }

}