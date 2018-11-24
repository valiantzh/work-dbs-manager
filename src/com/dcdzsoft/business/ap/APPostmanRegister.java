package com.dcdzsoft.business.ap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.config.ApplicationConfig;
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

public class APPostmanRegister extends ActionBean
{

    public String doBusiness(InParamAPPostmanRegister in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        
		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.Mobile)
			||StringUtils.isEmpty(in.PostmanName)
			||StringUtils.isEmpty(in.CompanyID)
			||StringUtils.isEmpty(in.Password))
			throw new EduException(ErrorCode.ERR_PARMERR);

		if(!isPhoneNumber(in.Mobile))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		in.PostmanName = StringUtils.normalizeName(in.PostmanName);
        ///////////////////生成投递员编号///////////////////////////
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman obj = new PMPostman();
        obj.PostmanID = in.Mobile;
        
        if(postmanDAO.isExist(obj))
    		throw new EduException(ErrorCode.ERR_OPERHAVEEXIST);
        
        StringBuffer sb = new StringBuffer();
        if(StringUtils.isNotEmpty(in.IDCard))
        {
        	JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID",in.IDCard);

            //if(postmanDAO.isExist(whereCols0) > 0){
                //throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
            //}
            
            RowSet rset = postmanDAO.select(whereCols0);
            while(RowSetUtils.rowsetNext(rset)){
            	//注销掉原来的
            	String postmanid = RowSetUtils.getStringValue(rset, "PostmanID");
            	
            	JDBCFieldArray setCols1 = new JDBCFieldArray();
            	JDBCFieldArray whereCols1 = new JDBCFieldArray();
            	
            	//setCols1.add("BindCardID","");
            	setCols1.add("PostmanStatus", SysDict.POSTMAN_STATUS_CANCEL);
            	whereCols1.add("PostmanID", postmanid);
            	
            	postmanDAO.update(setCols1, whereCols1);
            	
            	sb.append(postmanid + "|");
            }
        }
        
        obj.BindCardID = in.IDCard;
        
        String pwd = "";
        if(StringUtils.isEmpty(in.Password)){
            in.Password = RandUtils.generateNumber(6);
        }
            
        if(StringUtils.length(in.Password) == 32){
            pwd = in.Password;
        }else{
            pwd = SecurityUtils.md5(in.Password);
        }
        
        //插入投递员信息
        obj.CompanyID = in.CompanyID;
        obj.PostmanName = in.PostmanName;
        obj.PostmanType = SysDict.POSTMAN_TYPE_POST;
        obj.Password = pwd;
        obj.OfficeTel = "";
        obj.Mobile = in.Mobile;
        obj.Email = "";
        obj.IDCard = in.IDCard;
        obj.InputMobileFlag = SysDict.INPUTMOBILE_FLAG_LOCAL;
        obj.PostmanStatus = SysDict.POSTMAN_STATUS_NORMAL;
        obj.DepartmentID = ControlParam.getInstance().headerDepartmentID;
        obj.CreateOperID = "181818";
        obj.Remark = "App";
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
		log.OperID = in.Mobile;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = "";
		log.Remark = sb.toString() + "," + in.IDCard;

		commonDAO.addOperatorLog(log);


        return in.Mobile;
    }
    
    private boolean isPhoneNumber(String input){  
	    String regex="1([\\d]{10})";  
	    Pattern p = Pattern.compile(regex); 
	    Matcher m = p.matcher(input);  
	    
	    return m.find();//boolean 
	}  
}
