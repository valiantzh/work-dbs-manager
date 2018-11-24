package com.dcdzsoft.business.pt;


import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.email.EmailSenderManager;
import com.dcdzsoft.guotong.GuotongManager;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 用户取件 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTPickupPackage extends ActionBean {

    public OutParamPTPickupPackage doBusiness(InParamPTPickupPackage in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamPTPickupPackage out = new OutParamPTPickupPackage();

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.PackageID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        ////////////////////////////////////////////////////////////////////////
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		int timeDiff = NumberUtils.parseInt(ControlParam.getInstance().getInterTimeDifferent());
		if(timeDiff>0){
		    //System.out.println("===时差"+timeDiff);
	        sysDateTime = DateUtils.addTimeStampByHour(sysDateTime, timeDiff);      //国际时差，根据不同的国家设定不同的时间
		}
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        //验证包裹单号
        PTDeliverHistoryDAO historyPackDAO = daoFactory.getPTDeliverHistoryDAO();

        ///////////////////////////////////////////////////////////////////////////
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
        	if(StringUtils.isNotEmpty(in.Remark)) //同一个在箱记录
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
        
        ///////////////////////////////////////////////////
        //从在箱信息里面删除
        inboxPackDAO.delete(inboxPack);

        
        //先删除，保证数据不重复
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("PackageID", inboxPack.PackageID);
        whereCols1.add("TerminalNo", inboxPack.TerminalNo);
        whereCols1.add("StoredTime", inboxPack.StoredTime);

        historyPackDAO.delete(whereCols1);

        //////////////////////////////////////////////////////////////////////////
        //插入历史记录
        PTDeliverHistory historyPack = new PTDeliverHistory();

        historyPack.TerminalNo = in.TerminalNo;
        historyPack.PackageID = inboxPack.PackageID;
        historyPack.StoredTime = inboxPack.StoredTime;
        historyPack.StoredDate = inboxPack.StoredDate;
        historyPack.BoxNo = inboxPack.BoxNo;
        historyPack.PostmanID = inboxPack.PostmanID;
        historyPack.CompanyID = inboxPack.CompanyID;
        historyPack.TradeWaterNo = in.TradeWaterNo;
        historyPack.BoxNo = inboxPack.BoxNo;
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
        historyPack.TakedWay  = in.TakedWay;
        historyPack.LeftFlag = inboxPack.LeftFlag;
        historyPack.PosPayFlag = inboxPack.PosPayFlag;
        historyPack.PackageStatus = SysDict.PACKAGE_STATUS_OUTNORMAL;
        historyPack.PayedAmt = in.OverdueCost;
        historyPack.LastModifyTime = sysDateTime;
        historyPack.Remark = inboxPack.TradeWaterNo;
        
        historyPack.StaOrderID = inboxPack.StaOrderID;
   		historyPack.OfLogisticsID = inboxPack.OfLogisticsID;
   		historyPack.OfLogisticsName = inboxPack.OfLogisticsName;
   		historyPack.BlankBoxKey = inboxPack.BlankBoxKey;
        
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
		
		///////////////////////////////////////
		//sendPickupMsg(historyPack, terminal, postman,sysDateTime);
		//生成消息
		SMSInfo smsInfo = commonDAO.generateSendInfo(historyPack, terminal, postman, sysDateTime);
		// 发送取件通知-短信
		commonDAO.sendPickupSMS(smsInfo);
		// 发送取件通知-邮件
		commonDAO.sendPickupEmail(smsInfo);
        
        
        ///////////////////////////////////////////////////////////
        //反馈订单状态给合作伙伴 
		if(commonDAO.isSendItemEventToPartner(inboxPack.CompanyID, postman.InputMobileFlag)){
		    //sentPickupInfo(historyPack, terminal, postman, sysDateTime);
		    SMSInfo sendInfo = new SMSInfo(smsInfo);
	        GuotongManager.getInstance().sentDeliveryInfo(sendInfo);
        }

        return out;
    }
    /**
     * 
     * @param historyPack
     * @param terminal
     * @param postman
     * @param sysDateTime
     * @deprecated
     */
    private void sendPickupMsg(PTDeliverHistory historyPack, TBTerminal terminal, PMPostman postman, java.sql.Timestamp sysDateTime){
		SMSInfo smsInfo = new SMSInfo();
		smsInfo.PackageID = historyPack.PackageID;
		smsInfo.TerminalNo = historyPack.TerminalNo;
		smsInfo.TerminalName = terminal.TerminalName;
        smsInfo.OfBureau = terminal.OfBureau;
        smsInfo.Location = terminal.Location;
		smsInfo.StoredTime = historyPack.StoredTime;
		smsInfo.TakedTime = sysDateTime;
		smsInfo.sysDateTime = sysDateTime;
		smsInfo.CustomerMobile = historyPack.CustomerMobile;
		smsInfo.BoxNo = historyPack.BoxNo;
		smsInfo.PostmanID = historyPack.PostmanID;
		smsInfo.PostmanName = postman.PostmanName;
		smsInfo.PostmanMobile = postman.Mobile;
		smsInfo.DepartmentID  = postman.DepartmentID;
		smsInfo.CompanyID = postman.CompanyID;
		smsInfo.PackageStatus = historyPack.PackageStatus;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_TAKEDOUT;
		
		//不同公司投递员由不同公司的管理邮箱发送。
        PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
        PMCompany company = new PMCompany();
        company.CompanyID = postman.CompanyID;
        try {
			company = companyDAO.find(company);
			smsInfo.CompanyMobile = company.ContactTel;
		} catch (EduException e1) {
			e1.printStackTrace();
		}
        smsInfo.CompanyMobile = company.ContactTel;//公司联系人电话
    	smsInfo.FromEamilAddress = company.Address;//公司邮箱地址-->发件人地址
    	smsInfo.UserName = company.Address;//用户邮箱地址（一般就是发件人地址）
    	smsInfo.EmailPwd = company.Remark;//255字节
		
		ControlParam ctrlParam = ControlParam.getInstance();
		try{
			//发送取件短信 0:不使用,1:给客户,2:给投递员
		    if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg())) {
		    	switch(ctrlParam.getSendPickupSMS()){
		    	case "1":SMSManager.getInstance().sentPickupSMS(smsInfo);break;
		    	case "2":{
		    		SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
		    		SMSManager.getInstance().sentPickupSMSToPostman(smsInfoToPostman);break;}
		    	case "3":{
		    		SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
		    		SMSManager.getInstance().sentPickupSMSToCompany(smsInfoToCompany);break;}
		    	case "4":{
		    		SMSManager.getInstance().sentPickupSMS(smsInfo);
		    		SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
		    		SMSManager.getInstance().sentPickupSMSToPostman(smsInfoToPostman);
		    		SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
		    		SMSManager.getInstance().sentPickupSMSToCompany(smsInfoToCompany);break;}
		    	}				
		    }
	    	
		}catch(EduException e){
			e.printStackTrace();
		}
		
		try{
		  //发送投递邮件0:不使用,1:给客户,2:给投递员,3:给投递公司,4:都发送
	        switch(ctrlParam.getSendPickupEmail()){
	        case "1":{
	            smsInfo.Email = historyPack.CustomerMobile; //柜端取件人手机号作为邮箱
	            EmailSenderManager.getInstance().sendPickupEmail(smsInfo,"ToCM");break;}
	        case "2":{
	            smsInfo.Email = postman.Email;
	            EmailSenderManager.getInstance().sendPickupEmail(smsInfo,"ToPM");break;}
	        case "3":{
	            smsInfo.Email = smsInfo.FromEamilAddress;//公司邮箱发给自己邮箱作为投递凭证
	            EmailSenderManager.getInstance().sendPickupEmail(smsInfo,"ToCompany");break;}
	        case "4":{
	            smsInfo.Email = historyPack.CustomerMobile; //柜端取件人手机号作为邮箱
	            EmailSenderManager.getInstance().sendPickupEmail(smsInfo,"ToCM");
	            SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
	            smsInfoToPostman.Email = smsInfoToPostman.Email;
	            EmailSenderManager.getInstance().sendPickupEmail(smsInfoToPostman,"ToPM");
	            SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
	            smsInfoToCompany.Email = smsInfoToCompany.FromEamilAddress;
	            EmailSenderManager.getInstance().sendPickupEmail(smsInfoToCompany,"ToCompany");break;}
	        }
		}catch(EduException e){
            e.printStackTrace();
        }
    }
    
}
