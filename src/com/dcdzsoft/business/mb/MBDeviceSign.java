package com.dcdzsoft.business.mb;


import net.sf.json.JSONObject;

import com.dcdzsoft.EduException;
import com.dcdzsoft.business.GServer;
import com.dcdzsoft.sda.db.*;
import com.dcdzsoft.sequence.SequenceGenerator;
import com.dcdzsoft.util.*;
import com.dcdzsoft.dto.function.*;
import com.dcdzsoft.dto.business.*;
import com.dcdzsoft.dao.*;
import com.dcdzsoft.guotong.GuotongManager;
import com.dcdzsoft.config.ApplicationConfig;
import com.dcdzsoft.constant.*;
import com.dcdzsoft.business.ActionBean;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 * <p>Description: 设备签到 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: dcdzsoft</p>
 * @author 王中立
 * @version 1.0
 */

public class MBDeviceSign extends ActionBean {
	private static ApplicationConfig apCfg = ApplicationConfig.getInstance();
	
    public OutParamMBDeviceSign doBusiness(InParamMBDeviceSign in) throws EduException {
        utilDAO = this.getUtilDAO();
        commonDAO = this.getCommonDAO();
        dbSession = this.getCurrentDBSession();
        OutParamMBDeviceSign out = new OutParamMBDeviceSign();

        //1.	验证输入参数是否有效，如果无效返回-1。
        if (StringUtils.isEmpty(in.TerminalNo))
            throw new EduException(ErrorCode.ERR_PARMERR);

        //3.	调用UtilDAO.getSysDateTime()获得系统日期时间。
        java.sql.Timestamp sysDateTime = utilDAO.getCurrentDateTime();
        out.ServerTime = sysDateTime;

        //判断安装密码是否一致
        String SoftwareVersion = "";
        String InitPasswd = "";
        String LastInitPasswd = "";
        String WelcomeInfo = "";

        //系统信息
        try{
            SMSystemInfoDAO systemInfoDAO = daoFactory.getSMSystemInfoDAO();
            SMSystemInfo systemInfo = new SMSystemInfo();
            systemInfo.SystemID = "10001";//10001-默认系统编号
            if("1".equals(ControlParam.getInstance().terminalSoftUpgrade)){
                SMTerminalSoftDAO terminalSoftDAO = daoFactory.getSMTerminalSoftDAO();
                try{
                    SMTerminalSoft terminalSoft = new SMTerminalSoft();
                    terminalSoft.TerminalNo = in.TerminalNo;
                    terminalSoft.SoftwareType = "2";//2-终端软件
                    terminalSoft = terminalSoftDAO.find(terminalSoft);
                    
                    if(StringUtils.isNotEmpty(terminalSoft.SystemID)){
                        systemInfo.SystemID = terminalSoft.SystemID;
                    }
                }catch(EduException e){
                    log.error("SMTerminalSoft Qry error:"+e.getMessage());
                }
                softVersionBusiness(in, sysDateTime);
            }
            
            systemInfo = systemInfoDAO.find(systemInfo);
            SoftwareVersion = systemInfo.SoftwareVersion;
            InitPasswd = systemInfo.InitPasswd;
            LastInitPasswd = systemInfo.LastInitPasswd;
            WelcomeInfo = systemInfo.UpdateContent;
        }catch(EduException e){
            log.error("SystemID Qry error:"+e.getMessage());
        }
        /*JDBCFieldArray whereColsDummy = new JDBCFieldArray();
        RowSet rset = systemInfoDAO.select(whereColsDummy);

        if(RowSetUtils.rowsetNext(rset))
        {
            SoftwareVersion = RowSetUtils.getStringValue(rset, "SoftwareVersion");
            InitPasswd = RowSetUtils.getStringValue(rset, "InitPasswd");
            LastInitPasswd = RowSetUtils.getStringValue(rset, "LastInitPasswd");
            WelcomeInfo = RowSetUtils.getStringValue(rset, "UpdateContent");
        }*/
        
        //安装密码不一致
        if(!InitPasswd.equalsIgnoreCase(in.InitPasswd))
        {
        	if(StringUtils.isEmpty(LastInitPasswd) || !in.InitPasswd.equalsIgnoreCase(LastInitPasswd)){
        		out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;
        		out.SoftwareVersion = SoftwareVersion;

        		log.error("[invalid initpasswd] " + in.TerminalNo+","+in.InitPasswd);

        		return out;
        	}
        }

      //设备签到
        MBSignInfoDAO signInfoDAO = daoFactory.getMBSignInfoDAO();
        MBSignInfo signInfo = new MBSignInfo();
        signInfo.TerminalNo = in.TerminalNo;

        if(signInfoDAO.isExist(signInfo))
        {
            //判断MAC地址？？？
            /*signInfo = signInfoDAO.find(signInfo);
            if(in.SignMac.equals(signInfo.SignMac)){
                out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;
                out.SoftwareVersion = SoftwareVersion;

                log.error("[invalid SignMac] " + in.TerminalNo+",SignMac="+in.SignMac);

                return out;
            }*/
            
            JDBCFieldArray setCols1 = new JDBCFieldArray();
            JDBCFieldArray whereCols1 = new JDBCFieldArray();

            setCols1.add("SignTime", sysDateTime);
            setCols1.add("SignIP", in.SignIP);
            setCols1.add("SoftwareVersion", in.SoftwareVersion);
            setCols1.add("SignMac", in.SignMac);
            setCols1.add("InitPasswd", in.InitPasswd);
            setCols1.add("OnlineStatus","1");
            setCols1.add("LastModifyTime", sysDateTime);

            whereCols1.add("TerminalNo", in.TerminalNo);
            
            signInfoDAO.update(setCols1, whereCols1);
        } else {
            signInfo.SignTime = sysDateTime;
            signInfo.SignIP = in.SignIP;
            signInfo.SoftwareVersion = in.SoftwareVersion;
            signInfo.SignMac = in.SignMac;
            signInfo.InitPasswd = in.InitPasswd;
            signInfo.OnlineStatus = "1";
            signInfo.LastModifyTime = sysDateTime;

            signInfoDAO.insert(signInfo);
        }
        
        try
        {
        	String terminalNo = insertTerminal(in.TerminalInfo, in.BoxInfo, sysDateTime);
            insertBox(in.BoxInfo, terminalNo);

            out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_YES;
        }
        catch(EduException e)
        {
        	out.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_FAILURE;

        	log.error("[register terminal failure],terminalNo=" + in.TerminalNo + ",err=" + e.getMessage());
        }
        
        out.SoftwareVersion = SoftwareVersion;
        out.ServerTime = sysDateTime;
        out.WelcomeInfo = WelcomeInfo;
        if(ApplicationConfig.getInstance().getSysRunModel() == Constant.SYS_RUN_MODEL_OPERATE)
        {
	        out.SignKey = StringUtils.getUUID();
	        ///加入signkey到内存
	        GServer.getInstance().addSignKey(in.TerminalNo, out.SignKey);
        }else
        {
        	//想其它的办法，否则日志服务启动不了了
        	out.SignKey = "";
        	
        	//out.SignKey = StringUtils.getUUID();
 	        ///加入signkey到内存
 	        //GServer.getInstance().addSignKey(in.TerminalNo, out.SignKey);
        }
        
        return out;
    }
    
    private void softVersionBusiness(InParamMBDeviceSign in, java.sql.Timestamp sysDateTime){
        //终端软件升级管理
        try{
            //版本升级完成
            if(StringUtils.isNotEmpty(in.SoftwareVersion)){
                SMTerminalSoftDAO terminalSoftDAO = daoFactory.getSMTerminalSoftDAO();
                String[] softVersions = in.SoftwareVersion.split(",");//<终端软件版本>[,<驱动软件>]
                for(int i=0; i< softVersions.length; i++){
                    String lastVersion = softVersions[i];
                    SMTerminalSoft terminalSoft = new SMTerminalSoft();
                    terminalSoft.TerminalNo = in.TerminalNo;    
                    if(i == 0){//终端软件
                        terminalSoft.SoftwareType = "2";//2-终端软件
                        if(terminalSoftDAO.isExist(terminalSoft)){
                            terminalSoft = terminalSoftDAO.find(terminalSoft);
                        }
                    }else if(i == 1){//驱动软件
                        terminalSoft.SoftwareType = "1";//1- 驱动软件
                        if(terminalSoftDAO.isExist(terminalSoft)){
                            terminalSoft = terminalSoftDAO.find(terminalSoft);
                        }
                    }
                    
                    if(StringUtils.isNotEmpty(terminalSoft.SoftwareID)){
                        //记录更新流水
                        SMUpgradePackDAO upgradePackDAO = daoFactory.getSMUpgradePackDAO();
                        SMUpgradePack upgradePack = new SMUpgradePack();
                        upgradePack.SoftwareID = terminalSoft.SoftwareID;
                        if(upgradePackDAO.isExist(upgradePack) 
                                && "1".equals(terminalSoft.UpdateStatus)){//等待更新
                            upgradePack = upgradePackDAO.find(upgradePack);
                            
                            if(lastVersion.equalsIgnoreCase(upgradePack.SoftwareVersion)){
                                //版本号一致，更新完成
                                JDBCFieldArray setCols = new JDBCFieldArray();
                                JDBCFieldArray whereCols = new JDBCFieldArray();
                                
                                setCols.add("LastVersion", lastVersion);
                                setCols.add("UpdateStatus", "0");//0-更新完成
                                setCols.add("LastModifyTime", sysDateTime);
                                whereCols.add("SoftwareID", terminalSoft.SoftwareID);
                                whereCols.add("TerminalNo", in.TerminalNo);
                                terminalSoftDAO.update(setCols, whereCols);
                                
                                /////更新流水
                                SMUpgradeWaterDAO upgradeWaterDAO = daoFactory.getSMUpgradeWaterDAO();
                                SMUpgradeWater upgradeWater = new SMUpgradeWater();
                                upgradeWater.UpgradeWaterID = SequenceGenerator.getInstance().getNextKey(SequenceGenerator.SEQ_UPGRADEWATERID);
                                upgradeWater.SoftwareID = upgradePack.SoftwareID;
                                upgradeWater.TerminalNo = in.TerminalNo;
                                upgradeWater.SoftwareName = upgradePack.SoftwareName;
                                upgradeWater.SoftwareType = upgradePack.SoftwareType;
                                upgradeWater.SoftwareVersion = upgradePack.SoftwareVersion;
                                upgradeWater.SoftSignMd5 = upgradePack.SoftSignMd5;
                                upgradeWater.SystemType = upgradePack.SystemType;
                                upgradeWater.SystemID = upgradePack.SystemID;
                                upgradeWater.DownloadUrl = upgradePack.DownloadUrl;
                                upgradeWater.UpdateContent = upgradePack.UpdateContent;
                                upgradeWater.LastModifyTime = sysDateTime;
                                upgradeWater.Remark = "LastVersion="+terminalSoft.LastVersion;
                                upgradeWaterDAO.insert(upgradeWater);
                            }
                            
                        }
                    }
                    /////////////修改软件更新状态
                    /*JDBCFieldArray setCols = new JDBCFieldArray();
                    JDBCFieldArray whereCols = new JDBCFieldArray();
                    
                    setCols.add("LastVersion", lastVersion);
                    //setCols.add("UpdateStatus", "0");//0-更新完成
                    setCols.add("LastModifyTime", sysDateTime);
                    //whereCols.add("UpdateStatus", "1");
                    whereCols.add("TerminalNo", in.TerminalNo);
                    
                    if(i == 0){//终端软件
                        whereCols.add("SoftwareType", "2");//2-终端软件
                        updateCount = terminalSoftDAO.update(setCols, whereCols);
                        if(updateCount>0){
                            terminalSoft.SoftwareType = "2";//2-终端软件
                            terminalSoft = terminalSoftDAO.find(terminalSoft);
                        }
                        
                    }else if(i == 1){//驱动软件
                        whereCols.add("SoftwareType", "1");//1- 驱动软件
                        updateCount = terminalSoftDAO.update(setCols, whereCols);
                        
                        if(updateCount>0){
                            terminalSoft.SoftwareType = "1";//1- 驱动软件
                            terminalSoft = terminalSoftDAO.find(terminalSoft);
                        }
                    }*/
                    
                }
            }
        }catch(EduException e){
            log.error("softVersionBusiness:"+e.getMessage());
        }
        
        return;
    }
    protected String insertTerminal(String terminalInfo, String boxInfo, java.sql.Timestamp sysDateTime) throws EduException
    {
    	JSONObject obj = JSONObject.fromObject(terminalInfo);
    	TBTerminal terminal = (TBTerminal)JsonUtils.jsonObjectToDTO(obj, TBTerminal.class);
    	TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();

    	if(terminalDAO.isExist(terminal))
    	{
    		//已经存在柜体，注册失败
    		if(!SysDict.TERMINAL_REGISTERFLAG_YES.equalsIgnoreCase(terminal.RegisterFlag))
    		{
    			throw new EduException(ErrorCode.ERR_MODTERMINANO4REGISTER);
    		}
    		
    		TBTerminal terminalOld = terminalDAO.find(terminal);
            
            //已经存在但是已注销的柜体
            if(SysDict.TERMINAL_STATUS_CANCEL.equalsIgnoreCase(terminalOld.TerminalStatus)){
                throw new EduException(ErrorCode.ERR_MODTERMINANO4REGISTER);
            }
            
    		//更新
    		JDBCFieldArray setCols = new JDBCFieldArray();
    		JDBCFieldArray whereCols = new JDBCFieldArray();

    		setCols.add("BoxNum", terminal.BoxNum);
    		setCols.add("DeskNum", terminal.DeskNum);
    		setCols.checkAdd("TerminalName", terminal.TerminalName);
    		setCols.checkAdd("TerminalStatus", terminal.TerminalStatus);
    		setCols.checkAdd("Location", terminal.Location);

    	    //#start 在终端修改柜体坐标  add by zxy
    	    if(StringUtils.isNotEmpty(terminal.Latlon)){
    	        String[] Latlon = terminal.Latlon.split(",");
    	        if(Latlon!=null && Latlon.length == 2){
    	            setCols.add("Latlon", terminal.Latlon);
    	            setCols.add("Latitude", NumberUtils.parseDouble(Latlon[0]));
    	            setCols.add("Longitude", NumberUtils.parseDouble(Latlon[1]));
    	        } 
    	    }
    	    //#end
            
    		whereCols.add("TerminalNo", terminal.TerminalNo);

    		terminalDAO.update(setCols, whereCols);
    		
    		//#start 向国通同步柜体信息  add by zxy 20160128
            if(apCfg.isRegisterToPartner())
            {
                terminal = terminalDAO.find(terminal);
                try{
                    /*if("0".equals(terminal.RegisterFlag)){//未注册，重新注册
                        syncTerminalInfo(terminal, boxInfo,"A");
                    }else{
                        syncTerminalInfo(terminal, boxInfo,"U");
                    }*/
                    syncTerminalInfo(terminal, boxInfo,"U");
                }catch(Exception e)
                {
                    System.err.println("[syncTerminalInfo error("+terminal.TerminalNo+")] = " + e.getMessage());
                }
                
                
            }
            //#end
            
    	}else
    	{
    		//修改设备所属营业网点后改为已注册成功
    		if(StringUtils.isNotEmpty(terminal.OfBureau) && apCfg.isRegisterToPartner())
    		{
    			MBDepartmentDAO departmentDAO = daoFactory.getMBDepartmentDAO();
    			MBDepartment department = new MBDepartment();
    			department.DepartmentID = terminal.OfBureau;
    			
    			if(departmentDAO.isExist(department) == false)
    				throw new EduException(ErrorCode.ERR_NOTEXISTSBUREAU);
    			
    			terminal.DepartmentID = terminal.OfBureau; //上海邮政做法，挂到支局
    			terminal.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_YES;
    		}else{
    			terminal.DepartmentID = ControlParam.getInstance().headerDepartmentID;
        		terminal.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_NO;
    		}

        	//#start add by zxy
            String[] Latlon = {"0","0"};
            if(StringUtils.isNotEmpty(terminal.Latlon)){
                Latlon = terminal.Latlon.split(",");
            }
            terminal.Latitude=NumberUtils.parseDouble(Latlon[0]);
            terminal.Longitude=NumberUtils.parseDouble(Latlon[1]);
            //#end
            
        	terminal.CreateTime = sysDateTime;
    		terminal.LastModifyTime = sysDateTime;
    		terminal.Remark = "1";
    		terminal.TerminalStatus = SysDict.TERMINAL_STATUS_NORMAL;
    		terminalDAO.insert(terminal);
    		
    		//向国通注册(第一次向国通注册) 在插入数据之后
            if(apCfg.isRegisterToPartner())
            {
                syncTerminalInfo(terminal, boxInfo,"A");
            }
    		
    	}

    	return terminal.TerminalNo;
    }

    protected void insertBox(String boxInfo,String TerminalNo) throws EduException
    {
    	//JSONArray jsonArray = JSONArray.fromObject(boxInfo);
    	//java.util.List dtoList  = JsonUtils.jsonObjectToListDTO(jsonArray, TBServerBox.class);
    	if(StringUtils.isEmpty(boxInfo))
    		return;

    	String[] boxList = boxInfo.split("~");


    	TBServerBoxDAO boxDAO = daoFactory.getTBServerBoxDAO();
    	JDBCFieldArray whereCols = new JDBCFieldArray();
    	whereCols.add("TerminalNo", TerminalNo);

    	int boxCount = boxDAO.isExist(whereCols);
    	if(boxCount != boxList.length){
    		boxDAO.delete(whereCols);

    		//插入箱体信息
    		int dtoCount = boxList.length;
    		for(int i = 0;i < dtoCount; i++)
    		{
    			//TBServerBox box = (TBServerBox)dtoList.get(i);
    			String[] boxStr = boxList[i].split(",");
    			if(boxStr.length < 6)
    				continue;

    			TBServerBox box  = new TBServerBox();
    			box.TerminalNo = TerminalNo;
    			box.BoxNo = boxStr[0];
    			box.BoxName = boxStr[1];
    			box.DeskNo = NumberUtils.parseInt(boxStr[2]);
    			box.DeskBoxNo = NumberUtils.parseInt(boxStr[3]);
    			box.BoxType = boxStr[4];
    			box.BoxStatus = boxStr[5];

    			boxDAO.insert(box);
    		}
    		
    	}
    }
    private void syncTerminalInfo(TBTerminal terminal, String boxInfo, String GXBZ) throws EduException{
        
        if(!apCfg.isRegisterToPartner()){
            return;
        }
        TBTerminalDAO terminalDAO = daoFactory.getTBTerminalDAO();
        //向国通注册(第一次向国通注册)
        if("A".equals(GXBZ)){
            if(ApplicationConfig.getInstance().isNewGuotongApi()){
                //#start注册并获取运营方设备ID
                String resMsg = GuotongManager.getInstance().sentRegisterTerminal(terminal,"A");
                //F0#|T201601:310009314;
                if(resMsg.startsWith("F0#|")){
                    String temp = resMsg.substring(4);//T201601:310009314;
                    String[] temp2 = temp.split(";");
                    String[] lockerid = temp2[0].split(":");
                    if(lockerid.length == 2 && terminal.TerminalNo.equals(lockerid[0])){
                        terminal.MBDeviceNo = lockerid[1];
                        //记录运营方柜体编号
                        JDBCFieldArray setCols1 = new JDBCFieldArray();
                        JDBCFieldArray whereCols1 = new JDBCFieldArray();
                        setCols1.add("RegisterFlag", "1");
                        setCols1.add("MBDeviceNo", lockerid[1]);
                        whereCols1.add("TerminalNo", terminal.TerminalNo);

                        terminalDAO.update(setCols1, whereCols1);
                    }else{
                        throw new EduException(resMsg);
                    }
                }else{
                    throw new EduException(resMsg);
                }
                //#end
                
                //#start 同步格口信息
                if(StringUtils.isNotEmpty(boxInfo)){
                    resMsg = GuotongManager.getInstance().sentBoxInfo(terminal,boxInfo,"A");
                    if(!resMsg.startsWith("F0")){
                        throw new EduException(ErrorCode.ERR_REGISTERGUOTONGFAILURE);
                    }
                }
                //#end
            }else{
                String result = GuotongManager.getInstance().sentRegisterTerminal(terminal,"A");
                if(!result.equals("0")){
                    throw new EduException(ErrorCode.ERR_REGISTERGUOTONGFAILURE);
                    //不影响注册，事后线程轮询
                    //terminal.RegisterFlag = SysDict.TERMINAL_REGISTERFLAG_NO; 
                }
            }
        }else{
            if(ApplicationConfig.getInstance().isNewGuotongApi()){
                //同步柜体信息
                GuotongManager.getInstance().sentRegisterTerminal(terminal,"U");
                //#start 同步格口信息
                if(StringUtils.isNotEmpty(boxInfo)){
                    GuotongManager.getInstance().sentBoxInfo(terminal,boxInfo,"U");//A-删除格口后重新添加
                }
                //#end
            }
        }
    }
}
