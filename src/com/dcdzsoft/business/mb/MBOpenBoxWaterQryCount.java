package com.dcdzsoft.business.mb;

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
 * <p>Description: 开箱流水信息查询数量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class MBOpenBoxWaterQryCount extends ActionBean
{

    public int doBusiness(InParamMBOpenBoxWaterQryCount in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
		//if (StringUtils.isEmpty(in.OperID))
		//	throw new EduException(ErrorCode.ERR_PARMERR);

		//2.调用CommonDAO.isOnline(管理员编号)判断操作员是否在线。
		OPOperOnline operOnline = null;
        String limitSQL = "";
        if(StringUtils.isNotEmpty(in.OperID)){
            operOnline = commonDAO.isOnline(in.OperID);
            limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        }

        //3.调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        java.sql.Date sysDate = DateUtils.toSQLDate(sysDateTime);

        if(in.BeginDate==null){
            in.BeginDate = DateUtils.addDate(sysDate, -7);
        }
        
        // ///////////////////////////////////////////////////////////
        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        whereSQL.checkAdd("BoxNo", in.BoxNo);
        whereSQL.checkAdd("UploadFlag", in.UploadFlag);
        whereSQL.checkAdd("PackageStatus", in.PackageStatus);
        if(StringUtils.isNotEmpty(in.PackageID)){
            whereSQL.add("PackageID", in.PackageID);
        }else{
            whereSQL.checkAdd("OpenBoxDate", ">=",in.BeginDate);
            whereSQL.checkAdd("OpenBoxDate", "<=",in.EndDate);
        }


		String sql = "SELECT COUNT(*) FROM V_MBOpenBoxWater a WHERE 1=1 "+ whereSQL.getPreparedWhereSQL() + limitSQL;

        result = dbSession.executeQueryCount(sql,whereSQL);

        return result;
    }
}
