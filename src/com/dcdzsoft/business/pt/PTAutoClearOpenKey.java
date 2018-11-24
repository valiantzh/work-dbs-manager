package com.dcdzsoft.business.pt;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.constant.ControlParam;
import com.dcdzsoft.constant.ErrorCode;
import com.dcdzsoft.constant.SysDict;
import com.dcdzsoft.dao.PMCompanyDAO;
import com.dcdzsoft.dao.PTInBoxPackageDAO;
import com.dcdzsoft.dto.business.InParamAutoClearOpenKey;
import com.dcdzsoft.dto.business.InParamPTResetOpenBoxKey;
import com.dcdzsoft.guotong.GuotongManager;
import com.dcdzsoft.sda.db.JDBCFieldArray;
import com.dcdzsoft.sda.db.RowSetUtils;
import com.dcdzsoft.sda.security.SecurityUtils;
import com.dcdzsoft.sms.SMSInfo;
import com.dcdzsoft.util.DateUtils;
import com.dcdzsoft.util.NumberUtils;
import com.dcdzsoft.util.StringUtils;
/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 自动设置清除过期订单密码 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class PTAutoClearOpenKey extends ActionBean{


    public int doBusiness(InParamAutoClearOpenKey in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        int result = 0;

		//1.验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo))
                throw new EduException(ErrorCode.ERR_PARMERR);
        
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
            }/*else{
                int expiredDays = commonDAO.expiredDays();
                result = doBusiness(in, expiredDays, "");
            }*/
            
        }else{
            int expiredDays = commonDAO.expiredDays();
            result = doBusiness(in, expiredDays, "");
        }
        
        
        return result;
    }
    
    private int doBusiness(InParamAutoClearOpenKey in, int expiredDays, String companyID) throws EduException{
        int result = 0;
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        
        //查询即将逾期的订单
        PTInBoxPackageDAO inBoxPackageDAO = daoFactory.getPTInBoxPackageDAO();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        whereCols.add("TerminalNo", in.TerminalNo);
        whereCols.add("PackageStatus", SysDict.PACKAGE_STATUS_NORMAL);
        //whereCols.add("PackageStatus", "<>", SysDict.PACKAGE_STATUS_TIMEOUT);
        
        whereCols.checkAdd("CompanyID", companyID);//按公司设定逾期天数
        
        whereCols.addSQL(" AND ( (2=2 ");
        //未设置逾期时间，按recoveryTimeout时间逾期；设置的逾期时间大于存物时间，按设置的逾期时间逾期
        whereCols.addSQL(" AND ( ExpiredTime IS NULL OR ExpiredTime <= LastModifyTime )");//小于下订单时间，表示没有设置逾期时间
        whereCols.add("LastModifyTime", "<=", DateUtils.addTimeStamp(sysDateTime, -expiredDays));
        whereCols.addSQL(") OR (2=2 AND ExpiredTime > LastModifyTime "); //设置的逾期时间大于下订单时间，按设置的逾期时间逾期
        whereCols.add("ExpiredTime", "<", sysDateTime);
        whereCols.addSQL("  ) )"); //
        
        RowSet rset1 = inBoxPackageDAO.select(whereCols);
        while(RowSetUtils.rowsetNext(rset1))
        {
            String packageID       = RowSetUtils.getStringValue(rset1, "PackageID");//取的packageID和TerminalNo查询出逾期订单记录，
            String customerMobile  = RowSetUtils.getStringValue(rset1, "CustomerMobile");
            String OpenBoxKey      = RowSetUtils.getStringValue(rset1, "OpenBoxKey");
            if(StringUtils.isEmpty(customerMobile)){//将未下单的订单和第一次清除掉的排除。
                continue;
            }
            //由于推送到设备端的密码不能为空，所以使用新密码替换旧密码
            String blankBoxKey     = commonDAO.generatePickupPwd(OpenBoxKey, in.TerminalNo);//明文
            String openBoxKey      = SecurityUtils.md5(blankBoxKey);//密文    
            //先修改服务端密码，设备端不在线会导致更新设备端密码失败，密码验证统一改为网络验证
            JDBCFieldArray whereCols1 = new JDBCFieldArray();
            JDBCFieldArray setCols1 = new JDBCFieldArray();
            
            setCols1.add("CustomerMobile", "");
            setCols1.add("OpenBoxKey", openBoxKey);//清除密码，旧密码换成新密码。
            setCols1.add("BlankBoxKey", blankBoxKey);
            setCols1.add("Remark", "");//清除二维码
            setCols1.add("LastModifyTime", sysDateTime);
            
            whereCols1.add("TerminalNo", in.TerminalNo);
            whereCols1.add("PackageID", packageID);
            inBoxPackageDAO.update(setCols1, whereCols1);
            //推送到设备端
            InParamPTResetOpenBoxKey re = new InParamPTResetOpenBoxKey();
            re.PackageID = packageID;
            re.OperID = "system";
            re.TerminalNo = in.TerminalNo;
            re.CustomerMobile = customerMobile;
            re.OpenBoxKey = openBoxKey;
            re.RemoteFlag = SysDict.OPER_FLAG_REMOTE;
            System.out.println(re.toString());
            com.dcdzsoft.businessproxy.PushBusinessProxy.doBusiness(re);
            
            result++;
        }
        return result;
    }
}
