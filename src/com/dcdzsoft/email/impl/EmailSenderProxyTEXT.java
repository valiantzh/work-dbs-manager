package com.dcdzsoft.email.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;

import com.dcdzsoft.email.EmailAuthenticator;
import com.dcdzsoft.email.EmailInfo;
import com.dcdzsoft.email.EmailSenderProxy;

/**
 * 以文本格式发送邮件   
 * @author zhengxy
 *
 */
public class EmailSenderProxyTEXT implements EmailSenderProxy{
	
	/**   
     * @param mailInfo 待发送的邮件信息   
     */ 

	public Boolean sendEmail(EmailInfo mailInfo){
		//System.out.println("EmailSenderProxyTEXT===>"+mailInfo.toString());
		// 判断是否需要身份认证    
        EmailAuthenticator authenticator = null;    
        Properties pro = mailInfo.getProperties();   
        if (mailInfo.isValidate()) {    
        // 如果需要身份认证，则创建一个密码验证器    
            authenticator = new EmailAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
        }   
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
        Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
        try {
        	
            // 根据session创建一个邮件消息    
            Message mailMessage = new MimeMessage(sendMailSession);    
            // 创建邮件发送者地址    
            Address from = new InternetAddress(mailInfo.getFromAddress());    
            // 设置邮件消息的发送者    
            mailMessage.setFrom(from);    
            // 创建邮件的接收者地址，并设置到邮件消息中    
            String toEmail = mailInfo.getToAddress();
            if(StringUtils.isNotEmpty(toEmail)){
                String[] toList = toEmail.split(";");
                if(toList.length==1){
                    Address to = new InternetAddress(mailInfo.getToAddress());    
                    // Message.RecipientType.TO属性表示接收者的类型为TO    
                    mailMessage.setRecipient(Message.RecipientType.TO,to);  
                }else if(toList.length>1){
                    Address[]  addressTO = new InternetAddress[toList.length]; //收件人  
                    
                    for (int i = 0; i < toList.length; i++) {  
                        addressTO[i] = new InternetAddress(toList[i]);  
                    }  
                    mailMessage.setRecipients(Message.RecipientType.TO,addressTO);//设置收件人信息
                }else{
                    throw new AddressException("To Email Address Empty!");
                }
            }else{
                throw new AddressException("To Email Address Empty!");
            }
            // 设置邮件消息的主题    
            mailMessage.setSubject(mailInfo.getSubject());    
            // 设置邮件消息发送的时间    
            mailMessage.setSentDate(new Date());    
            
            // 设置邮件消息的主要内容    
            String mailContent = mailInfo.getContent();    
            mailMessage.setText(mailContent);    

            // 发送邮件    
            Transport.send(mailMessage);   
            
            return true;    
        } catch(AddressException e){
        	System.err.println("AddressError:"+e.getMessage());
        	//e.printStackTrace(); 
        	
        } catch (MessagingException e) {  
        	System.err.println("messagingError:"+e.getMessage());
            //e.printStackTrace();    
        }    
        return false; 
	}
}
