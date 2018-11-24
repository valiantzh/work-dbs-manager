package com.dcdzsoft.business.cm;

import java.text.SimpleDateFormat;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.outerproxy.SendInfo;
import com.dcdzsoft.sda.db.*;
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
 * <p>Description: 个人用户充值 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class CMCustomerTopup extends ActionBean
{

    public int doBusiness(InParamCMCustomerTopup in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.CustomerID)
			|| in.Amount < 0)
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		if(StringUtils.isEmpty(in.OperID)){
		    in.OperID = in.CustomerID;
		}else{
		    OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		}

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		if(StringUtils.isEmpty(in.TradeWaterNo)){
		    in.TradeWaterNo = commonDAO.generateTradeWaterNo(sysDateTime);
		}
		int amount = (int)(in.Amount*100);//数据库中保存金额单位：分
		
		ControlParam ctlParam = ControlParam.getInstance();
		//会员卡
		PYConsumedCard card = new PYConsumedCard();
		if(SysDict.CUSTOMER_TYPE_POSTMAN.equals(in.CustomerType)){
            //投递员用户信息查询
            PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
            PMPostman obj = new PMPostman();
            obj.PostmanID = in.CustomerID;
            if(!postmanDAO.isExist(obj)){
                throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
            }
            obj = postmanDAO.find(obj);
            if(StringUtils.isEmpty(obj.BindCardID)){//未绑定卡
                if(StringUtils.isNotEmpty(in.BindCardID))
                {
                    //检查是卡是否被其他用户绑定
                    JDBCFieldArray whereCols0 = new JDBCFieldArray();
                    whereCols0.add("BindCardID",in.BindCardID);
                    whereCols0.add("PostmanID","<>",obj.PostmanID);

                    if(postmanDAO.isExist(whereCols0) > 0)
                        throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
                    
                    //绑定卡号
                    JDBCFieldArray setCols = new JDBCFieldArray();
                    JDBCFieldArray whereCols = new JDBCFieldArray();
                    setCols.add("BindCardID", in.BindCardID);
                    whereCols.add("PostmanID", obj.PostmanID);
                    
                    postmanDAO.update(setCols, whereCols);
                    obj.BindCardID = in.BindCardID;
                }else{
                    //未绑定卡，需要绑卡
                    throw new EduException(ErrorCode.ERR_CUSTOMERBANDCARDNOTEXISTS);
                }
            }
            
            //会员卡号
            card.CardNo     = obj.BindCardID;
            card.CustomerID = obj.PostmanID;
		}else{
		    CMCustomerDAO customerDAO = daoFactory.getCMCustomerDAO();
		    CMCustomer obj = new CMCustomer();
            obj.CustomerID = in.CustomerID;
            if(!customerDAO.isExist(obj)){
                if("1".equals(ctlParam.getCardNoTopupMod())//允许充值时插入会员信息（会员信息在泰和系统中）
                    && StringUtils.isNotEmpty(in.TerminalNo)){
                    //插入用户信息
                    obj.CustomerName   = in.CustomerID;
                    obj.CustomerMobile = "";
                    obj.CustomerStatus = SysDict.CUSTOMER_STATUS_NROMAL;
                    obj.CustomerType   = SysDict.CUSTOMER_TYPE_NROMAL;//0-普通用户
                    obj.TerminalNo     = in.TerminalNo;
                    obj.CreateTime     = sysDateTime;
                    obj.LastModifyTime = sysDateTime;
                    obj.Remark         = in.Remark;
                    customerDAO.insert(obj);
                }else{
                    throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
                }
            }
            obj = customerDAO.find(obj);
            
            //绑定卡号
            if(StringUtils.isEmpty(obj.BindCardID)){
                if(StringUtils.isNotEmpty(in.BindCardID))
                {
                    //检查是卡是否被其他用户绑定
                    JDBCFieldArray whereCols0 = new JDBCFieldArray();
                    whereCols0.add("BindCardID",in.BindCardID);
                    whereCols0.add("CustomerID","<>",obj.CustomerID);

                    if(customerDAO.isExist(whereCols0) > 0)
                        throw new EduException(ErrorCode.ERR_BUSINESS_EXISTSBINDINGCARD);
                    
                    //绑定卡号
                    JDBCFieldArray setCols = new JDBCFieldArray();
                    JDBCFieldArray whereCols = new JDBCFieldArray();
                    setCols.add("BindCardID", in.BindCardID);
                    whereCols.add("CustomerID", obj.CustomerID);
                    
                    customerDAO.update(setCols, whereCols);
                    obj.BindCardID = in.BindCardID;
                }else{
                    throw new EduException(ErrorCode.ERR_CUSTOMERBANDCARDNOTEXISTS);
                }
            }
            
            //会员卡号
            card.CardNo     = obj.BindCardID;
            card.CustomerID = obj.CustomerID;
		}
		
		if(in.Discount<10 || in.Discount> 1000){
		    in.Discount = 100;//100%  表示原价
		}
		//查询会员卡信息,更新余额
		PYConsumedCardDAO cardDAO = daoFactory.getPYConsumedCardDAO();
		if(cardDAO.isExist(card)){
		    card = cardDAO.find(card);
		    JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();
            
            setCols.add("LastModifyTime", sysDateTime);
            setCols.addSQL(" Balance = Balance +  "+amount);
            if(!SysDict.CARD_STATUS_NORMAL.equals(card.CardStatus)
                    &&(card.Balance+amount)>0){
                setCols.add("CardStatus", SysDict.CARD_STATUS_NORMAL);
            }
            setCols.add("Discount", in.Discount);
            if(!in.CustomerID.equals(card.CustomerID)){
                //会员卡更换了使用的会员账号
                setCols.add("CustomerID", in.CustomerID);////???????
            }
            
            whereCols.add("CardNo", card.CardNo);
            cardDAO.update(setCols, whereCols);
            
		}else{
		    card.Balance     = amount;
		    card.BalanceUnit = SysDict.CARD_BALANCE_UNIT_YUAN;//按金额充值
		    if(card.Balance>0){
		        card.CardStatus  = SysDict.CARD_STATUS_NORMAL;
		    }else{
		        card.CardStatus  =  SysDict.CARD_STATUS_DISABLE;
		    }
		    
		    card.CardType    = SysDict.CARD_TYPE_ICCARD;//默认IC卡
		    card.CreditLimit = 0;
		    card.Discount    = in.Discount;
		    card.CreateTime  = sysDateTime;
		    card.EffectiveDate = sysDate;
		    card.WarningFlag   = "0";
		    card.WarningLimit  = 0;
		    card.LastModifyTime = sysDateTime;
		    cardDAO.insert(card);
		}
		//记录充值流水
		if(amount > 0){
		    PYTransactionWaterDAO tradeWaterDAO = daoFactory.getPYTransactionWaterDAO();
	        PYTransactionWater tradeWater = new PYTransactionWater();
	        tradeWater.CardNo          = card.CardNo;
	        tradeWater.CustomerID      = card.CustomerID;
	        tradeWater.TradeWaterNo    = in.TradeWaterNo;//
	        tradeWater.LastModifyTime  = sysDateTime;
	        tradeWater.TransactionAmt  = amount;//分
	        tradeWater.TradeTerminalNo = in.TerminalNo;
	        tradeWater.TransactionType = SysDict.BILL_TYPE_TOPUP;
	        tradeWater.TradeUserID     = in.OperID;
	        tradeWater.TransactionDate = commonDAO.generateTradeDate(sysDateTime); 
	        tradeWater.PackageID       = "";
	        tradeWater.Remark          = "Discount="+in.Discount;
	        if ("1".equalsIgnoreCase(ctlParam.getUploadTopupRecord()))
	            tradeWater.UploadFlag  = SysDict.UPLOAD_FLAG_NO;
	        else
	            tradeWater.UploadFlag  = SysDict.UPLOAD_FLAG_YES;
	        
	        tradeWaterDAO.insert(tradeWater);
	        
	        //同步充值流水
	        if ("1".equalsIgnoreCase(ctlParam.getUploadTopupRecord())){
	            SendInfo sendInfo  = new SendInfo();
	            sendInfo.CardNo          = tradeWater.CardNo;
	            sendInfo.CustomerID      = tradeWater.CustomerID;
	            sendInfo.TradeWaterNo    = tradeWater.TradeWaterNo;
	            sendInfo.TransactionAmt  = tradeWater.TransactionAmt;
	            sendInfo.TransactionDate = tradeWater.TransactionDate;
	            sendInfo.TransactionType = tradeWater.TransactionType;
	            sendInfo.TerminalNo      = tradeWater.TradeTerminalNo;
	            sendInfo.PackageID       = tradeWater.PackageID;
	            sendInfo.Remark          = tradeWater.Remark   ;
	            GuotongManager.getInstance().sentCustomerInfo(sendInfo);
	        }
		}
		
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		if(StringUtils.isNotEmpty(in.TerminalNo)){
		    log.StationAddr = in.TerminalNo;
		}else{
		    log.StationAddr = "";
		}
		
		log.Remark = in.CustomerID+","+in.BindCardID+","+in.Amount;

		commonDAO.addOperatorLog(log);

        return result;
    }
}
