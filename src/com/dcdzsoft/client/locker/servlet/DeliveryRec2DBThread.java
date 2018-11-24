package com.dcdzsoft.client.locker.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dcdzsoft.EduException;
import com.dcdzsoft.client.web.PTWebClientAdapter;
import com.dcdzsoft.constant.SysDict;
import com.dcdzsoft.dto.business.InParamPTDeliverPackage2DB;
import com.dcdzsoft.dto.business.InParamPTInboxPackage2DB;

/**
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
 * @author wangzl
 * @version 1.0
 */
public class DeliveryRec2DBThread extends Thread {
	public static final Log log = LogFactory.getLog(DeliveryRec2DBThread.class.getName());

	public DeliveryRec2DBThread() {
		
	}

	private String fileName;
	private String TerminalNo;
	private String uploadType;

	DeliveryRec2DBThread(String fileName, String TerminalNo,String uploadType) {
		// setPriority(4);
		this.fileName = fileName;
		this.TerminalNo = TerminalNo;
		this.uploadType = uploadType;
	}

	public void run() {
		if(SysDict.UPLOAD_LOG_TYPE_HISTORY2DB.equalsIgnoreCase(uploadType))
		{
			InParamPTDeliverPackage2DB inParam = new InParamPTDeliverPackage2DB();
			inParam.TerminalNo = TerminalNo;
			inParam.FileName = fileName;
			
			try
			{
				PTWebClientAdapter.doBusiness(inParam);
				
				log.info("DeliverPackage2DB completed...");
			}catch(EduException e)
			{
				log.error("[DeliverPackage2DB error]= " + e.getMessage());
			}
			
		}else if(SysDict.UPLOAD_LOG_TYPE_INBOX2DB.equalsIgnoreCase(uploadType))
		{
			InParamPTInboxPackage2DB inParam = new InParamPTInboxPackage2DB();
			inParam.TerminalNo = TerminalNo;
			inParam.FileName = fileName;
			
			try
			{
				PTWebClientAdapter.doBusiness(inParam);
				
				log.info("InboxPackage2DB completed...");
			}catch(EduException e)
			{
				log.error("[InboxPackage2DB error]= " + e.getMessage());
			}
		}
	}
}
