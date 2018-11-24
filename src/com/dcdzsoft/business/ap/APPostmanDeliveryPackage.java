package com.dcdzsoft.business.ap;


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
 * <p>Description: PDA确认投递（发送到柜端） </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APPostmanDeliveryPackage extends ActionBean
{

    public OutParamAPPostmanDeliveryPackage doBusiness(InParamAPPostmanDeliveryPackage in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPPostmanDeliveryPackage out = new OutParamAPPostmanDeliveryPackage();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.PostmanID)
			||StringUtils.isEmpty(in.PackageID)
			||StringUtils.isEmpty(in.BoxNo)
			||StringUtils.isEmpty(in.CustomerMobile)
			||StringUtils.isEmpty(in.LeftFlag))
			throw new EduException(ErrorCode.ERR_PARMERR);

		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		//
        String msgUid = WebUtils.getUmid();
        int result = 0;
        String errMsg = "";
        try{
            errMsg = PushBusinessProxy.doBusiness(in, msgUid);
        }catch(EduException e){
            System.err.println(e.getMessage());
        }
        
        out.BoxNo = in.BoxNo;
        out.PackageID = in.PackageID;
        out.TerminalNo = in.TerminalNo;
        out.TradeWaterNo = in.TradeWaterNo;
        
        if(ErrorCode.OK_SUCCESS.equals(errMsg)){
            out.Remark = "OK";
        }else{
            System.out.println(in.toString());
            //插入推送队列
            SCPushDataQueueDAO pushDataDAO = daoFactory.getSCPushDataQueueDAO();
            SCPushDataQueue pushData = new SCPushDataQueue();
            pushData.MsgUid = msgUid;
            pushData.ServiceName = in.getClass().getSimpleName();
            pushData.TerminalNo  = in.TerminalNo;
            pushData.MsgContent  = JsonUtils.dtoToJson(in);
            pushData.LastModifyTime = sysDateTime;
            pushData.FailureCount   = 1;
            pushData.Remark = errMsg;
            pushDataDAO.insert(pushData);
            out.Remark = "FAIL";
        }
        
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
