package com.dcdzsoft.business.tb;

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
 * <p>Description: 柜体信息查询数量 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class TBTerminalQryCount extends ActionBean {

    public int doBusiness(InParamTBTerminalQryCount in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.OperID))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //2.	调用CommonDAO.isOnline(操作员编号)判断操作员是否在线。
        String limitSQL = "";
        if(StringUtils.isNotEmpty(in.OperID))
        {
        	OPOperOnline operOnline = commonDAO.isOnline(in.OperID);
        	limitSQL = utilDAO.getLimitSQL(operOnline.DepartmentID);
        }

        PreparedWhereExpression whereSQL = new PreparedWhereExpression();
        //whereSQL.checkAdd("DepartmentID", in.DepartmentID);
        if(StringUtils.isNotBlank(in.DepartmentID)){
            limitSQL += " "+ utilDAO.getLimitSQL(in.DepartmentID);
        }
        whereSQL.checkAdd("TerminalNo", in.TerminalNo);
        //whereSQL.checkAdd("TerminalName", in.TerminalName);
        if(StringUtils.isNotEmpty(in.TerminalName))
           whereSQL.add("TerminalName", " LIKE ", "%" + in.TerminalName + "%");
        
        if(StringUtils.isNotEmpty(in.Location))
            whereSQL.add("Location", " LIKE ", "%" + in.Location + "%");
        
        whereSQL.checkAdd("RegisterFlag", in.RegisterFlag);
        whereSQL.checkAdd("TerminalStatus", in.TerminalStatus);

        String viewName = "";
        if("v".equalsIgnoreCase(in.QryFlag)){
            viewName = "V_TBTerminalV";
        }else if("s".equalsIgnoreCase(in.QryFlag)){
            viewName = "V_TBTerminalS";
        }else{
            viewName = "V_TBTerminal";
        }
        
        if(StringUtils.isNotEmpty(in.Bounds)){
            String[] bounds = in.Bounds.split(",");//Latlon,Latlon
            if(bounds.length == 4){
                // 0,2 -Latitude; 1,3- Longitude
                //经度Latitude
                double latlon0 = NumberUtils.parseDouble(bounds[0]);
                double latlon2 = NumberUtils.parseDouble(bounds[2]);
                whereSQL.add("Latitude",">", latlon0);//南
                whereSQL.add("Latitude","<", latlon2);//北
                //经度Longitude
                double latlon1 = NumberUtils.parseDouble(bounds[1]);
                double latlon3 = NumberUtils.parseDouble(bounds[3]);
                whereSQL.add("Longitude",">", latlon1);//西
                whereSQL.add("Longitude","<", latlon3);//东
            }
        }
        
        String sql = "SELECT COUNT(TerminalNo) FROM "+viewName+" a WHERE 1=1 " + whereSQL.getPreparedWhereSQL() + limitSQL;

        result = dbSession.executeQueryCount(sql,whereSQL);

        return result;
    }
}
