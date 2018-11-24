package com.dcdzsoft.dto.business;

/**
* 获取广告列表
*/

public class OutParamMBGetAdvertisePic implements java.io.Serializable
{
	public String HomePicUrl = ""; //主页广告图片
	public String PostmanPicUrl = ""; //投递员广告图片
	public String CustomerPicUrl = ""; //客户广告图片
	public int PlayInterval; //播放间隔

	public String getHomePicUrl()
	{
		return HomePicUrl;
	}
	public void setHomePicUrl(String HomePicUrl)
	{
		this.HomePicUrl = HomePicUrl;
	}

	public String getPostmanPicUrl()
	{
		return PostmanPicUrl;
	}
	public void setPostmanPicUrl(String PostmanPicUrl)
	{
		this.PostmanPicUrl = PostmanPicUrl;
	}

	public String getCustomerPicUrl()
	{
		return CustomerPicUrl;
	}
	public void setCustomerPicUrl(String CustomerPicUrl)
	{
		this.CustomerPicUrl = CustomerPicUrl;
	}

	public int getPlayInterval()
	{
		return PlayInterval;
	}
	public void setPlayInterval(int PlayInterval)
	{
		this.PlayInterval = PlayInterval;
	}

}