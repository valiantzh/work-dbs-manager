package com.dcdzsoft.business.pm;

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
 * <p>Description: 物流公司修改 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PMLogisticsMod extends ActionBean
{

    public int doBusiness(InParamPMLogisticsMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.LogisticsID)
			||StringUtils.isEmpty(in.LogisticsName))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		
		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		////////////////////////////////////////////////////////////////
		in.LogisticsName = StringUtils.normalizeName(in.LogisticsName);
        
		//////////////检查公司名称不重复////////////////////////////////
		PMLogisticsDAO logisticsDAO = daoFactory.getPMLogisticsDAO();

        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("LogisticsName", in.LogisticsName);
        whereCols0.add("LogisticsID", "<>", in.LogisticsID);
        if (logisticsDAO.isExist(whereCols0) > 0)
            throw new EduException(ErrorCode.ERR_COMPANYNAMEEXISTS);

        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        setCols.add("LogisticsName", in.LogisticsName);
        setCols.add("PinyinCode", in.PinyinCode);
        setCols.add("Remark", in.Remark);

        whereCols.add("LogisticsID", in.LogisticsID);

        logisticsDAO.update(setCols, whereCols);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.LogisticsName;

		commonDAO.addOperatorLog(log);

        return result;
    }
}
