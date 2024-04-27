package com.playexch.testclass;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;
import com.playexch.testlibrary.DataProviderClass;

public class LoginDataProviderTestCases extends BaseTest {
	
	@Test(priority = 0)
	public void getPlayexchUrl() throws IOException, ParseException {
		driver.get(Utilities.getJsonData("url"));
	}

	@Test(priority = 1, dataProvider = "data", dataProviderClass = DataProviderClass.class)
	public void loginToWebNegativeTc(Map<String, String> map) throws InterruptedException, IOException, ParseException {
		loginPage.clickPlayexchUpdate();
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl());
		loginPage.clearTextFiled();
		test.info(loginPage.getPlayexchLoginPageUn(map.get("Username")));
		test.info(loginPage.getPlayexchLoginPagePwd(map.get("Password")));
		loginPage.clickPlayexchLoginPageloginBtn();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.className("v-snack__content")))));
		String actual1 = loginPage.getPlayexchLoginPageWebMsg();
		test.log(Status.INFO, actual1);
		String expected1 = Utilities.getJsonData("successufulMsg");
		soft.assertNotEquals(actual1, expected1);
		soft.assertAll();
	}

	@Test(priority = 2)
	public void loginToWebPositiveTc()
			throws EncryptedDocumentException, IOException, InterruptedException, ParseException {
		loginPage.clickPlayexchUpdate();
		loginPage.clearTextFiled();
		test.info(loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		test.info(loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password")));
		loginPage.clickPlayexchLoginPageloginBtn();
		Thread.sleep(5000);
		String actual = loginPage.getPlayexchLoginPageWebMsg();
		test.info(actual);
	}

	@Test(priority = 3)
	public void logOutToWeb() {
		loginPage.clickPlayexchUpdate();
		loginPage.clickPlayexchLogOut(driver);
	}

}
