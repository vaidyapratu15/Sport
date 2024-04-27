package com.playexch.testclass;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.playexch.action.API;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;
import com.playexch.testlibrary.Log4j;

public class LoginTestCases extends BaseTest {
	
	@Test(priority = 1)
	public void validUsername_InavalidPassword() throws IOException, InterruptedException, ParseException {
		driver.get(Utilities.getJsonData("url"));
		setNotification();
		Log4j.log("url :" + driver.getCurrentUrl());	
		loginPage.clickPlayexchLoginPagelogin();
		test.info("Username :"+loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username1")));
		loginPage.getPlayexchLoginPagePwd();
		verifyLogin();	
	}

	@Test(priority = 2)
	public void invalidUsername_InavalidPassword() throws IOException, InterruptedException, ParseException {
		setNotification();
		loginPage.clearTextFiled();
		loginPage.getPlayexchLoginPageUn();
		loginPage.getPlayexchLoginPagePwd();
		verifyLogin();
	}

	@Test(priority = 3)
	public void invalidUsername_ValidPassword() throws IOException, InterruptedException, ParseException {
		setNotification();
		loginPage.clearTextFiled();
		loginPage.getPlayexchLoginPageUn();
		test.info(loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password")));
		verifyLogin();
	}

	@Test(priority = 4)
	public void validUsername_Password()
		throws EncryptedDocumentException, IOException, InterruptedException, ParseException {
		setNotification();
		test.info(driver.getCurrentUrl());
		loginPage.clearTextFiled();
		test.info(loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		Log4j.log("username :" + (Utilities.getJsonData("username")));
		Log4j.log("password :" + (Utilities.getJsonData("password")));
		test.info(loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password")));
		loginPage.clickPlayexchLoginPageloginBtn();
		Thread.sleep(5000);
		test.info(loginPage.getPlayexchLoginPageWebMsg());
	
	}
   
	@Test(priority = 5)
	public void StoredLocalStorageData() throws InterruptedException, IOException, ParseException{
        	loginPage.verifyAccessToken();
			loginPage.getBaseURL();
			loginPage.getDeviceID();
			loginPage.getAccessToken();
	}

	@Test(priority = 6)
	public void getSaveIDResponse() throws Exception{
		String url = Utilities.getLocalStorageData("baseUrl");
		String deviceId = Utilities.getLocalStorageData("deviceID");
		String token = Utilities.getLocalStorageData("token");

		test.info(API.getPostResponce(url, token, deviceId));
	}


	@Test(priority = 6, dependsOnMethods = "validUsername_Password")
	public void logOutToWeb() throws InterruptedException, IOException, ParseException {
		loginPage.clickPlayexchUpdate();
		loginPage.clickPlayexchLogOut(driver);
		loginPage.verifyAccessToken();
	}

	public void setNotification() {
		loginPage.clickPlayexchUpdate();
		loginPage.clickPlayexchSubscribe();
	}

	public void verifyLogin() throws InterruptedException, IOException, ParseException {
		loginPage.clickPlayexchLoginPageloginBtn();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.className("v-snack__content")))));
		String actual = loginPage.getPlayexchLoginPageWebMsg();
		test.info(actual);
		String expected =Utilities.getJsonData("successufulMsg");
		Assert.assertNotEquals(actual, expected);
		soft.assertAll();
	}

	/*
	 * @Test(priority = 1) public void validUsername_InavalidPassword() throws
	 * IOException, InterruptedException { setNotification();
	 * loginPage.clickPlayexchLoginPagelogin();
	 * test.info(loginPage.getPlayexchLoginPageUn(Utilities.getTestData(0, 0)));
	 * test.info(loginPage.getPlayexchLoginPagePwd(Utilities.getTestData(0, 1)));
	 * verifyLogin(); }
	 * 
	 * @Test(priority = 2) public void invalidUsername_InavalidPassword() throws
	 * IOException, InterruptedException { setNotification();
	 * loginPage.ClearTextFiled();
	 * test.info(loginPage.getPlayexchLoginPageUn(Utilities.getTestData(1, 0)));
	 * test.info(loginPage.getPlayexchLoginPagePwd(Utilities.getTestData(1, 1)));
	 * verifyLogin(); }
	 * 
	 * @Test(priority = 3) public void invalidUsername_ValidPassword() throws
	 * IOException, InterruptedException { setNotification();
	 * loginPage.ClearTextFiled();
	 * test.info(loginPage.getPlayexchLoginPageUn(Utilities.getTestData(2, 0)));
	 * test.info(loginPage.getPlayexchLoginPagePwd(Utilities.getTestData(2, 1)));
	 * verifyLogin(); }
	 * 
	 * @Test(priority = 6) public void validUsername_Password() throws
	 * EncryptedDocumentException, IOException, InterruptedException {
	 * setNotification(); test.info(driver.getCurrentUrl());
	 * loginPage.ClearTextFiled();
	 * test.info(loginPage.getPlayexchLoginPageUn(Utilities.getPropertyData(
	 * "username"))); test.info(
	 * loginPage.getPlayexchLoginPagePwd(Utilities.getPropertyData("password")));
	 * loginPage.clickPlayexchLoginPageloginBtn(); Thread.sleep(5000);
	 * test.info(loginPage.getPlayexchLoginPageWebMsg()); }
	 */

}
