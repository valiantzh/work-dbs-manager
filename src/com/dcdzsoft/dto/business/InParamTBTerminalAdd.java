package com.dcdzsoft.dto.business;

/**
 * 柜体信息增加
 */

public class InParamTBTerminalAdd implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "210001"; // 功能编号

    public String OperID = ""; // 管理员编号
    public String TerminalNo = ""; // 设备号
    public String TerminalName = ""; // 设备名称
    public int MainDeskAddress; // 主柜位置
    public int BoxNum; // 箱总数量
    public int DeskNum; // 扩展柜数量
    public String MBDeviceNo = ""; // 运营商设备编号
    public String OfBureau = ""; // 所属投递局
    public String OfSegment = ""; // 所属投递段
    public String Location = ""; // 安装地址
    public String Latlon = ""; // 柜位置经纬度
    public String Remark = ""; // 备注
    public String DepartmentID = "";//网点编号
    public String SystemID = "";// 系统编号
    public String InitPasswd = "";// 安装密码

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "210001";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "210001";
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

    public String getTerminalName() {
        return TerminalName;
    }

    public void setTerminalName(String TerminalName) {
        this.TerminalName = TerminalName;
    }

    public int getMainDeskAddress() {
        return MainDeskAddress;
    }

    public void setMainDeskAddress(int MainDeskAddress) {
        this.MainDeskAddress = MainDeskAddress;
    }

    public int getBoxNum() {
        return BoxNum;
    }

    public void setBoxNum(int BoxNum) {
        this.BoxNum = BoxNum;
    }

    public int getDeskNum() {
        return DeskNum;
    }

    public void setDeskNum(int DeskNum) {
        this.DeskNum = DeskNum;
    }

    public String getMBDeviceNo() {
        return MBDeviceNo;
    }

    public void setMBDeviceNo(String MBDeviceNo) {
        this.MBDeviceNo = MBDeviceNo;
    }

    public String getOfBureau() {
        return OfBureau;
    }

    public void setOfBureau(String OfBureau) {
        this.OfBureau = OfBureau;
    }

    public String getOfSegment() {
        return OfSegment;
    }

    public void setOfSegment(String OfSegment) {
        this.OfSegment = OfSegment;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getLatlon() {
        return Latlon;
    }

    public void setLatlon(String Latlon) {
        this.Latlon = Latlon;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public String getSystemID() {
        return SystemID;
    }

    public void setSystemID(String systemID) {
        SystemID = systemID;
    }

    public String getInitPasswd() {
        return InitPasswd;
    }

    public void setInitPasswd(String initPasswd) {
        InitPasswd = initPasswd;
    }

}