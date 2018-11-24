package com.dcdzsoft.business.mb;

import java.util.Calendar;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sequence.SequenceGenerator;
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
 * <p>Description: 发送催领短消息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBSendReminderMsg extends ActionBean
{

    public int doBusiness(InParamMBSendReminderMsg in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;
        
        //1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo) && StringUtils.isEmpty(in.PackageID))
			throw new EduException(ErrorCode.ERR_PARMERR);
      	
    	PTInBoxPackageDAO inBoxPackageDAO = daoFactory.getPTInBoxPackageDAO();
    	PTInBoxPackage inboxPack = new PTInBoxPackage();
        inboxPack.PackageID = in.PackageID;
        inboxPack.TerminalNo = in.TerminalNo;
        
        inboxPack = inBoxPackageDAO.find(inboxPack);
        
        //插入催领流水
        MBReminderWaterDAO waterDAO = daoFactory.getMBReminderWaterDAO();
        MBReminderWater water = new MBReminderWater();

        SequenceGenerator seqGen = SequenceGenerator.getInstance();
        water.WaterID = seqGen.getNextKey(SequenceGenerator.SEQ_WATERID);
        water.TerminalNo = inboxPack.TerminalNo;
        water.CustomerMobile = inboxPack.CustomerMobile;
        water.PackageID = inboxPack.PackageID;
        water.PostmanID = inboxPack.PostmanID;
        water.StoredDate = inboxPack.StoredDate;
        water.StoredTime = inboxPack.StoredTime;
        water.ReminderNum = 1;
        water.LastModifyTime = utilDAO.getCurrentDateTime();
        
        waterDAO.insert(water);
        
       // //////////////////////////////////////////////////////////////////////
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        
        MBPwdShortMsgDAO shortMsgDAO = daoFactory.getMBPwdShortMsgDAO();
		JDBCFieldArray whereCols2 = new JDBCFieldArray();
		whereCols2.add("PackageID", inboxPack.PackageID);
		whereCols2.add("TerminalNo", inboxPack.TerminalNo);
		whereCols2.add("StoredTime", inboxPack.StoredTime);
//		whereCols2.add("ReSendNum","<",2);//顺丰取件柜
		
		String OpenBoxKey = "";
		int WaterID = 0;
		RowSet rset = shortMsgDAO.select(whereCols2);
		while(RowSetUtils.rowsetNext(rset)){
			OpenBoxKey = RowSetUtils.getStringValue(rset, "OpenBoxKey");
			WaterID = RowSetUtils.getIntValue(rset, "WaterID");
		}
        
        //发送催领短信
        if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()) &&
        	StringUtils.isNotEmpty(OpenBoxKey))
        {
        	///////////////////////////////////////////////////
        	TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        	TBTerminal terminal = new TBTerminal();
        	terminal.TerminalNo = in.TerminalNo;
        	
        	terminal = terminalDAO.find(terminal);
        	
			//////////////////////////////////////////
			//modifySMSStat(in.TerminalNo,terminal.Location);//del 20160504  在短信发送线程中统计
        	
        	SMSInfo smsInfo = new SMSInfo();
        	smsInfo.WaterID = WaterID;
        	smsInfo.PackageID = in.PackageID;
        	smsInfo.TerminalNo = in.TerminalNo;
        	smsInfo.StoredTime = inboxPack.StoredTime;
        	smsInfo.BoxNo = inboxPack.BoxNo;
        	smsInfo.StaOrderID = inboxPack.StaOrderID;
        	smsInfo.sysDateTime = sysDateTime;
        	smsInfo.CustomerMobile = inboxPack.CustomerMobile;
        	smsInfo.OpenBoxKey = OpenBoxKey;
        	smsInfo.TerminalName = terminal.TerminalName;
        	smsInfo.DepartmentID = terminal.DepartmentID;
        	smsInfo.Location = terminal.Location;
        	smsInfo.MsgType = SMSInfo.MSG_TYPE_REMINDER;
        	smsInfo.Remark = inboxPack.Remark;
        	//需求发送提醒短信加上逾期时间
        	if(inboxPack.ExpiredTime == null || inboxPack.ExpiredTime.before(inboxPack.StoredTime)){
        		//从控制参数里面设置的逾期时间
	            ControlParam ctrlParam = ControlParam.getInstance();
	        	int expiredDays = NumberUtils.parseInt(ctrlParam.recoveryTimeout);	
	        	java.sql.Date expiredDate = DateUtils.addDate(inboxPack.StoredDate,-expiredDays);
	        	smsInfo.ExpiredTime = new java.sql.Timestamp(expiredDate.getTime());
        	}else{
        		smsInfo.ExpiredTime = inboxPack.ExpiredTime;
        	}
        	SMSManager.getInstance().sentReminderSMS(smsInfo);
        }
        
        return result;
    }
    
    private void modifySMSStat(String TerminalNo,String location) throws EduException
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
    		
    		if(StringUtils.isNotEmpty(location) && location.length() > 15){
    			setCols.addSQL(" TotalNum=TotalNum+2 ");
        		setCols.addSQL(" ReminderNum=ReminderNum+2 ");
    		}else{
    			setCols.addSQL(" TotalNum=TotalNum+1 ");
        		setCols.addSQL(" ReminderNum=ReminderNum+1 ");
    		}
    		
    		
    		whereCols.add("TerminalNo", TerminalNo);
    		whereCols.add("OccurYear", smsStat.OccurYear);
    		whereCols.add("OccurMonth", smsStat.OccurMonth);
    		
    		smsStatDAO.update(setCols, whereCols);
    	}else
    	{
    		smsStat.TotalNum = 1;
    		smsStat.PwdNum = 0;
    		smsStat.ExpireNum = 0;
    		smsStat.ReminderNum = 1;
    		smsStat.DynamicNum = 0;
    		smsStat.PickupNum = 0;
    		smsStat.OtherNum = 0;
    		
    		smsStatDAO.insert(smsStat);
    	}
    }
}
