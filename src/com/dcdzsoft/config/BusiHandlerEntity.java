package com.dcdzsoft.config;

import java.util.*;

import com.dcdzsoft.util.*;

/**
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company:  杭州东城电子有限公司</p>
 *
 * @author wangzl
 * @version 1.0
 */
public class BusiHandlerEntity
{
    private String cmd = "";
    private String returntype = "";

    private String paramClass = "";
    private String paramCountClass = "";
    private String adapterClass = "";
    private String myFuncMenuID = "";
    private String sucessMsg = "";
    private String errorMsg = "";

    private String paramRepClass = "";
    private String adapterRepClass = "";
    private String reportFile = "";
    private String attachFile = "";

    private HashMap colMaps = null;

    public BusiHandlerEntity()
    {

    }

    public void addColMap(String value)
    {
        if(StringUtils.isEmpty(value)) return;
        if(colMaps == null) colMaps = new HashMap(10);

        String[] fields = value.split(":");
        if(fields.length > 0)
            colMaps.put(fields[0].trim(),fields[1].trim());
    }
    public HashMap getColMap()
    {
        return colMaps;
    }
    public String getAdapterClass()
    {
        return adapterClass;
    }

    public String getErrorMsg()
    {
        if (StringUtils.isEmpty(errorMsg))
            return BusinessConfig.getInstance().getBusiErrMsg();
        else
            return errorMsg;
    }

    public String getMyFuncMenuID()
    {
        return myFuncMenuID;
    }

    public String getParamClass()
    {
        return paramClass;
    }

    public String getParamCountClass()
    {
        return paramCountClass;
    }

    public String getSucessMsg()
    {
        return sucessMsg;
    }

    public String getCmd()
    {
        return cmd;
    }

    public String getReturntype()
    {
        return returntype;
    }

    public String getParamRepClass()
    {
        return paramRepClass;
    }

    public HashMap getColMaps()
    {
        return colMaps;
    }

    public String getAdapterRepClass()
    {
        return adapterRepClass;
    }

    public String getReportFile()
    {
        return reportFile;
    }

    public String getAttachFile()
    {
        return attachFile;
    }

    public void setSucessMsg(String sucessMsg)
    {
        this.sucessMsg = sucessMsg;
    }

    public void setParamCountClass(String paramCountClass)
    {
        this.paramCountClass = paramCountClass;
    }

    public void setParamClass(String paramClass)
    {
        this.paramClass = paramClass;
    }

    public void setMyFuncMenuID(String myFuncMenuID)
    {
        this.myFuncMenuID = myFuncMenuID;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public void setAdapterClass(String adapterClass)
    {
        this.adapterClass = adapterClass;
    }

    public void setReturntype(String returntype)
    {
        this.returntype = returntype;
    }

    public void setCmd(String cmd)
    {
        this.cmd = cmd;
    }

    public void setAdapterRepClass(String adapterRepClass)
    {
        this.adapterRepClass = adapterRepClass;
    }

    public void setParamRepClass(String paramRepClass)
    {
        this.paramRepClass = paramRepClass;
    }

    public void setReportFile(String reportFile)
    {
        this.reportFile = reportFile;
    }

    public void setAttachFile(String attachFile)
    {
        this.attachFile = attachFile;
    }
}
