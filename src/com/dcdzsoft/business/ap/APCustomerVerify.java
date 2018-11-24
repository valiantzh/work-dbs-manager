package com.dcdzsoft.business.ap;

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
import com.dcdzsoft.sda.security.SecurityUtils;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 用户取件身份认证 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class APCustomerVerify extends ActionBean {

    public OutParamAPCustomerVerify doBusiness(InParamAPCustomerVerify in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamAPCustomerVerify out = new OutParamAPCustomerVerify();

        //1.	验证输入参数是否有效，如果无效返回-1。
        if ( StringUtils.isEmpty(in.OpenBoxKey))
            throw new EduException(ErrorCode.ERR_PARMERR);

        ControlParam ctrlParam = ControlParam.getInstance();

        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        if(in.OpenBoxKey.length()!= 32){
            in.OpenBoxKey = SecurityUtils.md5(in.OpenBoxKey).toLowerCase();
        }

        if(SysDict.TAKEOUTCHECK_TYPE_PWD.equals(ctrlParam.takeOutCheckType)){//单独密码
            whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
            whereCols.addSQL(" AND LOWER(OpenBoxKey) = " + StringUtils.addQuote(in.OpenBoxKey) + "");
        }else{
            if(StringUtils.isEmpty(in.CustomerID)){
                throw new EduException(ErrorCode.ERR_PARMERR);
            }
            if (SysDict.TAKEOUTCHECK_TYPE_PACKAGEID.equals(ctrlParam.takeOutCheckType)) {
                whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
                whereCols.addSQL(" AND PackageID = " + StringUtils.addQuote(in.CustomerID) + "");
                whereCols.addSQL(" AND LOWER(OpenBoxKey) = " + StringUtils.addQuote(in.OpenBoxKey) + "");
            } else if (SysDict.TAKEOUTCHECK_TYPE_MOBILE.equals(ctrlParam.takeOutCheckType)) {
                 whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
                whereCols.addSQL(" AND CustomerMobile = " + StringUtils.addQuote(in.CustomerID) + "");
                whereCols.addSQL(" AND LOWER(OpenBoxKey) = " + StringUtils.addQuote(in.OpenBoxKey) + "");
            } else if (SysDict.TAKEOUTCHECK_TYPE_MOBILEAFTER4.equals(ctrlParam.takeOutCheckType)) {
                whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
                whereCols.addSQL(" AND SUBSTR(CustomerMobile,LENGTH(CustomerMobile)-3,4) = " + StringUtils.addQuote(in.CustomerID) + "");
                whereCols.addSQL(" AND LOWER(OpenBoxKey) = " + StringUtils.addQuote(in.OpenBoxKey) + "");
            } else if (SysDict.TAKEOUTCHECK_TYPE_PACKORMOBILE.equals(ctrlParam.takeOutCheckType)) {
                whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
                whereCols.addSQL(" AND (PackageID = " + StringUtils.addQuote(in.CustomerID));
                whereCols.addSQL("      OR CustomerMobile = " + StringUtils.addQuote(in.CustomerID) + ")");
                whereCols.addSQL(" AND LOWER(OpenBoxKey) = " + StringUtils.addQuote(in.OpenBoxKey) + "");
            } else if (SysDict.TAKEOUTCHECK_TYPE_CARDID.equals(ctrlParam.takeOutCheckType)) {
                whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
                whereCols.addSQL(" AND CustomerID = " + StringUtils.addQuote(in.CustomerID) + "");
                whereCols.addSQL(" AND LOWER(OpenBoxKey) = " + StringUtils.addQuote(in.OpenBoxKey) + "");
            } else if (SysDict.TAKEOUTCHECK_TYPE_4PACKID.equals(ctrlParam.takeOutCheckType)) {
                whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
                whereCols.addSQL(" AND SUBSTR(PackageID,LENGTH(PackageID)-3,4) = " + StringUtils.addQuote(in.CustomerID) + "");
                whereCols.addSQL(" AND LOWER(OpenBoxKey) = " + StringUtils.addQuote(in.OpenBoxKey) + "");
            } else if (SysDict.TAKEOUTCHECK_TYPE_4PACKORMOBILE.equals(ctrlParam.takeOutCheckType)) {
                whereCols.addSQL(" AND TerminalNo = " + StringUtils.addQuote(in.TerminalNo) + "");
                whereCols.addSQL(" AND (SUBSTR(PackageID,LENGTH(PackageID)-3,4) = " + StringUtils.addQuote(in.CustomerID));
                whereCols.addSQL("      OR CustomerMobile = " + StringUtils.addQuote(in.CustomerID) + ")");
                whereCols.addSQL(" AND LOWER(OpenBoxKey) = " + StringUtils.addQuote(in.OpenBoxKey) + "");
            }
        }

        RowSet rset = inboxPackDAO.select(whereCols);
        if (RowSetUtils.rowsetNext(rset)) { //可能出现多个记录如何处理
            //判断包裹状态不????(判断锁定，不判断超时)
            if (RowSetUtils.getStringValue(rset,"PackageStatus").equalsIgnoreCase(SysDict.PACKAGE_STATUS_LOCKED))
            {
                throw new EduException(ErrorCode.ERR_PACKAGEHAVELOCKED);
            }

            out.BoxNo = RowSetUtils.getStringValue(rset, "BoxNo");
            out.PackageID = RowSetUtils.getStringValue(rset, "PackageID");
            out.PosPayFlag = RowSetUtils.getStringValue(rset, "PosPayFlag");
            out.ExpiredAmt = 0D; //???
            out.OverPayFlag = "0";
            out.Company    = "";
            out.CustomerTel = RowSetUtils.getStringValue(rset, "CustomerMobile");
            //是否支持一单多箱，输入的in.PackageID为设备扫描条码的单号
            if("1".equals(ControlParam.getInstance().orderDeliveryMode)){
                //一单多箱：生成内部投递订单号=in.PackageID+"#"+in.BoxNo
                out.PackageID = out.PackageID.replace(Constant.RETURN_GOODS_ORDER_REGEX+""+out.BoxNo, "");
            }
        } else {
            
            if (SysDict.TAKEOUTCHECK_TYPE_PACKAGEID.equals(ctrlParam.takeOutCheckType)) {
                throw new EduException(ErrorCode.ERR_BUSINESS_WRONGPACKAGID);
            } else if (SysDict.TAKEOUTCHECK_TYPE_MOBILE.equals(ctrlParam.takeOutCheckType)) {
                throw new EduException(ErrorCode.ERR_BUSINESS_WRONGMOBILE);
            } else if (SysDict.TAKEOUTCHECK_TYPE_MOBILEAFTER4.equals(ctrlParam.takeOutCheckType)) {
                throw new EduException(ErrorCode.ERR_BUSINESS_WRONGMOBILE);
            } else if (SysDict.TAKEOUTCHECK_TYPE_PACKORMOBILE.equals(ctrlParam.takeOutCheckType)) {
                throw new EduException(ErrorCode.ERR_BUSINESS_WRONGPACKMOBILE);
            } else if (SysDict.TAKEOUTCHECK_TYPE_CARDID.equals(ctrlParam.takeOutCheckType)) {
                throw new EduException(ErrorCode.ERR_BUSINESS_WRONGCUSTOMERID);
            } else if (SysDict.TAKEOUTCHECK_TYPE_4PACKID.equals(ctrlParam.takeOutCheckType)) {
                throw new EduException(ErrorCode.ERR_BUSINESS_WRONGPACKAGID);
            } else if (SysDict.TAKEOUTCHECK_TYPE_4PACKORMOBILE.equals(ctrlParam.takeOutCheckType)) {
                throw new EduException(ErrorCode.ERR_BUSINESS_WRONGPACKMOBILE);
            }else if (SysDict.TAKEOUTCHECK_TYPE_PWD.equals(ctrlParam.takeOutCheckType)) {
                throw new EduException(ErrorCode.ERR_BUSINESS_WRONGPASSWORD);
            }
        }

        return out;
    }
}
