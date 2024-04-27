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

public class PlayexchCricketPage extends BaseClass {

	ActionClass action = new ActionClass();

	@FindBy(xpath = "(//a[@role='tab']//following::a[1])[1]")
	private WebElement cricketBtn;

	@FindBy(xpath = "(//div[contains(@class,'text-right')])[1]")//div[contains(@class,'caption px-3')]
	private WebElement backBtn;

	@FindBy(xpath = "//div[contains(text(),'Currently')]")
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
	
	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[1]")
	private WebElement cricketFirstMatch;

	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[2]")
	private WebElement cricketSecondMatch;
	
	@FindBy(xpath = "(//div//button[contains(@class,'v-expansion-panel-header')]//a)[3]")
	private WebElement cricketThirdMatch;
	
	@FindBy (xpath="(((//div[@class='v-window__container'])[1]//div[contains(@class,'market-block')])[1]//button[contains(@class,'btn-odds')])[3]")
    private WebElement cashoutFHorseBack;
	
	@FindBy (xpath="(((//div[@class='v-window__container'])[1]//div[contains(@class,'market-block')])[1]//span[text()='Suspended'])[1]")
	private WebElement matchOddStatus;
	
	@FindBy (xpath="//span[contains(text(),'Match Odds')]")
	private WebElement matchOddsBtn;
	
	@FindBy (xpath = "//span[@class='match-title']")
	private  List<WebElement> cricketEvents ;
	
	@FindBy (xpath="//div[text()='CashOut']")
	private WebElement cashOutBtn;
	
	@FindBy (xpath="((//span[@class='v-btn__content'])[15]//span)[4]")
	private WebElement firstCashoutOdds;
	
	@FindBy (xpath="((//span[@class='v-btn__content'])[22]//span)[4]")
	private WebElement secondCashoutOdds;
	
	@FindBy (xpath="//div[contains(text(),'Stake value should be greater than 1')]")
	private WebElement stakeValuePopup;
	
	@FindBy (xpath="//div[contains(text(),'Back')]")
	private WebElement backText;
	
	public PlayexchCricketPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickPlayexchCricketbtn() {
		cricketBtn.click();
	}
	
	public void getPlayexchCricketEvents() {
		test.info("Total Events Available : " + cricketEvents.size());
		for(WebElement ele : cricketEvents) {			
			test.info(ele.getText()); }
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

	public String getPlayexchCricketBetPlaceMsg() {
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
			test.info("First Horse Lay is NOT clickable");
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
			test.info("Clicked on place bet");
			action.explicitVisiblility(driver, betPlaceMsg, 10);
			test.info(betPlaceMsg.getText());
		} catch (Exception e) {
		}
	}
	
	public void clickCashOutFHorseBack() {      //cashOut
		try {
			cashoutFHorseBack.click();
			test.info("First Horse Back is clickable");
		} catch (Exception e) {
			test.info("First Horse Back is NOT clickable");
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
	
	public void setPlayexchMatchOdds() throws InterruptedException {
		action.textToBePresentInElement(driver, matchOddsBtn, "MATCH ODDS", 10);
		test.info("matchOdds is present : " + matchOddsBtn.isEnabled());
	}

	public void scrollUpToCricketBtn() {
		action.jSForScroll(driver, cricketBtn);
	}
	
	public void getMatchOddsStatus() {
		try{test.info("Match Odds : " + matchOddStatus.getText());}catch(Exception e) { }
	}
	
	
	
	
	

}
