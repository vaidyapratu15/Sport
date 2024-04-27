package com.playexch.sportsbook;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class TennisBackMatchedTC extends BaseTest {

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
	public void verifyMatchesAvailable() throws InterruptedException {
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
	public void backBetPlace() throws InterruptedException {
		updateNewVersion();
		try {  //First Event 
		profilePage.getPlayexchFirstEventName();
		profilePage.setFirstMatchOddStatus(); // First event //oddsstatus
		tennisPage.clickPlayexchFHorseBack();
//		tennisPage.clickPlayexchSHorseBack();
		tennisPage.getPlayexchStakeValue("10");
		tennisPage.clickPlayexchPlaceBtn();
	} catch (Exception e) {
	    test.info("Unable to click on first event horse odds. Trying second event.");
	    profilePage.getPlayexchSecondEventName(); profilePage.setSecondMatchOddStatus();  //Second Event //oddsStatus
//	    tennisPage.clickPlayexchSecondEventFHorseBack();   
	    tennisPage.clickPlayexchSecondEventSHorseBack();   
		tennisPage.getPlayexchStakeValue("10");
		tennisPage.clickPlayexchPlaceBtn();
	}}

	@Test(priority = 4, dependsOnMethods = { "verifyMatchesAvailable", "backBetPlace" })
	public void verifyBetPlacedUnmatched() {
		updateNewVersion();
		try {
			openBetsPage.clickPlayexchOpenbetsBtn();
			test.info(openBetsPage.getPlayexchUnmatched());
			test.info(openBetsPage.getPlayexchUnmatchedBackLay());
			Thread.sleep(3000);
			tennisPage.clickPlayexchUnmatchedCancelBtn();
		} catch (Exception e) {
			test.info("No Unmatched Bets");
		}
	}

	@Test(priority = 5, dependsOnMethods = { "verifyMatchesAvailable", "backBetPlace" })
	public void verifyBetPlacedMatched() {
		updateNewVersion();
		try {
			test.info(openBetsPage.getPlayexchMatched());
			test.info(openBetsPage.getPlayexchMatchedBackLay1());
		} catch (Exception e) {
			test.info("No Matched Bets");
		}
	}

	@Test(priority = 6, dependsOnMethods = { "verifyMatchesAvailable", "backBetPlace" })
	public void getProfileInfoAfterPlaceBet() throws InterruptedException, IOException {
		test.info("After Bet Place");
		getProfileInfo();
		profilePage.verifyBalanceAfterBets();
		profilePage.clickPlayexchCloseBtn();
	}

	@Test(priority = 7, dependsOnMethods = { "verifyMatchesAvailable", "backBetPlace" })
	public void VerifyAllBetPlaceInfo() {
		updateNewVersion();
		openBetsPage.clickPlayexchOpenbetsBtn();
		openBetsPage.getPlayexchMyAllBets();
	}

	@Test(priority = 8, dependsOnMethods = { "verifyMatchesAvailable", "backBetPlace" })
	public void verifyNetExposure() throws IOException {
		profilePage.setPlayexchBalanceInformation();
		String before = Utilities.getBalanceData("BeforeNetExposure");
		String after = Utilities.getBalanceData("AfterNetExposure");
		test.info("Before NetExposure " + before + "  |  " + "After NetExposure " + after);
		Assert.assertNotEquals(before, after);
	}

	@Test(priority = 9)
	public void logOutToWeb() throws InterruptedException {
		loginPage.clickPlayexchLogOut(driver);
	}
	
	
	public void getProfileInfo() throws InterruptedException {
		profilePage.clickPlayexchProfile();
		Thread.sleep(3000);
		test.info(profilePage.getPlayexchavailableBlns());
		test.info(profilePage.getPlayexchCreditLimit());
		test.info(profilePage.getPlayexchWinnings());
		test.info(profilePage.getPlayexchNetExposure());
//			profilePage.clickPlayexchCloseBtn();
	}

	public void updateNewVersion() {
		loginPage.clickPlayexchUpdate();
	}

	
}
