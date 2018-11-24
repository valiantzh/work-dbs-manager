package com.dcdzsoft.business.pt;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 重置提货码 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTResetOpenBoxKey extends ActionBean {

    public int doBusiness(InParamPTResetOpenBoxKey in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.PackageID)
            || StringUtils.isEmpty(in.CustomerMobile))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //4.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        
        //////////////////////////////////////////////////////////////
        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
        PTInBoxPackage inboxPack = new PTInBoxPackage();
        
        inboxPack.PackageID = in.PackageID;
        inboxPack.TerminalNo = in.TerminalNo;
        
        try
        {
        	inboxPack = inboxPackDAO.find(inboxPack);
			if(SysDict.PACKAGE_STATUS_RETURNGOODS.equals(inboxPack.PackageStatus)){//退货在箱，不发送
        	    return result;
        	}
        }catch(EduException e)
        {
        	throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);
        }

        //生成开箱密码
        ControlParam ctrlParam = ControlParam.getInstance();
        int pwdLen = NumberUtils.parseInt(ctrlParam.takeOutPwdLen);
        String pwd = "";
        if (SysDict.TAKEOUTPWD_FORM_NUMBER.equals(ctrlParam.takeOutPwdFormat))
            pwd = RandUtils.generateNumber(pwdLen);
        else if (SysDict.TAKEOUTPWD_FORM_CHAR.equals(ctrlParam.takeOutPwdFormat))
            pwd = RandUtils.generateCharacter(pwdLen);
        else if (SysDict.TAKEOUTPWD_FORM_NUMBERCHAR.equals(ctrlParam.takeOutPwdFormat))
            pwd = RandUtils.generateString(pwdLen);

        if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()))
	   		 in.OpenBoxKey = SecurityUtils.md5(pwd);
	   	else
	   		 in.OpenBoxKey = SecurityUtils.md5("333333");

        ////////////////////////////////////////////////////////////////
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
         
        setCols.add("OpenBoxKey",in.OpenBoxKey);
        //setCols.add("LastModifyTime",sysDateTime);
        whereCols.add("PackageID", in.PackageID);
        whereCols.add("TerminalNo", in.TerminalNo);
        
        inboxPackDAO.update(setCols,whereCols);

        //发送短信
        if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg()))
        {
        	TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        	TBTerminal terminal = new TBTerminal();
        	terminal.TerminalNo = in.TerminalNo;
        	
        	terminal = terminalDAO.find(terminal);
        	
        	SMSInfo smsInfo = new SMSInfo();
        	smsInfo.PackageID = in.PackageID;
        	smsInfo.TerminalNo = in.TerminalNo;
        	smsInfo.StoredTime = inboxPack.StoredTime;
        	smsInfo.sysDateTime = sysDateTime;
        	smsInfo.CustomerMobile = in.CustomerMobile;
        	smsInfo.OpenBoxKey = pwd;
        	smsInfo.TerminalName = terminal.TerminalName;
        	smsInfo.DepartmentID = terminal.DepartmentID;
        	smsInfo.Location = terminal.Location;
        	smsInfo.MsgType = SMSInfo.MSG_TYPE_RESENT;
        	
        	SMSManager.getInstance().sentDeliverySMS(smsInfo);
        }
        
        ///推送到设备端
        com.dcdzsoft.businessproxy.PushBusinessProxy.doBusiness(in);
        
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
