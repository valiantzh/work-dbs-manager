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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 箱租用金额设置 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class TBBoxHireSet extends ActionBean
{

    public int doBusiness(InParamTBBoxHireSet in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
		    ||StringUtils.isEmpty(in.BoxType)
			||in.ChargeAmt < 0 )
			throw new EduException(ErrorCode.ERR_PARMERR);

		String remark = "type="+in.BoxType+",amount="+in.ChargeAmt;
		//2.调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		TBBoxTypeDAO boxTypeDAO = daoFactory.getTBBoxTypeDAO();
		
		if(StringUtils.isNotEmpty(in.TerminalNo)){
		    //单个柜子的格口类型费用设置
		    TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		    TBTerminal terminal = new TBTerminal();
		    terminal.TerminalNo = in.TerminalNo;
		    if(!terminalDAO.isExist(terminal)){
		        throw new EduException(ErrorCode.ERR_TERMINALNOTEXISTS);
		    }
		    TBBoxType boxType = new TBBoxType();
		    boxType.BoxType = in.BoxType;
		    boxType = boxTypeDAO.find(boxType);
		    
		    //设置服务端设备类型费用
		    TBServerBoxTypeDAO serverBoxTypeDAO = daoFactory.getTBServerBoxTypeDAO();
		    TBServerBoxType serverBoxType = new TBServerBoxType();
		    serverBoxType.TerminalNo = in.TerminalNo;
		    serverBoxType.BoxType    = in.BoxType;
		    
		    if(serverBoxTypeDAO.isExist(serverBoxType)){
		        JDBCFieldArray setCols = new JDBCFieldArray();
	            JDBCFieldArray whereCols = new JDBCFieldArray();
	            setCols.add("ChargeAmt", in.ChargeAmt);
	            setCols.add("Remark", in.Remark);
	            whereCols.add("BoxType", in.BoxType);
	            whereCols.add("TerminalNo", in.TerminalNo);
	            serverBoxTypeDAO.update(setCols, whereCols);
		    }else{
		        serverBoxType.BoxTypeName = boxType.BoxTypeName;
	            serverBoxType.BoxDepth    = boxType.BoxDepth;
	            serverBoxType.BoxHeight   = boxType.BoxHeight;
	            serverBoxType.BoxInterval = boxType.BoxInterval;
	            serverBoxType.BoxWidth    = boxType.BoxWidth;
	            serverBoxType.MBBoxType   = boxType.MBBoxType;
	            serverBoxType.ChargeAmt   = in.ChargeAmt;
	            serverBoxType.Remark      = in.Remark;
	            serverBoxTypeDAO.insert(serverBoxType);
		    }
		    remark+= ",terminalno="+in.TerminalNo;
		}else{
		    //统一的格口类型费用
		    if (in.OperID.equalsIgnoreCase(Constant.DEFAULT_SUPEROPERID) == false)
	            throw new EduException(ErrorCode.ERR_OPERNOOPENFUNC);
		    
	        JDBCFieldArray setCols = new JDBCFieldArray();
	        JDBCFieldArray whereCols = new JDBCFieldArray();
	        setCols.add("ChargeAmt", in.ChargeAmt);
	        setCols.add("Remark", in.Remark);
	        whereCols.add("BoxType", in.BoxType);
	        boxTypeDAO.update(setCols, whereCols);
		}
		
        
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = remark;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
