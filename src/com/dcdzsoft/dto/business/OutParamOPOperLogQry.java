package com.dcdzsoft.dto.business;

/**
* 管理员日志查询
*/

public class OutParamOPOperLogQry implements java.io.Serializable
{
	public long OperLogID; //管理员日志序号
	public String OperID = ""; //管理员内部编号
	public String UserID = ""; //管理员编号
	public String OperName = ""; //管理员姓名
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String FunctionID = ""; //功能编号
	public String FunctionName = ""; //功能名称
	public java.sql.Timestamp OccurTime; //发生时间
	public String StationAddr = ""; //站点地址
	public String TerminalNo = ""; //设备号
	public String Remark = ""; //备注

	public long getOperLogID()
	{
		return OperLogID;
	}
	public void setOperLogID(long OperLogID)
	{
		this.OperLogID = OperLogID;
	}

	public String getOperID()
	{
		return OperID;
	}
	public void setOperID(String OperID)
	{
		this.OperID = OperID;
	}

	public String getUserID()
	{
		return UserID;
	}
	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}

	public String getOperName()
	{
		return OperName;
	}
	public void setOperName(String OperName)
	{
		this.OperName = OperName;
	}

	public String getDepartmentID()
	{
		return DepartmentID;
	}
	public void setDepartmentID(String DepartmentID)
	{
		this.DepartmentID = DepartmentID;
	}

	public String getDepartmentName()
	{
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName)
	{
		this.DepartmentName = DepartmentName;
	}

	public String getFunctionID()
	{
		return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		this.FunctionID = FunctionID;
	}

	public String getFunctionName()
	{
		return FunctionName;
	}
	public void setFunctionName(String FunctionName)
	{
		this.FunctionName = FunctionName;
	}

	public java.sql.Timestamp getOccurTime()
	{
		return OccurTime;
	}
	public void setOccurTime(java.sql.Timestamp OccurTime)
	{
		this.OccurTime = OccurTime;
	}

	public String getStationAddr()
	{
		return StationAddr;
	}
	public void setStationAddr(String StationAddr)
	{
		this.StationAddr = StationAddr;
	}

	public String getTerminalNo()
	{
		return TerminalNo;
	}
	public void setTerminalNo(String TerminalNo)
	{
		this.TerminalNo = TerminalNo;
	}

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}