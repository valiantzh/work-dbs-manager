package com.dcdzsoft.dto.business;

/**
* 查询运营网点信息(以树形结构返回)
*/

public class OutParamMBDepartTreeQry implements java.io.Serializable
{
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String ParentDepartID = ""; //父级运营网点编号
	public String ParentDepartName = ""; //父级运营网点名称
	public int DepartLevel; //运营网点级别
	public int LeafFlag; //叶结点标志
	public String Area = ""; //所属县或地区
	public String City = ""; //所属市
	public String Province = ""; //所属省直辖市
	public String Address = ""; //地址
	public String Zip = ""; //邮编
	public String OfficeTel = ""; //电话
	public String ContactPerson = ""; //联系人
	public String ContactTel = ""; //联系人电话
	public String WelcomeInfo = ""; //欢迎词
	public String Remark = ""; //备注

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

	public String getParentDepartID()
	{
		return ParentDepartID;
	}
	public void setParentDepartID(String ParentDepartID)
	{
		this.ParentDepartID = ParentDepartID;
	}

	public String getParentDepartName()
	{
		return ParentDepartName;
	}
	public void setParentDepartName(String ParentDepartName)
	{
		this.ParentDepartName = ParentDepartName;
	}

	public int getDepartLevel()
	{
		return DepartLevel;
	}
	public void setDepartLevel(int DepartLevel)
	{
		this.DepartLevel = DepartLevel;
	}

	public int getLeafFlag()
	{
		return LeafFlag;
	}
	public void setLeafFlag(int LeafFlag)
	{
		this.LeafFlag = LeafFlag;
	}

	public String getArea()
	{
		return Area;
	}
	public void setArea(String Area)
	{
		this.Area = Area;
	}

	public String getCity()
	{
		return City;
	}
	public void setCity(String City)
	{
		this.City = City;
	}

	public String getProvince()
	{
		return Province;
	}
	public void setProvince(String Province)
	{
		this.Province = Province;
	}

	public String getAddress()
	{
		return Address;
	}
	public void setAddress(String Address)
	{
		this.Address = Address;
	}

	public String getZip()
	{
		return Zip;
	}
	public void setZip(String Zip)
	{
		this.Zip = Zip;
	}

	public String getOfficeTel()
	{
		return OfficeTel;
	}
	public void setOfficeTel(String OfficeTel)
	{
		this.OfficeTel = OfficeTel;
	}

	public String getContactPerson()
	{
		return ContactPerson;
	}
	public void setContactPerson(String ContactPerson)
	{
		this.ContactPerson = ContactPerson;
	}

	public String getContactTel()
	{
		return ContactTel;
	}
	public void setContactTel(String ContactTel)
	{
		this.ContactTel = ContactTel;
	}

	public String getWelcomeInfo()
	{
		return WelcomeInfo;
	}
	public void setWelcomeInfo(String WelcomeInfo)
	{
		this.WelcomeInfo = WelcomeInfo;
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