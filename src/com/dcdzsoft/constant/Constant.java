package com.dcdzsoft.constant;

import java.text.SimpleDateFormat;

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
public final class Constant {
    public static final SimpleDateFormat datetimeFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat dateFromat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 长字符串最大长度
     */
    public static final int LONGSTR_LEN = 40960; //40K

    public static final int CONTENT_MAX_LEN = 3900;
    
    public static final String SYSTEM_VERSION_1_2_3 = "v1.2.2";//添加软件升级管理模块

    /**
     * Operator
     */
    public static final String DEFAULT_SUPEROPERID = "181818"; //超级管理员编号
    public static final String DEFAULT_QUERYOPERID = "!@#4rfv*&^()"; //内部查询用

    public static final String DEFAULT_HEAD_DEPARTMENTID = "100000"; //

    /**
     * 系统运行模式
     */
    public static final int SYS_RUN_MODEL_FORWARD = 1; //转发
    public static final int SYS_RUN_MODEL_OPERATE = 2; //运营
    public static final int SYS_RUN_MODEL_MOBILE = 3; //运维


    /**
     * Operator type
     */
   public static final int OPER_TYPE_SYS = 1; //系统管理员

    /**
     * login Type
     */
    public static final String LOGIN_TYPE_INNER = "10";
    public static final String LOGIN_TYPE_HOTMAIL = "20";
    public static final String LOGIN_TYPE_FACEBOOK = "30";
    public static final String LOGIN_TYPE_GMAIL = "40";
    public static final String LOGIN_TYPE_YAHOO = "50";


    /**
     * ModuleID
     */
    public static final String MODULE_ID_OP = "13"; //操作员管理

    /**
     * 角色
     */
    public static final int ROLE_TYPE_COMMON = 1; //普通用户角色
    public static final int ROLE_TYPE_VIP = 2; //VIP用户角色
    public static final int ROLE_TYPE_SYS = 9; //管理部门角色



    /**
     * 用户状态
     */
    public static final String USER_STATUS_NORMAL = "0"; //正常
    public static final String USER_STATUS_LOGOUT = "1"; //注销(审核未通过)
    public static final String USER_STATUS_NOTACTIVATION = "2"; //注册未激活

    /**
     * 企业状态
     */
    public static final String CORP_STATUS_NORMAL = "0"; //正常
    public static final String CORP_STATUS_LOGOUT = "1"; //注销
    public static final String CORP_STATUS_NOTCHECED = "2"; //注册未审核
    public static final String CORP_STATUS_NOTPASSED = "3"; //审核未通过

    /**
     * 企业类型
     */
    public static final String CORP_TYPE_INNTERNET = "0"; //公网注册
    public static final String CORP_TYPE_INNER = "1"; //内部注册

    /**
     * 资料完善申请状态
     */
    public static final String IMPROVE_INFO_STATUS_FIRST = "1"; //初次申请
    public static final String IMPROVE_INFO_STATUS_SECOND = "2"; //二次申请

    /**
     * 审核状态
     */
    public static final String USER_CHECKFLAG_FAIL = "-1"; //审核未通过
    public static final String USER_CHECKFLAG_NOCHECK = "0"; //未审核
    public static final String USER_CHECKFLAG_PASS = "1"; //审核通过
    public static final String USER_CHECKFLAG_V = "2"; //加V标志




    /**
     * 显示标志
     */
    public static final String SHOW_FLAG_NO = "0"; //NO
    public static final String SHOW_FLAG_YES = "1"; //YES

    /**
     * 关注标志
     */
    public static final String CONCERN_FLAG_NO = "0"; //NO
    public static final String CONCERN_FLAG_YES = "1"; //YES

    /**
     * 好友组标识
     */
    public static final String FRIEND_GROUP_FLAG_COMMON = "0"; //common
    public static final String FRIEND_GROUP_FLAG_DEFAULT = "1"; //friend
    public static final String FRIEND_GROUP_FLAG_BLACK = "9"; //black


    /**
     * 记录日志标志
     */
    public static final String LOG_FLAG_NO = "0"; //不记录
    public static final String LOG_FLAG_MANUAL = "1"; //手动
    public static final String LOG_FLAG_AUTOMATIC = "5"; //自动
    public static final String LOG_FLAG_PAUSE = "6"; //暂停

    public static final String COMPANY_YOUZHENG_ID = "0001";
    
    public static final String COMPANY_ID = "10001";
    
    /**
     * 投递员登录方式
     */
    public static final String POSTMAN_LOGIN_TYPE_IDCARD = "2";//身份证登陆，不验证密码
    public static final String POSTMAN_LOGIN_TYPE_PDA = "PDA";//投递员PDA登录验证
    
    /**
     * apis 事件编码定义
     */
    public static final String ACTION_GET_CUSTOMERINFO     = "InParamPTPackageDetail";//从合作方查收件人手机
    public static final String ACTION_GET_CUSTOMERCARDINFO = "InParamMBCustomerBalanceQry";//从合作方查用户账户信息
    public static final String ACTION_GET_OPENBOXCHECK     = "InParamPTVerfiyUser";//由合作方验证提货码
    
    public static final String ACTION_SENT_DELIVERY       = "600076";
    public static final String ACTION_SENT_TERMINAL       = "600073";
    public static final String ACTION_SENT_BOXINFO        = "600074";
    public static final String ACTION_SENT_CHECKLINK      = "600075";//CheckLink
    public static final String ACTION_SENT_SMS            = "600071";
    public static final String ACTION_SENT_CUSTOMER_TOPUP = "600078";//发送会员充值记录到合作方
    public static final String ACTION_SENT_CUSTOMER_BANDCARD = "600079";//绑定会员卡
    
    /**
     * 软件类型
     */
    public static final String SOFTWARE_TYPE_DRIVE="1";//驱动软件
    public static final String SOFTWARE_TYPE_TERMINAL="2";//终端软件 
    public static final String SOFTWARE_TYPE_SERVER="3";//服务端软件升级包 
    
    /**
     * 系统类型
     */
    public static final String SYSTEM_TYPE_WINDOWS="1";   //windows系统
    public static final String SYSTEM_TYPE_LINUX="2";  //linux系统
    public static final String SYSTEM_TYPE_ANDROID="3";  //android系统
    public static final String SYSTEM_TYPE_OS="4";  //os系统
    
    public static final String DEFAULT_SYSTEM_ID = "10001";
    /**
     * 短信长度最短值
     */
    public static final int SMS_SIZE_BYTE = 68;
    
    public static final int PUSH_FAILURE_COUNT_MAX = 100;
    /**
     *发送设备离线监控邮件,在设定时间到2倍时间以内认定为离线，只上传一次，在设定时间以内检测到的其他异常，只上传一次
     */
    public static final int LOCKER_OFFLINE_DELAY_MINUTES = 15;
    /**
     * 合作伙伴定义
     */
    public static final String PARTNER_NAME_HAIER      = "haier";//海尔
    public static final String PARTNER_NAME_DIGIKALA   = "digikala";//伊朗，Digikala
    public static final String PARTNER_NAME_TAIHE      = "taiheshengxian";//湖南岳阳泰和生鲜柜
    public static final String PARTNER_NAME_LVCHENG    = "lvcheng";//绿城服务
    public static final String PARTNER_NAME_KUAILIFANG = "kuailifang";//百隆达快立方
    
    public static final String RETURN_GOODS_ORDER_REGEX = "#";//退件订单号=订单号+"#"+商品编号   应用于泰和生鲜
}
