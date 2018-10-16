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
import com.kms.katalon.core.webui.keyword.builtin.VerifyElementPresentKeyword
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.lang.annotation.ElementType
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class commonutilities {
	protected String Filelocation

	public final static String XPATH_LABEL ='//label[contains(.,"%s")]'



	@Keyword
	def static IdentifyLabelField(String Label) {
		String path=String.format(XPATH_LABEL , Label)
		TestObject RadionButton = new TestObject('Generic/XPATH').addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, path, true)
		WebUI.click(RadionButton)
	}
	
	
	
	
	
	@Keyword
	def static clickUsingJS(TestObject to, int timeout) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(to, timeout)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
	}
	@Keyword

	def static setprop(String Flow){

		if (Flow =='Approve') {
			GlobalVariable.RLReject=false
			GlobalVariable.RDReject=false
			GlobalVariable.OpsReject=false
			GlobalVariable.RouteToRL=true
			GlobalVariable.RDD_Reject=false
			GlobalVariable.EXP_SVC_Reject=false
			println Flow
		}

		else if(Flow =='Reject') {
			GlobalVariable.RLReject=true
			GlobalVariable.RDReject=true
			GlobalVariable.OpsReject=true
			GlobalVariable.RouteToRL=true
			GlobalVariable.RDD_Reject=true
			GlobalVariable.EXP_SVC_Reject=true
			println Flow
		}

		else {
			GlobalVariable.RLRecall=true
			GlobalVariable.RDRecall=true
			GlobalVariable.OpsRecall=true
			GlobalVariable.RouteToRL=true
			GlobalVariable.EXP_SVC_Recall=true
			println Flow
		}
	}


	@Keyword
	def static String MeetingDT() {
		def MeetingDate = new Date()
		MeetingDate=MeetingDate+2
		return  MeetingDate.format("MM/dd/YYYY")
	}

	@Keyword
	def searchStatus(){
		WebUI.waitForPageLoad(10)
		int attempts = 0
		def WOStatusList = []
		while(attempts < 2) {
			try {
				WOStatusList = WebUI.findWebElements(findTestObject('Generic/SearchStatus'), 10)

				for (int i = 0; i < WOStatusList.size(); i++) {
					if(WOStatusList[i].text !='') {
						GlobalVariable.WOSTATUS=WOStatusList[i].text
						print "Work object status is-----"
						println  GlobalVariable.WOSTATUS
					}
					else
						println "Text is null"
				}
				break
			}
			catch(StaleElementReferenceException) {
				println "Text is null"
			}
			attempts++;
		}


		if(GlobalVariable.WOSTATUS.contains('PENDING')) {

			GlobalVariable.AssignmentList = WebUI.findWebElements(findTestObject('Generic/AssignmentStatus'), 30)
			WebUI.delay(2)
			/*for (int j = 0; j < GlobalVariable.AssignmentList.size(); j++) {*/
			GlobalVariable.Status = GlobalVariable.AssignmentList[0].text
			println GlobalVariable.AssignmentList[0].text
			def assignedTo = WebUI.findWebElements(findTestObject('Generic/AssignedTo'), 30)
			WebUI.delay(2)
			/* This code is exclusively for special approval in ECR, ECR can have more than one SP approval too, in order to determine the user(assigned to) then look up the id of the person by using NameToID*/ 
			if(GlobalVariable.AssignmentList[0].text=='Pending-SpApprApproval' || GlobalVariable.AssignmentList[0].text=='Pending-SpRevReview' || GlobalVariable.AssignmentList[0].text=='Pending-ExpSvcApproval') {
				println assignedTo[0].text
				GlobalVariable.EXP_SVC=GlobalVariable.NameToID[assignedTo[0].text]
				println GlobalVariable.EXP_SVC
			}
		}
		/*	}*/
		else

			println "work object status is RESOLVED or other than PENDING"
	}


	@Keyword

	def verofyApprovals(){
		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)

		WebUI.delay(5)
		WebUI.click(findTestObject('Generic/approvalSummary'))
		WebUI.delay(3)
		def name=[]
		def title=[]
		title=WebUI.findWebElements(findTestObject('Generic/approverTitle'), 30)
		name=WebUI.findWebElements(findTestObject('Generic/approvalName'), 30)
		/*def OPS=WebUI.findWebElements(findTestObject('Generic/OPSName'), 30)
		 println  "GSOPS user is" + OPS.text*/

		for (int i = 0; i < name.size(); i++) {
			if(GlobalVariable.NameToID[name[i].text]==GlobalVariable.RD || GlobalVariable.RL || GlobalVariable.RDD|| GlobalVariable.EXP_SVC || 'smitmi@pegasystems.com') {
				print title[i].text+"  " +"is   "
				println name[i].text
			}
			else
				println "there is no matching user in the name to ID list"
		}
	}

	@Keyword
	def search() {
		WebUI.maximizeWindow()
		WebUI.delay(5)
		WebUI.setText(findTestObject('ECR/EngInfo/search'), GlobalVariable.WOID)
		WebUI.delay(3)
		WebUI.sendKeys(findTestObject('ECR/EngInfo/search'), Keys.chord(Keys.ENTER))
		if(WebUI.verifyAlertPresent(3,FailureHandling.OPTIONAL))
			WebUI.dismissAlert()
		WebUI.waitForPageLoad(5)
		println GlobalVariable.WOID
	}

	@Keyword

	def RC() {


		WebUI.switchToFrame(findTestObject('Generic/FRAME1'), 5)
		WebUI.delay(5)
		def rateCards = []
		rateCards = WebUI.findWebElements(findTestObject('Object Repository/RC'), 30)

		int num=200
		for (int i = 0; i < rateCards.size(); i++) {
			rateCards[i].clear()
			WebUI.delay(1)
			rateCards[i].sendKeys(Integer.toString(num))
			num=num+5
			WebUI.delay(1)
		}
	}


	@Keyword

	def static begin() {
		def BeginList=[]
		BeginList= WebUI.findWebElements(findTestObject('Object Repository/Generic/BeginList'), 30)
		WebUI.delay(7)
		for (int j = 0; j < BeginList.size(); j++) {
			if (BeginList[j].isEnabled()) {
				println 'element clickable'

				WebUI.delay(5)
				BeginList[j].click()
				WebUI.waitForPageLoad(5)
				break
			}
			else
				println 'element is not clickable'
		}

		WebUI.waitForPageLoad(10)
	}

	@Keyword
	def static WriteWO(String TestCaseNo, String TestDataFile,Integer colNum,String IDS) {
		java.lang.Integer seqNum = Integer.parseInt(TestCaseNo)
		println seqNum
		InputStream ExcelFileToRead = new FileInputStream(TestDataFile);
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead)
		XSSFSheet  sheet=wb.getSheetAt(0)
		Row row = sheet.getRow(seqNum);
		Cell cell = row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(IDS);
		ExcelFileToRead.close()
		FileOutputStream fos = new FileOutputStream(TestDataFile)
		wb.write(fos)
	}

	@Keyword

	def static beginGSOPS() {
		def BeginList = []
		def AssignmentList =[]
		AssignmentList= WebUI.findWebElements(findTestObject('Generic/AssignmentStatus'), 30)
		BeginList=WebUI.findWebElements(findTestObject('Object Repository/Generic/BeginList'), 1)
		WebUI.delay(3)
		for (int j = 0; j < AssignmentList.size(); j++) {
			WebUI.delay(2)

			def Boolean Check=BeginList[j].isEnabled()
			println Check
			if (BeginList[j].isEnabled() && (AssignmentList[j].text=='Pending-OPSApproval' ||  AssignmentList[j].text=='Pending-OPSReview') ) {
				println GlobalVariable.WOSTATUS=AssignmentList[j].text
				GlobalVariable.WOSTATUS=AssignmentList[j].text
				WebUI.delay(5)
				BeginList[j].click()
				WebUI.waitForPageLoad(5)
				break
			}
			else
				println 'element is not clickable'
		}
	}


	@Keyword
	def login(String username) {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.URL)
		WebUI.setText(findTestObject('Generic/input_UserIdentifier'), username)
		WebUI.setText(findTestObject('Generic/input_Password'), GlobalVariable.pwd)
		WebUI.sendKeys(findTestObject('Generic/input_Password'), Keys.chord(Keys.ENTER))
		WebUI.maximizeWindow()
	}
	@Keyword
	def static  woid() {
		WebUI.delay(10)
		GlobalVariable.WOID=WebUI.getText(findTestObject('Generic/WOID'))
		print(GlobalVariable.WOID)
	}

	@Keyword
	def static staleElement(String stale,String value, String option) {

		for(int i=0;i<2;i++) {
			if(option=='sendKeys')
				try {
					WebUI.sendKeys(findTestObject(stale),value)
					println "first try block"
					break
				}
				catch (StaleElementReferenceException) {
					println  i
				}


			else if (option=='setText')
				try {
					WebUI.setText(findTestObject(stale),value)
					println "first try block"
					break
				}
				catch (StaleElementReferenceException) {
					println  i
				}

			else if (option=='clear')
				try {

					WebUI.clearText(findTestObject(stale))
					println "first try block"
					break
				}
				catch (StaleElementReferenceException) {
					println  i
				}


			else (option=='click')
			try {
				WebUI.click(findTestObject(stale),value)
				println "first try block"
				break
			}
			catch (StaleElementReferenceException) {
				println  i
			}
		}
	}
}

// end of class
