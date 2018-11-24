package com.dcdzsoft.business.op;

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
 * <p>Description: 管理员菜单信息查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class OPOperToMenuQry extends ActionBean {

    public RowSet doBusiness(InParamOPOperToMenuQry in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        RowSet rset = null;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID)
            || StringUtils.isEmpty(in.ByOperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        String limitSql = " ";
        
        //11000000~11999999系统配置管理，只能由超级管理员查看?????????
        /*if(in.OperID.compareTo(Constant.DEFAULT_SUPEROPERID) != 0){
            limitSql = " AND b.MenuID>='12030000' AND b.MenuID<'85000000' ";
        }*/
        
        //3.	查询操作员菜单信息：
        /*String sql =
                 "SELECT a.OperID,b.MenuID,b.MenuName,b.MenuLevel,b.MenuType,b.LeafFlag "
                 + " FROM OPMenu b LEFT OUTER JOIN (SELECT OperID,MenuID FROM OPOperToMenu WHERE OperID = " +
                 StringUtils.addQuote(in.ByOperID)
                 + " ) a ON(a.MenuID = b.MenuID) WHERE b.MenuLevel=" +
                 (in.MenuLevel + 1)
                 +" "+limitSql;*/
        String sql = "SELECT a.OperID,b.MenuID,b.MenuName,b.MenuLevel,b.MenuType,b.LeafFlag FROM OPMenu b "
                + "LEFT OUTER JOIN ("
                + " SELECT OperID,MenuID FROM OPOperToMenu WHERE OperID = " +StringUtils.addQuote(in.ByOperID)
                + " UNION"
                + " SELECT a.OperID,b.MenuID FROM OPOperRole a, OPRoleToMenu b WHERE  b.RoleID=a.RoleID AND OperID = "+StringUtils.addQuote(in.ByOperID)
                + ") a ON(a.MenuID = b.MenuID) WHERE b.MenuLevel=" +(in.MenuLevel + 1)
                +" "+limitSql;
         if (StringUtils.isNotEmpty(in.MenuID) && in.MenuLevel != 0) {
             String substrSQL = utilDAO.getSubstringSQL("b.MenuID", 1,
                     in.MenuLevel * 2);

             sql = sql + " AND " + substrSQL + " = " +
                   StringUtils.addQuote(in.MenuID.substring(0, in.MenuLevel * 2));
         }

         rset = dbSession.executeQuery(sql);


        return rset;
    }
}
