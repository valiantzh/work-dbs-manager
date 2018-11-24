package com.dcdzsoft.dto.business;

/**
* 查询软件更新包信息列表
*/

public class OutParamSMUpgradePackListQry implements java.io.Serializable
{
	public String SoftwareID = ""; //软件编号
	public String SoftwareName = ""; //软件名称

	public String getSoftwareID()
	{
		return SoftwareID;
	}
	public void setSoftwareID(String SoftwareID)
	{
		this.SoftwareID = SoftwareID;
	}

	public String getSoftwareName()
	{
		return SoftwareName;
	}
	public void setSoftwareName(String SoftwareName)
	{
		this.SoftwareName = SoftwareName;
	}

}