package com.dcdzsoft.dao.factory;

import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.common.*;

import com.dcdzsoft.sda.db.DataSourceUtils;

// Abstract class DAO Factory
public abstract class DAOFactory
{
	// List of DAO types supported by the factory
	public static DAOFactory getDAOFactory(int whichFactory)
	{
		switch (whichFactory)
		{
			case DataSourceUtils.DB_TYPE_ORACLE:
				return new OracleDAOFactory();
			case DataSourceUtils.DB_TYPE_MYSQL:
				return new MysqlDAOFactory();
			default:
				return null;
		}
	}

	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	public abstract CommonDAO getCommonDAO();

	public abstract UtilDAO getUtilDAO();

	public abstract CMCustomerDAO getCMCustomerDAO();

	public abstract CMCustomerAddressDAO getCMCustomerAddressDAO();

	public abstract CMCustomerBoxRightDAO getCMCustomerBoxRightDAO();

	public abstract CMCustomerCardDAO getCMCustomerCardDAO();

	public abstract CMCustomerTerminalRightDAO getCMCustomerTerminalRightDAO();

	public abstract CMCustomerToGroupDAO getCMCustomerToGroupDAO();

	public abstract CMDeliverServiceUserDAO getCMDeliverServiceUserDAO();

	public abstract CMEleAccountDAO getCMEleAccountDAO();

	public abstract CMGroupDAO getCMGroupDAO();

	public abstract MBBindMobileWaterDAO getMBBindMobileWaterDAO();

	public abstract MBBoxStatusLogDAO getMBBoxStatusLogDAO();

	public abstract MBDeliveryReportDAO getMBDeliveryReportDAO();

	public abstract MBDepartmentDAO getMBDepartmentDAO();

	public abstract MBLockUserTimeDAO getMBLockUserTimeDAO();

	public abstract MBMobileBlackListDAO getMBMobileBlackListDAO();

	public abstract MBMsgSendWaterDAO getMBMsgSendWaterDAO();

	public abstract MBOpenBoxWaterDAO getMBOpenBoxWaterDAO();

	public abstract MBPwdShortMsgDAO getMBPwdShortMsgDAO();

	public abstract MBReminderWaterDAO getMBReminderWaterDAO();

	public abstract MBSignInfoDAO getMBSignInfoDAO();

	public abstract MBSmsStatDAO getMBSmsStatDAO();

	public abstract MBTerminalAlertLogDAO getMBTerminalAlertLogDAO();

	public abstract MBTerminalStatusLogDAO getMBTerminalStatusLogDAO();

	public abstract MBTerminalUpgradeDAO getMBTerminalUpgradeDAO();

	public abstract MBWrongPwdWaterDAO getMBWrongPwdWaterDAO();

	public abstract OPFunctionDAO getOPFunctionDAO();

	public abstract OPMenuDAO getOPMenuDAO();

	public abstract OPOperAllLimitDAO getOPOperAllLimitDAO();

	public abstract OPOperOnlineDAO getOPOperOnlineDAO();

	public abstract OPOperRoleDAO getOPOperRoleDAO();

	public abstract OPOperSpeRightDAO getOPOperSpeRightDAO();

	public abstract OPOperToMenuDAO getOPOperToMenuDAO();

	public abstract OPOperatorDAO getOPOperatorDAO();

	public abstract OPOperatorLogDAO getOPOperatorLogDAO();

	public abstract OPRoleDAO getOPRoleDAO();

	public abstract OPRoleAllLimitDAO getOPRoleAllLimitDAO();

	public abstract OPRoleSpeRightDAO getOPRoleSpeRightDAO();

	public abstract OPRoleToMenuDAO getOPRoleToMenuDAO();

	public abstract OPSpecialPrivDAO getOPSpecialPrivDAO();

	public abstract PAControlParamDAO getPAControlParamDAO();

	public abstract PADictionaryDAO getPADictionaryDAO();

	public abstract PAMsgGatewayDAO getPAMsgGatewayDAO();

	public abstract PAMsgTemplateDAO getPAMsgTemplateDAO();

	public abstract PASequenceDAO getPASequenceDAO();

	public abstract PATerminalCtrlParamDAO getPATerminalCtrlParamDAO();

	public abstract PMCompanyDAO getPMCompanyDAO();

	public abstract PMCompanyConfigDAO getPMCompanyConfigDAO();

	public abstract PMCorpBoxRightDAO getPMCorpBoxRightDAO();

	public abstract PMCorpTerminalRightDAO getPMCorpTerminalRightDAO();

	public abstract PMLogisticsDAO getPMLogisticsDAO();

	public abstract PMManTerminalRightDAO getPMManTerminalRightDAO();

	public abstract PMPostmanDAO getPMPostmanDAO();

	public abstract PMPostmanBoxRightDAO getPMPostmanBoxRightDAO();

	public abstract PTDeliverHistoryDAO getPTDeliverHistoryDAO();

	public abstract PTFeedbackFailDAO getPTFeedbackFailDAO();

	public abstract PTInBoxPackageDAO getPTInBoxPackageDAO();

	public abstract PTReadyPackageDAO getPTReadyPackageDAO();

	public abstract PYConsumedCardDAO getPYConsumedCardDAO();

	public abstract PYFeedbackFailDAO getPYFeedbackFailDAO();

	public abstract PYPackagePayDAO getPYPackagePayDAO();

	public abstract PYSMSAccountDAO getPYSMSAccountDAO();

	public abstract PYSMSBillWaterDAO getPYSMSBillWaterDAO();

	public abstract PYSMSServicesDAO getPYSMSServicesDAO();

	public abstract PYSupplyRegisterDAO getPYSupplyRegisterDAO();

	public abstract PYTransactionWaterDAO getPYTransactionWaterDAO();

	public abstract RMAppealLogDAO getRMAppealLogDAO();

	public abstract RMOpenBoxLogDAO getRMOpenBoxLogDAO();

	public abstract SCCheckAccLogDAO getSCCheckAccLogDAO();

	public abstract SCCheckAccResultDAO getSCCheckAccResultDAO();

	public abstract SCFtpLogDAO getSCFtpLogDAO();

	public abstract SCPushDataQueueDAO getSCPushDataQueueDAO();

	public abstract SCSyncFailWaterDAO getSCSyncFailWaterDAO();

	public abstract SCTimestampDAO getSCTimestampDAO();

	public abstract SCUploadDataQueueDAO getSCUploadDataQueueDAO();

	public abstract SMAdVideoDAO getSMAdVideoDAO();

	public abstract SMSystemInfoDAO getSMSystemInfoDAO();

	public abstract SMTerminalSoftDAO getSMTerminalSoftDAO();

	public abstract SMUpgradePackDAO getSMUpgradePackDAO();

	public abstract SMUpgradeWaterDAO getSMUpgradeWaterDAO();

	public abstract TBBoxDAO getTBBoxDAO();

	public abstract TBBoxTypeDAO getTBBoxTypeDAO();

	public abstract TBDeskDAO getTBDeskDAO();

	public abstract TBLedParamDAO getTBLedParamDAO();

	public abstract TBPDADAO getTBPDADAO();

	public abstract TBServerBoxDAO getTBServerBoxDAO();

	public abstract TBServerBoxTypeDAO getTBServerBoxTypeDAO();

	public abstract TBServerDeskDAO getTBServerDeskDAO();

	public abstract TBTerminalDAO getTBTerminalDAO();

	public abstract TBTerminalChargeDAO getTBTerminalChargeDAO();

}