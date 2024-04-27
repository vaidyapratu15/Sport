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

public class PlayexchBookmakerBetsPage extends BaseClass {
	ActionClass action = new ActionClass();
	// Scanner sa = new Scanner(System.in);

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[1]")
	private WebElement cricketFirstMatch;

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[2]")
	private WebElement cricketSecondMatch;

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[3]")
	private WebElement cricketThirdMatch;

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[4]")
	private WebElement cricketForthMatch;

	@FindBy(xpath = "//div[contains(text(),'Bookmaker')]")
	private WebElement bookmakerBtn;

	@FindBy(xpath = "((//div[@class='v-window__container'])[1]//button[contains(@class,'grey lighten-2')])[1]")
	private WebElement firstHorseText;

	@FindBy(xpath = "((//div[@class='v-window__container'])[1]//button[contains(@class,'grey lighten-2')])[2]")
	private WebElement secondHorseText;

	@FindBy(xpath = "((//div[contains(@class,'market')])[5]//button[contains(@class,'odds')])[3]")
	private WebElement firstHorseBack;

	@FindBy(xpath = "((//div[contains(@class,'market')])[5]//button[contains(@class,'odds')])[9]")
	private WebElement secondHorseBack;// ((//div[@class='pa-0 odds-category'])[2]//button[contains(@class,'odds')])[4]

	@FindBy(xpath = "((//div[contains(@class,'market')])[5]//button[contains(@class,'odds')])[4]")
	private WebElement firstHorseLay;

	@FindBy(xpath = "((//div[contains(@class,'market')])[5]//button[contains(@class,'odds')])[10]")
	private WebElement secondHorseLay;

	@FindBy(xpath = "(//div[@class='v-input__slot']//button)[1]")
	private WebElement oddvalueAddBtnL;

	@FindBy(xpath = "(//input[@type='text'])[5]")
	private WebElement stakevalue;

	@FindBy(xpath = "//div[@class = 'v-text-field__slot']//input[1]")
	private WebElement oddvalue;

	@FindBy(xpath = "(//span[@class='v-btn__content'])[6]")
	private WebElement profileBtn;

	@FindBy(xpath = "//button[@type='submit']") // span[text()='Place Bet ']
	private WebElement placeBtn;

	@FindBy(xpath = "//div[contains(@class,'v-sheet theme')]//div[@role='status']")
	private WebElement betPlaceMsg;

	@FindBy(xpath = "(//a[@role='tab']//following::a[1])[1]")
	private WebElement cricketBtn;

	@FindBy(xpath = "((//div[contains(@class,'market')])[5]//button[contains(@class,'odds')])[3] | "
			+ "((//div[contains(@class,'market')])[5]//button[contains(@class,'odds')])[4] | "
			+ "((//div[contains(@class,'market')])[5]//button[contains(@class,'odds')])[9] | "
			+ "((//div[contains(@class,'market')])[5]//button[contains(@class,'odds')])[10]")
	private List<WebElement> oddslist; // not using

	@FindBy(xpath = "(//div[@class='pa-0 odds-category'])[2]//button[contains(@class,'btn-odds')][position()=3or position()=4]")
	private List<WebElement> list; // bookmaker BACK LAY odds

	@FindBy(xpath = "//span[@class='match-title']")
	private List<WebElement> cricketEvents;

	public PlayexchBookmakerBetsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickPlayexchCricketFirstMatch() {
		cricketFirstMatch.click();
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

	public void scrollUpToCricketBtn() {
		action.jSForScroll(driver, cricketBtn);
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
		Thread.sleep(3000);
		action.jSForScroll(driver, bookmakerBtn);
		Thread.sleep(2000);
	}

	public void setPlayexchPageScrollUp() throws InterruptedException {
		action.jSForScroll(driver, profileBtn);
		Thread.sleep(3000);
	}

	public void setPlayexchBookmaker() throws InterruptedException {
		action.textToBePresentInElement(driver, bookmakerBtn, "Bookmaker", 10);
		test.info("Bookmaker is present : " + bookmakerBtn.isDisplayed());
	}

	public void getPlayexchFirstHorseOddStatus() {
		try {
			test.info("First horse Odds - " + firstHorseText.getText());
		} catch (Exception e) {
		}
	}

	public void getPlayexchSecondHorseOddStatus() {
		try {
			test.info("Second horse Odds - " + secondHorseText.getText());
		} catch (Exception e) {
		}
	}

	public void clickPlayexchFirstHorseBack() {
		try {
			action.explicitVisiblility(driver, firstHorseBack, 20);
			firstHorseBack.click();
			test.info("First Back Odds Visible");
			if (firstHorseBack.isEnabled()) {
				test.info("First Horse Back Odds  : " + firstHorseBack.getText());
			}
		} catch (Exception e) {
			test.info("First Back Odds NOT Visible");
		}
	}

	public void clickPlayexchSecondHorseBack() {
		try {
			action.explicitVisiblility(driver, secondHorseBack, 20);
			secondHorseBack.click();
			test.info("Second Back Odds Visible");
			if (secondHorseBack.isEnabled()) {
				test.info("Second Horse Back Odds  : " + secondHorseBack.getText());
			}
		} catch (Exception e) {
			test.info("Second Back Odds NOT Visible");
		}
	}

	public void clickPlayexchFirstHorseLay() {
		try {
			action.explicitVisiblility(driver, firstHorseLay, 20);
			firstHorseLay.click();
			test.info("First Lay Odds Visible");
			if (firstHorseLay.isEnabled()) {
				test.info("First Horse Lay Odds  : " + firstHorseLay.getText());
			}
		} catch (Exception e) {
			test.info("First Lay Odds NOT Visible");
		}
	}

	public void clickPlayexchSecondHorseLay() {
		try {
			action.explicitVisiblility(driver, secondHorseLay, 20);
			secondHorseLay.click();
			test.info("Second Lay Odds Visible");
			if (secondHorseLay.isEnabled()) {
				test.info("Second Horse Lay Odds  : " + secondHorseLay.getText());
			}
		} catch (Exception e) {
			test.info("Second Lay Odds NOT Visible");
		}
	}

	public void setPlayexchFOddsValue() {
		for (int i = 1; i <= 100; i = i + 20) {
			oddvalueAddBtnL.click();
		}
	}

	public void getPlayexchStakeValue(String entervalue) {
		stakevalue.sendKeys(entervalue);
		test.info("Stake Value : " + entervalue);
	}

	public void clickPlayexchPlaceBtn() throws InterruptedException {
			test.info("Place Bet Button is Enabled : " + placeBtn.isEnabled());
			try { placeBtn.click(); // Thread.sleep(3000);
			}catch(Exception e){
				action.jSForClick(driver, placeBtn);
			}
			action.explicitVisiblility(driver, betPlaceMsg, 10);
			test.info(betPlaceMsg.getText());
	}

	public void setPlayexchBookmakerOdd() {
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
		}
	}

	public void setPlayexchBookmakerOdds() {
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

}
