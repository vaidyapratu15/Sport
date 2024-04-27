package com.playexch.module;

import java.time.Duration;
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

public class PlayexchSoccerPage extends BaseClass {

	ActionClass action = new ActionClass();

	@FindBy(xpath = "//a[@role='tab']//following::a[2]")
	private WebElement soccerBtn;

	@FindBy(xpath = "//div[@class='v-overlay__scrim']")
	private WebElement oddsTable; // for visiblility

	@FindBy(xpath = "(//div[contains(@class,'text-right')])[1]")//div[contains(@class,'caption px-3')]
	private WebElement backBtn;

	@FindBy(xpath = "//div[contains(@class,'v-sheet theme')]//div[@role='status']")
	private WebElement betPlaceMsg;

	@FindBy(xpath = "//div[contains(text(),'Currently')]")
	private WebElement noMatches;

	@FindBy(xpath = "//span[contains(text(),'Match Odds')]")
	private WebElement matchOdds;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[3]")
	private WebElement firstRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[4]")
	private WebElement firstRowLay;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[9]")
	private WebElement secondRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[10]")
	private WebElement secondRowLay;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[15]")
	private WebElement thirdRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[16]")
	private WebElement thirdRowLay;

	@FindBy(xpath = "//div[@class = 'v-text-field__slot']//input[1]")
	private WebElement oddvalue1;

	@FindBy(xpath = "//div[@class='v-input__slot']//fieldset")
	private WebElement oddvalue;

	@FindBy(xpath = "(//div[@class='v-input__slot']//button)[1]")
	private WebElement oddvalueAddBtnL;

	@FindBy(xpath = "(//input[@type='text'])[5]")
	private WebElement stakevalue;

	@FindBy (xpath ="//button[@type='submit']")  //span[text()='Place Bet ']
	private WebElement placeBtn;

	@FindBy(xpath = "//span[text()='Cancel']")
	private WebElement unmatchedCancelBtn;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[3]")
	private WebElement secondEventFirstRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[4]")
	private WebElement secondEventFirstRowLay;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[9]")
	private WebElement secondEventSecondRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[10]")
	private WebElement secondEventSecondRowLay;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[15]")
	private WebElement secondEventThirdRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[16]")
	private WebElement secondEventThirdRowLay;

	public PlayexchSoccerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickPlayexchOnSoccer() {
		soccerBtn.click();
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

	public String getPlayexchBackBtn() {
		String text = backBtn.getText();
		return text;
	}

	public String getPlayexchSoccerBetPlaceMsg() {
		String msg = betPlaceMsg.getText();
		return msg;
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

	public void clickPlayexchTHorseBack() {
		try {
			action.explicitWaitClickable(driver, thirdRowBack);
			test.info("Draw Back is clickable");
		} catch (Exception e) {
			test.info("Draw Back is NOT clickable");
		}
	}

	public void clickPlayexchTHorseLay() {
		try {
			action.explicitWaitClickable(driver, thirdRowLay);
			test.info("Draw Lay is clickable");
		} catch (Exception e) {
			test.info("Draw Lay is NOT clickable");
		}
	}

	public void clickPlayexchUnmatchedCancelBtn() {
		unmatchedCancelBtn.click();
	}

	public void getPlayexchOddsValue(String value, WebDriver driver) throws InterruptedException {
		Actions actobj = new Actions(driver);
		actobj.doubleClick(oddvalue).sendKeys(Keys.DELETE).perform();
		actobj.moveToElement(oddvalue).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		oddvalue1.sendKeys(value);
	}

	public void getPlayexchFOddsValue() {
		for (int i = 1; i <= 100; i = i + 20) {
			oddvalueAddBtnL.click();
		}
	}

	public void getPlayexchStakeValue(String entervalue) {
		stakevalue.sendKeys(entervalue);
		test.info("Stake Value : " +entervalue );
	}

	public void verifyPlayexchPlacBetsPopup() {
		try {
			action.explicitVisiblility(driver, betPlaceMsg, 10);
			test.info(betPlaceMsg.getText());
		} catch (Exception e) {
		}
	}

	public void clickPlayexch2EventFHorseBack() {
		try {
			action.explicitWaitClickable(driver, secondEventFirstRowBack);
			test.info("First Horse Back is clickable");
		} catch (Exception e) {
			test.info("First Horse Back is NOT clickable");
		}
	}

	public void clickPlayexch2EventFHorseLay() {
		try {
			action.explicitWaitClickable(driver, secondEventFirstRowLay);
			test.info("First Horse Lay is clickable");
		} catch (Exception e) {
			test.info("First Horse Lay is NOT clckable");
		}
	}

	public void clickPlayexch2EventSHorseBack() {
		try {
			action.explicitWaitClickable(driver, secondEventSecondRowBack);
			test.info("Second Horse Back is clickable");
		} catch (Exception e) {
			test.info("Second Horse Back is NOT clickable");
		}
	}

	public void clickPlayexch2EventSHorseLay() {
		try {
			action.explicitWaitClickable(driver, secondEventSecondRowLay);
			test.info("Second Horse Lay is clickable");
		} catch (Exception e) {
			test.info("Second Horse Lay is NOT clickable");
		}
	}

	public void clickPlayexch2EventTHorseBack() {
		try {
			action.explicitWaitClickable(driver, secondEventThirdRowBack);
			test.info("Draw Back is clickable");
		} catch (Exception e) {
			test.info("Draw Back is NOT clickable");
		}
	}

	public void clickPlayexch2EventTHorseLay() {
		try {
			action.explicitWaitClickable(driver, secondEventThirdRowLay);
			test.info("Draw Lay is clickable");
		} catch (Exception e) {
			test.info("Draw Lay is NOT clickable");
		}
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
