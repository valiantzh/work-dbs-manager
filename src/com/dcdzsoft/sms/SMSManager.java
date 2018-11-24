package com.dcdzsoft.sms;

import java.io.StringWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.sql.RowSet;

import org.apache.commons.logging.Log;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.dcdzsoft.EduException;
import com.dcdzsoft.business.GServer;
import com.dcdzsoft.businessproxy.MonitorProxy;
import com.dcdzsoft.client.web.PMWebClientAdapter;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.Constant;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.constant.SysDict;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.sda.db.RowSetUtils;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.NumberUtils;
import com.dcdzsoft.util.StringUtils;

public class SMSManager {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(SMSManager.class);

	private static final String SMS_CHARSET = "utf-8";// UNICODE
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static ControlParam ctrlParam = ControlParam.getInstance();

	private Template shortMsgTemplate = null;
	private Template expiredMsgTemplate = null;
	private Template postmanPwdTemplate = null;
	private Template reminderTemplate = null;
	private Template urgentSMSTemplate = null;
	private Template checkCodeTemplate = null;
	private Template pickupCodeTemplate = null;
	
	private Template owingReportMsgTemplate = null;// 提醒通知-按量计费
	private Template owingReportMsg2Template = null;// 提醒通知-包月 或包年
	private Template owingReportMsg3Template = null;// 欠费停用通知

	private Template expiredMsgToPostmanTemplate = null;
	private Template shortMsgToPostmanTemplate = null;// 投递完成发送短信给投递员（和投递公司一样）
	private Template pickupMsgToPostmanTemplate = null;// 取件完成发送短信给投递员（和投递公司一样）

	/**
	 * 工作的线程数
	 */
	private int workerCount = 100;

	private ThreadPoolExecutor executor;
	private ReminderThread reminderThread;
	private SMSRunnerThread smsRunnerThread;
	private static boolean isRunning = true;
	
	/**
	 * 私有默认构造函数
	 */
	private SMSManager() {
		workerCount = ApplicationConfig.getInstance().getWorkerCount();
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(workerCount);

		reminderThread = new ReminderThread();
		reminderThread.start();

		smsRunnerThread = new SMSRunnerThread();
		smsRunnerThread.start();
		loadTemplate();
	}

	private static class SingletonHolder {
		private static final SMSManager instance = new SMSManager();
	}

	/**
	 * 静态工厂方法，返还此类的惟一实例
	 * 
	 * @return a SMSManager instance
	 */
	public static SMSManager getInstance() {
		return SingletonHolder.instance;
	}
	
	protected synchronized static boolean isRunning() {
        return isRunning;
    }
	
	public synchronized void destroy(){
	    GServer.println("---- SMSManager.destroy start----"+isRunning);
	    isRunning = false;
	    if(reminderThread!=null){
	        reminderThread.interrupt();
	        reminderThread = null;
	    }
	    
	    if(smsRunnerThread == null){
	        smsRunnerThread.interrupt();
	        smsRunnerThread = null;
	    }
	    
	    if(executor != null){
	        executor.shutdown();
	    }
	    GServer.println("---- SMSManager.destroy end ----"+isRunning);
	}
	public void loadTemplate() {
		String charset = apCfg.getSmsCharset();
		if (StringUtils.isEmpty(charset)) {
			charset = SMS_CHARSET;
		}
		try {
			shortMsgTemplate = Velocity.getTemplate("shortMsg.vm", charset);
			expiredMsgTemplate = Velocity.getTemplate("expiredMsg.vm", charset);
			
			postmanPwdTemplate = Velocity.getTemplate("postmanPwd.vm", charset);
			reminderTemplate = Velocity.getTemplate("reminderMsg.vm", charset);
			try {
			    expiredMsgToPostmanTemplate = Velocity.getTemplate("expiredMsgToPostman.vm", charset);
				shortMsgToPostmanTemplate = Velocity.getTemplate("shortMsgToPostman.vm", charset);
				pickupMsgToPostmanTemplate = Velocity.getTemplate("pickupMsgToPostman.vm", charset);
			} catch (Exception e1) {
			}
			try {
				urgentSMSTemplate = Velocity.getTemplate("urgentPwd.vm",charset);
			} catch (Exception e1) {
			}
			try {
				checkCodeTemplate = Velocity.getTemplate("checkCode.vm",charset);
			} catch (Exception e1) {
			}
			try {
				pickupCodeTemplate = Velocity.getTemplate("pickupMsg.vm",charset);
			} catch (Exception e1) {
			}

			try {
				owingReportMsgTemplate = Velocity.getTemplate("owingSmsMsg.vm","utf-8");
				owingReportMsg2Template = Velocity.getTemplate("owingSmsMsg2.vm", "utf-8");
				owingReportMsg3Template = Velocity.getTemplate("owingSmsMsg3.vm", "utf-8");
			} catch (Exception e1) {
			}

		} catch (Exception e) {
			log.error("[load vm error]" + e.getMessage());
		}
	}

	/**
	 * 发送投递短消息
	 * 
	 * @param smsInfo
	 * @throws EduException
	 */
	public void sentDeliverySMS(SMSInfo smsInfo) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("packageid", smsInfo.PackageID);
		context.put("terminalno", smsInfo.TerminalNo);
		context.put("boxno", smsInfo.BoxNo);
		context.put("location", smsInfo.Location);
		context.put("terminalName", smsInfo.TerminalName);
		context.put("pwd", smsInfo.OpenBoxKey);
		context.put("msgtel", ctrlParam.msgTel);
		context.put("manmobile", smsInfo.PostmanMobile);
		context.put("logisticsName", smsInfo.LogisticsName);
		context.put("remark", smsInfo.Remark);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			shortMsgTemplate.merge(context, writer);

			msgContent = writer.toString();
			log.info("发送投递短消息：" + msgContent);
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}

		smsInfo.MsgContent = msgContent;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_DELIVERY;
		smsInfo.msgTel = ctrlParam.msgTel;
		sentMessage(smsInfo);
	}

	/**
	 * 发送投递短消息给投递员
	 * 
	 * @param smsInfo
	 * @throws EduException
	 */
	public void sentDeliverySMSToPostman(SMSInfo smsInfo) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("packageid", smsInfo.PackageID);
		// context.put("boxno", smsInfo.BoxNo);
		context.put("postmanname", smsInfo.PostmanName);
		context.put("customermobile", smsInfo.CustomerMobile);
		context.put("storedtime", smsInfo.StoredTime);
		context.put("takedtime", smsInfo.TakedTime);
		context.put("location", smsInfo.Location);

		context.put("remark", smsInfo.Remark);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			shortMsgToPostmanTemplate.merge(context, writer);

			msgContent = writer.toString();
			log.info("发送投递短消息给投递员：" + msgContent);
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}

		smsInfo.MsgContent = msgContent;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_DELIVERYTOPOSTMAN;// 类型在发送短信的具体的方法中需要判断，确定发送的手机号
		smsInfo.msgTel = ctrlParam.msgTel;
		sentMessage(smsInfo);
	}

	/**
	 * 发送投递短消息给投递公司
	 * 
	 * @param smsInfo
	 * @throws EduException
	 */
	public void sentDeliverySMSToCompany(SMSInfo smsInfo) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("packageid", smsInfo.PackageID);
		// context.put("boxno", smsInfo.BoxNo);
		context.put("postmanname", smsInfo.PostmanName);
		context.put("customermobile", smsInfo.CustomerMobile);
		context.put("storedtime", smsInfo.StoredTime);
		context.put("location", smsInfo.Location);

		context.put("remark", smsInfo.Remark);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			shortMsgToPostmanTemplate.merge(context, writer);

			msgContent = writer.toString();
			log.info("发送投递短消息给投递公司：" + msgContent);
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}

		smsInfo.MsgContent = msgContent;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_DELIVERYTOCOMPANY;// 类型在发送短信的具体的方法中需要判断，确定发送的手机号
		smsInfo.msgTel = ctrlParam.msgTel;
		sentMessage(smsInfo);
	}

	/**
	 * 发送逾期短消息
	 * 
	 * @param terminalName
	 * @param location
	 * @param packageid
	 * @param pwd
	 * @param mobile
	 * @throws EduException
	 */
	public void sentExpiredSMS(SMSInfo smsInfo) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("packageid", smsInfo.PackageID);
		context.put("location", smsInfo.Location);
		context.put("terminalName", smsInfo.TerminalName);
		context.put("msgtel", ctrlParam.msgTel);
		context.put("manmobile", smsInfo.PostmanMobile);
		context.put("logisticsName", smsInfo.LogisticsName);
		context.put("openboxkey", smsInfo.OpenBoxKey);
		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			expiredMsgTemplate.merge(context, writer);

			msgContent = writer.toString();
			smsInfo.MsgContent = msgContent;
			if(smsInfo.MsgType == 0){
			    smsInfo.MsgType = SMSInfo.MSG_TYPE_EXPIRED;
			}
			
			smsInfo.msgTel = ctrlParam.msgTel;
			sentMessage(smsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			GServer.println("发送逾期短消息异常："+ ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
	}

	/**
	 * 发送逾期短消息给投递员
	 * @param smsInfo
	 * @throws EduException
	 */
	public void sentExpiredSMSToPostman(SMSInfo smsInfo) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("packageid", smsInfo.PackageID);
		context.put("location", smsInfo.Location);
		context.put("terminalName", smsInfo.TerminalName);
		context.put("msgtel", ctrlParam.msgTel);
		context.put("manmobile", smsInfo.PostmanMobile);
		context.put("logisticsName", smsInfo.LogisticsName);
		context.put("openboxkey", smsInfo.OpenBoxKey);
		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			expiredMsgToPostmanTemplate.merge(context, writer);

			msgContent = writer.toString();
			smsInfo.CustomerMobile = smsInfo.PostmanMobile;
			smsInfo.MsgContent = msgContent;
			if(smsInfo.MsgType == 0){
			    smsInfo.MsgType = SMSInfo.MSG_TYPE_EXPIREDTOPOSTMAN;
			}
			smsInfo.msgTel = ctrlParam.msgTel;
			sentMessage(smsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			GServer.println("发送逾期短消息给投递员异常："+ ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
	}
	
	/**
     * 发送逾期短消息给投递公司
     * @param smsInfo
     * @throws EduException
     */
    public void sentExpiredSMSToCompany(SMSInfo smsInfo) throws EduException {
        VelocityContext context = new VelocityContext();
        context.put("packageid", smsInfo.PackageID);
        context.put("location", smsInfo.Location);
        context.put("terminalName", smsInfo.TerminalName);
        context.put("msgtel", ctrlParam.msgTel);
        context.put("manmobile", smsInfo.PostmanMobile);
        context.put("logisticsName", smsInfo.LogisticsName);
        context.put("openboxkey", smsInfo.OpenBoxKey);
        // 设置输出
        StringWriter writer = new StringWriter();
        String msgContent = "";

        try {
            // 将环境数据转化输出
            expiredMsgToPostmanTemplate.merge(context, writer);

            msgContent = writer.toString();
            smsInfo.CustomerMobile = smsInfo.PostmanMobile;
            smsInfo.MsgContent = msgContent;
            if(smsInfo.MsgType == 0){
                smsInfo.MsgType = SMSInfo.MSG_TYPE_EXPIREDTOCOMPANY;
            }
            smsInfo.msgTel = ctrlParam.msgTel;
            sentMessage(smsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            GServer.println("发送逾期短消息给投递员异常："+ ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
        }
    }

	/**
	 * 发送取件短消息
	 * 
	 * @param terminalName
	 * @param location
	 * @param packageid
	 * @param pwd
	 * @param mobile
	 * @throws EduException
	 */
	public void sentPickupSMS(SMSInfo smsInfo) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("packageid", smsInfo.PackageID);
		context.put("msgtel", ctrlParam.msgTel);
		context.put("manmobile", smsInfo.PostmanMobile);
		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			pickupCodeTemplate.merge(context, writer);

			msgContent = writer.toString();
			log.info("发送取件短消息：" + msgContent);
			smsInfo.MsgContent = msgContent;
			smsInfo.MsgType = SMSInfo.MSG_TYPE_TAKEDOUT;
			smsInfo.msgTel = ctrlParam.msgTel;
			sentMessage(smsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			GServer.println("发送取件短消息异常："+ ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
	}

	/**
	 * 发送取件短消息给投递员
	 * 
	 * @param terminalName
	 * @param location
	 * @param packageid
	 * @param pwd
	 * @param mobile
	 * @throws EduException
	 */
	public void sentPickupSMSToPostman(SMSInfo smsInfo) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("terminalno", smsInfo.TerminalNo);
		context.put("packageid", smsInfo.PackageID);
		context.put("location", smsInfo.Location);
		context.put("takedtime", DateUtils.timestampToString(smsInfo.TakedTime));
		context.put("postmanmobile", smsInfo.PostmanMobile);
		context.put("msgtel", ctrlParam.msgTel);
		context.put("postmanname", smsInfo.PostmanName);
		context.put("customermobile", smsInfo.CustomerMobile);
		context.put("logisticsName", smsInfo.LogisticsName);
		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			pickupMsgToPostmanTemplate.merge(context, writer);

			msgContent = writer.toString();
			log.info("发送取件短消息给投递员：" + msgContent);
			smsInfo.MsgContent = msgContent;
			smsInfo.MsgType = SMSInfo.MSG_TYPE_TAKEDOUTTOPOSTMAN;
			smsInfo.msgTel = ctrlParam.msgTel;
			sentMessage(smsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			GServer.println("发送取件短消息给投递员异常："+ ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
	}

	/**
	 * 发送取件短消息给投递公司
	 * 
	 * @param terminalName
	 * @param location
	 * @param packageid
	 * @param pwd
	 * @param mobile
	 * @throws EduException
	 */
	public void sentPickupSMSToCompany(SMSInfo smsInfo) {
		VelocityContext context = new VelocityContext();
		context.put("terminalno", smsInfo.TerminalNo);
		context.put("packageid", smsInfo.PackageID);
		context.put("location", smsInfo.Location);
		context.put("takedtime", DateUtils.timestampToString(smsInfo.TakedTime));
		context.put("postmanmobile", smsInfo.PostmanMobile);
		context.put("msgtel", ctrlParam.msgTel);
		context.put("postmanname", smsInfo.PostmanName);
		context.put("customermobile", smsInfo.CustomerMobile);
		context.put("logisticsName", smsInfo.LogisticsName);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			pickupMsgToPostmanTemplate.merge(context, writer);

			msgContent = writer.toString();
			log.info("发送取件短消息给投递公司：" + msgContent);
			smsInfo.MsgContent = msgContent;
			smsInfo.MsgType = SMSInfo.MSG_TYPE_TAKEDOUTTOCOMPANY;
			smsInfo.msgTel = ctrlParam.msgTel;
			sentMessage(smsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			GServer.println("发送取件短消息给投递公司异常："+ ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
	}

	/**
	 * 发送催领短消息
	 * 
	 * @param smsInfo
	 * @throws EduException
	 */
	public void sentReminderSMS(SMSInfo smsInfo) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("packageid", smsInfo.PackageID);
		context.put("location", smsInfo.Location);
		context.put("terminalName", smsInfo.TerminalName);
		context.put("pwd", smsInfo.OpenBoxKey);
		context.put("msgtel", ctrlParam.msgTel);
		context.put("logisticsName", smsInfo.LogisticsName);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			reminderTemplate.merge(context, writer);

			msgContent = writer.toString();
			smsInfo.MsgContent = msgContent;
			smsInfo.MsgType = SMSInfo.MSG_TYPE_REMINDER;
			smsInfo.msgTel = ctrlParam.msgTel;
			sentMessage(smsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			GServer.println("发送催领短消息异常："+ ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
	}

	/**
	 * 发送投递员注册短消息
	 * 
	 * @param deparID
	 *            投递所属运营网点编号
	 * @param pwd
	 * @param mobile
	 * @throws EduException
	 */
	public void sentPostManPwd(String deparID, String pwd, String mobile)
			throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("pwd", pwd);
		context.put("msgtel", ctrlParam.msgTel);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			postmanPwdTemplate.merge(context, writer);

			msgContent = writer.toString();

			SMSInfo smsInfo = new SMSInfo();
			smsInfo.MsgContent = msgContent;
			smsInfo.CustomerMobile = mobile;
			smsInfo.DynamicCode = pwd;
			smsInfo.DepartmentID = deparID;
			smsInfo.MsgType = SMSInfo.MSG_TYPE_REGISTER;
			smsInfo.msgTel = ctrlParam.msgTel;
			sentMessage(smsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			GServer.println("发送投递员注册短消息异常："+ ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}

	}

	/**
	 * 发送紧急取件短信
	 * 
	 * @param deparID
	 *            包裹属运营网点编号
	 * @param pwd
	 * @param mobile
	 * @throws EduException
	 */
	public void sentUrgentSMS(String deparID, String pwd,
			String customerMobile, String urgentMobile) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("pwd", pwd);
		context.put("mobile", customerMobile);
		context.put("msgtel", ctrlParam.msgTel);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			urgentSMSTemplate.merge(context, writer);

			msgContent = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}

		// GServer.println(msgContent);

		SMSInfo smsInfo = new SMSInfo();
		smsInfo.MsgContent = msgContent;
		smsInfo.CustomerMobile = urgentMobile;
		smsInfo.DepartmentID = deparID;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_SENDURGENTSMS;

		sentMessage(smsInfo);
	}

	/**
	 * 发送投递员注册验证码消息
	 * 
	 * @param deparID
	 *            投递所属运营网点编号
	 * @param checkCode
	 * @param mobile
	 * @throws EduException
	 */
	public void sentCheckCode(String deparID, String checkCode, String mobile)
			throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("checkcode", checkCode);
		context.put("msgtel", ctrlParam.msgTel);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			checkCodeTemplate.merge(context, writer);

			msgContent = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}

		// GServer.println(msgContent);

		SMSInfo smsInfo = new SMSInfo();
		smsInfo.MsgContent = msgContent;
		smsInfo.CustomerMobile = mobile;
		smsInfo.DepartmentID = deparID;
		smsInfo.DynamicCode = checkCode;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_CHECKCODE;

		sentMessage(smsInfo);
	}

	/**
	 * 发送短信欠费通知消息
	 * 
	 * @param deparID
	 *            所属运营网点编号
	 * @param accountID
	 * @param smsNum
	 * @param mobile
	 * @throws EduException
	 */
	public void sentSMSOwingReport(String deparID, String accountID,
			int smsNum, String mobile) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("id", accountID);
		context.put("smsNum", smsNum);
		context.put("msgtel", ctrlParam.msgTel);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			owingReportMsgTemplate.merge(context, writer);

			msgContent = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}

		// GServer.println(msgContent);

		SMSInfo smsInfo = new SMSInfo();
		smsInfo.MsgContent = msgContent;
		smsInfo.CustomerMobile = mobile;
		smsInfo.DepartmentID = deparID;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_SMSOWING;

		sentMessage(smsInfo);
	}

	/**
	 * 发送短信欠费通知消息(包月或包年)
	 * 
	 * @param deparID
	 *            所属运营网点编号
	 * @param accountID
	 * @param vaildDate
	 * @param mobile
	 * @throws EduException
	 */
	public void sentSMSOwingReport(String deparID, String accountID,
			java.sql.Date vaildDate, String mobile) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("id", accountID);
		context.put("ValidDate", Constant.dateFromat.format(vaildDate));
		context.put("msgtel", ctrlParam.msgTel);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			owingReportMsg2Template.merge(context, writer);

			msgContent = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}

		// GServer.println(msgContent);

		SMSInfo smsInfo = new SMSInfo();
		smsInfo.MsgContent = msgContent;
		smsInfo.CustomerMobile = mobile;
		smsInfo.DepartmentID = deparID;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_SMSOWING;

		sentMessage(smsInfo);
	}

	/**
	 * 发送短信欠费通知消息(已欠费停用)
	 * 
	 * @param deparID
	 *            所属运营网点编号
	 * @param accountID
	 * @param mobile
	 * @throws EduException
	 */
	public void sentSMSOwingReport(String deparID, String accountID,
			String mobile) throws EduException {
		VelocityContext context = new VelocityContext();
		context.put("id", accountID);
		context.put("msgtel", ctrlParam.msgTel);

		// 设置输出
		StringWriter writer = new StringWriter();
		String msgContent = "";

		try {
			// 将环境数据转化输出
			owingReportMsg3Template.merge(context, writer);

			msgContent = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EduException(ErrorCode.ERR_BUSINESS_SENDSMSFAIL);
		}
		// GServer.println(msgContent);

		SMSInfo smsInfo = new SMSInfo();
		smsInfo.MsgContent = msgContent;
		smsInfo.CustomerMobile = mobile;
		smsInfo.DepartmentID = deparID;
		smsInfo.MsgType = SMSInfo.MSG_TYPE_SMSOWING;

		sentMessage(smsInfo);
	}
	
	/**
	 * 启动线程发送短消息
	 * 
	 * @param smsInfo
	 * @throws EduException
	 */
	private void sentMessage(SMSInfo smsInfo) throws EduException {
		//if("1".equals(ControlParam.getInstance().sendSMS))
		executor.submit(new SMSWorker(smsInfo));
	}
	
	/**
	 * 发送催领短消息线程
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
	private class ReminderThread extends Thread {
	    private Set<String> terminalNoList = new HashSet<String>();

        private void loadTerminalNo() {
            try {
                terminalNoList.clear();
                InParamTBTerminalListQry inParam0 = new InParamTBTerminalListQry();
                inParam0.TerminalStatus = "0";
                RowSet rset = MonitorProxy.doBusiness(inParam0);
                while (RowSetUtils.rowsetNext(rset)) {
                    terminalNoList.add(RowSetUtils.getStringValue(rset,"TerminalNo"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                GServer.println("[ReminderThread loadTerminalNo ] = "+ e.getMessage());
            }
        }
		public void run() {

		    try {
                Thread.sleep(1000 * 50 );
                loadTerminalNo();
            } catch (InterruptedException ex) {
            }
		    GServer.println(" ----------- ReminderThread start -----------"+isRunning());
		    
			while (isRunning()) {
				try {
					Date nowDate = new Date();
					GServer.println(" send reminder msg begin --------------");
					if ("1".equalsIgnoreCase(ControlParam.getInstance().sendReminderSMS)) {
					    boolean isSent = false;
						int reminderTimeHours = NumberUtils.parseInt(ctrlParam.getReminderDateTime());
						if(reminderTimeHours == 0){
						    //每小时检查
						    isSent = true;
						}else{
						    if (reminderTimeHours <= 7 || reminderTimeHours > 23) {
						        reminderTimeHours = 16;// 未设置时间，默认下午4点提醒
	                        }
	                        if (DateUtils.getHour(nowDate) == reminderTimeHours) {
	                            isSent = true;
	                        }
						}
						if(isSent){
						    for (String terminalNo : terminalNoList) {
                                InParamMBReminderMsgQry inParam1 = new InParamMBReminderMsgQry();
                                inParam1.TerminalNo = terminalNo;

                                RowSet rset1 = MonitorProxy.doBusiness(inParam1);
                                while (RowSetUtils.rowsetNext(rset1)) {
                                    try {
                                        InParamMBSendReminderMsg inParam2 = new InParamMBSendReminderMsg();
                                        inParam2.TerminalNo = RowSetUtils.getStringValue(rset1, "TerminalNo");
                                        inParam2.PackageID = RowSetUtils.getStringValue(rset1, "PackageID");
                                        GServer.println("===发送催领端消息==="+inParam2.PackageID);
                                        MonitorProxy.doBusiness(inParam2);

                                        Thread.sleep(50);
                                    } catch (Exception e) {
                                        GServer.println("[ReminderThread business error] = "+ e.getMessage());
                                    }
                                }
                            }
						}
						GServer.println("send reminder msg end --------------");
					}
					
					//////////////////// 顺丰取件柜，超过30h给投递员发送逾期短信
					if ("1".equalsIgnoreCase(ControlParam.getInstance().sendReminderSMS2Post)) {
					    for (String terminalNo : terminalNoList) {
                            InParamMBReminderMsgQry inParam1 = new InParamMBReminderMsgQry();
                            inParam1.TerminalNo = terminalNo;
                            inParam1.reminderHours = 30;
                            RowSet rset1 = MonitorProxy.doBusiness(inParam1);
                            while (RowSetUtils.rowsetNext(rset1)) {
                                try {
                                    GServer.println(" send expird msg to postman begin --------------");
                                    SMSInfo smsInfo = new SMSInfo();
                                    smsInfo.TerminalNo = RowSetUtils.getStringValue(rset1, "TerminalNo");
                                    smsInfo.PackageID = RowSetUtils.getStringValue(rset1, "PackageID");
                                    smsInfo.StoredTime = RowSetUtils.getTimestampValue(rset1,"StoredTime");
                                    smsInfo.PostmanID = RowSetUtils.getStringValue(rset1, "PostmanID");
                                    smsInfo.CustomerMobile = RowSetUtils.getStringValue(rset1, "CustomerMobile");
                                    smsInfo.OpenBoxKey = RowSetUtils.getStringValue(rset1, "BlankBoxKey");
                                    
                                    //提醒用户
                                    if(StringUtils.isNotEmpty(smsInfo.CustomerMobile)){
                                        smsInfo.MsgType = SMSInfo.MSG_TYPE_RETENTION;
                                        log.info("===发送超过30h消息给用户==="+smsInfo.PackageID);
                                        sentExpiredSMS(smsInfo);
                                    }else{
                                        log.error("send expird msg to cusomer error: mobile is empty."+smsInfo.PackageID);
                                    }
                                    
                                    
                                    //通知投递员
                                    SMSInfo smsInfoToPostman = new SMSInfo(smsInfo);
                                    //long time = System.currentTimeMillis()- inParam3.StoredTime.getTime();
                                    //if (time > 30L*60*60*1000 && time < 31L*60*60*1000){
                                        //查询投递员电话
                                        InParamPMPostmanQry inparamPostman = new InParamPMPostmanQry();
                                        inparamPostman.PostmanID = smsInfoToPostman.PostmanID;
                                        RowSet rsetPostman = PMWebClientAdapter.doBusiness(inparamPostman);
                                        
                                        if(RowSetUtils.rowsetNext(rsetPostman)){
                                            smsInfoToPostman.PostmanMobile = RowSetUtils.getStringValue(rsetPostman, "Mobile");
                                        }
                                        
                                        if(StringUtils.isNotEmpty(smsInfoToPostman.PostmanMobile)){
                                            smsInfoToPostman.MsgType = SMSInfo.MSG_TYPE_ITWRITER;
                                            log.info("===发送超过30h消息给投递员==="+smsInfo.PackageID);
                                            sentExpiredSMSToPostman(smsInfoToPostman);
                                        }else{
                                            log.error("send expird msg to postman error: mobile is empty. "+smsInfoToPostman.PackageID);
                                        }
                                        
                                    //}   
                                        GServer.println(" send expird msg to postman end --------------");
                                    
                                    Thread.sleep(50);
                                }catch (Exception e) {
                                    GServer.println("[ReminderThread business error] = "+ e.getMessage());
                                }
                            }
					    }
					}

					try {
						Thread.sleep(1000 * 60 * 59); // 休眠一个小时
						loadTerminalNo();
					} catch (InterruptedException ex) {

					}
				} catch (Exception e) {
				    GServer.println("[ReminderThread error] = "+ e.getMessage());
				}
				
				GServer.println(" ----------- ReminderThread end -----------"+isRunning());
			}

		}
	}

	/**
	 * 短信账号监控线程
	 * <p>
	 * Title: 智能自助包裹柜系统
	 * </p>
	 *
	 * <p>
	 * Description:
	 * </p>
	 *
	 * <p>
	 * Copyright: Copyright (c) 2014
	 * </p>
	 *
	 * <p>
	 * Company: 杭州东城电子有限公司
	 * </p>
	 *
	 * @author zhengxy
	 * @version 1.0
	 */
	private class SMSRunnerThread extends Thread {
	    private Set<String> terminalNoList = new HashSet<String>();

        private void loadTerminalNo() {
            try {
                terminalNoList.clear();
                InParamTBTerminalListQry inParam0 = new InParamTBTerminalListQry();
                inParam0.TerminalStatus = "0";
                RowSet rset = MonitorProxy.doBusiness(inParam0);
                while (RowSetUtils.rowsetNext(rset)) {
                    terminalNoList.add(RowSetUtils.getStringValue(rset,"TerminalNo"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                GServer.println("[SMSRunnerThread loadTerminalNo ] = "+ e.getMessage());
            }
        }
		public void run() {
			try {
				Thread.sleep(1000 * 33);
				GServer.println(" SMSRunner Thread checkSMSAccount="+ ControlParam.getInstance().checkSMSAccount);
				
				loadTerminalNo();
			} catch (InterruptedException ex) {
			}
			
			GServer.println(" ----------- SMSRunnerThread start -----------"+isRunning());
			while (isRunning()) {
				try {
					Date nowDate = new Date();

					int hourSendMsg = NumberUtils.parseInt(ctrlParam.getSendPwdSMSFirstTime());
					if (hourSendMsg < 6) {
						hourSendMsg = 6;
					}
					////////////////////////////////
					int hour = DateUtils.getHour(nowDate);
					if (hour == hourSendMsg  && StringUtils.isNotEmpty(apCfg.getSendShortMsg())) {
						int count = 0;
						GServer.println(" Auto Send Pwd SMS begin --------------");

						for (String terminalNo : terminalNoList) {
							InParamPTAutoSendPwdSMS in = new InParamPTAutoSendPwdSMS();
							in.TerminalNo = terminalNo;
							try {
								int result = MonitorProxy.doBusiness(in);
								if (result > 0) {
									count = count + result;

									Thread.sleep(50);
								}
							} catch (Exception e) {
								GServer.println("[SMSRunnerThread business(AutoSendPwdSMS) error] = "
												+ e.getMessage());
							}
						}
						GServer.println(" Auto Send Pwd SMS("+ hourSendMsg + ":00): " + count);

					}

					/////////////////////////////////////////////////////////////////////
					if ("1".equalsIgnoreCase(ControlParam.getInstance().checkSMSAccount)) {
						GServer.println( " SMSRunner Thread begin --------------");
						// 查询
						InParamPYSMSAccountListQry inParam0 = new InParamPYSMSAccountListQry();
						inParam0.AccountStatus = SysDict.SMS_ACCOUNT_STATUS_NORMAL;
						RowSet rset = MonitorProxy.doBusiness(inParam0);
						while (RowSetUtils.rowsetNext(rset)) {
							String AccountID = RowSetUtils.getStringValue(rset,"AccountID");
							try {
								if (hour == 23) {
									// 记录每天的短信发送账单
									InParamPYSMSConsumeBillAdd inParam1 = new InParamPYSMSConsumeBillAdd();
									inParam1.AccountID = AccountID;
									MonitorProxy.doBusiness(inParam1);
								} else if (hour > hourSendMsg && hour < 22) {
									// 检测账号状态，发送提醒通知
									InParamPYSMSOwingReportSend inParam1 = new InParamPYSMSOwingReportSend();
									inParam1.AccountID = AccountID;
									MonitorProxy.doBusiness(inParam1);
								}
								Thread.sleep(100);
							} catch (Exception e) {
								GServer.println("[SMSRunnerThread business error] = "+ e.getMessage());
							}
						}

						GServer.println("SMSRunner Thread  end --------------");
					}

					try {
						if (hour == (hourSendMsg - 1)) {
							Thread.sleep(1000 * 60 * 5); // 早上统一发送短信，
						} else {
							Thread.sleep(1000 * 60 * 59); // 休眠一个小时
							loadTerminalNo();
						}

					} catch (InterruptedException ex) {
					}
				} catch (Exception e) {
					GServer.println("[SMSRunnerThread error] = "+ e.getMessage());
				}
				
				GServer.println(" ----------- SMSRunnerThread end -----------"+isRunning());
			}

		}
	}

}
