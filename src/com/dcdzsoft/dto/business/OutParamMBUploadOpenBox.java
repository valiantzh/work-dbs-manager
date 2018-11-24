package com.dcdzsoft.dto.business;

/**
* 上传开箱消息
*/

public class OutParamMBUploadOpenBox implements java.io.Serializable
{
	public String Remark = ""; //备注

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}