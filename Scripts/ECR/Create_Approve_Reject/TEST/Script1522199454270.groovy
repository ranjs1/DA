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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedCondition as ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait

CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.OPS)

CustomKeywords.'com.da.commonutilities.search'()

WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)

GlobalVariable.referencesize = true

println(GlobalVariable.referencesize)

CustomKeywords.'com.da.commonutilities.searchStatus'()

CustomKeywords.'com.da.commonutilities.AttachCaseTypeDoc'()

not_run: while (GlobalVariable.WOSTATUS.contains('PENDING')) {
    not_run: for (int j = 0; j < GlobalVariable.AssignmentList.size(); j++) {
        GlobalVariable.Status = GlobalVariable.AssignmentList[j].text

        println(GlobalVariable.Status)

        if (GlobalVariable.Status == 'Pending-RDApproval') {
            if (GlobalVariable.RDReject == false) {
                if (GlobalVariable.Meeting == true) {
                    CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RD, 
                        GlobalVariable.pwd)

                    CustomKeywords.'com.da.commonutilities.search'()

                    CustomKeywords.'com.da.commonutilities.routeToRL'()

                    print(GlobalVariable.Status)

                    CustomKeywords.'com.da.commonutilities.searchStatus'()
                } else {
                    CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RD, 
                        GlobalVariable.pwd)

                    CustomKeywords.'com.da.commonutilities.search'()

                    CustomKeywords.'com.da.commonutilities.approve'()

                    CustomKeywords.'com.da.commonutilities.searchStatus'()
                }
            } else {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RD, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                WebUI.maximizeWindow()

                CustomKeywords.'com.da.commonutilities.reject'()

                GlobalVariable.RDReject = false

                CustomKeywords.'com.da.commonutilities.searchStatus'()
            }
        } else if (GlobalVariable.Status == 'Pending-RLApproval') {
            if (GlobalVariable.RLReject == false) {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RL, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                WebUI.maximizeWindow()

                CustomKeywords.'com.da.commonutilities.approve'()

                CustomKeywords.'com.da.commonutilities.searchStatus'()
            } else {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RL, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                WebUI.maximizeWindow()

                CustomKeywords.'com.da.commonutilities.reject'()

                GlobalVariable.RLReject = false

                CustomKeywords.'com.da.commonutilities.searchStatus'()
            }
        } else if (GlobalVariable.Status == 'Pending-RDDApproval') {
            if (GlobalVariable.RDD_Reject == false) {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RDD, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                CustomKeywords.'com.da.commonutilities.approve'()

                CustomKeywords.'com.da.commonutilities.searchStatus'()
            } else {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RDD, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                CustomKeywords.'com.da.commonutilities.reject'()

                GlobalVariable.RDD_Reject = false

                CustomKeywords.'com.da.commonutilities.searchStatus'()

                println(GlobalVariable.Status)
            }
        } else if (GlobalVariable.Status == 'Pending-OPSApproval') {
            if (GlobalVariable.OpsReject == false) {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.OPS, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                CustomKeywords.'com.da.commonutilities.gsopsApprove'()

                CustomKeywords.'com.da.commonutilities.searchStatus'()
            } else {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.OPS, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                WebUI.maximizeWindow()

                CustomKeywords.'com.da.commonutilities.gsopsReject'()

                CustomKeywords.'com.da.commonutilities.searchStatus'()

                GlobalVariable.OpsReject = false
            }
        } else if (GlobalVariable.Status == 'Pending-ExpSvcApproval') {
            if (GlobalVariable.EXP_SVC_Reject == false) {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.EXP_SVC, 
                    GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                CustomKeywords.'com.da.commonutilities.approve'()

                CustomKeywords.'com.da.commonutilities.searchStatus'()
            } else {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.EXP_SVC, 
                    GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                CustomKeywords.'com.da.commonutilities.reject'()

                GlobalVariable.EXP_SVC_Reject = false

                CustomKeywords.'com.da.commonutilities.searchStatus'()
            }
        } else if (GlobalVariable.Status == 'Pending-DocUpdate') {
            if (GlobalVariable.Meeting == false) {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.OPS, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                WebUI.maximizeWindow()

                CustomKeywords.'com.da.commonutilities.docUpdate'()

                CustomKeywords.'com.da.commonutilities.searchStatus'()

                WebUI.delay(3)
            } else {
                CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.OPS, GlobalVariable.pwd)

                CustomKeywords.'com.da.commonutilities.search'()

                WebUI.maximizeWindow()

                CustomKeywords.'com.da.commonutilities.docUpdateMeeting'()

                CustomKeywords.'com.da.commonutilities.searchStatus'()
            }
        } else if (GlobalVariable.Status == 'Pending-Contract') {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.PL, GlobalVariable.pwd)

            CustomKeywords.'com.da.commonutilities.search'()

            WebUI.maximizeWindow()

            CustomKeywords.'com.da.commonutilities.ContractDisposition'()

            WebUI.waitForPageLoad(4)

            CustomKeywords.'com.da.commonutilities.searchStatus'()
        } else if (GlobalVariable.Status == 'Pending-MeetingSchedule') {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.CLINIC, GlobalVariable.pwd)

            CustomKeywords.'com.da.commonutilities.search'()

            WebUI.maximizeWindow()

            CustomKeywords.'com.da.commonutilities.scheduleMeeting'()

            WebUI.waitForPageLoad(4)

            CustomKeywords.'com.da.commonutilities.searchStatus'()
        } else if (GlobalVariable.Status == 'Pending-ExpSvcReview') {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.EXP_SVC, GlobalVariable.pwd)

            CustomKeywords.'com.da.commonutilities.search'()

            WebUI.maximizeWindow()

            CustomKeywords.'com.da.commonutilities.EXPReview'()

            WebUI.waitForPageLoad(4)

            CustomKeywords.'com.da.commonutilities.searchStatus'()
        } else {
            CustomKeywords.'com.da.commonutilities.login'(GlobalVariable.URL, GlobalVariable.RD, GlobalVariable.pwd)

            CustomKeywords.'com.da.commonutilities.search'()

            WebUI.maximizeWindow()

            CustomKeywords.'com.da.commonutilities.pendingClinic'()

            WebUI.waitForPageLoad(4)

            CustomKeywords.'com.da.commonutilities.searchStatus'()
        }
    }
}

