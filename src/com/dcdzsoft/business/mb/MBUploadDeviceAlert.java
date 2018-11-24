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
import com.dcdzsoft.businessproxy.MonitorProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 上传设备警报信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBUploadDeviceAlert extends ActionBean {

    public String doBusiness(InParamMBUploadDeviceAlert in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = "1";

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.AlertType))
            throw new EduException(ErrorCode.ERR_PARMERR);
        
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        if(StringUtils.isNotEmpty(in.TerminalNo)){
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("TerminalNo", in.TerminalNo);
            whereCols.add("TerminalStatus", SysDict.TERMINAL_STATUS_CANCEL);
            if(terminalDAO.isExist(whereCols)>0){
                //设备注销  不记录报警
                return "0";
            }
        }
        
        commonDAO.insertAlert(in.TerminalNo, in.AlertType, in.AlertLevel, in.BoxNo,"");
        
      	MonitorProxy.broadcastAlert(in.TerminalNo, in.AlertType, in.AlertLevel, in.BoxNo,"");
        
        return result;
    }
}
