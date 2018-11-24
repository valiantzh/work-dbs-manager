package com.dcdzsoft.sms;

import com.dcdzsoft.EduException;

public interface ISMSProxy {
	public String sendMessage(SMSInfo smsInfo) throws EduException;
}
