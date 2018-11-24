package com.dcdzsoft.client.pda;

import java.util.Date;

/**
 * 
 * <p>Title: 智能自助包裹柜系统</p>
 *
 * <p>Description:PDA签到信息 </p>
 *
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * <p>Company: 杭州东城电子有限公司</p>
 *
 * @author zhengxy
 * @version 1.0
 */
public class PDASignInfo {
    public final static long PDA_OFFLINE_TIMEOUT   = (10*60*1000);//PDA连接超时时间
    public final static long FREE_BOX_TIMEOUT      = (10*60*1000);//10分钟  柜体上传的可用箱超时时间
    private String deviceID;//PDA唯一识别号
    private String pdaNo;//PDA编号
    private String status;//PDA 状态0-正常  5-注销
    
    //与PDA配对的柜体信息
    private String terminalNo;//PDA配对的柜体编号，为空表示未配对
    private String terminalName;
    private String location;
    private String freeBoxList;
    private String authToken = "";//PDA获取可用箱的令牌
    
    //
    private long signTimestamp;//PDA签到的时间
    private long uploadTimestamp;//柜体上传可用箱列表的时间
    private long updateTimestamp;//PDA获取可用箱列表的时间
    public String getDeviceID() {
        return deviceID;
    }
    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
    public String getPdaNo() {
        return pdaNo;
    }
    public void setPdaNo(String pdaNo) {
        this.pdaNo = pdaNo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTerminalNo() {
        return terminalNo;
    }
    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }
    public String getTerminalName() {
        return terminalName;
    }
    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getFreeBoxList() {
        return freeBoxList;
    }
    public void setFreeBoxList(String freeBoxList) {
        this.freeBoxList = freeBoxList;
    }
    public long getSignTimestamp() {
        return signTimestamp;
    }
    public void setSignTimestamp(long signTimestamp) {
        this.signTimestamp = signTimestamp;
    }
    public long getUploadTimestamp() {
        return uploadTimestamp;
    }
    public void setUploadTimestamp(long uploadTimestamp) {
        this.uploadTimestamp = uploadTimestamp;
    }
    public long getUpdateTimestamp() {
        return updateTimestamp;
    }
    public void setUpdateTimestamp(long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    @Override
    public String toString() {
        return "PDASignInfo [deviceID=" + deviceID + ", pdaNo=" + pdaNo + ", status=" + status + ", terminalNo="
                + terminalNo + ", terminalName=" + terminalName + ", location=" + location + ", freeBoxList="
                + freeBoxList + ", authToken=" + authToken + ", signTimestamp=" + signTimestamp + ", uploadTimestamp="
                + uploadTimestamp + ", updateTimestamp=" + updateTimestamp + "]";
    }
    
}
