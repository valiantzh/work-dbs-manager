package com.dcdzsoft.business.mb;

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
import com.dcdzsoft.businessproxy.PushBusinessProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 推送LED消息 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBPushTerminalLedMsg extends ActionBean
{

    public int doBusiness(InParamMBPushTerminalLedMsg in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID)
			||StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.LedMsg))
			throw new EduException(ErrorCode.ERR_PARMERR);

		//2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

		//3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		//默认值
		if(in.DisplayWay == 0)
			in.DisplayWay = 28; //0   随机    1   翻页    2   左覆盖   3   右覆盖    4   上覆盖   5   下覆盖
		if(in.QuitWay == 0)
			in.QuitWay = 28;    //0   随机    1   翻页    2   左覆盖   3   右覆盖    4   上覆盖   5   下覆盖
		if(in.RunSpeed == 0)
			in.RunSpeed = 5;
		if(in.StopTime == 0)
			in.StopTime = 0;
		if(in.FontSize == 0)
			in.FontSize = 32; //默认值 32 			(16 表示 16 点阵字库,0xff 表示区域自适应 )
		if(in.FontColor == 0)
			in.FontColor = 1; //默认值 0x01	        (红色：0x01，绿色：0x02，黄色： 0x03)
		
		TBLedParamDAO ledParamDAO = daoFactory.getTBLedParamDAO();
		TBLedParam ledParam = new TBLedParam();
		
		ledParam.TerminalNo = in.TerminalNo;
		if(ledParamDAO.isExist(ledParam))
		{
			JDBCFieldArray setCols1 = new JDBCFieldArray();
	    	JDBCFieldArray whereCols1 = new JDBCFieldArray();
	
	    	setCols1.add("LedMsg",in.LedMsg);
	    	setCols1.add("StartPointX",in.StartPointX);
	    	setCols1.add("StartPointY",in.StartPointY);
	    	setCols1.add("LedWidth",in.LedWidth);
	    	setCols1.add("LedHeight",in.LedHeight);
	    	setCols1.add("DisplayWay",in.DisplayWay);
	    	setCols1.add("QuitWay",in.QuitWay);
	    	setCols1.add("RunSpeed",in.RunSpeed);
	    	setCols1.add("StopTime",in.StopTime);
	    	setCols1.add("FontSize",in.FontSize);
	    	setCols1.add("FontColor",in.FontColor);
	    	setCols1.add("Remark",in.Remark);
	    	setCols1.add("LastModifyTime",sysDateTime);
	    	
	    	whereCols1.add("TerminalNo", in.TerminalNo);
	
	    	ledParamDAO.update(setCols1, whereCols1); 
		}else{
			ledParam.LedMsg = in.LedMsg;
			ledParam.StartPointX = in.StartPointX;
			ledParam.StartPointY = in.StartPointY;
			ledParam.LedWidth = in.LedWidth;
			ledParam.LedHeight = in.LedHeight;
			ledParam.DisplayWay = in.DisplayWay;
			ledParam.QuitWay = in.QuitWay;
			ledParam.RunSpeed = in.RunSpeed;
			ledParam.StopTime = in.StopTime;
			ledParam.FontSize = in.FontSize;
			ledParam.FontColor = in.FontColor;
			ledParam.Remark = in.Remark;
			ledParam.LastModifyTime = sysDateTime;
			
			ledParamDAO.insert(ledParam);
		}
		
		////////////////////////////////////////////////////////////////////
		PushBusinessProxy.doBusiness(in,in.TerminalNo);


		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = in.TerminalNo;

		commonDAO.addOperatorLog(log);


        return result;
    }
}
