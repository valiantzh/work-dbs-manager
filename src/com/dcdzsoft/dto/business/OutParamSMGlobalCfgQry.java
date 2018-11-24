package com.dcdzsoft.dto.business;

/**
* 全局配置查询
*/

public class OutParamSMGlobalCfgQry implements java.io.Serializable
{
	public String locale = ""; //国际化
	public String sysRunModel = ""; //系统运行模式
	public String interfacePackage = ""; //接口包名
	public String workerCount = ""; //业务处理线程的数量
	public String logRawMsg = ""; //记录自己平台的消息日志
	public String logMbMsg = ""; //记录运营商消息的日志
	public String sendShortMsg = ""; //发送短消息接口
	public String gatewayUser = ""; //短消息用户
	public String gatewayPwd = ""; //短消息密码
	public String mbHost = ""; //运营商服务器IP
	public String mbPort = ""; //运营商服务器Port
	public String mbUri = ""; //运营商服务器URI
	public String ftpHost = ""; //运营商文件服务器IP
	public String ftpPort = ""; //运营商文件服务器Port
	public String ftpUser = ""; //运营商文件服务器用户
	public String ftpPasswd = ""; //运营商文件服务器密码
	public String sentToGuotong = ""; //反馈信息到国通

	public String getlocale()
	{
		return locale;
	}
	public void setlocale(String locale)
	{
		this.locale = locale;
	}

	public String getsysRunModel()
	{
		return sysRunModel;
	}
	public void setsysRunModel(String sysRunModel)
	{
		this.sysRunModel = sysRunModel;
	}

	public String getinterfacePackage()
	{
		return interfacePackage;
	}
	public void setinterfacePackage(String interfacePackage)
	{
		this.interfacePackage = interfacePackage;
	}

	public String getworkerCount()
	{
		return workerCount;
	}
	public void setworkerCount(String workerCount)
	{
		this.workerCount = workerCount;
	}

	public String getlogRawMsg()
	{
		return logRawMsg;
	}
	public void setlogRawMsg(String logRawMsg)
	{
		this.logRawMsg = logRawMsg;
	}

	public String getlogMbMsg()
	{
		return logMbMsg;
	}
	public void setlogMbMsg(String logMbMsg)
	{
		this.logMbMsg = logMbMsg;
	}

	public String getsendShortMsg()
	{
		return sendShortMsg;
	}
	public void setsendShortMsg(String sendShortMsg)
	{
		this.sendShortMsg = sendShortMsg;
	}

	public String getgatewayUser()
	{
		return gatewayUser;
	}
	public void setgatewayUser(String gatewayUser)
	{
		this.gatewayUser = gatewayUser;
	}

	public String getgatewayPwd()
	{
		return gatewayPwd;
	}
	public void setgatewayPwd(String gatewayPwd)
	{
		this.gatewayPwd = gatewayPwd;
	}

	public String getmbHost()
	{
		return mbHost;
	}
	public void setmbHost(String mbHost)
	{
		this.mbHost = mbHost;
	}

	public String getmbPort()
	{
		return mbPort;
	}
	public void setmbPort(String mbPort)
	{
		this.mbPort = mbPort;
	}

	public String getmbUri()
	{
		return mbUri;
	}
	public void setmbUri(String mbUri)
	{
		this.mbUri = mbUri;
	}

	public String getftpHost()
	{
		return ftpHost;
	}
	public void setftpHost(String ftpHost)
	{
		this.ftpHost = ftpHost;
	}

	public String getftpPort()
	{
		return ftpPort;
	}
	public void setftpPort(String ftpPort)
	{
		this.ftpPort = ftpPort;
	}

	public String getftpUser()
	{
		return ftpUser;
	}
	public void setftpUser(String ftpUser)
	{
		this.ftpUser = ftpUser;
	}

	public String getftpPasswd()
	{
		return ftpPasswd;
	}
	public void setftpPasswd(String ftpPasswd)
	{
		this.ftpPasswd = ftpPasswd;
	}

	public String getsentToGuotong()
	{
		return sentToGuotong;
	}
	public void setsentToGuotong(String sentToGuotong)
	{
		this.sentToGuotong = sentToGuotong;
	}

}