package com.dcdzsoft.sms.impl;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.sms.ISMSProxy;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSManager;
import com.dcdzsoft.util.RandUtils;

public class TestSMS {
	private static String physicalPath = "D:\\MyProject\\java\\DBSManagerBZ\\dbs\\";
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();

	public static void init() {

		apCfg.setPhysicalPath(physicalPath);
		try {
			apCfg.load("D:\\MyProject\\java\\DBSManagerBZ\\dbs\\WEB-INF\\appconfig.xml");

			Properties p = new Properties();
			p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,
					physicalPath + "WEB-INF/vm/");
			p.setProperty(Velocity.INPUT_ENCODING, "utf-8");
			p.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
			Velocity.init(p);

			apCfg.setSmsCharset("UTF-8");
			SMSManager.getInstance().loadTemplate();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws EduException {
		// TODO Auto-generated method stub
		init();

		SMSInfo smsInfo = new SMSInfo();
		smsInfo.MsgType = 1;// 1-投递 2-逾期 3-催领 4; //取件 5; //重发投递 7; //注册验证码
		smsInfo.CustomerMobile = "658213309";
		// smsInfo.MsgContent =
		// "您的快递"+RandUtils.generateNumber(6)+"已送达杭海路1221号东城电子园区研发楼3楼自提柜,请凭提货密码"+RandUtils.generateNumber(6)+"取件。投递员电话:123456";
		smsInfo.MsgContent = "Your parcel test20180411 has been delivered to locker Dongcheng electronic Hangzhou, China, the pickup code 123456.";
		smsInfo.OpenBoxKey = RandUtils.generateNumber(4);
		smsInfo.TerminalName = "东城电子";
		smsInfo.PostmanID = "123456";
		smsInfo.PostmanMobile = "123456";
		smsInfo.BoxNo = "1";
		smsInfo.msgTel = "86468639";
		smsInfo.DynamicCode = "7085";
		smsInfo.Location = "elocker";
		smsInfo.PackageID = "1701" + RandUtils.generateNumber(8);
		smsInfo.OfBureau = "33";
		// apCfg.setGatewayUser("I97098201");
		// apCfg.setGatewayPwd("847609d0e7f919095e5143971551c89d");
		// apCfg.setSmsMobilePrefix("001 ");
		// ISMSProxy smsSender = new MsgProxyIhuyiGuoJi();

		// 烽火
		// apCfg.setSendShortMsg("MsgProxyFenghuo213");
		// apCfg.setGatewayUser("mobilis");
		// apCfg.setGatewayPwd("mobilis");
		// ISMSProxy smsSender = new MsgProxyFenghuo213();
		// SMSManager.getInstance().sentDeliverySMS(smsInfo);
		// 阿里大于
		// apCfg.setGatewayUser("23592026");
		// apCfg.setGatewayPwd("cadd7f03b4468fefdf21a1fcf4b8f464");
		// ISMSProxy smsSender = new MsgProxyTaiHeAli();
		// 亿美软通
		// apCfg.setGatewayUser("0SDK-EBB-6699-RDRNQ");
		// apCfg.setGatewayPwd("761570");
		// ISMSProxy smsSender = new MsgProxyYiMeiRuanTong();

		// 上海希奥
		// apCfg.setGatewayUser("506991");
		// apCfg.setGatewayPwd("sioo01");
		// ISMSProxy smsSender = new MsgProxySioo();

		// 君诚科技
		// apCfg.setGatewayUser("dcdz");
		// apCfg.setGatewayPwd("gspgwxzv");
		// ISMSProxy smsSender = new MsgProxyJunChengKJ();

		// 助通科技
		// apCfg.setGatewayUser("dongcheng888");
		// apCfg.setGatewayPwd("53rVQ9By");
		// ISMSProxy smsSender = new MsgProxyZhuTongKj();

		// 互亿科技
		// apCfg.setGatewayUser("cf_joyfreedom");
		// apCfg.setGatewayPwd("dcdz_918");
		// ISMSProxy smsSender = new MsgProxyIhuyi();
		// 互亿科技国际版
//		apCfg.setGatewayUser("I97098201");
//		apCfg.setGatewayPwd("847609d0e7f919095e5143971551c89d");
		ISMSProxy smsSender = new MsgProxyIhuyiGuoJi();

		// 泰和
		// smsInfo.BoxNo = "1";
		// smsInfo.Location = "杭海路1221号东城电子园区研发楼3楼自提柜";
		// smsInfo.MsgType = SMSInfo.MSG_TYPE_DELIVERY;
		// smsInfo.PostmanMobile = "123456";
		// apCfg.setGatewayUser("green");
		// apCfg.setGatewayPwd("green");
		// ISMSProxy smsSender = new MsgProxyTaiHe();
		// 法国用户
		// apCfg.setSmsServerPort("32b94f0ecfe0cdd3beab8a6244c49a7bb6ca9557");
		// ISMSProxy smsSender = new MsgProxyFrench();
		// 法国用户
//		 apCfg.setSmsServerPort("32b94f0ecfe0cdd3beab8a6244c49a7bb6ca9557");
//		 ISMSProxy smsSender = new MsgProxyFrenchThomas();
		// 美国短信平台
//		ControlParam.getInstance().setShortMsgFromPhone("+16263146711");// 发送短信的手机号,美观发送短信需要设置一个发送短信的手机号码。
//		ISMSProxy smsSender = new MsgProxyShunfeng();
		
		
		String result = "";

		result = smsSender.sendMessage(smsInfo);
		System.out.println("result=" + result);
	}

}
