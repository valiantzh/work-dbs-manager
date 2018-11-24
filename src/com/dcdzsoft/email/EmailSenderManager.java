package com.dcdzsoft.email;

 
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.dcdzsoft.EduException;
import com.dcdzsoft.business.GServer;
import com.dcdzsoft.businessproxy.MonitorProxy;
import com.dcdzsoft.client.web.SMWebClientAdapter;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.dto.business.InParamMBSendEmail;
import com.dcdzsoft.dto.function.CMCustomer;
import com.dcdzsoft.dto.function.PMPostman;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.DateUtils;





/**
 * 邮件发送器管理 
 * @author zhengxy
 *
 */
public class EmailSenderManager  {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(EmailSenderManager.class);
	private static final String EMAIL_CHARSET = "utf-8";//UNICODE
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static ControlParam ctrlParam = ControlParam.getInstance();
	
	
	//邮件主题
	private static String RegisterEmailToPMSubject = "邮递员注册邮件";
	private static String RegisterEmailToCMSubject = "用户注册邮件";
	private static String DeliverEmailSubject = "包裹已投递邮件";
	private static String PickupEmailToCMSubject = "用户取件完成邮件";
	private static String PickupEmailToPMSubject = "NEO7-24POST BİLGİLENDİRME MAİLİ";//取件完成发送给投递员的邮件。根据不同国家需求设置；


	//邮件模板
	private Template sendRegisterEmailToPMTemplate = null;
	private Template sendRegisterEmailToCMTemplate = null;
	private Template sendDeliverEmailToPMTemplate = null;
	private Template sendDeliverEmailToCMTemplate = null;
	private Template sendDeliverEmailToCompanyTemplate = null;
	
	private Template sendPickupEmailToCMTemplate = null;
	private Template sendPickupEmailToPMTemplate = null;
	/**
	 * 工作的线程数
	 */
	private int workerCount = 80;
	private EmailThread emailThread;
	private ThreadPoolExecutor executor;
	
	private static boolean isRunning = true;
	/**
     * 私有
     */
	private EmailSenderManager(){
		workerCount = ApplicationConfig.getInstance().getWorkerCount();
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(workerCount);
		
		emailThread = new EmailThread();
		emailThread.start();
		
		loadTemplate();
	}
	private static class SingletonHolder {
		private static final EmailSenderManager instance = new EmailSenderManager();
	}
	
	/**
     * 静态工厂方法，返还此类的惟一实例
     * @return a EmailSenderManager instance
     */
    public static EmailSenderManager getInstance() {
        return SingletonHolder.instance;
    }
    
    protected synchronized static boolean isRunning() {
        return isRunning;
    }
    
    public synchronized void destroy(){
        GServer.println("---- EmailSenderManager.destroy start----");
        isRunning = false;
        if(emailThread!=null){
            emailThread.interrupt();
            emailThread = null;
        }
        
        if(executor != null){
            executor.shutdown();
        }
        GServer.println("---- EmailSenderManager.destroy end ----");
    }
	public void loadTemplate() {
		try {
			//模板按需求自行添加
			
			sendPickupEmailToCMTemplate = Velocity.getTemplate("sendPickupEmailToCM.vm", EMAIL_CHARSET);
			sendPickupEmailToPMTemplate = Velocity.getTemplate("sendPickupEmailToPM.vm", EMAIL_CHARSET);
			sendDeliverEmailToCompanyTemplate = Velocity.getTemplate("sendDeliverEmailToPM.vm", EMAIL_CHARSET);
			
			sendRegisterEmailToPMTemplate = Velocity.getTemplate("sendRegisterEmailToPM.vm", EMAIL_CHARSET);
			sendRegisterEmailToCMTemplate = Velocity.getTemplate("sendRegisterEmailToCM.vm", EMAIL_CHARSET);
			sendDeliverEmailToPMTemplate = Velocity.getTemplate("sendDeliverEmailToPM.vm", EMAIL_CHARSET);
			sendDeliverEmailToCMTemplate = Velocity.getTemplate("sendDeliverEmailToCM.vm", EMAIL_CHARSET);

		} catch (Exception e) {
			log.error("[load vm error]" + e.getMessage());
		}
	}

	
	/**
	 * 启动线程发送邮件
	 * @param mailInfo
	 * @throws EduException
	 */
	private void sentMessage(EmailInfo mailInfo)
			throws Exception {
		executor.submit(new EmailSender(mailInfo));
	}
	
	/**   
      * 以文本格式发送邮件   
      * @param mailInfo 待发送的邮件的信息   
      */    
    public void sendTextMail(EmailInfo mailInfo) throws Exception{
    	mailInfo.setMailServerHost(ctrlParam.getEmailServerHost());    
		mailInfo.setMailServerPort(ctrlParam.getEmailServerPort());    
		mailInfo.setValidate(true);    
		mailInfo.setFromAddress(StringUtils.isEmpty(mailInfo.getFromAddress())?ctrlParam.getEmailFromAddress():mailInfo.getFromAddress());
		mailInfo.setUserName(StringUtils.isEmpty(mailInfo.getUserName())?ctrlParam.getEmailUser():mailInfo.getUserName()); 
		mailInfo.setPassword(StringUtils.isEmpty(mailInfo.getPassword())?ctrlParam.getEmailPwd():mailInfo.getPassword());
		if(StringUtils.isEmpty(mailInfo.getToAddress())){
			mailInfo.setToAddress(ctrlParam.getEmailToAddress());
		}
    	mailInfo.setFormat("text");
    	log.info("EmailInfo[Text]:"+mailInfo.toString());
    	sentMessage(mailInfo);
    }    
       
    /**   
      * 以HTML格式发送邮件   
      * @param mailInfo 待发送的邮件信息   
      */    
    public void sendHtmlMail(EmailInfo mailInfo) throws Exception{   
    	mailInfo.setMailServerHost(ctrlParam.getEmailServerHost());    
		mailInfo.setMailServerPort(ctrlParam.getEmailServerPort());    
		mailInfo.setValidate(true);
		mailInfo.setFromAddress(StringUtils.isEmpty(mailInfo.getFromAddress())?ctrlParam.getEmailFromAddress():mailInfo.getFromAddress());
		mailInfo.setUserName(StringUtils.isEmpty(mailInfo.getUserName())?ctrlParam.getEmailUser():mailInfo.getUserName()); 
		mailInfo.setPassword(StringUtils.isEmpty(mailInfo.getPassword())?ctrlParam.getEmailPwd():mailInfo.getPassword());
		if(StringUtils.isEmpty(mailInfo.getToAddress())){
			mailInfo.setToAddress(ctrlParam.getEmailToAddress());
		}
    	mailInfo.setFormat("html");
    	log.info("EmailInfo[Html]:"+mailInfo.toString());
    	sentMessage(mailInfo);
    }  
    //发送投递邮件
    /**
     * 
     * @param smsInfo
     * @param flag("ToCM","ToPM","ToCompany")
     * @throws EduException
     */
    public void sendDeliverEmail(SMSInfo smsInfo,String flag)throws EduException{
    	
    	VelocityContext context = new VelocityContext();
		context.put("postmanname", smsInfo.PostmanName);
		context.put("packageid", smsInfo.PackageID);
		context.put("terminalno", smsInfo.TerminalNo);
		context.put("location", smsInfo.Location);
		context.put("storedtime", DateUtils.timestampToString(smsInfo.StoredTime));
		if(StringUtils.isEmpty(smsInfo.PostmanMobile)){
			smsInfo.PostmanMobile = ctrlParam.getMsgTel();
		}
		context.put("postmanmobile", smsInfo.PostmanMobile);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";
		EmailSenderManager sender = EmailSenderManager.getInstance();
		EmailInfo mailInfo = new EmailInfo();
		try {
			String pickupEmailToCMSubject = new String(PickupEmailToCMSubject.getBytes("utf-8"),"utf-8");
			String pickupEmailToPMSubject = new String(PickupEmailToPMSubject.getBytes("utf-8"),"utf-8");

			// 将环境数据转化输出
			if("ToPM".equals(flag)){
				sendDeliverEmailToPMTemplate.merge(context, writer);
			}else if("ToCM".equals(flag)){
				sendDeliverEmailToCMTemplate.merge(context, writer);
			}else if("ToCompany".equals(flag)){				
				sendDeliverEmailToCompanyTemplate.merge(context, writer);
			}
			msgContent = writer.toString();
			msgContent = new String(msgContent.getBytes("utf-8"),"utf-8");
			log.info("发送投递邮件："+msgContent);
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_EMAILVMERROR);
		}
		mailInfo.setWaterID(smsInfo.WaterID);
		mailInfo.setSubject(DeliverEmailSubject);
		mailInfo.setContent(msgContent);
		mailInfo.setToAddress(smsInfo.Email);
		mailInfo.setFromAddress(smsInfo.FromEamilAddress);
		mailInfo.setUserName(smsInfo.UserName);
		mailInfo.setPassword(smsInfo.EmailPwd);
	    try {
	    	sender.sendHtmlMail(mailInfo);
		} catch (Exception e) {
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDEMAILFAIL);
		}
    }
    //发送取件邮件
    /**
     * 
     * @param smsInfo
     * @param flag("ToCM","ToPM","ToCompany")
     * @throws EduException
     */
    public void sendPickupEmail(SMSInfo smsInfo,String flag)throws EduException{
    	
    	VelocityContext context = new VelocityContext();
		context.put("terminalno", smsInfo.TerminalNo);
		context.put("packageid", smsInfo.PackageID);
		context.put("location", smsInfo.Location);
		context.put("takedtime", DateUtils.timestampToString(smsInfo.TakedTime));
		context.put("postmanmobile", smsInfo.PostmanMobile);
		context.put("msgtel",ctrlParam.msgTel);
		context.put("postmanname",smsInfo.PostmanName);
		context.put("customermobile",smsInfo.CustomerMobile);
		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		EmailSenderManager sender = EmailSenderManager.getInstance();
		EmailInfo mailInfo = new EmailInfo();
		try {
			String pickupEmailToCMSubject = new String(PickupEmailToCMSubject.getBytes("utf-8"),"utf-8");
			String pickupEmailToPMSubject = new String(PickupEmailToPMSubject.getBytes("utf-8"),"utf-8");
			// 将环境数据转化输出
			if("ToCM".equals(flag)){
				mailInfo.setSubject(PickupEmailToCMSubject);
				sendPickupEmailToCMTemplate.merge(context, writer);
			}else if("ToPM".equals(flag)){
				mailInfo.setSubject(pickupEmailToPMSubject);
				sendPickupEmailToPMTemplate.merge(context, writer);
			}else if("ToCompany".equals(flag)){				    //发送取件邮件给投递公司
				mailInfo.setSubject(pickupEmailToPMSubject);
				sendPickupEmailToPMTemplate.merge(context, writer);
			}		
			msgContent = writer.toString();	
			msgContent = new String(msgContent.getBytes("utf-8"),"utf-8");
			log.info("发送取件邮件："+msgContent);
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_EMAILVMERROR);
		}
		mailInfo.setWaterID(smsInfo.WaterID);
//		mailInfo.setSubject(PickupEmailToCMSubject);
		mailInfo.setContent(msgContent);
		mailInfo.setToAddress(smsInfo.Email);
		mailInfo.setFromAddress(smsInfo.FromEamilAddress);
		mailInfo.setUserName(smsInfo.UserName);
		mailInfo.setPassword(smsInfo.EmailPwd);
	    try {
	    	sender.sendHtmlMail(mailInfo);
		} catch (Exception e) {
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDEMAILFAIL);
		}
    }
 
    //发送注册邮件给投递员
    public void sendRegisterEmailToPM(SMSInfo smsInfo)throws EduException{
    	
    	VelocityContext context = new VelocityContext();
		context.put("postmanname", smsInfo.PostmanName);
		context.put("postmanid", smsInfo.PostmanID);
		context.put("pwd", smsInfo.PostmanPwd);
		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";
		try {
			// 将环境数据转化输出
			sendPickupEmailToCMTemplate.merge(context, writer);			
			msgContent = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_EMAILVMERROR);
		}
		EmailSenderManager sender = EmailSenderManager.getInstance();
		EmailInfo mailInfo = new EmailInfo();
		mailInfo.setSubject(RegisterEmailToPMSubject);
		mailInfo.setContent(msgContent);
		mailInfo.setToAddress(smsInfo.Email);
		mailInfo.setFromAddress(smsInfo.FromEamilAddress);
		mailInfo.setUserName(smsInfo.UserName);
		mailInfo.setPassword(smsInfo.EmailPwd);
	    try {
	    	sender.sendHtmlMail(mailInfo);
		} catch (Exception e) {
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDEMAILFAIL);
		}
    }
    //发送注册邮件给用户
    public void sendRegisterEmailToCM(CMCustomer customer)throws EduException{
    	
    	VelocityContext context = new VelocityContext();
		context.put("customerid", customer.CustomerID);
		context.put("customername", customer.CustomerName);
		context.put("pwd", customer.PlainPassword);
		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";
		try {
			// 将环境数据转化输出
			sendPickupEmailToCMTemplate.merge(context, writer);			
			msgContent = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_EMAILVMERROR);
		}
		EmailSenderManager sender = EmailSenderManager.getInstance();
		EmailInfo mailInfo = new EmailInfo();
		mailInfo.setSubject(PickupEmailToCMSubject);
		mailInfo.setContent(msgContent);
		mailInfo.setToAddress(customer.Email);
	    try {
	    	sender.sendHtmlMail(mailInfo);
		} catch (Exception e) {
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDEMAILFAIL);
		}
    }
    /**
	 * 发送设备离线或故障邮件线程
	 * <p>Title: 智能自助包裹柜系统</p>
	 *
	 * <p>Description: </p>
	 *
	 * <p>Copyright: Copyright (c) 2014</p>
	 *
	 * <p>Company: 杭州东城电子有限公司</p>
	 *
	 * @author wangzl
	 * @version 1.0
	 */
    private class EmailThread extends Thread
	{
        public void run() 
		{
            
            try {
                Thread.sleep(1000*40);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            GServer.println(" ----------- EmailThread start -----------"+isRunning());
        	while(isRunning())
			{
        		try
        		{

					GServer.println(" send email begin --------------");
					if("1".equalsIgnoreCase(ctrlParam.sendAlertEmail))
					{					   		                
						InParamMBSendEmail inParam = new InParamMBSendEmail();
		                MonitorProxy.doBusiness(inParam); 
					}
	                GServer.println("send email end --------------");					
				
        		}catch(Exception e){
        			GServer.println("[SendEmailThread error] = " + e.getMessage());
        		}
        		try
				{
					Thread.sleep(1000*60*15); //休眠15分钟
				}catch(InterruptedException ex)
				{
					
				}
			}
        	GServer.println(" ----------- EmailThread end -----------"+isRunning());
		}
	}
    
 
   
}   