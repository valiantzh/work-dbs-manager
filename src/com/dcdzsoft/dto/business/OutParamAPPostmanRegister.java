package com.dcdzsoft.dto.business;

/**
* 投递员注册
*/

public class OutParamAPPostmanRegister implements java.io.Serializable
{
	public String PostmanID = ""; //投递员编号

	public String getPostmanID()
	{
		return PostmanID;
	}
	public void setPostmanID(String PostmanID)
	{
		this.PostmanID = PostmanID;
	}

}