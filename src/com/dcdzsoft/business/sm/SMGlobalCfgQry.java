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
import com.dcdzsoft.sda.datarow.*;
import com.dcdzsoft.config.ApplicationConfig;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 全局配置查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SMGlobalCfgQry extends ActionBean {

    public RowSet doBusiness(InParamSMGlobalCfgQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        OPOperatorDAO operDAO = daoFactory.getOPOperatorDAO();
       OPOperator operSuperObj = new OPOperator();
       operSuperObj.OperID = in.OperID;
       operSuperObj = operDAO.find(operSuperObj);

       if (operSuperObj.UserID.equalsIgnoreCase(Constant.DEFAULT_SUPEROPERID) == false)
           throw new EduException(ErrorCode.ERR_OPERNOOPENFUNC);


       ApplicationConfig apCfg = ApplicationConfig.getInstance();

       DataRow dRow = new DataRow();
       dRow.addStrColumn("locale", 10);
       dRow.addStrColumn("sysRunModel", 10);
       dRow.addStrColumn("interfacePackage", 20);
       dRow.addStrColumn("workerCount", 10);
       dRow.addStrColumn("logRawMsg", 10);
       dRow.addStrColumn("logMbMsg", 10);
       dRow.addStrColumn("sendShortMsg", 30);
       dRow.addStrColumn("gatewayUser", 30);
       dRow.addStrColumn("gatewayPwd", 30);
       dRow.addStrColumn("mbHost", 32);
       dRow.addStrColumn("mbPort", 10);
       dRow.addStrColumn("mbUri", 64);
       dRow.addStrColumn("ftpHost", 32);
       dRow.addStrColumn("ftpPort", 10);
       dRow.addStrColumn("ftpUser", 64);
       dRow.addStrColumn("ftpPasswd", 32);
       dRow.addStrColumn("sentToGuotong", 10);

       ColFieldArray fArray = new ColFieldArray();

       fArray.addCol("locale", apCfg.getLocale());
       fArray.addCol("sysRunModel", apCfg.getSysRunModel());
       fArray.addCol("interfacePackage", apCfg.getInterfacePackage());
       fArray.addCol("workerCount", apCfg.getWorkerCount());
       
       if(apCfg.isLogRawMsg())
           fArray.addCol("logRawMsg", "true");
       else
           fArray.addCol("logRawMsg", "false");
       
       if(apCfg.isLogMbMsg())
          fArray.addCol("logMbMsg", "true");
       else
          fArray.addCol("logMbMsg", "false");
       
       fArray.addCol("sendShortMsg", apCfg.getSendShortMsg());
       fArray.addCol("gatewayUser", apCfg.getGatewayUser());
       fArray.addCol("gatewayPwd", apCfg.getGatewayPwd());
       
       fArray.addCol("mbHost", apCfg.getMbHost());
       fArray.addCol("mbPort", apCfg.getMbPort());
       fArray.addCol("mbUri", apCfg.getMbUri());

       fArray.addCol("ftpHost", apCfg.getFtpHost());
       fArray.addCol("ftpPort", apCfg.getFtpPort());
       fArray.addCol("ftpUser", apCfg.getFtpUser());
       fArray.addCol("ftpPasswd", apCfg.getFtpPasswd());
       
       if(apCfg.isSentToGutong())
           fArray.addCol("sentToGuotong", "true");
        else
           fArray.addCol("sentToGuotong", "false");
       
       dRow.addRow(fArray);

       DataRowToRowSet dtr = new DataRowToRowSet(dRow);
       rset = dtr.arrayToRowSet();


        return rset;
    }
}
