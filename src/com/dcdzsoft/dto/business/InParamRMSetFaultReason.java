package com.dcdzsoft.dto.business;

/**
* 设置故障原因
*/

public class InParamRMSetFaultReason implements java.io.Serializable
{
	public String FunctionID = "550038"; //功能编号

	public String OperID = ""; //管理员编号
	public String AppealNo = ""; //求助编号
	public String FaultStatus = ""; //故障状态
	public String FaultReason = ""; //故障原因分析
	public String AppealType = ""; //求助类型

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "550038";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "550038";
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

	public String getAppealNo()
	{
		return AppealNo;
	}
	public void setAppealNo(String AppealNo)
	{
		this.AppealNo = AppealNo;
	}

	public String getFaultStatus()
	{
		return FaultStatus;
	}
	public void setFaultStatus(String FaultStatus)
	{
		this.FaultStatus = FaultStatus;
	}

	public String getFaultReason()
	{
		return FaultReason;
	}
	public void setFaultReason(String FaultReason)
	{
		this.FaultReason = FaultReason;
	}

	public String getAppealType()
	{
		return AppealType;
	}
	public void setAppealType(String AppealType)
	{
		this.AppealType = AppealType;
	}

}