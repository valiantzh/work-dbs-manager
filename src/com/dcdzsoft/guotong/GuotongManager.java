package com.dcdzsoft.guotong;

import java.io.StringWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;

import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.dto.function.TBTerminal;
import com.dcdzsoft.outerproxy.SendInfo;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.sms.SMSWorker;


public class GuotongManager {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(GuotongManager.class);

	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
	/**
	 * 工作的线程数
	 */
	private int workerCount = 100;

	private ThreadPoolExecutor executor;
	
	/**
	 * 私有默认构造函数
	 */
	private GuotongManager() {
		workerCount = ApplicationConfig.getInstance().getWorkerCount();
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(workerCount);
	}

	private static class SingletonHolder {
		private static final GuotongManager instance = new GuotongManager();
	}

	/**
	 * 静态工厂方法，返还此类的惟一实例
	 * 
	 * @return a GuotongManager instance
	 */
	public static GuotongManager getInstance() {
		return SingletonHolder.instance;
	}
	
	/**
	 * 发送投递状态
	 * @param smsInfo
	 */
	public void sentDeliveryInfo(SMSInfo smsInfo)
	{
		executor.submit(new GuotongWorker(smsInfo,null, null));
	}

	/**
	 * 发送包裹柜注册信息
	 * @param terminal
	 * @param GXBZ
	 * @return
	 */
	public String sentRegisterTerminal(TBTerminal terminal,String GXBZ)
	{
		return HttpClient4Guotong.doSentTerminalInfo(terminal,GXBZ);
	}
	
	/**
	 * 发送格口信息
	 * @param terminal
	 * @param boxinfo
	 * @param GXBZ
	 * @return
	 */
	public String sentBoxInfo(TBTerminal terminal, String boxinfo, String GXBZ){
	    return HttpClient4Guotong.doSentBoxInfo(terminal,boxinfo, GXBZ);
	}
	
	/**
     * 批量上传在线设备编号
     * @param terminalNoList
     * @return
     */
    public String sentOnlineTerminals(String terminalNoList)
    {
        return HttpClient4Guotong.doSentOnlineTerminals(terminalNoList);
    }
    
    /**
     * 发送用户信息
     * @param sendInfo
     */
    public void sentCustomerInfo(SendInfo sendInfo){
        executor.submit(new GuotongWorker(null,null, sendInfo));
    }
}
