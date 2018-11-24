package com.dcdzsoft.email;

import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class EmailInfo {
    // 发送邮件的服务器的IP和端口    
    private String mailServerHost = "";    
    private String mailServerPort = "25";    
    // 邮件发送者的地址    
    private String fromAddress = "";    
    // 邮件接收者的地址    
    private String toAddress = "";    
    // 登陆邮件发送服务器的用户名和密码    
    private String userName = "";    
    private String password = "";    
    // 是否需要身份验证    
    private boolean validate = true;    
    // 邮件主题    
    private String subject = "";    
    // 邮件的文本内容    
    private String content = "";    
    // 邮件附件的文件名    
    private String[] attachFileNames = null;
    //邮件图片文件名称（显示在邮件正文中）
    private String[]  imgFileNames = null;
    //邮件格式：text,html..
    private String format;
    
    //修改信息发送状态
    private Long WaterID = 0L;
    /**   
     * 获得邮件会话属性   
     */    
    
   public Properties getProperties(){    
     Properties p = new Properties();    
     p.put("mail.smtp.host", this.mailServerHost);    
     p.put("mail.smtp.port", this.mailServerPort);    
     p.put("mail.smtp.auth", validate ? "true" : "false");    
     return p;    
   }    
public String getMailServerHost() {    
     return mailServerHost;    
   }    
   public void setMailServerHost(String mailServerHost) {    
     this.mailServerHost = mailServerHost;    
   }   
   public String getMailServerPort() {    
     return mailServerPort;    
   }   
   public void setMailServerPort(String mailServerPort) {    
     this.mailServerPort = mailServerPort;    
   }   
   public boolean isValidate() {    
     return validate;    
   }   
   public void setValidate(boolean validate) {    
     this.validate = validate;    
   }   
   public String[] getAttachFileNames() {    
     return attachFileNames;    
   }   
   public void setAttachFileNames(String[] fileNames) {    
     this.attachFileNames = fileNames;    
   }   
   public String[] getImgFileNames() {
     return imgFileNames;
   }
   public void setImgFileNames(String[] imgFileNames) {
     this.imgFileNames = imgFileNames;
   } 
   public String getFromAddress() {    
     return fromAddress;    
   }    
   public void setFromAddress(String fromAddress) {    
     this.fromAddress = fromAddress;    
   }   
   public String getPassword() {    
     return password;    
   }   
   public void setPassword(String password) {    
     this.password = password;    
   }   
   public String getToAddress() {    
     return toAddress;    
   }    
   public void setToAddress(String toAddress) {    
     this.toAddress = toAddress;    
   }    
   public String getUserName() {    
     return userName;    
   }   
   public void setUserName(String userName) {    
     this.userName = userName;    
   }   
   public String getSubject() {    
     return subject;    
   }   
   public void setSubject(String subject) {    
     this.subject = subject;    
   }   
   public String getContent() {    
     return content;    
   }   
   public void setContent(String textContent) {    
     this.content = textContent;    
   }
   /**
    * @return the format
    */
   public String getFormat() {
        if(StringUtils.isEmpty(this.format)){
        	this.format = "html";//默认HTML格式
        }
     return format;
   }
   /**
    * @param format the format to set
    */
   public void setFormat(String format) {
	this.format = format;
   }
   
	public Long getWaterID() {
	return WaterID;
}
public void setWaterID(Long waterID) {
	WaterID = waterID;
}
	@Override
	public String toString() {
		return "EmailInfo [mailServerHost=" + mailServerHost
				+ ", mailServerPort=" + mailServerPort + ", fromAddress="
				+ fromAddress + ", toAddress=" + toAddress + ", userName="
				+ userName + ", password=" + password + ", validate="
				+ validate + ", subject=" + subject + ", content=" + content
				+ ", attachFileNames=" + Arrays.toString(attachFileNames)
				+ ", imgFileNames=" + Arrays.toString(imgFileNames)
				+ ", format=" + format + "]";
	}  
   
}
