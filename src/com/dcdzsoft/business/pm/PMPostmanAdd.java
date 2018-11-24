package com.dcdzsoft.business.pm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.sms.SMSManager;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递员增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMPostmanAdd extends ActionBean {

    public int doBusiness(InParamPMPostmanAdd in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.CompanyID)
            || StringUtils.isEmpty(in.PostmanName))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = null;
        if(StringUtils.isNotEmpty(in.OperID)){
            operOnline = commonDAO.isOnline(in.OperID);
            if(StringUtils.isEmpty(in.DepartmentID)){
                in.DepartmentID = operOnline.DepartmentID;
            }
        }
        
        PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
        PMCompany company = new PMCompany();
        company.CompanyID = in.CompanyID;
        if(!companyDAO.isExist(company)){
            throw new EduException(ErrorCode.ERR_COMPANYNOTEXISTS);
        }
        company = companyDAO.find(company);
        if(StringUtils.isEmpty(in.DepartmentID)){
            in.DepartmentID = company.DepartmentID;
        }
        
        MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
        MBDepartment department = new MBDepartment();
        department.DepartmentID = in.DepartmentID;
        if(!departmentDAO.isExist(department)){
            throw new EduException(ErrorCode.ERR_COMPANYNOTEXISTS);
        }
        
        if(StringUtils.isEmpty(in.Mobile) && StringUtils.isNotEmpty(in.PostmanID))
        {
        	if(in.PostmanID.length() == 11 && in.PostmanID.startsWith("1"))
        		in.Mobile = in.PostmanID;
        }
        
        //if(StringUtils.isNotEmpty(in.Mobile) && !isPhoneNumber(in.Mobile))
    	//	in.Mobile = "";

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        in.PostmanName = StringUtils.normalizeName(in.PostmanName);
        ///////////////////生成投递员编号///////////////////////////
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman obj = new PMPostman();

        if(StringUtils.isEmpty(in.PostmanID))
        {
        	/*String maxPostManID = "";
            long iMaxPostManID = 0;

            JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("CompanyID", in.CompanyID);
            maxPostManID = postmanDAO.selectFunctions("MAX(PostmanID)", whereCols0);

            if (StringUtils.isEmpty(maxPostManID)) {
                obj.PostmanID = in.CompanyID + StringUtils.leftPad("1", 3, '0');
            } else {
                iMaxPostManID = NumberUtils.parseLong(maxPostManID) + 1;
                obj.PostmanID = StringUtils.leftPad(String.valueOf(iMaxPostManID),10, '0');
            }*/
        	obj.PostmanID = RandUtils.generateNumber(10).toUpperCase();
        }else
        {
        	obj.PostmanID = in.PostmanID;
        	if(postmanDAO.isExist(obj))
        		throw new EduException(ErrorCode.ERR_OPERHAVEEXIST);
        }
        
        if(StringUtils.isNotEmpty(in.BindCardID))
        {
        	JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID",in.BindCardID);
            whereCols0.add("PostmanStatus", SysDict.POSTMAN_STATUS_NORMAL);

            if(postmanDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
        }
        
        if(StringUtils.isNotEmpty(in.BindMobile))
        {
        	if(!this.isPhoneNumber(in.BindMobile))
        		throw new EduException(ErrorCode.ERR_BUSINESS_MOBILEFORMATERROR);
        	
        	JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindMobile",in.BindMobile);
            whereCols0.add("PostmanStatus", SysDict.POSTMAN_STATUS_NORMAL);

            if(postmanDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDMOBILE);
        }
        
        String pwd = RandUtils.generateNumber(6);
        if(StringUtils.isEmpty(in.Password))
        {
        	in.Password = pwd;
        }
        if(StringUtils.isEmpty(in.PostmanType)){
        	in.PostmanType = SysDict.POSTMAN_TYPE_POST;
        }
        if(StringUtils.isEmpty(in.InputMobileFlag)){
        	in.InputMobileFlag = SysDict.INPUTMOBILE_FLAG_LOCAL;
        }
        //插入投递员信息
        obj.CompanyID = in.CompanyID;
        obj.PostmanName = in.PostmanName;
        obj.PostmanType = in.PostmanType;
        obj.Password = SecurityUtils.md5(in.Password); //MD5 加密
        obj.PlainPassword = in.Password;
        obj.OfficeTel = in.OfficeTel;
        obj.Mobile = in.Mobile;
        obj.Email = in.Email;
        obj.BindCardID = in.BindCardID;
        obj.BindMobile = in.BindMobile;
        obj.ContactAddress = in.ContactAddress;
        obj.InputMobileFlag = in.InputMobileFlag;
        obj.PostmanStatus = SysDict.POSTMAN_STATUS_NORMAL;
        obj.DepartmentID = in.DepartmentID;
        obj.CreateOperID = in.OperID;
        obj.Remark = in.Remark;
        obj.CreateTime = sysDateTime;

        postmanDAO.insert(obj);
        
        ControlParam ctrlParam = ControlParam.getInstance();
        
        //发送短信密码
        if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()) 
        		&&StringUtils.isNotEmpty(in.Mobile)
        		&&"1".equals(ctrlParam.regSentPwdSms))
        {
        	SMSManager.getInstance().sentPostManPwd(obj.DepartmentID, in.Password,in.Mobile);
        }

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        if(operOnline !=null){
            log.StationAddr = operOnline.LoginIPAddr;
        }
        log.Remark = "";

        commonDAO.addOperatorLog(log);

        return result;
    }
    
    private boolean isPhoneNumber(String input){  
	    String regex="1([\\d]{10})";  
	    Pattern p = Pattern.compile(regex); 
	    Matcher m = p.matcher(input);  
	    
	    return m.find();//boolean 
	}  
}
