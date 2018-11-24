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
 * <p>Description: 同步管理端操作日志 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SCSyncManagerLog extends ActionBean
{

    public String doBusiness(InParamSCSyncManagerLog in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = "";

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		if(in.OccurTime == null) //有时间查一下为何客户端传上来的是1970-01-01 08:00:00.0
			in.OccurTime = utilDAO.getCurrentDateTime();
		
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.FactFunctionID;
		log.OccurTime = in.OccurTime;
		log.StationAddr = "";
		log.TerminalNo = in.TerminalNo;
		log.Remark = in.Remark+","+in.TradeWaterNo;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
