package com.dcdzsoft.businessproxy;

import com.dcdzsoft.aop.BusiBeanAOPBaseClass;
import com.dcdzsoft.dto.business.*;

/**
 * 
 * <p>
 * Title: 智能自助包裹柜系统
 * </p>
 * 
 * <p>
 * Description: 暴露给移动端所有业务接口
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * 
 * <p>
 * Company: 杭州东城电子有限公司
 * </p>
 * 
 * @author wangzl
 * @version 1.0
 */
public class MobileProxy {
	/**
	 * 投递员身份验证
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static OutParamAPPostmanCheck doBusiness(InParamAPPostmanCheck p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanCheck bean = (com.dcdzsoft.business.ap.APPostmanCheck) aop
				.bind(com.dcdzsoft.business.ap.APPostmanCheck.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 投递员未取包裹列表
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamAPPostmanInboxPackage> doBusiness(InParamAPPostmanInboxPackage p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanInboxPackage bean = (com.dcdzsoft.business.ap.APPostmanInboxPackage) aop
				.bind(com.dcdzsoft.business.ap.APPostmanInboxPackage.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 投递员包裹列表
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamAPPostmanPackageList> doBusiness(InParamAPPostmanPackageList p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanPackageList bean = (com.dcdzsoft.business.ap.APPostmanPackageList) aop
				.bind(com.dcdzsoft.business.ap.APPostmanPackageList.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 投递员包裹列表总记录数
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static int doBusiness(InParamAPPostmanPackageCount p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanPackageCount bean = (com.dcdzsoft.business.ap.APPostmanPackageCount) aop
				.bind(com.dcdzsoft.business.ap.APPostmanPackageCount.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 投递员单个包裹查询
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static OutParamAPPostmanPackageDetail doBusiness(InParamAPPostmanPackageDetail p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanPackageDetail bean = (com.dcdzsoft.business.ap.APPostmanPackageDetail) aop
				.bind(com.dcdzsoft.business.ap.APPostmanPackageDetail.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 投递员包裹统计
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamAPPostmanPackageStat> doBusiness(InParamAPPostmanPackageStat p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanPackageStat bean = (com.dcdzsoft.business.ap.APPostmanPackageStat) aop
				.bind(com.dcdzsoft.business.ap.APPostmanPackageStat.class);
		return bean.doBusiness(p1);
	}
	
	/**
     * 收件人未取包裹列表
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static java.util.List<OutParamAPCustomerInboxPackage> doBusiness(InParamAPCustomerInboxPackage p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APCustomerInboxPackage bean = (com.dcdzsoft.business.ap.APCustomerInboxPackage) aop
                .bind(com.dcdzsoft.business.ap.APCustomerInboxPackage.class);
        return bean.doBusiness(p1);
    }
	/**
	 * 收件人包裹列表
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamAPCustomerPackageList> doBusiness(InParamAPCustomerPackageList p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APCustomerPackageList bean = (com.dcdzsoft.business.ap.APCustomerPackageList) aop
				.bind(com.dcdzsoft.business.ap.APCustomerPackageList.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 收件人包裹列表总记录数
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static int doBusiness(InParamAPCustomerPackageCount p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APCustomerPackageCount bean = (com.dcdzsoft.business.ap.APCustomerPackageCount) aop
				.bind(com.dcdzsoft.business.ap.APCustomerPackageCount.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 收件人单个包裹查询
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static OutParamAPCustomerPackageDetail doBusiness(InParamAPCustomerPackageDetail p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APCustomerPackageDetail bean = (com.dcdzsoft.business.ap.APCustomerPackageDetail) aop
				.bind(com.dcdzsoft.business.ap.APCustomerPackageDetail.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 收件人包裹统计
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamAPCustomerPackageStat> doBusiness(InParamAPCustomerPackageStat p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APCustomerPackageStat bean = (com.dcdzsoft.business.ap.APCustomerPackageStat) aop
				.bind(com.dcdzsoft.business.ap.APCustomerPackageStat.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 运营网点列表查询
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamAPDepartListQry> doBusiness(InParamAPDepartListQry p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APDepartListQry bean = (com.dcdzsoft.business.ap.APDepartListQry) aop
				.bind(com.dcdzsoft.business.ap.APDepartListQry.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 用户APP开箱
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static String doBusiness(InParamAPCustomerAppOpenBox p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APCustomerAppOpenBox bean = (com.dcdzsoft.business.ap.APCustomerAppOpenBox) aop
				.bind(com.dcdzsoft.business.ap.APCustomerAppOpenBox.class);
		return bean.doBusiness(p1);
	}
	/**
	 * 用户付费开箱
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static int doBusiness(InParamAPCustomerPayment p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APCustomerPayment bean = (com.dcdzsoft.business.ap.APCustomerPayment) aop
				.bind(com.dcdzsoft.business.ap.APCustomerPayment.class);
		return bean.doBusiness(p1);
	}
	/**
	 * 管理员APP开箱
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static String doBusiness(InParamAPManagerAppOpenBox p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APManagerAppOpenBox bean = (com.dcdzsoft.business.ap.APManagerAppOpenBox) aop
				.bind(com.dcdzsoft.business.ap.APManagerAppOpenBox.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 管理员远程重启工控
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static String doBusiness(InParamAPRemoteRestartPC p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APRemoteRestartPC bean = (com.dcdzsoft.business.ap.APRemoteRestartPC) aop
				.bind(com.dcdzsoft.business.ap.APRemoteRestartPC.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 投递员APP取回
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static String doBusiness(InParamAPPostmanAppTakeout p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanAppTakeout bean = (com.dcdzsoft.business.ap.APPostmanAppTakeout) aop
				.bind(com.dcdzsoft.business.ap.APPostmanAppTakeout.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 投递员APP扫码登录
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static OutParamAPPostmanLogin doBusiness(InParamAPPostmanLogin p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanLogin bean = (com.dcdzsoft.business.ap.APPostmanLogin) aop
				.bind(com.dcdzsoft.business.ap.APPostmanLogin.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 投递员注册
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static String doBusiness(InParamAPPostmanRegister p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanRegister bean = (com.dcdzsoft.business.ap.APPostmanRegister) aop
				.bind(com.dcdzsoft.business.ap.APPostmanRegister.class);
		return bean.doBusiness(p1);
	}
	

	/**
	 * 投递员修改密码
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static String doBusiness(InParamAPPostmanModPwd p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanModPwd  bean = (com.dcdzsoft.business.ap.APPostmanModPwd ) aop
				.bind(com.dcdzsoft.business.ap.APPostmanModPwd .class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 同步柜体信息
	 * 
	 * @param p1
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamSCSyncTBTerminal> doBusiness(InParamSCSyncTBTerminal p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCSyncTBTerminal bean = (com.dcdzsoft.business.sc.SCSyncTBTerminal) aop
				.bind(com.dcdzsoft.business.sc.SCSyncTBTerminal.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 同步箱体信息
	 * 
	 * @param p1
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamSCSyncTBBox> doBusiness(InParamSCSyncTBBox p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCSyncTBBox bean = (com.dcdzsoft.business.sc.SCSyncTBBox) aop
				.bind(com.dcdzsoft.business.sc.SCSyncTBBox.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 同步投递公司信息
	 * 
	 * @param p1
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamSCSyncPMCompany> doBusiness(InParamSCSyncPMCompany p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCSyncPMCompany bean = (com.dcdzsoft.business.sc.SCSyncPMCompany) aop
				.bind(com.dcdzsoft.business.sc.SCSyncPMCompany.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 同步投递信息
	 * 
	 * @param p1
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamSCSyncDeliveryInfo> doBusiness(InParamSCSyncDeliveryInfo p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCSyncDeliveryInfo bean = (com.dcdzsoft.business.sc.SCSyncDeliveryInfo) aop
				.bind(com.dcdzsoft.business.sc.SCSyncDeliveryInfo.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 同步投递信息
	 * 
	 * @param p1
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static java.util.List<OutParamSCSyncShortMsgInfo> doBusiness(InParamSCSyncShortMsgInfo p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.sc.SCSyncShortMsgInfo bean = (com.dcdzsoft.business.sc.SCSyncShortMsgInfo) aop
				.bind(com.dcdzsoft.business.sc.SCSyncShortMsgInfo.class);
		return bean.doBusiness(p1);
	}
	
	/**
	 * 重发取件短信
	 * 
	 * @param p1
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static int doBusiness(InParamAPReSentOpenBoxKey p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APReSentOpenBoxKey bean = (com.dcdzsoft.business.ap.APReSentOpenBoxKey) aop
				.bind(com.dcdzsoft.business.ap.APReSentOpenBoxKey.class);
		return bean.doBusiness(p1);
	}
	/**
     * 检查包裹是否已投递
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static int doBusiness(com.dcdzsoft.dto.business.InParamPTPackIsDelivery p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.pt.PTPackIsDelivery bean = (com.dcdzsoft.business.pt.PTPackIsDelivery) aop
                .bind(com.dcdzsoft.business.pt.PTPackIsDelivery.class);
        return bean.doBusiness(p1);
    }
	/**
     * 投递公司列表查询
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static java.util.List<OutParamAPCompanyListQry> doBusiness(InParamAPCompanyListQry p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APCompanyListQry bean = (com.dcdzsoft.business.ap.APCompanyListQry) aop
                .bind(com.dcdzsoft.business.ap.APCompanyListQry.class);
        return bean.doBusiness(p1);
    }
    /**
     * 获取投递员登录凭证
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static OutParamAPPostmanPwdGet doBusiness(InParamAPPostmanPwdGet p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APPostmanPwdGet bean = (com.dcdzsoft.business.ap.APPostmanPwdGet) aop
                .bind(com.dcdzsoft.business.ap.APPostmanPwdGet.class);
        return bean.doBusiness(p1);
    }
    /**
     * 获取验证码
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static com.dcdzsoft.dto.business.OutParamAPGetCheckCode doBusiness(com.dcdzsoft.dto.business.InParamAPGetCheckCode p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APGetCheckCode bean = (com.dcdzsoft.business.ap.APGetCheckCode) aop
                .bind(com.dcdzsoft.business.ap.APGetCheckCode.class);
        return bean.doBusiness(p1);
    }
    /**
     * 绑定合作方用户编号
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static com.dcdzsoft.dto.business.OutParamAPCustomerBindOpenID doBusiness(com.dcdzsoft.dto.business.InParamAPCustomerBindOpenID p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APCustomerBindOpenID bean = (com.dcdzsoft.business.ap.APCustomerBindOpenID) aop
                .bind(com.dcdzsoft.business.ap.APCustomerBindOpenID.class);
        return bean.doBusiness(p1);
    }
    /**
     * 解绑定合作方用户编号
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static int doBusiness(com.dcdzsoft.dto.business.InParamAPCustomerUnBindOpenID p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APCustomerUnBindOpenID bean = (com.dcdzsoft.business.ap.APCustomerUnBindOpenID) aop
                .bind(com.dcdzsoft.business.ap.APCustomerUnBindOpenID.class);
        return bean.doBusiness(p1);
    }
    /**
     * 修改绑定手机号
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static com.dcdzsoft.dto.business.OutParamAPCustomerBindMobile doBusiness(com.dcdzsoft.dto.business.InParamAPCustomerBindMobile p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APCustomerBindMobile bean = (com.dcdzsoft.business.ap.APCustomerBindMobile) aop
                .bind(com.dcdzsoft.business.ap.APCustomerBindMobile.class);
        return bean.doBusiness(p1);
    }
    /**
     * 绑定合作方用户编号查询
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static com.dcdzsoft.dto.business.OutParamAPCustomerBindOpenIDQry doBusiness(com.dcdzsoft.dto.business.InParamAPCustomerBindOpenIDQry p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APCustomerBindOpenIDQry bean = (com.dcdzsoft.business.ap.APCustomerBindOpenIDQry) aop
                .bind(com.dcdzsoft.business.ap.APCustomerBindOpenIDQry.class);
        return bean.doBusiness(p1);
    } 
    
    /**
     * 用户取件验证
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static com.dcdzsoft.dto.business.OutParamAPCustomerVerify doBusiness(com.dcdzsoft.dto.business.InParamAPCustomerVerify p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APCustomerVerify bean = (com.dcdzsoft.business.ap.APCustomerVerify) aop
                .bind(com.dcdzsoft.business.ap.APCustomerVerify.class);
        return bean.doBusiness(p1);
    }
    
    /**
     * 查询柜体信息
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static java.util.List<OutParamAPTerminalListQry> doBusiness(InParamAPTerminalListQry p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APTerminalListQry bean = (com.dcdzsoft.business.ap.APTerminalListQry) aop
                .bind(com.dcdzsoft.business.ap.APTerminalListQry.class);
        return bean.doBusiness(p1);
    }
    
    /**
     * 查询格口信息
     * 
     * @param p1
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static java.util.List<OutParamAPBoxQry> doBusiness(InParamAPBoxQry p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APBoxQry bean = (com.dcdzsoft.business.ap.APBoxQry) aop
                .bind(com.dcdzsoft.business.ap.APBoxQry.class);
        return bean.doBusiness(p1);
    }
}
