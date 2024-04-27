package com.playexch.module;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.playexch.action.ActionClass;
import com.playexch.library.BaseClass;

public class PlayexchFancyPage extends BaseClass {
	ActionClass action = new ActionClass();

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[1]")
	private WebElement cricketFirstMatch;

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[2]")
	private WebElement cricketSecondMatch;

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[3]")
	private WebElement cricketThirdMatch;

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[4]")
	private WebElement cricketForthMatch;

	@FindBy(xpath = "//div//span[contains(text(),'Fancy')]")
	private WebElement fancyBtn;

	@FindBy(xpath = "((//div[@class='v-window__container'])[2]//button[contains(@class,'grey lighten-2')])[1]")
	private WebElement firstMarketText;

	@FindBy(xpath = "((//div[@class='v-window__container'])[2]//button[contains(@class,'grey lighten-2')])[2]")
	private WebElement secondMarketText;

	@FindBy(xpath = "((//div[contains(@class,'v-window__container')])[2]//button[contains(@class,'btn-odds')])[1]")
	private WebElement fancyNo; // ((//div[contains(@class,'market')])[6]//button[contains(@class,'btn-odds')])[1]

	@FindBy(xpath = "((//div[contains(@class,'v-window__container')])[2]//button[contains(@class,'btn-odds')])[2]")
	private WebElement fancyYes;

	@FindBy(xpath = "((//div[contains(@class,'v-window__container')])[2]//button[contains(@class,'btn-odds')])[5]")
	private WebElement secondNo;

	@FindBy(xpath = "((//div[contains(@class,'v-window__container')])[2]//button[contains(@class,'btn-odds')])[6]")
	private WebElement secondYes;

	@FindBy(xpath = "((//div[contains(@class,'market')])[6]//span[@class='d-block text-center'])[1]")
	private WebElement getNoText;

	@FindBy(xpath = "((//div[contains(@class,'market')])[6]//span[@class='d-block text-center'])[2]")
	private WebElement getYesText;

	@FindBy(xpath = "(//input[@type='text'])[5]")
	private WebElement stakevalue;

	@FindBy(xpath = "(//span[@class='v-btn__content'])[6]")
	private WebElement profileBtn;

	@FindBy(xpath = "//button[@type='submit']") // span[text()='Place Bet ']
	private WebElement placeBtn;

	@FindBy(xpath = "//div[contains(@class,'v-sheet theme')]//div[@role='status']")
	private WebElement betPlaceMsg;

	@FindBy(xpath = "(//a[@role='tab']//following::a[1])[1]")
	private WebElement cricketBtn;

	@FindBy(xpath = "//span[@class='match-title']")
	private List<WebElement> cricketEvents;

	@FindBy(xpath = "((//div[contains(@class,'v-window__container')])[2]//span[contains(@class,'d-block text-center')])[position()=1 or position()=3 or position()=9 or position()=11 or position()=17 or position()=19]")
	private List<WebElement> list; // fancy yes no odds

	

	public PlayexchFancyPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void scrollUpToCricketBtn() {
		action.jSForScroll(driver, cricketBtn);
	}

	public void clickPlayexchCricketbtn() throws InterruptedException {
		try {
			test.info("Cricket btn visible :" + cricketBtn.isEnabled());
			cricketBtn.click();
		} catch (Exception e) {
			action.jSForClick(driver, cricketBtn);
		}
	}

	public void getPlayexchCricketEvents() {
		test.info("Total Events Available : " + cricketEvents.size());
		for (WebElement ele : cricketEvents) {
			test.info(ele.getText());
		}
	}

	public void clickPlayexchCricketFirstMatch() {
		cricketFirstMatch.click();
	}

	public void clickPlayexchCricketSecondMatch() {
		try {
			cricketSecondMatch.click();
		} catch (Exception e) {
			action.jSForScrollDown(driver);
			cricketSecondMatch.click();
		}
	}

	public void clickPlayexchCricketThirdMatch() {
		try {
			cricketThirdMatch.click();
		} catch (Exception e) {
			action.jSForScrollDown(driver);
			cricketThirdMatch.click();
		}
	}

	public void clickPlayexchCricketFourthMatch() {
		try {
			cricketForthMatch.click();
		} catch (Exception e) {
			action.jSForScrollDown(driver);
			cricketForthMatch.click();
		}
	}

	public void setPlayexchPageScrollDown() throws InterruptedException {
		Thread.sleep(4000);
		action.jSForScroll(driver, fancyBtn);
		Thread.sleep(3000);
	}

	public void setPlayexchPageScrollUp() throws InterruptedException {
		action.jSForScroll(driver, profileBtn);
		Thread.sleep(3000);
	}

	public void setPlayexchFancy() throws InterruptedException {
		action.textToBePresentInElement(driver, fancyBtn, "FANCY", 10);
		test.info("Fancy Market is present : " + fancyBtn.isEnabled());
	}

	public void getPlayexchFirstOddStatus() {
		try {
			test.info("First market odds - " + firstMarketText.getText());
		} catch (Exception e) {
		}
	}

	public void getPlayexchSecondOddStatus() {
		try {
			test.info("Second market odds - " + secondMarketText.getText());
		} catch (Exception e) {
		}
	}

	public void clickPlayexchFancyMarketNo() {
		try {
			action.explicitWaitClickable(driver, fancyNo);
			test.info("Fancy NO match odd is clickable");
		} catch (Exception e) {
			test.info("Fancy No match odds is NOT clickable");
		}
	}

	public void clickPlayexchfancyMarketYes() {
		try {
			action.explicitWaitClickable(driver, fancyYes);
			test.info("Fancy Yes match odd is clickable");
		} catch (Exception e) {
			test.info("Fancy Yes match odds is NOT clickable");
		}
	}

	public void getPlayexchStakeValue(String entervalue) {
		stakevalue.sendKeys(entervalue);
		test.info("Stake Value : " + entervalue);
	}

	public void clickPlayexchPlaceBtn() throws InterruptedException {
		test.info("Place Bet Button is Enabled : " + placeBtn.isEnabled());
		try {
			placeBtn.click();
		} catch (Exception e) {
			action.jSForClick(driver, placeBtn);
			action.explicitVisiblility(driver, betPlaceMsg, 10);
			test.info(betPlaceMsg.getText());
		}
	}

	public void setPlayexchFancyYesNoOdds() {
		Random gen = new Random();
		boolean buttonClicked = false;
		int attempts = 0;
		int maxAttempts = 10; // maximum number of attempts to find a clickable button
		long startTime = System.currentTimeMillis();
		long maxTime = 5000; // maximum time to spend trying to find a clickable button (in milliseconds)

		while (!buttonClicked && list.size() > 0 && attempts < maxAttempts
				&& System.currentTimeMillis() - startTime < maxTime) {
			WebElement ele = list.get(gen.nextInt(list.size()));
			try {
				ele.click();
				test.info("Clicked Odd button text: " + ele.getText());
				buttonClicked = true;
			} catch (ElementNotInteractableException e) {
				test.info("Odds Button not interactable: " + ele.getText());
				list.remove(ele);
			}
			attempts++;
		}

		if (!buttonClicked) {
			test.info("All buttons are not interactable.");
			throw new RuntimeException("No clickable button found.");
		}
	}

	// public void setPlayexchFanc() {
	// try {action.textToBePresentInElement(driver, fancyBtn, "FANCY", 10);
	// test.info("Fancy Market is present");}catch(Exception e) {
	// test.info("Fancy Market is NOT present"); }
	// }
}
