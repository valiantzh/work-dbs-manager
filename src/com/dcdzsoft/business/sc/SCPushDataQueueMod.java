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
 * <p>Description: 推送成功，修改数据推送队列 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SCPushDataQueueMod extends ActionBean
{

    public int doBusiness(InParamSCPushDataQueueMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.MsgUid))
			throw new EduException(ErrorCode.ERR_PARMERR);
        
		SCPushDataQueueDAO pushDataDAO = daoFactory.getSCPushDataQueueDAO();
        SCPushDataQueue pushData = new SCPushDataQueue();
        pushData.MsgUid = in.MsgUid;
        pushDataDAO.delete(pushData);

        return result;
    }
}
