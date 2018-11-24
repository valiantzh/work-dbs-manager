package com.dcdzsoft.business.pm;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递公司增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMCompanyAdd extends ActionBean {

    public int doBusiness(InParamPMCompanyAdd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.CompanyName))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        in.CompanyName = StringUtils.normalizeName(in.CompanyName);
        //////////////检查公司名称不重复//////////////////////
        PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("CompanyName", in.CompanyName);
        if (companyDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_COMPANYNAMEEXISTS);

        ///////////////生成公司编号//////////////////////////
        String maxCompanyID = "";
        int iMaxCompanyID = 0;
        PMCompany obj = new PMCompany();
        
        if(StringUtils.isEmpty(in.CompanyID))
        {
        	//8位数字
	        JDBCFieldArray whereCols1 = new JDBCFieldArray();
	        whereCols1.add("LogisticsID", in.LogisticsID);
	        maxCompanyID = companyDAO.selectFunctions("MAX(CompanyID)", whereCols1);
	        if (StringUtils.isEmpty(maxCompanyID)) {
	            obj.CompanyID = in.LogisticsID +  "0001";
	        } else {
	        	iMaxCompanyID = NumberUtils.parseInt(maxCompanyID) + 1;
	        	if(iMaxCompanyID>1){
	        	    obj.CompanyID = StringUtils.leftPad(String.valueOf(iMaxCompanyID), 8, '0');
	        	}else{
	        	    obj.CompanyID = RandUtils.generateString(6).toUpperCase();
	        	}
	        }
        }else
        {
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            whereCols1.add("CompanyID", in.CompanyID);
            if (companyDAO.isExist(whereCols1) > 0)
                throw new EduException(ErrorCode.ERR_COMPANYEXISTS);
            
            obj.CompanyID = in.CompanyID;
        }
        
        obj.LogisticsID = in.LogisticsID;
        obj.DepartmentID = operOnline.DepartmentID;
        
        obj.CompanyName = in.CompanyName;
        obj.OfficeTel = in.OfficeTel;
        obj.ContactPerson = in.ContactPerson;
        obj.ContactTel = in.ContactTel;
        obj.Province = in.Province;
        obj.OfficeTel = in.OfficeTel;
        obj.City = in.City;
        obj.Zip = in.Zip;
        obj.PinyinCode = in.PinyinCode;
        obj.Address = in.Address;
        obj.CreateTime = sysDateTime;
        obj.Remark = in.Remark;

        result = companyDAO.insert(obj);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = in.CompanyName;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
