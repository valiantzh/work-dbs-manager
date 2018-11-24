package com.dcdzsoft.sequence;

import org.apache.commons.collections.FastHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dcdzsoft.util.Loader;
import com.dcdzsoft.EduException;
import com.dcdzsoft.constant.ErrorCode;

/**
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
public class SequenceGenerator {
    private static final Log log = LogFactory.getLog(SequenceGenerator.class.
            getName());

    /**
     * 流水号
     */
    public static final String SEQ_OPERLOGID = "SeqOperLogID"; //操作员日志序号
  	public static final String SEQ_REPORTWATERID = "SeqReportWaterID"; //报告流水编号
    public static final String SEQ_WATERID = "SeqWaterID"; //流水编号
    public static final String SEQ_TRADEWATERID = "SeqTradeWaterID"; //交易流水编号
    public static final String SEQ_UPGRADEWATERID = "SeqUpgradeWaterID";//软件更新流水编号
    

    private static final String SEQ_PACKAGE = "com.dcdzsoft.sequence.";

    /*
     * 延迟加载，对多线程友好
     */
    private static class SingletonHolder{
    	private static SequenceGenerator instance = new SequenceGenerator();
    }

    private FastHashMap keyList = new FastHashMap(20);

    private SequenceGenerator() {
        keyList.setFast(true);
    }

    /**
     * 单例模式，提供自己的实例
     * @return KeyGenerator instance
     */
    public static SequenceGenerator getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * @param className The class name of SequenceGenerator
     * @return sequence number
     * @throws EduException
     */
    public long getNextKey(String className) throws EduException {
        Sequence sequence = (Sequence) keyList.get(className);

        if (sequence == null) {
            synchronized (keyList) {
                log.debug(
                        "  Double checking for KeyInfo instance already there. Class name " +
                        className);
                // Double check to avoid a race condition
                sequence = (Sequence) keyList.get(className);
                if (sequence == null) {
                    // Go ahead and create the new Sequence instance
                    // ASSERT:  This will never ever happen more than once
                    //  for a particular Sequence class name
                    try {
                        log.debug("  Creating new Sequence instance");
                        Class<?> clazz = Loader.loadClass(SEQ_PACKAGE + className);
                        sequence = (Sequence) clazz.newInstance();
                        sequence.initValue();
                        keyList.put(className, sequence);
                    } catch (Throwable t) {
                        log.error(
                                "Error creating Sequence instance :" +
                                "', class name '" +
                                className + "'", t);

                        throw new EduException(ErrorCode.ERR_GETSEQUENCEERR);
                    }
                }
            }
        }

        return sequence.getNextKey();
    }

}
