package com.dcdzsoft.business.sm;

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
import com.dcdzsoft.config.ApplicationConfig;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 全局配置设置 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SMGlobalCfgSet extends ActionBean {

    public int doBusiness(InParamSMGlobalCfgSet in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.locale)
            || StringUtils.isEmpty(in.sysRunModel)
            || StringUtils.isEmpty(in.interfacePackage)
            || StringUtils.isEmpty(in.workerCount))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //3.	调用UtilDAO.getCurrentDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
        OPOperator operSuperObj = new OPOperator();
        operSuperObj.OperID = in.OperID;
        operSuperObj = operDAO.find(operSuperObj);

        if (operSuperObj.UserID.equalsIgnoreCase(Constant.DEFAULT_SUPEROPERID) == false)
            throw new EduException(ErrorCode.ERR_OPERNOOPENFUNC);

        ApplicationConfig apCfg = ApplicationConfig.getInstance();

        apCfg.setLocale(in.locale);
        //apCfg.setSysRunModel(NumberUtils.parseInt(in.sysRunModel));
        //apCfg.setInterfacePackage(in.interfacePackage);
        //apCfg.setWorkerCount(NumberUtils.parseInt(in.workerCount));
        if("true".equalsIgnoreCase(in.logRawMsg))
            apCfg.setLogRawMsg(true);
        else
            apCfg.setLogRawMsg(false);
        
        if("true".equalsIgnoreCase(in.logMbMsg))
            apCfg.setLogMbMsg(true);
        else
            apCfg.setLogMbMsg(false);
        
        if("true".equalsIgnoreCase(in.sentToGuotong)){
            apCfg.setSentToGuotong(true);
            apCfg.setUploadToPartner(true);
            
        }  
        else{
            apCfg.setSentToGuotong(false);
            apCfg.setUploadToPartner(false);
        }
            
        /*
        apCfg.setSendShortMsg(in.sendShortMsg);
        apCfg.setGatewayUser(in.gatewayUser);
        apCfg.setGatewayPwd(in.gatewayPwd);
        */
        
        /*apCfg.setMbHost(in.mbHost);
        apCfg.setMbPort(in.mbPort);
        apCfg.setMbUri(in.mbUri);

        apCfg.setFtpHost(in.ftpHost);
        apCfg.setFtpPort(in.ftpPort);
        apCfg.setFtpUser(in.ftpUser);
        apCfg.setFtpPasswd(in.ftpPasswd);
        */

        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = StringUtils.reflectionToString(in);

        commonDAO.addOperatorLog(log);

        return result;
    }
}
