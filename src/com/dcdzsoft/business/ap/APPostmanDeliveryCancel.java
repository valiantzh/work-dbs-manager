package com.dcdzsoft.business.ap;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.websocket.WebSocketManager;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;

import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.businessproxy.PushBusinessProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: PDA取消投递 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class APPostmanDeliveryCancel extends ActionBean
{

    public OutParamAPPostmanDeliveryCancel doBusiness(InParamAPPostmanDeliveryCancel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPPostmanDeliveryCancel out = new OutParamAPPostmanDeliveryCancel();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.PostmanID)
			||StringUtils.isEmpty(in.PackageID)
			||StringUtils.isEmpty(in.BoxNo))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		//
		String msgUid = WebUtils.getUmid();
        String errMsg = "";
		try{
		    errMsg = PushBusinessProxy.doBusiness(in, msgUid);
		}catch(EduException e){
		    System.err.println(e.getMessage());
		}
		out.PackageID    = in.PackageID;
		out.TerminalNo   = in.TerminalNo;
		out.BoxNo        = in.BoxNo;
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
        return out;
    }
}
