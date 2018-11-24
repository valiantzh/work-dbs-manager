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
 * <p>Description: 设置故障原因 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class RMSetFaultReason extends ActionBean
{

    public int doBusiness(InParamRMSetFaultReason in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.AppealNo))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		RMAppealLogDAO appealLogDAO = daoFactory.getRMAppealLogDAO();
		RMAppealLog appealLog = new RMAppealLog();
		appealLog.AppealNo = in.AppealNo;
		appealLog = appealLogDAO.find(appealLog);
		
		JDBCFieldArray setCols = new JDBCFieldArray();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		
		setCols.add("FaultReason", in.FaultReason);
		setCols.add("LastModifyTime", sysDateTime);
		if(StringUtils.isNotEmpty(in.AppealType))
			setCols.add("AppealType", in.AppealType);
		
		whereCols.add("AppealNo", in.AppealNo);
		
		result = appealLogDAO.update(setCols, whereCols);
		
		TBServerBoxDAO boxDAO = daoFactory.getTBServerBoxDAO();
        TBServerBox box = new TBServerBox();
        box.BoxNo = appealLog.BoxNo;
        box.TerminalNo = appealLog.TerminalNo;

        box = boxDAO.find(box);

        int boxStatus = NumberUtils.parseInt(box.BoxStatus);
        if (SysDict.BOX_FAULTSTATUS_NO.equalsIgnoreCase(in.FaultStatus)) //正常
            boxStatus = boxStatus & 1;
        else //设为故障
            boxStatus = boxStatus | 2;

        //状态发生变化
        if(!box.BoxStatus.equalsIgnoreCase(String.valueOf(boxStatus)))
        {
        	//修改状态
            JDBCFieldArray setCols1 = new JDBCFieldArray();
            JDBCFieldArray whereCols1 = new JDBCFieldArray();

            setCols1.add("BoxStatus", String.valueOf(boxStatus));
            whereCols1.add("BoxNo", appealLog.BoxNo);
            whereCols1.add("TerminalNo", appealLog.TerminalNo);

            boxDAO.update(setCols1, whereCols1);
            
            InParamTBFaultStatusMod inParam = new InParamTBFaultStatusMod();
            inParam.OperID = in.OperID;
            inParam.TerminalNo = appealLog.TerminalNo;
            inParam.BoxNo = appealLog.BoxNo;
            inParam.RemoteFlag = SysDict.OPER_FLAG_REMOTE;
            inParam.FaultStatus = in.FaultStatus;
            
            ///推送到设备端
            PushBusinessProxy.doBusiness(inParam);
        }

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = "";

		commonDAO.addOperatorLog(log);
		
        return result;
    }
}
