package com.playexch.module;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.playexch.action.ActionClass;
import com.playexch.library.BaseClass;
import io.netty.util.internal.ThreadLocalRandom;

public class PlayexchOneClickBetPage extends BaseClass {
	ActionClass action = new ActionClass();
	Actions actobj = new Actions(driver);

	@FindBy(xpath = "(//span[@class='v-btn__content'])[6]") // (//button[@type='button'])[5]
	private WebElement profileBtn;

	@FindBy(xpath = "(//a//div[contains(text(),'Settings')])[1]")
	private WebElement settingBtn;

	@FindBy(xpath = "//input[@role='switch']")
	private WebElement toggleBtn;

	@FindBy(xpath = "(//div[@class='row']//div)[1]//span[text()=' Edit']")
	private WebElement editBtn;

	@FindBy(xpath = "(//div[contains(@class,'v-input__slot')]//div//input)[4]")
	private WebElement stakeValue;

	@FindBy(xpath = "//footer//div[@role='listitem']")
	private WebElement activationText;

	@FindBy(xpath = "(//button[contains(@class,'odds')])[4]")
	private WebElement FirstEFirstHorseBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'odds')])[4]")
	private WebElement secondEFirstHorseLay;

	@FindBy(xpath = ("((//div[contains(@class,'v-expansion-panel')])[9]//button[contains(@class,'odds')])[9]"))
	private WebElement thirdESecondHorseBack;

	@FindBy(xpath = ("((//div[contains(@class,'v-expansion-panel')])[12]//button[contains(@class,'odds')])[10]"))
	private WebElement forthESecondHorseLay;

	@FindBy(xpath = ("((//div[contains(@class,'v-expansion-panel')])[14]//button[contains(@class,'odds')])[3]"))
	private WebElement fifthEFirstHorseBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[18]//button[contains(@class,'odds')])[10]")
	private WebElement sisthESecondHorseLay;

	@FindBy(xpath = ("((//div[contains(@class,'v-expansion-panel')])[20]//button[contains(@class,'odds')])[3]"))
	private WebElement seventhEFirstHorseBack;

	@FindBy(xpath = "//div[contains(@class,'v-sheet theme')]//div[@role='status']")
	private WebElement betPlaceMsg;
	
	@FindBy (xpath="//div//a//span[text()='Edit Stakes']")
	private WebElement editStakeBtn;
	
	@FindBy (xpath="(//div[contains(@class,'v-expansion-panel')])[3]//button | (//div[contains(@class,'v-expansion-panel')])[6]//button")
	private List<WebElement> oddList;   //div//span[contains(@class,'position-relative')]

	@FindBy (xpath="(//button[contains(@class,'black--text')])[2]")
	private WebElement saveBtn;

	@FindBy (xpath="(//button[contains(@class,'black--text')])[1]")
	private WebElement editbtnVisibility;





	public PlayexchOneClickBetPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickPlayexchProfile() {
		try {
			action.explicitWaitClickable(driver, profileBtn);
		} catch (Exception e) {
			action.jSForClick(driver, profileBtn);
		}
	}

	public void clickProfileSettingBtn() {
		action.jSForScroll(driver, settingBtn);
		settingBtn.click();
	}

	public void clickOneClickBettingBtn() {
		try {
			editBtn.click();
			test.info("One-click toggle button is OFF");
		} catch (Exception e) {
			test.info("One-click toggle button is ON");
		}
		action.jSForClick(driver, toggleBtn);
	}

	public void clickOneClickBtnOff() throws InterruptedException {
		action.jSForClick(driver, toggleBtn);
		try {
			activationText.getText();
		} catch (Exception e) {
			test.info("One-click toggle button is OFF");
		}
	}

	public void offOneClickBet() { // on cricket page
		action.jSForClick(driver, toggleBtn);
		action.jSForScroll(driver, profileBtn);
		try {
			activationText.getText();
		} catch (Exception e) {
			test.info("able to OFF one click button");
		}
	}

	public void checkActivationPopup() { // on cricket page
		try {
			action.jSForClick(driver, toggleBtn);
		} catch (Exception e) {
		}
	}
	public void checkToggleBtnOff() {
	    boolean isDisplayed = false;
	    try {
	        isDisplayed = toggleBtn.isDisplayed();
	    } catch (NoSuchElementException e) {
	        // Ignore the exception if the toggle button is not present
	    }

	    if (isDisplayed) {
	        action.jSForClick(driver, toggleBtn);
	        test.pass("Toggle button is off");
	    } else {
	        test.info("Toggle button is not present");
	    }
	}


	public void clickEditBtn() throws InterruptedException { 
		Thread.sleep(3000);
		test.info("Save button is Enabled : " + saveBtn.isEnabled());
	    test.info("Edit Button is Enabled : " + editBtn.isEnabled());
		editBtn.click();
		test.info("Cliked on edit button");
	}	

	public void setStakesValue(String valueStake) throws InterruptedException {
		actobj.moveToElement(stakeValue).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		Thread.sleep(3000);
		stakeValue.sendKeys(valueStake);
		test.info("Stake value : "+ valueStake);
	}

	public void clickOnSaveBtn() {
		test.info("Save button is Enabled : " + saveBtn.isEnabled());
		action.jSForClick(driver, saveBtn);
		test.info("Clicked on save button ");
	}

	public void getActivestakePopup() throws InterruptedException {
		Thread.sleep(3000);
		test.info(activationText.getText());
	}

	public void setFirstSecondEventsBets() {
		try {
			action.explicitWaitClickable(driver, FirstEFirstHorseBack);
			test.info("First event is clickable ");
		} catch (Exception e) {
			action.explicitWaitClickable(driver, secondEFirstHorseLay);
			test.info("Second event is clickable ");
		}
	}

	public void setThirdForthEventsBets() {
		try {
			action.explicitWaitClickable(driver, thirdESecondHorseBack);
			test.info("Third event is clickable");
		} catch (Exception e) {
			action.jSForScrollDown(driver);
			action.explicitWaitClickable(driver, forthESecondHorseLay);
			test.info("Forth event is clickable");
		}
	}

	public void setFifthSixEventsBets() {
		try {
			action.jSForScrollDown(driver);
			action.explicitWaitClickable(driver, fifthEFirstHorseBack);
			test.info("Fifth event clickable");
		} catch (Exception e) {
			action.jSForScrollDown(driver);
			action.explicitWaitClickable(driver, sisthESecondHorseLay);
			test.info("Sis event is clickable");
		}
	}

	public void getPlayexchBetPlacedMsg() {
		action.explicitVisiblility(driver, betPlaceMsg, 10);
		test.info(betPlaceMsg.getText());
	}
	
	public void setEditStake() {
		action.jSForClick(driver, editStakeBtn);
	//	editStakeBtn.click();
		test.info("Clicked on edit stake button");
	}
	
	
	public void verifyToggleBtnOn() {
		String attributeName = "disabled";
		String disabledAttribute = editbtnVisibility.getAttribute(attributeName);
	
		boolean isAttributePresent = disabledAttribute != null;
	
		test.info("Attribute '" + attributeName + "' is present: " + isAttributePresent);
	
		if (isAttributePresent) {
			test.pass("Toggle button is OFF");
		} else {
			test.fail("Toggle button is ON");
			throw new AssertionError("Toggle button is ON, test case failed.");
		}
	}
	
	
	
	public boolean clickOnOddsButton() {
	   
	    int startIndex = 0;
	    int endIndex = 3;

	    int randomIndex = ThreadLocalRandom.current().nextInt(startIndex, Math.min(endIndex + 1, oddList.size()));
	    WebElement randomButton = oddList.get(randomIndex);

	    if (randomButton.isDisplayed()) {
	        try {
	            randomButton.click();
	            test.pass("Clicked on a random button");
	            return true; // Button click successful
	        } catch (Exception e) {
	            test.pass("Failed to click on the random button");
	            throw new RuntimeException("Button click failed");
	        }
	    } else {
	        test.fail("Random button is not visible");
	        throw new RuntimeException("Button not visible"); 
	    }
	}
 
	
}
