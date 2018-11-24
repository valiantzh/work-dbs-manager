package com.dcdzsoft.business.pm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.email.EmailSenderManager;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递员注册 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMPostmanRegister extends ActionBean
{

    public OutParamPMPostmanRegister doBusiness(InParamPMPostmanRegister in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPMPostmanRegister out = new OutParamPMPostmanRegister();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.Mobile)
			||StringUtils.isEmpty(in.PostmanName)
			||StringUtils.isEmpty(in.IDCard)
			||StringUtils.isEmpty(in.Password))
			throw new EduException(ErrorCode.ERR_PARMERR);

		if(!isPhoneNumber(in.Mobile))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		in.PostmanName = StringUtils.normalizeName(in.PostmanName);
        ///////////////////生成投递员编号///////////////////////////
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = in.Mobile;
        
        if(postmanDAO.isExist(postman))
    		throw new EduException(ErrorCode.ERR_OPERHAVEEXIST);
        
        if(StringUtils.isNotEmpty(in.IDCard))
        {
        	JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID",in.IDCard);

            if(postmanDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
        }
        
        postman.BindCardID = in.IDCard;
        //柜端不加密
        String blankPwd = in.Password;
        String pwd = SecurityUtils.md5(blankPwd);//密文
        
        if(StringUtils.isEmpty(in.CompanyID))
        	in.CompanyID = "99990001";
        
        //插入投递员信息
        postman.CompanyID = in.CompanyID;
        postman.PostmanName = in.PostmanName;
        postman.PostmanType = SysDict.POSTMAN_TYPE_POST;
        postman.Password = pwd;
        postman.OfficeTel = "";
        postman.Mobile = in.Mobile;
        postman.Email = "";//////
        postman.IDCard = in.IDCard;
        postman.InputMobileFlag = SysDict.INPUTMOBILE_FLAG_LOCAL;
        postman.PostmanStatus = "0"; 
        postman.CreateOperID = "181818";
        postman.Remark = "Terminal";
        postman.CreateTime = sysDateTime;
        
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        TBTerminal terminal = new TBTerminal();
        terminal.TerminalNo = in.TerminalNo;
        
        terminal = terminalDAO.find(terminal);
        
        if(StringUtils.isNotEmpty(terminal.DepartmentID))
        	postman.DepartmentID = terminal.DepartmentID;
        else
        	postman.DepartmentID = ControlParam.getInstance().headerDepartmentID;

        postmanDAO.insert(postman);

        //发送注册邮件
        if("1".equals(ControlParam.getInstance().getSendRegisterEmailToPM())
        	&& StringUtils.isNotEmpty(postman.Email)){
        	
            PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
            PMCompany company = new PMCompany();
            company.CompanyID = in.CompanyID;
            company = companyDAO.find(company);
        	SMSInfo smsInfo = new SMSInfo();
        	smsInfo.FromEamilAddress = company.Address;//公司邮箱地址-->发件人地址
        	smsInfo.UserName = company.Address;//用户邮箱地址（一般就是发件人地址）
        	smsInfo.EmailPwd = company.Zip;
        	
        	smsInfo.PostmanID = postman.PostmanID;
        	smsInfo.PostmanName = postman.PostmanName;
        	smsInfo.PostmanPwd = in.Password;
        	smsInfo.Email = postman.Email;
        	
			EmailSenderManager.getInstance().sendRegisterEmailToPM(smsInfo);
        }
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.Mobile;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = "";
		log.Remark = in.TerminalNo;

		commonDAO.addOperatorLog(log);


        return out;
    }
    
    private boolean isPhoneNumber(String input){  
	    String regex="1([\\d]{10})";  
	    Pattern p = Pattern.compile(regex); 
	    Matcher m = p.matcher(input);  
	    
	    return m.find();//boolean 
	}  
}
