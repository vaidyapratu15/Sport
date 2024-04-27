package com.playexch.testclass;

import org.testng.annotations.Test;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class OneClickBettingTC extends BaseTest {

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

	@Test(priority = 1, dependsOnMethods = {"loginToWeb"})
	public void setOneClickBetSetting() throws InterruptedException {
		updateNewVersion();
		Thread.sleep(2000);
		oneClickPage.clickPlayexchProfile();
		oneClickPage.clickProfileSettingBtn();
		oneClickPage.clickOneClickBettingBtn();
//	  	oneClickPage.clickEditBtn();
	}

	@Test(priority = 3, dependsOnMethods = {"setOneClickBetSetting"})
	public void verifyActiveStake() throws InterruptedException {
		cricketPage.clickPlayexchCricketbtn();
//      tennisPage.clickPlayexchTennisbtn();		
		oneClickPage.getActivestakePopup();
	}

	@Test(priority = 4, dependsOnMethods = { "setOneClickBetSetting", "verifyActiveStake" })
	public void betPlace() throws InterruptedException {
		try {
			oneClickPage.setFirstSecondEventsBets();
		} catch (Exception e) {
			oneClickPage.setThirdForthEventsBets();
		}
//		oneClickPage.setFifthSixEventsBets();
		oneClickPage.getPlayexchBetPlacedMsg();
	}

	@Test(priority = 5, dependsOnMethods = { "setOneClickBetSetting", "verifyActiveStake" })
	public void setOneClickBetSettingOff() throws InterruptedException {
		// oneClickPage.clickOneClickBtnOff();
		oneClickPage.offOneClickBet();
	}

	@Test(priority = 6, dependsOnMethods = { "setOneClickBetSetting", "setOneClickBetSettingOff" })
	public void logOutToWeb() {
		loginPage.clickPlayexchLogOut(driver);
	}

	public void updateNewVersion() {
		loginPage.clickPlayexchUpdate();
	}

//	@Test(priority=2, dependsOnMethods= { "setOneClickBetSetting"})
	public void setStakeValue() throws InterruptedException {
		oneClickPage.setStakesValue("1");
		//oneClickPage.clickAndVerifySaveBtn();
	}

}
