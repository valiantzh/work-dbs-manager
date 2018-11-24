package com.dcdzsoft.websocket;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;

import javax.websocket.Session;

import org.apache.commons.logging.Log;

import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.packet.PacketUtils;
import com.dcdzsoft.business.GServer;
import com.dcdzsoft.util.StringUtils;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.EduException;

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
public class WebSocketManager {
    private static Log log = org.apache.commons.logging.LogFactory.getLog(WebSocketManager.class);

    private static GServer gServer = GServer.getInstance();

    /**
     * 工作的线程数
     */
    private int workerCount = 100;

    private ThreadPoolExecutor executor;
    /**
     * socket list map
     */
    private ConcurrentHashMap<String, Session> socketListMap;

    private ConcurrentHashMap<String, java.util.Date> lastActiveTimeMap;

    private ConcurrentHashMap<String, WebSocketMsg> pushWebSocketMsgMap;// 用于设备推送消息缓存
    
    private ConcurrentHashMap<String, Session> socketListMapOld;//废弃session的Map
    
    private MonitorConnAliveThread pingThread;
    
    private static boolean isRunning = true;

    /**
     * 私有默认构造函数
     */
    private WebSocketManager() {
        workerCount = ApplicationConfig.getInstance().getWorkerCount();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(workerCount);

        socketListMap = new ConcurrentHashMap<String, Session>(workerCount);
        socketListMapOld = new ConcurrentHashMap<String, Session>(workerCount);

        lastActiveTimeMap = new ConcurrentHashMap<String, java.util.Date>(workerCount);

        pushWebSocketMsgMap = new ConcurrentHashMap<String, WebSocketMsg>(workerCount);

        pingThread = new MonitorConnAliveThread();
        pingThread.start();
    }

    private static class SingletonHolder {
        private static final WebSocketManager instance = new WebSocketManager();
    }

    /**
     * 静态工厂方法，返还此类的惟一实例
     * 
     * @return a WebSocketManager instance
     */
    public static WebSocketManager getInstance() {
        return SingletonHolder.instance;
    }

    protected synchronized static boolean isRunning() {
        return isRunning;
    }
    
    public synchronized void destroy(){
        isRunning = false;
        if(pingThread!=null){
            pingThread.interrupt();
            pingThread = null;
        }
        
        if(executor != null){
            executor.shutdown();
        }
    }
    
    /**
     * SocketSession 管理
     * 
     * @param terminalNo
     * @param session
     */
    public void addSocketSession(String terminalNo, Session session) {
        socketListMap.put(terminalNo, session);
    }

    public Session getSocketSession(String terminalNo) {
        return socketListMap.get(terminalNo);
    }

    public void removeSocketSession(String terminalNo) {
        socketListMap.remove(terminalNo);
    }

    public Set<String> getOnlineTerminalNoList() {
        return socketListMap.keySet();
    }

    /**
     * 废弃session管理
     * 
     * @param TerminalNo
     * @param session
     */
    public void addSocketSessionOld(String terminalNo, Session session) {
        socketListMapOld.put(terminalNo, session);
    }

    public Session getSocketSessionOld(String terminalNo) {
        return socketListMapOld.get(terminalNo);
    }

    public ConcurrentHashMap<String, Session> getSocketListMapOld() {
        return socketListMapOld;
    }

    public void removeSocketSessionOld(String terminalNo) {
        socketListMapOld.remove(terminalNo);
    }

    public void addLastActiveDateTime(String TerminalNo, java.util.Date nowTime) {
        lastActiveTimeMap.put(TerminalNo, nowTime);
    }

    public void removeLastActiveDateTime(String TerminalNo) {
        lastActiveTimeMap.remove(TerminalNo);
    }

    public java.util.Date getLastActiveDateTime(String TerminalNo) {
        return lastActiveTimeMap.get(TerminalNo);
    }

    public Set<String> getLastActiveDateTimeList() {
        return lastActiveTimeMap.keySet();
    }

    public void addPushWebSocketMsgMap(String terminalNo) {
        WebSocketMsg oldMsg = pushWebSocketMsgMap.get(terminalNo);
        if (oldMsg != null) {
            synchronized (oldMsg) {
                oldMsg.notifyAll();
            }
            pushWebSocketMsgMap.remove(terminalNo);
        }
        pushWebSocketMsgMap.put(terminalNo, new WebSocketMsg(terminalNo));
    }

    public WebSocketMsg getPushWebSocketMsgMap(String terminalNo) {
        return pushWebSocketMsgMap.get(terminalNo);
    }

    public void removePushWebSocketMsgMap(String terminalNo) {
        WebSocketMsg oldMsg = pushWebSocketMsgMap.get(terminalNo);
        if (oldMsg != null) {
            synchronized (oldMsg) {
                oldMsg.notifyAll();
            }
            pushWebSocketMsgMap.remove(terminalNo);
        }
    }

    public void removeAllSessionInfo(String terminalNo) {

        removePushWebSocketMsgMap(terminalNo);

        removeLastActiveDateTime(terminalNo);
        gServer.removieSignKey(terminalNo);

        removeSocketSession(terminalNo);
    }

    /**
     * 业务处理
     */
    public void submitTask(String message, String terminalNo) {
        executor.submit(new BusinessWorker(message, terminalNo));
    }

    /**
     * 发送响应消息
     * 
     * @param message
     * @param terminalNo
     */
    public void sendResponseMessage(String message, String terminalNo) throws EduException {
        Session session = getSocketSession(terminalNo);
        if (session != null && session.isOpen()) {
            // 加锁否???
            synchronized (session) {
                try {
                    session.getAsyncRemote().sendText(message);
                } catch (IllegalStateException ise) {
                    // An ISE can occur if an attempt is made to write to a
                    // WebSocket connection after it has been closed. The
                    // alternative to catching this exception is to synchronise
                    // the writes to the clients along with the addSnake() and
                    // removeSnake() methods that are already synchronised.

                    log.error("[Network Error],msg = " + message);

                    // 异常报警

                    try {
                        session.close();

                        removeSocketSession(terminalNo);
                    } catch (IOException ex) {
                    }

                    throw new EduException(ErrorCode.ERR_DEVICENETWORKABNORMAL);
                }

            }
        } else {
            throw new EduException(ErrorCode.ERR_DEVICENETWORKABNORMAL);
        }
    }

    /**
     * 发送推送消息
     * 
     * @param message
     * @param terminalNo
     * @return 0 超时 1 成功 2 失败
     */
    private int _sendPushMsg(String message, String terminalNo) throws EduException {
        if (StringUtils.isNotEmpty(message) && PacketUtils.PACKET_FORMAT_ERROR.equalsIgnoreCase(message))
            throw new EduException(ErrorCode.ERR_WRONGPUSHMSGFORMAT);
        log.debug("_sendPushMsg,msg = " + message+","+terminalNo);
        Session session = getSocketSession(terminalNo);
        if (session != null && session.isOpen()) {
            // 加锁否???
            synchronized (session) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (java.io.IOException e) {
                    // An ISE can occur if an attempt is made to write to a
                    // WebSocket connection after it has been closed. The
                    // alternative to catching this exception is to synchronise
                    // the writes to the clients along with the addSnake() and
                    // removeSnake() methods that are already synchronised.

                    log.error("[Network Error],msg = " + message);

                    // 异常报警

                    try {
                        session.close();

                        removeSocketSession(terminalNo);
                    } catch (IOException ex) {
                    }

                    throw new EduException(ErrorCode.ERR_DEVICENETWORKABNORMAL);
                }

            }
        } else {
            throw new EduException(ErrorCode.ERR_DEVICENETWORKABNORMAL);
        }
        return 1;
    }

    /**
     * 发送推送消息
     * 
     * @param message
     * @param terminalNo
     */
    public void sendRequestMsg(Object request, String terminalNo) throws EduException {
        String message = PacketUtils.createRequestPacket(request, terminalNo);
        _sendPushMsg(message, terminalNo);
    }

    /**
     * 发送推送消息以及等待消息应答
     * 
     * @param message
     * @param terminalNo
     */
    public String sendPushMsgSync(Object request, String terminalNo) throws EduException {
        WebSocketMsg webSocketMsg = getPushWebSocketMsgMap(terminalNo);
        if (webSocketMsg != null) {
            String message = webSocketMsg.createRequestPacket(request, terminalNo);

            _sendPushMsg(message, terminalNo);

            // 等待应答结果

            String result = webSocketMsg.getResult();
            if (!ErrorCode.OK_SUCCESS.equals(result)) {
                throw new EduException(result);
            }
        } else {
            throw new EduException(ErrorCode.ERR_DEVICENETWORKABNORMAL);
        }
        return "";
    }

    /**
     * 发送推送消息(等待应答消息)
     * 
     * @param messageId
     * @param serviceName
     * @param body
     * @param terminalNo
     * @return 0 成功
     * @throws EduException
     */
    public String sendPushMsgSync(String messageId, String serviceName, String body, String terminalNo)
            throws EduException {
        String result = "";
        WebSocketMsg webSocketMsg = getPushWebSocketMsgMap(terminalNo);
        if (webSocketMsg != null) {
            String message = webSocketMsg.createRequestPacket(messageId, serviceName, body);
            //System.out.println("====WebSocketManager==sendPushMsgSync:" + message);
            _sendPushMsg(message, terminalNo);

            // 等待应答结果
            result = webSocketMsg.getResult();

        } else {
            throw new EduException(ErrorCode.ERR_DEVICENETWORKABNORMAL);
        }

        return result;
    }

    /**
     * 监控网络连接是否正常的线程
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
    private class MonitorConnAliveThread extends Thread {
        private final long sleepTime = 1000 * 60 * 2L;

        public void run() {
            try {
                Thread.sleep(1000 * 60);// *3

                java.util.Date nowDate = null;
                java.util.Date lastActiveDate = null;
                long difference = 0L;
                long minute = 0L;
                String terminalNo = "";
                GServer.println(" ----------- MonitorConnAliveThread start -----------"+isRunning());
                while (isRunning()) {
                    // 改为从数据库中取吗?
                    Set<String> keySet = getLastActiveDateTimeList();
                    for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
                        terminalNo = it.next();
                        lastActiveDate = getLastActiveDateTime(terminalNo);
                        if (lastActiveDate != null) {
                            // 相差天数：long day=difference/(3600*24*1000)
                            // 相差小时：long hour=difference/(3600*1000)
                            // 相差分钟：long minute=difference/(60*1000)
                            // 相差秒： long second=difference/1000
                            nowDate = new java.util.Date();
                            difference = nowDate.getTime() - lastActiveDate.getTime();
                            minute = difference / (60 * 1000); //
                            // System.out.println("difference=" + minute);
                            Session session = getSocketSession(terminalNo);
                            if (minute > 8)// 网络断开之后离线判定
                            {
                                if (session != null) {
                                    GServer.println(" close Session=>" + terminalNo);
                                    //addSocketSessionOld(terminalNo, session);// 记录旧的session
                                    removeAllSessionInfo(terminalNo);// 如果设备离线则清空旧的记录
                                    try {
                                        session.close();
                                    } catch (Exception ex0) {
                                        
                                    }
                                    
                                    /////////////////
                                    /*try {
                                        BusinessWorker.deviceOffline(terminalNo);
                                    } catch (Exception ex1) {
                                    }*/
                                }
                                
                            }
                        }
                    }

                    try{
                        Thread.sleep(sleepTime);
                    }catch(InterruptedException e){
                        
                    }
                }
            } catch (Exception e) {
                log.error("[MonitorConnAliveThread error] = " + e.getMessage());
            }
            
            GServer.println(" ----------- MonitorConnAliveThread end -----------"+isRunning());
        }
    }

}
