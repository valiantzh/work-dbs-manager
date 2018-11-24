package com.dcdzsoft.business.ap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
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
 * @author zhengxy
 * @version 1.0
 */

public class APGetCheckCode extends ActionBean
{

    public OutParamAPGetCheckCode doBusiness(InParamAPGetCheckCode in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPGetCheckCode out = new OutParamAPGetCheckCode();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.Mobile))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		if(!isPhoneNumber(in.Mobile))
            throw new EduException(ErrorCode.ERR_PARMERR);
		
		String rawCode = RandUtils.generateNumber(6);
        
        //先删除过期的验证码
        MBBindMobileWaterDAO mobileCodeDAO = daoFactory.getMBBindMobileWaterDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("ExpiredTime","<", sysDateTime);
        mobileCodeDAO.delete(whereCols);
        
        MBBindMobileWater mobileCode  = new MBBindMobileWater();
        mobileCode.VerifyKey = rawCode;
        if(mobileCodeDAO.isExist(mobileCode)){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }
            rawCode = RandUtils.generateNumber(6);
            mobileCode.VerifyKey = rawCode;
            if(mobileCodeDAO.isExist(mobileCode)){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) { }
                rawCode = RandUtils.generateNumber(6);
            }
        }
        mobileCode.VerifyKey   = rawCode;
        mobileCode.BindMobile  = in.Mobile;
        mobileCode.ExpiredTime = new java.sql.Timestamp((sysDateTime.getTime()+1000*60*15));//15分钟
        mobileCode.LastModifyTime  = sysDateTime;
        mobileCodeDAO.insert(mobileCode);
        
        out.CheckCode = rawCode;
        ///////////////////////////////////////////////////////////////////
        //发送验证码
        if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()) 
                &&StringUtils.isNotEmpty(in.Mobile))
        {
            SMSManager.getInstance().sentCheckCode("", rawCode, in.Mobile);
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
