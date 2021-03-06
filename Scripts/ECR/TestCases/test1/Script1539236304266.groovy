import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'com.da.commonutilities.setprop'(Path)

CustomKeywords.'com.da.commonutilities.login'(OPS)

CustomKeywords.'com.da.commonutilities.search'()

WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)

not_run: CustomKeywords.'com.da.ECR.CreateECR'(FileLocation, ORG, ACC, PL, RD, RL, PLTL, DataDrive, TestCaseno, '\\Data Files\\ECR\\TestDataECR.xlsx',
	'')

CustomKeywords.'com.da.commonutilities.searchStatus'()

while (GlobalVariable.WOSTATUS.contains('PENDING') && (GlobalVariable.AssignmentList.size() > 0)) {
	/*	for (int j = 0; j < GlobalVariable.AssignmentList.size(); j++) {*/
	GlobalVariable.Status = GlobalVariable.AssignmentList[0].text

	println(GlobalVariable.Status)

	/*
		CustomKeywords.'com.da.commonutilities.searchStatus'()
		
		int k=0
		while(GlobalVariable.AssignmentList[k].text!=GlobalVariable.Status)
		k++
		GlobalVariable.Status=GlobalVariable.AssignmentList[k].text*/
	if (GlobalVariable.Status == 'Pending-RDApproval') {
		if (GlobalVariable.RDReject == false) {
			CustomKeywords.'com.da.commonutilities.login'(RD)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.approve_reject_review'('Approve')

			GlobalVariable.RouteToRL = false
		} else {
			CustomKeywords.'com.da.commonutilities.login'(RD)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.approve_reject_review'('Approve')

			GlobalVariable.RDReject = false
		}
	} else if (GlobalVariable.Status == 'Pending-RLApproval') {
		if (GlobalVariable.RLReject == false) {
			CustomKeywords.'com.da.commonutilities.login'(RL)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.approve_reject_review'('Approve')
		} else {
			CustomKeywords.'com.da.commonutilities.login'(RL)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.reject'()

			GlobalVariable.RLReject = false
		}
	} else if (GlobalVariable.Status == 'Pending-RDDApproval') {
		if (GlobalVariable.RDD_Reject == false) {
			CustomKeywords.'com.da.commonutilities.login'(RDD)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.approve_reject_review'('Approve')
		} else {
			CustomKeywords.'com.da.commonutilities.login'(RDD)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.reject'()

			GlobalVariable.RDD_Reject = false
		}
	} else if (GlobalVariable.Status == 'Pending-OPSApproval') {
		if (GlobalVariable.OpsReject == false) {
			CustomKeywords.'com.da.commonutilities.login'(OPS)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.approve_reject_review'()
		} else {
			CustomKeywords.'com.da.commonutilities.login'(OPS)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.gsopsReject'()

			GlobalVariable.OpsReject = false
		}
	} else if (GlobalVariable.Status == 'Pending-ExpSvcApproval') {
		if (GlobalVariable.EXP_SVC_Reject == false) {
			CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.EXP_SVC)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.approve_reject_review'('Approve')
		} else {
			CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.EXP_SVC)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.reject'()

			GlobalVariable.EXP_SVC_Reject = false
		}
	} else if (GlobalVariable.Status == 'Pending-SpApprApproval') {
		if (GlobalVariable.EXP_SVC_Reject == false) {
			CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.EXP_SVC)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.approve_reject'('Approve')
		} else {
			CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.EXP_SVC)

			CustomKeywords.'com.da.commonutilities.search'()

			CustomKeywords.'com.da.ECR.reject'()

			GlobalVariable.EXP_SVC_Reject = false
		}
	} else if (GlobalVariable.Status == 'Pending-DocUpdate') {
		CustomKeywords.'com.da.commonutilities.login'(OPS)

		CustomKeywords.'com.da.commonutilities.search'()

		CustomKeywords.'com.da.ECR.docUpdate'()
	} else if (GlobalVariable.Status == 'Pending-Contract') {
		CustomKeywords.'com.da.commonutilities.login'(PL)

		CustomKeywords.'com.da.commonutilities.search'()

		CustomKeywords.'com.da.ECR.ContractDisposition'(ContractOption, DispositionReason)

		not_run: CustomKeywords.'com.da.ECR.readWO'(TestCaseno, TestDataFile)

		GlobalVariable.WOSTATUS == 'Resolved-won'
	} else if (GlobalVariable.Status == 'Pending-MeetingSchedule') {
		CustomKeywords.'com.da.commonutilities.login'(CLINIC)

		CustomKeywords.'com.da.commonutilities.search'()

		CustomKeywords.'com.da.ECR.scheduleMeeting'()
	} else if (GlobalVariable.Status == 'Pending-RDDReview') {
		CustomKeywords.'com.da.commonutilities.login'(RDD)

		CustomKeywords.'com.da.commonutilities.search'()

		CustomKeywords.'com.da.ECR.docUpdate'()
	} else if (GlobalVariable.Status == 'Pending-OPSReview') {
		CustomKeywords.'com.da.commonutilities.login'(OPS)

		CustomKeywords.'com.da.commonutilities.search'()

		CustomKeywords.'com.da.ECR.gsopsReview'()
	} else if (GlobalVariable.Status == 'Pending-ExpSvcReview') {
		CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.EXP_SVC)

		CustomKeywords.'com.da.commonutilities.search'()

		CustomKeywords.'com.da.ECR.EXPReview_RDDReview_SPReview'()
	} else if (GlobalVariable.Status == 'Pending-SpRevReview') {
		CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.EXP_SVC)

		CustomKeywords.'com.da.commonutilities.search'()

		CustomKeywords.'com.da.ECR.EXPReview_RDDReview_SPReview'()
	} else {
		CustomKeywords.'com.da.commonutilities.login'(RD)

		CustomKeywords.'com.da.commonutilities.search'()

		CustomKeywords.'com.da.ECR.pendingClinic'(ClinicOutcome)
	}
	
	/* }for loop end braces */
	CustomKeywords.'com.da.commonutilities.searchStatus'()

	if (GlobalVariable.WOSTATUS.contains('RESOLVED')) {
		CustomKeywords.'com.da.ECR.readWO'(TestCaseno, TestDataFile)
	}
}

