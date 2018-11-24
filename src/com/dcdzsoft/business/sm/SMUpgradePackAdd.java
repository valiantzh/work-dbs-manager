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
 * <p>
 * Title: 智能柜运维管理系统
 * </p>
 * <p>
 * Description: 添加软件更新包
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: dcdzsoft
 * </p>
 * 
 * @author 郑晓勇
 * @version 1.0
 */

public class SMUpgradePackAdd extends ActionBean {

	public int doBusiness(InParamSMUpgradePackAdd in) throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		int result = 0;

		// 1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID) 
		        || StringUtils.isEmpty(in.SystemID)
				|| StringUtils.isEmpty(in.SoftwareName)
				|| StringUtils.isEmpty(in.SoftwareType)
				|| StringUtils.isEmpty(in.SoftwareVersion)
				|| StringUtils.isEmpty(in.SoftSignMd5)
				|| StringUtils.isEmpty(in.SystemType)
				|| StringUtils.isEmpty(in.DownloadUrl))
			throw new EduException(ErrorCode.ERR_PARMERR);

		// 2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		// 3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

        //////////////检查系统编号不重复////////////////////// 
        SMSystemInfoDAO systemInfoDAO =daoFactory.getSMSystemInfoDAO();
        SMSystemInfo systemInfo = new SMSystemInfo();
        systemInfo.SystemID = in.SystemID;
        if(!systemInfoDAO.isExist(systemInfo)){
            throw new EduException(ErrorCode.ERR_SYSTEMIDNOTEXIST);
        }
        if (StringUtils.isEmpty(in.SoftwareID)) {
            in.SoftwareID = in.SystemID+"-"+in.SoftwareType;
        }
  
		in.SoftwareName = StringUtils.normalizeName(in.SoftwareName);
		

		// 判断编号是否存在
		SMUpgradePackDAO smUpgradePackDAO = daoFactory.getSMUpgradePackDAO();
		SMUpgradePack smUpgradePack = new SMUpgradePack();
		smUpgradePack.SoftwareID = in.SoftwareID;

		if (smUpgradePackDAO.isExist(smUpgradePack))
			throw new EduException(ErrorCode.ERR_SOFTWAREIDYEXISTS);

		// 插入
		smUpgradePack.SoftwareID = in.SoftwareID;
		smUpgradePack.SystemID = in.SystemID;
		smUpgradePack.SoftwareName = in.SoftwareName;
		smUpgradePack.SoftwareType = in.SoftwareType;
		smUpgradePack.SoftwareVersion = in.SoftwareVersion;
		smUpgradePack.SoftSignMd5 = in.SoftSignMd5;
		smUpgradePack.SystemType = in.SystemType;
		smUpgradePack.DownloadUrl = in.DownloadUrl;
		smUpgradePack.UpdateContent = in.UpdateContent;
		smUpgradePack.LastModifyTime = sysDateTime;
		smUpgradePack.Remark = in.Remark;
		
		smUpgradePackDAO.insert(smUpgradePack);
		//result = smUpgradePackDAO.insert(smUpgradePack);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.SoftwareName;

		commonDAO.addOperatorLog(log);

		return result;
	}
}
