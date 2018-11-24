package com.dcdzsoft.config;

import java.util.*;

import org.apache.commons.configuration.*;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company:  杭州东城电子有限公司</p>
 *
 * @author wangzl
 * @version 1.0
 */
public class BusinessConfig
{
    private String busiErrMsg = "";
    private String noFuncMsg = "";
    private String sysErrMsg = "";
    private String treeleafIcon = "";

    //打印方式
    private String printWay = ""; //html pdf

    //导出到excel是否显示line
    private String displayLine = "true";

    private HashMap cmds = new HashMap(100);

    protected BusinessConfig()
    {
    }

    private static class SingletonHolder{
    	private static final BusinessConfig instance = new BusinessConfig();
    }
    
    /**
     * 静态工厂方法，返还此类的惟一实例
     * @return a Config instance
     */
    public static BusinessConfig getInstance()
    {
        return SingletonHolder.instance;
    }

    /**
     * 读取配置文件
     * @param fileName String
     * @throws ConfigurationException
     */
    public void load(String fileName)
        throws ConfigurationException
    {
        String globalPrefix = "global.";
        String printPrefix = "print.";

        XMLConfiguration config = new XMLConfiguration(fileName);
        config.setEncoding("utf-8"); //设置编码

        this.setBusiErrMsg(config.getString(globalPrefix + "busiErrMsg" + "[@value]"));
        this.setNoFuncMsg(config.getString(globalPrefix + "noFuncMsg" + "[@value]"));
        this.setSysErrMsg(config.getString(globalPrefix + "sysErrMsg" + "[@value]"));
        this.setTreeleafIcon(config.getString(globalPrefix + "treeleafIcon" + "[@value]"));

        this.setPrintWay(config.getString(printPrefix + "printWay" + "[@value]"));
        this.setDisplayLine(config.getString(printPrefix + "displayLine" + "[@value]"));

        List list = config.configurationsAt("handler.FuncMenuID");
        for(Iterator it = list.iterator(); it.hasNext();)
        {
            HierarchicalConfiguration sub = (HierarchicalConfiguration) it.next();
            //ConfigurationNode node = sub.getRootNode();

            String FuncMenuID = sub.getString("[@id]");

            List cmdList = sub.configurationsAt("cmd");
            for(Iterator itr = cmdList.iterator(); itr.hasNext();)
            {
                HierarchicalConfiguration subCmd = (HierarchicalConfiguration) itr.next();
                BusiHandlerEntity entity = new BusiHandlerEntity();
                String cmd = subCmd.getString("[@name]");

                entity.setCmd(cmd);
                entity.setReturntype(subCmd.getString("[@returntype]"));
                entity.setParamClass(subCmd.getString("paramClass"));
                entity.setParamCountClass(subCmd.getString("paramCountClass"));
                entity.setAdapterClass(subCmd.getString("adapterClass"));
                entity.setMyFuncMenuID(subCmd.getString("myFuncMenuID"));
                entity.setSucessMsg(subCmd.getString("sucessMsg"));
                entity.setErrorMsg(subCmd.getString("errorMsg"));

                entity.setParamRepClass(subCmd.getString("paramRepClass"));
                entity.setAdapterRepClass(subCmd.getString("adapterRepClass"));
                entity.setReportFile(subCmd.getString("reportFile"));
                entity.setAttachFile(subCmd.getString("attachFile"));

                List colmapList = subCmd.configurationsAt("colmap");
                for(Iterator itrr = colmapList.iterator(); itrr.hasNext();)
                {
                    HierarchicalConfiguration subColMap = (HierarchicalConfiguration) itrr.next();
                    entity.addColMap(subColMap.getString("map"));
                }

                String key = FuncMenuID + cmd;

                addHandler(key,entity);
            }
        }
    }

    public void addHandler(String key,BusiHandlerEntity entity)
    {
        cmds.put(key,entity);
    }

    public BusiHandlerEntity getHandler(String key)
    {
        Object obj = cmds.get(key);
        if(obj != null)
            return (BusiHandlerEntity)obj;

        return null;
    }

    public String getBusiErrMsg()
    {
        return busiErrMsg;
    }

    public String getNoFuncMsg()
    {
        return noFuncMsg;
    }

    public String getSysErrMsg()
    {
        return sysErrMsg;
    }

    public String getTreeleafIcon()
    {
        return treeleafIcon;
    }

    public String getDisplayLine()
    {
        return displayLine;
    }

    public String getPrintWay()
    {
        return printWay;
    }

    public void setNoFuncMsg(String noFuncMsg)
    {
        this.noFuncMsg = noFuncMsg;
    }

    public void setBusiErrMsg(String busiErrMsg)
    {
        this.busiErrMsg = busiErrMsg;
    }

    public void setSysErrMsg(String sysErrMsg)
    {
        this.sysErrMsg = sysErrMsg;
    }

    public void setTreeleafIcon(String treeleafIcon)
    {
        this.treeleafIcon = treeleafIcon;
    }

    public void setDisplayLine(String displayLine)
    {
        this.displayLine = displayLine;
    }

    public void setPrintWay(String printWay)
    {
        this.printWay = printWay;
    }

    public static void main(String[] args)
        throws Exception
    {
      

    }

}
