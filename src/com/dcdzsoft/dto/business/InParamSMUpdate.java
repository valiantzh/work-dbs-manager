package com.dcdzsoft.dto.business;

import java.io.Serializable;
import java.util.List;


public class InParamSMUpdate implements Serializable{
	
	public String FunctionID = "110120"; //功能编号

	public String OperID = ""; //操作员编号
	public String SystemID = ""; //系统编号
	public String TerminalNo = "";//设备号
	public String SoftwareVersion = ""; //版本号
	public String TerminalPasswd = ""; //加密文件MD5码
	public String Remark = ""; //文件路径，下载地址
	public String RemoteFlag = ""; //远程操作标志

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110120";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110120";
		else
			this.FunctionID = FunctionID;
	}
	
	public String getTerminalNo() {
		return TerminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		TerminalNo = terminalNo;
	}
	public String getOperID() {
		return OperID;
	}
	public void setOperID(String operID) {
		OperID = operID;
	}
	public String getSystemID() {
		return SystemID;
	}
	public void setSystemID(String systemID) {
		SystemID = systemID;
	}
	public String getSoftwareVersion() {
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		SoftwareVersion = softwareVersion;
	}
	public String getTerminalPasswd() {
		return TerminalPasswd;
	}
	public void setTerminalPasswd(String terminalPasswd) {
		TerminalPasswd = terminalPasswd;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getRemoteFlag() {
		return RemoteFlag;
	}
	public void setRemoteFlag(String remoteFlag) {
		RemoteFlag = remoteFlag;
	}


}
