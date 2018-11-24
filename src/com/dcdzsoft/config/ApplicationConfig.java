package com.dcdzsoft.config;

import com.dcdzsoft.sda.db.DataSourceUtils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 *
 * <p>
 * Company: 杭州东城电子有限公司
 * </p>
 *
 * @author wangzl
 * @version 1.0
 */
public class ApplicationConfig {

    /**
     * database config
     */
    private DataSourceUtils dbCfg = DataSourceUtils.getInstance();

    // 错误消息提示语言
    private String locale = "";

    // 系统运行模式
    private int sysRunModel = 1; // 1:转发 2:运营 3:转发运营

    // 接口包名
    private String interfacePackage = ""; //

    // 业务处理线程的数量
    private int workerCount = 100; // 业务处理线程的数量

    // 记录自己平台的消息日志
    private boolean logRawMsg = false;

    // 记录运营商消息的日志
    private boolean logMbMsg = false;

    // 测试需要发送短信否
    private String sendShortMsg = ""; // MsgProxyDcdzsoft,MsgProxyFangzhengkuandai,MsgProxyShanghaiyz
    private String gatewayUser = "";
    private String gatewayPwd = "";
    private String smsServerIp = "";
    private String smsServerPort = "";
    private String smsCharset = "";// 短信编码
    private String smsMobilePrefix = "";// 手机号前缀，用户发送国家短信
    private String smsSender = "";// 短信发送者

    // 运营商服务器IP
    private String mbHost = "";
    // 运营商服务器Port
    private String mbPort = "";
    // 运营商服务器URI
    private String mbUri = "";

    private String ftpHost = "";
    private String ftpPort = "";
    private String ftpUser = "";
    private String ftpPasswd = "";
    private String headerDepartmentID = "";// 运营商根网点编号

    // 合作方服务器(国通、菜鸟)
    private boolean sentToGuotong = false;
    private boolean newGuotongApi = false;
    private boolean uploadToPartner = false;// 订单状态是否上传到合作方系统
    private boolean registerToPartner = false;// 柜体是否需要注册到合作方系统（国通、菜鸟等）
    private String dbsAipsIp = "";
    private String dbsAipsPort = "";

    // 合作方接口
    private String outProxy = "";
    private String apiStringWs = "";
    private String apiSysIdWs = "";
    private String apiKeyWs = "";
    private String apiToken = "";

    /**
     * webapps的真实路径
     */
    private String physicalPath = "";

    private String terminalLogPath = "terminalLog";
    private String fullTerminalLogPath;
    private String fullTerminalLogTmpPath;

    private String picFilePath = "pic";
    private String fullPicFilePath = "";

    /**
     * 私有默认构造函数
     */
    private ApplicationConfig() {

    }

    private static class SingletonHolder {
        private static final ApplicationConfig instance = new ApplicationConfig();
    }

    /**
     * 静态工厂方法，返还此类的惟一实例
     * 
     * @return a ApplicationConfig instance
     */
    public static ApplicationConfig getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 读取配置文件
     * 
     * @param fileName
     *            String
     * @throws ConfigurationException
     * @throws IOException
     */
    public void load(String fileName) throws ConfigurationException, java.io.IOException {
        String globalPrefix = "global.";
        String mbPrefix = "mbconfig.";
        String smsPrefix = "smsconfig.";
        String dbPrefix = "dbconfig.";
        String outerconfig = "outerconfig.";

        XMLConfiguration config = new XMLConfiguration(fileName);
        config.setEncoding("utf-8"); // 设置编码
        config.setDefaultListDelimiter('~');

        // global
        this.setLocale(config.getString(globalPrefix + "locale"));
        this.setSysRunModel(config.getInt(globalPrefix + "sysRunModel"));
        this.setInterfacePackage(config.getString(globalPrefix + "interfacePackage"));
        this.setWorkerCount(config.getInt(globalPrefix + "workerCount"));
        this.setLogRawMsg(config.getBoolean(globalPrefix + "logRawMsg"));
        this.setLogMbMsg(config.getBoolean(globalPrefix + "logMbMsg"));

        this.setSendShortMsg(config.getString(smsPrefix + "sendShortMsg"));
        this.setSmsServerIp(config.getString(smsPrefix + "smsServerIp"));
        this.setSmsServerPort(config.getString(smsPrefix + "smsServerPort"));
        this.setGatewayUser(config.getString(smsPrefix + "gatewayUser"));
        this.setGatewayPwd(config.getString(smsPrefix + "gatewayPwd"));
        try {
            this.setSmsCharset(config.getString(smsPrefix + "smsCharset"));
        } catch (Exception e) {
            this.setSmsCharset("utf-8");
        }
        try {
            this.setSmsMobilePrefix(config.getString(smsPrefix + "smsMobilePrefix"));
        } catch (Exception e) {
            this.setSmsMobilePrefix("");
        }
        try {
            this.setSmsSender(config.getString(smsPrefix + "smsSender"));
        } catch (Exception e) {
            this.setSmsSender("");
        }

        // mbconfig
        this.setMbHost(config.getString(mbPrefix + "mbHost"));
        this.setMbPort(config.getString(mbPrefix + "mbPort"));
        this.setMbUri(config.getString(mbPrefix + "mbUri"));

        this.setFtpHost(config.getString(mbPrefix + "ftpHost"));
        this.setFtpPort(config.getString(mbPrefix + "ftpPort"));
        this.setFtpUser(config.getString(mbPrefix + "ftpUser"));
        this.setFtpPasswd(config.getString(mbPrefix + "ftpPasswd"));

        try {
            this.setHeaderDepartmentID(config.getString(mbPrefix + "headerDepartmentID"));
        } catch (Exception e) {
            this.setHeaderDepartmentID("");
        }

        /*
         * 旧版本 this.setSentToGuotong(config.getBoolean(mbPrefix +
         * "sentToGuotong")); try{
         * this.setNewGuotongApi(config.getBoolean(mbPrefix + "newGuotongApi"));
         * }catch(Exception e){}
         */
        try {
            // 新版本
            this.setUploadToPartner(config.getBoolean(mbPrefix + "uploadToPartner"));
            this.setRegisterToPartner(config.getBoolean(mbPrefix + "registerToPartner"));
        } catch (Exception e) {
            // 兼容旧版本软件
            this.setSentToGuotong(config.getBoolean(mbPrefix + "sentToGuotong"));
            try {
                this.setNewGuotongApi(config.getBoolean(mbPrefix + "newGuotongApi"));
            } catch (Exception e1) {
            }
            this.setUploadToPartner(this.isSentToGutong());
            this.setRegisterToPartner(this.isNewGuotongApi());
        }

        this.setDbsAipsIp(config.getString(mbPrefix + "dbsAipsIp"));
        this.setDbsAipsPort(config.getString(mbPrefix + "dbsAipsPort"));

        // outerconfig
        try {
            this.setOutProxy(config.getString(outerconfig + "outProxy"));
            this.setApiStringWs(config.getString(outerconfig + "apiStringWs"));
            this.setApiKeyWs(config.getString(outerconfig + "apiKeyWs"));
            this.setApiSysIdWs(config.getString(outerconfig + "apiSysIdWs"));
            this.setApiToken(config.getString(outerconfig + "apiToken"));
        } catch (Exception e) {
        }

        // db config
        dbCfg.setDatabaseType(config.getInt(dbPrefix + "databasetype"));
        dbCfg.setDriverClassName(config.getString(dbPrefix + "driverClassName"));
        dbCfg.setUrl(config.getString(dbPrefix + "url"));
        dbCfg.setUsername(config.getString(dbPrefix + "username"));
        dbCfg.setPassword(config.getString(dbPrefix + "password"));
        dbCfg.setMaxActive(config.getInt(dbPrefix + "maxActive"));
        dbCfg.setMaxIdle(config.getInt(dbPrefix + "maxIdle"));
        dbCfg.setMinIdle(config.getInt(dbPrefix + "minIdle"));
        dbCfg.setMaxWait(config.getInt(dbPrefix + "maxWait"));
        dbCfg.setTimeBetweenEvictionRunsMillis(config.getInt(dbPrefix + "timeBetweenEvictionRunsMillis"));
        dbCfg.setValidationQuery(config.getString(dbPrefix + "validationQuery"));

        // 加载错误信息提示文件
        String errorfileName = physicalPath + "WEB-INF/locale/errorMsg_" + this.getLocale();
        ErrorMsgConfig.load(errorfileName);
    }

    public String getPhysicalPath() {
        return physicalPath;
    }

    public String getLocale() {
        return locale;
    }

    public int getSysRunModel() {
        return sysRunModel;
    }

    public void setSysRunModel(int sysRunModel) {
        this.sysRunModel = sysRunModel;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(int workerCount) {
        this.workerCount = workerCount;
    }

    public String getFullTerminalLogPath() {
        return this.getPhysicalPath() + this.terminalLogPath;
    }

    public String getFullTerminalLogTmpPath() {
        return getFullTerminalLogPath() + "/temp";
    }

    public void setPhysicalPath(String physicalPath) {
        this.physicalPath = physicalPath;
    }

    public void setTerminalLogPath(String terminalLogPath) {
        this.terminalLogPath = terminalLogPath;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setInterfacePackage(String value) {
        this.interfacePackage = value;
    }

    public String getInterfacePackage() {
        return interfacePackage;
    }

    public void setMbHost(String value) {
        this.mbHost = value;
    }

    public String getMbHost() {
        return mbHost;
    }

    public void setMbPort(String value) {
        this.mbPort = value;
    }

    public String getMbPort() {
        return mbPort;
    }

    public void setMbUri(String value) {
        this.mbUri = value;
    }

    public String getHeaderDepartmentID() {
        return headerDepartmentID;
    }

    public void setHeaderDepartmentID(String headerDepartmentID) {
        this.headerDepartmentID = headerDepartmentID;
    }

    public boolean isSentToGuotong() {
        return sentToGuotong;
    }

    public void setLogRawMsg(boolean logRawMsg) {
        this.logRawMsg = logRawMsg;
    }

    public void setLogMbMsg(boolean logMbMsg) {
        this.logMbMsg = logMbMsg;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public void setFtpPasswd(String ftpPasswd) {
        this.ftpPasswd = ftpPasswd;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public void setFullTerminalLogPath(String fullTerminalLogPath) {
        this.fullTerminalLogPath = fullTerminalLogPath;
    }

    public void setFullTerminalLogTmpPath(String fullTerminalLogTmpPath) {
        this.fullTerminalLogTmpPath = fullTerminalLogTmpPath;
    }

    public String getMbUri() {
        return mbUri;
    }

    public boolean isLogRawMsg() {
        return logRawMsg;
    }

    public boolean isLogMbMsg() {
        return logMbMsg;
    }

    public boolean isSentToGutong() {
        return sentToGuotong;
    }

    public void setSentToGuotong(boolean sentToGuotong) {
        this.sentToGuotong = sentToGuotong;
    }

    public void setDbsAipsIp(String dbsAipsIp) {
        this.dbsAipsIp = dbsAipsIp;
    }

    public String getDbsAipsIp() {
        return dbsAipsIp;
    }

    public void setDbsAipsPort(String dbsAipsPort) {
        this.dbsAipsPort = dbsAipsPort;
    }

    public String getDbsAipsPort() {
        return dbsAipsPort;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public String getFtpPasswd() {
        return ftpPasswd;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public String getTerminalLogPath() {
        return terminalLogPath;
    }

    public String getSendShortMsg() {
        return sendShortMsg;
    }

    public void setSendShortMsg(String sendShortMsg) {
        this.sendShortMsg = sendShortMsg;
    }

    public String getSmsServerIp() {
        return smsServerIp;
    }

    public void setSmsServerIp(String smsServerIp) {
        this.smsServerIp = smsServerIp;
    }

    public String getSmsServerPort() {
        return smsServerPort;
    }

    public void setSmsServerPort(String smsServerPort) {
        this.smsServerPort = smsServerPort;
    }

    public String getGatewayUser() {
        return gatewayUser;
    }

    public void setGatewayUser(String gatewayUser) {
        this.gatewayUser = gatewayUser;
    }

    public String getGatewayPwd() {
        return gatewayPwd;
    }

    public void setGatewayPwd(String gatewayPwd) {
        this.gatewayPwd = gatewayPwd;
    }

    public String getSmsCharset() {
        return smsCharset;
    }

    public void setSmsCharset(String smsCharset) {
        if(StringUtils.isEmpty(smsCharset)){
            this.smsCharset = "utf-8";
        }else{
            this.smsCharset = smsCharset;
        }
        
    }

    public boolean isNewGuotongApi() {
        return newGuotongApi;
    }

    public void setNewGuotongApi(boolean newGuotongApi) {
        this.newGuotongApi = newGuotongApi;
    }

    public String getFullPicFilePath() {
        this.fullPicFilePath = this.getPhysicalPath() + this.picFilePath;
        return fullPicFilePath;
    }

    public void setFullPicFilePath(String fullPicFilePath) {
        this.fullPicFilePath = fullPicFilePath;
    }

    public String getPicFilePath() {
        return picFilePath;
    }

    public void setPicFilePath(String picFilePath) {
        this.picFilePath = picFilePath;
    }

    public String getOutProxy() {
        return outProxy;
    }

    public void setOutProxy(String outProxy) {
        this.outProxy = outProxy;
    }

    public String getApiStringWs() {
        return apiStringWs;
    }

    public void setApiStringWs(String apiStringWs) {
        this.apiStringWs = apiStringWs;
    }

    public String getApiSysIdWs() {
        return apiSysIdWs;
    }

    public void setApiSysIdWs(String apiSysIdWs) {
        this.apiSysIdWs = apiSysIdWs;
    }

    public String getApiKeyWs() {
        return apiKeyWs;
    }

    public void setApiKeyWs(String apiKeyWs) {
        this.apiKeyWs = apiKeyWs;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public boolean isRegisterToPartner() {
        return registerToPartner;
    }

    public void setRegisterToPartner(boolean registerToPartner) {
        this.registerToPartner = registerToPartner;
    }

    public boolean isUploadToPartner() {
        return uploadToPartner;
    }

    public void setUploadToPartner(boolean uploadToPartner) {
        this.uploadToPartner = uploadToPartner;
    }

    public String getSmsMobilePrefix() {
        return smsMobilePrefix;
    }

    public void setSmsMobilePrefix(String smsMobilePrefix) {
        this.smsMobilePrefix = smsMobilePrefix;
    }

    public String getSmsSender() {
        return smsSender;
    }

    public void setSmsSender(String smsSender) {
        this.smsSender = smsSender;
    }

}
