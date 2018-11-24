package com.dcdzsoft.dto.business;

/**
 * 设备注册
 */

public class OutParamMBDeviceRegister implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String TerminalNo = ""; // 设备号
    public String TerminalName = ""; // 设备名称
    public String Location = ""; // 设备地址
    public String MBDeviceNo = ""; // 运营商设备编号
    public String DepartmentID = ""; // 所属网点编号
    public String SystemID = ""; // 系统编号
    public String InitPasswd = "";// 安装密码
    public java.sql.Timestamp ServerTime; // 服务器时间
    public String ServerUrl = ""; // 业务服务地址
    public String BoxInfo = ""; // 箱体信息
    public String ControlInfo = ""; // 控制信息

    public String getTerminalNo() {
        return TerminalNo;
    }

    public void setTerminalNo(String TerminalNo) {
        this.TerminalNo = TerminalNo;
    }

    public String getTerminalName() {
        return TerminalName;
    }

    public void setTerminalName(String TerminalName) {
        this.TerminalName = TerminalName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getMBDeviceNo() {
        return MBDeviceNo;
    }

    public void setMBDeviceNo(String MBDeviceNo) {
        this.MBDeviceNo = MBDeviceNo;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getSystemID() {
        return SystemID;
    }

    public void setSystemID(String SystemID) {
        this.SystemID = SystemID;
    }

    public String getInitPasswd() {
        return InitPasswd;
    }

    public void setInitPasswd(String initPasswd) {
        InitPasswd = initPasswd;
    }

    public java.sql.Timestamp getServerTime() {
        return ServerTime;
    }

    public void setServerTime(java.sql.Timestamp serverTime) {
        ServerTime = serverTime;
    }

    public String getServerUrl() {
        return ServerUrl;
    }

    public void setServerUrl(String ServerUrl) {
        this.ServerUrl = ServerUrl;
    }

    public String getBoxInfo() {
        return BoxInfo;
    }

    public void setBoxInfo(String BoxInfo) {
        this.BoxInfo = BoxInfo;
    }

    public String getControlInfo() {
        return ControlInfo;
    }

    public void setControlInfo(String ControlInfo) {
        this.ControlInfo = ControlInfo;
    }

}