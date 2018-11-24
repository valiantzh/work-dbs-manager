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
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 定期发送未发送的密码短信 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PTAutoSendPwdSMS extends ActionBean
{

    public int doBusiness(InParamPTAutoSendPwdSMS in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        
		//1.验证输入参数是否有效，如果无效返回-1。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        
        if(StringUtils.isEmpty(ApplicationConfig.getInstance().getSendShortMsg())){
            return 0;
        }
        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
        
        //查询发送的短信
        MBPwdShortMsgDAO shortMsgDAO = daoFactory.getMBPwdShortMsgDAO();
        JDBCFieldArray whereCols2 = new JDBCFieldArray();
        whereCols2.checkAdd("TerminalNo", in.TerminalNo);
        whereCols2.add("SendStatus", "0");//0-未发送
        
        RowSet rset = shortMsgDAO.select(whereCols2);
        while(RowSetUtils.rowsetNext(rset)){
            MBPwdShortMsg shortMsg = new MBPwdShortMsg();
            shortMsg.WaterID = RowSetUtils.getLongValue(rset, "WaterID");
            if(shortMsg.WaterID <= 0){
                continue;
            }
            shortMsg = shortMsgDAO.find(shortMsg);
            
            //
            PTInBoxPackage inboxPack = new PTInBoxPackage();
            inboxPack.TerminalNo = shortMsg.TerminalNo;
            inboxPack.PackageID = shortMsg.PackageID;
            //非在箱信息不发送
            try
            {
                inboxPack = inboxPackDAO.find(inboxPack);
            }catch(EduException e)
            {
                JDBCFieldArray whereCols = new JDBCFieldArray();
                JDBCFieldArray setCols = new JDBCFieldArray();
                setCols.add("SendStatus", "3");//0:未发送 1:发送进行中 3-取消发送 2:发送成功 4:发送失败
                whereCols.add("WaterID", shortMsg.WaterID);
                shortMsgDAO.update(setCols, whereCols);
                continue;
            }
            
            ////////////////////////////////////////////////////////////////////////
            TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
            TBTerminal terminal = new TBTerminal();
            terminal.TerminalNo = in.TerminalNo;

            terminal = terminalDAO.find(terminal);
            
            PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
            PMPostman postman = new PMPostman();
            postman.PostmanID = inboxPack.PostmanID;
            
            postman = postmanDAO.find(postman);

            PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
            PMCompany company = new PMCompany();
            company.CompanyID = postman.CompanyID;
            
            company = companyDAO.find(company);
            
            SMSInfo smsInfo = new SMSInfo();
            smsInfo.PackageID = inboxPack.PackageID;
            smsInfo.TerminalNo = in.TerminalNo;
            smsInfo.StoredTime = inboxPack.StoredTime;
            smsInfo.sysDateTime = sysDateTime;
            smsInfo.CustomerMobile = shortMsg.CustomerMobile;
            smsInfo.OfBureau = terminal.OfBureau;
            smsInfo.BoxNo = inboxPack.BoxNo;
            smsInfo.OpenBoxKey = shortMsg.OpenBoxKey;
            smsInfo.TerminalName = terminal.TerminalName;
            smsInfo.Location = terminal.Location;
            smsInfo.DepartmentID = terminal.DepartmentID;
            smsInfo.PostmanID = inboxPack.PostmanID;
            smsInfo.PostmanName = postman.PostmanName;
            smsInfo.PostmanMobile = postman.Mobile;
            smsInfo.CompanyID = postman.CompanyID;
            smsInfo.CompanyName = company.CompanyName;
            smsInfo.LogisticsName = inboxPack.OfLogisticsName;
            smsInfo.PackageStatus = inboxPack.PackageStatus;
            smsInfo.MsgType = SMSInfo.MSG_TYPE_DELIVERY;

            smsInfo.TradeWaterNo = inboxPack.TradeWaterNo;
            smsInfo.StaOrderID = inboxPack.StaOrderID;
            smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
            smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
            smsInfo.MBDeviceNo = terminal.MBDeviceNo;
            smsInfo.WaterID = shortMsg.WaterID;
            
            JDBCFieldArray whereCols = new JDBCFieldArray();
            JDBCFieldArray setCols = new JDBCFieldArray();
            setCols.add("SendStatus", "1");// 0:未发送 1:发送进行中 3-取消发送 2:发送成功 4:发送失败
            whereCols.add("WaterID", shortMsg.WaterID);
            shortMsgDAO.update(setCols, whereCols);
            //发送短信
            SMSManager.getInstance().sentDeliverySMS(smsInfo);
            
            result++;
        }
        return result;
    }
}
