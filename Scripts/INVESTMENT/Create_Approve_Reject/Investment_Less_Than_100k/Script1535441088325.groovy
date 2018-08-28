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
import com.sun.xml.internal.org.jvnet.mimepull.DataFile
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testdata.InternalData


int rowNo = 1

InternalData data = findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K')

for (def index : (0..data.getRowNumbers() - 1)) {

WebUI.openBrowser(findTestData('URL').getValue(1, 1))

WebUI.maximizeWindow()

'Login as Practice Leader'
CustomKeywords.'da.common.methods.CommonActionsInDA.loginDA'(findTestData('Role-UserName-Password').getValue(2, 2), findTestData(
        'Role-UserName-Password').getValue(3, 2))

WebUI.delay(2)

String engagementName = CustomKeywords.'da.common.methods.CommonActionsInDA.randomEngagementNameGenerator'()

WebUI.comment(engagementName)

String filePath = 'C:\\Users\\ranjs1\\git\\DAUIAutomation\\Data Files\\BSW\\Investment\\INV-BSW_BSW_PRJ v9_LessThan100K.xlsm'

'Go to ----> (+) -- > Investment'
CustomKeywords.'da.common.methods.CommonActionsInDA.createInvestment'()

'Select \'Create New Investment Option\' and Proceed by creating the investment with the values as mentioned in the BSW file and the test Data \'New Engagement\''
CustomKeywords.'da.investment.investmentCommonAction.createNewInvestment'(
	findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(1, rowNo), 
    findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(2, rowNo),
	engagementName,
	findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(4, rowNo), 
	findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(5, rowNo), 
	findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(6, rowNo), 
	filePath,
	findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(7, rowNo),
	findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(8, rowNo),
	findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(9, rowNo),
	findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(10, rowNo) 
	)

'Enter Background Verification and Justification'
CustomKeywords.'da.investment.investmentCommonAction.enterJustificationAndBackGround'()

'Fetching Work Object ID, OPS id, RD id, RL id....'
String woid = WebUI.getText(findTestObject('CommonDAObjectRepository/Page_Pega 7/WOID'))
String gsops_email = findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(22, rowNo)
String rd_email = findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(18, rowNo)
String rl_email = findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(19, rowNo)
String pltl_email = findTestData('Investment/Test-Data-For-Inv-Value-Less-Than-100K').getValue(20, rowNo)
String sp_email = findTestData('Investment/Test-Data-For-Inv-Value-More-Than-100K').getValue(23, rowNo)

WebUI.comment('Investment Work Object Created is = ' + woid)

'Approve Investment Request as spl approver'
CustomKeywords.'da.investment.investmentCommonAction.spApproval'(woid,sp_email)

'Approve Investment Request as GSOPS'
CustomKeywords.'da.investment.investmentCommonAction.gsopsApproval'(woid,gsops_email)

'Approve Investment Request as Regional Director'
CustomKeywords.'da.investment.investmentCommonAction.rdApproval'(woid,rd_email)

'Approve Investment Request as Regional Leader'
CustomKeywords.'da.investment.investmentCommonAction.rlApproval'(woid,rl_email)

'Investment Work Object and Details.\r\nVerification to see if it is properly linked to Projector'
CustomKeywords.'da.investment.investmentCommonAction.verifyProjectInfoOnInv'(woid,gsops_email)

rowNo++

WebUI.closeBrowser()

}

