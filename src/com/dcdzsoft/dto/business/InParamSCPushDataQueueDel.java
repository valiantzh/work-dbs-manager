package com.dcdzsoft.dto.business;

/**
* 清除数据推送队列
*/

public class InParamSCPushDataQueueDel implements java.io.Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String FunctionID = "510052"; //功能编号

	public String MsgUid = ""; //消息唯一编号
	public String ErrMsg = ""; //错误信息

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "510052";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "510052";
		else
			this.FunctionID = FunctionID;
	}
    public String getMsgUid() {
        return MsgUid;
    }
    public void setMsgUid(String MsgUid) {
        this.MsgUid = MsgUid;
    }
    public String getErrMsg() {
        return ErrMsg;
    }
    public void setErrMsg(String ErrMsg) {
       this.ErrMsg = ErrMsg;
    }

}