package com.dcdzsoft.business.sc;

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
 * <p>Description: 添加推送数据到队列 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SCPushDataQueueAdd extends ActionBean
{

    public int doBusiness(InParamSCPushDataQueueAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
		    || StringUtils.isEmpty(in.ServiceName)
		    || StringUtils.isEmpty(in.MsgContent))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        
        if(StringUtils.isEmpty(in.MsgUid)){
            in.MsgUid = WebUtils.getUmid();
        }
        
		SCPushDataQueueDAO pushDataDAO = daoFactory.getSCPushDataQueueDAO();
		SCPushDataQueue pushData = new SCPushDataQueue();
		pushData.MsgUid = in.MsgUid;
		if(!pushDataDAO.isExist(pushData)){
		    pushData.FailureCount = 0;
	        pushData.MsgContent   = in.MsgContent;
	        pushData.ServiceName  = in.ServiceName;
	        pushData.TerminalNo   = in.TerminalNo;
	        pushData.LastModifyTime = sysDateTime;
	        pushDataDAO.insert(pushData);
		}
		

        return result;
    }
}
