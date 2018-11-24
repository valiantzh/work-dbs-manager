package com.dcdzsoft.business.mb;

import java.io.IOException;

import javax.sql.RowSet;
import javax.websocket.Session;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.websocket.WebSocketManager;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 设备心跳包检测 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBHeartBeat extends ActionBean
{
    public OutParamMBHeartBeat doBusiness(InParamMBHeartBeat in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamMBHeartBeat out = new OutParamMBHeartBeat();
        //发送心跳包之后将活跃时间更新
//        System.out.println(in.TerminalNo+"心跳");
        WebSocketManager socketManager = WebSocketManager.getInstance();
        if(socketManager.getSocketSession(in.TerminalNo)==null){
        	throw new EduException(ErrorCode.ERR_DEVICENETWORKABNORMAL);//这个错误用来应对这种情况：柜端网络畅通，心跳包服务端可以收到，但是柜端没有建立wedsocket连接，因此需要拒绝心跳包，让柜端重连socket
        }
        
        socketManager.addLastActiveDateTime(in.TerminalNo, new java.util.Date());
        
        //关闭session
//      Session session = socketManager.getSocketSession(in.TerminalNo);
//		if (session != null && session.isOpen()) {
//			try {
//	            session.close();
//            } catch (IOException e) {
//	            e.printStackTrace();
//            }
//		}
    
        return out;
    }
}
