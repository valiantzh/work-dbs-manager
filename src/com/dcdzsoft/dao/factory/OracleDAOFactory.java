package com.dcdzsoft.dao.factory;

// Oracle concrete DAO Factory implementation
import com.dcdzsoft.dao.*;
import com.dcdzsoft.dao.impl.*;

import com.dcdzsoft.dao.common.*;
import com.dcdzsoft.dao.common.impl.*;
import com.dcdzsoft.dao.common.impl.oracle.*;

public class OracleDAOFactory extends DAOFactory
{
	public CommonDAO getCommonDAO()
	{
		return new CommonDAOImpl();
	}

	public UtilDAO getUtilDAO()
	{
		return new OracleUtilDAO();
	}

	public CMCustomerDAO getCMCustomerDAO()
	{
		return new CMCustomerDAOImpl();
	}

	public CMCustomerAddressDAO getCMCustomerAddressDAO()
	{
		return new CMCustomerAddressDAOImpl();
	}

	public CMCustomerBoxRightDAO getCMCustomerBoxRightDAO()
	{
		return new CMCustomerBoxRightDAOImpl();
	}

	public CMCustomerCardDAO getCMCustomerCardDAO()
	{
		return new CMCustomerCardDAOImpl();
	}

	public CMCustomerTerminalRightDAO getCMCustomerTerminalRightDAO()
	{
		return new CMCustomerTerminalRightDAOImpl();
	}

	public CMCustomerToGroupDAO getCMCustomerToGroupDAO()
	{
		return new CMCustomerToGroupDAOImpl();
	}

	public CMDeliverServiceUserDAO getCMDeliverServiceUserDAO()
	{
		return new CMDeliverServiceUserDAOImpl();
	}

	public CMEleAccountDAO getCMEleAccountDAO()
	{
		return new CMEleAccountDAOImpl();
	}

	public CMGroupDAO getCMGroupDAO()
	{
		return new CMGroupDAOImpl();
	}

	public MBBindMobileWaterDAO getMBBindMobileWaterDAO()
	{
		return new MBBindMobileWaterDAOImpl();
	}

	public MBBoxStatusLogDAO getMBBoxStatusLogDAO()
	{
		return new MBBoxStatusLogDAOImpl();
	}

	public MBDeliveryReportDAO getMBDeliveryReportDAO()
	{
		return new MBDeliveryReportDAOImpl();
	}

	public MBDepartmentDAO getMBDepartmentDAO()
	{
		return new MBDepartmentDAOImpl();
	}

	public MBLockUserTimeDAO getMBLockUserTimeDAO()
	{
		return new MBLockUserTimeDAOImpl();
	}

	public MBMobileBlackListDAO getMBMobileBlackListDAO()
	{
		return new MBMobileBlackListDAOImpl();
	}

	public MBMsgSendWaterDAO getMBMsgSendWaterDAO()
	{
		return new MBMsgSendWaterDAOImpl();
	}

	public MBOpenBoxWaterDAO getMBOpenBoxWaterDAO()
	{
		return new MBOpenBoxWaterDAOImpl();
	}

	public MBPwdShortMsgDAO getMBPwdShortMsgDAO()
	{
		return new MBPwdShortMsgDAOImpl();
	}

	public MBReminderWaterDAO getMBReminderWaterDAO()
	{
		return new MBReminderWaterDAOImpl();
	}

	public MBSignInfoDAO getMBSignInfoDAO()
	{
		return new MBSignInfoDAOImpl();
	}

	public MBSmsStatDAO getMBSmsStatDAO()
	{
		return new MBSmsStatDAOImpl();
	}

	public MBTerminalAlertLogDAO getMBTerminalAlertLogDAO()
	{
		return new MBTerminalAlertLogDAOImpl();
	}

	public MBTerminalStatusLogDAO getMBTerminalStatusLogDAO()
	{
		return new MBTerminalStatusLogDAOImpl();
	}

	public MBTerminalUpgradeDAO getMBTerminalUpgradeDAO()
	{
		return new MBTerminalUpgradeDAOImpl();
	}

	public MBWrongPwdWaterDAO getMBWrongPwdWaterDAO()
	{
		return new MBWrongPwdWaterDAOImpl();
	}

	public OPFunctionDAO getOPFunctionDAO()
	{
		return new OPFunctionDAOImpl();
	}

	public OPMenuDAO getOPMenuDAO()
	{
		return new OPMenuDAOImpl();
	}

	public OPOperAllLimitDAO getOPOperAllLimitDAO()
	{
		return new OPOperAllLimitDAOImpl();
	}

	public OPOperOnlineDAO getOPOperOnlineDAO()
	{
		return new OPOperOnlineDAOImpl();
	}

	public OPOperRoleDAO getOPOperRoleDAO()
	{
		return new OPOperRoleDAOImpl();
	}

	public OPOperSpeRightDAO getOPOperSpeRightDAO()
	{
		return new OPOperSpeRightDAOImpl();
	}

	public OPOperToMenuDAO getOPOperToMenuDAO()
	{
		return new OPOperToMenuDAOImpl();
	}

	public OPOperatorDAO getOPOperatorDAO()
	{
		return new OPOperatorDAOImpl();
	}

	public OPOperatorLogDAO getOPOperatorLogDAO()
	{
		return new OPOperatorLogDAOImpl();
	}

	public OPRoleDAO getOPRoleDAO()
	{
		return new OPRoleDAOImpl();
	}

	public OPRoleAllLimitDAO getOPRoleAllLimitDAO()
	{
		return new OPRoleAllLimitDAOImpl();
	}

	public OPRoleSpeRightDAO getOPRoleSpeRightDAO()
	{
		return new OPRoleSpeRightDAOImpl();
	}

	public OPRoleToMenuDAO getOPRoleToMenuDAO()
	{
		return new OPRoleToMenuDAOImpl();
	}

	public OPSpecialPrivDAO getOPSpecialPrivDAO()
	{
		return new OPSpecialPrivDAOImpl();
	}

	public PAControlParamDAO getPAControlParamDAO()
	{
		return new PAControlParamDAOImpl();
	}

	public PADictionaryDAO getPADictionaryDAO()
	{
		return new PADictionaryDAOImpl();
	}

	public PAMsgGatewayDAO getPAMsgGatewayDAO()
	{
		return new PAMsgGatewayDAOImpl();
	}

	public PAMsgTemplateDAO getPAMsgTemplateDAO()
	{
		return new PAMsgTemplateDAOImpl();
	}

	public PASequenceDAO getPASequenceDAO()
	{
		return new PASequenceDAOImpl();
	}

	public PATerminalCtrlParamDAO getPATerminalCtrlParamDAO()
	{
		return new PATerminalCtrlParamDAOImpl();
	}

	public PMCompanyDAO getPMCompanyDAO()
	{
		return new PMCompanyDAOImpl();
	}

	public PMCompanyConfigDAO getPMCompanyConfigDAO()
	{
		return new PMCompanyConfigDAOImpl();
	}

	public PMCorpBoxRightDAO getPMCorpBoxRightDAO()
	{
		return new PMCorpBoxRightDAOImpl();
	}

	public PMCorpTerminalRightDAO getPMCorpTerminalRightDAO()
	{
		return new PMCorpTerminalRightDAOImpl();
	}

	public PMLogisticsDAO getPMLogisticsDAO()
	{
		return new PMLogisticsDAOImpl();
	}

	public PMManTerminalRightDAO getPMManTerminalRightDAO()
	{
		return new PMManTerminalRightDAOImpl();
	}

	public PMPostmanDAO getPMPostmanDAO()
	{
		return new PMPostmanDAOImpl();
	}

	public PMPostmanBoxRightDAO getPMPostmanBoxRightDAO()
	{
		return new PMPostmanBoxRightDAOImpl();
	}

	public PTDeliverHistoryDAO getPTDeliverHistoryDAO()
	{
		return new PTDeliverHistoryDAOImpl();
	}

	public PTFeedbackFailDAO getPTFeedbackFailDAO()
	{
		return new PTFeedbackFailDAOImpl();
	}

	public PTInBoxPackageDAO getPTInBoxPackageDAO()
	{
		return new PTInBoxPackageDAOImpl();
	}

	public PTReadyPackageDAO getPTReadyPackageDAO()
	{
		return new PTReadyPackageDAOImpl();
	}

	public PYConsumedCardDAO getPYConsumedCardDAO()
	{
		return new PYConsumedCardDAOImpl();
	}

	public PYFeedbackFailDAO getPYFeedbackFailDAO()
	{
		return new PYFeedbackFailDAOImpl();
	}

	public PYPackagePayDAO getPYPackagePayDAO()
	{
		return new PYPackagePayDAOImpl();
	}

	public PYSMSAccountDAO getPYSMSAccountDAO()
	{
		return new PYSMSAccountDAOImpl();
	}

	public PYSMSBillWaterDAO getPYSMSBillWaterDAO()
	{
		return new PYSMSBillWaterDAOImpl();
	}

	public PYSMSServicesDAO getPYSMSServicesDAO()
	{
		return new PYSMSServicesDAOImpl();
	}

	public PYSupplyRegisterDAO getPYSupplyRegisterDAO()
	{
		return new PYSupplyRegisterDAOImpl();
	}

	public PYTransactionWaterDAO getPYTransactionWaterDAO()
	{
		return new PYTransactionWaterDAOImpl();
	}

	public RMAppealLogDAO getRMAppealLogDAO()
	{
		return new RMAppealLogDAOImpl();
	}

	public RMOpenBoxLogDAO getRMOpenBoxLogDAO()
	{
		return new RMOpenBoxLogDAOImpl();
	}

	public SCCheckAccLogDAO getSCCheckAccLogDAO()
	{
		return new SCCheckAccLogDAOImpl();
	}

	public SCCheckAccResultDAO getSCCheckAccResultDAO()
	{
		return new SCCheckAccResultDAOImpl();
	}

	public SCFtpLogDAO getSCFtpLogDAO()
	{
		return new SCFtpLogDAOImpl();
	}

	public SCPushDataQueueDAO getSCPushDataQueueDAO()
	{
		return new SCPushDataQueueDAOImpl();
	}

	public SCSyncFailWaterDAO getSCSyncFailWaterDAO()
	{
		return new SCSyncFailWaterDAOImpl();
	}

	public SCTimestampDAO getSCTimestampDAO()
	{
		return new SCTimestampDAOImpl();
	}

	public SCUploadDataQueueDAO getSCUploadDataQueueDAO()
	{
		return new SCUploadDataQueueDAOImpl();
	}

	public SMAdVideoDAO getSMAdVideoDAO()
	{
		return new SMAdVideoDAOImpl();
	}

	public SMSystemInfoDAO getSMSystemInfoDAO()
	{
		return new SMSystemInfoDAOImpl();
	}

	public SMTerminalSoftDAO getSMTerminalSoftDAO()
	{
		return new SMTerminalSoftDAOImpl();
	}

	public SMUpgradePackDAO getSMUpgradePackDAO()
	{
		return new SMUpgradePackDAOImpl();
	}

	public SMUpgradeWaterDAO getSMUpgradeWaterDAO()
	{
		return new SMUpgradeWaterDAOImpl();
	}

	public TBBoxDAO getTBBoxDAO()
	{
		return new TBBoxDAOImpl();
	}

	public TBBoxTypeDAO getTBBoxTypeDAO()
	{
		return new TBBoxTypeDAOImpl();
	}

	public TBDeskDAO getTBDeskDAO()
	{
		return new TBDeskDAOImpl();
	}

	public TBLedParamDAO getTBLedParamDAO()
	{
		return new TBLedParamDAOImpl();
	}

	public TBPDADAO getTBPDADAO()
	{
		return new TBPDADAOImpl();
	}

	public TBServerBoxDAO getTBServerBoxDAO()
	{
		return new TBServerBoxDAOImpl();
	}

	public TBServerBoxTypeDAO getTBServerBoxTypeDAO()
	{
		return new TBServerBoxTypeDAOImpl();
	}

	public TBServerDeskDAO getTBServerDeskDAO()
	{
		return new TBServerDeskDAOImpl();
	}

	public TBTerminalDAO getTBTerminalDAO()
	{
		return new TBTerminalDAOImpl();
	}

	public TBTerminalChargeDAO getTBTerminalChargeDAO()
	{
		return new TBTerminalChargeDAOImpl();
	}

}