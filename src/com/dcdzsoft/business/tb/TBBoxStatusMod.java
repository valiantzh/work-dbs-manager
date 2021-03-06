package com.dcdzsoft.business.tb;

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
 * <p>Description: 箱体状态修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class TBBoxStatusMod extends ActionBean {

    public String doBusiness(InParamTBBoxStatusMod in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = in.BoxNo;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.BoxNo)
            || StringUtils.isEmpty(in.BoxStatus))
            throw new EduException(ErrorCode.ERR_PARMERR);

        if (StringUtils.isEmpty(in.RemoteFlag))
            in.RemoteFlag = SysDict.OPER_FLAG_REMOTE;

        if (in.RemoteFlag.equalsIgnoreCase(SysDict.OPER_FLAG_REMOTE)) {
            //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
            OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
        }

        //修改状态
        TBServerBoxDAO boxDAO = daoFactory.getTBServerBoxDAO();
        JDBCFieldArray setCols1 = new JDBCFieldArray();
        JDBCFieldArray whereCols1 = new JDBCFieldArray();

        setCols1.add("BoxStatus", in.BoxStatus);
        whereCols1.add("BoxNo", in.BoxNo);
        whereCols1.add("TerminalNo", in.TerminalNo);

        boxDAO.update(setCols1, whereCols1);

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = null;
        log.StationAddr = "";
        log.Remark = in.RemoteFlag;

        commonDAO.addOperatorLog(log);

        if (in.RemoteFlag.equalsIgnoreCase(SysDict.OPER_FLAG_REMOTE))
        {
            ///推送到设备端
        	try
        	{
        		PushBusinessProxy.doBusiness(in);
        	}catch(EduException e)
        	{
        		
        	}
        }

        return result;
    }
}
