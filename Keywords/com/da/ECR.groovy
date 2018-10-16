package com.da
import org.junit.After
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.StaleElementReferenceException
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.sun.org.apache.xalan.internal.xsltc.compiler.Number
import org.apache.commons.lang.RandomStringUtils
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.WebDriver as WebDriver
import WebElement as WebElement
import java.text.SimpleDateFormat
import java.text.NumberFormat
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.sun.jna.platform.win32.WinDef.WORD
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.da.commonutilities as CUTILS


public class ECR {

	public final static String XPATH_LABEL ='//label[contains(.,"%s")]'

	@Keyword
	def CreateECR(String Filelocation, String ORG, String ACC,String PL, String RD, String RL, String PLTL,Boolean DataDriven, String TestCaseNo,String TestDataFile, String APP) {
		Filelocation=System.getProperty("user.dir")+Filelocation
		GlobalVariable.Filelocation=Filelocation
		TestDataFile=System.getProperty("user.dir")+TestDataFile
		println Filelocation
		println TestDataFile



		WebUI.delay(2)
		WebUI.click(findTestObject('Generic/New'))
		WebUI.delay(4)
		WebUI.click(findTestObject('Generic/span_Bid Process'))
		WebUI.delay(4)
		WebUI.click(findTestObject('ECR/Generic/WOTAB'))
		WebUI.delay(2)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 2)
		WebUI.delay(4)
		WebUI.click(findTestObject('ECR/Generic/BID_TYPE'))
		Import(Filelocation)
		WebUI.delay(2)



		CUTILS.staleElement('Generic/ORG',ORG,'setText')
		WebUI.delay(5)
		WebUI.sendKeys(findTestObject('Generic/ORG'), Keys.chord(Keys.TAB))
		WebUI.delay(5)
		CUTILS.staleElement('Generic/ACC',ACC,'setText')
		WebUI.delay(5)
		WebUI.sendKeys(findTestObject('Generic/ACC'), Keys.chord(Keys.TAB))
		WebUI.delay(7)
		WebUI.waitForPageLoad(30)
		def EngNew = false

		if(WebUI.verifyTextPresent("The engagement you entered doesn't match", false, FailureHandling.OPTIONAL)) {
			EngNew = true
			WebUI.setText(findTestObject('Generic/ENG_DESC'), 'AUTOTEST1')
			WebUI.delay(3)
		}
		else
			WebUI.delay(5)

		CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
		WebUI.verifyTextPresent("LedBy", false, FailureHandling.OPTIONAL)
		WebUI.delay(5)

		WebUI.waitForPageLoad(30)
		String LedBy=WebUI.getText(findTestObject("ECR/Generic/LedBy"))
		WebUI.delay(2)
		println LedBy
		String ProjectType=WebUI.getText(findTestObject("ECR/Generic/ProjectType"))
		WebUI.delay(2)
		println ProjectType
		String Licence=WebUI.getText(findTestObject("ECR/Generic/LicenceNew"))
		println Licence
		WebUI.setText(findTestObject('ECR/EngInfo/COID'), 'CO-12')
		WebUI.delay(2)


		if(LedBy=='Pega Led' && ProjectType=='Full Implementation') {
			GlobalVariable.referencesize=true
			println GlobalVariable.referencesize
			CUTILS.clickUsingJS(findTestObject('Object Repository/Generic/RapidDelivery'),30)
			WebUI.delay(2)
			CUTILS.clickUsingJS(findTestObject('Object Repository/Generic/RapidDelivery'),30)
			WebUI.delay(2)
			CUTILS.clickUsingJS(findTestObject('Object Repository/Generic/MorethanOneRLS'),30)
			WebUI.delay(2)
			CUTILS.clickUsingJS(findTestObject('Object Repository/Generic/gapDrivenMethodology'),30)
			WebUI.delay(2)
		}
		else {
			CUTILS.clickUsingJS(findTestObject('Object Repository/Generic/MorethanOneRLS'),30)
			println "going to click one element"
			WebUI.delay(2)
			CUTILS.clickUsingJS(findTestObject('Object Repository/Generic/MorethanOneRLS'),30)
		}

		if(Licence=='Yes') {
			WebUI.delay(1)
			WebUI.setText(findTestObject('ECR/Generic/Licence'), '85000')
			WebUI.delay(1)
			println 'licence value entered'
		}
		else
			println 'no licence value'

		if(EngNew) {
			WebUI.delay(5)
			WebUI.selectOptionByValue(findTestObject('Object Repository/Generic/PlatformID'), 'PRD-87', false)
			WebUI.delay(5)
			WebUI.selectOptionByValue(findTestObject('Object Repository/Generic/PlatformVersion'),'RLS-2699',false)
			WebUI.delay(5)
			CUTILS.clickUsingJS(findTestObject('Object Repository/Generic/PlatformTrue'),30)
			WebUI.delay(2)
			WebUI.sendKeys(findTestObject('ECR/Generic/Location'), 'Cambridge')
			if(WebUI.verifyTextPresent("PL Team Leader", false, FailureHandling.OPTIONAL)) {
				WebUI.selectOptionByValue(findTestObject('ECR/EngInfo/PLTL'),PLTL,false)
				WebUI.delay(5)
			}
			else

				WebUI.delay(5)
			WebUI.selectOptionByValue(findTestObject('ECR/EngInfo/PL'),PL, false)
			WebUI.delay(5)
			WebUI.selectOptionByValue(findTestObject('ECR/EngInfo/RL'),RL,false)
			WebUI.delay(5)
			WebUI.selectOptionByValue(findTestObject('ECR/EngInfo/RD'),RD,false)
		}
		else
			WebUI.sendKeys(findTestObject('ECR/Generic/Location'), 'Cambridge')

		WebUI.delay(5)
		CUTILS.woid()

		if(DataDriven)
			CUTILS.WriteWO(TestCaseNo, TestDataFile,1,GlobalVariable.WOID)



		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'), 2)
		WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
		WebUI.waitForPageLoad(5)


		if(WebUI.verifyElementPresent(findTestObject('ECR/Meeting/ClinicMeeting'), 3, FailureHandling.OPTIONAL)) {

			GlobalVariable.Meeting=true
			WebUI.setText(findTestObject('ECR/EngInfo/MeetingDate'), CUTILS.MeetingDT())
			WebUI.delay(5)
			WebUI.setText(findTestObject('ECR/EngInfo/instruction'), GlobalVariable.LongNotes)
			GlobalVariable.LongNotes
			WebUI.click(findTestObject('ECR/EngInfo/IsPegaMarketingYes'))
			WebUI.delay(5)
			WebUI.click(findTestObject('ECR/EngInfo/IsPegaMobileYes'))
			WebUI.delay(5)
			WebUI.click(findTestObject('ECR/EngInfo/Cloud'))
			WebUI.selectOptionByValue(findTestObject('ECR/EngInfo/Cloud'), 'On Premise', false)

			if(EngNew) {
				WebUI.delay(5)
				CUTILS.staleElement('EngInfo/AE','','setText')
				CUTILS.staleElement('EngInfo/AE','Nagaveni Nipinale','setText')
				WebUI.sendKeys(findTestObject('ECR/EngInfo/AE'), Keys.chord(Keys.DOWN))
				WebUI.delay(2)
				WebUI.click(findTestObject('ECR/Meeting/SelectHighlighted'))
				WebUI.delay(5)
				WebUI.selectOptionByValue(findTestObject('ECR/Meeting/SalesConsultant'), 'dubea1@pegasystems.com', false)
				WebUI.delay(3)
			}

			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'), 2)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			if(GlobalVariable.referencesize)
				referenceSizing()
			/*WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'), 2)*/
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('ECR/Generic/FINISH_ECR'), 2)
			WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
			WebUI.delay(5)

			if(GlobalVariable.Meeting==true){
				WebUI.click(findTestObject('ECR/EngInfo/Continue'))
				WebUI.delay(5)
				attachDocs()
			}
		}


		else {

			if(GlobalVariable.referencesize)
				referenceSizing()
			else
				WebUI.delay(2)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(7)
			WebUI.waitForPageLoad(3)
			WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
			WebUI.waitForPageLoad(30)
			WebUI.delay(5)

			if(GlobalVariable.referencesize && GlobalVariable.Meeting==false) {
				AttachCaseTypeDoc()
				WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
				WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),2)
				WebUI.click(findTestObject('Generic/SUBMIT'))
				WebUI.waitForPageLoad(30)
			}
		}
	}


	@Keyword
	def static AttachCaseTypeDoc() {
		println GlobalVariable.Filelocation
		def attachmentlist=WebUI.findWebElements(findTestObject('ECR/Meeting/Sizing/attachment'), 30)
		/* Since the data test id used for attachment xpath identifies multiple elements, the first in the list is casetype hence the index is 0 for attachmentlist[0]*/
		WebUI.delay(7)
		attachmentlist[0].click()
		WebUI.waitForPageLoad(5)
		WebUI.uploadFile(findTestObject('ECR/Meeting/Sizing/filepath'), GlobalVariable.Filelocation)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Generic/button_Submit'), 5)
		WebUI.click(findTestObject('Generic/button_Submit'))
		WebUI.delay(5)
	}



	@Keyword
	def static referenceSizing(){
		CUTILS.staleElement('ECR/Meeting/Sizing/casetype','15','setText')
		WebUI.delay(2)
		WebUI.selectOptionByValue(findTestObject('ECR/Meeting/Sizing/Complexity'), 'Low', false)
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/personas','15', 'setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/newinterface','5','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/existinginterface','4','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/channel','5','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/reports','43','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/MLPDurationLow','18','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/MLPDurationHigh','24','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/MLPEffortLow','18','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/MLPEffortHigh','24','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/ProjectDurationHigh','36','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/ProjectDurationLow','24','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/ProjectPegaEffortHigh','8700','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/ProjectPegaEffortLow','5100','setText')
		WebUI.delay(2)
		CUTILS.staleElement('ECR/Meeting/Sizing/FPRNotes','Testing','setText')
		WebUI.delay(2)
	}

	@Keyword
	def Import(String Filelocation) {
		WebUI.verifyElementPresent(findTestObject('Generic/button_Import'),2)
		WebUI.delay(5)
		WebUI.click(findTestObject('Generic/button_Import'))
		WebUI.uploadFile(findTestObject('Generic/FilePath'), Filelocation)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Generic/button_Submit'), 5)
		WebUI.click(findTestObject('Generic/button_Submit'))
		WebUI.delay(5)
	}

	@Keyword
	def approve_reject_review(String Option) {
		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		if(GlobalVariable.Status=='Pending-OPSApproval' || GlobalVariable.Status=='Pending-OPSReview')
			CUTILS.beginGSOPS()
		else(CUTILS.begin())
		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		WebUI.delay(5)
		if(WebUI.verifyTextPresent("Select an action to takeRequired", false, FailureHandling.OPTIONAL))
			CUTILS.IdentifyLabelField(Option)
		WebUI.delay(5)
		GlobalVariable.RouteToRL=false
		if(WebUI.verifyTextPresent("This quote will not be automatically routed for RL approval.", false, FailureHandling.OPTIONAL))
			GlobalVariable.RouteToRL=true

		if(GlobalVariable.RouteToRL==true) {
			CUTILS.clickUsingJS(findTestObject('ECR/Approve-Reject/routetoRL'),30)
			WebUI.delay(5)
			WebUI.waitForPageLoad(4)
			GlobalVariable.RouteToRL=false
			println GlobalVariable.RouteToRL
		}

		WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),2)
		WebUI.click(findTestObject('Generic/SUBMIT'))
		WebUI.waitForPageLoad(10)
	}

	@Keyword
	def recall() {
		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 2)
		WebUI.click(findTestObject('Generic/otherAction'))
		WebUI.delay(5)
		WebUI.click(findTestObject('Generic/Recall/recall'))
		WebUI.delay(7)
		WebUI.setText(findTestObject('Generic/Recall/recall_reason'), GlobalVariable.LongNotes)
		WebUI.delay(5)
		WebUI.click(findTestObject('Generic/Recall/recall_submit'))
		WebUI.waitForPageLoad(20)
	}


	@Keyword
	def gsopsReview(){
		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.beginGSOPS()
		WebUI.waitForPageLoad(3)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
		CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
		WebUI.waitForPageLoad(10)
	}



	@Keyword
	def gsopsReject(){

		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.beginGSOPS()
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)

		CUTILS.IdentifyLabelField('Return to requestor')
		WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
		CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'), 2)
		WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
		WebUI.delay(5)

		if(WebUI.verifyElementPresent(findTestObject('ECR/Meeting/clinicLink'), 10, FailureHandling.OPTIONAL)) {
			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'), 2)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'), 2)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('ECR/Generic/FINISH_ECR'), 2)
			WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
			WebUI.delay(5)
			WebUI.click(findTestObject('ECR/EngInfo/Continue'))

			WebUI.delay(5)
			WebUI.clearText(findTestObject('ECR/Meeting/BSW_TEXT', FailureHandling.STOP_ON_FAILURE))
			WebUI.delay(3)
			WebUI.sendKeys(findTestObject('ECR/Meeting/BSW_TEXT'), GlobalVariable.LongNotes)
			WebUI.delay(5)
			/*
			 def List= []
			 List = WebUI.findWebElements(findTestObject('ECR/Meeting/TextArea_Meeting'), 10)
			 for (int i = 0; i < List.size(); i++) {
			 List[i].clear()
			 List[i].sendKeys(GlobalVariable.LongNotes)
			 }*/

			WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
			WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
			CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
			WebUI.delay(5)
		}

		else if(GlobalVariable.referencesize==true){

			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'),5)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.waitForPageLoad(4)
			WebUI.scrollToElement(findTestObject('ECR/Generic/FINISH_ECR'),3)
			WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
			WebUI.delay(5)
			WebUI.waitForPageLoad(4)
			WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
			CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
			WebUI.delay(5)
		}

		else {
			println "no meeting"
			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'), 2)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('ECR/Generic/FINISH_ECR'), 2)
			WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
			WebUI.delay(5)
		}
	}


	@Keyword
	def static readWO(String TestCaseNo, String TestDataFile) {
		TestDataFile=System.getProperty("user.dir")+TestDataFile
		println TestDataFile
		WebUI.delay(2)
		/*	WebUI.click(findTestObject('ECR/Generic/WO/EngTab'))
		 WebUI.delay(2)
		 String ENGID=WebUI.getText(findTestObject('Object Repository/ECR/Generic/WO/ENGID'))
		 WebUI.delay(2)
		 String PROJID=WebUI.getText(findTestObject('Object Repository/ECR/Generic/WO/PROJID'))
		 WebUI.delay(2)
		 CUTILS.WriteWO(TestCaseNo, TestDataFile,3,ENGID)
		 CUTILS.WriteWO(TestCaseNo, TestDataFile,4,PROJID)
		 WebUI.delay(2)
		 WebUI.click(findTestObject('Object Repository/ECR/Generic/WO/CRTab'))
		 WebUI.delay(2)
		 String CRID=WebUI.getText(findTestObject('Object Repository/ECR/Generic/WO/CRID'))
		 WebUI.delay(2)
		 CUTILS.WriteWO(TestCaseNo, TestDataFile,2,CRID)
		 WebUI.delay(2)*/
		CUTILS.WriteWO(TestCaseNo, TestDataFile,5,GlobalVariable.WOSTATUS)
		CUTILS.WriteWO(TestCaseNo, TestDataFile,1,GlobalVariable.WOID)
	}

	@Keyword
	def attachDocs(){
		WebUI.delay(5)
		CUTILS.begin()
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		WebUI.delay(5)
		if(GlobalVariable.referencesize)
			AttachCaseTypeDoc()

		WebUI.delay(2)
		WebUI.sendKeys(findTestObject('ECR/Meeting/BSW_TEXT'), GlobalVariable.LongNotes)

		WebUI.delay(5)
		def List= []
		List = WebUI.findWebElements(findTestObject('ECR/Meeting/TextArea_Meeting'), 10)
		for (int i = 0; i < List.size(); i++) {
			List[i].clear()
			List[i].sendKeys(GlobalVariable.LongNotes)
		}
		WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
		CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
		WebUI.delay(5)
		GlobalVariable.Meeting=true
	}



	@Keyword
	def ContractDisposition(String ContractOption, String DispositionReason) {

		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.begin()
		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.IdentifyLabelField(ContractOption)
		WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
		CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
		WebUI.delay(5)
		WebUI.waitForPageLoad(4)
		CUTILS.begin()
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.IdentifyLabelField(DispositionReason)
		WebUI.delay(5)
		WebUI.setText(findTestObject('ECR/Approve-Reject/disposition_comments'), GlobalVariable.LongNotes)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
		CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
		WebUI.delay(5)
	}


	/*
	 @Keyword
	 def docUpdate() {
	 WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
	 CUTILS.begin()
	 WebUI.delay(5)
	 WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
	 WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'),5)
	 WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
	 WebUI.delay(5)
	 WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'),5)
	 WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
	 WebUI.delay(5)
	 WebUI.waitForPageLoad(4)
	 WebUI.scrollToElement(findTestObject('ECR/Generic/FINISH_ECR'),3)
	 WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
	 WebUI.delay(5)
	 WebUI.waitForPageLoad(4)
	 }
	 */

	@Keyword

	def docUpdate() {

		GlobalVariable.referencesize=true
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.begin()
		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'),5)
		WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
		WebUI.delay(5)
		if(WebUI.verifyElementPresent(findTestObject('ECR/Meeting/clinicLink'), 10, FailureHandling.OPTIONAL)) {
			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'),5)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'),5)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.waitForPageLoad(4)
			WebUI.scrollToElement(findTestObject('ECR/Generic/FINISH_ECR'),3)
			WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
			WebUI.delay(5)
			WebUI.click(findTestObject('ECR/EngInfo/Continue'))
			WebUI.waitForPageLoad(3)
			/*
			 CUTILS.begin()
			 WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
			 WebUI.sendKeys(findTestObject('ECR/Meeting/BSW_TEXT'), GlobalVariable.LongNotes)
			 */


			WebUI.delay(5)
			def List= []
			List = WebUI.findWebElements(findTestObject('ECR/Meeting/TextArea_Meeting'), 10)
			for (int i = 0; i < List.size(); i++) {
				List[i].clear()
				List[i].sendKeys(GlobalVariable.LongNotes)
			}

			WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
			CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
			WebUI.delay(5)
		}
		else if(GlobalVariable.referencesize==true){

			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'),5)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.waitForPageLoad(4)
			WebUI.scrollToElement(findTestObject('ECR/Generic/FINISH_ECR'),3)
			WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
			WebUI.delay(5)
			WebUI.waitForPageLoad(4)
			WebUI.sendKeys(findTestObject('ECR/Approve-Reject/NOTES'), GlobalVariable.LongNotes)
			WebUI.delay(5)
			WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
			CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
			WebUI.delay(5)
		}
		else {
			WebUI.scrollToElement(findTestObject('Generic/NEXT_BUTTON'),5)
			WebUI.click(findTestObject('Generic/NEXT_BUTTON'))
			WebUI.delay(5)
			WebUI.waitForPageLoad(4)
			WebUI.scrollToElement(findTestObject('ECR/Generic/FINISH_ECR'),3)
			WebUI.click(findTestObject('ECR/Generic/FINISH_ECR'))
			WebUI.delay(5)
			WebUI.waitForPageLoad(4)
		}
	}


	@Keyword
	def scheduleMeeting(){
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.begin()
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		WebUI.setText(findTestObject('ECR/Meeting/location'), 'Cambridge')
		WebUI.delay(2)

		/*
		 TestObject ckeditor_frame = driver.find_element(:class => 'cke_wysiwyg_frame cke_reset')
		 # Using JavaScript injection to set innerHTML, shown as WYSIWYG
		 driver.switch_to.frame(ckeditor_frame)
		 ck_editor_body = driver.find_element(:tag_name => 'body')
		 driver.execute_script("arguments[0].innerHTML = '<h1>CKEditor</h1>Test'", ck_editor_body)
		 */
		WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
		CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
		WebUI.waitForPageLoad(30)
	}
	@Keyword

	def pendingClinic(String Outcome) {
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.begin()
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		CUTILS.IdentifyLabelField(Outcome)
		WebUI.setText(findTestObject('ECR/Meeting/OUTCOME_NOTES'), GlobalVariable.LongNotes)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('Object Repository/Generic/SUBMIT'),5)
		CUTILS.clickUsingJS(findTestObject('Generic/SUBMIT'),30)
		WebUI.waitForPageLoad(30)
	}
}



