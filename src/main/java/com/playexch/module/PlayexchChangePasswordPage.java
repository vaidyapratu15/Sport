package com.playexch.module;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.playexch.action.ActionClass;
import com.playexch.action.RandomGenerator;
import com.playexch.library.BaseClass;
import com.playexch.library.Utilities;

public class PlayexchChangePasswordPage extends BaseClass {
	ActionClass action = new ActionClass();
	Actions actobj = new Actions(driver);
    RandomGenerator random = new RandomGenerator();
	
	@FindBy(xpath = "(//input[@type='password'])[1]")
	private WebElement currentPwd;

	@FindBy(id = "newPassword")
	private WebElement newPwd;

	@FindBy(xpath = "(//button[@type='button'])[8]")
	private WebElement showNewPwd;

	@FindBy(xpath = "(//div[contains(@class,'v-text-field--is-booted')])[4]//input") // (//form[@class='v-form']//input[@type='text'])[3]
	private WebElement reEnterPwd;

	@FindBy(xpath = "//span[contains(text(),'Change Password')]")
	private WebElement changePwdBtn;

	@FindBy(xpath = "//div[contains(text(),'Change Password')]")
	private WebElement webChangePwdBtn;

	@FindBy(xpath = "//div[@role='status']") // div[@role='status']
	private WebElement statusChangePwd; // Password changed sucessfully

	@FindBy(xpath = "//div[@role='alert']")
	private WebElement newValidation;

	@FindBy(xpath = "//div[@role='alert']") // (//div[@role='alert'])[2]
	private WebElement reValidationMsg;

	@FindBy(xpath = "//div[text()='This password  did not match']")
	private WebElement reValidationdidnotMatch;

	public PlayexchChangePasswordPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickPlayexchShowPwd() {
		showNewPwd.click();
	}

	public boolean clickPlayexchChangePwdBtn() {
		changePwdBtn.click();
		boolean text = changePwdBtn.isEnabled();
		return text;
	}
	public void clickPlayexchWebChangePwdBtn() {
		webChangePwdBtn.click();
	}
	public void getPlayexchChangePwdMsg() throws InterruptedException {
		try {
			test.info(statusChangePwd.getText());
		} catch (Exception e) {
		}
	}
	public void getPlayexchNewValidationMsg() throws IOException, InterruptedException {
		test.info(newValidation.getText());
	}

	public void getPlayexchvalidationMsg() throws IOException, InterruptedException {
		test.info(reValidationMsg.getText());
	}
	public void getPlayexchvalidation() throws IOException, InterruptedException {
		test.info(reValidationdidnotMatch.getText());
	}
	public String getPlayexchCurrentPwd(String CPwd) {
		actobj.doubleClick(currentPwd).sendKeys(Keys.DELETE).perform();
		currentPwd.sendKeys(CPwd);
		return CPwd;
	}
	public String getPlayexchNewPwd(String newpwd) {
		actobj.doubleClick(newPwd).sendKeys(Keys.DELETE).perform();
		newPwd.sendKeys(newpwd);
		return newpwd;
	}
	public String getPlayexchreEnterPwd(String reEnterpwd) {
		actobj.doubleClick(reEnterPwd).sendKeys(Keys.DELETE).perform();
		reEnterPwd.sendKeys(reEnterpwd);
		return reEnterpwd;
	}
	
	///////////////////////////////////////////////////////////////////
	
	public void setPlayexchNewSpacePassword() {
		action.clearTextField(driver, newPwd);
		String spacePwd = random.generateRandomSpacePassword();
	    newPwd.sendKeys(spacePwd);
	    test.info("New password - " + spacePwd);	
	}
	public void setPlayexchNewLessCharPassword() {
		action.clearTextField(driver, newPwd);
		String lessChar = random.generateRandomWeakPassword();
	    newPwd.sendKeys(lessChar);
	    test.info("Less character password - " + lessChar);	
	}
	public void setIncorrectCurrentPwd() {
		action.clearTextField(driver, currentPwd);
		String Pwd = random.generateRandomStrongPassword();
		currentPwd.sendKeys(Pwd);
		test.info("Incorrect Current password - " + Pwd);	
	}
	public void setPlayexchNewPassword() throws IOException, ParseException {
		action.clearTextField(driver, newPwd);
		String newPass = random.generatePassword();
	    newPwd.sendKeys(newPass);
	    test.info("New password - " + newPass);	
	    Utilities.storeData("newPassword",newPass, "/Json/UIJsonData/data.json");  
	    test.info("New password Stored in .json file");
	}
	public void setUnmatchedReEnterPwd() {
		action.clearTextField(driver, reEnterPwd);
		String Pwd = random.generateRandomStrongPassword();
		reEnterPwd.sendKeys(Pwd);
		test.info("ReEnter password - " + Pwd);	
	}
	

}
