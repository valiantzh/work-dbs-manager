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
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 删除订单 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTDelPackage extends ActionBean {

    public int doBusiness(InParamPTDelPackage in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.PackageID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        OPOperOnline operOnline = commonDAO.isOnline(in.OperID);

        //4.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        //判断特殊权限

        //////////////////////////////////////////////////////////////
        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();

        PTInBoxPackage inboxPack = new PTInBoxPackage();
        inboxPack.PackageID = in.PackageID;
        inboxPack.TerminalNo = in.TerminalNo;

        inboxPack = inboxPackDAO.find(inboxPack);
        
        //设备是否存在
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        TBTerminal terminal = new TBTerminal();
        terminal.TerminalNo = in.TerminalNo;
        
        terminal = terminalDAO.find(terminal);
        
        //取投递员信息
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = inboxPack.PostmanID;
        try
        {
            postman = postmanDAO.find(postman);
        } catch (Exception e) {
            //throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
        }
        
        ////////////////////////////////////////////////////////////////
        //从在箱信息里面删除
        inboxPackDAO.delete(inboxPack);

        PTDeliverHistoryDAO historyPackDAO = daoFactory.getPTDeliverHistoryDAO();

        //先删除，保证数据不重复
        JDBCFieldArray whereCols1 = new JDBCFieldArray();
        whereCols1.add("PackageID", inboxPack.PackageID);
        whereCols1.add("TerminalNo", inboxPack.TerminalNo);
        whereCols1.add("StoredTime", inboxPack.StoredTime);

        historyPackDAO.delete(whereCols1);

        //////////////////////////////////////////////////////////////////////////
        //插入历史记录
        PTDeliverHistory historyPack = new PTDeliverHistory();

        historyPack.TerminalNo = in.TerminalNo;
        historyPack.BoxNo = inboxPack.BoxNo;
        historyPack.PackageID = inboxPack.PackageID;
        historyPack.StoredTime = inboxPack.StoredTime;
        historyPack.StoredDate = inboxPack.StoredDate;
        historyPack.PostmanID = inboxPack.PostmanID;
        historyPack.CompanyID = inboxPack.CompanyID;
        historyPack.ExpiredTime = inboxPack.ExpiredTime;
        historyPack.CustomerID = inboxPack.CustomerID;
        historyPack.CustomerMobile = inboxPack.CustomerMobile;
        historyPack.CustomerName = inboxPack.CustomerName;
        historyPack.CustomerAddress = inboxPack.CustomerAddress;
        historyPack.OpenBoxKey = inboxPack.OpenBoxKey;
        historyPack.HireAmt = inboxPack.HireAmt;
        historyPack.HireWhoPay = inboxPack.HireWhoPay;
        historyPack.PayedAmt = inboxPack.PayedAmt;
        historyPack.LeftFlag = inboxPack.LeftFlag;
        historyPack.PosPayFlag = inboxPack.PosPayFlag;
        historyPack.PackageStatus = SysDict.PACKAGE_STATUS_CANCEL;
        historyPack.BlankBoxKey = inboxPack.BlankBoxKey;
        historyPack.LastModifyTime = sysDateTime;
        historyPack.TakedPersonID = in.OperID;
        if(commonDAO.isSendItemEventToPartner(inboxPack.CompanyID, "1"))
            historyPack.UploadFlag = SysDict.UPLOAD_FLAG_NO;
        else
            historyPack.UploadFlag = SysDict.UPLOAD_FLAG_YES;
        historyPack.Remark = inboxPack.Remark;
        historyPack.TradeWaterNo = inboxPack.TradeWaterNo;
        historyPackDAO.insert(historyPack);

        
        ///推送到设备端
        try{
            com.dcdzsoft.businessproxy.PushBusinessProxy.doBusiness(in);
        }catch(EduException e){
            //默认不允许设备离线清箱
            if(!"1".equals(ControlParam.getInstance().getCancelDeliveryOffline())){
                throw new EduException(e.getMessage());
            }
        }
        

        //修改短信里面的包裹状态
        MBPwdShortMsgDAO shortMsgDAO = daoFactory.getMBPwdShortMsgDAO();
        JDBCFieldArray setCols2 = new JDBCFieldArray();
        JDBCFieldArray whereCols2 = new JDBCFieldArray();
        setCols2.add("PackageStatus", historyPack.PackageStatus);
        whereCols2.add("PackageID", inboxPack.PackageID);
        whereCols2.add("TerminalNo", inboxPack.TerminalNo);
        whereCols2.add("StoredTime", inboxPack.StoredTime);
        
        shortMsgDAO.update(setCols2, whereCols2);


        // /////////////////////////////////////////////////////////
        SMSInfo smsInfo = commonDAO.generateSendInfo(historyPack, terminal, postman, sysDateTime);
        // 订单状态是否 反馈给合作方系统
        if (commonDAO.isSendItemEventToPartner(inboxPack.CompanyID,"1")) {
            /*SMSInfo smsInfo = new SMSInfo();
            smsInfo.PackageID = in.PackageID;
            smsInfo.TerminalNo = in.TerminalNo;
            
            smsInfo.StoredTime = historyPack.StoredTime;
            smsInfo.sysDateTime = sysDateTime;
            smsInfo.TakedTime  = historyPack.TakedTime;
            smsInfo.CustomerMobile = historyPack.CustomerMobile;
            smsInfo.BoxNo = inboxPack.BoxNo;
            smsInfo.PackageStatus = historyPack.PackageStatus;
            smsInfo.PostmanID = historyPack.PostmanID;
            smsInfo.PostmanName = "";
            smsInfo.PostmanMobile = "";
            smsInfo.CompanyID = historyPack.CompanyID;
            smsInfo.OpenBoxKey = "";
            smsInfo.TerminalName = terminal.TerminalName;
            smsInfo.OfBureau = terminal.OfBureau;
            smsInfo.Location = terminal.Location;
            
            smsInfo.TradeWaterNo = historyPack.TradeWaterNo;
            
            smsInfo.StaOrderID = inboxPack.StaOrderID;
            smsInfo.OfLogisticsID = inboxPack.OfLogisticsID;
            smsInfo.OfLogisticsName = inboxPack.OfLogisticsName;
            smsInfo.OpenBoxKey = inboxPack.BlankBoxKey;
            smsInfo.MBDeviceNo = terminal.MBDeviceNo;*/

            SMSInfo sendInfo = new SMSInfo(smsInfo);
            GuotongManager.getInstance().sentDeliveryInfo(sendInfo);
        }
        // 调用CommonDAO.addOperatorLog(OperID，功能编号，系统日期时间，“”)
        OPOperatorLog log = new OPOperatorLog();
        log.OperID = in.OperID;
        log.FunctionID = in.getFunctionID();
        log.OccurTime = sysDateTime;
        log.StationAddr = operOnline.LoginIPAddr;
        log.Remark = in.PackageID;

        commonDAO.addOperatorLog(log);

        return result;
    }
}
