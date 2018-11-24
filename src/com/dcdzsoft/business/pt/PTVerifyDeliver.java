package com.dcdzsoft.business.pt;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.util.*;

import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;

import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 用户验证-投递账号验证 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class PTVerifyDeliver extends ActionBean
{

    public OutParamPTVerifyDeliver doBusiness(InParamPTVerifyDeliver in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTVerifyDeliver out = new OutParamPTVerifyDeliver();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.getTerminalNo())
			||StringUtils.isEmpty(in.getLoginPwd()))
			throw new EduException(ErrorCode.ERR_PARMERR);

		ControlParam  controlParam = ControlParam.getInstance();
		PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        //MD5加密
       /* if(SysDict.POSTMAN_PWD_MD5_YES.equals(controlParam.postmanMD5Flag)){
            in.setLoginPwd(SecurityUtils.md5(in.getLoginPwd()).toLowerCase());
            whereCols.addSQL(" AND Password = " + StringUtils.addQuote(in.LoginPwd) + "");
        }*/
        ///投递员登录类型    1-投递员 2-房管 3-管理员
        if(SysDict.POSTMAN_TYPE_POST.equals(in.getLoginType())){
            whereCols.addSQL(" AND PostmanType = " + StringUtils.addQuote(in.LoginType) + "");
        }
        if(SysDict.POSTMAN_TYPE_LEFT.equals(in.getLoginType())){
            whereCols.addSQL(" AND PostmanType = " + StringUtils.addQuote(in.LoginType) + "");
        }
        if(SysDict.POSTMAN_TYPE_CLEAR.equals(in.getLoginType())){
            whereCols.addSQL(" AND PostmanType = " + StringUtils.addQuote(in.LoginType) + "");
        }
        if(SysDict.POSTMAN_VERIFY_PWD.equals(controlParam.postmanCheckModel)){
            whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
            whereCols.addSQL(" AND PlainPassword = " + StringUtils.addQuote(in.LoginPwd) + "");
        }
        
        RowSet rset = postmanDAO.select(whereCols);
        if(RowSetUtils.rowsetNext(rset)){
            if(!RowSetUtils.getStringValue(rset, "PostmanStatus").equalsIgnoreCase(SysDict.SMS_ACCOUNT_STATUS_NORMAL)){
                throw new EduException(ErrorCode.ERR_POSTMANHAVECANCELED);
            }
            out.setCompanyID(RowSetUtils.getStringValue(rset, "CompanyID"));
            out.setDynamicCode(RowSetUtils.getStringValue(rset, "PlainPassword"));
            out.setOfBureau(RowSetUtils.getStringValue(rset, "DepartmentID"));
            out.setPostmanID(RowSetUtils.getStringValue(rset, "PostmanID"));
            out.setPostmanName(RowSetUtils.getStringValue(rset, "PostmanName"));
            out.setPostmanType(RowSetUtils.getStringValue(rset, "PostmanType"));
            //out.setVerifyFlag(RowSetUtils.getStringValue(rset, "VerifyFlag"));
            //out.setBoxList(RowSetUtils.getStringValue(rset, "BoxList"));
            out.setRemark(RowSetUtils.getStringValue(rset, "Remark"));
        }else{
            if(SysDict.POSTMAN_VERIFY_PWD.equals(controlParam.postmanCheckModel)){
                throw new EduException(ErrorCode.ERR_OPERPWDWRONG);
            }else{
                throw new EduException(ErrorCode.ERR_USERNOTEXIST);
            }
            
        }
        return out;
    }
}
