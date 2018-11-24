package com.dcdzsoft.sms.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;

/**
 * 
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: 助通科技</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zxy
 * @version 1.0
 */
public class MsgProxyZhuTongKj  implements ISMSProxy{
    private static Log log = org.apache.commons.logging.LogFactory.getLog(MsgProxyZhuTongKj.class);
    private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
    
    public String sendMessage(SMSInfo smsInfo) throws EduException
    {
        String result = "";
        String url="http://www.ztsms.cn/sendNSms.do";
        String username  = apCfg.getGatewayUser();  //账号
        String password  = apCfg.getGatewayPwd();  //密码
        String tkey      = getNowTime("yyyyMMddHHmmss");
        String mobile    = smsInfo.CustomerMobile;    //发送的手机号
        String content   = smsInfo.MsgContent;   //内容
        String productid = "333333"; //产品id  验证码 95533 通知 333333

        String dstime    = "";  //
        String xh        ="";
        try {
            content=URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String param ="username="+username+"&password="+getMD5(getMD5(password)+tkey)+"&tkey="+tkey+"&mobile="+mobile+"&content="+content+"&dstime="+dstime+"&productid="+productid+"&xh="+xh;
        String ret   =  sendPost(url,param);
        /**
         * -1            用户名或者密码不正确或用户禁用或者是管理账户;
         * 1,xxxxxxxx    1代表发送短信成功,xxxxxxxx代表消息编号
         * 2             余额不够或扣费错误
         * 3             扣费失败异常（请联系客服）
         * 5,xxxxxxxx    短信定时成功, xxxxxxxx代表消息编号
         * 6    有效号码为空
         * 7   短信内容为空
         * 8   无签名，必须，格式：【签名】
         * 9   没有Url提交权限
         * 10  发送号码过多,最多支持2000个号码； 
         * 11  产品ID异常或产品禁用
         * 12  参数异常
         * 15  Ip验证失败
         * 19  短信内容过长，最多支持500个,或提交编码异常导致
         */
        if(!ret.startsWith("1,")){
            result = "-333";
        }else{
            result = "0";
        }
        log.debug(ret+","+smsInfo.MsgContent);
        System.out.println(ret);
        return result;
    }
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    @SuppressWarnings("unused")
    private static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            //设置相应请求时间
            connection.setConnectTimeout(30000);
            //设置读取超时时间
            connection.setReadTimeout(30000);
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }*/
            //System.out.println("响应时间--->" + map.get(null));
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println(e);
            return "发送GET请求出现异常！";
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    @SuppressWarnings("unused")
    private static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            //设置相应请求时间
            connection.setConnectTimeout(30000);
            //设置读取超时时间
            connection.setReadTimeout(30000);
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }*/
            //System.out.println("响应时间--->" + map.get(null));
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println(e);
            return "发送GET请求出现异常！";
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置相应请求时间
            conn.setConnectTimeout(30000);
            //设置读取超时时间
            conn.setReadTimeout(30000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println(e);
            return "发送 POST 请求出现异常！";
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    /**
     * MD5加密
     * 
     * @param src
     * @return
     */
    private static String getMD5(String src) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(src.getBytes());
            byte[] s=m.digest();

            return bintoascii(s);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    private static String bintoascii(byte[] bySourceByte) {
        int len, i;
        byte tb;
        char high, tmp, low;
        String result = new String();
        len = bySourceByte.length;
        for (i = 0; i < len; i++) {
            tb = bySourceByte[i];

            tmp = (char) ((tb >>> 4) & 0x000f);
            if (tmp >= 10) {
                high = (char) ('a' + tmp - 10);
            } else {
                high = (char) ('0' + tmp);
            }
            result += high;
            tmp = (char) (tb & 0x000f);
            if (tmp >= 10) {
                low = (char) ('a' + tmp - 10);
            } else {
                low = (char) ('0' + tmp);
            }

            result += low;
        }
        return result;
    }
    /**
     * 计算指定日期距今多少年
     * @param times 指定日期
     * @return 年
     */
    private static int getTime(String times){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(new Date());
        String t1 = time.replace('-','/'); 
        String t2 = times.replace('-','/'); 

        @SuppressWarnings("deprecation")
        Date dt1= new Date(t1); 
        @SuppressWarnings("deprecation")
        Date dt2= new Date(t2); 
        long i= (dt1.getTime() - dt2.getTime())/(1000*60*60*24); 
        return (int) (i/365);
    }
    
    /**
     * 返回当前时间
     * @return
     */
    private static String getNowTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(new Date());
        return time;
    }
    
    /**
     * 返回当前时间
     * @return
     */
    private static String getNowTime(String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String time=sdf.format(new Date());
        return time;
    }
}
