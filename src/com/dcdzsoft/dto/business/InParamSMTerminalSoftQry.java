package com.dcdzsoft.dto.business;

/**
 * 查询终端软件版本信息
 */

public class InParamSMTerminalSoftQry implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "110534"; // 功能编号

    public int recordBegin;
    public int recordNum;

    public String OperID = ""; // 操作员编号
    public String DepartmentID = ""; // 运营网点编号
    public String TerminalNo = ""; // 设备号
    public String TerminalName = ""; // 设备名称
    public String SoftwareID = ""; // 软件编号
    public String SoftwareName = ""; // 软件名称
    public String SoftwareType = ""; // 软件类型：1-
    public String SystemID = ""; // 系统编号
    public String UpdateStatus = "";// 更新状态 0-更新完成，1-等待更新

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "110534";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "110534";
        else
            this.FunctionID = FunctionID;
    }

    public String getOperID() {
        return OperID;
    }

    public void setOperID(String OperID) {
        this.OperID = OperID;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String DepartmentID) {
        this.DepartmentID = DepartmentID;
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

    public String getSoftwareID() {
        return SoftwareID;
    }

    public void setSoftwareID(String SoftwareID) {
        this.SoftwareID = SoftwareID;
    }

    public String getSoftwareName() {
        return SoftwareName;
    }

    public void setSoftwareName(String SoftwareName) {
        this.SoftwareName = SoftwareName;
    }

    public String getSoftwareType() {
        return SoftwareType;
    }

    public void setSoftwareType(String SoftwareType) {
        this.SoftwareType = SoftwareType;
    }

    public String getSystemID() {
        return SystemID;
    }

    public void setSystemID(String SystemID) {
        this.SystemID = SystemID;
    }

    public String getUpdateStatus() {
        return UpdateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        UpdateStatus = updateStatus;
    }

}