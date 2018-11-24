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
 * <p>Description: 插入对账文件上传日志 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCInsertFtpLog extends ActionBean
{

    public int doBusiness(InParamSCInsertFtpLog in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||in.StoredDate == null )
			throw new EduException(ErrorCode.ERR_PARMERR);

		//4.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		SCFtpLogDAO ftpLogDAO = daoFactory.getSCFtpLogDAO();
		SCFtpLog ftpLog = new SCFtpLog();
		ftpLog.TerminalNo = in.TerminalNo;
		ftpLog.StoredDate = in.StoredDate;
		ftpLog.LastModifyTime = sysDateTime;
		ftpLog.Remark = in.Remark;
		
		
		result = ftpLogDAO.insert(ftpLog);

        return result;
    }
}
