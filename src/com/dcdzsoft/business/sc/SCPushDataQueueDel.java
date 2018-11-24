package com.dcdzsoft.business.sc;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sequence.SequenceGenerator;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 推送失败，清除数据推送队列 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SCPushDataQueueDel extends ActionBean
{

    public int doBusiness(InParamSCPushDataQueueDel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.MsgUid))
			throw new EduException(ErrorCode.ERR_PARMERR);
        
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		SCPushDataQueueDAO pushDataDAO = daoFactory.getSCPushDataQueueDAO();
        SCPushDataQueue pushData = new SCPushDataQueue();
        pushData.MsgUid = in.MsgUid;
        if(pushDataDAO.isExist(pushData)){
            pushData = pushDataDAO.find(pushData);
            
            if(pushData.FailureCount < Constant.PUSH_FAILURE_COUNT_MAX){
                JDBCFieldArray setCols = new JDBCFieldArray();
                JDBCFieldArray whereCols = new JDBCFieldArray();

                setCols.add("Remark", in.ErrMsg);
                setCols.addSQL(" FailureCount = FailureCount + 1 ");
                whereCols.add("MsgUid", in.MsgUid);
                
                pushDataDAO.update(setCols, whereCols);
            }else{
                //清除数据推送队列
                SCSyncFailWaterDAO failWaterDAO = daoFactory.getSCSyncFailWaterDAO();
                SCSyncFailWater failWater = new SCSyncFailWater();
                failWater.WaterID = SequenceGenerator.getInstance().getNextKey(SequenceGenerator.SEQ_WATERID);
                failWater.TerminalNo = pushData.TerminalNo;
                failWater.PackageID  = in.MsgUid;
                failWater.FailReason = in.ErrMsg;
                failWater.MsgContent = pushData.MsgContent;
                failWater.ServiceName    = pushData.ServiceName;
                failWater.LastModifyTime = sysDateTime;
                failWater.Remark         = "MsgUid="+pushData.MsgUid;
                failWaterDAO.insert(failWater);
                
                pushDataDAO.delete(pushData);
            }
        }
	        
        return result;
    }
}
