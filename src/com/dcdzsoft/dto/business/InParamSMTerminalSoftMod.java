package com.dcdzsoft.dto.business;

/**
 * 修改终端软件版本
 */

public class InParamSMTerminalSoftMod implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "110532"; // 功能编号

    public String OperID = ""; // 操作员编号
    public String SoftwareID = ""; // 软件编号
    public String SoftwareType = ""; // 软件类型：1-驱动软件  2-终端应用软件
    public String SoftwareVersion = ""; // 软件版本号
    public String UpdateStatus = "";// 更新状态 0-更新完成，1-等待更新
    public String TerminalNoStr = ""; // 更新内容

    public String getFunctionID() {
        if (this.FunctionID == null || this.FunctionID.compareTo("") == 0)
            return "110532";
        else
            return FunctionID;
    }

    public void setFunctionID(String FunctionID) {
        if (FunctionID == null || FunctionID.compareTo("") == 0)
            this.FunctionID = "110532";
        else
            this.FunctionID = FunctionID;
    }

    public String getOperID() {
        return OperID;
    }

    public void setOperID(String OperID) {
        this.OperID = OperID;
    }

    public String getSoftwareID() {
        return SoftwareID;
    }

    public void setSoftwareID(String SoftwareID) {
        this.SoftwareID = SoftwareID;
    }

    public String getSoftwareType() {
        return SoftwareType;
    }

    public void setSoftwareType(String SoftwareType) {
        this.SoftwareType = SoftwareType;
    }

    public String getSoftwareVersion() {
        return SoftwareVersion;
    }

    public void setSoftwareVersion(String SoftwareVersion) {
        this.SoftwareVersion = SoftwareVersion;
    }

    public String getUpdateStatus() {
        return UpdateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        UpdateStatus = updateStatus;
    }

    public String getTerminalNoStr() {
        return TerminalNoStr;
    }

    public void setTerminalNoStr(String TerminalNoStr) {
        this.TerminalNoStr = TerminalNoStr;
    }

}