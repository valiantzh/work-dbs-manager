package com.dcdzsoft.business.sc;

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

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 重发推送数据 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SCPushDataQueueResend extends ActionBean
{

    public int doBusiness(InParamSCPushDataQueueResend in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
		    || StringUtils.isEmpty(in.MsgUid))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        
		SCPushDataQueueDAO pushDataDAO = daoFactory.getSCPushDataQueueDAO();
		SCPushDataQueue pushData = new SCPushDataQueue();
		pushData.MsgUid = in.MsgUid;
		if(!pushDataDAO.isExist(pushData)){
		    pushData = pushDataDAO.find(pushData);
		    boolean isOk = false;
		    try{
		        String errMsg = WebSocketManager.getInstance().sendPushMsgSync(pushData.MsgUid, pushData.ServiceName, pushData.MsgContent, pushData.TerminalNo);
		        if(ErrorCode.OK_SUCCESS.equals(errMsg)){
		            isOk = true;
		        }
		    }catch(EduException e){
		    }
		    if(isOk){
                pushDataDAO.delete(pushData);
            }else{
                JDBCFieldArray setCols = new JDBCFieldArray();
                JDBCFieldArray whereCols = new JDBCFieldArray();

                setCols.addSQL(" FailureCount = FailureCount + 1 ");
                whereCols.add("MsgUid", in.MsgUid);
                
                pushDataDAO.update(setCols, whereCols);
            }
		}
		

        return result;
    }
}
