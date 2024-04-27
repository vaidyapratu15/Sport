package com.playexch.module;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.playexch.action.ActionClass;
import com.playexch.library.BaseClass;

public class PlayexchTennisPage extends BaseClass {

	ActionClass action = new ActionClass();

	@FindBy(xpath = "(//a[@role='tab']//following::a[3])[1]")
	private WebElement tennisBtn;

	@FindBy(xpath = "(//div[contains(@class,'text-right')])[1]")//div[contains(@class,'caption px-3')]
	private WebElement backBtn;

	@FindBy(xpath = "//div[contains(text(),'Currently')]")
	private WebElement noMatches1;
	
	@FindBy (xpath="(//div[@class='v-image__image v-image__image--cover'])[4]")
	private WebElement noMatches;

	@FindBy(xpath = "//span[contains(text(),'Match Odds')]")
	private WebElement matchOdds;

	@FindBy(xpath = "//div[contains(@class,'v-sheet theme')]//div[@role='status']")
	private WebElement betPlaceMsg;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[3]")
	private WebElement firstRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[4]")
	private WebElement firstRowLay;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[9]")
	private WebElement secondRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[10]")
	private WebElement secondRowLay;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[3]")
	private WebElement secondEfirstRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[4]")
	private WebElement secondEfirstRowLay;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[9]")
	private WebElement secondEsecondRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[10]")
	private WebElement secondEsecondRowLay;

	@FindBy(xpath = "//div[@class='v-input__slot']//fieldset")
	private WebElement oddvalue;

	@FindBy(xpath = "//div[@class = 'v-text-field__slot']//input[1]")
	private WebElement oddvalue1;

	@FindBy(xpath = "(//div[@class='v-input__slot']//button)[1]")
	private WebElement oddvalueAddBtnL;

	@FindBy(xpath = "(//input[@type='text'])[5]")
	private WebElement stakevalue;

	@FindBy (xpath ="//button[@type='submit']")  //span[text()='Place Bet ']
	private WebElement placeBtn;

	@FindBy(xpath = "//span[text()='Cancel']")
	private WebElement unmatchedCancelBtn;
	
	@FindBy (xpath = "//span[@class='match-title']")
	private  List<WebElement> tennisEvents ;

	public PlayexchTennisPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickPlayexchTennisbtn() {
		tennisBtn.click();
	}

	public void setPlayexchMatch(WebDriver driver) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.textToBePresentInElement(backBtn, "Back"));
			test.info("Match is present");
		} catch (Exception e) {
			test.info("No Match Available");
		}
	}

	public String getPlayexchCurrentlyMatches() {
		try {
			String text = noMatches.getText();
			return text;
		} catch (Exception e) {
		}
		return null;
	}
	
	public void getPlayexchTennisEvents() {
		test.info("Total Events Available : " + tennisEvents.size());
		for(WebElement ele : tennisEvents) {			
			test.info(ele.getText()); }
	}

	public String getPlayexchTennisBetPlaceMsg() {
		String msg = betPlaceMsg.getText();
		return msg;
	}

	public String getPlayexchBackBtn() {
		String text = backBtn.getText();
		return text;
	}

	public void clickPlayexchFHorseBack() {
		try {
			action.explicitWaitClickable(driver, firstRowBack);
			test.info("First Horse Back is clickable");
		} catch (Exception e) {
			test.info("First Horse Back is NOT clickable");
		}
	}

	public void clickPlayexchFHorseLay() {
		try {
			action.explicitWaitClickable(driver, firstRowLay);
			test.info("First Horse Lay is clickable");
		} catch (Exception e) {
			test.info("First Horse Lay is NOT clickable");
		}
	}

	public void clickPlayexchSHorseBack() {
		try {
			action.explicitWaitClickable(driver, secondRowBack);
			test.info("Second Horse Back is clickable");
		} catch (Exception e) {
			test.info("Second Horse Back is NOT clickable");
		}
	}

	public void clickPlayexchSHorseLay() {
		try {
			action.explicitWaitClickable(driver, secondRowLay);
			test.info("Second Horse Lay is clickable");
		} catch (Exception e) {
			test.info("Second Horse Lay is NOT clickable");
		}
	}

	public void clickPlayexchSecondEventFHorseBack() {
		try {
			action.explicitWaitClickable(driver, secondEfirstRowBack);
			test.info("First Horse Back is clickable");
		} catch (Exception e) {
			test.info("First Horse Back is NOT clickable");
		}
	}

	public void clickPlayexchSecondEventFHorseLay() {
		try {
			action.explicitWaitClickable(driver, secondEfirstRowLay);
			test.info("First Horse Lay is clickable");
		} catch (Exception e) {
			test.info("First Horse Back is NOT clickable");
		}
	}

	public void clickPlayexchSecondEventSHorseBack() {
		try {
			action.explicitWaitClickable(driver, secondEsecondRowBack);
			test.info("Second Horse Back is clickable");
		} catch (Exception e) {
			test.info("Second Horse Back is NOT clickable");
		}
	}

	public void clickPlayexchSecondEventSHorseLay() {
		try {
			action.explicitWaitClickable(driver, secondEsecondRowLay);
			test.info("Second Horse Lay is clickable");
		} catch (Exception e) {
			test.info("Second Horse Lay is NOT clickable");
		}
	}

	public void clickPlayexchUnmatchedCancelBtn() {
		unmatchedCancelBtn.click();
	}

	public void getPlayexchOddsValue(String value, WebDriver driver) throws InterruptedException {
		Actions actobj = new Actions(driver);
		actobj.doubleClick(oddvalue).sendKeys(Keys.DELETE).perform();
		Thread.sleep(2000);
		actobj.moveToElement(oddvalue).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		oddvalue1.sendKeys(value);
	}

	public void setPlayexchFOddsValue() {
		for (int i = 1; i <= 100; i = i + 20) {
			oddvalueAddBtnL.click();
		}
	}

	public void getPlayexchStakeValue(String entervalue) {
		stakevalue.sendKeys(entervalue);
		test.info("Stake Value : " +entervalue );
	}

	public void clickPlayexchPlaceBtn() {
		try {
			test.info("Place Bet Button is Enabled : " + placeBtn.isEnabled());
			placeBtn.click();
			action.explicitVisiblility(driver, betPlaceMsg, 10);
			test.info(betPlaceMsg.getText());
		} catch (Exception e) {
		}
	}

}
