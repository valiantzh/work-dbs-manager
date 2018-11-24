package com.dcdzsoft.business.sm;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.constant.*;

import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能柜运维管理系统</p>
 * <p>Description: 获取软件升级信息 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 郑晓勇
 * @version 1.0
 */

public class SMGetUpgradeInfo extends ActionBean
{

    public OutParamSMGetUpgradeInfo doBusiness(InParamSMGetUpgradeInfo in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamSMGetUpgradeInfo out = new OutParamSMGetUpgradeInfo();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.TerminalNo)
		        || StringUtils.isEmpty(in.SoftwareType)
			||StringUtils.isEmpty(in.SoftwareVersion))
			throw new EduException(ErrorCode.ERR_PARMERR);


		//3.调用UtilDAO.getCurrentDateTime()获得系统日期时间。
		//java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
		//java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

		SMTerminalSoftDAO terminalSoftDAO = daoFactory.getSMTerminalSoftDAO();
		SMTerminalSoft terminalSoft = new SMTerminalSoft();
        terminalSoft.TerminalNo = in.TerminalNo;
        terminalSoft.SoftwareType = in.SoftwareType;
        if(terminalSoftDAO.isExist(terminalSoft)){
            terminalSoft = terminalSoftDAO.find(terminalSoft);
            if(!"1".equals(terminalSoft.UpdateStatus)){
                out.Remark ="无更新版本";
                return out;
            }
            
            //查升级包
            SMUpgradePackDAO upgradePackDAO = daoFactory.getSMUpgradePackDAO();
            SMUpgradePack upgradePack = new SMUpgradePack();
            upgradePack.SoftwareID = terminalSoft.SoftwareID;
            try{
                upgradePack = upgradePackDAO.find(upgradePack);
                if(in.SoftwareVersion.equals(upgradePack.SoftwareVersion)){
                    out.Remark ="软件版本相同，无更新版本";
                    return out;
                }
                out.SoftwareVersion = upgradePack.SoftwareVersion;
                out.SoftwareType = upgradePack.SoftwareType;
                out.DownloadUrl = upgradePack.DownloadUrl;
                out.SoftSignMd5 = upgradePack.SoftSignMd5;
                out.UpdateContent = upgradePack.UpdateContent;
                out.TerminalNo = in.TerminalNo;
            }catch(EduException e){
                out.Remark ="升级包未上传，联系管理员";
            }
        }else{
            out.Remark ="无软件更新版本";
        }
        
        return out;
    }
}
