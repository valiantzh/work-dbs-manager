package com.dcdzsoft.business.tb;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能储物柜（码柜）运营系统</p>
 * <p>Description: 柜格口费用设置 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class TBTerminalHireSet extends ActionBean
{

    public int doBusiness(InParamTBTerminalHireSet in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.ChargeType)
			||StringUtils.isEmpty(in.ChargeMode)
			||in.Num <= 0 
			||in.Amount < 0 )
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		TBTerminal terminal = new TBTerminal();
		terminal.TerminalNo = in.TerminalNo;
		if(!terminalDAO.isExist(terminal)){
		    throw new EduException(ErrorCode.ERR_TERMINALNOTEXISTS);
		}
		
		StringBuffer desc = new StringBuffer();//收费描述
		/*switch(in.ChargeType){
        case SysDict.CHARGE_TYPE_PREPAY://预付
        case SysDict.CHARGE_TYPE_NORMAL://正常
            break;
        case SysDict.CHARGE_TYPE_EXPIRED://逾期
            break;
        default:
             break;   
        }*/
		desc.append(in.Amount).append("元/");
		switch(in.ChargeMode){
        case SysDict.CHARGE_TYPE_USAGE://按次收费
            in.Num = 1;
            desc.append("次");
            break;
        case SysDict.CHARGE_TYPE_HOURS://按小时收费
            desc.append(in.Num+"小时");
            desc.append("，不足"+in.Num+"小时，按"+in.Num+"小时计费");
            break;
        case SysDict.CHARGE_TYPE_DAYS://按天收费
            desc.append(in.Num+"天");
            desc.append("，不足"+in.Num+"天，按"+in.Num+"天计费");
            break;
        }
		//
		/*int amount = (int)(in.Amount*100);//保存整型，单位：分
		TBTerminalChargeDAO chargeDAO = daoFactory.getTBTerminalChargeDAO();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		whereCols.add("TerminalNo", in.TerminalNo);
		whereCols.add("ChargeType", in.ChargeType);
		if(chargeDAO.isExist(whereCols)>0){
		    JDBCFieldArray setCols = new JDBCFieldArray();
		    setCols.add("Amount", amount);
		    setCols.add("Discount", in.Discount);
		    setCols.add("Num", in.Num);
		    setCols.add("ChargeMode", in.ChargeMode);
		    setCols.add("LastModifyTime", sysDateTime);
		    setCols.add("Remark", desc.toString());
		    chargeDAO.update(setCols, whereCols);
		}else{
		    TBTerminalCharge charge = new TBTerminalCharge();
	        charge.TerminalNo = in.TerminalNo;
	        charge.ChargeType = in.ChargeType;
		    charge.Amount = amount;
		    charge.Discount = in.Discount;
		    charge.Num      = in.Num;
		    charge.CreateTime     = sysDateTime;
		    charge.LastModifyTime = sysDateTime;
		    charge.ChargeMode = in.ChargeMode;
		    charge.Remark     = desc.toString();
		    chargeDAO.insert(charge);
		}
		*/
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.TerminalNo+","+in.ChargeType+","+in.ChargeMode+","+in.Amount;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
