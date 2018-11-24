package com.dcdzsoft.dto.business;

/**
* 修改数据推送队列
*/

public class InParamSCPushDataQueueMod implements java.io.Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "510053"; //功能编号

	public String MsgUid = ""; //消息唯一编号

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510053";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510053";
		else
			this.FunctionID = FunctionID;
	}
    public String getMsgUid() {
        return MsgUid;
    }
    public void setMsgUid(String MsgUid) {
        this.MsgUid = MsgUid;
    }
}