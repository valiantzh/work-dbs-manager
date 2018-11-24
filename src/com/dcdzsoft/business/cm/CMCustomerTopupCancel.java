package com.dcdzsoft.business.cm;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.outerproxy.SendInfo;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sda.security.SecurityUtils;
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
 * <p>Description: 个人用户取消充值 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class CMCustomerTopupCancel extends ActionBean
{

    public int doBusiness(InParamCMCustomerTopupCancel in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.CustomerID)
			||StringUtils.isEmpty(in.TradeWaterNo)
			||StringUtils.isEmpty(in.OperPassword))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);


		//验证用户密码
		OPOperatorDAO operatorDAO = daoFactory.getOPOperatorDAO();
        OPOperator opOperator = new OPOperator();
        opOperator.OperID = in.OperID;
        opOperator = operatorDAO.find(opOperator);
        if (opOperator.OperPassword.compareTo(SecurityUtils.md5(in.OperPassword)) != 0) {
            throw new EduException(ErrorCode.ERR_OPERPWDWRONG);
        }
        
        //检查是否会员卡是否存在
        PYConsumedCardDAO cardDAO = daoFactory.getPYConsumedCardDAO();
        PYConsumedCard card = new PYConsumedCard();
        card.CardNo     = in.BindCardID;
        if(!cardDAO.isExist(card)){
            throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
        }
        card = cardDAO.find(card);
        if(!in.CustomerID.equals(card.CustomerID)){
            throw new EduException(ErrorCode.ERR_CUSTOMERNOTEXISTS);
        }
      //whereCols0.add("CardNo", card.CardNo);
        //whereCols0.add("CustomerID", card.CustomerID);
        //检查充值流水号：
        PYTransactionWaterDAO tradeWaterDAO = daoFactory.getPYTransactionWaterDAO();
        PYTransactionWater tradeWater = new PYTransactionWater();
        //tradeWater.CardNo          = in.BindCardID;
        //tradeWater.CustomerID      = in.CustomerID;
        tradeWater.TradeWaterNo    = in.TradeWaterNo;//
        if(!tradeWaterDAO.isExist(tradeWater)){
            throw new EduException(ErrorCode.ERR_BILLWATERIDNOTEXISTS);
        }
        
        tradeWater = tradeWaterDAO.find(tradeWater);
        if(!SysDict.BILL_TYPE_TOPUP.equals(tradeWater.TransactionType)
                ||!in.BindCardID.equals(tradeWater.CardNo)
                ||!in.CustomerID.equals(tradeWater.CustomerID)){
            throw new EduException(ErrorCode.ERR_FORBIDDELBILLWATER);
        }
        int days = DateUtils.diffDate(sysDate, tradeWater.LastModifyTime);
        if(days>7){//超过一天，不允许退
            throw new EduException(ErrorCode.ERR_FORBIDDELBILLWATER);
        }
        
        //撤销充值
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        setCols.add("LastModifyTime", sysDateTime);
        if((card.Balance-tradeWater.TransactionAmt) <= 0){
            setCols.add("CardStatus", SysDict.CARD_STATUS_DISABLE);
        }
        setCols.addSQL(" Balance = Balance -  "+tradeWater.TransactionAmt);
        
        whereCols.add("CardNo", card.CardNo);
        cardDAO.update(setCols, whereCols);
        
        //修改充值流水中交易账单状态
        JDBCFieldArray setCols0 = new JDBCFieldArray();
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        setCols0.add("LastModifyTime", sysDateTime);
        setCols0.checkAdd("Remark", in.Remark);
        setCols0.add("TransactionType",SysDict.BILL_TYPE_CANCEL);
        
        whereCols0.add("TradeWaterNo", in.TradeWaterNo);
        tradeWaterDAO.update(setCols0, whereCols0);
        
        //同步充值流水
        if ("1".equalsIgnoreCase(ControlParam.getInstance().getUploadTopupRecord())){
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
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = ""+card.CardNo+","+card.CustomerID+","+in.TradeWaterNo+","+tradeWater.TransactionAmt;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
