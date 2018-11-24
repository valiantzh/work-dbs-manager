package com.dcdzsoft.client.web.servlet;

import java.util.ArrayList;
import java.util.List;

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

public class UserLoginInfoList {
    private List container;

    private static UserLoginInfoList instance = new UserLoginInfoList();

    private UserLoginInfoList() {
        container = new ArrayList();
    }

    public static UserLoginInfoList getInstance() {
        return instance;
    }

    public void addUser(UserLoginInfo user) {
        container.add(user);
    }

    public List getUserList() {
        return container;
    }

    public void removeUser(UserLoginInfo user) {
        if (user != null)
            container.remove(user);
    }
}
