package com.dcdzsoft.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dcdzsoft.sda.aop.Interceptor;
import com.dcdzsoft.sda.aop.InvocationInfo;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.EduException;
import com.dcdzsoft.config.ErrorMsgConfig;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.Constant;

import com.dcdzsoft.dao.factory.DAOFactory;
import com.dcdzsoft.dao.common.*;

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
public class ActionBean implements Interceptor
{
    /**
     *The logger
     */
    protected static Log log = org.apache.commons.logging.LogFactory.getLog(
        ActionBean.class);

    /**
     * 工厂类
     */
    protected DAOFactory daoFactory = null;

    /**
     * DBSession
     */
    protected DBSession dbSession = null;

    /**
     * 公共函数类
     */
    protected CommonDAO commonDAO = null;

    /**
     * 工具类
     */
    protected UtilDAO utilDAO = null;

    protected int sysRunModel = 1;

    public ActionBean()
    {
        daoFactory = DAOFactory.getDAOFactory(DataSourceUtils.getInstance().getDatabaseType());
        sysRunModel = ApplicationConfig.getInstance().getSysRunModel();
    }

    /***
     * Interceptor implements
     */

    /**
     * before
     *
     * @param invocationInfo InvocationInfo
     * @throws EduException
     */
    public void before(InvocationInfo invocationInfo)
        throws EduException
    {
    	DBSession session = new DBSession();
        LocalSessionHolder.setCurrentDBSession(session);

        //处理功能是否开通进行判断
    }

    /**
     * after
     *
     * @param invocationInfo InvocationInfo
     * @throws EduException
     */
    public void after(InvocationInfo invocationInfo)
        throws EduException
    {
        DBSession session = LocalSessionHolder.getCurrentDBSession();
        if (session != null)
            session.commit();
    }

    /**
     * last
     *
     * @param invocationInfo InvocationInfo
     * @throws EduException
     */
    public void last(InvocationInfo invocationInfo)
        throws EduException
    {
        DBSession session = LocalSessionHolder.getCurrentDBSession();
        if (session != null)
            session.closeConnection();

        LocalSessionHolder.clearDBSession();
    }

    /**
     * exceptionThrow
     *
     * @param invocationInfo InvocationInfo
     * @throws EduException
     */
    public void exceptionThrow(InvocationInfo invocationInfo)
        throws EduException
    {
        DBSession session = LocalSessionHolder.getCurrentDBSession();
        if (session != null)
            session.rollback();

        Throwable e = invocationInfo.getException();
        if(e instanceof EduException){
        }else{
            e.printStackTrace();
        }

        //if(e.getMessage() == null || "-1".equals(e.getMessage()))
        //    e.printStackTrace();

        if(e.getMessage() != null)
            log.error("[BUSIERROR-(" + e.getMessage() + ")]" + e.getMessage());

    }

    //常用对象
    public UtilDAO getUtilDAO()
    {
    	return daoFactory.getUtilDAO();
    }

    public CommonDAO getCommonDAO()
    {
    	return daoFactory.getCommonDAO();
    }

    public DBSession getCurrentDBSession()
    {
        return LocalSessionHolder.getCurrentDBSession();
    }

    public boolean isForwardModel(){
        if(sysRunModel == Constant.SYS_RUN_MODEL_FORWARD)
            return true;

        return false;
    }
}
