package com.dcdzsoft.business.rm;

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
 * <p>Description: 远程开大箱 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class RMRemoteOpenBox extends ActionBean
{

    public int doBusiness(InParamRMRemoteOpenBox in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.AppealNo)
			||StringUtils.isEmpty(in.OpenBoxKey)
			||StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

		///////////////////////////////////////////////////////////////////
		RMAppealLogDAO appealLogDAO = daoFactory.getRMAppealLogDAO();
        RMAppealLog appealLog = new RMAppealLog();
        appealLog.AppealNo = in.AppealNo;
        appealLog = appealLogDAO.find(appealLog);

        /////////////////////////////////////////////////////////////////////
        long difference = sysDateTime.getTime() - appealLog.AppealTime.getTime(); 
		long minute = difference/(60*1000);
		
		if(minute > 5)
		{
			throw new EduException(ErrorCode.ERR_BUSINESS_REMOTEBOX_TIMEOUT);
		}
		
        /////////////////////////////////////////////////////////////////////
        OPOperSpeRightDAO operSpeRightDAO = daoFactory.getOPOperSpeRightDAO();
        OPOperSpeRight speRight = new OPOperSpeRight();
        speRight.OperID = in.OperID;
        speRight.SpePrivID = SysDict.SPEPRIV_OPENBOX;
        
        if(operSpeRightDAO.isExist(speRight) == false)
        	throw new EduException(ErrorCode.ERR_OPERNOOPENFUNC);
        
        ///推送到设备端
        PushBusinessProxy.doBusiness(in);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.TerminalNo + "," + appealLog.BoxNo;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
