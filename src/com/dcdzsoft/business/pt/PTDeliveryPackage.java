package com.dcdzsoft.business.pt;

import java.util.Calendar;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.email.EmailSenderManager;
import com.dcdzsoft.guotong.GuotongManager;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.sequence.SequenceGenerator;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.config.ApplicationConfig;

/**
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * <p>
 * Description: 投递包裹
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: dcdzsoft
 * </p>
 * 
 * @author zhengxy
 * @version 1.0
 */

public class PTDeliveryPackage extends ActionBean {

	public OutParamPTDeliveryPackage doBusiness(InParamPTDeliveryPackage in)
			throws EduException {
		utilDAO = this.getUtilDAO();
		commonDAO = this.getCommonDAO();
		dbSession = this.getCurrentDBSession();
		OutParamPTDeliveryPackage out = new OutParamPTDeliveryPackage();

		// 1. 验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
				|| StringUtils.isEmpty(in.PostmanID)
				|| StringUtils.isEmpty(in.PackageID)
				|| StringUtils.isEmpty(in.BoxNo)
				|| StringUtils.isEmpty(in.LeftFlag))
			throw new EduException(ErrorCode.ERR_PARMERR);

		// 验证投递员是否存在
		PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
		PMPostman postman = new PMPostman();
		postman.PostmanID = in.PostmanID;
		try {
			postman = postmanDAO.find(postman);
		} catch (Exception e) {
			throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
		}
		
		//设备是否存在
		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		TBTerminal terminal = new TBTerminal();
		terminal.TerminalNo = in.TerminalNo;

		terminal = terminalDAO.find(terminal);
		

		// 验证柜体权限(登陆时候验证)
		// commonDAO.checkManTerminalRight(postman, in.TerminalNo);
		
		// //////////////////////////////////////////////////////////////////////
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		int timeDiff = NumberUtils.parseInt(ControlParam.getInstance().getInterTimeDifferent());
		if(timeDiff>0){
		    //System.out.println("===时差"+timeDiff);
	        sysDateTime = DateUtils.addTimeStampByHour(sysDateTime, timeDiff);      //国际时差，根据不同的国家设定不同的时间       
        }
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		// //////////////////////////////////////////////////////////////////////////////
		// 判断是否已经投递过
		PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
		
		JDBCFieldArray whereCols = new JDBCFieldArray();
		whereCols.add("PackageID", in.PackageID);
		whereCols.add("TerminalNo", in.TerminalNo);

		if (inboxPackDAO.isExist(whereCols) > 0)
		// throw new EduException(ErrorCode.ERR_PACKHAVEDELIVERYD);
		{
			PTInBoxPackage inboxPack = new PTInBoxPackage();
			inboxPack.PackageID = in.PackageID;
			inboxPack.TerminalNo = in.TerminalNo;

			inboxPack = inboxPackDAO.find(inboxPack);
			
			////////////////////////等待下一个交易重新排队发送(强制排队)/////////////////////
			if(!inboxPack.TradeWaterNo.equalsIgnoreCase(in.TradeWaterNo))
				throw new EduException(ErrorCode.ERR_PACKHAVEDELIVERYD);

			// 生成返回数据
			out.PackageID = in.PackageID;
			out.BoxNo = in.BoxNo;
			out.OpenBoxKey = inboxPack.OpenBoxKey;
			out.ServerTime = sysDateTime;

			return out;
		}

		//生成取件密码
		
		String blankBoxKey = "";//明文
		if(999 == ApplicationConfig.getInstance().getSysRunModel()){
		    blankBoxKey = "555555"+in.BoxNo;
		}else{
		    blankBoxKey = commonDAO.generatePickupPwd(in.OpenBoxKey, in.TerminalNo);//明文
		}
		//System.out.println(ApplicationConfig.getInstance().getSysRunModel()+","+blankBoxKey);
		in.OpenBoxKey      = SecurityUtils.md5(blankBoxKey);//密文
		// 判断箱门是否已经在用????
		
		
		// 插入在箱信息
		PTInBoxPackage inboxPack = new PTInBoxPackage();
		inboxPack.TerminalNo = in.TerminalNo;
		inboxPack.BoxNo = in.BoxNo;
		inboxPack.PackageID = in.PackageID;
		inboxPack.PostmanID = in.PostmanID;
		inboxPack.CompanyID = postman.CompanyID;
		inboxPack.DynamicCode = in.DynamicCode;
		inboxPack.TradeWaterNo = in.TradeWaterNo;
		inboxPack.StoredTime = sysDateTime;
		inboxPack.StoredDate = sysDate;
		inboxPack.ExpiredTime = in.ExpiredTime;
		inboxPack.CustomerID = in.CustomerID;
		inboxPack.CustomerMobile = in.CustomerMobile;
		inboxPack.OpenBoxKey = in.OpenBoxKey;
		inboxPack.CustomerName = "";
		inboxPack.CustomerAddress = "";
		inboxPack.LeftFlag = in.LeftFlag;
		
		inboxPack.PackageStatus = SysDict.PACKAGE_STATUS_NORMAL;
		inboxPack.LastModifyTime = sysDateTime;
		
		inboxPack.StaOrderID = in.StaOrderID;
		inboxPack.OfLogisticsID = in.OfLogisticsID;
		inboxPack.OfLogisticsName = in.OfLogisticsName;
		inboxPack.BlankBoxKey = blankBoxKey;

		//格口收费
		if("1".equals(ControlParam.getInstance().getLockerChargeUser())){//对投递员收费
		    StringBuffer remark = new StringBuffer();
            //计算费用
            double amount = generateHireAmt(in.TerminalNo, in.BoxNo, remark);
            //扣费
            inboxPack.PosPayFlag = generateTradeWater(in, amount, remark, postman, sysDateTime);
            inboxPack.HireAmt    = amount;//格口租用金额
            inboxPack.HireWhoPay = SysDict.CUSTOMER_TYPE_POSTMAN;//格口租用支付方
            
        }else{
            inboxPack.PosPayFlag = in.PosPayFlag;
        }
		
		if (commonDAO.isSendItemEventToPartner(inboxPack.CompanyID,postman.InputMobileFlag))
			inboxPack.UploadFlag = SysDict.UPLOAD_FLAG_NO;
		else
			inboxPack.UploadFlag = SysDict.UPLOAD_FLAG_YES;

		inboxPackDAO.insert(inboxPack);

		// 删除待投递列表
		PTReadyPackageDAO readyPackDAO = daoFactory.getPTReadyPackageDAO();
		PTReadyPackage readyPack = new PTReadyPackage();
		readyPack.TerminalNo = in.TerminalNo;
		readyPack.PackageID = in.PackageID;

		readyPackDAO.delete(readyPack);


		// 生成返回数据
		out.PackageID = in.PackageID;
		out.BoxNo = in.BoxNo;
		out.OpenBoxKey = in.OpenBoxKey;
		out.ServerTime = sysDateTime;

		/////////////////////////
		//sendDeliveryMsg(inboxPack, terminal, postman, sysDateTime);
		
		//生成消息
        SMSInfo smsInfo = commonDAO.generateSendInfo(inboxPack, terminal, postman, sysDateTime);
		// 发送投递信息-短信
        commonDAO.sendDeliverySMS(smsInfo);
		//发送投递消息-邮件
        commonDAO.sendDeliveryEmail(smsInfo);
        
		
		// 订单状态反馈给合作方系统
		if (commonDAO.isSendItemEventToPartner(inboxPack.CompanyID,postman.InputMobileFlag)) {
			//sentDeliveryInfo(inboxPack, terminal, postman, sysDateTime);
		    SMSInfo sendInfo = new SMSInfo(smsInfo);
            GuotongManager.getInstance().sentDeliveryInfo(sendInfo);
		}

		return out;
	}
	
	// 
	/**发送投递信息
	 * @param inboxPack
	 * @param terminal
	 * @param postman
	 * @param sysDateTime
	 * @deprecated
	 * @throws EduException
	 */
	private void sendDeliveryMsg(PTInBoxPackage inboxPack, TBTerminal terminal, PMPostman postman, java.sql.Timestamp sysDateTime) throws EduException {
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		// //////////////////////////////////
		MBPwdShortMsgDAO shortMsgDAO = daoFactory.getMBPwdShortMsgDAO();
		MBPwdShortMsg shortMsg = new MBPwdShortMsg();
		SequenceGenerator seqGen = SequenceGenerator.getInstance();

		shortMsg.WaterID = seqGen.getNextKey(SequenceGenerator.SEQ_WATERID);
		shortMsg.TerminalNo = inboxPack.TerminalNo;
		shortMsg.PackageID = inboxPack.PackageID;
		shortMsg.StoredTime = sysDateTime;
		shortMsg.StoredDate = sysDate;
		shortMsg.OpenBoxKey = inboxPack.BlankBoxKey;
		shortMsg.CustomerMobile = inboxPack.CustomerMobile;
		shortMsg.LastModifyTime = sysDateTime;
		shortMsg.ReSendNum = 0;
		shortMsg.PackageStatus = inboxPack.PackageStatus;
		shortMsg.Remark = inboxPack.TradeWaterNo;
		//shortMsg.SenderMobile = inboxPack.Remark;//发件人手机
		
		int hourSendMsg = NumberUtils.parseInt(ControlParam.getInstance().getSendPwdSMSFirstTime());
        int hour = DateUtils.getHour(sysDate);
		if(hour>=hourSendMsg){
		    //早上7点以后即时发送
		    shortMsg.SendStatus = "1"; // 0:未发送 1:发送进行中 2:发送成功 4:发送失败
            shortMsgDAO.insert(shortMsg);
        }else{
            //早上7点以前不发送
            shortMsg.SendStatus = "0"; // 0:未发送 1:发送进行中 2:发送成功 4:发送失败
            shortMsgDAO.insert(shortMsg);
            return;
        }
		
		//发送短信
		SMSInfo smsInfo = new SMSInfo();
		smsInfo.PackageID = inboxPack.PackageID;
		smsInfo.TerminalNo = inboxPack.TerminalNo;
		smsInfo.StoredTime = sysDateTime;
		smsInfo.sysDateTime = sysDateTime;
		smsInfo.CustomerMobile = inboxPack.CustomerMobile;
		smsInfo.OfBureau = terminal.OfBureau;
		smsInfo.BoxNo   = inboxPack.BoxNo;
		smsInfo.OpenBoxKey = inboxPack.BlankBoxKey;
		smsInfo.TerminalName = terminal.TerminalName;
		smsInfo.Location = terminal.Location;
		smsInfo.DepartmentID = terminal.DepartmentID;
		smsInfo.PostmanID = postman.PostmanID;
		smsInfo.PostmanName = postman.PostmanName;
		smsInfo.PostmanMobile = postman.Mobile;
		smsInfo.CompanyID = postman.CompanyID;
		smsInfo.PackageStatus = inboxPack.PackageStatus;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_DELIVERY;
		smsInfo.TradeWaterNo = inboxPack.TradeWaterNo;
		smsInfo.StaOrderID = inboxPack.StaOrderID;
		smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
		smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
		smsInfo.TradeWaterNo = inboxPack.TradeWaterNo;
		smsInfo.MBDeviceNo = terminal.MBDeviceNo;
		smsInfo.WaterID = shortMsg.WaterID;

		ControlParam ctrlParam = ControlParam.getInstance();
		try {
			//发送投递短信0:不使用,1:给客户,2:给投递员,3:给投递公司,4:都发送
		    if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg())) {
	    	
		    	switch(ctrlParam.getSendDeliverSMS()){
		    	case "1":break;
		    	case "2":{
		    		SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
		    		SMSManager.getInstance().sentDeliverySMSToPostman(smsInfoToPostman);break;}
		    	case "3":{
		    		SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
		    		SMSManager.getInstance().sentDeliverySMSToCompany(smsInfoToCompany);break;}
		    	case "4":{
		    		SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
		    		SMSManager.getInstance().sentDeliverySMSToPostman(smsInfoToPostman);
		    		SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
		    		SMSManager.getInstance().sentDeliverySMSToCompany(smsInfoToCompany);break;}
		    	}
		    	SMSManager.getInstance().sentDeliverySMS(smsInfo);				
		    }
	    	//发送投递邮件0:不使用,1:给客户,2:给投递员,3:给投递公司,4:都发送
		    if(StringUtils.isNotEmpty(ctrlParam.getSendDeliverEmail())){
		        //不同公司投递员由不同公司的管理邮箱发送。
		        PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
		        PMCompany company = new PMCompany();
		        company.CompanyID = postman.CompanyID;
		        company = companyDAO.find(company);
		        smsInfo.CompanyMobile = company.ContactTel;//公司联系人电话
		        smsInfo.FromEamilAddress = company.Address;//公司邮箱地址-->发件人地址
		        smsInfo.UserName = company.Address;//用户邮箱地址（一般就是发件人地址）
		        smsInfo.EmailPwd = company.Remark;//255字节
		        
		        switch(ctrlParam.getSendDeliverEmail()){
	            case "1":{
	                smsInfo.Email = inboxPack.CustomerMobile;   //柜端取件人手机号作为邮箱
	                EmailSenderManager.getInstance().sendDeliverEmail(smsInfo,"ToCM");break;}
	            case "2":{
	                smsInfo.Email = postman.Email;
	                EmailSenderManager.getInstance().sendDeliverEmail(smsInfo,"ToPM");break;}
	            case "3":{
	                smsInfo.Email = smsInfo.FromEamilAddress;//公司邮箱发给自己邮箱作为投递凭证
	                EmailSenderManager.getInstance().sendDeliverEmail(smsInfo,"ToCompany");break;}
	            case "4":{
	                smsInfo.Email = inboxPack.CustomerMobile;   //柜端取件人手机号作为邮箱
	                EmailSenderManager.getInstance().sendDeliverEmail(smsInfo,"ToCM");
	                SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
	                smsInfoToPostman.Email = smsInfoToPostman.Email;
	                EmailSenderManager.getInstance().sendDeliverEmail(smsInfoToPostman,"ToPM");
	                SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
	                smsInfoToCompany.Email = smsInfoToCompany.FromEamilAddress;
	                EmailSenderManager.getInstance().sendDeliverEmail(smsInfoToCompany,"ToCompany");break;}
	            }
		    }
		    

		} catch (EduException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param inboxPack
	 * @param terminal
	 * @param postman
	 * @param sysDateTime
	 * @deprecated
	 */
	private void sentDeliveryInfo(PTInBoxPackage inboxPack, TBTerminal terminal, PMPostman postman, java.sql.Timestamp sysDateTime){
		//
		PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
		PMCompany company = new PMCompany();
		company.CompanyID = postman.CompanyID;
		
		PMLogisticsDAO logisticDAO = daoFactory.getPMLogisticsDAO();
		PMLogistics logistic = new PMLogistics();
		///////////
		try {
			company = companyDAO.find(company);
			
			logistic.LogisticsID = company.LogisticsID;
			logistic = logisticDAO.find(logistic);
		} catch (EduException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SMSInfo smsInfo = new SMSInfo();
		smsInfo.PackageID = inboxPack.PackageID;
		smsInfo.TerminalNo = inboxPack.TerminalNo;
		smsInfo.StoredTime = sysDateTime;
		smsInfo.sysDateTime = sysDateTime;
		smsInfo.CustomerMobile = inboxPack.CustomerMobile;
		smsInfo.OfBureau = terminal.OfBureau;
		smsInfo.BoxNo = inboxPack.BoxNo;
		smsInfo.OpenBoxKey = inboxPack.BlankBoxKey;
		smsInfo.TerminalName = terminal.TerminalName;
		smsInfo.Location = terminal.Location;
		smsInfo.DepartmentID = terminal.DepartmentID;
		smsInfo.PostmanID = postman.PostmanID;
		smsInfo.PostmanName = postman.PostmanName;
		smsInfo.PostmanMobile = postman.Mobile;
		smsInfo.CompanyID = postman.CompanyID;
		smsInfo.CompanyName = company.CompanyName;
		smsInfo.LogisticsName = logistic.LogisticsName;
		smsInfo.PackageStatus = inboxPack.PackageStatus;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_DELIVERY;
		
		smsInfo.TradeWaterNo = inboxPack.TradeWaterNo;
		smsInfo.StaOrderID = inboxPack.StaOrderID;
		smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
		smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
		smsInfo.MBDeviceNo = terminal.MBDeviceNo;
		
		if(StringUtils.isEmpty(smsInfo.OfLogisticsID)){
            smsInfo.OfLogisticsID = company.LogisticsID;
        }
		GuotongManager.getInstance().sentDeliveryInfo(smsInfo);
	}

	/**
	 * 生成格口租用金额
	 * @param inTerminalNo
	 * @param inBoxNo
	 * @param remark
	 * @return amount 
	 */
	private double generateHireAmt(String inTerminalNo,String inBoxNo, StringBuffer remark) {
	    double amount = 0;
	    
	    try{
	        //按格口类型收费：
	        TBServerBoxDAO boxDAO = daoFactory.getTBServerBoxDAO();
	        TBServerBox box = new TBServerBox();
	        box.TerminalNo  = inTerminalNo;
	        box.BoxNo       = inBoxNo;
	        box = boxDAO.find(box);
	        
	        remark.append("格口编号：").append(inBoxNo).append(";");
	        
	        //先查单个柜体格口类型费用
	        TBServerBoxTypeDAO serverBoxTypeDAO = daoFactory.getTBServerBoxTypeDAO();
            TBServerBoxType serverBoxType = new TBServerBoxType();
            serverBoxType.TerminalNo = inTerminalNo;
            serverBoxType.BoxType    = box.BoxType;
            if(serverBoxTypeDAO.isExist(serverBoxType)){
                serverBoxType = serverBoxTypeDAO.find(serverBoxType);
                amount = serverBoxType.ChargeAmt;
                remark.append("格口类型：").append(serverBoxType.BoxTypeName).append(";");
            }else{
                //查统一格口类型费用
                TBBoxTypeDAO boxTypeDAO = daoFactory.getTBBoxTypeDAO();
                TBBoxType boxType = new TBBoxType();
                boxType.BoxType = box.BoxType;
                boxType = boxTypeDAO.find(boxType);
                amount = boxType.ChargeAmt;
                remark.append("格口类型：").append(boxType.BoxTypeName).append(";");
            }
            remark.append("格口费用：").append(amount).append("元;");
	    }catch(EduException e){
	    }
	    return amount;
	}

	/**
	 * 扣费&产生交易流水
	 * @param in
	 * @param amount
	 * @param remark
	 * @param postman
	 * @param sysDateTime
	 * @return PAY_FLAG
	 * @throws EduException
	 */
	private String generateTradeWater(InParamPTDeliveryPackage in, double amount, StringBuffer remark, PMPostman postman, java.sql.Timestamp sysDateTime)throws EduException {
        //扣费
	    if(amount<=0){
            return SysDict.PAY_FLAG_NO;//不需要支付
        }
	    PYConsumedCardDAO cardDAO = daoFactory.getPYConsumedCardDAO();
        PYConsumedCard card = new PYConsumedCard();
        card.CardNo     = postman.BindCardID;
        card.CustomerID = postman.PostmanID;
        if(cardDAO.isExist(card)){
            int amountCent = 0;//分
            //账号存在才扣费
            card = cardDAO.find(card);
            if(card.Discount>=10 && card.Discount<= 1000){//10%~1000%
                amountCent = (int)((amount*card.Discount));//金额（元）*(折扣/100）*100=金额（元）*折扣=金额（分）
            }else{
                amountCent = (int)(amount*100);//金额（元）*100=金额（分）
            }
            JDBCFieldArray setCols1 = new JDBCFieldArray();
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            setCols1.add("LastModifyTime", sysDateTime);
            setCols1.addSQL(" Balance = Balance -  "+amountCent);//分
            int balance = card.Balance - amountCent;
            if(balance <= 0){
                //欠费停用
                setCols1.add("CardStatus", SysDict.CARD_STATUS_DISABLE);
            }
            whereCols1.add("CardNo", card.CardNo);
            //whereCols1.add("CustomerID", card.CustomerID);
            
            cardDAO.update(setCols1, whereCols1);
            
            
            //生成消费账单
            remark.append("账户余额：").append((double)balance/100).append("元;");
            PYTransactionWaterDAO tradeWaterDAO = daoFactory.getPYTransactionWaterDAO();
            PYTransactionWater tradeWater = new PYTransactionWater();
            
            tradeWater.CustomerID      = card.CustomerID;
            tradeWater.TradeWaterNo    = commonDAO.generateTradeWaterNo(sysDateTime);//
            tradeWater.TransactionAmt  = amountCent;//分
            tradeWater.TransactionDate = commonDAO.generateTradeDate(sysDateTime); 
            tradeWater.TransactionType = SysDict.BILL_TYPE_CONSUMED;//消费-格口租金
            tradeWater.ChargeType      = SysDict.CHARGE_TYPE_USAGE;
            tradeWater.TransactionNum  = 1;//1次
            tradeWater.CardNo          = card.CardNo;
            tradeWater.TradeUserID     = postman.Mobile;
            tradeWater.TradeTerminalNo = in.TerminalNo;
            tradeWater.PackageID       = in.PackageID;
            tradeWater.BoxNo           = in.BoxNo;
            tradeWater.ReferenceCode   = "";
            tradeWater.Remark          = remark.toString();
            tradeWater.UploadFlag      = SysDict.UPLOAD_FLAG_YES;
            tradeWater.LastModifyTime = sysDateTime;
            tradeWaterDAO.insert(tradeWater);
            return SysDict.PAY_FLAG_SUCCESS;//支付成功
        }else{
            return SysDict.PAY_FLAG_NO;//不需要支付
        }
        
	}
}
