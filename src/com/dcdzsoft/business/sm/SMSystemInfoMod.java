package com.dcdzsoft.business.sm;

import java.util.List;

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
 * <p>Description: 修改系统信息 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SMSystemInfoMod extends ActionBean
{

    public int doBusiness(InParamSMSystemInfoMod in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.SystemID)
			||StringUtils.isEmpty(in.InitPasswd)
			)//||StringUtils.isEmpty(in.TerminalPasswd)
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		
		//////////////检查系统编号不重复////////////////////// 
		SMSystemInfoDAO systemInfoDAO =daoFactory.getSMSystemInfoDAO();
		SMSystemInfo systemInfo = new SMSystemInfo();
		systemInfo.SystemID = in.SystemID;
		if(!systemInfoDAO.isExist(systemInfo)){
		    throw new EduException(ErrorCode.ERR_SYSTEMIDNOTEXIST);
		}
		
		systemInfo = systemInfoDAO.find(systemInfo);
		
		if(!in.InitPasswd.equals(systemInfo.InitPasswd)){
		    OPOperSpeRightDAO operSpeRightDAO = daoFactory.getOPOperSpeRightDAO();
	        OPOperSpeRight speRight = new OPOperSpeRight();
	        speRight.OperID = in.OperID;
	        speRight.SpePrivID = SysDict.SPEPRIV_MODINITPASSWD;
	        
	        if(operSpeRightDAO.isExist(speRight) == false)
	            throw new EduException(ErrorCode.ERR_OPERNOOPENFUNC);
	        
	        //单次有效
	        operSpeRightDAO.delete(speRight);
		}
		
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        setCols.checkAdd("InitPasswd", in.InitPasswd);
        setCols.checkAdd("TerminalPasswd", in.TerminalPasswd);
        setCols.checkAdd("ServerIP", in.ServerIP);
        setCols.checkAdd("ServerPort", in.ServerPort);
        setCols.checkAdd("MonServerIP", in.MonServerIP);
        setCols.checkAdd("MonServerPort", in.MonServerPort);
        setCols.add("UpdateContent", in.UpdateContent);
        setCols.add("Remark", in.Remark);
       
        whereCols.add("SystemID", in.SystemID);
       
        systemInfoDAO.update(setCols, whereCols);
        

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = ""+in.SystemID;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
