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
 * <p>Description: 黑名单增加 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBMobileBlackListAdd extends ActionBean
{

    public int doBusiness(InParamMBMobileBlackListAdd in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.CustomerMobile))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		/////////////////////////////////////////////////////////////
		MBMobileBlackListDAO blackListDAO = daoFactory.getMBMobileBlackListDAO();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		whereCols.add("CustomerMobile", in.CustomerMobile);
		
		if(blackListDAO.isExist(whereCols) > 0)
			throw new EduException(ErrorCode.ERR_RECORDEXISTS);
		
		
		////////////////////////////////////////////////////////////////////
		MBMobileBlackList blackList = new MBMobileBlackList();
		blackList.CustomerMobile = in.CustomerMobile;
		blackList.LastModifyTime = sysDateTime;
		
		blackListDAO.insert(blackList);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.CustomerMobile;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
