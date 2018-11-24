package com.dcdzsoft.business.pt;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.guotong.GuotongManager;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 修改订单超时时间 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTModExpiredTime extends ActionBean {

    public int doBusiness(InParamPTModExpiredTime in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.PackageID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        if (StringUtils.isEmpty(in.RemoteFlag))
            in.RemoteFlag = SysDict.OPER_FLAG_REMOTE;
        
        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = null;
        if(StringUtils.isNotEmpty(in.OperID)){
           operOnline = commonDAO.isOnline(in.OperID);
        }else{
            in.OperID = "181818";
        }
        

        //4.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //////////////////////////////////////////////////////////////
        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();

        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("PackageID", in.PackageID);
        whereCols.add("TerminalNo", in.TerminalNo);

        if (inboxPackDAO.isExist(whereCols) == 0)
            throw new EduException(ErrorCode.ERR_PACKAGENOTEXISTS);

        if(in.ExpiredTime==null){
            in.ExpiredTime = sysDateTime;
        }
        PTInBoxPackage inboxPack = new PTInBoxPackage();
        inboxPack.TerminalNo = in.TerminalNo;
        inboxPack.PackageID  = in.PackageID;
        inboxPack = inboxPackDAO.find(inboxPack);
        if(inboxPack.ExpiredTime == null){
            inboxPack.ExpiredTime = queryExpiredDayTime(inboxPack.StoredTime);
        }
        
        boolean isExpiredFlag = false;//是否处于逾期状态
        ////////////////////////////////////////////////////////////////
        JDBCFieldArray setCols = new JDBCFieldArray();
        if(SysDict.PACKAGE_STATUS_TIMEOUT.equals(inboxPack.PackageStatus)
            ||SysDict.PACKAGE_STATUS_LOCKED.equals(inboxPack.PackageStatus)){
            //逾期状态处理，新的逾期时间只能在原逾期时间之后
            if(in.ExpiredTime.after(inboxPack.ExpiredTime)){//
                setCols.add("ExpiredTime",in.ExpiredTime);
                if(in.ExpiredTime.after(sysDateTime)){//新的逾期时间在当前时间之后，更新订单状态
                    inboxPack.PackageStatus = SysDict.PACKAGE_STATUS_NORMAL;
                    setCols.add("PackageStatus", inboxPack.PackageStatus);
                    isExpiredFlag = false;
                }else{
                    isExpiredFlag = true;
                }
                setCols.add("LastModifyTime",sysDateTime);
                
                inboxPackDAO.update(setCols,whereCols);
            }
        }else{
            //正常状态处理
            setCols.add("ExpiredTime",in.ExpiredTime);
            if(in.ExpiredTime.before(sysDateTime)){//新的逾期时间在当前时间之前，更新订单状态
                inboxPack.PackageStatus = SysDict.PACKAGE_STATUS_TIMEOUT;
                setCols.add("PackageStatus", inboxPack.PackageStatus);
                isExpiredFlag = true;
            }else{
                isExpiredFlag = false;
            }
            setCols.add("LastModifyTime",sysDateTime);
            inboxPackDAO.update(setCols,whereCols);
            
        }
        
        if (in.RemoteFlag.equalsIgnoreCase(SysDict.OPER_FLAG_REMOTE))
        {
            ///推送到设备端
            try{
                com.dcdzsoft.businessproxy.PushBusinessProxy.doBusiness(in);
            }catch(Exception e){
                
            }
        }
        
        //处于逾期状态，发送反馈信息
        if(isExpiredFlag){
            SMSInfo smsInfo = new SMSInfo();
            smsInfo.PackageID = in.PackageID;
            smsInfo.TerminalNo = in.TerminalNo;
            smsInfo.StoredTime = inboxPack.StoredTime;
            smsInfo.sysDateTime = sysDateTime;
            smsInfo.CustomerMobile = inboxPack.CustomerMobile;
            smsInfo.BoxNo = inboxPack.BoxNo;
            smsInfo.PackageStatus = SysDict.PACKAGE_STATUS_TIMEOUT;
            smsInfo.PostmanID = inboxPack.PostmanID;
            
            smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
            smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
            smsInfo.StaOrderID = inboxPack.StaOrderID;
            smsInfo.TradeWaterNo = inboxPack.TradeWaterNo;
            smsInfo.OpenBoxKey = "";
            
            smsInfo.ExpiredTime = in.ExpiredTime;
            
            PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
            PMPostman postman = new PMPostman();
            postman.PostmanID = inboxPack.PostmanID;
            postman = postmanDAO.find(postman);
            
            // 反馈订单状态给合作伙伴
            if (commonDAO.isSendItemEventToPartner(inboxPack.CompanyID,postman.InputMobileFlag)) {
                GuotongManager.getInstance().sentDeliveryInfo(smsInfo);
            }
        }
        
        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = "127.0.0.1";
        log.Remark = "";

        commonDAO.addOperatorLog(log);
        
        return result;
    }
    private java.sql.Timestamp queryExpiredDayTime(java.sql.Timestamp sysDateTime) throws EduException
    {
        if(sysDateTime == null){
            sysDateTime = utilDAO.getCurrentDateTime();
        }
        ControlParam ctrlParam = ControlParam.getInstance();
        int days = NumberUtils.parseInt(ctrlParam.recoveryTimeout);
        
        return DateUtils.addTimeStamp(sysDateTime, days);
    }
}
