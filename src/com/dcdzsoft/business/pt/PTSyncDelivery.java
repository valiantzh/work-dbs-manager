package com.dcdzsoft.business.pt;

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
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 同步投递反馈信息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTSyncDelivery extends ActionBean
{

    public int doBusiness(InParamPTSyncDelivery in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.PackageID)
			||in.StoredTime == null 
			||StringUtils.isEmpty(in.PackageStatus))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		
		//3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		//////////////////////////////////////////
		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
    	TBTerminal terminal = new TBTerminal();
    	terminal.TerminalNo = in.TerminalNo;
    	
    	terminal = terminalDAO.find(terminal);
    	
    	//////////////////////////////////////////////////////
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        
		////////////////////////////////////////
		SMSInfo smsInfo = new SMSInfo();
		
		/////////////////////////////////////////////
		if(SysDict.PACKAGE_STATUS_NORMAL.equals(in.PackageStatus) ||
			SysDict.PACKAGE_STATUS_LOCKED.equals(in.PackageStatus) ||
			SysDict.PACKAGE_STATUS_TIMEOUT.equals(in.PackageStatus)||
			SysDict.PACKAGE_STATUS_RETURNGOODS.equals(in.PackageStatus)//退货在箱-泰和
			)
		{
			PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
			PTInBoxPackage inboxPack = new PTInBoxPackage();

			inboxPack.TerminalNo = in.TerminalNo;
			inboxPack.PackageID = in.PackageID;

			inboxPack = inboxPackDAO.find(inboxPack);
			
			try
            {
			    postman.PostmanID = inboxPack.PostmanID;
                postman = postmanDAO.find(postman);
            }catch (Exception e) {
                //throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
            }
			smsInfo = commonDAO.generateSendInfo(inboxPack, terminal, postman, sysDateTime);
			
			/*smsInfo.PackageID = in.PackageID;
	    	smsInfo.TerminalNo = in.TerminalNo;
	    	smsInfo.StoredTime = inboxPack.StoredTime;
	    	smsInfo.sysDateTime = sysDateTime;
	    	smsInfo.CustomerMobile = inboxPack.CustomerMobile;
	    	smsInfo.OfBureau = terminal.OfBureau;
	    	smsInfo.BoxNo = inboxPack.BoxNo;
	    	//smsInfo.OpenBoxKey = "";
	    	smsInfo.PackageStatus = inboxPack.PackageStatus;
	    	smsInfo.PostmanID = inboxPack.PostmanID;
	    	
	    	smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
	    	smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
	    	smsInfo.StaOrderID = inboxPack.StaOrderID;
	    	smsInfo.TradeWaterNo = inboxPack.TradeWaterNo;
	    	smsInfo.OpenBoxKey = inboxPack.BlankBoxKey;
	    	smsInfo.MBDeviceNo = terminal.MBDeviceNo;
	    	smsInfo.TerminalName = terminal.TerminalName;
	        smsInfo.Location = terminal.Location;
	        smsInfo.DepartmentID = terminal.DepartmentID;
	        
	        smsInfo.ExpiredTime  = inboxPack.ExpiredTime;*/
	        
		}
		else
		{
			PTDeliverHistoryDAO historyPackDAO = daoFactory.getPTDeliverHistoryDAO();
			PTDeliverHistory historyPack = new PTDeliverHistory();
			
			historyPack.TerminalNo = in.TerminalNo;
			historyPack.PackageID = in.PackageID;
			historyPack.StoredTime = in.StoredTime;
			
			historyPack = historyPackDAO.find(historyPack);

			try
	        {
			    postman.PostmanID = historyPack.PostmanID;
			    postman = postmanDAO.find(postman);
	        }catch (Exception e) {
	            //throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
	        }
			
			smsInfo = commonDAO.generateSendInfo(historyPack, terminal, postman, sysDateTime);
			
			/*smsInfo.PackageID = in.PackageID;
	    	smsInfo.TerminalNo = in.TerminalNo;
	    	smsInfo.StoredTime = in.StoredTime;
	    	smsInfo.TakedTime = historyPack.TakedTime;
	    	smsInfo.sysDateTime = sysDateTime;
	    	smsInfo.CustomerMobile = historyPack.CustomerMobile;
	    	smsInfo.OfBureau = terminal.OfBureau;
	    	smsInfo.BoxNo = historyPack.BoxNo;
	    	//smsInfo.OpenBoxKey = "";
	    	smsInfo.PackageStatus = historyPack.PackageStatus;
	    	smsInfo.PostmanID = historyPack.PostmanID;
	    
	    	smsInfo.OfLogisticsID = historyPack.OfLogisticsID;
	    	smsInfo.OfLogisticsName = historyPack.OfLogisticsName;
	    	smsInfo.StaOrderID = historyPack.StaOrderID;
	    	smsInfo.TradeWaterNo = historyPack.TradeWaterNo;
	    	smsInfo.OpenBoxKey = historyPack.BlankBoxKey;
	    	smsInfo.MBDeviceNo = terminal.MBDeviceNo;
	    	smsInfo.TerminalName = terminal.TerminalName;
            smsInfo.Location = terminal.Location;
            smsInfo.DepartmentID = terminal.DepartmentID;
            
            smsInfo.ExpiredTime = historyPack.ExpiredTime;*/
            
		}
		
		/*try
        {
            postman = postmanDAO.find(postman);
            smsInfo.PostmanName = postman.PostmanName;
            smsInfo.PostmanMobile = postman.Mobile;
            smsInfo.CompanyID = postman.CompanyID;
            
            //投递公司信息
            PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
            PMCompany company = new PMCompany();
            company.CompanyID = postman.CompanyID;
            company = companyDAO.find(company);
            smsInfo.CompanyName = company.CompanyName;
            
            if(StringUtils.isEmpty(smsInfo.OfLogisticsID)){          
                PMLogisticsDAO logisticDAO = daoFactory.getPMLogisticsDAO();
                PMLogistics logistic = new PMLogistics();
                logistic.LogisticsID = company.LogisticsID;
                logistic = logisticDAO.find(logistic);
                
                
                smsInfo.OfLogisticsID   = logistic.LogisticsID;
                smsInfo.OfLogisticsName = logistic.LogisticsName;
            }
            if(smsInfo.ExpiredTime== null || smsInfo.ExpiredTime.before(smsInfo.StoredTime)){
                smsInfo.ExpiredTime = queryExpiredDayTime(postman.CompanyID, smsInfo.StoredTime);
            }
        } catch (Exception e) {
            //throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
        }*/
		
		
		///////////////////////////////////////////////////
		//反馈订单状态给合作伙伴 
        if(commonDAO.isSendItemEventToPartner(postman.CompanyID, postman.InputMobileFlag))
        {         
            smsInfo.Remark = "ManSyncDelivery";//人工同步
            
            SMSInfo sendInfo = new SMSInfo(smsInfo);
            GuotongManager.getInstance().sentDeliveryInfo(sendInfo);
        }

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.PackageID;

		commonDAO.addOperatorLog(log);


        return result;
    }
    private java.sql.Timestamp queryExpiredDayTime(String companyID,java.sql.Timestamp sysDateTime) throws EduException
    {
        
        if(sysDateTime == null){
            sysDateTime = utilDAO.getCurrentDateTime();
        }
        int days = commonDAO.expiredDays(companyID);
        
        return DateUtils.addTimeStamp(sysDateTime, days);
    }
}
