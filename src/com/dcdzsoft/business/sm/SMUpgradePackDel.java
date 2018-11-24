package com.dcdzsoft.business.sm;

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
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 删除软件更新包 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SMUpgradePackDel extends ActionBean
{

    public int doBusiness(InParamSMUpgradePackDel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.SoftwareID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		SMTerminalSoftDAO smTerminalSoftDAO=daoFactory.getSMTerminalSoftDAO();

	    JDBCFieldArray whereCols = new JDBCFieldArray();
	    whereCols.add("SoftwareID", in.SoftwareID);

	    if (smTerminalSoftDAO.isExist(whereCols) > 0)
	    	throw new EduException(ErrorCode.ERR_FORBIDDELUPGRADEPACK);
	    
	    //4.调用smUpgradePackDAO.delete()删除
	    SMUpgradePackDAO smUpgradePackDAO = daoFactory.getSMUpgradePackDAO();
	    smUpgradePackDAO.delete(whereCols);


		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = ""+in.SoftwareID;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
