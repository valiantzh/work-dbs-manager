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
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递员修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMPostmanMod extends ActionBean {

    public int doBusiness(InParamPMPostmanMod in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.PostmanID)
            || StringUtils.isEmpty(in.PostmanName)
            || StringUtils.isEmpty(in.PostmanType))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = null;
        if(StringUtils.isNotEmpty(in.OperID)){
            operOnline = commonDAO.isOnline(in.OperID);
            if(StringUtils.isEmpty(in.DepartmentID)){
                in.DepartmentID = operOnline.DepartmentID;
            }
        }

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //////////////////////////////////////////////////////////
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = in.PostmanID;
        postman = postmanDAO.find(postman);
        
        if(StringUtils.isNotEmpty(in.BindCardID) && !in.BindCardID.equals(postman.BindCardID))
        {
        	JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID",in.BindCardID);

            if(postmanDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
        }
        
        if(StringUtils.isNotEmpty(in.BindMobile) && !in.BindMobile.equals(postman.BindMobile))
        {
        	//手机号格式判定
//        	if(!this.isPhoneNumber(in.BindMobile))
//        		throw new EduException(ErrorCode.ERR_BUSINESS_MOBILEFORMATERROR);
//        	
        	JDBCFieldArray whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindMobile",in.BindMobile);

            if(postmanDAO.isExist(whereCols0) > 0)
                throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDMOBILE);
        }

        ////////////////////////////////////////////////////////
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        //setCols.add("CompanyID",in.CompanyID);
        setCols.checkAdd("PostmanName",in.PostmanName);
        setCols.checkAdd("Mobile",in.Mobile);
        setCols.checkAdd("Email",in.Email);
        setCols.checkAdd("PostmanType",in.PostmanType);
        setCols.checkAdd("OfficeTel",in.OfficeTel);
        setCols.checkAdd("ContactAddress",in.ContactAddress);
        setCols.checkAdd("BindCardID", in.BindCardID);
        setCols.checkAdd("BindMobile", in.BindMobile);
        setCols.checkAdd("InputMobileFlag",in.InputMobileFlag);
        setCols.checkAdd("PostmanStatus",in.PostmanStatus);
        setCols.checkAdd("IDCard", in.IDCard);
        setCols.checkAdd("Remark",in.Remark);

        whereCols.add("PostmanID",in.PostmanID);

        postmanDAO.update(setCols,whereCols);


        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        if(operOnline != null){
            log.StationAddr = operOnline.LoginIPAddr;
        }
        
        log.Remark = in.toString();

        commonDAO.addOperatorLog(log);

        return result;
    }
    //中国大陆手机号格式判定
    private boolean isPhoneNumber(String input){  
	    String regex="1([\\d]{10})";  
	    Pattern p = Pattern.compile(regex); 
	    Matcher m = p.matcher(input);  
	    
	    return m.find();//boolean 
	}  
}
