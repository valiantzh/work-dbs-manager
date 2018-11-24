package com.dcdzsoft.business.pt;

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
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 下载待投递订单列表 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTReadPackageQry extends ActionBean {

    public java.util.List<OutParamPTReadPackageQry> doBusiness(InParamPTReadPackageQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        java.util.List<OutParamPTReadPackageQry> outList = new java.util.LinkedList<OutParamPTReadPackageQry>();

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
        commonDAO.checkManTerminalRight(postman,in.TerminalNo);

        ///////////////////////////////
        PTReadyPackageDAO readyPackDAO = daoFactory.getPTReadyPackageDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("TerminalNo",in.TerminalNo);
        whereCols.add("PostmanID",in.PostmanID);

        rset = readyPackDAO.select(whereCols);

        while(RowSetUtils.rowsetNext(rset)){
            OutParamPTReadPackageQry outParam = new OutParamPTReadPackageQry();
            outParam.PID = RowSetUtils.getStringValue(rset,"PackageID");
            outParam.BNO = RowSetUtils.getStringValue(rset,"BoxNo");
            outParam.MOB = RowSetUtils.getStringValue(rset,"CustomerMobile");
            outParam.FlG = RowSetUtils.getStringValue(rset,"PosPayFlag");
            outParam.STS = RowSetUtils.getStringValue(rset,"PackageStatus");

            outList.add(outParam);
        }

        return outList;
    }
}
