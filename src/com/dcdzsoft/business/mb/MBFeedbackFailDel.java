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

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 反馈投递信息重新发送 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class MBFeedbackFailDel extends ActionBean
{

    public int doBusiness(InParamMBFeedbackFailDel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		
		if(StringUtils.isEmpty(in.OperID)){
            in.OperID = "181818";
        }else{
            OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
        }
		
		//3.调用UtilDAO.getSysDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);


		PTFeedbackFailDAO feedbackFailDAO = daoFactory.getPTFeedbackFailDAO();
		if("1".equalsIgnoreCase(in.Mode)){
		    JDBCFieldArray whereCols = new JDBCFieldArray();
		    whereCols.add("FailureTime","<",  DateUtils.addTimeStamp(sysDateTime, -90));
		    feedbackFailDAO.delete(whereCols);
        }else{
            if (StringUtils.isEmpty(in.WaterIDList))
                throw new EduException(ErrorCode.ERR_PARMERR);
            String[] waterIDList = in.WaterIDList.split(",");
            for(int i = 0; i < waterIDList.length; i++)
            {
                
                PTFeedbackFail feedbackFail = new PTFeedbackFail();
                feedbackFail.TradeWaterNo = waterIDList[i];
                feedbackFailDAO.delete(feedbackFail);
            }
        }

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = "";
		log.Remark = "";

		commonDAO.addOperatorLog(log);


        return result;
    }
}
