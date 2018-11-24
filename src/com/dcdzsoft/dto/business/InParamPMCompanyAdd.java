package com.dcdzsoft.dto.business;

/**
* 投递公司增加
*/

public class InParamPMCompanyAdd implements java.io.Serializable
{
	public String FunctionID = "310001"; //功能编号

	public String OperID = ""; //操作员编号
	public String CompanyID = ""; //投递公司编号
	public String CompanyName = ""; //投递公司名称
	public String LogisticsID = ""; //物流公司编号
	public String OfficeTel = ""; //办公电话
	public String ContactPerson = ""; //联系人
	public String ContactTel = ""; //联系人电话
	public String Province = ""; //所属省直辖市
	public String PinyinCode = ""; //拼音代码
	public String City = ""; //所属市
	public String Zip = ""; //邮编
	public String Address = ""; //地址
	public String Remark = ""; //备注

	public String getFunctionID()
	{
		if(this.FunctionID == null || this.FunctionID.compareTo("") == 0)
			return "310001";
		else
			return FunctionID;
	}
	public void setFunctionID(String FunctionID)
	{
		if(FunctionID == null || FunctionID.compareTo("") == 0)
			this.FunctionID = "310001";
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

	public String getPinyinCode()
	{
		return PinyinCode;
	}
	public void setPinyinCode(String PinyinCode)
	{
		this.PinyinCode = PinyinCode;
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

	public String getRemark()
	{
		return Remark;
	}
	public void setRemark(String Remark)
	{
		this.Remark = Remark;
	}

}