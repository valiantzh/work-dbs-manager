package com.dcdzsoft.dto.business;

import java.io.Serializable;
import java.util.List;


public class OutParamSMUpdate implements Serializable{

	public String TerminalNo = "";//设备号
	public String SoftwareVersion = ""; //版本号
	public String TerminalPasswd = ""; //加密文件MD5码
	public String Remark = ""; //文件路径，下载地址

	public String getTerminalNo() {
		return TerminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		TerminalNo = terminalNo;
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
	@Override
	public String toString() {
		return "OutParamSMUpdate [TerminalNo=" + TerminalNo
				+ ", SoftwareVersion=" + SoftwareVersion + ", TerminalPasswd="
				+ TerminalPasswd + ", Remark=" + Remark + "]";
	}



}
