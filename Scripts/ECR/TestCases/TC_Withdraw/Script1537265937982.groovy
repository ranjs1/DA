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

not_run: WebUI.callTestCase(findTestCase('ECR/Create_Approve_Reject/ECR_Create'), [('RD') : 'ellia@pegasystems.com', ('RL') : 'panee@pegasystems.com'
        , ('PL') : 'hendj@pegasystems.com', ('ORG') : 'BOFA', ('ACC') : 'BOFA', ('ENG') : '', ('PLTL') : 'higgr@pegasystems.com'
        , ('TestDataFile') : '\\Data Files\\ECR\\TestDataECR.xlsx', ('TestCaseno') : '', ('FileLocation') : '\\Data Files\\ECR\\ECR_LITE_CASETYPE_YES.xlsm'
        , ('RDD') : 'lachd@pegasystems.com', ('CLINIC') : 'diazz@pegasystems.com', ('OPS') : 'pogoa@pegasystems.com', ('EXP') : GlobalVariable.EXP_SVC
        , ('DataDrive') : false], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.OPS)

not_run: GlobalVariable.WOID = 'ECR-12Sep2018-6'

CustomKeywords.'com.da.commonutilities.search'()

CustomKeywords.'com.da.BEP.withdraw'()

