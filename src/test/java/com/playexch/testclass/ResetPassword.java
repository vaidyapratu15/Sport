package com.playexch.testclass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class ResetPassword extends BaseTest {

	@Test(priority = 0)
	public void loginToWeb() throws EncryptedDocumentException, IOException, ParseException, InterruptedException {
		driver.get(Utilities.getJsonData("url"));
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl());
		test.info(loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		test.info(loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password")));
		loginPage.clickPlayexchLoginPageloginBtn();
		Thread.sleep(3000);
	}
	
	@Test(priority = 1)
	public void verifyNewPasswordSameAsCurrent() throws IOException, InterruptedException, ParseException {
		Thread.sleep(3000);
		profilePage.clickPlayexchProfile();
		System.out.println("clicked on profile btn");
		changePwdPage.clickPlayexchWebChangePwdBtn();
		test.info("Current password - " + changePwdPage.getPlayexchCurrentPwd(Utilities.getJsonData("password")));
		test.info("New password - " + changePwdPage.getPlayexchNewPwd(Utilities.getJsonData("password")));
		Thread.sleep(3000);
		changePwdPage.getPlayexchNewValidationMsg();
		clickOnChangePwdBtn();
	}

	@Test(priority = 2, dependsOnMethods = "verifyNewPasswordSameAsCurrent")
	public void verifyNewPasswordContainspaces() throws IOException, InterruptedException, ParseException {
		test.info("Current password - " + changePwdPage.getPlayexchCurrentPwd(Utilities.getJsonData("password")));
		changePwdPage.setPlayexchNewSpacePassword();
		Thread.sleep(3000);
		changePwdPage.getPlayexchNewValidationMsg();
		clickOnChangePwdBtn();
	}
	
	@Test(priority = 3, dependsOnMethods = "verifyNewPasswordContainspaces")
	public void verifyLessCharacterPassword() throws IOException, InterruptedException, ParseException {
		test.info("Current password - " + changePwdPage.getPlayexchCurrentPwd(Utilities.getJsonData("password")));
		changePwdPage.setPlayexchNewLessCharPassword();
		Thread.sleep(3000);
		changePwdPage.getPlayexchNewValidationMsg();
		clickOnChangePwdBtn();
	}

	@Test(priority = 4, dependsOnMethods = "verifyLessCharacterPassword")
	public void verifyIncorrectCurrentPassword() throws IOException, InterruptedException, ParseException {
		changePwdPage.setIncorrectCurrentPwd();
		changePwdPage.setPlayexchNewPassword();
		Thread.sleep(4000);
		test.info("ReEnter password - " + changePwdPage.getPlayexchreEnterPwd(Utilities.getJsonData("newPassword")));
		clickOnChangePwdBtn();
		Thread.sleep(3000);
		changePwdPage.getPlayexchChangePwdMsg();
		driver.navigate().back();
	}
	
	@Test(priority = 5, dependsOnMethods = "verifyIncorrectCurrentPassword")
	public void verifyUnmatchedPassword() throws IOException, InterruptedException, ParseException {
		profilePage.clickPlayexchProfile();
		changePwdPage.clickPlayexchWebChangePwdBtn();
		test.info("Current password - " + changePwdPage.getPlayexchCurrentPwd(Utilities.getJsonData("password")));
		test.info("New password - " + changePwdPage.getPlayexchNewPwd(Utilities.getJsonData("newPassword")));
		Thread.sleep(3000);
		changePwdPage.getPlayexchNewValidationMsg();
		changePwdPage.setUnmatchedReEnterPwd();
		Thread.sleep(3000);
		changePwdPage.getPlayexchvalidation();
		clickOnChangePwdBtn();
		driver.navigate().back();
	}
	
	@Test(priority = 6, dependsOnMethods = "verifyUnmatchedPassword")
	public void setCurrentPassword() throws IOException, InterruptedException, ParseException {
		profilePage.clickPlayexchProfile();
		changePwdPage.clickPlayexchWebChangePwdBtn();
		test.info("Current password - " + changePwdPage.getPlayexchCurrentPwd(Utilities.getJsonData("password")));
		clickOnChangePwdBtn();
	}
	
	@Test(priority = 7, dependsOnMethods = "setCurrentPassword")
	public void setNewPassword() throws IOException, InterruptedException, ParseException {
		test.info("New password - " + changePwdPage.getPlayexchNewPwd(Utilities.getJsonData("newPassword")));
		changePwdPage.clickPlayexchShowPwd();
		clickOnChangePwdBtn();
	}

	@Test(priority = 8, dependsOnMethods = "setNewPassword")
	public void setReEnterPassword() throws IOException, InterruptedException, ParseException {

		test.info("ReEnter password - " + changePwdPage.getPlayexchreEnterPwd(Utilities.getJsonData("newPassword")));
		clickOnChangePwdBtn();
		Thread.sleep(3000);
		changePwdPage.getPlayexchChangePwdMsg();
		Utilities.updateJsonData();
		 test.info("updated password data back to the JSON file with each key-value pair");

//	    Assert.assertTrue( true, "Password changed sucessfully"); 
	}
	
	public void clickOnChangePwdBtn() throws InterruptedException {
		try {
			Boolean btn = changePwdPage.clickPlayexchChangePwdBtn();
			test.info("Change Password Button is Enabled :  " + btn);
		} catch (Exception e) {
			test.info("Change Password Button is Not Enabled");
		}

	}

}
