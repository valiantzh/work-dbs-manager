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
 * <p>Description: 自动锁定过期订单 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class PTAutoLockOrder extends ActionBean
{
	
    public int doBusiness(InParamPTAutoLockOrder in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;
        
        //////////////////////////////////////////////////////////////
        
        
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
    int doBusiness(InParamPTAutoLockOrder in,  int expiredDays, String companyID) throws EduException{
        int result = 0;
        
        java.sql.Date sysDate = utilDAO.getCurrentDate();
        java.sql.Date expiredDate = DateUtils.addDate(sysDate,-expiredDays);
        
        PTInBoxPackageDAO inboxPackDAO = daoFactory.getPTInBoxPackageDAO();
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        setCols.add("PackageStatus", SysDict.PACKAGE_STATUS_LOCKED);
        whereCols.add("PackageStatus", SysDict.PACKAGE_STATUS_NORMAL);
        whereCols.add("StoredDate", "<=",expiredDate);
        whereCols.checkAdd("CompanyID", companyID);//按公司设定逾期天数
        
        result = inboxPackDAO.update(setCols,whereCols);
        return result;
    }
}
