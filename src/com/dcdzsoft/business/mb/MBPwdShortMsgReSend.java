package com.dcdzsoft.business.mb;

import java.util.Calendar;

import javax.sql.RowSet;

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
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 取件密码短消息重新发送 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBPwdShortMsgReSend extends ActionBean
{

    public int doBusiness(InParamMBPwdShortMsgReSend in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.OperID) ||
			StringUtils.isEmpty(in.WaterID))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//2.	调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
		
		//3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		
		////////////////////////////////////////////////////////
		String[] waterIDList = in.WaterID.split(",");
		for(int i = 0; i < waterIDList.length; i++)
		{
			sentPwdShortMsg(NumberUtils.parseLong(waterIDList[i]),sysDateTime);
		}
		
		// 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
		OPOperatorLog log = new OPOperatorLog();
		log.OperID = in.OperID;
		log.FunctionID = in.getFunctionID();
		log.OccurTime = sysDateTime;
		log.StationAddr = operOnline.LoginIPAddr;
		log.Remark = "";

        return result;
    }
    
    public void sentPwdShortMsg(long WaterID,java.sql.Timestamp sysDateTime) throws EduException
    {
		////////////////////////////////////////////////////////////
		MBPwdShortMsgDAO pwdSMSDAO = daoFactory.getMBPwdShortMsgDAO();
		MBPwdShortMsg smsObj = new MBPwdShortMsg();
		smsObj.WaterID = WaterID;
		
		smsObj = pwdSMSDAO.find(smsObj);
		
		PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
		PTInBoxPackage inboxPack = new PTInBoxPackage();
        inboxPack.TerminalNo = smsObj.TerminalNo;
        inboxPack.PackageID = smsObj.PackageID;
        
        //非在箱信息不发送
        try{
        	inboxPack = inboxPackDAO.find(inboxPack);
        }catch(EduException e){
        	return;
        }
		
		/*JDBCFieldArray setCols = new JDBCFieldArray();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		
		//setCols.add("OpenBoxKey",smsObj.OpenBoxKey);
		setCols.add("ReSendNum", smsObj.ReSendNum + 1);
		setCols.add("LastModifyTime", sysDateTime);
		
		whereCols.add("WaterID", WaterID);
		
		pwdSMSDAO.update(setCols, whereCols);	
		*/
		//发送信息
		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		TBTerminal terminal = new TBTerminal();
		terminal.TerminalNo = smsObj.TerminalNo;
		
		terminal = terminalDAO.find(terminal);		
		
		PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
		PMPostman postman = new PMPostman();
		postman.PostmanID = inboxPack.PostmanID;
		
		postman = postmanDAO.find(postman);		    
		////////////////
		/*SMSInfo smsInfo = new SMSInfo();
		smsInfo.PackageID = smsObj.PackageID;
		//smsInfo.PackageID = inboxPack.StaOrderID;//华为发送短信按用户下订单的运单号发送			
		smsInfo.TerminalNo = smsObj.TerminalNo;
		smsInfo.StoredTime = smsObj.StoredTime;
		smsInfo.CustomerMobile = smsObj.CustomerMobile;
		smsInfo.OpenBoxKey = smsObj.OpenBoxKey;
		smsInfo.TerminalName = terminal.TerminalName;
		smsInfo.Location = terminal.Location;
		smsInfo.DepartmentID = terminal.DepartmentID;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_RESENT;
		smsInfo.WaterID = WaterID;
		smsInfo.StaOrderID = inboxPack.StaOrderID;
		smsInfo.PostmanID = inboxPack.PostmanID;
		smsInfo.PostmanName = postman.PostmanName;
		smsInfo.PostmanMobile = postman.Mobile;
		smsInfo.CompanyID = postman.CompanyID;
		smsInfo.MBDeviceNo = terminal.MBDeviceNo;
		smsInfo.Remark     = smsObj.Remark;
		smsInfo.OfBureau   = terminal.OfBureau;
	//	smsInfo.Remark     = smsObj.Remark.substring(0,smsObj.Remark.indexOf("_"));
	//	smsInfo.BoxNo = smsObj.Remark.substring(smsObj.Remark.indexOf("_")+1);
		if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()))
		{
			SMSManager.getInstance().sentDeliverySMS(smsInfo);
		}
		if(!"0".equals(ControlParam.getInstance().getSendDeliverEmail())){
			smsInfo.Email = inboxPack.CustomerMobile;	//柜端取件人手机号作为邮箱
			EmailSenderManager.getInstance().sendDeliverEmail(smsInfo,"ToCM");
		}*/
		
		//生成消息
        SMSInfo smsInfo = commonDAO.generateSendInfo(inboxPack, terminal, postman, sysDateTime);
        smsInfo.WaterID = WaterID;
        // 发送投递信息-短信
        commonDAO.sendDeliverySMS(smsInfo);
        //发送投递消息-邮件
        commonDAO.sendDeliveryEmail(smsInfo);
    }
}

