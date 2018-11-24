package com.dcdzsoft.dao.common.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.sql.RowSet;

import java.util.HashMap;
import java.util.ArrayList;

import org.apache.commons.logging.Log;

import com.dcdzsoft.business.GServer;
import com.dcdzsoft.sda.db.DBSession;
import com.dcdzsoft.sda.db.JDBCFieldArray;
import com.dcdzsoft.sda.db.LocalSessionHolder;
import com.dcdzsoft.sda.db.ProcedureUtils;
import com.dcdzsoft.sda.db.RowSetUtils;
import com.dcdzsoft.EduException;
import com.dcdzsoft.util.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.sequence.*;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.dao.common.CommonDAO;
import com.dcdzsoft.dao.common.UtilDAO;
import com.dcdzsoft.dao.factory.DAOFactory;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.email.EmailSenderManager;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.sda.security.SecurityUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dcdzsoft.sda.db.PreparedWhereExpression;

public class CommonDAOImpl implements CommonDAO {
    private static Log log = org.apache.commons.logging.LogFactory.getLog(
            CommonDAOImpl.class);
    private static ApplicationConfig apcfg = ApplicationConfig.getInstance();
    //private static GServer gserver = GServer.getInstance();

    private DBSession dbSession = LocalSessionHolder.getCurrentDBSession();
    private DAOFactory daoFactory = DAOFactory.getDAOFactory(dbSession.getDatabaseType());
    private UtilDAO utilDAO = daoFactory.getUtilDAO();

    /**
     * 判断操作员是否在线
     * @param OperID String
     * @return OPOperOnline
     * @throws EduException
     */
    public OPOperOnline isOnline(String OperID) throws EduException {
        OPOperOnline result = new OPOperOnline();
        OPOperOnlineDAO operOnlineDAO = daoFactory.getOPOperOnlineDAO();

        result.OperID = OperID;
        try {
            result = operOnlineDAO.find(result);

            //检查online状态 和invalidSession冲突
            if (!result.OnlineStatus.equals("1"))
                throw new EduException(ErrorCode.ERR_OPERNOLOGIN);
        } catch (EduException e) {
            if (ErrorCode.ERR_OPOPERONLINENODATA.equalsIgnoreCase(e.getMessage()))
                throw new EduException(ErrorCode.ERR_OPERNOLOGIN);
            else
                throw e;
        }

        return result;
    }

    /**
     * 记录操作员日志
     * @param log OPOperatorLog
     * @return long
     * @throws EduException
     */
    public long addOperatorLog(OPOperatorLog log) throws EduException {
        String logFlag = GServer.getInstance().getLogFlag(log.FunctionID);
        if (Constant.LOG_FLAG_AUTOMATIC.equals(logFlag)
            || Constant.LOG_FLAG_MANUAL.equals(logFlag)
            || StringUtils.isEmpty(logFlag)) {

            OPOperatorLogDAO logDAO = daoFactory.getOPOperatorLogDAO();

            SequenceGenerator seqGen = SequenceGenerator.getInstance();
            log.OperLogID = seqGen.getNextKey(SequenceGenerator.SEQ_OPERLOGID);
            if (log.OccurTime == null) {
                java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
                log.OccurTime = sysDateTime;
            }

            log.OccurDate = DateUtils.toSQLDate(log.OccurTime);

            logDAO.insert(log);
        }

        return log.OperLogID;
    }


    /**
     * 修改操作员在线信息
     * @param operOnline OPOperOnline
     * @return boolean
     * @throws EduException
     */
    public boolean modifyOperOnline(OPOperOnline operOnline) throws EduException {
        boolean isSameLogin = true;

        OPOperOnlineDAO onlineDAO = daoFactory.getOPOperOnlineDAO();

        //????
        //operOnline.DefaultOperID = operOnline.OperID;
        //operOnline.OnlineStatus = "1";


        if (onlineDAO.isExist(operOnline) == false) {
            operOnline.LastLoginTime = operOnline.LoginInTime;
            operOnline.LastLoginIP = operOnline.LoginIPAddr;
            operOnline.PreVersion = ControlParam.getInstance().currentVersion;
            operOnline.CurrentVersion = ControlParam.getInstance().currentVersion;

            onlineDAO.insert(operOnline);
        } else {
            OPOperOnline lastOperOnline = new OPOperOnline();
            lastOperOnline.OperID = operOnline.OperID;
            lastOperOnline = onlineDAO.find(lastOperOnline);

            JDBCFieldArray setCols = new JDBCFieldArray();

            setCols.add("OperType", operOnline.OperType);

            setCols.add("OnlineStatus", operOnline.OnlineStatus);

            if (operOnline.LoginInTime != null)
                setCols.add("LoginInTime", operOnline.LoginInTime);
            if (operOnline.LoginOutTime != null)
                setCols.add("LoginOutTime", operOnline.LoginOutTime);
            if (StringUtils.isNotEmpty(operOnline.NetCardAddr))
                setCols.add("NetCardAddr", operOnline.NetCardAddr);
            if (StringUtils.isNotEmpty(operOnline.LoginIPAddr))
                setCols.add("LoginIPAddr", operOnline.LoginIPAddr);
            if (operOnline.LastQueryTime != null)
                setCols.add("LastQueryTime", operOnline.LastQueryTime);

            setCols.add("LastLoginTime", lastOperOnline.LoginInTime);
            setCols.add("LastLoginIP", lastOperOnline.LoginIPAddr);
            setCols.add("LoginTerminal", lastOperOnline.LoginTerminal);

            if ("12".equals(operOnline.LoginTerminal)) {
                operOnline.CurrentClientVersion = ControlParam.getInstance().currentVersion;

                setCols.add("PreClientVession", operOnline.PreClientVession);
                setCols.add("CurrentClientVersion", operOnline.CurrentClientVersion);
            } else {
                setCols.add("PreVersion", lastOperOnline.CurrentVersion);
                setCols.add("CurrentVersion", ControlParam.getInstance().currentVersion);
            }

            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("OperID", operOnline.OperID);

            onlineDAO.update(setCols, whereCols);

            if (!lastOperOnline.LoginIPAddr.equalsIgnoreCase(operOnline.LoginIPAddr))
                isSameLogin = false;
        }

        //设置查询最新时间

        return isSameLogin;
    }

    /**
     * 生成管理员编号
     * @return String
     * @throws EduException
     */
    public String getInnerUserID() throws EduException {
        String operid = "";

        OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
        operid = operDAO.selectFunctions("MAX(OperID)", null);

        if (StringUtils.isEmpty(operid) || operid.length() < 10) {
            operid = "1934554321";
        } else {
            operid = String.valueOf(NumberUtils.parseLong(operid) + 1);
        }

        return operid;
    }

    /**
     * 操作员菜单信息查询
     * @param OperID String
     * @param ModuleID String
     * @return RowSet
     * @throws EduException
     */
    public RowSet operMenuQry(String OperID, String ModuleID) throws EduException {
        RowSet rset = null;

        String whereSQL = "";
        if (StringUtils.isNotEmpty(ModuleID))
            whereSQL = " AND a.MenuID like " + StringUtils.leftQuote(ModuleID) + "%'";

        String sql = "SELECT "
                     + " a.OperID,"
                     + " b.MenuID,"
                     + " b.MenuName,"
                     + " b.MenuLevel,"
                     + " b.MenuEngName,"
                     + " b.Description,"
                     + " b.Action,"
                     + " b.HotKey,"
                     + " b.Icon,"
                     + " b.HelpContext,"
                     + " b.MenuType,"
                     + " b.LeafFlag,"
                     + " b.Remark"
                     + " FROM OPOperToMenu a,OPMenu b"
                     + " WHERE a.OperID = " + StringUtils.addQuote(OperID)
                     + whereSQL
                     + " AND b.MenuID = a.MenuID";

        sql = sql + " ORDER BY b.MenuID,b.MenuLevel";

        rset = dbSession.executeQuery(sql);

        return rset;
    }

    /**
     * 检查投递员的柜体权限
     * @param postman PMPostman
     * @param terminalno String
     * @throws EduException
     */
    public void checkManTerminalRight(PMPostman postman, String terminalno) throws EduException {
        ControlParam ctrlParam = ControlParam.getInstance();

        if (ctrlParam.manTerminalRightCheck.equalsIgnoreCase(SysDict.POSTMAN_LOCKRIGHT_JUDGE_COMPANY)) {
            PMCorpTerminalRightDAO corpRightDAO = daoFactory.getPMCorpTerminalRightDAO();
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            whereCols1.add("CompanyID", postman.CompanyID);
            whereCols1.add("TerminalNo", terminalno);

            if (corpRightDAO.isExist(whereCols1) == 0) {
                throw new EduException(ErrorCode.ERR_FORBIDOPERATORLOCK);
            }
        } else if (ctrlParam.manTerminalRightCheck.equalsIgnoreCase(SysDict.POSTMAN_LOCKRIGHT_JUDGE_PERSON)) {
            PMManTerminalRightDAO manRightDAO = daoFactory.getPMManTerminalRightDAO();
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            whereCols1.add("PostmanID", postman.PostmanID);
            whereCols1.add("TerminalNo", terminalno);

            if (manRightDAO.isExist(whereCols1) == 0) {
                throw new EduException(ErrorCode.ERR_FORBIDOPERATORLOCK);
            }
        }
    }
    
    /**
     * 警报消息入库
     * @param TerminalNo
     * @param AlertType
     * @param AlertLevel
     * @param BoxNo
     * @param Remark
     * @throws EduException
     */
    public void insertAlert(String TerminalNo,String AlertType,String AlertLevel,String BoxNo,String Remark) throws EduException
    {
    	java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
		SequenceGenerator seqGen = SequenceGenerator.getInstance();

		MBTerminalAlertLogDAO alertLogDAO = daoFactory.getMBTerminalAlertLogDAO();
		MBTerminalAlertLog alertLog = new MBTerminalAlertLog();

		alertLog.ReportWaterID = seqGen.getNextKey(SequenceGenerator.SEQ_REPORTWATERID);
		alertLog.TerminalNo = TerminalNo;
		alertLog.AlertLevel = AlertLevel;
		alertLog.AlertType = AlertType;
		alertLog.BoxNo = BoxNo;
		alertLog.LogTime = sysDateTime;
		alertLog.LogDate = sysDate;
		alertLog.Remark = Remark;
		
		
		alertLogDAO.insert(alertLog);
    }
    
    /**
     * 判断是否为邮政公司的
     * @param CompanyID
     * @return
     */
    public boolean isYouzhengCompany(String CompanyID)
    {
    	if(StringUtils.isEmpty(CompanyID) || CompanyID.length() < 4)
    		return false;
    	
    	if(CompanyID.substring(0,4).equalsIgnoreCase(Constant.COMPANY_YOUZHENG_ID))
    		return true;
    	
    	return true;
    }
    
    /**
     * 是否需要发送到国通
     * @param PostmanID
     * @param CompanyID
     * @return
     * @throws EduExcption
     */
    public boolean isNeedSendToGuoTong(String CompanyID,String InputMobileFlag) throws EduException
    {
    	String isSendPartner = ControlParam.getInstance().getSendToPartner();
    	if("1".equals(isSendPartner) == true || (StringUtils.isNotEmpty(isSendPartner) && isSendPartner.trim().length() > 1))
    		return true;
    	
    	if(!isYouzhengCompany(CompanyID))
    		return false;
    	
    	if(SysDict.INPUTMOBILE_FLAG_LOCAL.equals(InputMobileFlag))
    		return false;
    	
    	return true;
    }
	/**
     * 订单状态是否同步到合作方系统
     * @param PostmanID
     * @param InputMobileFlag
     * @return
     * @throws EduExcption
     */
    public boolean isSendItemEventToPartner(String CompanyID,String InputMobileFlag) throws EduException{
        //System.out.println("SendItemEventToPartner:"+ApplicationConfig.getInstance().isUploadToPartner()+","+ControlParam.getInstance().getSendToPartner());
        if(ApplicationConfig.getInstance().isUploadToPartner()){
            String isSendPartner = ControlParam.getInstance().getSendToPartner();
            if("1".equals(isSendPartner) == true || (StringUtils.isNotEmpty(isSendPartner) && isSendPartner.trim().length() > 1))
                return true;
            
            if(!isYouzhengCompany(CompanyID))
                return false;
            
            if(SysDict.INPUTMOBILE_FLAG_LOCAL.equals(InputMobileFlag))
                return false;
            
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 柜体是否需要注册到合作方系统（国通、菜鸟等）
     * @return
     * @throws EduExcption
     */
    public boolean isRegisterToPartner() throws EduException{
        if(ApplicationConfig.getInstance().isRegisterToPartner()){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 手机号检查
     * @param input
     * @return
     */
    public boolean isPhoneNumber(String input){  
        String regex="1([\\d]{10})";  
        Pattern p = Pattern.compile(regex); 
        Matcher m = p.matcher(input);  
        
        return m.find();//boolean 
    } 
    /**
     * 生成交易流水:yyyyMMddHHmmss+5位随机数
     * @param sysDateTime
     * @return
     */
    public String generateTradeWaterNo(java.sql.Timestamp sysDateTime) throws EduException{
        SimpleDateFormat datetimeFromat = new SimpleDateFormat("yyyyMMddHHmmss");
        String headStr = datetimeFromat.format(sysDateTime);
        StringBuffer buff = new StringBuffer();
        buff.append(headStr).append(RandUtils.generateNumber(5));
        
        //检查是否重复？？？？？
        /*PYTransactionWaterDAO transWaterDAO = daoFactory.getPYTransactionWaterDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("TransactionNo", buff.toString());
        if(transWaterDAO.isExist(whereCols)>0){
            buff = new StringBuffer();
            buff.append(headStr).append(RandUtils.generateNumber(5));
        }*/
        return buff.toString();
    }
    /**
     * 生成交易时间
     * @param sysDateTime
     * @return
     */
    public String generateTradeDate(java.sql.Timestamp sysDateTime){
        SimpleDateFormat datetimeFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer buff = new StringBuffer(datetimeFromat.format(sysDateTime));
        return buff.toString();
    }

	public String generatePickupPwd(){
		String pwd = "";
		ControlParam ctrlParam = ControlParam.getInstance();
		int pwdLen = NumberUtils.parseInt(ctrlParam.takeOutPwdLen);
		if (SysDict.TAKEOUTPWD_FORM_NUMBER.equals(ctrlParam.takeOutPwdFormat)){
			pwd = RandUtils.generateNumber(pwdLen);
		}else if (SysDict.TAKEOUTPWD_FORM_CHAR.equals(ctrlParam.takeOutPwdFormat)){
			pwd = RandUtils.generateCharacter(pwdLen);
		}else if (SysDict.TAKEOUTPWD_FORM_NUMBERCHAR.equals(ctrlParam.takeOutPwdFormat)){
			pwd = RandUtils.generateString(pwdLen);
		}else {
			pwd = RandUtils.generateNumber(pwdLen);
		}
		if(Constant.PARTNER_NAME_LVCHENG.equals(ctrlParam.getSendToPartner())){
			if(pwd.length() == 6 ){
				pwd = "161"+pwd;//绿城密码规则：长度为9，第一位固定为1，第二位数字6~9  在 同一个园区内，二维码不能重复
			}
		}
		return pwd;
	}
	
	public String generatePickupPwd(String inOpenBoxKey, String inTerminalNo) throws EduException{
		String pwd = "";
		if(StringUtils.isNotEmpty(inOpenBoxKey) && inOpenBoxKey.length() != 32){
			pwd = inOpenBoxKey;
		}else{
			pwd = generatePickupPwd();
		}
		
		PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
		int count = 1;//允许重新生成一次
		do{
			
			inOpenBoxKey = SecurityUtils.md5(pwd);
			JDBCFieldArray whereCols = new JDBCFieldArray();
			whereCols.add("BlankBoxKey", inOpenBoxKey);
			whereCols.add("TerminalNo", inTerminalNo);

			if(inboxPackDAO.isExist(whereCols) < 1){
				break;
			}else{
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {}
				count--;
				if(count <= 0){
					throw new EduException(ErrorCode.ERR_OPENBOXKEYEXISTS);
				}else{
					pwd = generatePickupPwd();
				}
			}
			
		}while(true);
		return pwd;
	}
	
	/**
     * 生成取件密码
     * @return
     */
    public String generatePickupPwd(String inTerminalNo, java.sql.Date sysDate) throws EduException{
        String pwd = generatePickupPwd();
        //泰和密码两天不重复
        int count = 3;
        do{
            MBPwdShortMsgDAO shortMsgDAO = daoFactory.getMBPwdShortMsgDAO();
            JDBCFieldArray whereCols = new JDBCFieldArray();
            whereCols.add("BlankBoxKey", pwd);
            whereCols.add("TerminalNo", inTerminalNo);
            whereCols.add("StoredDate",">=", DateUtils.addDate(sysDate, -1));
            try {
                if(shortMsgDAO.isExist(whereCols)>0){
                    if(count<=0){
                        break;
                    }
                    pwd = generatePickupPwd();
                    count --;
                }else{
                    break;
                }
            } catch (EduException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }
        }while(true);
        return pwd;
    }
    
    /**
     * 指定表中取某列的值（过滤重复值）
     * @param tableName
     * @param fName
     * @param whereSQL
     * @return
     * @throws EduException
     */
    public java.util.Set<String> selectFieldByGroup(String tableName, String fName, PreparedWhereExpression whereSQL)throws EduException{
        java.util.Set<String> resSet = new java.util.HashSet<>();
        if(whereSQL == null){
            whereSQL = new PreparedWhereExpression();
        }
        
        String sql = "SELECT a." + fName + " FROM  "+ tableName+" a WHERE 1=1";
        
        String groupby = " GROUP BY " +fName;
        sql = sql + whereSQL.getPreparedWhereSQL() + groupby;

        RowSet rset = dbSession.executeQuery(sql, whereSQL);
        while(RowSetUtils.rowsetNext(rset)){
            resSet.add(RowSetUtils.getStringValue(rset, 1));//""+fName
        }
        return resSet;
    }
    
    /**
     * 取在箱表中投递公司的值（过滤重复值）
     * @return
     * @throws EduException
     */
    public java.util.Set<String> selectCompanyFromInBoxPackage()throws EduException{
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.add("PackageStatus", SysDict.PACKAGE_STATUS_NORMAL);
        return selectFieldByGroup("PTInBoxPackage", "CompanyID", whereSQL);
    }
    /**
     * 取逾期天数
     * @param companyID
     * @return
     * @throws EduException
     */
    public int expiredDays(String companyID) throws EduException{
        ControlParam ctrlParam = ControlParam.getInstance();
        int expiredDays = NumberUtils.parseInt(ctrlParam.recoveryTimeout);
        if("1".equals(ctrlParam.recoveryTimeSet)//按公司设定逾期天数
                && StringUtils.isNotEmpty(companyID)){
            try{
                PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                JDBCFieldArray whereCols1 = new JDBCFieldArray();
                whereCols1.add("CompanyID", companyID);
                String tmp = companyDAO.selectFunctions("Zip", whereCols1);//国外&字段Zip保存逾期天数
                if(tmp!=null){
                    int days = NumberUtils.parseInt(tmp);
                    if(days>1 && days < expiredDays){
                        expiredDays = days;
                    }
                }
            }catch(EduException e){
            }
        }
        if(expiredDays<1){
            expiredDays = 1;  
        }
        return expiredDays;
    }
    /**
     * 取逾期天数
     * @return
     * @throws EduException
     */
    public int expiredDays() throws EduException{
        return expiredDays("");
    }
    
    /**
     * 生成发送信息
     * @param inboxPack
     * @param terminal
     * @param postman
     * @param sysDateTime
     * @return
     */
    public SMSInfo generateSendInfo(PTInBoxPackage inboxPack, TBTerminal terminal, PMPostman postman, java.sql.Timestamp sysDateTime){
        SMSInfo smsInfo = new SMSInfo();
        smsInfo.PackageID = inboxPack.PackageID;
        smsInfo.StoredTime = inboxPack.StoredTime;
        smsInfo.StoredDate = inboxPack.StoredDate;
        
        smsInfo.CustomerID = inboxPack.CustomerID;
        smsInfo.CustomerMobile = inboxPack.CustomerMobile;
        smsInfo.BoxNo   = inboxPack.BoxNo;
        smsInfo.OpenBoxKey = inboxPack.BlankBoxKey;
        smsInfo.PackageStatus = inboxPack.PackageStatus;
        
        smsInfo.TerminalNo = terminal.TerminalNo;
        smsInfo.TerminalName = terminal.TerminalName;
        smsInfo.MBDeviceNo = terminal.MBDeviceNo;
        smsInfo.Location = terminal.Location;
        smsInfo.DepartmentID = terminal.DepartmentID;
        smsInfo.OfBureau = terminal.OfBureau;
        
        smsInfo.TradeWaterNo = inboxPack.TradeWaterNo;
        smsInfo.StaOrderID = inboxPack.StaOrderID;
        smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
        smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
        smsInfo.sysDateTime = sysDateTime;
        
        
        try {
            int expiredDays = 7;
            if(postman != null){
                smsInfo.PostmanID = postman.PostmanID;
                smsInfo.PostmanName = postman.PostmanName;
                smsInfo.PostmanMobile = postman.Mobile;
                smsInfo.PostmanEmail  = postman.Email;
                smsInfo.CompanyID = postman.CompanyID;
                
                PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                PMCompany company = new PMCompany();
                company.CompanyID = postman.CompanyID;
                company = companyDAO.find(company);
                smsInfo.CompanyName   = company.CompanyName;
                
                if(StringUtils.isEmpty(smsInfo.OfLogisticsID)){
                    smsInfo.OfLogisticsID = company.LogisticsID;
                }
                //物流公司
                PMLogisticsDAO logisticDAO = daoFactory.getPMLogisticsDAO();
                PMLogistics logistic = new PMLogistics();
                logistic.LogisticsID = company.LogisticsID;
                logistic = logisticDAO.find(logistic);
                smsInfo.LogisticsName = logistic.LogisticsName;
                
                expiredDays = expiredDays(postman.CompanyID);
            }else{
                expiredDays = expiredDays();
            }
            if(smsInfo.ExpiredTime== null || smsInfo.ExpiredTime.before(smsInfo.StoredTime)){
                smsInfo.ExpiredTime = DateUtils.addTimeStamp(smsInfo.StoredTime, expiredDays);
            }
        } catch (EduException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return smsInfo;
    }
    /**
     * 生成发送信息-取件
     * @param historyPack
     * @param terminal
     * @param postman
     * @param sysDateTime
     * @return
     */
    public SMSInfo generateSendInfo(PTDeliverHistory historyPack, TBTerminal terminal, PMPostman postman, java.sql.Timestamp sysDateTime){
        SMSInfo smsInfo = new SMSInfo();
        smsInfo.PackageID = historyPack.PackageID;
        smsInfo.StoredTime = historyPack.StoredTime;
        smsInfo.StoredDate = historyPack.StoredDate;
        
        smsInfo.CustomerID = historyPack.CustomerID;
        smsInfo.CustomerMobile = historyPack.CustomerMobile;
        smsInfo.BoxNo = historyPack.BoxNo;
        smsInfo.PackageStatus = historyPack.PackageStatus;
        
        smsInfo.TerminalNo = terminal.TerminalNo;
        smsInfo.TerminalName = terminal.TerminalName;
        smsInfo.MBDeviceNo = terminal.MBDeviceNo;
        smsInfo.Location = terminal.Location;
        smsInfo.DepartmentID = terminal.DepartmentID;
        smsInfo.OfBureau = terminal.OfBureau;
        
        smsInfo.TradeWaterNo = historyPack.TradeWaterNo;
        smsInfo.StaOrderID = historyPack.StaOrderID;
        smsInfo.OfLogisticsID = historyPack.OfLogisticsID;
        smsInfo.OfLogisticsName = historyPack.OfLogisticsName;
        smsInfo.TakedTime = sysDateTime;
        smsInfo.sysDateTime = sysDateTime;
        
        

        try {
            int expiredDays = 7;
            if(postman != null){
                smsInfo.PostmanID = postman.PostmanID;
                smsInfo.PostmanName = postman.PostmanName;
                smsInfo.PostmanMobile = postman.Mobile;
                smsInfo.PostmanEmail  = postman.Email;
                smsInfo.CompanyID = postman.CompanyID;
                
                PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                PMCompany company = new PMCompany();
                company.CompanyID = postman.CompanyID;
                company = companyDAO.find(company);
                smsInfo.CompanyName   = company.CompanyName;
                
                if(StringUtils.isEmpty(smsInfo.OfLogisticsID)){
                    smsInfo.OfLogisticsID = company.LogisticsID;
                }
                //物流公司
                PMLogisticsDAO logisticDAO = daoFactory.getPMLogisticsDAO();
                PMLogistics logistic = new PMLogistics();
                logistic.LogisticsID = company.LogisticsID;
                logistic = logisticDAO.find(logistic);
                smsInfo.LogisticsName = logistic.LogisticsName;
                
                expiredDays = expiredDays(postman.CompanyID);
            }else{
                expiredDays = expiredDays();
            }

            if(smsInfo.ExpiredTime== null || smsInfo.ExpiredTime.before(smsInfo.StoredTime)){
                smsInfo.ExpiredTime = DateUtils.addTimeStamp(smsInfo.StoredTime, expiredDays);
            }
        } catch (EduException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return smsInfo;
    }
    /**
     * 发送投递通知-短信
     * @param smsInfo
     */
    public void sendDeliverySMS(SMSInfo smsInfo) {
        try{
            MBPwdShortMsgDAO shortMsgDAO = daoFactory.getMBPwdShortMsgDAO();
            if(smsInfo.WaterID == 0){
                MBPwdShortMsg shortMsg = new MBPwdShortMsg();
                SequenceGenerator seqGen = SequenceGenerator.getInstance();

                shortMsg.WaterID = seqGen.getNextKey(SequenceGenerator.SEQ_WATERID);
                shortMsg.TerminalNo = smsInfo.TerminalNo;
                shortMsg.PackageID = smsInfo.PackageID;
                shortMsg.StoredTime = smsInfo.StoredTime;
                shortMsg.StoredDate = smsInfo.StoredDate;
                shortMsg.OpenBoxKey = smsInfo.OpenBoxKey;
                shortMsg.CustomerMobile = smsInfo.CustomerMobile;
                shortMsg.LastModifyTime = smsInfo.sysDateTime;
                shortMsg.ReSendNum = 0;
                shortMsg.PackageStatus = smsInfo.PackageStatus;
                shortMsg.Remark = smsInfo.TradeWaterNo;
                //shortMsg.SenderMobile = inboxPack.Remark;//发件人手机
                
                int hourSendMsg = NumberUtils.parseInt(ControlParam.getInstance().getSendPwdSMSFirstTime());
                int hour = DateUtils.getHour(smsInfo.sysDateTime);
                if(hour>=hourSendMsg){
                    //早上7点以后即时发送
                    shortMsg.SendStatus = "1"; // 0:未发送 1:发送进行中 2:发送成功 4:发送失败
                    shortMsgDAO.insert(shortMsg);
                }else{
                    //早上7点以前不发送
                    shortMsg.SendStatus = "0"; // 0:未发送 1:发送进行中 2:发送成功 4:发送失败
                    shortMsgDAO.insert(shortMsg);
                    return;
                }
                
                smsInfo.WaterID = shortMsg.WaterID;
            } else{
                JDBCFieldArray setCols = new JDBCFieldArray();
                JDBCFieldArray whereCols = new JDBCFieldArray();
                
                setCols.addSQL(" ReSendNum = ReSendNum+1");
                setCols.add("LastModifyTime", smsInfo.sysDateTime);
                
                whereCols.add("WaterID", smsInfo.WaterID);
                
                shortMsgDAO.update(setCols, whereCols);   
            }
            
            
            
            ControlParam ctrlParam = ControlParam.getInstance();
            
            if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg())) {
                //发送短信给取件人
                if("1".equals(ctrlParam.getSendDeliverSMS())){
                    SMSInfo smsInfoToCustomer = new SMSInfo(smsInfo);
                    smsInfoToCustomer.MsgType = SMSInfo.MSG_TYPE_DELIVERY;
                    smsInfoToCustomer.WaterID = smsInfo.WaterID;
                    SMSManager.getInstance().sentDeliverySMS(smsInfoToCustomer);       
                }
                //发送提醒通知给投递员，1-发送投递员,3-发送给投递员&投递公司
                if("1".equals(ctrlParam.getSendDeliverSMS2Post()) || "3".equals(ctrlParam.getSendDeliverSMS2Post())){
                    if(StringUtils.isNotEmpty(smsInfo.PostmanMobile)){
                        SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
                        SMSManager.getInstance().sentDeliverySMSToPostman(smsInfoToPostman);
                    } 
                }
                //发送提醒通知给投递公司 2-发送给投递公司,3-发送给投递员&投递公司
                if("2".equals(ctrlParam.getSendDeliverSMS2Post()) || "3".equals(ctrlParam.getSendDeliverSMS2Post())){
                    PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                    PMCompany company = new PMCompany();
                    company.CompanyID = smsInfo.CompanyID;
                    company = companyDAO.find(company);
                    if(StringUtils.isNotEmpty(company.ContactTel)){
                        SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
                        smsInfoToCompany.CompanyMobile = company.ContactTel;
                        SMSManager.getInstance().sentDeliverySMSToCompany(smsInfoToCompany);
                    }
                }     
            }
            
            
        }catch(EduException e){
            log.error(""+e.getMessage());
        }
    }
    /**
     * 发送投递通知-邮件
     * @param smsInfo
     */
    public void sendDeliveryEmail(SMSInfo smsInfo) {
        ControlParam ctrlParam = ControlParam.getInstance();
        try{
            if("1".equals(ctrlParam.getSendDeliverEmail())){
                SMSInfo smsInfoToCustomer = new SMSInfo(smsInfo);
                smsInfoToCustomer.Email = smsInfo.CustomerMobile;   //柜端取件人手机号作为邮箱
                EmailSenderManager.getInstance().sendDeliverEmail(smsInfoToCustomer,"ToCM");
            }
            //发送提醒通知给投递员，1-发送投递员,3-发送给投递员&投递公司
            if("1".equals(ctrlParam.getSendDeliverEmail2Post()) || "3".equals(ctrlParam.getSendDeliverEmail2Post())){
                if(StringUtils.isNotEmpty(smsInfo.PostmanMobile)){
                    SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
                    smsInfoToPostman.Email = smsInfo.PostmanEmail;
                    EmailSenderManager.getInstance().sendDeliverEmail(smsInfoToPostman,"ToPM");
                } 
            }
            //发送提醒通知给投递公司 2-发送给投递公司,3-发送给投递员&投递公司
            if("2".equals(ctrlParam.getSendDeliverEmail2Post()) || "3".equals(ctrlParam.getSendDeliverEmail2Post())){
                PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                PMCompany company = new PMCompany();
                company.CompanyID = smsInfo.CompanyID;
                company = companyDAO.find(company);
                if(StringUtils.isNotEmpty(company.Address)){////公司邮箱地址
                    SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
                    smsInfoToCompany.CompanyMobile = company.Address;
                    EmailSenderManager.getInstance().sendDeliverEmail(smsInfoToCompany,"ToCompany");
                }
            } 
        }catch(EduException e){
            log.error(""+e.getMessage());
        }
    }
    
    /**
     * 发送取件通知-短信
     * @param smsInfo
     */
    public void sendPickupSMS(SMSInfo smsInfo) {
        try{
            ControlParam ctrlParam = ControlParam.getInstance();
            
            if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg())) {
                //发送短信给取件人
                if("1".equals(ctrlParam.getSendPickupSMS())){
                    SMSInfo smsInfoToCustomer = new SMSInfo(smsInfo);
                    smsInfoToCustomer.MsgType = SMSInfo.MSG_TYPE_TAKEDOUT;
                    SMSManager.getInstance().sentPickupSMS(smsInfoToCustomer);       
                }
                //发送提醒通知给投递员，1-发送投递员,3-发送给投递员&投递公司
                if("1".equals(ctrlParam.getSendPickupSMS2Post()) || "3".equals(ctrlParam.getSendPickupSMS2Post())){
                    if(StringUtils.isNotEmpty(smsInfo.PostmanMobile)){
                        SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
                        SMSManager.getInstance().sentPickupSMSToPostman(smsInfoToPostman);
                    } 
                }
                //发送提醒通知给投递公司 2-发送给投递公司,3-发送给投递员&投递公司
                if("2".equals(ctrlParam.getSendPickupSMS2Post()) || "3".equals(ctrlParam.getSendPickupSMS2Post())){
                    PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                    PMCompany company = new PMCompany();
                    company.CompanyID = smsInfo.CompanyID;
                    company = companyDAO.find(company);
                    if(StringUtils.isNotEmpty(company.ContactTel)){
                        SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
                        smsInfoToCompany.CompanyMobile = company.ContactTel;
                        SMSManager.getInstance().sentPickupSMSToCompany(smsInfoToCompany);
                    }
                }     
            }
            
            
        }catch(EduException e){
            log.error(""+e.getMessage());
        }
    }
    /**
     * 发送取件通知-邮件
     * @param smsInfo
     */
    public void sendPickupEmail(SMSInfo smsInfo) {
        ControlParam ctrlParam = ControlParam.getInstance();
        try{
            if("1".equals(ctrlParam.getSendPickupEmail())){
                SMSInfo smsInfoToCustomer = new SMSInfo(smsInfo);
                smsInfoToCustomer.Email = smsInfo.CustomerMobile;   //柜端取件人手机号作为邮箱
                EmailSenderManager.getInstance().sendPickupEmail(smsInfoToCustomer,"ToCM");
            }
            //发送提醒通知给投递员，1-发送投递员,3-发送给投递员&投递公司
            if("1".equals(ctrlParam.getSendPickupEmail2Post()) || "3".equals(ctrlParam.getSendPickupEmail2Post())){
                if(StringUtils.isNotEmpty(smsInfo.PostmanMobile)){
                    SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
                    smsInfoToPostman.Email = smsInfo.PostmanEmail;
                    EmailSenderManager.getInstance().sendPickupEmail(smsInfoToPostman,"ToPM");
                } 
            }
            //发送提醒通知给投递公司 2-发送给投递公司,3-发送给投递员&投递公司
            if("2".equals(ctrlParam.getSendPickupEmail2Post()) || "3".equals(ctrlParam.getSendPickupEmail2Post())){
                PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                PMCompany company = new PMCompany();
                company.CompanyID = smsInfo.CompanyID;
                company = companyDAO.find(company);
                if(StringUtils.isNotEmpty(company.Address)){////公司邮箱地址
                    SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
                    smsInfoToCompany.CompanyMobile = company.Address;
                    EmailSenderManager.getInstance().sendPickupEmail(smsInfoToCompany,"ToCompany");
                }
            } 
        }catch(EduException e){
            log.error(""+e.getMessage());
        }
    }
    
    /**
     * 发送逾期取回通知-短信
     * @param smsInfo
     */
    public void sendExpiredSMS(SMSInfo smsInfo) {
        try{
            ControlParam ctrlParam = ControlParam.getInstance();
            
            if(StringUtils.isNotEmpty(ApplicationConfig.getInstance().getSendShortMsg())) {
                //发送短信给取件人
                if("1".equals(ctrlParam.getSendExpiredSMS())){
                    SMSInfo smsInfoToCustomer = new SMSInfo(smsInfo);
                    smsInfoToCustomer.MsgType = SMSInfo.MSG_TYPE_EXPIRED;
                    SMSManager.getInstance().sentExpiredSMS(smsInfoToCustomer);       
                }
                //发送提醒通知给投递员，1-发送投递员,3-发送给投递员&投递公司
                if("1".equals(ctrlParam.getSendExpiredSMS2Post()) || "3".equals(ctrlParam.getSendExpiredSMS2Post())){
                    if(StringUtils.isNotEmpty(smsInfo.PostmanMobile)){
                        SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
                        SMSManager.getInstance().sentExpiredSMSToPostman(smsInfoToPostman);
                    } 
                }
                //发送提醒通知给投递公司 2-发送给投递公司,3-发送给投递员&投递公司
                if("2".equals(ctrlParam.getSendExpiredSMS2Post()) || "3".equals(ctrlParam.getSendExpiredSMS2Post())){
                    PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                    PMCompany company = new PMCompany();
                    company.CompanyID = smsInfo.CompanyID;
                    company = companyDAO.find(company);
                    if(StringUtils.isNotEmpty(company.ContactTel)){
                        SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
                        smsInfoToCompany.CompanyMobile = company.ContactTel;
                        SMSManager.getInstance().sentExpiredSMSToCompany(smsInfoToCompany);
                    }
                }     
            }
            
            
        }catch(EduException e){
            log.error(""+e.getMessage());
        }
    }
    /**
     * 发送逾期取回通知-邮件
     * @param smsInfo
     */
    public void sendExpiredEmail(SMSInfo smsInfo) {
        ControlParam ctrlParam = ControlParam.getInstance();
        try{
            if("1".equals(ctrlParam.getSendExpiredEmail())){
                SMSInfo smsInfoToCustomer = new SMSInfo(smsInfo);
                smsInfoToCustomer.Email = smsInfo.CustomerMobile;   //柜端取件人手机号作为邮箱
                //EmailSenderManager.getInstance().sendExpiredEmail(smsInfoToCustomer,"ToCM");
            }
            //发送提醒通知给投递员，1-发送投递员,3-发送给投递员&投递公司
            if("1".equals(ctrlParam.getSendExpiredEmail2Post()) || "3".equals(ctrlParam.getSendExpiredEmail2Post())){
                if(StringUtils.isNotEmpty(smsInfo.PostmanMobile)){
                    SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
                    smsInfoToPostman.Email = smsInfo.PostmanEmail;
                    //EmailSenderManager.getInstance().sendExpiredEmail(smsInfoToPostman,"ToPM");
                } 
            }
            //发送提醒通知给投递公司 2-发送给投递公司,3-发送给投递员&投递公司
            if("2".equals(ctrlParam.getSendExpiredEmail2Post()) || "3".equals(ctrlParam.getSendExpiredEmail2Post())){
                PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
                PMCompany company = new PMCompany();
                company.CompanyID = smsInfo.CompanyID;
                company = companyDAO.find(company);
                if(StringUtils.isNotEmpty(company.Address)){////公司邮箱地址
                    SMSInfo smsInfoToCompany = new SMSInfo(smsInfo);
                    smsInfoToCompany.CompanyMobile = company.Address;
                    //EmailSenderManager.getInstance().sendExpiredEmail(smsInfoToCompany,"ToCompany");
                }
            } 
        }catch(EduException e){
            log.error(""+e.getMessage());
        }
    }
}
