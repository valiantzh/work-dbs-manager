package com.dcdzsoft.dto.business;

/**
* 数据库配置查询
*/

public class OutParamSMDbCfgQry implements java.io.Serializable
{
	public int maxActive; //最大活动连接
	public int maxIdle; //最大空闲
	public int minIdle; //最小空闲
	public int maxWait; //最大等待
	public int numActive; //当前活动数量
	public int numIdle; //当前空闲数量

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

	public int getnumActive()
	{
		return numActive;
	}
	public void setnumActive(int numActive)
	{
		this.numActive = numActive;
	}

	public int getnumIdle()
	{
		return numIdle;
	}
	public void setnumIdle(int numIdle)
	{
		this.numIdle = numIdle;
	}

}