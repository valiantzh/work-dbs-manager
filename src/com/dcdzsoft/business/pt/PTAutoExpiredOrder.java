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
 * <p>Description: 自动设置订单过期 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PTAutoExpiredOrder extends ActionBean
{

    public int doBusiness(InParamPTAutoExpiredOrder in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo))
                throw new EduException(ErrorCode.ERR_PARMERR);
        
        
        
        /*String recoveryTimeout = ControlParam.getInstance().recoveryTimeout;

        int expiredDays = NumberUtils.parseInt(recoveryTimeout);
        if(expiredDays<1){
            //System.out.println(Constant.datetimeFromat.format(sysDateTime)+" recoveryTimeout="+recoveryTimeout);
            expiredDays = 1;
        }*/
        
        if("1".equals(ControlParam.getInstance().recoveryTimeSet)){
            //按公司设定逾期天数
            /*PMCompanyDAO companyDAO = daoFactory.getPMCompanyDAO();
            JDBCFieldArray whereCols2 = new JDBCFieldArray();
            RowSet rset = companyDAO.select(whereCols2);
            while(RowSetUtils.rowsetNext(rset)){
                String companyID = RowSetUtils.getStringValue(rset, "CompanyID");
                int days = 0;
                try{
                    days = NumberUtils.parseInt(RowSetUtils.getStringValue(rset, "Zip"));//rset.getString("Zip")
                }catch(Exception e){
                    continue;
                }
                if(days <1 || days > expiredDays){
                    days = expiredDays;
                }
                result += doBusiness(in, days, companyID);
            }*/
            java.util.Set<String> companySet = commonDAO.selectCompanyFromInBoxPackage();
            if(companySet.size() > 0){
                for(String companyID :companySet){
                    int expiredDays = commonDAO.expiredDays(companyID);
                    result = doBusiness(in, expiredDays, companyID);
                }
            }
        }else{
            int expiredDays = commonDAO.expiredDays();
            result = doBusiness(in, expiredDays, "");
        }
        
        return result;
    }
    private int  doBusiness(InParamPTAutoExpiredOrder in,  int expiredDays, String companyID) throws EduException{
        int result = 0;
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        //java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);
        //查询即将逾期的订单
        PTInBoxPackageDAO inBoxPackageDAO = daoFactory.getPTInBoxPackageDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("TerminalNo", in.TerminalNo);
        whereCols.add("PackageStatus", SysDict.PACKAGE_STATUS_NORMAL);
        //whereCols.add("PackageStatus", "<>", SysDict.PACKAGE_STATUS_TIMEOUT);
        
        whereCols.checkAdd("CompanyID", companyID);//按公司设定逾期天数
        
        whereCols.addSQL(" AND ( (2=2 ");
        //未设置逾期时间，按recoveryTimeout时间逾期；设置的逾期时间大于存物时间，按设置的逾期时间逾期
        whereCols.addSQL(" AND ( ExpiredTime IS NULL OR ExpiredTime <= StoredTime )");//小于存物时间，表示没有设置逾期时间
        whereCols.add("StoredTime", "<=", DateUtils.addTimeStamp(sysDateTime, -expiredDays));
        whereCols.addSQL(") OR (2=2 AND ExpiredTime > StoredTime "); //设置的逾期时间大于存物时间，按设置的逾期时间逾期
        whereCols.add("ExpiredTime", "<", sysDateTime);
        whereCols.addSQL("  ) )"); //
        
        RowSet rset1 = inBoxPackageDAO.select(whereCols);
        while(RowSetUtils.rowsetNext(rset1))
        {
            SMSInfo smsInfo = new SMSInfo();
            smsInfo.PackageID = RowSetUtils.getStringValue(rset1, "PackageID");//;
            smsInfo.TerminalNo = in.TerminalNo;
            smsInfo.StoredTime = RowSetUtils.getTimestampValue(rset1, "StoredTime");//inboxPack.StoredTime;
            smsInfo.sysDateTime = sysDateTime;
            smsInfo.CustomerMobile = RowSetUtils.getStringValue(rset1, "CustomerMobile");//inboxPack.CustomerMobile;
            smsInfo.BoxNo = RowSetUtils.getStringValue(rset1, "BoxNo");//inboxPack.BoxNo;
            smsInfo.PackageStatus = SysDict.PACKAGE_STATUS_TIMEOUT;
            smsInfo.PostmanID = RowSetUtils.getStringValue(rset1, "PostmanID");//inboxPack.PostmanID;
            
            smsInfo.OfLogisticsID = RowSetUtils.getStringValue(rset1, "OfLogisticsID");//inboxPack.OfLogisticsID;
            smsInfo.OfLogisticsName = RowSetUtils.getStringValue(rset1, "OfLogisticsName");//inboxPack.OfLogisticsName;
            smsInfo.StaOrderID = RowSetUtils.getStringValue(rset1, "StaOrderID");//inboxPack.StaOrderID;
            smsInfo.TradeWaterNo = RowSetUtils.getStringValue(rset1, "TradeWaterNo");//inboxPack.TradeWaterNo;
            smsInfo.OpenBoxKey = "";//RowSetUtils.getStringValue(rset1, "BlankBoxKey");//inboxPack.BlankBoxKey;
            
            smsInfo.ExpiredTime = RowSetUtils.getTimestampValue(rset1, "ExpiredTime");//inboxPack.StoredTime;
            
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            JDBCFieldArray setCols1 = new JDBCFieldArray();
            setCols1.add("PackageStatus", SysDict.PACKAGE_STATUS_TIMEOUT);
            if(smsInfo.ExpiredTime==null || smsInfo.ExpiredTime.before(smsInfo.StoredTime)){
                smsInfo.ExpiredTime = DateUtils.addTimeStamp(smsInfo.StoredTime, expiredDays);
                setCols1.add("ExpiredTime", smsInfo.ExpiredTime);
            }
            setCols1.add("UploadFlag", "0");//0-未发送
            
            whereCols1.add("TerminalNo", in.TerminalNo);
            whereCols1.add("PackageID", smsInfo.PackageID);
            inBoxPackageDAO.update(setCols1, whereCols1);
            if(commonDAO.isSendItemEventToPartner(smsInfo.CompanyID, "1"))
            {
                GuotongManager.getInstance().sentDeliveryInfo(smsInfo);
            }
            try {
                smsInfo = null;
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
            }
            result ++;
        }
        return result;
    }
}
