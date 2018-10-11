
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



public class ESU {

	public final static String XPATH_TRIAGE_CREATION ='//label[contains(.,"%s")]'
	public final static String XPATH_HEALTH_STATUS = '//input[@id(contains,"PSD"+"%s"+"%s")]'
	@Keyword

	def login() {
		WebUI.openBrowser('')

		WebUI.navigateToUrl(GlobalVariable.URL)

		WebUI.setText(findTestObject('ESU/Genric/input_UserIdentifier'), GlobalVariable.OPS)

		WebUI.setText(findTestObject('ESU/Genric/input_Password'), GlobalVariable.pwd)

		WebUI.click(findTestObject('ESU/Genric/span_Log in'))
	}

	@Keyword
	def ESUwo() {
		GlobalVariable.WOID=WebUI.getText(findTestObject('Generic/WOID'))
		println GlobalVariable.WOID
	}

	@Keyword

	def ESUCreation(
			String ORG,
			String ACC,
			String ENG) {

		WebUI.click(findTestObject('ESU/ESUobj/New Button'))

		WebUI.mouseOver(findTestObject('ESU/ESUobj/span_Engagement Status Update'))

		WebUI.click(findTestObject('ESU/ESUobj/span_Engagement Status Update'))
		WebUI.delay(2)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 30)
		WebUI.delay(2)
		WebUI.setText(findTestObject('/Generic/ORG'), ORG)
		WebUI.delay(5)
		WebUI.sendKeys(findTestObject('/Generic/ORG'), Keys.chord(Keys.TAB))
		WebUI.delay(5)
		WebUI.setText(findTestObject('/Generic/ACC'), ACC)
		WebUI.delay(5)
		WebUI.sendKeys(findTestObject('/Generic/ACC'), Keys.chord(Keys.TAB))
		WebUI.delay(5)
		WebUI.setText(findTestObject('/Generic/ENG'), ENG)
		WebUI.delay(5)
		WebUI.sendKeys(findTestObject('/Generic/ENG'), Keys.chord(Keys.TAB))
		WebUI.delay(5)
		setSLA()
		WebUI.delay(5)
		ESUwo()
		WebUI.delay(5)
	}

	@Keyword

	def setSLA(){
		WebUI.delay(2)
		WebUI.waitForElementPresent(findTestObject('ESU/ESUobj/Set_SLA'), 5)
		WebUI.delay(2)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_ELGOAL'))
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/input_ELGOAL'), CUTILS.MeetingDT())
		WebUI.delay(2)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_ELDeadline'))
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/input_ELDeadline'), CUTILS.MeetingDT())
		WebUI.delay(2)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_RecallDeadline'))
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/input_RecallDeadline'), CUTILS.MeetingDT())
		WebUI.delay(2)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_PLGoal'))
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/input_PLGoal'), CUTILS.MeetingDT())
		WebUI.delay(2)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_Reopen_SLA'))
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/input_Reopen_SLA'), CUTILS.MeetingDT())
		WebUI.delay(2)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_Initial_ForceClose'))
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/input_Initial_ForceClose'), CUTILS.MeetingDT())
		WebUI.delay(2)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_Final_ForceClose'))
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/input_Final_ForceClose'), CUTILS.MeetingDT())
		WebUI.delay(2)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_ProjectorProblem'))
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/input_ProjectorProblem'), CUTILS.MeetingDT())
		WebUI.delay(2)
		WebUI.scrollToElement(findTestObject('ESU/ESUobj/button_Submit'), 5)
		WebUI.click(findTestObject('ESU/ESUobj/button_Submit'))
		WebUI.delay(5)
	}

	@Keyword
	def approve(){
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 2)
		CUTILS.begin()
		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 2)
		GlobalVariable.EngagementLeader=WebUI.getText(findTestObject("ESU/ESUobj/Eng_Ldr"))
		WebUI.delay(2)
		println GlobalVariable.EngagementLeader
		GlobalVariable.PracticeLeader=WebUI.getText(findTestObject("ESU/ESUobj/Prac_Ldr"))
		WebUI.delay(2)
		println GlobalVariable.PracticeLeader
		WebUI.delay(2)
		if(GlobalVariable.EngagementLeader != GlobalVariable.PracticeLeader){
			if(GlobalVariable.Skip == false) {
				ESUApproval()
				submit()
				GlobalVariable.Skip == true
			}

			else {
				submit()
			}
		}

		else {
			ESUApproval()
			submit()
		}
	}


	@Keyword
	def HealthStatus(healthlable,color) {
		String path=String.format(XPATH_HEALTH_STATUS, healthlable,color)

		TestObject WHS = new TestObject('ESU/ESUobj/Healthcode').addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, path, true)
		WebUI.click(WHS)
		WebUI.delay(2)
	}

	@Keyword
	def ESUApproval(){

		/*WebUI.delay(2)
		 WebUI.click(findTestObject('ESU/ESUobj/label_CustomerSuccess'))
		 WebUI.delay(2)
		 WebUI.click(findTestObject('ESU/ESUobj/label_HoursBudget'))
		 WebUI.delay(2)
		 WebUI.click(findTestObject('ESU/ESUobj/label_Legal'))
		 WebUI.delay(2)
		 WebUI.click(findTestObject('ESU/ESUobj/label_Resources'))
		 WebUI.delay(2)
		 WebUI.click(findTestObject('ESU/ESUobj/label_Schedule'))
		 WebUI.delay(2)
		 WebUI.click(findTestObject('ESU/ESUobj/label_Technical'))*/
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/ESU/ESUobj/Governance_Meet'))
		WebUI.delay(2)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_AESStatus'), 'Pega Cloud', false)
		WebUI.delay(2)
		WebUI.click(findTestObject('/ESU/ESUobj/input_AESDev'))
		WebUI.delay(2)
		WebUI.click(findTestObject('ESU/ESUobj/h3_Engagement profile'))
		WebUI.delay(2)
		WebUI.click(findTestObject('ESU/ESUobj/label_DCO'))
		WebUI.delay(2)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_MethodologyUsed'), 'Scrum', false)
		WebUI.delay(2)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_serviceTransition'), 'Completed', false)
		WebUI.delay(2)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_Platform'), 'PRD-87', false)
		WebUI.delay(3)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_PlatformVersion'), 'RLS-1832', false)
		WebUI.delay(2)
		WebUI.click(findTestObject('ESU/ESUobj/label_PlatformOnly'))
		WebUI.delay(5)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_Vertical'), 'HEALTHCARE', false)
		WebUI.delay(2)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_ProjectType'), 'Full Implementation',
				false)
		WebUI.delay(10)
		/*WebUI.click(findTestObject('ESU/ESUobj/label_Co-production'))
		 WebUI.delay(10)*/

		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_MethodOfDelivery'), 'PARTNER', false)
		WebUI.delay(5)
		WebUI.selectOptionByLabel(findTestObject('ESU/ESUobj/select_TechnicalContact'), 'Abhay Thomas', false)
		// WebUI.verifyOptionPresentByValue(findTestObject('ESU/ESUobj/select_TechnicalContact'), 'Abhay Thomas', false, 5)
		WebUI.delay(5)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_POCassets'), 'Some', false)
		WebUI.delay(4)
		WebUI.clearText(findTestObject('ESU/ESUobj/input_EstimatedHours'))
		WebUI.delay(4)
		WebUI.setText(findTestObject('ESU/ESUobj/input_EstimatedHours'), '34')
		WebUI.delay(5)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_Cloud_OnPrem'), 'Pega Cloud', false)
		WebUI.delay(4)

		String MOD=WebUI.getText(findTestObject('ESU/ESUobj/select_MethodOfDelivery'))
		WebUI.delay(2)
		println MOD
		/* String ProjectType=WebUI.getText(findTestObject('ESU/ESUobj/select_ProjectType'))
		 WebUI.delay(2)
		 println ProjectType*/

		if(MOD =='PL' || MOD == 'PLPP'){
			GlobalVariable.PSR=true
			println GlobalVariable.PSR
			Attcahment()
			submit()
		}

		else if (MOD =='PARTNER') {
			WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/select_DeliveryPartner'), 'ACCENT', false)
			WebUI.delay(5)
		}

		else {
			submit()
		}
		
	}


	@Keyword
	def submit(){
		WebUI.click(findTestObject('h3_Weekly Health Status'))
		WebUI.delay(2)
		WebUI.delay(2)
		WebUI.setText(findTestObject('ESU/ESUobj/textarea_Comment'), 'Test Automation script')
		WebUI.delay(2)
		WebUI.click(findTestObject('ESU/ESUobj/button_Submit'))
		WebUI.delay(4)
	}

	@Keyword
	def Attcahment(){
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 2)
		WebUI.delay(2)
		WebUI.click(findTestObject('h3_Weekly Health Status'))
		WebUI.delay(2)
		WebUI.click(findTestObject('ESU/ESUobj/Attachment'))
		WebUI.delay(4)
		WebUI.uploadFile(findTestObject('ESU/ESUobj/SelectFile'), GlobalVariable.PSRFile)
		WebUI.delay(5)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/filetype'), 'PSR', false)
		WebUI.delay(5)
		WebUI.scrollToElement(findTestObject('ESU/Genric/Save_close'), 5)
		WebUI.delay(5)
		WebUI.verifyElementPresent(findTestObject('ESU/ESUobj/PSRsubmitted'), 5)
		WebUI.delay(5)

	}

	@Keyword
	def creattriage(Triage)
	{
		WebUI.waitForElementPresent(findTestObject('ESU/ESUobj/Trgtext'), 5)
		WebUI.delay(2)


		String path=String.format(XPATH_TRIAGE_CREATION, Triage)

		TestObject Triagebutton = new TestObject('ESU/ESUobj/Triagetype').addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, path, true)
		WebUI.click(Triagebutton)
		WebUI.delay(2)

		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/TrgCategory'), 'Hardware', false)
		WebUI.delay(5)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/TrgReason'), 'Server Memory', false)
		WebUI.delay(5)

	}

	@Keyword
	def TransferESU(){
		WebUI.delay(5)
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 2)
		WebUI.click(findTestObject('ESU/ESUobj/OtherActions'))
		WebUI.delay(5)
		WebUI.click(findTestObject('ESU/ESUobj/Transferlink'))
		WebUI.delay(5)
		WebUI.selectOptionByValue(findTestObject('ESU/ESUobj/TransferELPL'), 'hendj@pegasystems.com', false)
		WebUI.delay(5)
		WebUI.click(findTestObject('ESU/ESUobj/TrasferSubmit'))
		WebUI.delay(5)
		WebUI.click(findTestObject('ESU/ESUobj/TrasferClose'))
		WebUI.delay(5)
	}



}


