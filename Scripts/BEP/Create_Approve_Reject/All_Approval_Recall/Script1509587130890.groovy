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

'The first 3 steps whcih are disabled are addded to test the test case indepently. By Default they should be disabled'
CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.OPS, GlobalVariable.pwd)

CustomKeywords.'com.da.commonutilities.search'()

WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)

while (GlobalVariable.WOSTATUS.contains('PENDING') && (GlobalVariable.AssignmentList.size() > 0)) {
    /*   for (int j = 0; j < GlobalVariable.AssignmentList.size(); j++) {*/
    GlobalVariable.Status = GlobalVariable.AssignmentList[0].text

    println(GlobalVariable.Status)

    if (GlobalVariable.Status == 'Pending-RDApproval') {
        if (GlobalVariable.RDRecall == false) {
            if (GlobalVariable.RouteToRL == true) {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.RD)

                CustomKeywords.'com.da.commonutilities.search'()

                CustomKeywords.'com.da.BEP.RDapprove'()
            } else {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.RD)

                CustomKeywords.'com.da.commonutilities.search'()

                CustomKeywords.'com.da.BEP.noToRL'()
            }
        } else {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.PL)

            CustomKeywords.'com.da.commonutilities.search'()

            CustomKeywords.'com.da.BEP.recall'()

            GlobalVariable.RDRecall = false

            print(GlobalVariable.RDRecall)
        }
    } else if (GlobalVariable.Status == 'Pending-RLApproval') {
        if (GlobalVariable.RLRecall == false) {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.RL)

            CustomKeywords.'com.da.commonutilities.search'()

            CustomKeywords.'com.da.BEP.RLapprove'()
        } else {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.PL)

            CustomKeywords.'com.da.commonutilities.search'()

            CustomKeywords.'com.da.BEP.recall'()

            GlobalVariable.RLRecall = false

            print(GlobalVariable.RLRecall)
        }
    } else if (GlobalVariable.Status == 'Pending-Update') {
        CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.PL)

        CustomKeywords.'com.da.commonutilities.search'()

        CustomKeywords.'com.da.BEP.UpdateAfterRecall'()
    } else if (GlobalVariable.Status == 'Pending-Meeting') {
        CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.RL)

        CustomKeywords.'com.da.commonutilities.search'()

        CustomKeywords.'com.da.BEP.RLapprove'()
    } else if (GlobalVariable.Status == 'Pending-EmailCustomer') {
        if (GlobalVariable.SendEmail == true) {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.PL)

            CustomKeywords.'com.da.commonutilities.search'()

            CustomKeywords.'com.da.BEP.EmailCustomer'()
        } else {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.PL)

            CustomKeywords.'com.da.commonutilities.search'()

            CustomKeywords.'com.da.BEP.SkipEmail'()
        }
    } else {
        CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.OPS)

        CustomKeywords.'com.da.commonutilities.search'()

        CustomKeywords.'com.da.BEP.GSOpsReview'()
    }
    
    /* }for loop end braces */
    CustomKeywords.'com.da.commonutilities.searchStatus'()
}

