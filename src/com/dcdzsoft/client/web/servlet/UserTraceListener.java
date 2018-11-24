package com.dcdzsoft.client.web.servlet;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

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

public class UserTraceListener implements HttpSessionBindingListener
{
    private UserLoginInfo user;
	private UserLoginInfoList container = UserLoginInfoList.getInstance();

    public void valueBound(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void valueUnbound(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
        container.removeUser(user);

        //调用登出业务
        com.dcdzsoft.dto.business.InParamOPOperLogout operLogout = new com.dcdzsoft.dto.business.InParamOPOperLogout();

        operLogout.setOperID(user.getOperID());
        try
        {
            com.dcdzsoft.client.web.OPWebClientAdapter.doBusiness(operLogout);
        }
        catch(com.dcdzsoft.EduException e)
        {
            System.err.println("logout error:" + e.getMessage());
        }

    }

    public UserLoginInfo getUser() {
        return user;
    }

    public void setUser(UserLoginInfo user) {
        this.user = user;
    }

}
