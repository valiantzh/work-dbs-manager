package com.dcdzsoft.client.locker.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.util.TrustManagerUtils;

import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.NumberUtils;
import com.dcdzsoft.dto.business.InParamSCCheckFtpLog;
import com.dcdzsoft.dto.business.InParamSCInsertFtpLog;
import com.dcdzsoft.client.web.SCWebClientAdapter;


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
public class FtpSendFile extends Thread {
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	public static final Log log = LogFactory
			.getLog(FtpSendFile.class.getName());

	public FtpSendFile() {
	}

	private String fileName;
	private String TerminalNo;
	private boolean checkFileUploaded;

	public FtpSendFile(String fileName, String TerminalNo,boolean checkFileUploaded) {
		// setPriority(4);
		this.fileName = fileName;
		this.TerminalNo = TerminalNo;
		this.checkFileUploaded = checkFileUploaded;
	}

	public void run() {
		if (StringUtils.isEmpty(apCfg.getFtpHost())
				|| StringUtils.isEmpty(apCfg.getFtpPort()))
			return;
		
		//检查日志是否已经上传
		if(checkFileUploaded && checkFtpLog(TerminalNo) > 0)
			return;

		FTPClient ftpClient = null;

		try {
			Thread.sleep(1000);

			ftpClient = new FTPClient();

			ftpClient.connect(apCfg.getFtpHost(),NumberUtils.parseInt(apCfg.getFtpPort()));

			// After connection attempt, you should check the reply code to
			// verify
			// success.
			int reply = ftpClient.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				log.error("FTP server refused connection.");

				return;
			}
		} catch (Exception e) {
			log.error("[ftp file failure0]==" + TerminalNo + "," + e.getMessage());

			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException f) {
					// do nothing
				}
				
				return;
			}
		}
		
		InputStream inputStream = null;
		////////////////////////////////////////////////
		try {
			if (!ftpClient.login(apCfg.getFtpUser(), apCfg.getFtpPasswd())) {

				ftpClient.logout();
			} else {
				log.info("[ftp file begin]==" + TerminalNo);

				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				
				inputStream = new FileInputStream(fileName);
                
				String remote = buildFileName(TerminalNo);
				
				ftpClient.storeFile(remote, inputStream);
				
				ftpClient.logout();
				
				//写入日志
				if(checkFileUploaded)
					insertFtpLog(TerminalNo);
				
				log.info("[ftp file end]==" + TerminalNo);
			}
		} catch (FTPConnectionClosedException e) {
			log.error("[ftp file failure1]==" + TerminalNo + "," + e.getMessage());
		} catch (IOException e) {
			log.error("[ftp file failure2]==" + TerminalNo + "," + e.getMessage());
		} finally {
			if(inputStream != null)
			{
				try
				{
					inputStream.close();
				}catch(IOException e)
				{
					
				}
			}
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException f) {
					// do nothing
				}
			}
		}
	}
	
	private String buildFileName(String terminalNo)
	{
		java.util.Date date = new java.util.Date();
		date = DateUtils.addDate(date, -1);
		
		TimeZone timeZone = null;
	    timeZone = TimeZone.getDefault();
	
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    sdf.setTimeZone(timeZone);
	       
		String sDate = sdf.format(date);
		
		return terminalNo + "_" + sDate + ".txt";
	}
	
	private int checkFtpLog(String terminalNo)
	{
		java.util.Date date = new java.util.Date();
		date = DateUtils.addDate(date, -1);
		
		InParamSCCheckFtpLog inParam = new InParamSCCheckFtpLog();
		inParam.StoredDate = DateUtils.toSQLDate(date);
		inParam.TerminalNo = terminalNo;
		
		int result = 0;
		
		try
		{
			result = SCWebClientAdapter.doBusiness(inParam);
		}catch(EduException e)
		{
			log.error("[checkFtpLog failure] = " + e.getMessage());
		}
		
		return result;
		
		
	}
	
	private int insertFtpLog(String terminalNo)
	{
		java.util.Date date = new java.util.Date();
		date = DateUtils.addDate(date, -1);
		
		InParamSCInsertFtpLog inParam = new InParamSCInsertFtpLog();
		inParam.StoredDate = DateUtils.toSQLDate(date);
		inParam.TerminalNo = terminalNo;
		
		int result = 0;
		
		try
		{
			result = SCWebClientAdapter.doBusiness(inParam);
		}catch(EduException e)
		{
			log.error("[insertFtpLog failure] = " + e.getMessage());
		}
		
		return result;
	}

}
