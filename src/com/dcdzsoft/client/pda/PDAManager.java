package com.dcdzsoft.client.pda;


import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;






import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.dto.function.TBTerminal;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.websocket.BusinessWorker;


public class PDAManager {
	private static Log log = org.apache.commons.logging.LogFactory.getLog(PDAManager.class);

	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	private static ControlParam ctrlParam = ControlParam.getInstance();
	
	private ConcurrentHashMap<String,PDASignInfo> onlinePADSignInfoMap;//在线PDA签到信息保存到内存中
	private ConcurrentHashMap<String,String> terminalBindingPDAMap;//在线的柜子绑定PDA <TerminalNo, PDANo>
	
	/**
	 * 工作的线程数
	 */
	private int workerCount = 100;

	
	PDAServiceMonitorThread monitorThread;
	
	/**
	 * 私有默认构造函数
	 */
	private PDAManager() {
	    onlinePADSignInfoMap = new  ConcurrentHashMap<String,PDASignInfo>(workerCount);
	    terminalBindingPDAMap = new  ConcurrentHashMap<String,String>(workerCount);
	    monitorThread = new PDAServiceMonitorThread();
	    monitorThread.start();
	}

	private static class SingletonHolder {
		private static final PDAManager instance = new PDAManager();
	}

	/**
	 * 静态工厂方法，返还此类的惟一实例
	 * 
	 * @return a SMSManager instance
	 */
	public static PDAManager getInstance() {
		return SingletonHolder.instance;
	}
	
	public void addPDASignInfo(PDASignInfo sign){
	    PDASignInfo signInfo = onlinePADSignInfoMap.get(sign.getPdaNo());
	    if(signInfo == null){
	        signInfo = sign;
	    }
	    if(signInfo.getSignTimestamp() == 0){
	        signInfo.setSignTimestamp(new java.util.Date().getTime());
	    }
	    if(signInfo.getUpdateTimestamp() ==0){
	        signInfo.setUpdateTimestamp(new java.util.Date().getTime());
	    }
	    if(signInfo.getUploadTimestamp() ==0){
            signInfo.setUploadTimestamp(new java.util.Date().getTime());
        }
	    
	    //TODO 
	    onlinePADSignInfoMap.put(signInfo.getPdaNo(), signInfo);
	    System.out.println("addPDASignInfo:"+onlinePADSignInfoMap.toString());
	}
	public Set<String> getPDASignInfoSet(){
		return onlinePADSignInfoMap.keySet();
	}
	public PDASignInfo getPDASignInfo(String key){
	    //System.out.println("getPDASignInfo:"+onlinePADSignInfoMap.toString()+",pdaNo="+key);
	    return onlinePADSignInfoMap.get(key);
	}
	public void removePDASignInfo(String key){
	    //System.out.println("removePDASignInfo:pdaNo="+key+","+onlinePADSignInfoMap.toString());
		onlinePADSignInfoMap.remove(key);
	}
	/**
	 * 
	 * @param pdaNo
	 * @param authToken PDA获取可用箱列表的令牌
	 * @return  <TerminalNo>=<freeBoxList>
	 *       TerminalNo:柜号
	 *       freeBoxList:<BoxNo>,<BoxType>;<BoxNo>,<BoxType>;.....
	 *       "locker01=1,0;2,1;3,0;4,1"
	 */
	public String getFreeBoxList(String pdaNo, String authToken){
		StringBuffer freeBoxList = new StringBuffer();
		PDASignInfo signInfo = getPDASignInfo(pdaNo);
		
		if(signInfo != null 
		        && signInfo.getAuthToken().equals(authToken)){//验证令牌
			if(StringUtils.isNotEmpty(signInfo.getTerminalNo())){
				freeBoxList.append(signInfo.getTerminalNo());
			}else{
				freeBoxList.append("NULL");
			}
			freeBoxList.append("=");
			if(StringUtils.isNotEmpty(signInfo.getFreeBoxList())){
				freeBoxList.append(signInfo.getFreeBoxList());
			}else{
				freeBoxList.append("NOTFREE");
			}
		} else {
		    System.out.println("getFreeBoxList signInfo is NULL: pdaNo="+pdaNo);
		}
		return freeBoxList.toString();//TerminalNo=freeBoxList
	}
	/**
	 * 更新PDA最后签到时间
	 * @param pdaNo
	 * @return
	 */
	public boolean updatePdaLastSignTime(String pdaNo){
	    PDASignInfo signInfo = getPDASignInfo(pdaNo);
        if(signInfo!=null){
            signInfo.setUpdateTimestamp(new java.util.Date().getTime());
            return true;
        }
	    return false;
	}
	/**
	 * 柜体绑定PDA
	 * @param terminal     柜体信息
	 * @param pdaNo        PDA编号
	 * @param freeBoxList  可用箱列表
	 * @param authToken    PDA 获取可用箱的令牌
	 * @param forceFlag    强制更换绑定的PDA
	 * @return
	 */
	public boolean bindingPDA(TBTerminal terminal, String pdaNo, String freeBoxList, String authToken, boolean forceFlag) throws EduException{
		if(terminal == null){
			return false;
		}
		return bindingPDA(terminal.TerminalNo, pdaNo, freeBoxList, authToken, forceFlag);
	}
	public boolean bindingPDA(String terminalNo, String pdaNo, String freeBoxList, String authToken, boolean forceFlag) throws EduException{
		System.out.println("terminalNo="+terminalNo+",pdaNo="+pdaNo+",freeBoxList="+freeBoxList+",authToken="+authToken+",forceFlag="+forceFlag);
	    if(StringUtils.isEmpty(terminalNo) || StringUtils.isEmpty(pdaNo)){
	        System.err.println("Error:terminalNo or padNo is empty");
			return false;
		}
		
		//新的PDA是否在线
		PDASignInfo signInfo = getPDASignInfo(pdaNo);
    	if(signInfo == null){
    		//PDA 离线
    		//return false;
    	    System.err.println("Error:Device offline");
    		throw new EduException(ErrorCode.ERR_DEVICENETWORKABNORMAL);
    	}
    	String bindingPDANo = null;
    	synchronized (this) {
    		bindingPDANo = terminalBindingPDAMap.get(terminalNo);
    		if(forceFlag && StringUtils.isNotEmpty(bindingPDANo)){
    			//强制更换绑定的PDA
    			terminalBindingPDAMap.remove(terminalNo);
    			removePDASignInfo(bindingPDANo);
    			bindingPDANo = null;
    		}
    		//一个柜体同一时间只能配对一个PDA
    		if(StringUtils.isEmpty(bindingPDANo) || bindingPDANo.equals(pdaNo)){
    			//柜体未绑定PDA,进行绑定
                terminalBindingPDAMap.put(terminalNo, pdaNo);
                //更新可用箱列表
                signInfo.setTerminalNo(terminalNo);
                signInfo.setAuthToken(authToken);
                signInfo.setFreeBoxList(freeBoxList);
                signInfo.setUploadTimestamp(new java.util.Date().getTime());
                System.out.println("bindingPDA:pdaNo="+pdaNo+",tNo="+terminalNo+",map="+terminalBindingPDAMap.toString());
                return true;
    		}else{
    			//柜体已绑定其他PDA,不允许绑定新的PDA
            	//return false;
    		    System.err.println("Error:Locker already binding PDA!" +bindingPDANo);
            	throw new EduException(ErrorCode.ERR_FORBIDPDABINDING);
    		}
		}
	}

	/**
     * PDA服务监控线程
     * <p>Title: 智能自助包裹柜系统</p>
     *
     * <p>Description: </p>
     *
     * <p>Copyright: Copyright (c) 2017</p>
     *
     * <p>Company: 杭州东城电子有限公司</p>
     *
     * @author zhengxy
     * @version 1.0
     */
    private class PDAServiceMonitorThread extends Thread
    {
        public void run() 
        {
            try
            {
                Thread.sleep(1000*3); 
                System.out.println(DateUtils.nowDate() + " " + DateUtils.nowTime() + " PDAServiceMonitorThread ");
            }catch(InterruptedException ex) { }
            try
            {
                while(true)
                {
                    Date nowDate = new Date();
                    Set<String> keySet = getPDASignInfoSet();
                    for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
						String pdaNo = it.next();
						PDASignInfo signInfo = getPDASignInfo(pdaNo);
						if(signInfo != null){
							if(nowDate.getTime()-signInfo.getUpdateTimestamp()> PDASignInfo.PDA_OFFLINE_TIMEOUT){
								
								try
								{
									BusinessWorker.deviceOffline(pdaNo);
								}catch(Exception ex1){}
								
								removePDASignInfo(pdaNo);
							}
						}
                    }
                    
                    try
                    {
                        Thread.sleep(1000*60*3); //休眠3分钟
                    }catch(InterruptedException ex)
                    { }
                }
            }catch(Exception e)
            {
                System.err.println("[PDAServiceMonitorThread error] = " + e.getMessage());
            }
        }
    }
}
