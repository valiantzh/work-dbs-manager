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
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * <p>
 * Description: 管理员取件
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: dcdzsoft
 * </p>
 * 
 * @author 王中立
 * @version 1.0
 */

public class PTManagerPickupPack extends ActionBean {

	public OutParamPTManagerPickupPack doBusiness(InParamPTManagerPickupPack in)
			throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		OutParamPTManagerPickupPack out = new OutParamPTManagerPickupPack();

		// 1. 验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PackageID))
			throw new EduException(ErrorCode.ERR_PARMERR);

		// 2. 调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		// OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		// //////////////////////////////////////////////////////////////////////
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		int timeDiff = NumberUtils.parseInt(ControlParam.getInstance().getInterTimeDifferent());
        if(timeDiff>0){
            //System.out.println("===时差"+timeDiff);
            sysDateTime = DateUtils.addTimeStampByHour(sysDateTime, timeDiff);      //国际时差，根据不同的国家设定不同的时间
        }
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		
		// 验证包裹单号
		PTDeliverHistoryDAO historyPackDAO = daoFactory.getPTDeliverHistoryDAO();
		
		///////////////////////////////////////////////////////
    	JDBCFieldArray whereCols0 = new JDBCFieldArray();
    	whereCols0.add("PackageID", in.PackageID);
    	whereCols0.add("TerminalNo", in.TerminalNo);
    	whereCols0.add("TradeWaterNo", in.TradeWaterNo);
        
    	if(historyPackDAO.isExist(whereCols0) == 1) //同一笔交易
    	{
    		out.ServerTime = sysDateTime;
    		
    		return out;
    	}
    	
    	///////////////////////////////////////////////////////////////////////
		PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
		PTInBoxPackage inboxPack = new PTInBoxPackage();

		inboxPack.PackageID = in.PackageID;
		inboxPack.TerminalNo = in.TerminalNo;

		try {
			inboxPack = inboxPackDAO.find(inboxPack);
			
		} catch (Exception e) {
    		if(StringUtils.isNotEmpty(in.Remark)) //同一个包裹
    		{
    			JDBCFieldArray whereCols01 = new JDBCFieldArray();
    			whereCols01.add("Remark", in.Remark);
            	
    			if(historyPackDAO.isExist(whereCols01) > 0)
            	{
            		out.ServerTime = sysDateTime;
            		
            		return out;
            	}else{
            		throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);
            	}
    		}else{
    			throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);
    		}
		}

        //////////不是此前发生的这笔交易//////////////////////////////////////////////////////
        if(StringUtils.isNotEmpty(in.Remark) && !in.Remark.equalsIgnoreCase(inboxPack.TradeWaterNo)){
            throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);
        }
        
        
        //设备是否存在
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        TBTerminal terminal = new TBTerminal();
        terminal.TerminalNo = in.TerminalNo;
        
        terminal = terminalDAO.find(terminal);
        
        //取投递员信息
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = inboxPack.PostmanID;
        try
        {
            postman = postmanDAO.find(postman);
        } catch (Exception e) {
            //throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
        }

		// 从在箱信息里面删除
		inboxPackDAO.delete(inboxPack);

		/////////////////////////////////////////////////////////////////////
		
		// 先删除，保证数据不重复
		JDBCFieldArray whereCols1 = new JDBCFieldArray();
		whereCols1.add("PackageID", inboxPack.PackageID);
		whereCols1.add("TerminalNo", inboxPack.TerminalNo);
		whereCols1.add("StoredTime", inboxPack.StoredTime);

		historyPackDAO.delete(whereCols1);

		// ////////////////////////////////////////////////////////////////////////
		// 插入历史记录
		PTDeliverHistory historyPack = new PTDeliverHistory();

		historyPack.TerminalNo = in.TerminalNo;
		historyPack.PackageID = inboxPack.PackageID;
		historyPack.StoredTime = inboxPack.StoredTime;
		historyPack.StoredDate = inboxPack.StoredDate;
		historyPack.BoxNo = inboxPack.BoxNo;
		historyPack.PostmanID = inboxPack.PostmanID;
		historyPack.CompanyID = inboxPack.CompanyID;
		historyPack.ExpiredTime = inboxPack.ExpiredTime;
		historyPack.CustomerID = inboxPack.CustomerID;
		historyPack.CustomerMobile = inboxPack.CustomerMobile;
		historyPack.CustomerName = inboxPack.CustomerName;
		historyPack.CustomerAddress = inboxPack.CustomerAddress;
		historyPack.OpenBoxKey = inboxPack.OpenBoxKey;
		historyPack.HireAmt = inboxPack.HireAmt;
		historyPack.HireWhoPay = inboxPack.HireWhoPay;
		historyPack.PayedAmt = inboxPack.PayedAmt;
		historyPack.TakedTime = sysDateTime;
		historyPack.TakedWay  = SysDict.TAKEOUT_WAY_UNKNOWN;
		historyPack.LeftFlag = inboxPack.LeftFlag;
		historyPack.PosPayFlag = inboxPack.PosPayFlag;
		historyPack.PackageStatus = SysDict.PACKAGE_STATUS_OUT4MANAGER;
		historyPack.LastModifyTime = sysDateTime;
		historyPack.Remark = inboxPack.TradeWaterNo;
		
		historyPack.StaOrderID = inboxPack.StaOrderID;
		historyPack.OfLogisticsID = inboxPack.OfLogisticsID;
		historyPack.OfLogisticsName = inboxPack.OfLogisticsName;
		historyPack.BlankBoxKey = inboxPack.BlankBoxKey;
		historyPack.TradeWaterNo = in.TradeWaterNo;
		
		if(commonDAO.isSendItemEventToPartner(inboxPack.CompanyID, postman.InputMobileFlag))
        	historyPack.UploadFlag = SysDict.UPLOAD_FLAG_NO;
        else
        	historyPack.UploadFlag = SysDict.UPLOAD_FLAG_YES;

		historyPackDAO.insert(historyPack);

		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.TerminalNo = in.TerminalNo;
		log.StationAddr = "";
		log.Remark = in.PackageID + "," + in.TerminalNo;
		
		commonDAO.addOperatorLog(log);

		out.ServerTime = sysDateTime;
		
		//修改短信里面的包裹状态
		MBPwdShortMsgDAO shortMsgDAO = daoFactory.getMBPwdShortMsgDAO();
		JDBCFieldArray setCols2 = new JDBCFieldArray();
		JDBCFieldArray whereCols2 = new JDBCFieldArray();
		setCols2.add("PackageStatus", historyPack.PackageStatus);
		whereCols2.add("PackageID", inboxPack.PackageID);
		whereCols2.add("TerminalNo", inboxPack.TerminalNo);
		whereCols2.add("StoredTime", inboxPack.StoredTime);
		
		shortMsgDAO.update(setCols2, whereCols2);


		// /////////////////////////////////////////////////////////
		SMSInfo smsInfo = commonDAO.generateSendInfo(historyPack, terminal, postman, sysDateTime);
		// 订单状态是否 反馈给合作方系统
		if (commonDAO.isSendItemEventToPartner(inboxPack.CompanyID,postman.InputMobileFlag)) {
            

            /*SMSInfo smsInfo = new SMSInfo();
            smsInfo.PackageID = in.PackageID;
            smsInfo.TerminalNo = in.TerminalNo;
            
            smsInfo.StoredTime = historyPack.StoredTime;
            smsInfo.sysDateTime = sysDateTime;
            smsInfo.TakedTime  = historyPack.TakedTime;
            smsInfo.CustomerMobile = historyPack.CustomerMobile;
            smsInfo.BoxNo = inboxPack.BoxNo;
            smsInfo.PackageStatus = historyPack.PackageStatus;
            smsInfo.PostmanID = historyPack.PostmanID;
            smsInfo.PostmanName = postman.PostmanName;
            smsInfo.PostmanMobile = postman.Mobile;
            smsInfo.CompanyID = historyPack.CompanyID;
            smsInfo.OpenBoxKey = "";
            smsInfo.TerminalName = terminal.TerminalName;
            smsInfo.OfBureau = terminal.OfBureau;
            smsInfo.Location = terminal.Location;
            
            smsInfo.TradeWaterNo = in.TradeWaterNo;
            
            smsInfo.StaOrderID = inboxPack.StaOrderID;
            smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
            smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
            smsInfo.OpenBoxKey = inboxPack.BlankBoxKey;
            smsInfo.MBDeviceNo = terminal.MBDeviceNo;

            if(StringUtils.isEmpty(smsInfo.OfLogisticsID)){
                PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                PMCompany company = new PMCompany();
                company.CompanyID = postman.CompanyID;
                
                company = companyDAO.find(company);
                smsInfo.OfLogisticsID = company.LogisticsID;
            }*/
		    SMSInfo sendInfo = new SMSInfo(smsInfo);
            GuotongManager.getInstance().sentDeliveryInfo(sendInfo);
        }

		return out;
	}
}
