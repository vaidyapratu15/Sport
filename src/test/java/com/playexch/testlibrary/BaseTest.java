package com.playexch.testlibrary;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.commons.mail.EmailException;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.playexch.library.BaseClass;
import com.playexch.library.Utilities;
import com.playexch.module.PlayexchBookmakerBetsPage;
import com.playexch.module.PlayexchChangePasswordPage;
import com.playexch.module.PlayexchCricketPage;
import com.playexch.module.PlayexchEditStakePage;
import com.playexch.module.PlayexchFancyPage;
import com.playexch.module.PlayexchLoginPage;
import com.playexch.module.PlayexchOneClickBetPage;
import com.playexch.module.PlayexchOpenBets;
import com.playexch.module.PlayexchProfilePage;
import com.playexch.module.PlayexchSoccerPage;
import com.playexch.module.PlayexchTennisPage;
import com.playexch.module.PlayexchUofPage;
import com.playexch.module.PlayexchSportsBookPage;

public class BaseTest extends BaseClass {
	
		
	@BeforeSuite //(alwaysRun = true)
	public void initializeBrowser() throws IOException, ParseException {	
		setUpBrowser();
	//	driver.get(Utilities.getJsonData("url"));
	}	
	@AfterSuite
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
	@BeforeTest
	public void beforeTest() {
		setExtentReport();
	}
	@AfterTest
	public void afterTest() throws IOException {
		extent.flush();
		Desktop.getDesktop().browse(new File("Extent Report.html").toURI());
	}
	@BeforeMethod
	public void createExtentTest(ITestResult result,Method m ){
//		 test = extent.createTest(m.getName());
		String testName = result.getMethod().getRealClass().getSimpleName() + " - " + result.getMethod().getMethodName();
		test = extent.createTest(testName, result.getMethod().getDescription());
	}
	@AfterMethod
	public void captureSceenshot(ITestResult result, Method m) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			Utilities.CaptureScreenshots(result.getMethod().getMethodName(), driver);
			test.log(Status.FAIL, m.getName()); // test.addScreenCaptureFromPath(ss);
		} else {
			test.log(Status.PASS, m.getName());
		} 
	}
	
	
	@AfterSuite
	public void setEmail() throws EmailException {
//		MailTestClass.setAttachEmailReport();
	}
	
	@BeforeClass
	public void pageObjects() {
		soft = new SoftAssert(); loginPage = new PlayexchLoginPage(driver); profilePage = new PlayexchProfilePage(driver);
		openBetsPage = new PlayexchOpenBets(driver);soccerPage = new PlayexchSoccerPage(driver);changePwdPage = new PlayexchChangePasswordPage(driver);
	    cricketPage = new PlayexchCricketPage(driver); tennisPage = new PlayexchTennisPage(driver);bookmakerPage = new PlayexchBookmakerBetsPage(driver);
	    fancyPage = new PlayexchFancyPage(driver);  oneClickPage= new PlayexchOneClickBetPage(driver);
	    uofPage = new PlayexchUofPage(driver); sportbookPg = new PlayexchSportsBookPage(driver);
		editstake = new PlayexchEditStakePage(driver);
	}
	
	protected SoftAssert soft;protected PlayexchLoginPage loginPage;protected PlayexchProfilePage profilePage;
	protected PlayexchOpenBets openBetsPage; protected PlayexchSoccerPage soccerPage;protected PlayexchChangePasswordPage changePwdPage;
	protected PlayexchCricketPage  cricketPage; protected PlayexchTennisPage  tennisPage; protected PlayexchBookmakerBetsPage  bookmakerPage;
	protected PlayexchFancyPage fancyPage; protected PlayexchOneClickBetPage oneClickPage; protected  PlayexchUofPage uofPage;
	protected PlayexchSportsBookPage sportbookPg; protected PlayexchEditStakePage editstake;
		
		
	
}

























