package com.dcdzsoft.dto.business;

/**
* 投递公司查询
*/

public class OutParamPMCompanyQry implements java.io.Serializable
{
	public String CompanyID = ""; //投递公司编号
	public String CompanyName = ""; //投递公司名称
	public String LogisticsID = ""; //物流公司编号
	public String LogisticsName = ""; //物流公司名称
	public String DepartmentID = ""; //网点编号
	public String DepartmentName = ""; //网点名称
	public String OfficeTel = ""; //办公电话
	public String ContactPerson = ""; //联系人
	public String ContactTel = ""; //联系人电话
	public String Province = ""; //所属省直辖市
	public String City = ""; //所属市
	public String Zip = ""; //邮编
	public String Address = ""; //地址
	public String PinyinCode = ""; //拼音代码
	public String Remark = ""; //备注

	public String getCompanyID()
	{
		return CompanyID;
	}
	public void setCompanyID(String CompanyID)
	{
		this.CompanyID = CompanyID;
	}

	public String getCompanyName()
	{
		return CompanyName;
	}
	public void setCompanyName(String CompanyName)
	{
		this.CompanyName = CompanyName;
	}

	public String getLogisticsID()
	{
		return LogisticsID;
	}
	public void setLogisticsID(String LogisticsID)
	{
		this.LogisticsID = LogisticsID;
	}

	public String getLogisticsName()
	{
		return LogisticsName;
	}
	public void setLogisticsName(String LogisticsName)
	{
		this.LogisticsName = LogisticsName;
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

	public String getProvince()
	{
		return Province;
	}
	public void setProvince(String Province)
	{
		this.Province = Province;
	}

	public String getCity()
	{
		return City;
	}
	public void setCity(String City)
	{
		this.City = City;
	}

	public String getZip()
	{
		return Zip;
	}
	public void setZip(String Zip)
	{
		this.Zip = Zip;
	}

	public String getAddress()
	{
		return Address;
	}
	public void setAddress(String Address)
	{
		this.Address = Address;
	}

	public String getPinyinCode()
	{
		return PinyinCode;
	}
	public void setPinyinCode(String PinyinCode)
	{
		this.PinyinCode = PinyinCode;
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