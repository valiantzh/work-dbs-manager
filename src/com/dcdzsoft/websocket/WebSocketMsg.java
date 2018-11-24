package com.dcdzsoft.websocket;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import net.sf.json.JSONObject;

import com.dcdzsoft.EduException;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.packet.JsonPacket;
import com.dcdzsoft.util.JsonUtils;

/**
 * 
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zxy
 * @version 1.0
 */
public class WebSocketMsg {
    private static Log log = org.apache.commons.logging.LogFactory.getLog(WebSocketMsg.class);
    private boolean isBusy = false;
    private String _TerminalNo = "";
    private String _MessageId = ""; //消息ID
    private String _ServiceName = ""; //服务名称
    private String _body = "";
    private String _errMsg = "";
    private String message = "";
    private String respBody = "";
    public WebSocketMsg(String terminalNo){
        this._TerminalNo = terminalNo;
    }
    public String getMessageId(){
        return this._MessageId;
    }
    public String getTermianlNo(){
        return this._TerminalNo;
    }
    public String getReqBody(){
        return this._body;
    }
    
    private String createRequestPacket() throws EduException{
        //包头
        JsonPacket packet = new JsonPacket();
        packet._CmdType = JsonPacket.MSG_SENT_BY_SERVER;
        packet._MessageId = this._MessageId;
        packet._ServiceName = this._ServiceName;

        //包体
        packet.body = this._body;
        //packet._Sign = ""; //md5(m_sSecretKey + body)
        
        
        //打包JSON字符串
        JSONObject json = JSONObject.fromObject(packet);
        if(json == null){
            throw new EduException(ErrorCode.ERR_WRONGPUSHMSGFORMAT);
        }
        this.message = json.toString();
        return this.message;
    }
    /**
     * 创建服务端发起的请求报文
     * @param request
     * @param terminalNo
     * @return
     */
    public  String createRequestPacket(Object request,String terminalNo)  throws EduException
    {
        return createRequestPacket(UUID.randomUUID().toString(), request.getClass().getSimpleName(), JsonUtils.dtoToJson(request));
    }
    
    /**
     * 
     * @param messageId
     * @param serviceName
     * @param body
     * @return
     * @throws EduException
     */
    public synchronized String createRequestPacket(String messageId, String serviceName, String body)  throws EduException
    {
        if(this.isBusy){//同时只能有一个远程请求
//            System.out.println("ERR_DEVICEBUSINESSBUSY:messageId="+messageId+",serviceName="+serviceName+",body="+body);
            throw new EduException(ErrorCode.ERR_DEVICEBUSINESSBUSY);
        }
        //消息内容
        this._MessageId = messageId;
        this._ServiceName = serviceName;
        this._body = body;
        
        //应答结果初始化
        this._errMsg = "";
        this.respBody= "";
        this.isBusy = true;
        
        return createRequestPacket();
    }
    public synchronized  void putResponse(JsonPacket packet, String message) throws EduException
    {
        //System.out.println("Response=" + message+","+packet.body);
        if(packet!=null && this._MessageId.equalsIgnoreCase(packet._MessageId) 
                && this._ServiceName.equalsIgnoreCase(packet._ServiceName)){
            //提取应答结果
            this.respBody = packet.body;
            try{
                JSONObject json = JSONObject.fromObject(packet.body);
                if(json != null){
                    //System.out.println(json.toString());
                    boolean success = json.optBoolean("success");
                    if(success){
                        this._errMsg =  ErrorCode.OK_SUCCESS;//成功
                    }else{
                        this._errMsg = json.optString("msg");
                    }
                }else{
                    log.error("[unpack error(JsonResult):],msg=" + message);
                }
            }catch(Exception e){
                log.error("[handle Response(JsonResult):],error= " + e.getMessage() + ",msg=" + message );
            }
            this.notifyAll();
        }
    }
    /**
     * 
     * @return 
     */
    public synchronized String getResult(){
        
        try {
            this.wait(5*1000);//5秒
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(StringUtils.isEmpty(_errMsg)){
            this._errMsg = ErrorCode.ERR_DEVICEBUSINESSTIMEOUT;//业务处理超时
        }
        log.info("GetPushMsgResult:" +this._MessageId+","+this._ServiceName+","+this.respBody+",result="+_errMsg);
        
        String result = this._errMsg;//
        this.isBusy = false;
        this._errMsg = "";
        return result;
    }
}
