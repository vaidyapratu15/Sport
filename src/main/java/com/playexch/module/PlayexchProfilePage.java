package com.playexch.module;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.FindBy;
import com.playexch.action.RandomGenerator;
import com.playexch.action.ActionClass;

import com.playexch.library.BaseClass;
import com.playexch.library.JsonUtility;
import com.playexch.library.Utilities;


public class PlayexchProfilePage extends BaseClass {
	ActionClass action = new ActionClass();
    RandomGenerator random = new RandomGenerator();
	Properties props = new Properties();

	@FindBy(xpath = "(//span[@class='v-btn__content'])[6]") // (//button[@type='button'])[5]
	private WebElement profileBtn;

	@FindBy(xpath = "(//div[contains(@class,' v-list--dense')]//div)[4]")
	private WebElement availableCredit;

	@FindBy(xpath = "(//div[contains(@class,' v-list--dense')]//div)[7]")
	private WebElement CreditLimit;

	@FindBy(xpath = "(//div[contains(@class,' v-list--dense')]//div)[10]")
	private WebElement winnings;

	@FindBy(xpath = "(//div[contains(@class,' v-list--dense')]//div)[13]")
	private WebElement netExposure;

	@FindBy(xpath = "(//button[@type='button']//span)[1]") // (//span[contains(@class,'content')]//i)[1]
	private WebElement closeBtn;

	@FindBy(xpath = "//div//span[@class='match-title']")
	private List<WebElement> eventNames;

	@FindBy(xpath = "//span[@class='v-chip__content']")
	private List<WebElement> matchStatus;

	@FindBy(xpath = "(//div[contains(@class,'radius')])[2]")
	private WebElement firstEventlivepositionName;

	@FindBy(xpath = "(//div[contains(@class,'radius')])[4]")
	private WebElement SecondEventlivepositionName;

	@FindBy(xpath = "(//div[contains(@class,'radius')])[6]")
	private WebElement thirdEventlivepositionName;

	@FindBy(xpath = "(//div[contains(@class,'radius')])[8]")
	private WebElement forthEventlivepositionName;

	@FindBy(xpath = "(//div[contains(@class,'v-list-item__titl')])[3]")
	private WebElement vAvailable;

	@FindBy(xpath = "(//div[contains(@class,'v-list-item__titl')])[4]")
	private WebElement vCredit;

	@FindBy(xpath = "(//div[contains(@class,'v-list-item__titl')])[6]")
	private WebElement vnetExposure;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[4]//button[contains(@class,'grey')])[1]")
	private WebElement FRowSuspended;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[4]//button[contains(@class,'grey')])[2]")
	private WebElement SRowSuspended;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'grey')])[1]")
	private WebElement secondFRowSuspended;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[5]//button[contains(@class,'grey')])[2]")
	private WebElement secondSRowSuspended;
	
	@FindBy (xpath="(//div[contains(@class,'v-text-field')])[13]")
	private WebElement maxText;
	
	@FindBy (xpath ="//button[@type='submit']")  //span[text()='Place Bet ']
	private WebElement placeBtn;
	
	@FindBy (xpath="//div[@aria-live='polite']") //div[contains(text(),'Insufficient credit limit - 1')]
	private WebElement insufficientLimitPopup;
	
	@FindBy(xpath = "(//input[@type='text'])[5]")
	private WebElement stakevalue;

	public PlayexchProfilePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickPlayexchProfile() {
		profileBtn.click();
	}

	public String getPlayexchavailableBlns() {
		String text = availableCredit.getText();
		return text;
	}

	public String getPlayexchCreditLimit() {
		String text = CreditLimit.getText();
		return text;
	}

	public String getPlayexchWinnings() {
		String text = winnings.getText();
		return text;
	}

	public String getPlayexchNetExposure() {
		String text = netExposure.getText();
		return text;
	}

	public void clickPlayexchCloseBtn() {
		action.jSForClick(driver, closeBtn); // closeBtn.click();
	}

	public void getPlayexchEventsName(String events) {
		test.info("Get event size : " + eventNames.size());
		for (WebElement a : eventNames) {
			test.info(a.getText());
		}

		for (int i = 0; i < eventNames.size(); i++) {
			if (eventNames.get(i).getText().equalsIgnoreCase(events)) {
				eventNames.get(i).click();
				test.info("clicked");
				break;
			}
		}
	}

	public void getMatchStatus() {
		for (WebElement a : matchStatus) {
			test.info(a.getText());
		}
	}

	public void getPlayexchFirstEventName() {
		test.info("First Event Name- " + firstEventlivepositionName.getText());
	}

	public void getPlayexchSecondEventName() {
		test.info("Second Event Name- " + SecondEventlivepositionName.getText());
	}

	public void getPlayexchThirdEventName() {
		test.info("Third Event Name- " + thirdEventlivepositionName.getText());
	}

	public void getPlayexchFourthEventName() {
		test.info("Forth Event Name- " + forthEventlivepositionName.getText());
	}

	public void setFirstMatchOddStatus() {
		try {
			test.info("First event FHorseOdds Status : " + FRowSuspended.getText());
			test.info("First event SHorseOdds Status : " + SRowSuspended.getText());
		} catch (Exception e) {
		}
	}

	public void setSecondMatchOddStatus() {
		try {
			test.info("Second event FHorseOdds status : " + secondFRowSuspended.getText());
			test.info("Second event SHorseOdds status : " + secondSRowSuspended.getText());
		} catch (Exception e) {
		}
	}

	public void verifyBalanceBeforeBets() throws IOException {
		props.put("BeforeAvailableLimit", vAvailable.getText());
		props.put("BeforeCreditLimit", vCredit.getText());
		props.put("BeforeNetExposure", vnetExposure.getText());
//		FileOutputStream outputStrem = new FileOutputStream((System.getProperty("user.dir") + "//data.properties"));
		FileOutputStream outputStrem = new FileOutputStream((System.getProperty("user.dir") + "//configuration//balanceinfo.properties"));
		props.store(outputStrem, "This is a beforeBalance properties file");
	}

	public void verifyBalanceAfterBets() throws IOException {
		props.put("AfterAvailableLimit", vAvailable.getText());
		props.put("AfterCreditLimit", vCredit.getText());
		props.put("AfterNetExposure", vnetExposure.getText());
//		FileOutputStream outputStrem = new FileOutputStream((System.getProperty("user.dir") + "//data.properties"));
		FileOutputStream outputStrem = new FileOutputStream((System.getProperty("user.dir") + "//configuration//balanceinfo.properties"));
		props.store(outputStrem, "This is a afterbalance properties file");
	}

	public void setPlayexchBalanceInformation() throws IOException {
		test.info("Before Available Limit  " + Utilities.getBalanceData("BeforeAvailableLimit") + "  |  "
				+ "After Available Limit  " + Utilities.getBalanceData("AfterAvailableLimit"));

		test.info("Before Credit Limit  " + Utilities.getBalanceData("BeforeCreditLimit") + "  |  "
				+ "After Credit Limit  " + Utilities.getBalanceData("AfterCreditLimit"));
	}
	
	public void getMinMaxAmount() {
		try {
		test.info("Stake Limit :  "+ maxText.getText());}
		catch(Exception e) { }
	}
	
    public void clearStakesField() {
		action.clearTextField(driver, stakevalue);
	}
	
	public String getRandomStake(int number) {
		String randomNo = random.generateRandomNumber(number);
		test.info("Random stake : " + randomNo);
		return randomNo;
	}
		
	public void getInsufficientLimitPopup1() {
			 String actualText = insufficientLimitPopup.getText();
		     String expectedText = "Insufficient credit limit - 1"; //Maximum Profit Exceeded Over 100
		        if (actualText.equalsIgnoreCase(expectedText)) {
		           test.info("Text is as expected: " + actualText);
		        } else {
		        	 test.info("Text is not as expected. Actual: " + actualText + ", Expected: " + expectedText);
		        }
	}
	
	public void getInsufficientLimitPopup() {
	    String actualText = insufficientLimitPopup.getText();
	    String expectedText1 = "Insufficient credit limit - 1";
	    String expectedText2 = "Maximum Profit Exceeded Over 100";

	    if (actualText.equalsIgnoreCase(expectedText1) || actualText.equalsIgnoreCase(expectedText2)) {
         test.info("Text is as expected: " + actualText);
	   
	    } else {
	      //  test.info("Text is not as expected. Actual: " + actualText + ", Expected: " + expectedText1 + " or " + expectedText2);
	    }
	}
	
	




	
	public void setPlaceBetButton() {
		  test.info("PlaceBet button isEnabled  : " +placeBtn.isEnabled());
		    boolean isEnabled = placeBtn.isEnabled();
		    if (isEnabled) {
		        test.fail("PlaceBet button is visible on the page");
		    } else {
		        test.pass("PlaceBet button is not visible on the page");
		    }
	}
	
	

	@SuppressWarnings("unchecked")
	public void verifyJSONBalanceBeforeBets() throws IOException {
		JSONObject balanceDetails = new JSONObject();
		balanceDetails.put("BeforeAvailableLimit", vAvailable.getText());
		balanceDetails.put("BeforeCreditLimit", vCredit.getText());
		balanceDetails.put("BeforeNetExposure", vnetExposure.getText());

		// Append JSON data to file
		try (FileWriter file = new FileWriter("Json//beforeBalanceInfo.json", true)) {
			file.write(balanceDetails.toJSONString() + "\n"); // added// + "\n"
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void verifyJSONBalanceAfterBets() throws IOException {
		JSONObject balanceDetails = new JSONObject();
		balanceDetails.put("AfterAvailableLimit", vAvailable.getText());
		balanceDetails.put("AfterCreditLimit", vCredit.getText());
		balanceDetails.put("AfterNetExposure", vnetExposure.getText());
		try (FileWriter file = new FileWriter("Json//afterbalanceInfo.json", true)) {
			file.write(balanceDetails.toJSONString()); // added// + "\n"
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPlayexchJsonBalanceInformation() throws IOException, ParseException {
		test.info("Before Available Limit  " + JsonUtility.getJsonBeforeBalanceData("BeforeAvailableLimit") + "  |  "
				+ "After Available Limit  " + JsonUtility.getJsonAfterBalanceData("AfterAvailableLimit"));

		test.info("Before Credit Limit  " + JsonUtility.getJsonBeforeBalanceData("BeforeCreditLimit") + "  |  "
				+ "After Credit Limit  " + JsonUtility.getJsonAfterBalanceData("AfterCreditLimit"));
	}

}
