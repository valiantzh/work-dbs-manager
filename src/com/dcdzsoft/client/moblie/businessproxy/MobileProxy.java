package com.dcdzsoft.client.moblie.businessproxy;

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
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * <p>
 * Company: 杭州东城电子有限公司
 * </p>
 * 
 * @author zxy
 * @version 1.0
 */
public class MobileProxy extends com.dcdzsoft.businessproxy.MobileProxy{
	
	/**
	 * PDA开箱
	 * 
	 * @param p1
	 * @param TerminalNo
	 * @return
	 * @throws com.dcdzsoft.EduException
	 */
	public static OutParamAPPostmanAppOpenBox doBusiness(InParamAPPostmanAppOpenBox p1) throws com.dcdzsoft.EduException {
		BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
		com.dcdzsoft.business.ap.APPostmanAppOpenBox bean = (com.dcdzsoft.business.ap.APPostmanAppOpenBox) aop
				.bind(com.dcdzsoft.business.ap.APPostmanAppOpenBox.class);
		return bean.doBusiness(p1);
	}
	
	/**
     * 投递开箱
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static OutParamAPStoreInOpenBox doBusiness(InParamAPStoreInOpenBox p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APStoreInOpenBox bean = (com.dcdzsoft.business.ap.APStoreInOpenBox) aop
                .bind(com.dcdzsoft.business.ap.APStoreInOpenBox.class);
        return bean.doBusiness(p1);
    }
    
    /**
     * 取件开箱
     * 
     * @param p1
     * @param TerminalNo
     * @return
     * @throws com.dcdzsoft.EduException
     */
    public static OutParamAPTakeOutOpenBox doBusiness(InParamAPTakeOutOpenBox p1) throws com.dcdzsoft.EduException {
        BusiBeanAOPBaseClass aop = BusiBeanAOPBaseClass.getInstance();
        com.dcdzsoft.business.ap.APTakeOutOpenBox bean = (com.dcdzsoft.business.ap.APTakeOutOpenBox) aop
                .bind(com.dcdzsoft.business.ap.APTakeOutOpenBox.class);
        return bean.doBusiness(p1);
    }
}
