
package com.dcdzsoft.websocket;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;

import com.dcdzsoft.business.GServer;
import com.dcdzsoft.util.DateUtils;

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

@ServerEndpoint(value = "/dbscomm/{terminal-no}")
public class DBSCommWebSocket {
    private static Log log = org.apache.commons.logging.LogFactory.getLog(DBSCommWebSocket.class);
    private static WebSocketManager socketManager = WebSocketManager.getInstance();
    private static GServer gServer = GServer.getInstance();

    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetDecoder decoder = charset.newDecoder();

    @OnOpen
    public void onOpen(Session session, @PathParam("terminal-no") String terminalNo) {
        System.out.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + ",onOpen=>" + terminalNo);

        // session.setMaxTextMessageBufferSize(8192*2);

        // 判断是否存在未关闭的连接
        Session oldSession = socketManager.getSocketSession(terminalNo);
        if (oldSession != null) {
            System.out.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + ",remove oldSession=>" + terminalNo);
            socketManager.addSocketSessionOld(terminalNo, session);// 记录旧的session
            socketManager.removeAllSessionInfo(terminalNo);
            try {
                /// 发送关闭信息否???
                oldSession.close();
                oldSession = null;
            } catch (Exception e) {
            }
        }

        ///
        socketManager.addSocketSession(terminalNo, session);
        socketManager.addLastActiveDateTime(terminalNo, new java.util.Date());
        socketManager.addPushWebSocketMsgMap(terminalNo);

        try {
            BusinessWorker.deviceOnline(terminalNo);
        } catch (Exception ex1) {
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("terminal-no") String terminalNo) {
        System.out.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + ",onClose=>" + terminalNo);

        Session oldSession = socketManager.getSocketSessionOld(terminalNo);
        if(oldSession != null){
            System.out.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + ",removeOldSession=>" + terminalNo);
            socketManager.removeSocketSessionOld(terminalNo);
        }else{
            ///
            socketManager.removeAllSessionInfo(terminalNo);
            try {
                BusinessWorker.deviceOffline(terminalNo);
            } catch (Exception e) {
            }
        }
        
    }

    @OnError
    public void onError(Session session, Throwable t) throws Throwable {
        // Most likely cause is a user closing their browser. Check to see if
        // the root cause is EOF and if it is ignore it.
        // Protect against infinite loops.
        int count = 0;
        Throwable root = t;
        while (root.getCause() != null && count < 20) {
            root = root.getCause();
            count++;
        }

        if (root instanceof EOFException) {
            // Assume this is triggered by the user closing their browser and
            // ignore it.
            System.out.println(
                    DateUtils.nowDate() + " " + DateUtils.nowTime() + ",[webSocket onError0] " + t.getMessage());
        } else if (root instanceof IOException) {
            System.out.println(
                    DateUtils.nowDate() + " " + DateUtils.nowTime() + ",[webSocket onError1] " + t.getMessage());
        } else {
            // throw t;
            // t.printStackTrace();
            System.out.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + ",[webSocket onError2]= "
                    + t.getClass().getName() + "," + t.getMessage());
        }
    }

    @OnMessage
    public void onTextMessage(Session session, String message, boolean last,
            @PathParam("terminal-no") String terminalNo) {
        // System.out.println("terminalNo=" + terminalNo + "," + message);

        if (session.isOpen() && last) {
            socketManager.addLastActiveDateTime(terminalNo, new java.util.Date());
            socketManager.submitTask(message, terminalNo);
        }
    }

    /*
     * Process a received binary message
     * 
     * @param
     */
    @OnMessage
    public void onBinaryMessage(ByteBuffer bb, @PathParam("terminal-no") String terminalNo) {
        // System.out.println("haha:" + byteBufferToString(bb) + "," +
        // terminalNo);
        socketManager.addLastActiveDateTime(terminalNo, new java.util.Date());
    }

    /**
     * Process a received pong. This is a NO-OP.
     *
     * @param pm
     *            Ignored.
     */
    @OnMessage
    public void onPongMessage(PongMessage pm) {
        // System.out.println("haha:" + pm.getApplicationData());
        // session.getAsyncRemote().sendBinary(pm.getApplicationData());
    }

    private static String byteBufferToString(ByteBuffer buffer) {
        CharBuffer charBuffer = null;

        try {
            charBuffer = decoder.decode(buffer);
            buffer.flip();

            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
