package com.dcdzsoft.aop;

import com.dcdzsoft.sda.aop.*;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: dcdzsoft</p>
 *
 * @author wangzl
 * @version 1.0
 */
public class BusiBeanAOPBaseClass
    extends AOPBaseClass
{

    public BusiBeanAOPBaseClass()
    {
        super();

        //initInterMethodNames();
        //initInterceptors();
    }

    /**
     * 静态工厂方法，返还此类的惟一实例(???)
     * @return a BusiBeanAOPBaseClass instance
     */
    public static BusiBeanAOPBaseClass getInstance()
    {
        return new BusiBeanAOPBaseClass();
    }

    /**
     * initInterMethodNames
     *
     * @todo Implement this com.dcdzsoft.sda.aop.AOPBaseClass method
     */
    protected void initInterMethodNames()
    {
        interMethodNames.add("doBusiness");
    }

    /**
     * initInterceptors
     *
     * @todo Implement this com.dcdzsoft.sda.aop.AOPBaseClass method
     */
    protected void initInterceptors()
    {
        interceptors.add(new com.dcdzsoft.business.ActionBean());
    }
}
