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
import com.da.commonutilities as CUTILS

CustomKeywords.'com.da.commonutilities.login'(EL)

CustomKeywords.'com.da.commonutilities.search'()

WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)

CustomKeywords.'com.da.commonutilities.searchStatus'()

/*GlobalVariable.WOSTATUS = 'Pending-EL'
println GlobalVariable.WOSTATUS

GlobalVariable.WOID = 'ESU-08Aug2018-10'

GlobalVariable.Status = 'Pending-EL'
println GlobalVariable.Status */

while (GlobalVariable.WOSTATUS.contains('PENDING') && (GlobalVariable.AssignmentList.size() > 0)) {
	GlobalVariable.Status = GlobalVariable.AssignmentList[0].text

	println(GlobalVariable.Status)

    if (GlobalVariable.Status == 'Pending-EL') {
        CustomKeywords.'com.da.commonutilities.login'(EL)

        CustomKeywords.'com.da.commonutilities.search'()

        CustomKeywords.'com.da.ESU.approve'()
		
    } else if (GlobalVariable.Status == 'Pending-PL') {
        CustomKeywords.'com.da.commonutilities.login'(PL)

        CustomKeywords.'com.da.commonutilities.search'()

        CustomKeywords.'com.da.ESU.approve'()
    }
    
    CustomKeywords.'com.da.commonutilities.searchStatus'()

    'The first 3 steps whcih are disabled are addded to test the test case indepently. By Default they should be disabled'
}

