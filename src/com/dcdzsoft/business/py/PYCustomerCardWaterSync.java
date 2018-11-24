package com.dcdzsoft.business.py;

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
 * <p>Description: 同步充值流水 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PYCustomerCardWaterSync extends ActionBean
{

    public int doBusiness(InParamPYCustomerCardWaterSync in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.CardNo)
			||StringUtils.isEmpty(in.CustomerID)
			||StringUtils.isEmpty(in.TradeWaterNo))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

	    //3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);


        //交易流水
        PYTransactionWaterDAO tradeWaterDAO = daoFactory.getPYTransactionWaterDAO();
        PYTransactionWater tradeWater = new PYTransactionWater();
        tradeWater.CardNo          = in.CardNo;
        tradeWater.CustomerID      = in.CustomerID;
        tradeWater.TradeWaterNo    = in.TradeWaterNo;//
        if(!tradeWaterDAO.isExist(tradeWater)){
            throw new EduException(ErrorCode.ERR_BILLWATERIDNOTEXISTS);
        }
        tradeWater = tradeWaterDAO.find(tradeWater);
        
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
		log.Remark = "";

		commonDAO.addOperatorLog(log);


        return result;
    }
}
