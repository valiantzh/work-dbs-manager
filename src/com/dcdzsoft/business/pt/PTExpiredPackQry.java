package com.dcdzsoft.business.pt;

import javax.sql.RowSet;

import java.util.List;
import java.util.LinkedList;

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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 下载逾期包裹单列表 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTExpiredPackQry extends ActionBean {

    public java.util.List<OutParamPTExpiredPackQry> doBusiness(InParamPTExpiredPackQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        java.util.List<OutParamPTExpiredPackQry> outList = new java.util.LinkedList<OutParamPTExpiredPackQry>();

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo)
            || StringUtils.isEmpty(in.PostmanID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //验证投递员是否存在
        PMPostmanDAO postmanDAO = daoFactory.getPMPostmanDAO();
        PMPostman postman = new PMPostman();
        postman.PostmanID = in.PostmanID;
        try {
            postman = postmanDAO.find(postman);
        } catch (Exception e) {
            throw new EduException(ErrorCode.ERR_POSTMANNOTEXISTS);
        }
        //验证柜体权限
        commonDAO.checkManTerminalRight(postman, in.TerminalNo);

        //////////////////////////////////////////
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        int expiredDays = commonDAO.expiredDays(postman.CompanyID);
        
        
        PTInBoxPackageDAO inBoxPackageDAO = daoFactory.getPTInBoxPackageDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();

        if("1".equals(ControlParam.getInstance().recoveryScope)){
            whereCols.add("PostmanID", in.PostmanID);
        }else if("2".equals(ControlParam.getInstance().recoveryScope)){
            whereCols.add("CompanyID", postman.CompanyID);
        }
        whereCols.add("TerminalNo",in.TerminalNo);
        //未设置逾期时间，按recoveryTimeout时间逾期；设置的逾期时间大于存物时间，按设置的逾期时间逾期
        whereCols.addSQL(" AND ( ");
        whereCols.addSQL(" (2=2  AND ( ExpiredTime IS NULL OR ExpiredTime <= StoredTime )");//小于存物时间，表示没有设置逾期时间
        whereCols.add("StoredDate", "<=",DateUtils.addDate(sysDate, -expiredDays));
        whereCols.add("PackageStatus", "0");//正常订单，不查退货在箱订单
        whereCols.addSQL(") "); //未设置逾期时间，按recoveryTimeout时间逾期；
        whereCols.addSQL(" OR PackageStatus IN('1','3') "); //1.锁定 3.超时
        whereCols.addSQL(" OR (2=2 AND ExpiredTime > StoredTime "); //设置的逾期时间大于存物时间，按设置的逾期时间逾期
        whereCols.add("ExpiredTime", "<", sysDateTime);
        whereCols.add("PackageStatus", "0");//正常订单，不查退货在箱
        whereCols.addSQL("  ) )"); //
        
        boolean isFlag = false;
        if("1".equals(ControlParam.getInstance().orderDeliveryMode)){
            //一单多箱：生成内部投递订单号=扫描的订单号+"#"+in.BoxNo
            isFlag = true;
        }
        
        rset = inBoxPackageDAO.select(whereCols);
        while (RowSetUtils.rowsetNext(rset)) {
            OutParamPTExpiredPackQry outParam = new OutParamPTExpiredPackQry();
            outParam.PID = RowSetUtils.getStringValue(rset, "PackageID");
            if(isFlag){
                String boxNo = RowSetUtils.getStringValue(rset, "BoxNo");
                outParam.PID = outParam.PID.replace(Constant.RETURN_GOODS_ORDER_REGEX+""+boxNo, "");
            }
            outParam.BNO = RowSetUtils.getStringValue(rset, "BoxNo");
            outParam.MOB = RowSetUtils.getStringValue(rset, "CustomerMobile");
            outParam.STM = RowSetUtils.getTimestampValue(rset, "StoredTime");
            outParam.STS = RowSetUtils.getStringValue(rset, "PackageStatus");

            outList.add(outParam);
        }

        return outList;
    }
}
