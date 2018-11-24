package com.dcdzsoft.sms.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSONObject;

/**
 * Http请求
 * @author mszhou
 *
 */
public class MsgProxyShunfeng {
    private static final int TIMEOUT = 45000;
    public static final String ENCODING = "utf-8";

    /**
     * 创建HTTP连接
     * 
     * @param url
     *            地址
     * @param method
     *            方法
     * @param headerParameters
     *            头信息
     * @param body
     *            请求内容
     * @return
     * @throws Exception
     */
    private static HttpURLConnection createConnection(String url,
            String method, Map<String, String> headerParameters, String body)
            throws Exception {
        URL Url = new URL(url);
        trustAllHttpsCertificates();
        HttpURLConnection httpConnection = (HttpURLConnection) Url
                .openConnection();
        // 设置请求时间
        httpConnection.setConnectTimeout(TIMEOUT);
        // 设置 header
        if (headerParameters != null) {
            Iterator<String> iteratorHeader = headerParameters.keySet()
                    .iterator();
            while (iteratorHeader.hasNext()) {
                String key = iteratorHeader.next();
                httpConnection.setRequestProperty(key,
                        headerParameters.get(key));
            }
        }
        httpConnection.setRequestProperty("Content-Type",
                "application/json;charset=" + ENCODING);

        // 设置请求方法
        httpConnection.setRequestMethod(method);
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        // 写query数据流
        if (!(body == null || body.trim().equals(""))) {
            OutputStream writer = httpConnection.getOutputStream();
            try {
                writer.write(body.getBytes(ENCODING));
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

        // 请求结果
        int responseCode = httpConnection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception(responseCode
                    + ":"
                    + inputStream2String(httpConnection.getErrorStream(),
                            ENCODING));
        }

        return httpConnection;
    }

    /**
     * POST请求
     * @param address 请求地址
     * @param headerParameters 参数
     * @param body
     * @return
     * @throws Exception
     */
    public static String post(String address,
            Map<String, String> headerParameters, String body) throws Exception {
    	

        return proxyHttpRequest(address, "POST", null,
                getRequestBody(headerParameters));
    }

    /**
     * GET请求
     * @param address
     * @param headerParameters
     * @param body
     * @return
     * @throws Exception
     */
    public static String get(String address,
            Map<String, String> headerParameters, String body) throws Exception {

        return proxyHttpRequest(address + "?"
                + getRequestBody(headerParameters), "GET", null, null);
    }

    /**
     * 读取网络文件
     * @param address
     * @param headerParameters
     * @param body
     * @param file
     * @return
     * @throws Exception
     */
    public static String getFile(String address,
            Map<String, String> headerParameters, File file) throws Exception {
        String result = "fail";

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, "POST", null,
                    getRequestBody(headerParameters));
            result = readInputStream(httpConnection.getInputStream(), file);

        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }

        return result;
    }

    public static byte[] getFileByte(String address,
            Map<String, String> headerParameters) throws Exception {
        byte[] result = null;

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, "POST", null,
                    getRequestBody(headerParameters));
            result = readInputStreamToByte(httpConnection.getInputStream());

        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }

        return result;
    }

    /**
     * 读取文件流
     * @param in
     * @return
     * @throws Exception
     */
    public static String readInputStream(InputStream in, File file)
            throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }

            out = new FileOutputStream(file);
            out.write(output.toByteArray());

        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return "success";
    }

    public static byte[] readInputStreamToByte(InputStream in) throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;
        byte[] byteFile = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            byteFile = output.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }

        return byteFile;
    }

    /**
     * HTTP请求
     * 
     * @param address
     *            地址
     * @param method
     *            方法
     * @param headerParameters
     *            头信息
     * @param body
     *            请求内容
     * @return
     * @throws Exception
     */
    public static String proxyHttpRequest(String address, String method,
            Map<String, String> headerParameters, String body) throws Exception {
        String result = null;
        HttpURLConnection httpConnection = null;

        try {
            httpConnection = createConnection(address, method,
                    headerParameters, body);

            String encoding = "UTF-8";
            if (httpConnection.getContentType() != null
                    && httpConnection.getContentType().indexOf("charset=") >= 0) {
                encoding = httpConnection.getContentType()
                        .substring(
                                httpConnection.getContentType().indexOf(
                                        "charset=") + 8);
            }
            result = inputStream2String(httpConnection.getInputStream(),
                    encoding);
            // logger.info("HTTPproxy response: {},{}", address,
            // result.toString());

        } catch (Exception e) {
            // logger.info("HTTPproxy error: {}", e.getMessage());
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }

    /**
     * 将参数化为 body
     * @param params
     * @return
     */
    public static String getRequestBody(Map<String, String> params) {
        return getRequestBody(params, true);
    }

    /**
     * 将参数化为 body
     * @param params
     * @return
     */
    public static String getRequestBody(Map<String, String> params,
            boolean urlEncode) {
        StringBuilder body = new StringBuilder();

        Iterator<String> iteratorHeader = params.keySet().iterator();
        body.append("{");
        while (iteratorHeader.hasNext()) {
            String key = iteratorHeader.next();
            String value = params.get(key);

            if (urlEncode) {
//                try {
//                    body.append(key + "=" + URLEncoder.encode(value, ENCODING)+ "&");                	

//                } catch (UnsupportedEncodingException e) {
//                    // e.printStackTrace();
//                }
            	if(value.contains("{")){
            		body.append("\""+key + "\":" + value+",");
            	}else{
            		body.append("\""+key + "\":\"" + value+"\",");
            	}
            } else {
                body.append(key + "=" + value + "&");
            }
        }
        body = new StringBuilder(body.substring(0, body.length() - 1));
        body.append("}");
        if (body.length() == 0) {
            return "";
        }
//        return body.toString();
        return body.toString();
    }

    /**
     * 读取inputStream 到 string
     * @param input
     * @param encoding
     * @return
     * @throws IOException
     */
    private static String inputStream2String(InputStream input, String encoding)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                encoding));
        StringBuilder result = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            result.append(temp);
        }

        return result.toString();

    }


    /**
     * 设置 https 请求
     * @throws Exception
     */
    private static void trustAllHttpsCertificates() throws Exception {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String str, SSLSession session) {
                return true;
            }
        });
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }


    //设置 https 请求证书
    static class miTM implements javax.net.ssl.TrustManager,javax.net.ssl.X509TrustManager {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }


    }

    //====================================================================
    //============================= 测试调用   ============================
    //====================================================================
    public static void main(String[] args) {

            try {
                String address = "https://unp-mail-sit.sit.sf-express.com:40086/unp/notice/single";
                String mobile = "18871082691";
                String gridNo = "1";                    //故障格口号
                String waybillNo = "2018053001";        //订单号
                String tickTime = "2018-05-30 10:57:00";//取件时间
                String pickupCode = "123456";           //取件码
                String validateCode = "QWER";           //用户注册验证码
                //请求参数
                Map<String, String> params = new HashMap<String, String>();
                params.put("userId", mobile);
                params.put("accessId", "mEGeuBri");
                params.put("accessToken", "188e50e3705f40ffb24f88baad2d380a");
                //1.投件时没关格口通知给仓管
//                JSONObject param = new JSONObject();
//                param.put("gridNo", gridNo);
//                params.put("templateParam", param.toJSONString());
//                params.put("templateCode", "TAKEUP_EXCEPTION_CG");
                //2.取件柜快件取出通知给仓管
//                JSONObject param = new JSONObject();
//                param.put("waybillNo", waybillNo);
//                param.put("tickTime", tickTime);
//                params.put("templateParam", param.toJSONString());
//                params.put("templateCode", "TAKEUP_OUT_CG");
                //3.取件柜快件取出通知给用户
//                JSONObject param = new JSONObject();
//                param.put("waybillNo", waybillNo);
//                params.put("templateParam", param.toJSONString());
//                params.put("templateCode", "TAKEUP_OUT_CUS");
                //4.取件柜滞留30小时通知给用户
                JSONObject param = new JSONObject();
                param.put("waybillNo", waybillNo);
                param.put("pickupCode", pickupCode);
                params.put("templateParam", param.toJSONString());
                params.put("templateCode", "TAKEUP_30_HOURS_CUS");
//                //5.取件柜滞留30小时通知
//                JSONObject param = new JSONObject();
//                param.put("waybillNo", waybillNo);
//                params.put("templateParam", param.toJSONString());
//                params.put("templateCode", "TAKEUP_30_HOURS_CG");
//              //6.取件柜滞留24小时通知
//                JSONObject param = new JSONObject();
//                param.put("waybillNo", waybillNo);
//                param.put("pickupCode", pickupCode);
//                params.put("templateParam", param.toJSONString());
//                params.put("templateCode", "TAKEUP_24_HOURS");
//              //7.取件柜客户取件码
//                JSONObject param = new JSONObject();
//                param.put("pickupCode", pickupCode);
//                params.put("templateParam", param.toJSONString());
//                params.put("templateCode", "TAKEUP_PICKUPCODE");
//              //8.取件柜用户注册
//                JSONObject param = new JSONObject();
//                param.put("validateCode", validateCode);
//                params.put("templateParam", param.toJSONString());
//                params.put("templateCode", "TAKEUP_USER_REGISTER");
                // 调用 post 请求
                String res = post(address, params, null);
                System.out.println(res);//打印返回参数

                res = res.substring(res.indexOf("{"));//截取
                JSONObject result = JSONObject.parseObject(res);//转JSON

                System.out.println(result.toString());//打印

            } catch (Exception e) {
                // TODO 异常
                e.printStackTrace();
            }

    }

}