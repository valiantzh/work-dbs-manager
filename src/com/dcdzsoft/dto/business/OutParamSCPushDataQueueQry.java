package com.dcdzsoft.dto.business;

/**
* 数据推送队列查询
*/

public class OutParamSCPushDataQueueQry implements java.io.Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	public String MsgUid = ""; //消息唯一编号
	public String ServiceName = ""; //服务名称
	public String TerminalNo = "";//柜体编号
	public String MsgContent = "";//消息内容
	public int  FailureCount = 0;//同步失败次数
	public String Remark = "";//备注
	
    public String getMsgUid() {
        return MsgUid;
    }
    public void setMsgUid(String MsgUid) {
        this.MsgUid = MsgUid;
    }
    public String getServiceName() {
        return ServiceName;
    }
    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }
    public String getTerminalNo() {
        return TerminalNo;
    }
    public void setTerminalNo(String TerminalNo) {
        this.TerminalNo = TerminalNo;
    }
    public String getMsgContent() {
        return MsgContent;
    }
    public void setMsgContent(String MsgContent) {
        this.MsgContent = MsgContent;
    }
    public int getFailureCount() {
        return FailureCount;
    }
    public void setFailureCount(int FailureCount) {
        this.FailureCount = FailureCount;
    }
    public String getRemark() {
        return Remark;
    }
    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    
}