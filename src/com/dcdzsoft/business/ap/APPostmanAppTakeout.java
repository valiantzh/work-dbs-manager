package com.dcdzsoft.business.ap;

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
 * <p>Description: 投递员APP取回 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APPostmanAppTakeout extends ActionBean
{

	 public String doBusiness(InParamAPPostmanAppTakeout in) throws EduException
	    {
	        utilDAO = this.getUtilDAO();
	        commonDAO = this.getCommonDAO();
	        dbSession = this.getCurrentDBSession();
	        String result = "";

			//1.	验证输入参数是否有效，如果无效返回-1。
			if (StringUtils.isEmpty(in.PostmanID)
				||StringUtils.isEmpty(in.CustomerMobile)
				||StringUtils.isEmpty(in.PackageID)
				||StringUtils.isEmpty(in.TerminalNo)
				||StringUtils.isEmpty(in.TradeWaterNo))
				throw new EduException(ErrorCode.ERR_PARMERR);
			
			
			//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
			java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
			java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

			/*PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
			PTInBoxPackage inboxPack = new PTInBoxPackage();
			inboxPack.TerminalNo = in.TerminalNo;
			inboxPack.PackageID = in.PackageID;

			try {
				inboxPack = inboxPackDAO.find(inboxPack);
			}catch(Exception e)
			{
				throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);
			}
			
			if (!in.CustomerMobile.equals(inboxPack.CustomerMobile)
				||!in.TradeWaterNo.equals(inboxPack.TradeWaterNo)
				||!in.PostmanID.equals(inboxPack.PostmanID))
				throw new EduException(ErrorCode.ERR_PARMERR);*/
			
			//下行业务
			PushBusinessProxy.doBusiness(in);
			
			try{
				Thread.sleep(1000);
			}catch(Exception e)
			{
				
			}

			// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
			OPOperatorLog log = new OPOperatorLog();
			log.OperID = in.PostmanID;
			log.FunctionID = in.getFunctionID();
			log.OccurTime = sysDateTime;
			log.StationAddr = "";
			log.Remark = in.TerminalNo + "," + in.PackageID;

			commonDAO.addOperatorLog(log);


	        return in.CustomerMobile;
	    }
}
