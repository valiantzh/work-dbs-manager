package com.dcdzsoft.email.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;

import com.dcdzsoft.email.EmailAuthenticator;
import com.dcdzsoft.email.EmailInfo;
import com.dcdzsoft.email.EmailSenderProxy;

/**
 * 以HTML格式发送邮件
 * @author zhengxy
 *
 */
public class EmailSenderProxyHTML implements EmailSenderProxy{

	/**  
     * @param mailInfo 待发送的邮件信息   
     */ 
	public Boolean sendEmail(EmailInfo mailInfo) {
        // 判断是否需要身份认证    
        EmailAuthenticator authenticator = null;   
        Properties pro = mailInfo.getProperties();   
        //如果需要身份认证，则创建一个密码验证器     
        if (mailInfo.isValidate()) {    
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
            
            
            //创建邮件的正文。MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
            MimeMultipart mainPart = new MimeMultipart();
            //创建一个包含HTML内容的MimeBodyPart.
            MimeBodyPart html = new MimeBodyPart();
            // setContent(“邮件的正文内容”,”设置邮件内容的编码方式”)
            html.setContent(mailInfo.getContent(),"text/html;charset=utf-8");
            mainPart.addBodyPart(html); 
            
            //正文添加图片
            String[] imgFileNames = mailInfo.getImgFileNames();
            if(imgFileNames!=null){
            	for(int i=0; i< imgFileNames.length; i++){
            		//创建图片
            	    MimeBodyPart img = new MimeBodyPart();
            	    /*JavaMail API不限制信息只为文本,任何形式的信息都可能为MimeMessage的一部分.
            	     * 除了文本信息,作为文件附件包含在电子邮件信息的一部分是很普遍的.
            	     * JavaMail API通过使用DataHandler对象,提供一个允许我们包含非文本BodyPart对象的简便方法.*/
            	    DataHandler dh = new DataHandler(new FileDataSource(imgFileNames[i]));
            	    img.setDataHandler(dh);
            	    //创建图片的一个表示用于显示在邮件中显示
            	    img.setContentID(""+(i));//i+1
            	    
            	    mainPart.addBodyPart(img);
            	}
            	mainPart.setSubType("related");//设置正文与图片之间的关系
            }
            
            //图片与正文的 body
            MimeBodyPart mainBody = new MimeBodyPart();
            mainBody.setContent(mainPart);
            
            //邮件的内容，可包括Html内容和附件
            MimeMultipart contentPart = new MimeMultipart();
            contentPart.addBodyPart(mainBody);
            
            //添加附件
            String[] attchFileNames = mailInfo.getAttachFileNames();
            if(attchFileNames != null){
            	for(String filename : attchFileNames){
            		File attachment = new File(filename);
            		
            		if(!attachment.exists()){
            			//附件不存在，
            			throw new Exception("Attachment file "+filename+" is not exists!!!");
            		}
                	
                    BodyPart attachmentBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(attachment);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    
                    //MimeUtility.encodeWord可以避免文件名乱码
                    attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                    contentPart.addBodyPart(attachmentBodyPart);
            	}
            	
            	contentPart.setSubType("mixed");//设置正文与附件之间的关系
            	
            }
            
            // 将MiniMultipart对象设置为邮件内容    
            mailMessage.setContent(contentPart);
            
            mailMessage.saveChanges(); //保存修改

            // 发送邮件    
            Transport.send(mailMessage);    
            return true;    
        } catch(AddressException e){
        	System.err.println("AddressError:"+e.getMessage());
        	//e.printStackTrace(); 
        	
        } catch (MessagingException e) {  
        	System.err.println("messagingError:"+e.getMessage());
            //e.printStackTrace();    
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
        	System.err.println("UnsupportedEncodingError:"+e.getMessage());
			//e.printStackTrace();
		} catch (Exception e){
			System.err.println("Exception:"+e.getMessage());
		}
        return false;  
	}
	
	
}
