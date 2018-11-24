package com.dcdzsoft.business.mb;

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
 * <p>Description: 修改短消息发送状态 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBModSMSSentStatus extends ActionBean
{

    public int doBusiness(InParamMBModSMSSentStatus in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (in.WaterID <= 0
//			||StringUtils.isEmpty(in.SendStatus)
			)
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		////////////////////////////////////////////////////
		MBPwdShortMsgDAO pwdSMSDAO = daoFactory.getMBPwdShortMsgDAO();
		JDBCFieldArray setCols = new JDBCFieldArray();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		whereCols.add("WaterID", in.WaterID);
		String ReSendNum = pwdSMSDAO.selectFunctions("ReSendNum", whereCols);
		setCols.add("SendStatus",in.SendStatus);
		setCols.add("LastModifyTime", sysDateTime);
		setCols.add("ReSendNum",Integer.parseInt(ReSendNum)+1);

		pwdSMSDAO.update(setCols, whereCols);
		
        return result;
    }
}
