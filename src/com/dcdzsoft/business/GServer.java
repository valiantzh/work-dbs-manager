package com.dcdzsoft.business;

import java.util.*;

import javax.sql.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.dcdzsoft.*;
import com.dcdzsoft.businessproxy.MonitorProxy;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.guotong.GuotongManager;

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
public class GServer {
    public static final String LOCALE_EN = "en_US";
    public static final String LOCALE_ZH = "zh_CN";
    private static final String SoftVersion = "v1.2.4";
    
    private static Log log = org.apache.commons.logging.LogFactory.getLog(GServer.class);
    
    private static boolean isRunning = true;

    // 记录日志标志
    private HashMap<String, String> functionMap = new HashMap<String, String>();

    // SignKey
    private HashMap<String, String> signkeyMap = new HashMap<String, String>();

    private AutoLockerOrderThread autoLockerThread;

    private static ApiRunnerThread runnerThread;

    protected GServer() {
        autoLockerThread = new AutoLockerOrderThread();
        autoLockerThread.start();

        runnerThread = new ApiRunnerThread();
        runnerThread.start();
    }

    private static class SingletonHolder {
        private static final GServer instance = new GServer();
    }

    /**
     * 静态工厂方法，返还此类的惟一实例
     * 
     * @return a GServer instance
     */
    public static GServer getInstance() {
        return SingletonHolder.instance;
    }

    
    protected synchronized static boolean isRunning() {
        return isRunning;
    }

    public  void destroy(){
        GServer.println("---- GServer.destroy start----"+isRunning);
        synchronized (this) {
            isRunning = false;
            if(autoLockerThread !=null){
                autoLockerThread.interrupt();
                autoLockerThread = null;
            }
            
            if(runnerThread != null){
                runnerThread.interrupt();
                runnerThread = null;
            }
        }
        
        GServer.println("---- GServer.destroy end ----"+isRunning);
        
    }
    
    // 初始化内存数据
    public void initMemoryData() throws EduException {
        // 加载日志标志
        loadFunctionToMemory();

        // 加载控制参数
        loadCtrlParamToMemory();
    }

    // 加载日志标志
    public void loadFunctionToMemory() throws EduException {
        InParamOPFunctionQry inParam = new InParamOPFunctionQry();

        RowSet rset = MonitorProxy.doBusiness(inParam);
        while (RowSetUtils.rowsetNext(rset)) {
            functionMap.put(RowSetUtils.getStringValue(rset, "FunctionID"),RowSetUtils.getStringValue(rset, "LogFlag"));
        }
    }

    public void loadFunctionToMemory(RowSet rset) throws EduException {
        while (RowSetUtils.rowsetNext(rset)) {
            functionMap.put(RowSetUtils.getStringValue(rset, "FunctionID"),RowSetUtils.getStringValue(rset, "LogFlag"));
        }
    }

    // 加载控制参数
    public void loadCtrlParamToMemory() throws EduException {
        InParamPAControlParamQry inParam = new InParamPAControlParamQry();

        RowSet rset = MonitorProxy.doBusiness(inParam);

        ControlParam ctrlParam = ControlParam.getInstance();
        while (RowSetUtils.rowsetNext(rset)) {
            try {
                BeanUtils.setProperty(ctrlParam, RowSetUtils.getStringValue(rset, "KeyString"),RowSetUtils.getStringValue(rset, "DefaultValue"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadCtrlParamToMemory(RowSet rset) throws EduException {
        ControlParam ctrlParam = ControlParam.getInstance();
        while (RowSetUtils.rowsetNext(rset)) {
            try {
                BeanUtils.setProperty(ctrlParam, RowSetUtils.getStringValue(rset, "KeyString"),RowSetUtils.getStringValue(rset, "DefaultValue"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, String> getFunctionMap() {
        return this.functionMap;
    }

    public String getLogFlag(String FunctionID) {
        String logFlag = "";

        Object o = functionMap.get(FunctionID);
        if (o != null)
            logFlag = o.toString();

        return logFlag;
    }

    /**
     * 签到信息相关
     * 
     * @param terminalNo
     * @param signkey
     */
    public void addSignKey(String terminalNo, String signkey) {
        signkeyMap.put(terminalNo, signkey);
    }

    public void removieSignKey(String terminalNo) {
        signkeyMap.remove(terminalNo);
    }

    public String getSignKey(String terminalNo) {
        return signkeyMap.get(terminalNo);
    }

    public static String getSoftversion() {
        return SoftVersion;
    }

    /**
     * 获取面向终端业务的代理类
     * 
     * @return
     * @throws Exception
     */
    public static Class<?> getLockerBusinussProxyClass() throws Exception {
        Class<?> proxyClass = null;
        String proxyClassName = "";
        String ingerfacePackage = ApplicationConfig.getInstance().getInterfacePackage();

        final String PROXY_PACKAGE_PREFIX_OLD = "com.dcdzsoft.businessproxy.";// 原代理所在包
        final String PROXY_PACKAGE_PREFIX = "com.dcdzsoft.client.locker.businessproxy.";// 新的代理所在包
        try {
            proxyClassName = PROXY_PACKAGE_PREFIX + ingerfacePackage;
            proxyClass = Class.forName(proxyClassName);
            GServer.println("======== NewLockerBusinussProxyClass=" + proxyClassName + " ======== ");
            log.info("======== NewLockerBusinussProxyClass=" + proxyClassName + " ======== ");
        } catch (Exception e) {
            //
            GServer.println("******** NewLockerBusinussProxyClass=" + proxyClassName + " error:" + e.getMessage());
            log.error("******** NewLockerBusinussProxyClass=" + proxyClassName + " error:" + e.getMessage());
            // 支持原代理所在包
            proxyClassName = PROXY_PACKAGE_PREFIX_OLD + ingerfacePackage;
            proxyClass = Class.forName(proxyClassName);
            GServer.println("======== OldLockerBusinussProxyClass=" + proxyClassName + " ======== ");
            log.info("======== OldLockerBusinussProxyClass=" + proxyClassName + " ======== ");
        }
        return proxyClass;
    }

    /**
     * 发送催领短消息线程
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
    private class AutoLockerOrderThread extends Thread {
        public void run() {
            try {
                Thread.sleep(1000*30);
                
                GServer.println(" ----------- AutoLockerOrderThread start -----------"+GServer.getInstance().isRunning());
                
                while (GServer.getInstance().isRunning()) {
                    Date nowDate = new Date();
                    int hour = DateUtils.getHour(nowDate);
                    // 凌晨12点启动
                    if (hour == 0 && "1".equalsIgnoreCase(ControlParam.getInstance().autoLockOrder)) {

                        GServer.println("auto locker order begin --------------");

                        InParamPTAutoLockOrder inParam = new InParamPTAutoLockOrder();

                        try {
                            MonitorProxy.doBusiness(inParam);
                        } catch (Exception e) {
                            GServer.println("[AutoLockOrderThread business error] = " + e.getMessage());
                        }

                        GServer.println("auto locker order end --------------");
                    } else if (hour == 2) {
                        InParamSMSysCleanAuto inParam = new InParamSMSysCleanAuto();
                        try {
                            MonitorProxy.doBusiness(inParam);
                        } catch (Exception e) {
                            GServer.println("[AutoLockOrderThread business error] = " + e.getMessage());
                        }
                    }

                    try {
                        Thread.sleep(1000 * 60 * 30); // 休眠半小时
                    } catch (InterruptedException ex) {

                    }
                }
                
                GServer.println(" ----------- AutoLockerOrderThread end -----------"+GServer.getInstance().isRunning());
            } catch (Exception e) {
                GServer.println("[auto locke Thread error] = " + e.getMessage());
            }
        }
    }

    /**
     * 接口监控线程
     * <p>
     * Title: 智能自助包裹柜系统
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     * <p>
     * Copyright: Copyright (c) 2016
     * </p>
     *
     * <p>
     * Company: 杭州东城电子有限公司
     * </p>
     *
     * @author zhengxy
     * @version 1.0
     */
    private static class ApiRunnerThread extends Thread {
        private static Set<String> terminalNoList = new HashSet<String>();

        private void loadTerminalNo() {
            try {
                terminalNoList.clear();
                InParamTBTerminalListQry inParam0 = new InParamTBTerminalListQry();
                inParam0.TerminalStatus = "0";
                RowSet rset = MonitorProxy.doBusiness(inParam0);
                while (RowSetUtils.rowsetNext(rset)) {
                    terminalNoList.add(RowSetUtils.getStringValue(rset, "TerminalNo"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                GServer.println("[APIRunnerThread loadTerminalNo] = " + e.getMessage());
            }
        }

        public void run() {

            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e1) {
            }

            if (!ApplicationConfig.getInstance().isUploadToPartner()) {
                return;
            }

            GServer.println(" ----------- ApiRunnerThread start -----------"+GServer.getInstance().isRunning());

            boolean isDel = true;// 是否开启清除发送失败记录功能
            loadTerminalNo();// 加载设备编号
            while (isRunning()) {// 每30分钟循环一次
                try {
                    Date nowDate = new Date();

                    int hour = DateUtils.getHour(nowDate);

                    if (hour == 3 && isDel) {
                        loadTerminalNo();// 加载设备编号
                        try {
                            MonitorProxy.doBusiness(new InParamTBTerminalListQry());
                            Thread.sleep(100); //

                        } catch (Exception e) {
                            e.printStackTrace();
                            GServer.println("[APIRunnerThread error2] = " + e.getMessage());
                        }
                        isDel = false;
                    } else {
                        isDel = true;
                    }

                    // 自动逾期(百隆达)
                    if (hour > 6 && "1".equalsIgnoreCase(ControlParam.getInstance().autoExpiredOrder)) {
                        for (String terminalNo : terminalNoList) {
                            try {
                                InParamPTAutoExpiredOrder in = new InParamPTAutoExpiredOrder();
                                in.TerminalNo = terminalNo;
                                int count = MonitorProxy.doBusiness(in);
                                if (count > 0) {
                                    GServer.println(" AutoExpiredOrder " + terminalNo + ",count=" + count);
                                    Thread.sleep(100); //
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                GServer.println("[APIRunnerThread error3] = " + e.getMessage() + ",terminalNo=" + terminalNo);
                            }
                        }

                    }
                    // 逾期件自动清除密码
                    if (hour > 6 && "1".equalsIgnoreCase(ControlParam.getInstance().autoClearOpenKey)) {
                        GServer.println("======>>>自动清除密码线程启动========");
                        for (String terminalNo : terminalNoList) {
                            try {
                                InParamAutoClearOpenKey in = new InParamAutoClearOpenKey();
                                in.TerminalNo = terminalNo;
                                int count = MonitorProxy.doBusiness(in);
                                if (count > 0) {
                                    GServer.println("AutoClearOpenKey " + terminalNo + ",count=" + count);
                                    Thread.sleep(100); //
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                GServer.println("[APIRunnerThread error AutoClearOpenKey] = " + e.getMessage() + ",terminalNo=" + terminalNo);
                            }
                        }

                    }

                    // 发送失败 重新发送,间隔一小时发送一次
                    if ("1".equalsIgnoreCase(ControlParam.getInstance().reSendDeliveryInfo)) {// 1~1小时;
                                                                                              // 3~2小时
                        GServer.println(" API FeedbackFailReSend start");

                        for (String terminalNo : terminalNoList) {
                            InParamMBFeedbackFailQry inParam1 = new InParamMBFeedbackFailQry();
                            inParam1.TerminalNo = terminalNo;

                            StringBuffer WaterIDList = new StringBuffer();
                            RowSet rset1 = MonitorProxy.doBusiness(inParam1);
                            while (RowSetUtils.rowsetNext(rset1)) {
                                WaterIDList.append(",").append(RowSetUtils.getStringValue(rset1, "TradeWaterNo"));
                            }

                            if (WaterIDList.length() <= 1) {
                                continue;
                            }
                            WaterIDList.deleteCharAt(0);

                            try {
                                InParamMBFeedbackFailReSend inParam2 = new InParamMBFeedbackFailReSend();
                                inParam2.WaterIDList = WaterIDList.toString();

                                int count = MonitorProxy.doBusiness(inParam2);
                                if (count > 0) {
                                    Thread.sleep(20000);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                GServer.println("[APIRunnerThread business error4] = " + e.getMessage());
                            }
                        }

                        GServer.println(" API FeedbackFailReSend end");
                    }

                    // ///////////////////同步柜体在线状态
                    if (ApplicationConfig.getInstance().isRegisterToPartner()
                            && ApplicationConfig.getInstance().isNewGuotongApi()) {// 向国通系统发送心跳检测请求
                        // GServer.println("sentOnlineTerminals
                        // --------------");

                        final int MAX_NUM = 80;
                        int iLocker = 0;
                        StringBuffer terminalNoBuff = new StringBuffer(1024);
                        InParamMBDeviceSignQry inParam0 = new InParamMBDeviceSignQry();
                        inParam0.OnlineStatus = "1";// 在线
                        RowSet rset = MonitorProxy.doBusiness(inParam0);
                        while (RowSetUtils.rowsetNext(rset)) {
                            String no = RowSetUtils.getStringValue(rset, "MBDeviceNo");
                            if (StringUtils.isNotEmpty(no)) {

                                terminalNoBuff.append(no + ",");
                                iLocker++;
                            }
                            if (iLocker >= MAX_NUM) {
                                String terminalNoList = terminalNoBuff.substring(0, terminalNoBuff.length() - 1);
                                // GServer.println("sentOnlineTerminals"+terminalNoList);
                                GuotongManager.getInstance().sentOnlineTerminals(terminalNoList);
                                iLocker = 0;
                                terminalNoBuff = new StringBuffer(1024);
                            }
                        }
                        if (terminalNoBuff.length() > 1) {
                            String terminalNoList = terminalNoBuff.substring(0, terminalNoBuff.length() - 1);
                            // GServer.println("sentOnlineTerminals"+terminalNoList);
                            GuotongManager.getInstance().sentOnlineTerminals(terminalNoList);
                        }

                    }
                    try {
                        Thread.sleep(1000 * 60 * 30); // 休眠半小时
                    } catch (InterruptedException ex) {
                    }
                } catch (Exception e) {
                    GServer.println("[APIRunnerThread error] = " + e.getMessage());
                }
            }
            
            GServer.println(" ----------- ApiRunnerThread end -----------"+GServer.getInstance().isRunning());

        }
    }
    public  static void println(String msg){
        System.out.println(DateUtils.nowDate() + " "+ DateUtils.nowTime() + " " + msg);
    }
}
