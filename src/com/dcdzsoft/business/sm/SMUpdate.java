package com.dcdzsoft.business.sm;

import javax.sql.RowSet;

import com.dcdzsoft.EduException;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;
import com.dcdzsoft.businessproxy.PushBusinessProxy;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 升级包更新 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class SMUpdate extends ActionBean {
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
    public OutParamSMUpdate doBusiness(InParamSMUpdate in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamSMUpdate out = new OutParamSMUpdate();
        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.SoftwareVersion))
            throw new EduException(ErrorCode.ERR_PARMERR);
        
        SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
        JDBCFieldArray setCols = new JDBCFieldArray();
        JDBCFieldArray whereCols = new JDBCFieldArray();
        if(StringUtils.isNotEmpty(in.OperID)){		
	        setCols.add("TerminalPasswd",in.TerminalPasswd);
	        setCols.add("Remark",in.Remark);
	        setCols.add("SoftwareVersion",in.SoftwareVersion);
	        whereCols.add("SystemID",in.SystemID);	
	        
	        systemInfoDAO.update(setCols,whereCols);
        }else{
        	RowSet rset = systemInfoDAO.select(whereCols);
        	while(RowSetUtils.rowsetNext(rset)){
        		out.SoftwareVersion = RowSetUtils.getStringValue(rset, "SoftwareVersion");
        		if(compareVersion(in.SoftwareVersion,out.SoftwareVersion)>=0){
        			System.out.println("设备端版本号应小于服务器版本才能升级");
        			return out;
        		}
            	//下载文件的路径
        		out.Remark = RowSetUtils.getStringValue(rset, "Remark");
        		out.TerminalPasswd = RowSetUtils.getStringValue(rset, "TerminalPasswd");
        		out.TerminalNo = in.TerminalNo;
        		log.debug("软件更新："+out.toString());
        		System.out.println("软件更新："+out.toString());
        	}
        }        
        return out;
    }
    /** 
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0 
     * @param version1 
     * @param version2 
     * @return 
     */  
    public int compareVersion(String version1, String version2){  
        if (version1 == null || version2 == null) {  
            System.out.println("版本校验出错");  
        }  
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；  
        String[] versionArray2 = version2.split("\\.");  
        int idx = 0;  
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值  
        int diff = 0;  
        while (idx < minLength  
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度  
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符  
            ++idx;  
        }  
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；  
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;  
        return diff;  
    }  
}
