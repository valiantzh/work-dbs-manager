package com.dcdzsoft.business.ap;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 重发取件短信 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APReSentOpenBoxKey extends ActionBean
{

    public int doBusiness(InParamAPReSentOpenBoxKey in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CustomerMobile)
			||StringUtils.isEmpty(in.PackageID)
			//||in.StoredTime == null 
			||StringUtils.isEmpty(in.TerminalNo))
			throw new EduException(ErrorCode.ERR_PARMERR);


		MBPwdShortMsgDAO pwdSMSDAO = daoFactory.getMBPwdShortMsgDAO();
		JDBCFieldArray whereCols0 = new JDBCFieldArray();
		whereCols0.add("PackageID", in.PackageID);
		whereCols0.add("TerminalNo", in.TerminalNo);
		whereCols0.add("StoredTime", in.StoredTime);
		whereCols0.add("CustomerMobile", in.CustomerMobile);
		whereCols0.add("PackageStatus", SysDict.PACKAGE_STATUS_NORMAL);
		
		RowSet rset = pwdSMSDAO.select(whereCols0);
		long WaterID = 0L;
		while(RowSetUtils.rowsetNext(rset))
		{
			WaterID = RowSetUtils.getLongValue(rset, "WaterID");
		}
		
		if(WaterID != 0L){
			MBPwdShortMsg smsObj = new MBPwdShortMsg();
			smsObj.WaterID = WaterID;
			
			smsObj = pwdSMSDAO.find(smsObj);
			
			JDBCFieldArray setCols = new JDBCFieldArray();
			JDBCFieldArray whereCols = new JDBCFieldArray();
			
			//setCols.add("OpenBoxKey",smsObj.OpenBoxKey);
			setCols.add("ReSendNum", smsObj.ReSendNum + 1);
			setCols.add("LastModifyTime", utilDAO.getCurrentDateTime());
			
			whereCols.add("WaterID", WaterID);
			
			pwdSMSDAO.update(setCols, whereCols);
			
			//发送短信
			if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()))
			{
				TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
				TBTerminal terminal = new TBTerminal();
				terminal.TerminalNo = smsObj.TerminalNo;
				
				terminal = terminalDAO.find(terminal);
				
				//////////////
				//modifySMSStat(smsObj.TerminalNo);  //del 20160504  在短信发送线程中统计
				
				////////////////
				java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
				
				SMSInfo smsInfo = new SMSInfo();
				smsInfo.PackageID = smsObj.PackageID;
				smsInfo.TerminalNo = smsObj.TerminalNo;
				smsInfo.StoredTime = smsObj.StoredTime;
				smsInfo.sysDateTime = sysDateTime;
				smsInfo.CustomerMobile = smsObj.CustomerMobile;
				smsInfo.OpenBoxKey = smsObj.OpenBoxKey;
				smsInfo.TerminalName = terminal.TerminalName;
				smsInfo.Location = terminal.Location;
				smsInfo.DepartmentID = terminal.DepartmentID;
				smsInfo.MsgType = SMSInfo.MSG_TYPE_RESENT;
				smsInfo.WaterID = WaterID;
				
				SMSManager.getInstance().sentDeliverySMS(smsInfo);
			}
		}
		
		
        return result;
    }
    
    private void modifySMSStat(String TerminalNo) throws EduException
    {
    	MBSmsStatDAO smsStatDAO = daoFactory.getMBSmsStatDAO();
    	MBSmsStat smsStat = new MBSmsStat();
    	smsStat.TerminalNo = TerminalNo;
    	smsStat.OccurYear = DateUtils.currentYear();
    	smsStat.OccurMonth = DateUtils.currentMonth();
    	
    	if(smsStatDAO.isExist(smsStat))
    	{
    		JDBCFieldArray setCols = new JDBCFieldArray();
    		JDBCFieldArray whereCols = new JDBCFieldArray();
    		
    		setCols.addSQL(" TotalNum=TotalNum+1 ");
    		setCols.addSQL(" PwdNum=PwdNum+1 ");
    		whereCols.add("TerminalNo", TerminalNo);
    		whereCols.add("OccurYear", smsStat.OccurYear);
    		whereCols.add("OccurMonth", smsStat.OccurMonth);
    		
    		smsStatDAO.update(setCols, whereCols);
    	}else
    	{
    		smsStat.TotalNum = 1;
    		smsStat.PwdNum = 1;
    		smsStat.ExpireNum = 0;
    		smsStat.ReminderNum = 0;
    		smsStat.DynamicNum = 0;
    		smsStat.PickupNum = 0;
    		smsStat.OtherNum = 0;
    		
    		smsStatDAO.insert(smsStat);
    	}
    }
}
