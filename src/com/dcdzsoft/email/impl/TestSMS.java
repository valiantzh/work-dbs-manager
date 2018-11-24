package com.dcdzsoft.email.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.email.EmailInfo;
import com.dcdzsoft.email.EmailSenderProxy;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.RandUtils;

public class TestSMS {
   
    public static void main(String[] args) throws EduException {
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setFormat("HTML");
        emailInfo.setMailServerHost("mail.neo7-24post.com");
        emailInfo.setMailServerPort("587");
        emailInfo.setUserName("mng@neo7-24post.com");
        emailInfo.setPassword("Av9QThafp2");
        emailInfo.setFromAddress("mng@neo7-24post.com");
        emailInfo.setToAddress("chenyufeng@hzdongcheng.cn");
        emailInfo.setSubject("NEO7-24POST BİLGİLENDİRME MAİLİ");
        emailInfo.setContent("Barkod Numarası:"+1+"\n"
        		+"Telefon:"+2+"\n"
        		+2+" to recieved by "+3+"\n"
        		+"Destination adres:"+7+"\n");
        EmailSenderProxy smsSender = new EmailSenderProxyTEXT();
        Boolean result;
        result = smsSender.sendEmail(emailInfo);

        System.out.println("result="+result);
        Date sysdate = new Date(System.currentTimeMillis());
        System.out.println(sysdate.toString());
        
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");//小写的mm表示的是分钟  
        String dstr="06:00:00";  
        java.util.Date date = null ;
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        System.out.println(date.toString());
        
    }

}
