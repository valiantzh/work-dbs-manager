package com.dcdzsoft.business.pt;



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
 * <p>Description: 取回逾期包裹 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTWithdrawExpiredPack extends ActionBean {

    public OutParamPTWithdrawExpiredPack doBusiness(InParamPTWithdrawExpiredPack in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTWithdrawExpiredPack out = new OutParamPTWithdrawExpiredPack();

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo)
            || StringUtils.isEmpty(in.PostmanID)
            || StringUtils.isEmpty(in.PackageID))
            throw new EduException(ErrorCode.ERR_PARMERR);


        
        //4.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        int timeDiff = NumberUtils.parseInt(ControlParam.getInstance().getInterTimeDifferent());
        if(timeDiff>0){
            //System.out.println("===时差"+timeDiff);
            sysDateTime = DateUtils.addTimeStampByHour(sysDateTime, timeDiff);      //国际时差，根据不同的国家设定不同的时间
        }
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        
        
        //验证包裹单号
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

        try{
        	inboxPack = inboxPackDAO.find(inboxPack);
        	
        }catch(Exception e)
        {
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
        
        //验证柜体权限(登陆时候验证)
        //commonDAO.checkManTerminalRight(postman, in.TerminalNo);
        
        //从在箱信息里面删除
        inboxPackDAO.delete(inboxPack);

        //先删除，保证数据不重复(可能会删除重复的)考虑用交易流水号做主键
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("PackageID", inboxPack.PackageID);
        whereCols1.add("TerminalNo", inboxPack.TerminalNo);
        whereCols1.add("StoredTime", inboxPack.StoredTime);

        historyPackDAO.delete(whereCols1);

        //////////////////////////////////////////////////////////////////////////
        //插入历史记录
        PTDeliverHistory historyPack = new PTDeliverHistory();

        historyPack.TerminalNo = in.TerminalNo;
        historyPack.PackageID = in.PackageID;
        historyPack.BoxNo = inboxPack.BoxNo;
        historyPack.StoredTime = inboxPack.StoredTime;
        historyPack.StoredDate = inboxPack.StoredDate;
        historyPack.PostmanID = inboxPack.PostmanID;
        historyPack.CompanyID = inboxPack.CompanyID;
        historyPack.DynamicCode = in.DynamicCode;
        historyPack.TradeWaterNo = in.TradeWaterNo;
        historyPack.ExpiredTime = inboxPack.ExpiredTime;
        historyPack.CustomerID = inboxPack.CustomerID;
        historyPack.CustomerMobile = inboxPack.CustomerMobile;
        historyPack.CustomerName = inboxPack.CustomerName;
        historyPack.CustomerAddress = inboxPack.CustomerAddress;
        historyPack.TakedPersonID = in.PostmanID;
        historyPack.TakedTime = sysDateTime;
        historyPack.TakedWay  = SysDict.TAKEOUT_WAY_UNKNOWN;
        historyPack.OpenBoxKey = inboxPack.OpenBoxKey;
        historyPack.HireAmt = inboxPack.HireAmt;
        historyPack.HireWhoPay = inboxPack.HireWhoPay;
        historyPack.PayedAmt = inboxPack.PayedAmt;
        historyPack.LeftFlag = inboxPack.LeftFlag;
        historyPack.PosPayFlag = inboxPack.PosPayFlag;
        historyPack.LastModifyTime = sysDateTime;
        
        historyPack.Remark = inboxPack.TradeWaterNo;
        
        historyPack.StaOrderID = inboxPack.StaOrderID;
		historyPack.OfLogisticsID = inboxPack.OfLogisticsID;
		historyPack.OfLogisticsName = inboxPack.OfLogisticsName;
		historyPack.BlankBoxKey = inboxPack.BlankBoxKey;
        
        if(StringUtils.isEmpty(in.PackageStatus))
        {
        	historyPack.PackageStatus = SysDict.PACKAGE_STATUS_OUTEXPIRED;
        }else
        {
        	historyPack.PackageStatus = in.PackageStatus;
        }
        
        if(commonDAO.isSendItemEventToPartner(inboxPack.CompanyID, postman.InputMobileFlag))
        	historyPack.UploadFlag = SysDict.UPLOAD_FLAG_NO;
        else
        	historyPack.UploadFlag = SysDict.UPLOAD_FLAG_YES;

        historyPackDAO.insert(historyPack);

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
        
        ///////////////////////////////////////////////////////////
        SMSInfo smsInfo = commonDAO.generateSendInfo(historyPack, terminal, postman, sysDateTime);

    	//发送短信
        commonDAO.sendExpiredSMS(smsInfo);
        /*if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()))
        {
    		smsInfo.MsgType = SMSInfo.MSG_TYPE_EXPIRED;    
    		
    		ControlParam ctrlParam = ControlParam.getInstance();	
    		switch(ctrlParam.getSendExpiredSMS()){
	    	case "1":{
	    		SMSManager.getInstance().sentExpiredSMS(smsInfo);break;}
	    	case "2":{
	    		//发送逾期短信给投递员。
	    		SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
	    		SMSManager.getInstance().sentExpiredSMSToPostman(smsInfoToPostman);break;}
	    	case "3":{
	    		SMSManager.getInstance().sentExpiredSMS(smsInfo);
	    		SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
	    		SMSManager.getInstance().sentExpiredSMSToPostman(smsInfoToPostman);break;}
    		}
    		
        }*/
        
        //发送邮件
        commonDAO.sendExpiredEmail(smsInfo);
        
        //反馈订单状态给合作伙伴 
        if(commonDAO.isSendItemEventToPartner(inboxPack.CompanyID, postman.InputMobileFlag))
	    {        	
        	/*PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
    		PMCompany company = new PMCompany();
    		company.CompanyID = postman.CompanyID;
    		
    		company = companyDAO.find(company);
    		
        	PMLogisticsDAO logisticDAO = daoFactory.getPMLogisticsDAO();
     		PMLogistics logistic = new PMLogistics();
     		logistic.LogisticsID = company.LogisticsID;
     		logistic = logisticDAO.find(logistic);
     		
        	SMSInfo smsInfo = new SMSInfo();
        	smsInfo.PackageID = in.PackageID;
        	smsInfo.TerminalNo = in.TerminalNo;
        	smsInfo.StoredTime = historyPack.StoredTime;
        	smsInfo.sysDateTime = sysDateTime;
        	smsInfo.TakedTime  = historyPack.TakedTime;
        	smsInfo.CustomerMobile = historyPack.CustomerMobile;
        	smsInfo.BoxNo = inboxPack.BoxNo;
        	smsInfo.PackageStatus = historyPack.PackageStatus;
        	smsInfo.OpenBoxKey = "";
        	smsInfo.TerminalName = terminal.TerminalName;
        	smsInfo.OfBureau = terminal.OfBureau;
        	smsInfo.Location = terminal.Location;
        	smsInfo.DepartmentID = terminal.DepartmentID;
        	smsInfo.PostmanID = historyPack.PostmanID;
        	smsInfo.PostmanName = postman.PostmanName;
        	smsInfo.PostmanMobile = postman.Mobile;
    		smsInfo.CompanyID = historyPack.CompanyID;
    		smsInfo.LogisticsName = logistic.LogisticsName;
        	smsInfo.MsgType = SMSInfo.MSG_TYPE_EXPIRED;
        	
        	smsInfo.TradeWaterNo = in.TradeWaterNo;
        	
        	smsInfo.StaOrderID = inboxPack.StaOrderID;
    		smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
    		smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
    		smsInfo.OpenBoxKey = inboxPack.BlankBoxKey;
    		smsInfo.MBDeviceNo = terminal.MBDeviceNo;
    		smsInfo.TakedTime  = sysDateTime;
            if(StringUtils.isEmpty(smsInfo.OfLogisticsID)){
                smsInfo.OfLogisticsID = company.LogisticsID;
            }*/
            SMSInfo sendInfo = new SMSInfo(smsInfo);
            GuotongManager.getInstance().sentDeliveryInfo(sendInfo);
	    }
        
        return out;
    }
}
