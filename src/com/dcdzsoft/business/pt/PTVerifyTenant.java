package com.dcdzsoft.business.pt;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 用户验证-住户账号验证 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class PTVerifyTenant extends ActionBean
{

    public OutParamPTVerifyTenant doBusiness(InParamPTVerifyTenant in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTVerifyTenant out = new OutParamPTVerifyTenant();
        RowSet rset = null;
		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.LoginPwd))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		ControlParam  controlParam = ControlParam.getInstance();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        
		/*if(SysDict.PASSWD_CHECKMODEL_COMMON.equals(controlParam.passwdCheckModel)){//MD5加密
		    in.setLoginPwd(SecurityUtils.md5(in.getLoginPwd()).toLowerCase());
		}*/
		/*if(SysDict.POSTMAN_VERIFY_PWD.equals(in.getLoginWay())){//密码认证
		    whereCols.addSQL(" AND PlainPassword = " + StringUtils.addQuote(in.getLoginPwd()) + "");
		}else if(SysDict.POSTMAN_VERIFY_CODE.equals(in.getLoginWay())){//二维码认证
           
        }*/
		whereCols.addSQL(" AND PlainPassword = " + StringUtils.addQuote(in.getLoginPwd()) + "");		
		String sql = "SELECT * FROM V_CMDeliverServiceUser2pc a WHERE 1=1 " + whereCols.toWhereSQL();
	    rset = dbSession.executeQuery(sql);
	    if (RowSetUtils.rowsetNext(rset)){
	        if(!RowSetUtils.getStringValue(rset, "ServiceStatus").equalsIgnoreCase(SysDict.CUSTOMER_STATUS_NROMAL)){
	            throw new EduException(ErrorCode.ERR_CUSTOMERHAVECANCELED);
	        }
	        out.setFirstName(RowSetUtils.getStringValue(rset, "FirstName"));
	        out.setLastName(RowSetUtils.getStringValue(rset, "LastName"));
	        out.setCustomerID(RowSetUtils.getStringValue(rset, "CustomerID"));
	        out.setCustomerMobile(RowSetUtils.getStringValue(rset, "CustomerMobile"));
	        out.setCustomerName(RowSetUtils.getStringValue(rset, "CustomerName"));
	        out.setAddress(RowSetUtils.getStringValue(rset, "Address"));
	        out.setEmail(RowSetUtils.getStringValue(rset, "Email"));
	        out.setSpecialFlag(RowSetUtils.getStringValue(rset, "SpecialFlag"));
	    }else{
	        throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
	    }
        return out;
    }
}
