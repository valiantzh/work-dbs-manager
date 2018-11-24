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
import com.dcdzsoft.businessproxy.PushBusinessProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: PDA投递开箱 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APPostmanAppOpenBox extends ActionBean
{

    public OutParamAPPostmanAppOpenBox doBusiness(InParamAPPostmanAppOpenBox in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPPostmanAppOpenBox out = new OutParamAPPostmanAppOpenBox();

		//2.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.PackageID)
			||StringUtils.isEmpty(in.PostmanID)
			||StringUtils.isEmpty(in.BoxNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		PushBusinessProxy.doBusiness(in);
		
		out.BoxNo = in.BoxNo;
		out.PackageID = in.PackageID;
		out.TerminalNo = in.TerminalNo;
		out.TradeWaterNo = in.TradeWaterNo;
		out.Remark      = in.Remark;
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.PostmanID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = "";
		log.Remark = in.toString();

		commonDAO.addOperatorLog(log);


        return out;
    }
}
