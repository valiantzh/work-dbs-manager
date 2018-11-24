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

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 数据库配置查询 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SMDbCfgQry extends ActionBean {

    public RowSet doBusiness(InParamSMDbCfgQry in) throws EduException {
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


        DataSourceUtils dbCfg = DataSourceUtils.getInstance();

        DataRow dRow = new DataRow();
        dRow.addIntColumn("maxActive");
        dRow.addIntColumn("maxIdle");
        dRow.addIntColumn("minIdle");
        dRow.addIntColumn("maxWait");
        dRow.addIntColumn("numActive");
        dRow.addIntColumn("numIdle");

        ColFieldArray fArray = new ColFieldArray();
        fArray.addCol("maxActive", dbCfg.getMaxActive());
        fArray.addCol("maxIdle", dbCfg.getMaxIdle());
        fArray.addCol("minIdle", dbCfg.getMinIdle());
        fArray.addCol("maxWait", (int)dbCfg.getMaxWait());
        fArray.addCol("numActive", dbCfg.getNumActive());
        fArray.addCol("numIdle", dbCfg.getNumIdle());

        dRow.addRow(fArray);

        DataRowToRowSet dtr = new DataRowToRowSet(dRow);
        rset = dtr.arrayToRowSet();


        return rset;
    }
}
