package com.dcdzsoft.dao.common;

import java.text.SimpleDateFormat;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.dto.function.OPOperOnline;
import com.dcdzsoft.dto.function.OPOperatorLog;
import com.dcdzsoft.dto.function.PMPostman;
import com.dcdzsoft.dto.function.PTDeliverHistory;
import com.dcdzsoft.dto.function.PTInBoxPackage;
import com.dcdzsoft.dto.function.TBTerminal;
import com.dcdzsoft.sda.db.PreparedWhereExpression;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.RandUtils;

public interface CommonDAO {
    /**
     * 判断操作员是否在线
     * @param OperID String
     * @return OPOperOnline
     * @throws EduException
     */
    public OPOperOnline isOnline(String OperID) throws EduException;

    /**
     * 记录操作员日志
     * @param log OPOperatorLog
     * @return long
     * @throws EduException
     */
    public long addOperatorLog(OPOperatorLog log) throws EduException;


    /**
     * 修改操作员在线信息
     * @param operOnline OPOperOnline
     * @return boolean
     * @throws EduException
     */
    public boolean modifyOperOnline(OPOperOnline operOnline) throws EduException;

    /**
     * 生成管理员编号
     * @return String
     * @throws EduException
     */
    public String getInnerUserID() throws EduException;

    /**
     * 操作员菜单信息查询
     * @param OperID String
     * @param ModuleID String
     * @return RowSet
     * @throws EduException
     */
    public RowSet operMenuQry(String OperID, String ModuleID) throws EduException;

    /**
      * 检查投递员的柜体权限
      * @param postman PMPostman
      * @param terminalno String
      * @throws EduException
      */
     public void checkManTerminalRight(PMPostman postman,String terminalno) throws EduException;
     
     /**
      * 警报消息入库
      * @param TerminalNo
      * @param AlertType
      * @param AlertLevel
      * @param BoxNo
      * @param Remark
      * @throws EduException
      */
     public void insertAlert(String TerminalNo,String AlertType,String AlertLevel,String BoxNo,String Remark) throws EduException;
     
     /**
      * 判断是否为邮政公司的
      * @param CompanyID
      * @return
      */
     public boolean isYouzhengCompany(String CompanyID);
     
     /**
      * 是否需要发送到国通
      * @param PostmanID
      * @param InputMobileFlag
      * @return
      * @throws EduExcption
      */
     //public boolean isNeedSendToGuoTong(String CompanyID,String InputMobileFlag) throws EduException;
     /**
      * 订单状态是否同步到合作方系统
      * @param PostmanID
      * @param InputMobileFlag
      * @return
      * @throws EduExcption
      */
     public boolean isSendItemEventToPartner(String CompanyID,String InputMobileFlag) throws EduException;
     
     /**
      * 柜体是否需要注册到合作方系统（国通、菜鸟等）
      * @return
      * @throws EduExcption
      */
     public boolean isRegisterToPartner() throws EduException;
	 /**
      * 手机号检查
      * @param input
      * @return
      */
     public boolean isPhoneNumber(String input);
     /**
      * 生成交易流水:yyyyMMddHHmmss+5位随机数
      * @param sysDateTime
      * @return
      */
     public String generateTradeWaterNo(java.sql.Timestamp sysDateTime) throws EduException;
     /**
      * 生成交易时间
      * @param sysDateTime
      * @return
      */
     public String generateTradeDate(java.sql.Timestamp sysDateTime);
     /**
      * 生成取件密码
      * @return
      */
     public String generatePickupPwd();
     /**
      * 生成取件密码
      * @return
      */
     public String generatePickupPwd(String inOpenBoxKey, String inTerminalNo) throws EduException;
     
     /**
      * 生成取件密码
      * @return
      */
     public String generatePickupPwd(String inTerminalNo, java.sql.Date sysDate) throws EduException;
     
     /**
      * 指定表中取某列的值（过滤重复值）
      * @param tableName
      * @param fName
      * @param whereSQL
      * @return
      * @throws EduException
      */
     public java.util.Set<String> selectFieldByGroup(String tableName, String fName, PreparedWhereExpression whereSQL)throws EduException;
     
     /**
      * 取在箱表中投递公司的值（过滤重复值）
      * @return
      * @throws EduException
      */
     public java.util.Set<String> selectCompanyFromInBoxPackage()throws EduException;
     
     
     /**
      * 取逾期天数
      * @param companyID
      * @return
      * @throws EduException
      */
     public int expiredDays(String companyID) throws EduException;
     
     /**
      * 取逾期天数
      * @return
      * @throws EduException
      */
     public int expiredDays() throws EduException;
     /**
      * 生成发送信息-投递
      * @param inboxPack
      * @param terminal
      * @param postman
      * @param sysDateTime
      * @return
      */
     public SMSInfo generateSendInfo(PTInBoxPackage inboxPack, TBTerminal terminal, PMPostman postman, java.sql.Timestamp sysDateTime);
     /**
      * 生成发送信息-取件
      * @param historyPack
      * @param terminal
      * @param postman
      * @param sysDateTime
      * @return
      */
     public SMSInfo generateSendInfo(PTDeliverHistory historyPack, TBTerminal terminal, PMPostman postman, java.sql.Timestamp sysDateTime);
     
     /**
      * 发送投递通知-短信
      * @param smsInfo
      */
     public void sendDeliverySMS(SMSInfo smsInfo) ;
     
     /**
      * 发送投递通知-邮件
      * @param smsInfo
      */
     public void sendDeliveryEmail(SMSInfo smsInfo);
     
     /**
      * 发送取件通知-短信
      * @param smsInfo
      */
     public void sendPickupSMS(SMSInfo smsInfo);
     
     /**
      * 发送取件通知-邮件
      * @param smsInfo
      */
     public void sendPickupEmail(SMSInfo smsInfo);
     
     /**
      * 发送逾期取回通知-短信
      * @param smsInfo
      */
     public void sendExpiredSMS(SMSInfo smsInfo) ;
     
     /**
      * 发送逾期取回通知-邮件
      * @param smsInfo
      */
     public void sendExpiredEmail(SMSInfo smsInfo);
     
}