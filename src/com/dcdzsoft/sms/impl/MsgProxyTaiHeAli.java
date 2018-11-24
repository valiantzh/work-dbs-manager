package com.dcdzsoft.sms.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.DateUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
/**
 * 
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: 东信短信接口</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zhengxy
 * @version 1.0
 */
public class MsgProxyTaiHeAli implements ISMSProxy {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyTaiHeAli.class);
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
    
	public String sendMessage(SMSInfo smsInfo) throws EduException
	{
	    String url     = "http://gw.api.taobao.com/router/rest";
	    String appkey  = apCfg.getGatewayUser();
	    String secret  = apCfg.getGatewayPwd();
	    TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
	    AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
	    req.setExtend("123456");
	    req.setSmsType("normal");
	    req.setSmsFreeSignName("绿色菜园网");
	    req.setRecNum(smsInfo.CustomerMobile);

		String result = "";
        try
        {
            StringBuffer msgParam =new StringBuffer();
            String msgTemplate = "";
            switch(smsInfo.MsgType){
            case SMSInfo.MSG_TYPE_DELIVERY:
                StringBuffer msg =new StringBuffer();
                msg.append(smsInfo.TerminalName);//smsInfo.Location
                if(StringUtils.isNotEmpty(smsInfo.BoxNo)){
                    msg.append(smsInfo.BoxNo).append("号箱");//smsInfo.Location
                }
                msgParam.append("{");
                msgParam.append("\"location\":\"").append(msg.toString()).append("\"");
                msgParam.append(",\"openboxkey\":\"").append(smsInfo.OpenBoxKey).append("\"");
                msgParam.append("}");
                msgTemplate = "SMS_70235143";
                break;
            case SMSInfo.MSG_TYPE_REMINDER:
                msgParam.append("{");
                msgParam.append("\"hour\":\"").append("24").append("\"");
                msgParam.append(",\"time\":\"").append("24:00").append("\"");
                msgParam.append(",\"openboxkey\":\"").append(smsInfo.OpenBoxKey).append("\"");
                msgParam.append(",\"location\":\"").append(smsInfo.TerminalName).append("\"");
                msgParam.append(",\"msgtel\":\"").append(smsInfo.msgTel).append("\"");
                msgParam.append("}");
                msgTemplate = "SMS_38745037";
                break;
            case SMSInfo.MSG_TYPE_EXPIRED:
                msgParam.append("{");
                msgParam.append("\"location\":\"").append(smsInfo.TerminalName).append("\"");
                msgParam.append(",\"packageid\":\"").append(smsInfo.PackageID).append("\"");
                msgParam.append(",\"manmobile\":\"").append("").append("\"");//彭工要求不用配送员 170417手机号smsInfo.PostmanID
                msgParam.append(",\"msgtel\":\"").append(smsInfo.msgTel).append("\"");
                msgParam.append("}");
                msgTemplate = "SMS_38645072";
                break;
            case SMSInfo.MSG_TYPE_TAKEDOUT:
                msgParam.append("{");
                msgParam.append("\"packageid\":\"").append(smsInfo.PackageID).append("\"");
                msgParam.append(",\"msgtel\":\"").append(smsInfo.msgTel).append("\"");
                msgParam.append("}");
                msgTemplate = "SMS_38760054";
                break;
            case SMSInfo.MSG_TYPE_CHECKCODE:
            case SMSInfo.MSG_TYPE_REGISTER:
                msgParam.append("{");
                msgParam.append("\"pwd\":\"").append(smsInfo.DynamicCode).append("\"");
                msgParam.append("}");
                msgTemplate = "SMS_38780055";
                break;
            }
            req.setSmsTemplateCode(msgTemplate);
            req.setSmsParamString(msgParam.toString());
            
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            String respBody = rsp.getBody();
            log.info(msgTemplate+","+msgParam.toString()+":"+respBody);
            com.taobao.api.domain.BizResult res = rsp.getResult();
            if(res == null){
                System.out.println(respBody);
                throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
            }
            if(res.getSuccess()){
                result = "0";
            }else{
                throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
            }
            
        }catch(Exception e)
        {
        	e.printStackTrace();
        	throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        }
        finally
        {
        }
        
        return result;
	}
}
