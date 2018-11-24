package com.dcdzsoft.business.pt;

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
 * <p>Description: 重新发送用户取件密码 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTReSentOpenBoxKey extends ActionBean
{

    public String doBusiness(InParamPTReSentOpenBoxKey in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        String result = "";

		//1.	验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
			||StringUtils.isEmpty(in.TradeWaterNo))
			throw new EduException(ErrorCode.ERR_PARMERR);

		PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
		PTInBoxPackage inboxPack = new PTInBoxPackage();
        inboxPack.TerminalNo = in.TerminalNo;
        inboxPack.PackageID = in.PackageID;
        
        //非在箱信息不发送
        try
        {
        	inboxPack = inboxPackDAO.find(inboxPack);
        	if(!inboxPack.CustomerMobile.equalsIgnoreCase(in.CustomerMobile))
        		return result;
        	if(SysDict.PACKAGE_STATUS_RETURNGOODS.equals(inboxPack.PackageStatus)){//退货在箱，不发送
        	    return result;
        	}
        }catch(EduException e)
        {
        	return result;
        }
        	
        ////////////////////////////////////////////////////////////////////
        MBPwdShortMsgDAO pwdSMSDAO = daoFactory.getMBPwdShortMsgDAO();
        MBPwdShortMsg smsObj = new MBPwdShortMsg();
		
        JDBCFieldArray whereCols0 = new JDBCFieldArray();
        whereCols0.add("PackageID", in.PackageID);
        whereCols0.add("TerminalNo", in.TerminalNo);
        whereCols0.add("StoredTime", inboxPack.StoredTime);
        whereCols0.add("PackageStatus", SysDict.PACKAGE_STATUS_NORMAL);
        
        RowSet rset = pwdSMSDAO.select(whereCols0);
        while(RowSetUtils.rowsetNext(rset))
        {
        	smsObj.WaterID = RowSetUtils.getLongValue(rset, "WaterID");
        }
        
        if(smsObj.WaterID <= 0)
        	return result;
        
        smsObj = pwdSMSDAO.find(smsObj);
		
        if(smsObj.ReSendNum > 5)
        	return result;
        
        JDBCFieldArray setCols = new JDBCFieldArray();
		JDBCFieldArray whereCols = new JDBCFieldArray();
		
		//setCols.add("OpenBoxKey",smsObj.OpenBoxKey);
		setCols.add("ReSendNum", smsObj.ReSendNum + 1);
		setCols.add("LastModifyTime", utilDAO.getCurrentDateTime());
		
		whereCols.add("WaterID", smsObj.WaterID);
		
		pwdSMSDAO.update(setCols, whereCols);
		
		//发送信息
		TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
		TBTerminal terminal = new TBTerminal();
		terminal.TerminalNo = smsObj.TerminalNo;
		
		terminal = terminalDAO.find(terminal);		
		
		PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = inboxPack.PostmanID;
        try {
            postman = postmanDAO.find(postman);
        } catch (Exception e) {
            throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
        }
		////////////////
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		/*SMSInfo smsInfo = new SMSInfo();
		smsInfo.PackageID = smsObj.PackageID;
		smsInfo.TerminalNo = smsObj.TerminalNo;
		smsInfo.StoredTime = smsObj.StoredTime;
		smsInfo.sysDateTime = sysDateTime;
		smsInfo.CustomerMobile = smsObj.CustomerMobile;
		smsInfo.OpenBoxKey = smsObj.OpenBoxKey;
		smsInfo.TerminalName = terminal.TerminalName;
		smsInfo.Location = terminal.Location;
		smsInfo.DepartmentID = terminal.DepartmentID;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_RESENT;
		smsInfo.WaterID = smsObj.WaterID;
		smsInfo.StaOrderID = inboxPack.StaOrderID;
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
        smsInfo.WaterID = smsObj.WaterID;
        // 发送投递信息-短信
        commonDAO.sendDeliverySMS(smsInfo);
        //发送投递消息-邮件
        commonDAO.sendDeliveryEmail(smsInfo);
        return result;
    }
}
