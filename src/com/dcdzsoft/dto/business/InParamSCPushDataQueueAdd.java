package com.dcdzsoft.dto.business;

/**
* 添加推送数据到队列
*/

public class InParamSCPushDataQueueAdd implements java.io.Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "510051"; //功能编号

	public String MsgUid = ""; //消息唯一编号
	public String ServiceName = ""; //服务名称
	public String TerminalNo = "";//柜体编号
	public String MsgContent = "";//消息内容

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510051";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510051";
		else
			this.FunctionID = FunctionID;
	}
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
}