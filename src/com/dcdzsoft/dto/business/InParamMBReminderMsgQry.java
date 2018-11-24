package com.dcdzsoft.dto.business;

/**
* 待催领信息查询
*/

public class InParamMBReminderMsgQry implements java.io.Serializable
{
	public String FunctionID = "150144"; //功能编号

	public String OperID = ""; //管理员编号
	public String TerminalNo = ""; //设备号
	public int reminderHours = 0;//多少小时后催领  大于24小时有效

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150144";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150144";
		else
			this.FunctionID = FunctionID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}
    public int getReminderHours() {
        return reminderHours;
    }
    public void setReminderHours(int reminderHours) {
        this.reminderHours = reminderHours;
    }

}