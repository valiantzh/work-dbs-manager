package com.dcdzsoft.sms.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

/**
 * 
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: 亿美软通</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zxy
 * @version 1.0
 */
public class MsgProxyYiMeiRuanTong  implements ISMSProxy{
    private static Log logger = org.apache.commons.logging.LogFactory.getLog(MsgProxyYiMeiRuanTong.class);
    private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
    
    private static String sn = "2SDK-EMY-6688-AAAA";// 软件序列号,请通过亿美销售人员获取
    private static String key = "1";// 注册完，默认和密码一样
    private static String password = "1";// 密码,请通过亿美销售人员获取
    private static String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
    
    public String sendMessage(SMSInfo smsInfo) throws EduException
    {
        String result    = "";

        sn  = apCfg.getGatewayUser();  //账号
        key  = apCfg.getGatewayPwd();  //密码
        password       = apCfg.getGatewayPwd();  //密码
        String mdn     = smsInfo.CustomerMobile;    //发送的手机号
        String message = smsInfo.MsgContent;   //内容

        try {
            message=URLEncoder.encode(message, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String code = "";
        long seqId = System.currentTimeMillis();
        String param = "cdkey=" + sn + "&password=" + key + "&phone=" + mdn + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
        String url = baseUrl + "sendsms.action";
        String ret = sendSMS(url, param);
        ///System.out.println("发送结果:" + ret);
        if(ret.equals("0")){
            result = "0";
        }else{
            result = "-333";
        }
        logger.debug(smsInfo.MsgContent+","+ret);
        //System.out.println(ret);
        return result;
    }
    public static String regist(){
        sn  = apCfg.getGatewayUser();  //账号
        password       = apCfg.getGatewayPwd();  //密码
        String url = baseUrl + "regist.action";
        String param = "cdkey=" + sn + "&password=" + password;
        String ret = registAndLogout(url, param);
        System.out.println(param+"注册结果:" + ret);
        return ret;
    }
    
    // 注册、注销
    @SuppressWarnings("unused")
    private static String registAndLogout(String url, String param) {
        String ret = "失败";
        url = url + "?" + param;
        System.out.println("【SDKHttpClient】发送请求到SDK->" + url);
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        
        responseString = responseString.trim();
        if (null != responseString && !"".equals(responseString)) {
            ret = xmlResponseForRegist(responseString);
        }
        return ret;
    }

    // 解析注册注销响应
    private static String xmlResponseForRegist(String response) {
        String result = "失败";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        result = root.elementText("error");
        return result;
    }

    // 获取mo
    @SuppressWarnings("unused")
    private static List<Mo> getMos(String url, String sn, String key) {
        List<Mo> _Mos = new ArrayList<Mo>();

        if ("".equals(url)) {
            return _Mos;
        }
        String param = "cdkey=" + sn + "&password=" + key;
        url = url + "?" + param;
        System.out.println("【SDKHttpClient】Request-Url:" + url);
        HttpClientUtil.getInstance();
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);

        responseString = responseString.trim();
        if (null != responseString && !"".equals(responseString)) {
            List<Element> elements = xmlDoc(responseString);
            for (Element element : elements) {
                if (null != element) {
                    logger.debug("【SDKHttpClient】上行请求->" + responseString);
                    Mo mo = new Mo();
                    mo.setMobileNumber(element.elementText("srctermid"));
                    mo.setSmsContent(element.elementText("msgcontent"));
                    mo.setAddSerial(element.elementText("addSerial"));
                    mo.setAddSerialRev(element.elementText("addSerialRev"));
                    mo.setSentTime(element.elementText("sendTime"));
                    _Mos.add(mo);
                }
            }
        }
        return _Mos;
    }

    // 获取report
    @SuppressWarnings("unused")
    private static List<StatusReport> getReports(String url, String sn, String key) {
        List<StatusReport> _Reports = new ArrayList<StatusReport>();
        if ("".equals(url)) {
            return _Reports;
        }
        String param = "cdkey=" + sn + "&password=" + key;
        url = url + "?" + param;
        logger.debug("【SDKHttpClient】Request-Url:" + url);
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if (null != responseString && !"".equals(responseString)) {
            List<Element> elements = xmlDoc(responseString);
            for (Element element : elements) {
                if (null != element) {
                    logger.debug("【SDKHttpClient】REPORT->" + element.elementText("seqid"));
                    StatusReport report = new StatusReport();
                    report.setMobile(element.elementText("srctermid"));
                    report.setErrorCode(element.elementText("state"));
                    report.setSeqID(Long.parseLong(element.elementText("seqid")));
                    report.setReceiveDate(element.elementText("receiveDate"));
                    report.setSubmitDate(element.elementText("submitDate"));
                    report.setServiceCodeAdd(element.elementText("addSerialRev"));
                    System.out.println("收到一条报告：手机号码=" + report.getMobile() + "|消息id=" + report.getSeqID() + "|状态=" + report.getErrorCode() + "|报告时间=" + report.getReceiveDate());
                    _Reports.add(report);
                }
            }

        }
        return _Reports;
    }
    // 下发
    private static String sendSMS(String url, String param) {
        String ret = "";
        url = url + "?" + param;
        //System.out.println("【SDKHttpClient】发送MT到SDK->" + url);
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        //String responseString = HttpClientUtil.sendGet(url);
        responseString = responseString.trim();
        if (null != responseString && !"".equals(responseString)) {
            ret = xmlMt(responseString);
        }
        return ret;
    }
    // 下发Post
    @SuppressWarnings("unused")
    private static String sendSMSByPost(String url, String param) {
        String ret = "";
        url = url + "?" + param;
        //System.out.println("【SDKHttpClient】发送MT到SDK By Post->" + url);
        String responseString = HttpClientUtil.getInstance().doPostRequest(url);
        responseString = responseString.trim();
        if (null != responseString && !"".equals(responseString)) {
            ret = xmlMt(responseString);
        }
        return ret;
    }

    // 获取余额
    @SuppressWarnings("unused")
    private static String getBalance(String url, String param) {
        String ret = "失败";
        url = url + "?" + param;
        logger.info("【SDKHttpClient】Balance->" + url);
        System.out.println(url);
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if (null != responseString && !"".equals(responseString)) {
            ret = xmlResponse(responseString);
        }
        return ret;
    }

    // 统一解析格式
    private static String xmlResponse(String response) {
        String result = "失败";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        result = root.elementText("message");
        return result;
    }

    // 解析状态、上行
    private static List<Element> xmlDoc(String response) {
        boolean result = false;
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        List<Element> elemets = new ArrayList();
        // 增强for循环来遍历所有的U8ArrivalVouch节点
        for (Element element : list) {
            String message = element.getName();
            if ("message".equalsIgnoreCase(message)) {
                if (element.elements().size() > 0) {
                    // System.out.println("--------------------"+element.elements().size());
                    elemets.add(element);
                }
            }

        }
        return elemets;
    }

    // 解析下发response
    private static String xmlMt(String response) {
        String result = "0";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
            result = "-250";
        }
        Element root = document.getRootElement();
        result = root.elementText("error");
        if (null == result || "".equals(result)) {
            result = "-250";
        }
        return result;
    }
    
}

class HttpClientUtil {
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);
    private static HttpClient client = null;

    // 构造单例
    private HttpClientUtil() {

        MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        // 默认连接超时时间
        params.setConnectionTimeout(60000);
        // 默认读取超时时间
        params.setSoTimeout(60000);
        // 默认单个host最大连接数
        params.setDefaultMaxConnectionsPerHost(200);// very important!!
        // 最大总连接数
        params.setMaxTotalConnections(500);// very important!!
        httpConnectionManager.setParams(params);

        client = new HttpClient(httpConnectionManager);

        client.getParams().setConnectionManagerTimeout(3000);
        // client.getParams().setIntParameter("http.socket.timeout", 10000);
        // client.getParams().setIntParameter("http.connection.timeout", 5000);
    }

    private static class ClientUtilInstance {
        private static final HttpClientUtil ClientUtil = new HttpClientUtil();
    }

    public static HttpClientUtil getInstance() {
        return ClientUtilInstance.ClientUtil;
    }

    /**
     * 发送http GET请求，并返回http响应字符串
     * 
     * @param urlstr
     *            完整的请求url字符串
     * @return
     */
    public String doGetRequest(String urlstr) {
        String response = "";

        HttpMethod httpmethod = new GetMethod(urlstr);
        try {
            int statusCode = client.executeMethod(httpmethod);
            InputStream _InputStream = null;
            if (statusCode == HttpStatus.SC_OK) {
                _InputStream = httpmethod.getResponseBodyAsStream();
            }
            if (_InputStream != null) {
                response = GetResponseString(_InputStream, "UTF-8");
            }
        } catch (HttpException e) {
            logger.error("获取响应错误，原因：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("获取响应错误，原因1：" + e.getMessage());
            e.printStackTrace();
        } finally {
            httpmethod.releaseConnection();
        }
        return response;
    }

    public String doPostRequest(String postUrl) {
        String response = "";
        PostMethod postMethod = new PostMethod(postUrl);
        try {
        //postMethod.getParams().setContentCharset("utf-8");
            int statusCode = client.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                InputStream _InputStream = null;
                if (statusCode == HttpStatus.SC_OK) {
                    _InputStream = postMethod.getResponseBodyAsStream();
                }
                if (_InputStream != null) {
                    response = GetResponseString(_InputStream, "UTF-8");
                }
            }
        } catch (HttpException e) {
            logger.error("获取响应错误，原因：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("获取响应错误，原因1：" + e.getMessage());
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return response;
    }

    /**
     * 
     * @param _InputStream
     * @param Charset
     * @return
     */
    public String GetResponseString(InputStream _InputStream, String Charset) {
        String response = "";
        try {
            if (_InputStream != null) {
                StringBuffer buffer = new StringBuffer();
                InputStreamReader isr = new InputStreamReader(_InputStream, Charset);
                Reader in = new BufferedReader(isr);
                int ch;
                while ((ch = in.read()) > -1) {
                    buffer.append((char) ch);
                }
                response = buffer.toString();
                buffer = null;
            }
        } catch (Exception e) {
            logger.error("获取响应错误，原因：" + e.getMessage());
            response = response + e.getMessage();
            e.printStackTrace();
        }
        return response;
    }
}

class Mo implements java.io.Serializable {
    private java.lang.String addSerial;

    private java.lang.String addSerialRev;

    private java.lang.String channelnumber;

    private java.lang.String mobileNumber;

    private java.lang.String sentTime;

    private java.lang.String smsContent;

    public Mo() {
    }

    public Mo(java.lang.String addSerial, java.lang.String addSerialRev, java.lang.String channelnumber, java.lang.String mobileNumber, java.lang.String sentTime, java.lang.String smsContent) {
    this.addSerial = addSerial;
    this.addSerialRev = addSerialRev;
    this.channelnumber = channelnumber;
    this.mobileNumber = mobileNumber;
    this.sentTime = sentTime;
    this.smsContent = smsContent;
    }

    /**
     * Gets the addSerial value for this Mo.
     * 
     * @return addSerial
     */
    public java.lang.String getAddSerial() {
    return addSerial;
    }

    /**
     * Sets the addSerial value for this Mo.
     * 
     * @param addSerial
     */
    public void setAddSerial(java.lang.String addSerial) {
    this.addSerial = addSerial;
    }

    /**
     * Gets the addSerialRev value for this Mo.
     * 
     * @return addSerialRev
     */
    public java.lang.String getAddSerialRev() {
    return addSerialRev;
    }

    /**
     * Sets the addSerialRev value for this Mo.
     * 
     * @param addSerialRev
     */
    public void setAddSerialRev(java.lang.String addSerialRev) {
    this.addSerialRev = addSerialRev;
    }

    /**
     * Gets the channelnumber value for this Mo.
     * 
     * @return channelnumber
     */
    public java.lang.String getChannelnumber() {
    return channelnumber;
    }

    /**
     * Sets the channelnumber value for this Mo.
     * 
     * @param channelnumber
     */
    public void setChannelnumber(java.lang.String channelnumber) {
    this.channelnumber = channelnumber;
    }

    /**
     * Gets the mobileNumber value for this Mo.
     * 
     * @return mobileNumber
     */
    public java.lang.String getMobileNumber() {
    return mobileNumber;
    }

    /**
     * Sets the mobileNumber value for this Mo.
     * 
     * @param mobileNumber
     */
    public void setMobileNumber(java.lang.String mobileNumber) {
    this.mobileNumber = mobileNumber;
    }

    /**
     * Gets the sentTime value for this Mo.
     * 
     * @return sentTime
     */
    public java.lang.String getSentTime() {
    return sentTime;
    }

    /**
     * Sets the sentTime value for this Mo.
     * 
     * @param sentTime
     */
    public void setSentTime(java.lang.String sentTime) {
    this.sentTime = sentTime;
    }

    /**
     * Gets the smsContent value for this Mo.
     * 
     * @return smsContent
     */
    public java.lang.String getSmsContent() {
    return smsContent;
    }

    /**
     * Sets the smsContent value for this Mo.
     * 
     * @param smsContent
     */
    public void setSmsContent(java.lang.String smsContent) {
    this.smsContent = smsContent;
    }

    public String toString() {
    return addSerial + "|" + addSerialRev + "|" + channelnumber + "|" + mobileNumber + "|" + sentTime + "|" + smsContent;
    }
}

class StatusReport implements java.io.Serializable {
    private java.lang.String errorCode;

    private java.lang.String memo;

    private java.lang.String mobile;

    private java.lang.String receiveDate;

    private int reportStatus;

    private long seqID;

    private java.lang.String serviceCodeAdd;

    private java.lang.String submitDate;

    public StatusReport() {
    }

    public StatusReport(java.lang.String errorCode, java.lang.String memo, java.lang.String mobile, java.lang.String receiveDate, int reportStatus, long seqID, java.lang.String serviceCodeAdd,
        java.lang.String submitDate) {
    this.errorCode = errorCode;
    this.memo = memo;
    this.mobile = mobile;
    this.receiveDate = receiveDate;
    this.reportStatus = reportStatus;
    this.seqID = seqID;
    this.serviceCodeAdd = serviceCodeAdd;
    this.submitDate = submitDate;
    }

    /**
     * Gets the errorCode value for this StatusReport.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
    return errorCode;
    }

    /**
     * Sets the errorCode value for this StatusReport.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.String errorCode) {
    this.errorCode = errorCode;
    }

    /**
     * Gets the memo value for this StatusReport.
     * 
     * @return memo
     */
    public java.lang.String getMemo() {
    return memo;
    }

    /**
     * Sets the memo value for this StatusReport.
     * 
     * @param memo
     */
    public void setMemo(java.lang.String memo) {
    this.memo = memo;
    }

    /**
     * Gets the mobile value for this StatusReport.
     * 
     * @return mobile
     */
    public java.lang.String getMobile() {
    return mobile;
    }

    /**
     * Sets the mobile value for this StatusReport.
     * 
     * @param mobile
     */
    public void setMobile(java.lang.String mobile) {
    this.mobile = mobile;
    }

    /**
     * Gets the receiveDate value for this StatusReport.
     * 
     * @return receiveDate
     */
    public java.lang.String getReceiveDate() {
    return receiveDate;
    }

    /**
     * Sets the receiveDate value for this StatusReport.
     * 
     * @param receiveDate
     */
    public void setReceiveDate(java.lang.String receiveDate) {
    this.receiveDate = receiveDate;
    }

    /**
     * Gets the reportStatus value for this StatusReport.
     * 
     * @return reportStatus
     */
    public int getReportStatus() {
    return reportStatus;
    }

    /**
     * Sets the reportStatus value for this StatusReport.
     * 
     * @param reportStatus
     */
    public void setReportStatus(int reportStatus) {
    this.reportStatus = reportStatus;
    }

    /**
     * Gets the seqID value for this StatusReport.
     * 
     * @return seqID
     */
    public long getSeqID() {
    return seqID;
    }

    /**
     * Sets the seqID value for this StatusReport.
     * 
     * @param seqID
     */
    public void setSeqID(long seqID) {
    this.seqID = seqID;
    }

    /**
     * Gets the serviceCodeAdd value for this StatusReport.
     * 
     * @return serviceCodeAdd
     */
    public java.lang.String getServiceCodeAdd() {
    return serviceCodeAdd;
    }

    /**
     * Sets the serviceCodeAdd value for this StatusReport.
     * 
     * @param serviceCodeAdd
     */
    public void setServiceCodeAdd(java.lang.String serviceCodeAdd) {
    this.serviceCodeAdd = serviceCodeAdd;
    }

    /**
     * Gets the submitDate value for this StatusReport.
     * 
     * @return submitDate
     */
    public java.lang.String getSubmitDate() {
    return submitDate;
    }

    /**
     * Sets the submitDate value for this StatusReport.
     * 
     * @param submitDate
     */
    public void setSubmitDate(java.lang.String submitDate) {
    this.submitDate = submitDate;
    }
}
