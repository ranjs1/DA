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
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('BEP/Create_Approve_Reject/Create_BEP_NoOutput'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.RD)

CustomKeywords.'com.da.commonutilities.search'()

CustomKeywords.'com.da.BEP.RDapprove'()

CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RL, GlobalVariable.pwd)

CustomKeywords.'com.da.commonutilities.search'()

CustomKeywords.'com.da.BEP.RLRequestMeeting'()

CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.CLINIC, GlobalVariable.pwd)

CustomKeywords.'com.da.commonutilities.search'()

CustomKeywords.'com.da.BEP.ScheduleMeeting'()

CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RL, GlobalVariable.pwd)

CustomKeywords.'com.da.commonutilities.search'()

GlobalVariable.Meeting = true

CustomKeywords.'com.da.BEP.RLapprove'()

not_run: WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 0)

not_run: WebUI.click(findTestObject('Generic/CLOSE_WORK_OBJECT'))

not_run: CustomKeywords.'com.da.commonutilities.search'()

GlobalVariable.Meeting = false

CustomKeywords.'com.da.BEP.RequestCancelMeeting'()

CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.CLINIC, GlobalVariable.pwd)

CustomKeywords.'com.da.commonutilities.search'()

CustomKeywords.'com.da.BEP.Cancelmeeting'()

CustomKeywords.'com.da.commonutilities.searchStatus'()

GlobalVariable.Meeting = false

WebUI.callTestCase(findTestCase('BEP/Create_Approve_Reject/All_Approval_Reject'), [:], FailureHandling.STOP_ON_FAILURE)

