package com.dcdzsoft.business.pm;

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
 * <p>Description: 重新获取验证码 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMReGetCheckCode extends ActionBean
{

    public OutParamPMReGetCheckCode doBusiness(InParamPMReGetCheckCode in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPMReGetCheckCode out = new OutParamPMReGetCheckCode();

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.Mobile))
			throw new EduException(ErrorCode.ERR_PARMERR);


		if(!isPhoneNumber(in.Mobile))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		///////////////////生成投递员编号///////////////////////////
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman obj = new PMPostman();
        obj.PostmanID = in.Mobile;
        
        if("1".equalsIgnoreCase(in.ModPwdFlag)){
            //修改密码
            if(!postmanDAO.isExist(obj))
                throw new EduException(ErrorCode.ERR_USERNOTEXIST);
        }else{
            if(postmanDAO.isExist(obj))
                throw new EduException(ErrorCode.ERR_OPERHAVEEXIST);
        }
        

        String rawCode = RandUtils.generateNumber(6);
        //String checkcode = SecurityUtils.md5(rawCode);
        out.CheckCode = rawCode;
        
        ///////////////////////////////////////////////////////////////////
        //发送验证码
        if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()) 
        		&&StringUtils.isNotEmpty(in.Mobile))
        {
            //查询柜体所属运营网点，用于短信数量统计
            TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
            TBTerminal terminal = new TBTerminal();
            terminal.TerminalNo = in.TerminalNo;
            try{
                terminal =  terminalDAO.find(terminal);
            }catch(EduException e){}
            
        	SMSManager.getInstance().sentCheckCode(terminal.DepartmentID, rawCode,in.Mobile);
        }
        
        return out;
    }
    
    private boolean isPhoneNumber(String input){  
	    String regex="1([\\d]{10})";  
	    Pattern p = Pattern.compile(regex); 
	    Matcher m = p.matcher(input);  
	    
	    return m.find();//boolean 
	}  
}
