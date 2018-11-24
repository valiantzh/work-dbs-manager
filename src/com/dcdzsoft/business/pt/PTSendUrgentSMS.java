package com.dcdzsoft.business.pt;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 发送紧急取件短信 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTSendUrgentSMS extends ActionBean
{

    public String doBusiness(InParamPTSendUrgentSMS in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = "";

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CustomerMobile)
			||StringUtils.isEmpty(in.UrgentMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);


		///////////////////////////////////////////////////////////////
		SMSManager.getInstance().sentUrgentSMS(Constant.DEFAULT_HEAD_DEPARTMENTID,in.DynamicCode, in.CustomerMobile, in.UrgentMobile);


        return result;
    }
}
