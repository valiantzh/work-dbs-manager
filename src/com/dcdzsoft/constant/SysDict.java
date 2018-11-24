package com.dcdzsoft.constant;

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
public final class SysDict {
    public static final int CNST_DICT_MODULE = 11001; //系统模块

    public static final int CNST_DICT_OPERTYPE = 13001; //操作员类别
    public static final int CNST_DICT_OPERSTATUS = 13002; //操作员状态

    //管理员类型
    public final static String OPER_TYPE_SUPER = "1"; //超级管理员
    public final static String OPER_TYPE_SPOT = "3"; //现场管理人员
    public final static String OPER_TYPE_POSTMAN = "99"; //投递员

    //15051-个人客户状态
    public final static String CUSTOMER_STATUS_NROMAL   = "0"; //正常
    public final static String CUSTOMER_STATUS_CANCEL   = "1"; //注销
    public final static String CUSTOMER_STATUS_UNACTIVE = "2"; //注册未激活
    
    //15055-会员类型
    public final static String CUSTOMER_TYPE_NROMAL  = "0"; //普通客户
    public final static String CUSTOMER_TYPE_APP     = "10"; //APP用户
    public final static String CUSTOMER_TYPE_WEIXIN  = "11"; //微信用户
    public final static String CUSTOMER_TYPE_DELIVERUSER = "20";//投递服务用户（即收件人）
    public final static String CUSTOMER_TYPE_POSTMAN = "99"; //投递人员
    
    //服务状态
    public final static String DELIVER_SERVICE_STATUS_NROMAL   = "0"; //正常
    public final static String DELIVER_SERVICE_STATUS_CANCEL   = "1"; //注销
    public final static String DELIVER_SERVICE_STATUS_UNACTIVE = "2"; //未激活
    
    
    //用户特殊标志
    public final static String CUSTOMER_SPECIAL_FLAG_NO  = "0";//正常
    public final static String CUSTOMER_SPECIAL_FLAG_YES = "1";//残障
    
    
    //投递异常处理
    public final static String DELIVER_FAIL_CONTACTUSER  = "0";//联系用户
    public final static String DELIVER_FAIL_GOTOOFFICE   = "1";//放到收发室
    public final static String DELIVER_FAIL_RETURNTOSENDER = "2";//退回给寄件人
    
    
    //投递员类型
    public final static String POSTMAN_TYPE_POST = "1"; //投递员
    public final static String POSTMAN_TYPE_LEFT = "2"; //寄存客户
    public final static String POSTMAN_TYPE_CLEAR = "3"; //生鲜配送员

    //投递员身份地点
    public final static String POSTMAN_CHECKSOURCE_LOCAL = "0"; //本地
    public final static String POSTMAN_CHECKSOURCE_NETWORK = "1"; //网络

    //投递员身份验证方式
    public final static String POSTMAN_CHECKMODEL_DYNAMIC = "0"; //动态密码认证
    public final static String POSTMAN_CHECKMODEL_STATIC = "1"; //静态密码认证

    //投递员登陆认证标志
    public final static String POSTMAN_VERIFY_CODE = "0"; //动态验证码
    public final static String POSTMAN_VERIFY_PWD = "1"; //密码认证

    //投递员密码存储是否使用MD5加密
    public final static String POSTMAN_PWD_MD5_NO = "0"; //不使用
    public final static String POSTMAN_PWD_MD5_YES = "1"; //使用

    public final static String PASSWD_CHECKMODEL_COMMON = "0"; //普通MD5
    public final static String PASSWD_CHECKMODEL_JINGDONG = "1"; //京东MD5
    public final static String PASSWD_CHECKMODEL_HZYOUCHENG = "2"; //杭州邮政


    //投递员柜体权限验证模式
    public final static String POSTMAN_LOCKRIGHT_JUDGE_NO = "0"; //不需要
    public final static String POSTMAN_LOCKRIGHT_JUDGE_COMPANY = "1"; //验证公司
    public final static String POSTMAN_LOCKRIGHT_JUDGE_PERSON = "2"; //验证本人

    //投递员格口权限验证模式
    public final static String POSTMAN_BOXRIGHT_JUDGE_NO = "0"; //不需要
    public final static String POSTMAN_BOXRIGHT_JUDGE_COMPANY = "1"; //验证公司
    public final static String POSTMAN_BOXRIGHT_JUDGE_PERSON = "2"; //验证本人

    //投递订单来源
    public final static String ORDER_DELIVERY_SOURCE_NO = "0"; //无
    public final static String ORDER_DELIVERY_SOURCE_NETWORK = "1"; //网络
    public final static String ORDER_DELIVERY_SOURCE_LOCAL = "2"; //本地

    //列表外订单处理方式
    public final static String ORDERS_NOTINLIST_NO = "0"; //不允许投递
    public final static String ORDERS_NOTINLIST_YES = "1"; //允许投递

    //订单需要网络验证
    public final static String ORDER_NEED_NETCHCK_NO = "0"; //不需要
    public final static String ORDER_NEED_NETCHCK_YES = "1"; //需要


    //订单号格式处理方式(扫描出特殊字符后,提示重刷(26个字符加数字加-,存储时候替换-为空)
    public final static String ORDER_FORMATWAY_COMMON = "0"; //普通
    public final static String ORDER_FORMATWAY_JINGDONG = "1"; //京东

    //回收订单来源
    public final static String RECOVERY_DELIVERY_SOURCE_NETWORK = "1"; //从服务器下载
    public final static String RECOVERY_DELIVERY_SOURCE_LOCAL = "2"; //从本地库验证

    //回收范围
    public final static String RECOVERY_DELIVERY_SCOPE_PERSON = "1"; //投递员
    public final static String RECOVERY_DELIVERY_SCOPE_COMPANY = "2"; //投递公司

    //寄存标志
    public final static String PACKAGE_LEFT_FLAG_N0 = "0"; //投递包裹
    public final static String PACKAGE_LEFT_FLAG_YES = "1"; //寄存包裹

    //支付标志
    public final static String PAY_FLAG_NO = "0"; //不需要支付
    public final static String PAY_FLAG_YES = "1"; //需要支付
    public final static String PAY_FLAG_SUCCESS = "2"; //支付成功

    //取件密码生成来源
    public final static String TAKEOUTPWD_SOURCE_LOCAL_NO = "0"; //本地不生成
    public final static String TAKEOUTPWD_SOURCE_LOCAL_YES = "1"; //本地生成

    //取件密码组成格式
    public final static String TAKEOUTPWD_FORM_NUMBER = "0"; //数字
    public final static String TAKEOUTPWD_FORM_CHAR = "1"; //字母
    public final static String TAKEOUTPWD_FORM_NUMBERCHAR = "2"; //字母数字组合

    //取件验证模式
    public final static String TAKEOUTCHECK_MODEL_LOCAL = "1"; //本地网络
    public final static String TAKEOUTCHECK_MODEL_NETWORK = "2"; //网络验证

    //取件密码MD5加密类型
    public final static String TAKEOUTPWD_MD5TYPE_COMMON = "0"; //标准
    public final static String TAKEOUTPWD_MD5TYPE_JINGDONG = "1"; //京东


    //取件验证组合方式
    public final static String TAKEOUTCHECK_TYPE_PACKAGEID = "1"; //单号+密码
    public final static String TAKEOUTCHECK_TYPE_MOBILE = "2"; //手机号+密码
    public final static String TAKEOUTCHECK_TYPE_MOBILEAFTER4 = "3"; //手机号后4位+密码
    public final static String TAKEOUTCHECK_TYPE_PACKORMOBILE = "4"; //单号或手机号+密码
    public final static String TAKEOUTCHECK_TYPE_CARDID = "5"; //会员卡+提货码
    public final static String TAKEOUTCHECK_TYPE_PWD = "6"; //提货码
    public final static String TAKEOUTCHECK_TYPE_4PACKID = "8"; //单号后4位+密码
    public final static String TAKEOUTCHECK_TYPE_4PACKORMOBILE = "9"; //单号后4位或手机号+密码

    //取件方式-33021
    public final static String TAKEOUT_WAY_PWD     = "0";  //提货码
    public final static String TAKEOUT_WAY_QRCODE  = "1";  //二维码
    public final static String TAKEOUT_WAY_CARDID  = "2";  //刷卡
    public final static String TAKEOUT_WAY_UNKNOWN = "9"; //未知
	
    //包裹状态
    public final static String PACKAGE_STATUS_NORMAL = "0"; //正常状态
    public final static String PACKAGE_STATUS_LOCKED = "1"; //锁定状态
    public final static String PACKAGE_STATUS_CANCEL = "2"; //取消状态(删除) 历史记录 ???
    public final static String PACKAGE_STATUS_TIMEOUT = "3"; //超时状态
    public final static String PACKAGE_STATUS_OUT4POSTMAN = "5"; //投递员取回
    public final static String PACKAGE_STATUS_OUTEXPIRED = "6"; //逾期回收
    public final static String PACKAGE_STATUS_OUTEXCEPTION = "7"; //异常回收
    public final static String PACKAGE_STATUS_OUTNORMAL = "8"; //正常取件
    public final static String PACKAGE_STATUS_OUT4MANAGER = "9"; //管理员取件
    
    //衣物类包裹状态
    public final static String PACKAGE_STATUS_DIRTYCLOTH = "10"; //脏衣服待取
    public final static String PACKAGE_STATUS_WASHING = "11"; //正在洗衣
    public final static String PACKAGE_STATUS_CLEANCLOTH = "12"; //干净衣服待取
    public final static String PACKAGE_STATUS_PICKUPDIRTY = "17";//脏衣服已取
    public final static String PACKAGE_STATUS_PICKUPCLEAN = "18";//干净衣服已取
    public final static String PACKAGE_STATUS_WASHED = "19";//洗衣完成
    
    //包裹类型
    public final static String PACKAGE_TYPE_ORDINARYPACK = "0"; //普通包裹
    public final static String PACKAGE_TYPE_DRUG = "1"; //药品类
    public final static String PACKAGE_TYPE_CLOTHES = "2"; //衣物类
    
    //洗衣方式
    public final static String PACKAGE_LAUNDRY_WAY0 = "0"; //水洗烘干
    public final static String PACKAGE_LAUNDRY_WAY1 = "1"; //水洗熨烫
    public final static String PACKAGE_LAUNDRY_WAY2 = "2"; //干洗
    public final static String PACKAGE_LAUNDRY_WAY3 = "3"; //其他
    
    //退货商品状态（泰和）
    public final static String PACKAGE_STATUS_RETURNGOODS = "20";//退货在箱
    public final static String PACKAGE_STATUS_OUTRETURNGOODS = "25";//退货取回
    public final static String PACKAGE_STATUS_OUTRETURNGOODS4MANAGER = "29";//退货管理员取回
    
    //上传标志
    public final static String UPLOAD_FLAG_NO = "0"; //未上传
    public final static String UPLOAD_FLAG_YES = "1"; //已上传
    public final static String UPLOAD_FLAG_FAILURE = "2"; //上传失败


    //柜体状态
    public final static String TERMINAL_STATUS_NORMAL = "0"; //正常
    public final static String TERMINAL_STATUS_LOCKED = "1"; //锁定
    public final static String TERMINAL_STATUS_FAULT = "2"; //故障
    public final static String TERMINAL_STATUS_FAULTLOCKED = "3"; //故障锁定
    public final static String TERMINAL_STATUS_FAULT_POWERFAIL = "4"; //掉电故障Power-fail
    public final static String TERMINAL_STATUS_CANCEL          = "5"; //5-注销cancel
    public final static String TERMINAL_STATUS_FAULT_OFFLINE   = "6"; //离线故障Off-line
    public final static String TERMINAL_STATUS_FAULT_BOX       = "7"; //存在箱门故障
    public final static String TERMINAL_STATUS_UNKNOWN = "9"; //未知

    //柜体注册标志
    public final static String TERMINAL_REGISTERFLAG_NO = "0"; //未注册
    public final static String TERMINAL_REGISTERFLAG_YES = "1"; //已注册
    public final static String TERMINAL_REGISTERFLAG_FAILURE = "2"; //注册失败

    //箱体状态
    public final static String BOX_STATUS_NORMAL = "0"; //正常
    public final static String BOX_STATUS_LOCKED = "1"; //锁定
    public final static String BOX_STATUS_FAULT = "2"; //故障
    public final static String BOX_STATUS_FAULTLOCKED = "4"; //故障锁定
    public final static String BOX_STATUS_USED = "8"; //占用
    public final static String BOX_STATUS_UNKNOWN = "9"; //未知

    //箱锁定状态
    public final static String BOX_LOCKEDSTATUS_NO = "0"; //正常
    public final static String BOX_LOCKEDSTATUS_YES = "1"; //已锁定

    //箱故障状态
    public final static String BOX_FAULTSTATUS_NO = "0"; //正常
    public final static String BOX_FAULTSTATUS_YES = "1"; //故障

    //箱体使用状态
    public final static String BOX_USED_STATUS_FREE = "0"; //空闲
    public final static String BOX_USED_STATUS_NORMAL = "1"; //订单正常
    public final static String BOX_USED_STATUS_LOCKED = "2"; //订单锁定
    public final static String BOX_USED_STATUS_CANCEL = "3"; //订单取消
    public final static String BOX_USED_STATUS_EXPIRED = "4"; //订单超时
    public final static String BOX_USED_STATUS_UNKNOWN = "9"; //未知

    //箱物品状态
    public final static String BOX_ARTICLE_STATUS_NON = "0"; //无
    public final static String BOX_ARTICLE_STATUS_EXIST = "1"; //有
    public final static String BOX_ARTICLE_STATUS_UNKNOWN = "9"; //未知
    
    //开关箱种类
    public final static String OPEN_CLOSE_TYPE_OPEN2STORE = "1";//存开箱
    public final static String OPEN_CLOSE_TYPE_OPEN2TAKEOUT = "2";//取开箱
    public final static String OPEN_CLOSE_TYPE_OPEN2MANAGER = "3";//管理开箱
    public final static String OPEN_CLOSE_TYPE_OPEN2UNKNOWN = "9";//未知开箱（非法开箱）
    public final static String OPEN_CLOSE_TYPE_CLOSAFTERSTORE = "11";//存件后关箱
    public final static String OPEN_CLOSE_TYPE_CLOSAFTERTAKEOUT = "12";//取后关箱
    public final static String OPEN_CLOSE_TYPE_CLOSAFTERMANAGER = "13";//管理开箱后关箱
    public final static String OPEN_CLOSE_TYPE_CLOSAFTERUNKNOWN = "19";//未知开箱后关箱
    
    //开关箱状态
    public final static String OPEN_CLOSE_BOX_STATUS_OPENNING = "0";//开箱中
    public final static String OPEN_CLOSE_BOX_STATUS_OPENSUCCESS = "1";//开箱成功
    public final static String OPEN_CLOSE_BOX_STATUS_OPENFAIL = "2";//开箱失败
    public final static String OPEN_CLOSE_BOX_STATUS_CLOSESUCCESS = "11";//箱门已关
    public final static String OPEN_CLOSE_BOX_STATUS_CLOSEFAIL = "12";//箱门未关
    
    
    //箱门状态
    public final static String BOX_DOOR_STATUS_CLOSE = "0"; //关
    public final static String BOX_DOOR_STATUS_OPEN = "1"; //开
    public final static String BOX_DOOR_STATUS_UNKNOWN = "9"; //未知

    //箱类型
    public final static String BOX_TYPE_SMALL = "0"; //小
    public final static String BOX_TYPE_MEDIAL = "1"; //中
    public final static String BOX_TYPE_BIG = "2"; //大
    public final static String BOX_TYPE_HUGE = "3"; //超大
    public final static String BOX_TYPE_FRESH = "7"; //生鲜
    public final static String BOX_TYPE_UNKNOWN = "9"; //未知

    public final static int STATUS_ABNORMAL = 0; //不正常
    public final static int STATUS_NORMAL = 1; //正常

    //外设需要标志
    public final static String DEVICE_NEED_FLAG_NO = "0"; //不需要
    public final static String DEVICE_NEED_FLAG_YES = "1"; //需要

    //需要同步数据标志
    public final static String SYNC_DATA_NEED_FLAG_NO = "0"; //不需要
    public final static String SYNC_DATA_NEED_FLAG_YES = "1"; //需要

    //本地远程操作标志
    public final static String OPER_FLAG_LOCAL = "LOCAL"; //本地操作
    public final static String OPER_FLAG_REMOTE = "REMOTE"; //远程操作

    public final static String ALERT_TYPE_SHOCK = "11"; //震动
    public final static String ALERT_TYPE_RESISTANT = "12"; //防撬
    public final static String ALERT_TYPE_POWERLOW = "13"; //电源不足
    public final static String ALERT_TYPE_DRIVERERROR = "16"; //驱动板异常
    public final static String ALERT_TYPE_HIGHTEMP = "21"; //高温异常
    public final static String ALERT_TYPE_LOWTERMP = "23"; //低温异常
    public final static String ALERT_TYPE_DEVICEFAULT = "31"; //柜体故障
    public final static String ALERT_TYPE_CARDFAULT = "33"; //读卡器故障
    public final static String ALERT_TYPE_BARCODEFAULT = "34"; //条码扫描仪故障
    public final static String ALERT_TYPE_PRINTFAULT = "35"; //打印机故障
    public final static String ALERT_TYPE_BOXFAULT = "41"; //箱体故障
    public final static String ALERT_TYPE_BOXNOTCLOSED = "43"; //箱门长时间未关
    public final static String ALERT_TYPE_NETWORKCLOSED = "91"; //网络异常
    
    public final static String ALERT_LEVEL_SLIGHT = "A"; //轻微
    public final static String ALERT_LEVEL_COMMON = "B"; //一般
    public final static String ALERT_LEVEL_URGENCY = "C"; //紧急
    public final static String ALERT_LEVEL_CRITICAL = "D"; //严重
    
    public final static String UPLOAD_LOG_TYPE_INBOX = "inbox";            //在箱信息
    public final static String UPLOAD_LOG_TYPE_INBOX2DB = "inbox2db";      //入库在箱信息
    public final static String UPLOAD_LOG_TYPE_HISTORY = "history";        //投递记录
    public final static String UPLOAD_LOG_TYPE_HISTORY2DB = "history2db";   //入库投递记录
    public final static String UPLOAD_LOG_TYPE_NOTMATCH = "detectresult";  //不一致结果
    public final static String UPLOAD_LOG_TYPE_SYSLOG = "syslog";          //系统日志
    public final static String UPLOAD_LOG_TYPE_OPERLOG = "operlog";         //管理员日志
    public final static String UPLOAD_LOG_TYPE_CTRLPARAM = "ctrlparam";     //控制参数
    public final static String UPLOAD_LOG_TYPE_FAILDATA = "faildata";       //失败上传数据
    public final static String UPLOAD_LOG_TYPE_CHECKACCOUNT = "checkacc";   //上传对账文件
    public final static String UPLOAD_LOG_TYPE_TEMPCHECKACCOUNT = "manualcheckacc";   //上传对账文件
    
    public final static String APPEAL_STATUS_REQUESTKEY = "1"; //申请开箱密码
    public final static String APPEAL_STATUS_READYOPEN = "3"; //准备开箱
    public final static String APPEAL_STATUS_OPENED = "5"; //开箱成功
    
    public final static int SPEPRIV_OPENBOX = 1; //远程开箱
    public final static int SPEPRIV_MODWELCOMEINFO = 2; //修改设备端欢迎词
    public final static int SPEPRIV_QUREYPICKUPKEY = 3;//查询取件密码
    public final static int SPEPRIV_MODINITPASSWD = 4;//修改签到密码
    public final static int SPEPRIV_MODTERMINALSOFT = 5;//修改终端软件版本
    
  //订单转入标志
    public final static String INPUTMOBILE_FLAG_LOCAL = "0"; //寄件，需要输入手机
    public final static String INPUTMOBILE_FLAG_TRANSFER = "1"; //转入，手机回显不能修改
    public final static String INPUTMOBILE_FLAG_NETWORK = "2"; //网络获取，手机回显可以修改
    
    public final static String POSTMAN_STATUS_NORMAL = "0"; //投递员状态 正常
    public final static String POSTMAN_STATUS_CANCEL = "1"; //投递员状态 注销
    
    //支付信息管理(41001-41999)
    //41001 账单类型
    public final static String BILL_TYPE_TOPUP    = "1"; //账单类型  充值
    public final static String BILL_TYPE_CONSUMED = "2"; //账单类型 格口租金
    public final static String BILL_TYPE_REFOUND  = "3"; //退回-格口租金
    public final static String BILL_TYPE_CANCEL   = "9"; //账单类型 取消充值
    
    //41003 格口收费类型
    public final static String CHARGE_TYPE_USAGE   = "1"; //按次收费
    public final static String CHARGE_TYPE_HOURS   = "4"; //按小时
    public final static String CHARGE_TYPE_DAYS    = "5"; //按天
    public final static String CHARGE_TYPE_WEEKS   = "6"; //按周
    public final static String CHARGE_TYPE_MONTHS  = "7"; //按月
    public final static String CHARGE_TYPE_QUQRTER = "8"; //按季
    public final static String CHARGE_TYPE_YEARS   = "9"; //按年
    
    
    //41005 格口收费模式
    public final static String CHARGE_MODE_PREPAY   = "1"; //预收费
    public final static String CHARGE_MODE_NORMAL   = "2"; //正常收费
    public final static String CHARGE_MODE_EXPIRED  = "3"; //逾期收费
    
    //41011 计费方式 Billing method
    public final static String BILL_METHOD_USAGE    = "1"; //计费方式  按实际使用量
    public final static String BILL_METHOD_MONTHLY  = "2"; //计费方式 包月 Monthly
    public final static String BILL_METHOD_YARELY   = "3"; //计费方式 包年  yearly
    
    //41013 短信计费单位
    public final static String BILL_UNITS_USAGE   = "1"; //计费方式  按实际使用量
    public final static String BILL_UNITS_MONTHL  = "2"; //计费方式 包月 Monthly
    public final static String BILL_UNITS_YAREL   = "3"; //计费方式 包年  yearly
    
    //41021 账号状态
    public final static String SMS_ACCOUNT_STATUS_NORMAL  = "0"; //账号状态 正常
    public final static String SMS_ACCOUNT_STATUS_CANCEL  = "1"; //账号状态 注销
    public final static String SMS_ACCOUNT_STATUS_DISABLE = "2"; //账号状态 停用

    //41031 卡类型
    public final static String CARD_TYPE_IDCARD  = "1"; //卡类型 身份证
    public final static String CARD_TYPE_ICCARD  = "2"; //卡类型 普通IC卡
    public final static String CARD_TYPE_RECHARGECARD = "9"; //卡类型 会员充值卡（余额在合作方系统）
    public final static String CARD_TYPE_BANKCARD = "10"; //卡类型     银行卡 （余额在银行）
    
    //41033 卡状态
    public final static String CARD_STATUS_NORMAL  = "0"; //卡状态 正常
    public final static String CARD_STATUS_CANCEL  = "1"; //卡状态 注销
    public final static String CARD_STATUS_DISABLE = "2"; //卡状态 停用
    
    //41035 卡余额单位
    public final static String CARD_BALANCE_UNIT_YUAN = "0"; //卡余额单位   元
    public final static String CARD_BALANCE_UNIT_ITEM = "1"; //卡余额单位  次/条
    public final static String CARD_BALANCE_UNIT_HOUR = "4"; //卡余额单位  小时
    public final static String CARD_BALANCE_UNIT_DAY  = "5"; //卡余额单位   天
    
    //41037 充值数量单位
    public final static String TOPUP_NUM_UNIT_YUAN    = "0"; //充值数量单位   元
    public final static String TOPUP_NUM_UNIT_ITEM    = "1"; //充值数量单位  次/条
    public final static String TOPUP_NUM_UNIT_HOUR    = "4"; //充值数量单位  小时
    public final static String TOPUP_NUM_UNIT_DAY     = "5"; //充值数量单位   天    
    public final static String TOPUP_NUM_UNIT_WEEK    = "6"; //充值数量单位   周    
    public final static String TOPUP_NUM_UNIT_MONTH   = "7"; //充值数量单位    月   
    public final static String TOPUP_NUM_UNIT_QUARTER = "8"; //充值数量单位   季度    
    public final static String TOPUP_NUM_UNIT_YEAR    = "9"; //充值数量单位   年   
    
    //短信发送模式
    public final static String SENDDELIVERSMS_NO           = "0";//发送投递消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
    public final static String SENDDELIVERSMS_TORECEIVER   = "1";//发送投递消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
    public final static String SENDDELIVERSMS_TOSENDER     = "2";//发送投递消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
    public final static String SENDDELIVERSMS_TOALL        = "3";//发送投递消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人

	public final static String SENDPICKUPSMS_NO            = "0";//发送取件消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
	public final static String SENDPICKUPSMS_TORECEIVER    = "1";//发送取件消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
	public final static String SENDPICKUPSMS_TOSENDER      = "2";//发送取件消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
	public final static String SENDPICKUPSMS_TOALL         = "3";//发送取件消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人

	public final static String SENDEXPIREDSMS_NO           = "0";//发送逾期消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
	public final static String SENDEXPIREDSMS_TORECEIVER   = "1";//发送逾期消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
	public final static String SENDEXPIREDSMS_TOSENDER     = "2";//发送逾期消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人
	public final static String SENDEXPIREDSMS_TOALL        = "3";//发送逾期消息： 0-不发送，1-发给收件人，2-发给寄件人，3-发给寄件人和收件人

	public final static String SENDREMINDERSMS_NO          = "0";//发送催领消息： 0-不发送，1-发给收件人
	public final static String SENDREMINDERSMS_TORECEIVER  = "1";//发送催领消息： 0-不发送，1-发给收件人    
	
	
	
}
