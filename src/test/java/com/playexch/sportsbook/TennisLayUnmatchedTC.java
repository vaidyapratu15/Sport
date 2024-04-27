package com.playexch.sportsbook;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import com.aventstack.extentreports.Status;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class TennisLayUnmatchedTC extends BaseTest {

	@Test(priority = 0)
	public void loginToWeb() throws EncryptedDocumentException, IOException, ParseException {
		driver.get(Utilities.getJsonData("url"));
		updateNewVersion();
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl());
		test.info("Title - " + driver.getTitle());
		test.info("Username - " + loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password"));
		loginPage.clickPlayexchLoginPageloginBtn();
	}

	@Test(priority = 1)
	public void verifyMatchesAvailable() {
		updateNewVersion();
		tennisPage.clickPlayexchTennisbtn();
		tennisPage.setPlayexchMatch(driver);
		String text = tennisPage.getPlayexchCurrentlyMatches();
		test.log(Status.INFO, text);
		String actual = tennisPage.getPlayexchBackBtn();
		Assert.assertEquals(actual, "Back");
	}

	@Test(priority = 2, dependsOnMethods = "verifyMatchesAvailable")
	public void getProfileInfoBeforePlaceBet() throws InterruptedException, IOException {
		test.info("Before Bet Place");
		getProfileInfo();
		profilePage.verifyBalanceBeforeBets();
		profilePage.clickPlayexchCloseBtn();
	}

	@Test(priority = 3, dependsOnMethods = "verifyMatchesAvailable")
	public void layBetPlace() throws InterruptedException {
		updateNewVersion();
		try {  //First Event 
		profilePage.getPlayexchFirstEventName();
		profilePage.setFirstMatchOddStatus(); // First event //oddsStatus
//		tennisPage.clickPlayexchFHorseLay();
	    tennisPage.clickPlayexchSHorseLay(); 
		tennisPage.setPlayexchFOddsValue();
		tennisPage.getPlayexchStakeValue("20");
		tennisPage.clickPlayexchPlaceBtn();
	 } catch (Exception e) {
	    test.info("Unable to click on first event horse odds. Trying second event.");
		profilePage.getPlayexchSecondEventName();  profilePage.setSecondMatchOddStatus(); //Second Event //oddsStatus
		tennisPage.clickPlayexchSecondEventFHorseLay();   
		tennisPage.clickPlayexchSecondEventSHorseLay();   
		tennisPage.setPlayexchFOddsValue();
		tennisPage.getPlayexchStakeValue("20");
//      tennisPage.getPlayexchOddsValue("1.01", driver);
		tennisPage.clickPlayexchPlaceBtn();
	}}

	@Test(priority = 4, dependsOnMethods = { "verifyMatchesAvailable", "layBetPlace" })
	public void verifyBetPlacedUnmatched() throws InterruptedException {
		updateNewVersion();
		openBetsPage.clickPlayexchOpenbetsBtn();
		Thread.sleep(3000);
		test.info(openBetsPage.getPlayexchUnmatched());
		test.info(openBetsPage.getPlayexchUnmatchedBackLay());
	}

	@Test(priority = 5, dependsOnMethods = { "verifyMatchesAvailable", "layBetPlace" })
	public void getProfileInfoAfterPlaceBet() throws InterruptedException, IOException {
		test.info("After Bet Place");
		getProfileInfo();
		profilePage.verifyBalanceAfterBets();
		profilePage.clickPlayexchCloseBtn();
	}

	@Test(priority = 6, dependsOnMethods = { "verifyMatchesAvailable", "layBetPlace" })
	public void getProfileInfoAfterCancelUnmatched() throws InterruptedException, IOException {
		updateNewVersion();
		openBetsPage.clickPlayexchOpenbetsBtn();
		cricketPage.clickPlayexchUnmatchedCancelBtn();
		test.info("After cancel Unmatch profile info ");
		getProfileInfo();
		profilePage.clickPlayexchCloseBtn();
	}

	@Test(priority = 7, dependsOnMethods = { "verifyMatchesAvailable", "layBetPlace" })
	public void VerifyAllBetPlaceInfo() {
		updateNewVersion();
		openBetsPage.clickPlayexchOpenbetsBtn();
		openBetsPage.getPlayexchMyAllBets();
	}

	@Test(priority = 8, dependsOnMethods = { "verifyMatchesAvailable", "layBetPlace" })
	public void verifyNetExposure() throws IOException {
		profilePage.setPlayexchBalanceInformation();
		String before = Utilities.getBalanceData("BeforeNetExposure");
		String after = Utilities.getBalanceData("AfterNetExposure");
		test.info("Before NetExposure " + before + "  |  " + "After NetExposure " + after);
		Assert.assertNotEquals(before, after);
	}

	@Test(priority = 9)
	public void logOutToWeb() {
		loginPage.clickPlayexchLogOut(driver);
	}

	public void getProfileInfo() throws InterruptedException {
		profilePage.clickPlayexchProfile();
		Thread.sleep(3000);
		test.info(profilePage.getPlayexchavailableBlns());
		test.info(profilePage.getPlayexchCreditLimit());
		test.info(profilePage.getPlayexchWinnings());
		test.info(profilePage.getPlayexchNetExposure());
//		profilePage.clickPlayexchCloseBtn();
	}

	public void updateNewVersion() {
		loginPage.clickPlayexchUpdate();
	}

}
