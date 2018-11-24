package com.dcdzsoft.dto.business;

/**
* 修改运营网点信息
*/

public class InParamMBDepartmentMod implements java.io.Serializable
{
	public String FunctionID = "150002"; //功能编号

	public String OperID = ""; //管理员编号
	public String DepartmentID = ""; //运营网点编号
	public String DepartmentName = ""; //运营网点名称
	public String ParentDepartID = ""; //父级管理运营网点
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

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "150002";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "150002";
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