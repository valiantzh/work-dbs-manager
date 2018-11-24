package com.dcdzsoft.dto.business;

/**
* 同步柜体信息
*/

public class OutParamSCSyncTBTerminal implements java.io.Serializable
{
	public String terminalNo = ""; //设备号
	public String terminalName = ""; //设备名称
	public String location = ""; //安装地址
	public int boxCount; //箱总数量
	public String status = ""; //柜体状态
	public String website = ""; //网点名称
	public String websiteNo = ""; //网点编号
	public String manufacturer = ""; //厂商标识
	public int hugeBoxCount; //超大箱数量
	public int largeBoxCount; //大箱数量
	public int middleBoxCount; //中箱数量
	public int smallBoxCount; //小箱数量
	public int miniBoxCount; //超小箱数量
	public String province = ""; //省份
	public String city = ""; //城市
	public String area = ""; //地区
	public String websiteType = ""; //网点类型
	public String longitude = ""; //经度
	public String latitude = ""; //纬度
	public java.sql.Timestamp createTime; //创建时间
	public java.sql.Timestamp usedTime; //启用时间
	public java.sql.Timestamp updateTime; //最后修改时间

	public String getterminalNo()
	{
		return terminalNo;
	}
	public void setterminalNo(String terminalNo)
	{
		this.terminalNo = terminalNo;
	}

	public String getterminalName()
	{
		return terminalName;
	}
	public void setterminalName(String terminalName)
	{
		this.terminalName = terminalName;
	}

	public String getlocation()
	{
		return location;
	}
	public void setlocation(String location)
	{
		this.location = location;
	}

	public int getboxCount()
	{
		return boxCount;
	}
	public void setboxCount(int boxCount)
	{
		this.boxCount = boxCount;
	}

	public String getstatus()
	{
		return status;
	}
	public void setstatus(String status)
	{
		this.status = status;
	}

	public String getwebsite()
	{
		return website;
	}
	public void setwebsite(String website)
	{
		this.website = website;
	}

	public String getwebsiteNo()
	{
		return websiteNo;
	}
	public void setwebsiteNo(String websiteNo)
	{
		this.websiteNo = websiteNo;
	}

	public String getmanufacturer()
	{
		return manufacturer;
	}
	public void setmanufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}

	public int gethugeBoxCount()
	{
		return hugeBoxCount;
	}
	public void sethugeBoxCount(int hugeBoxCount)
	{
		this.hugeBoxCount = hugeBoxCount;
	}

	public int getlargeBoxCount()
	{
		return largeBoxCount;
	}
	public void setlargeBoxCount(int largeBoxCount)
	{
		this.largeBoxCount = largeBoxCount;
	}

	public int getmiddleBoxCount()
	{
		return middleBoxCount;
	}
	public void setmiddleBoxCount(int middleBoxCount)
	{
		this.middleBoxCount = middleBoxCount;
	}

	public int getsmallBoxCount()
	{
		return smallBoxCount;
	}
	public void setsmallBoxCount(int smallBoxCount)
	{
		this.smallBoxCount = smallBoxCount;
	}

	public int getminiBoxCount()
	{
		return miniBoxCount;
	}
	public void setminiBoxCount(int miniBoxCount)
	{
		this.miniBoxCount = miniBoxCount;
	}

	public String getprovince()
	{
		return province;
	}
	public void setprovince(String province)
	{
		this.province = province;
	}

	public String getcity()
	{
		return city;
	}
	public void setcity(String city)
	{
		this.city = city;
	}

	public String getarea()
	{
		return area;
	}
	public void setarea(String area)
	{
		this.area = area;
	}

	public String getwebsiteType()
	{
		return websiteType;
	}
	public void setwebsiteType(String websiteType)
	{
		this.websiteType = websiteType;
	}

	public String getlongitude()
	{
		return longitude;
	}
	public void setlongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public String getlatitude()
	{
		return latitude;
	}
	public void setlatitude(String latitude)
	{
		this.latitude = latitude;
	}

	public java.sql.Timestamp getcreateTime()
	{
		return createTime;
	}
	public void setcreateTime(java.sql.Timestamp createTime)
	{
		this.createTime = createTime;
	}

	public java.sql.Timestamp getusedTime()
	{
		return usedTime;
	}
	public void setusedTime(java.sql.Timestamp usedTime)
	{
		this.usedTime = usedTime;
	}

	public java.sql.Timestamp getupdateTime()
	{
		return updateTime;
	}
	public void setupdateTime(java.sql.Timestamp updateTime)
	{
		this.updateTime = updateTime;
	}

}