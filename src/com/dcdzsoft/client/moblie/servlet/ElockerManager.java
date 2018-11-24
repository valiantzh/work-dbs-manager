package com.dcdzsoft.client.moblie.servlet;


import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.RowSet;

import org.apache.commons.logging.Log;

import com.dcdzsoft.business.GServer;
import com.dcdzsoft.client.web.MBWebClientAdapter;

import com.dcdzsoft.client.web.TBWebClientAdapter;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.dto.business.InParamMBDeviceSignQry;
import com.dcdzsoft.dto.business.InParamTBTerminalQry;
import com.dcdzsoft.sda.db.RowSetUtils;
import com.dcdzsoft.util.DateUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class ElockerManager {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(ElockerManager.class);

	
	private static ConcurrentHashMap<String,ElockerStatus> elockerStatusListMap;
	
	/**
	 * 工作的线程数
	 */
	private int workerCount = 100;

	private RunnerThread runnerThread;
	
	private static boolean isRunning = true;
	
	
	/**
	 * 私有默认构造函数
	 */
	private ElockerManager() {
		workerCount = ApplicationConfig.getInstance().getWorkerCount();
		
		elockerStatusListMap =new ConcurrentHashMap<String,ElockerStatus>(workerCount);
		 
		runnerThread = new RunnerThread();
		runnerThread.start();
	}

	private static class SingletonHolder {
		private static final ElockerManager instance = new ElockerManager();
	}

	/**
	 * 静态工厂方法，返还此类的惟一实例
	 * 
	 * @return a SMSManager instance
	 */
	public static ElockerManager getInstance() {
		return SingletonHolder.instance;
	}

	protected synchronized static boolean isRunning() {
        return isRunning;
    }
	
	public synchronized void destroy(){
	    GServer.println("---- ElockerManager.destroy start----"+isRunning);
        isRunning = false;
        if(runnerThread != null){
            runnerThread.interrupt();
        }
        GServer.println("---- ElockerManager.destroy end ----"+isRunning);
    }
	
	private void cleanElockerStatusList(){
		elockerStatusListMap.clear();
	}
	private void addElockerStatus(ElockerStatus elockerStatus){
		elockerStatusListMap.put(elockerStatus.getTerminalNo(), elockerStatus);
	}
	public String getElockerStatusList(){
		JSONArray statusArray = new JSONArray();
		Set<String> set = elockerStatusListMap.keySet();
		for(String terminalNo : set){
			statusArray.add(elockerStatusListMap.get(terminalNo));
		}
		return statusArray.toString();
	}
	private void updateElockerOnlineStatus(String treminalNo, String onlineStatus){
		ElockerStatus elockerStatus = elockerStatusListMap.get(treminalNo);
		if(elockerStatus!=null){
			if(!"1".equals(onlineStatus)){
				onlineStatus = "0";
			}
			System.out.println(treminalNo+","+onlineStatus);
			elockerStatus.setOnlineStatus(onlineStatus);
		}
	}
	/**
     * 设备状态信息获取控线程
     * <p>Title: 智能自助包裹柜系统</p>
     *
     * <p>Description: </p>
     *
     * <p>Copyright: Copyright (c) 2014</p>
     *
     * <p>Company: 杭州东城电子有限公司</p>
     *
     * @author zhengxy
     * @version 1.0
     */
    private class RunnerThread extends Thread
    {
        public void run() 
        {
            try
            {
            	int initHours = 7;
            	boolean isReLoad = true;
            	
            	GServer.println(" ----------- ElockerManager.RunnerThread start -----------"+isRunning());
                while(isRunning())
                {
                	
                	Date nowDate = new Date();
                	int hour = DateUtils.getHour(nowDate);
                	if(hour == initHours ){
                		isReLoad = true;
                		initHours = initHours>7?7:13;//不用太频繁的查询设备信息
                	}
                	
                	//查询设备信息
                	if(isReLoad){
                		cleanElockerStatusList();
                		InParamTBTerminalQry  infoQry = new InParamTBTerminalQry();
                    	RowSet rset1 = TBWebClientAdapter.doBusiness(infoQry);
                    	while(RowSetUtils.rowsetNext(rset1))
                        {
                    		ElockerStatus elockerStatus= new ElockerStatus();
                    		elockerStatus.setTerminalNo( RowSetUtils.getStringValue(rset1, "TerminalNo"));
                    		elockerStatus.setTerminalName(RowSetUtils.getStringValue(rset1, "TerminalName"));
                    		elockerStatus.setLocation( RowSetUtils.getStringValue(rset1, "Location"));
                    		elockerStatus.setTerminalStatus( RowSetUtils.getStringValue(rset1, "TerminalStatus"));
                    		elockerStatus.setBoxNum( RowSetUtils.getIntValue(rset1, "BoxNum"));
                    		elockerStatus.setOnlineStatus( "0");//RowSetUtils.getStringValue(rset1, "OnlineStatus");
                    		
                    		addElockerStatus(elockerStatus);
                        }
                    	isReLoad = false;
                	}
                	
                	//查询设备状态
                	InParamMBDeviceSignQry signQry = new InParamMBDeviceSignQry();
                	RowSet rset2 = MBWebClientAdapter.doBusiness(signQry);
                	while(RowSetUtils.rowsetNext(rset2))
                    {
                		ElockerStatus elockerStatus= new ElockerStatus();
                		elockerStatus.setTerminalNo(RowSetUtils.getStringValue(rset2, "TerminalNo"));
                		elockerStatus.setOnlineStatus( RowSetUtils.getStringValue(rset2, "OnlineStatus"));
                		updateElockerOnlineStatus(elockerStatus.getTerminalNo(), elockerStatus.getOnlineStatus());
                    }
                    
                    try
                    {
                        Thread.sleep(1000*60*5); //
                    }catch(InterruptedException ex)
                    { }
                }
            }catch(Exception e)
            {
                System.err.println("[ElockerRunnerThread error] = " + e.getMessage());
            }
            GServer.println(" ----------- ElockerManager.RunnerThread end -----------"+isRunning());
        }
    }
}
