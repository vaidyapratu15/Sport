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

public class PlayexchLoginPage extends BaseClass {
	ActionClass action = new ActionClass();
	Actions actobj = new Actions(driver);
	 RandomGenerator random = new RandomGenerator();

	@FindBy(xpath = "//span[contains(text(),'Update')]")
	private WebElement updateBtn;

	@FindBy(xpath = "//button[@id = 'onesignal-slidedown-cancel-button']")
	private WebElement subscribeBtn;

	@FindBy(xpath = "//span[contains(text(),'Login')]")
	private WebElement login;

	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "sign-in")
	private WebElement signin;

	@FindBy(xpath = "//span[contains(text(),'Login')]")
	private WebElement loginBtn;

	@FindBy(xpath = "//div[@role='status']")
	private WebElement errorMsg;

	@FindBy(xpath = "//div[@role='status']")
	private WebElement MsgOnWeb;

	@FindBy(xpath = "//div[contains(text(),'Invalid')]")
	private WebElement invalidMsg;

	@FindBy(xpath = "//div[contains(text(),'Logged')]")
	private WebElement successfulMsg;

	@FindBy(xpath = "(//span[@class='v-btn__content'])[6]")
	private WebElement profileBtn;

	@FindBy(xpath = "//span[text()='Logout']")
	private WebElement logOutBtn;

	@FindBy(id = "logout")
	private WebElement logOut;

	@FindBy(xpath = "//div[@class = 'v-input--selection-controls__ripple']")
	private WebElement checkBoxBtn;

	@FindBy(xpath = "//button//span[contains(text(),'Accept and Continue')]")
	private WebElement acceptNContinue;

	public PlayexchLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickBetLimitRuleAcceptance() {
		try {
			checkBoxBtn.click();
			acceptNContinue.click();
		} catch (Exception e) {
		}
	}

	public void clickPlayexchUpdate() {
		try {
			updateBtn.click();
		} catch (Exception e) {
		}
	}

	public void clickPlayexchSubscribe() {
		try {
			subscribeBtn.click();
		} catch (Exception e) {
		}
	}

	public void clickPlayexchLoginPagelogin() {
		login.click();
	}

	public void verifyAccessToken() throws InterruptedException, IOException, ParseException {
		Thread.sleep(4000);
		test.info("Token : " + action.getAccessToken(driver));
	}

	public String getPlayexchLoginPageUn(String UN) {
		username.sendKeys(UN);
		return UN;
	}

	public String getPlayexchLoginPagePwd(String PWD) {
		password.sendKeys(PWD);
		return PWD;
	}
	
	public void getPlayexchLoginPageUn() {
		String name =random.generateRandomName(8);
		username.sendKeys(name);
		test.info("Username -" + name);
		
	}

	public void getPlayexchLoginPagePwd() {
		String pwd = random.generateRandomStrongPassword();
		password.sendKeys(pwd);
		test.info("Password -" + pwd);
		
	}

	public void clickPlayexchLoginPageloginBtn() {
		loginBtn.click();
	}

	public void getBackendLoginPageUn(String UN, WebDriver driver) {
		Actions actobj = new Actions(driver);
		actobj.doubleClick(username).sendKeys(Keys.DELETE).perform();
		username.sendKeys(UN);
	}

	public void getBackendLoginPagePwd(String PWD, WebDriver driver) {
		Actions actobj = new Actions(driver);
		actobj.doubleClick(password).sendKeys(Keys.DELETE).perform();
		password.sendKeys(PWD);
	}

	public void getBackendLoginPagesignin() {
		signin.click();
	}

	public String getPlayexchLoginPagErrorMsg() {
		String msg = errorMsg.getText();
		return msg;
	}

	public String getPlayexchLoginPageSuccessMsg() {
		String msg = successfulMsg.getText();
		return msg;
	}

	public String getPlayexchLoginPageWebMsg() {
		String msg = MsgOnWeb.getText();
		return msg;
	}

	public String getPackendLoginPageWebMsg() {
		String msg = invalidMsg.getText();
		return msg;
	}

	public void clickPlayexchLogOut(WebDriver driver) {
		try {
			profileBtn.click();
			action.jSForScroll(driver, logOutBtn);
			logOutBtn.click();
			test.info("Successfully logged out");
		} catch (Exception e) {
			test.info("Unable to logged out");
		}
	}

	public void clickBackendLogOut(WebDriver driver) {
		action.jSForScroll(driver, logOutBtn);
		logOut.click();
	}

	public void clearTextFiled() throws InterruptedException {
		Thread.sleep(1000);
		actobj.moveToElement(username).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		actobj.moveToElement(password).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		Thread.sleep(1000);
	}

	public void getAccessToken() throws InterruptedException, IOException, ParseException {
        String getToken = action.getAccessToken(driver);
	//	test.info("Token : " +getToken);
		Utilities.storeData("token",getToken,"/Json/UIJsonData/localStorage.json");  
	    test.info("Token Stored in .json file");
	}

	public void getBaseURL() throws InterruptedException, IOException, ParseException {
        String getURL = action.getUserUrl(driver);
	//	test.info("Token : " +getToken);
		Utilities.storeData("baseUrl",getURL,"/Json/UIJsonData/localStorage.json");  
	    test.info("URL Stored in .json file");
	}

	public void getDeviceID() throws InterruptedException, IOException, ParseException {
        String getToken = action.getDeviceID(driver);
	//	test.info("Token : " +getToken);
		Utilities.storeData("deviceID",getToken,"/Json/UIJsonData/localStorage.json");  
	    test.info("DeviceID Stored in .json file");
	}

}
