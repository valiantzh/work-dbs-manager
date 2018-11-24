package com.dcdzsoft.business.ap;

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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 投递员修改密码 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APPostmanModPwd extends ActionBean
{

    public String doBusiness(InParamAPPostmanModPwd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = "";

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.Mobile)
			||StringUtils.isEmpty(in.NewPassword)
			||StringUtils.isEmpty(in.OldPassword))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		//////////////////////////////////////////////////////////////
		PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = in.Mobile;
        postman  = postmanDAO.find(postman);
        
        if(!postman.Password.equalsIgnoreCase(in.OldPassword))
        	throw new EduException(ErrorCode.ERR_PARMERR);
        
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        setCols.add("Password",in.NewPassword);
        whereCols.add("PostmanID",in.Mobile);
        
        postmanDAO.update(setCols,whereCols);


		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.Mobile;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = "";
		log.Remark = "";

		commonDAO.addOperatorLog(log);


        return in.Mobile;
    }
}
