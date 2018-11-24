package com.dcdzsoft.business.mb;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.guotong.GuotongManager;
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

public class MBFeedbackFailReSend extends ActionBean
{

    public int doBusiness(InParamMBFeedbackFailReSend in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.WaterIDList))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		if(StringUtils.isEmpty(in.OperID)){
		    in.OperID = "181818";
		}else{
		    OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		}

		//3.调用UtilDAO.getSysDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);


		String[] waterIDList = in.WaterIDList.split(",");
        for(int i = 0; i < waterIDList.length; i++)
        {
            result = sentInfo(waterIDList[i],sysDateTime);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
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
    private int sentInfo(String TradeWaterNo,java.sql.Timestamp sysDateTime) throws EduException
    {
        int result = 0;
        PTFeedbackFailDAO feedbackFailDAO = daoFactory.getPTFeedbackFailDAO();
        PTFeedbackFail feedbackFail = new PTFeedbackFail();
        feedbackFail.TradeWaterNo = TradeWaterNo;
        feedbackFail = feedbackFailDAO.find(feedbackFail);
        
        SMSInfo smsInfo = new SMSInfo();
        
        if(SysDict.PACKAGE_STATUS_NORMAL.equals(feedbackFail.PackageStatus)
                ||(SysDict.PACKAGE_STATUS_LOCKED.equals(feedbackFail.PackageStatus))
                ||(SysDict.PACKAGE_STATUS_TIMEOUT.equals(feedbackFail.PackageStatus)))
        {
            PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
            PTInBoxPackage inboxPack = new PTInBoxPackage();

            inboxPack.TerminalNo = feedbackFail.TerminalNo;
            inboxPack.PackageID = feedbackFail.PackageID;
            if(!inboxPackDAO.isExist(inboxPack)){
                //不在表中，删除
                feedbackFailDAO.delete(feedbackFail);
                return result;
            }
            inboxPack = inboxPackDAO.find(inboxPack);
            if(!inboxPack.TradeWaterNo.equals(feedbackFail.TradeWaterNo)){
                //已过期，删除
                feedbackFailDAO.delete(feedbackFail);
                return result;
            }
            
            TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
            TBTerminal terminal = new TBTerminal();
            terminal.TerminalNo = inboxPack.TerminalNo;
            
            terminal = terminalDAO.find(terminal);
            
            PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
            PMPostman postman = new PMPostman();
            postman.PostmanID = inboxPack.PostmanID;
            postman = postmanDAO.find(postman);
            
            smsInfo = commonDAO.generateSendInfo(inboxPack, terminal, postman, sysDateTime);
            
            /*smsInfo.PackageID = inboxPack.PackageID;
            smsInfo.TerminalNo = inboxPack.TerminalNo;
            smsInfo.StoredTime = inboxPack.StoredTime;
            smsInfo.sysDateTime = sysDateTime;
            smsInfo.CustomerMobile = inboxPack.CustomerMobile;
            smsInfo.OfBureau = terminal.OfBureau;
            smsInfo.BoxNo = inboxPack.BoxNo;
            //smsInfo.OpenBoxKey = "";
            smsInfo.PackageStatus = inboxPack.PackageStatus;
            smsInfo.PostmanID = inboxPack.PostmanID;
            smsInfo.PostmanName = postman.PostmanName;
			smsInfo.PostmanMobile = postman.Mobile;
			smsInfo.DepartmentID  = postman.DepartmentID;
			smsInfo.CompanyID = postman.CompanyID;
            
            smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
            smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
            smsInfo.StaOrderID = inboxPack.StaOrderID;
            smsInfo.TradeWaterNo = inboxPack.TradeWaterNo;
            smsInfo.OpenBoxKey = inboxPack.BlankBoxKey;
            smsInfo.MBDeviceNo = terminal.MBDeviceNo;
            smsInfo.TerminalName = terminal.TerminalName;
            smsInfo.Location = terminal.Location;
            smsInfo.DepartmentID = terminal.DepartmentID;*/
            
        }else{
            PTDeliverHistoryDAO historyPackDAO = daoFactory.getPTDeliverHistoryDAO();
            PTDeliverHistory historyPack = new PTDeliverHistory();
            
            historyPack.TerminalNo = feedbackFail.TerminalNo;
            historyPack.PackageID = feedbackFail.PackageID;
            historyPack.StoredTime = feedbackFail.StoredTime;
            if(!historyPackDAO.isExist(historyPack)){
                //不在表中，删除
                feedbackFailDAO.delete(feedbackFail);
                return result;
            }
            historyPack = historyPackDAO.find(historyPack);
            if(!historyPack.TradeWaterNo.equals(feedbackFail.TradeWaterNo)){
                //已过期，删除
                feedbackFailDAO.delete(feedbackFail);
                return result;
            }
            
            TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
            TBTerminal terminal = new TBTerminal();
            terminal.TerminalNo = feedbackFail.TerminalNo;
            
            terminal = terminalDAO.find(terminal);
            
            PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
            PMPostman postman = new PMPostman();
            postman.PostmanID = historyPack.PostmanID;
            postman = postmanDAO.find(postman);
            
            smsInfo = commonDAO.generateSendInfo(historyPack, terminal, postman, sysDateTime);
            
            /*smsInfo.PackageID = feedbackFail.PackageID;
            smsInfo.TerminalNo = feedbackFail.TerminalNo;
            smsInfo.StoredTime = feedbackFail.StoredTime;
            smsInfo.TakedTime = historyPack.TakedTime;
            smsInfo.sysDateTime = sysDateTime;
            smsInfo.CustomerMobile = historyPack.CustomerMobile;
            smsInfo.OfBureau = terminal.OfBureau;
            smsInfo.BoxNo = historyPack.BoxNo;
            //smsInfo.OpenBoxKey = "";
            smsInfo.PackageStatus = historyPack.PackageStatus;
            smsInfo.PostmanID = historyPack.PostmanID;
            smsInfo.PostmanName = postman.PostmanName;
 			smsInfo.PostmanMobile = postman.Mobile;
 			smsInfo.DepartmentID  = postman.DepartmentID;
 			smsInfo.CompanyID = postman.CompanyID;
        
            smsInfo.OfLogisticsID = historyPack.OfLogisticsID;
            smsInfo.OfLogisticsName = historyPack.OfLogisticsName;
            smsInfo.StaOrderID = historyPack.StaOrderID;
            smsInfo.TradeWaterNo = historyPack.TradeWaterNo;
            smsInfo.OpenBoxKey = historyPack.BlankBoxKey;
            smsInfo.MBDeviceNo = terminal.MBDeviceNo;
            smsInfo.TerminalName = terminal.TerminalName;
            smsInfo.Location = terminal.Location;
            smsInfo.DepartmentID = terminal.DepartmentID;*/
        }
        
        if(commonDAO.isSendItemEventToPartner(feedbackFail.CompanyID, "1"))
        {
            SMSInfo sendInfo = new SMSInfo(smsInfo);
            GuotongManager.getInstance().sentDeliveryInfo(sendInfo);
        }
        return 1;
    }
}
