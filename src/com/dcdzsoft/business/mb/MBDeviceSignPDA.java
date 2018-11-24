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
import com.dcdzsoft.client.pda.PDAManager;
import com.dcdzsoft.client.pda.PDASignInfo;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: PDA设备签到 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: dcdzsoft</p>
 * @author zhengxy
 * @version 1.0
 */

public class MBDeviceSignPDA extends ActionBean
{

    public OutParamMBDeviceSignPDA doBusiness(InParamMBDeviceSignPDA in) throws EduException
    {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamMBDeviceSignPDA out = new OutParamMBDeviceSignPDA();

		//1.验证输入参数是否有效，如果无效返回-1。
		if (StringUtils.isEmpty(in.PDANo))
			throw new EduException(ErrorCode.ERR_PARMERR);
		
		java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();

		//判断安装密码是否一致
        String SoftwareVersion = "";
        String InitPasswd = "";
        String LastInitPasswd = "";
        //String WelcomeInfo = "";


        SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
        JDBCFieldArray whereColsDummy = new JDBCFieldArray();
        RowSet rset = systemInfoDAO.select(whereColsDummy);

        if(RowSetUtils.rowsetNext(rset))
        {
            SoftwareVersion = RowSetUtils.getStringValue(rset, "SoftwareVersion");
            InitPasswd = RowSetUtils.getStringValue(rset, "InitPasswd");
            LastInitPasswd = RowSetUtils.getStringValue(rset, "LastInitPasswd");
            //WelcomeInfo = RowSetUtils.getStringValue(rset, "UpdateContent");
        }

        //安装密码不一致
        /*if(!InitPasswd.equalsIgnoreCase(in.InitPasswd))
        {
            if(StringUtils.isEmpty(LastInitPasswd) || !in.InitPasswd.equalsIgnoreCase(LastInitPasswd)){
                out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;
                out.SoftwareVersion = SoftwareVersion;

                log.error("[invalid initpasswd] " + in.PDANo);

                return out;
            }
        }*/
        
        out.SoftwareVersion = SoftwareVersion;
        out.ServerTime      = sysDateTime;
        out.SignKey         = "";
        
        ///////记录PDA信息
        TBPDADAO pdaDAO = daoFactory.getTBPDADAO();
        TBPDA pda = new TBPDA();
        pda.PDANo = in.PDANo;
        
        if(pdaDAO.isExist(pda)){
            pda = pdaDAO.find(pda);
            
            //已经PDANo已被其他设备占用，注册失败
            /*if(!pda.DeviceID.equals(in.DeviceID)){///测试先屏蔽
                //throw new EduException(ErrorCode.ERR_MODTERMINANO4REGISTER);
                out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;
                return out;
            }*/
            
            //PDA注销，不允许再签到
            if(SysDict.TERMINAL_STATUS_CANCEL.equals(pda.PDAStatus)){
                out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;
                return out;
            }
            //更新PDA信息
            JDBCFieldArray setCols = new JDBCFieldArray();
            JDBCFieldArray whereCols = new JDBCFieldArray();

            
            setCols.checkAdd("PDAName", in.PDAName);
            setCols.checkAdd("DeviceID", in.DeviceID);
            setCols.add("RegisterFlag", SysDict.TERMINAL_REGISTERFLAG_YES);
            setCols.checkAdd("SIMCard", in.SIMCard);
            setCols.checkAdd("MobileNum", in.Mobile);
            setCols.add("LastModifyTime", sysDateTime);

            whereCols.add("PDANo", in.PDANo);

            pdaDAO.update(setCols, whereCols);
        }else{
            pda.DeviceID = in.DeviceID;
            pda.PDAName  = in.PDAName;
            pda.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_YES;
            pda.SIMCard      = in.SIMCard;
            pda.MobileNum    = in.Mobile;
            pda.PDAStatus    = "0";
            pda.UpdateTime   = sysDateTime;
            pda.LastModifyTime = sysDateTime;
            pda.TerminalNo     = "";   
            pdaDAO.insert(pda);
        }
        
        try {
            
            //设备签到
            MBSignInfoDAO signInfoDAO = daoFactory.getMBSignInfoDAO();
            MBSignInfo signInfo = new MBSignInfo();
            signInfo.TerminalNo = in.PDANo;

            if(signInfoDAO.isExist(signInfo))
            {
                JDBCFieldArray setCols1 = new JDBCFieldArray();
                JDBCFieldArray whereCols1 = new JDBCFieldArray();

                setCols1.add("SignTime", sysDateTime);
                setCols1.add("SignIP", in.Mobile);
                setCols1.add("SoftwareVersion", in.SoftwareVersion);
                setCols1.add("SignMac", in.DeviceID);
                setCols1.add("InitPasswd", in.InitPasswd);
                setCols1.add("OnlineStatus","1");
                setCols1.add("LastModifyTime", sysDateTime);

                whereCols1.add("TerminalNo", in.PDANo);

                signInfoDAO.update(setCols1, whereCols1);
            }
            else
            {
                signInfo.SignTime = sysDateTime;
                signInfo.SignIP = in.Mobile;
                signInfo.SoftwareVersion = in.SoftwareVersion;
                signInfo.SignMac = in.DeviceID;
                signInfo.InitPasswd = in.InitPasswd;
                signInfo.OnlineStatus = "1";
                signInfo.LastModifyTime = sysDateTime;

                signInfoDAO.insert(signInfo);
            }
            out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_YES;
            
            //将PDA签到信息保存到内存中
            PDASignInfo pdaSignInfo = new PDASignInfo();
            pdaSignInfo.setPdaNo(in.PDANo);
            pdaSignInfo.setDeviceID(in.DeviceID);
            pdaSignInfo.setStatus(SysDict.TERMINAL_STATUS_NORMAL);
            pdaSignInfo.setAuthToken(in.InitPasswd);
            PDAManager.getInstance().addPDASignInfo(pdaSignInfo );
            
        }catch(EduException e) {
            out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;

            log.error("[register PDA failure],PDANo=" + in.PDANo + ",err=" + e.getMessage());
        }
        return out;
    }
}
