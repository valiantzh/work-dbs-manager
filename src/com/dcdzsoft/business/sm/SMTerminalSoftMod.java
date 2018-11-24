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
 * <p>Description: 修改终端软件版本 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SMTerminalSoftMod extends ActionBean
{

    public int doBusiness(InParamSMTerminalSoftMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.SoftwareID)
			||StringUtils.isEmpty(in.SoftwareType)
			||StringUtils.isEmpty(in.TerminalNoStr))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		
		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime(); 
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		if(StringUtils.isEmpty(in.UpdateStatus)){
		    in.UpdateStatus = "0";//0-更新完成
		}
		//检查软件升级包是否存在
		SMUpgradePackDAO upgradePackDAO = daoFactory.getSMUpgradePackDAO();
		SMUpgradePack upgradePack = new SMUpgradePack();
		upgradePack.SoftwareID = in.SoftwareID;
		if(!upgradePackDAO.isExist(upgradePack)){
		    throw new EduException(ErrorCode.ERR_SOFTWAREIDNOTEXIST);
		}
		upgradePack = upgradePackDAO.find(upgradePack);
		
		if(StringUtils.isEmpty(upgradePack.DownloadUrl) 
		        ||StringUtils.isEmpty(upgradePack.SystemID)
		        || StringUtils.isEmpty(upgradePack.SoftSignMd5)){
		    throw new EduException(ErrorCode.ERR_UPGRADEPACKERROR);
		}
		
		//检查软件类型
		if(!in.SoftwareType.equals(upgradePack.SoftwareType)){
		    throw new EduException(ErrorCode.ERR_SOFTWARETYPEERROR);
		}
		//检查软件版本
		if(!in.SoftwareVersion.equals(upgradePack.SoftwareVersion)){
            throw new EduException(ErrorCode.ERR_SOFTWAREVERSIONERROR);
        }
		
		//设置终端软件版本
		SMTerminalSoftDAO terminalSoftDAO = daoFactory.getSMTerminalSoftDAO();
		String[] terminalNoList = in.TerminalNoStr.split(",");
		for(String terminalNo: terminalNoList){
		    SMTerminalSoft terminalSoft = new SMTerminalSoft();
		    terminalSoft.TerminalNo = terminalNo;
		    terminalSoft.SoftwareType = upgradePack.SoftwareType;
		    if(terminalSoftDAO.isExist(terminalSoft)){
		        //
		        JDBCFieldArray setCols = new JDBCFieldArray();
		        JDBCFieldArray whereCols = new JDBCFieldArray();
		        
		        setCols.add("UpdateStatus", in.UpdateStatus);//1-等待更新
		        setCols.add("SystemID", upgradePack.SystemID);
		        setCols.add("SoftwareID", upgradePack.SoftwareID);
		        setCols.add("LastModifyTime", sysDateTime);

		        whereCols.add("TerminalNo", terminalNo);
		        whereCols.add("SoftwareType", upgradePack.SoftwareType);
		        terminalSoftDAO.update(setCols, whereCols);
		        
		    }else{
		        //不存在，添加终端版本
		        terminalSoft.SoftwareID = upgradePack.SoftwareID;
		        terminalSoft.SystemID = upgradePack.SystemID;
		        terminalSoft.UpdateStatus = in.UpdateStatus;//1-等待更新
		        terminalSoft.LastModifyTime = sysDateTime;
		        terminalSoft.LastVersion = "";
		        terminalSoftDAO.insert(terminalSoft);
		    }
		}
		
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = ""+in.TerminalNoStr+","+in.SoftwareID+","+upgradePack.SoftwareVersion;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
