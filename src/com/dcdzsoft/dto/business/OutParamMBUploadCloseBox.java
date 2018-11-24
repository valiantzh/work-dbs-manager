package com.dcdzsoft.dto.business;

/**
* 上传关箱消息
*/

public class OutParamMBUploadCloseBox implements java.io.Serializable
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