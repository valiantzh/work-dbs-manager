package com.dcdzsoft.dto.business;

/**
* 数据库配置设置
*/

public class InParamSMDbCfgSet implements java.io.Serializable
{
	public String FunctionID = "110712"; //功能编号

	public String OperID = ""; //操作员编号
	public int maxActive; //最大活动连接
	public int maxIdle; //最大空闲
	public int minIdle; //最小空闲
	public int maxWait; //最大等待

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "110712";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "110712";
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

	public int getmaxActive()
	{
		return maxActive;
	}
	public void setmaxActive(int maxActive)
	{
		this.maxActive = maxActive;
	}

	public int getmaxIdle()
	{
		return maxIdle;
	}
	public void setmaxIdle(int maxIdle)
	{
		this.maxIdle = maxIdle;
	}

	public int getminIdle()
	{
		return minIdle;
	}
	public void setminIdle(int minIdle)
	{
		this.minIdle = minIdle;
	}

	public int getmaxWait()
	{
		return maxWait;
	}
	public void setmaxWait(int maxWait)
	{
		this.maxWait = maxWait;
	}

}