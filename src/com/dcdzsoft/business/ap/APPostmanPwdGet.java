package com.dcdzsoft.business.ap;

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
 * <p>Description: 获取投递员登录凭证 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APPostmanPwdGet extends ActionBean
{

    public OutParamAPPostmanPwdGet doBusiness(InParamAPPostmanPwdGet in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPPostmanPwdGet out = new OutParamAPPostmanPwdGet();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.Mobile))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		String postmanid = "";
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("PostmanID", in.Mobile);
        RowSet rset = postmanDAO.select(whereCols0);
        if (RowSetUtils.rowsetNext(rset)) {
            postmanid = RowSetUtils.getStringValue(rset, "PostmanID");
        } else {
            whereCols0 = new JDBCFieldArray();
            whereCols0.add("BindCardID", in.Mobile);
            
            rset = postmanDAO.select(whereCols0);

            if (RowSetUtils.rowsetNext(rset)) {
                postmanid = RowSetUtils.getStringValue(rset, "PostmanID");
            } else {
                //用户名或密码错误
                 throw new EduException(ErrorCode.ERR_WRONGPWDORUSERID);
            }
        }
        
        PMPostman postman = new PMPostman();
        postman.PostmanID = postmanid;
        postman = postmanDAO.find(postman);
        
        if(!postman.PostmanStatus.equals(SysDict.POSTMAN_STATUS_NORMAL))
        {
            throw new EduException(ErrorCode.ERR_POSTMANHAVECANCELED);
        }
        out.Mobile = in.Mobile;
        out.Password = postman.Password;
        
        return out;
    }
}
