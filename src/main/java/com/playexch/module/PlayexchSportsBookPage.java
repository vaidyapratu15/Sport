package com.playexch.module;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.playexch.action.ActionClass;
import com.playexch.library.BaseClass;

import io.netty.handler.timeout.TimeoutException;

public class PlayexchSportsBookPage extends BaseClass {

	ActionClass action = new ActionClass();

	@FindBy(xpath = "//div[text()='CashOut']")
	private WebElement cashoutBtn;
	
	@FindBy(xpath = "//div[@role='status']")//div[contains(text(),' Rates Not valid!')]
	private WebElement cashoutPopup; //div[contains(text(),' Net Exposure Not Found!')]
	
	@FindBy(xpath="(//div[@class='pa-0 odds-category'])[1]//button[contains(@class,'btn-odds')]")
	private List<WebElement> oddList;  //16 buttons for single event
	
	@FindBy (xpath="(((//div[@class='pa-0 odds-category'])[1]//span[@class='v-btn__content'])[1]//span)[4]")
	private WebElement firstCashoutOdds;
	
	@FindBy (xpath="((((//div[@class='pa-0 odds-category'])[1]//span[@class='v-btn__content']))[8]//span)[4]")
	private WebElement secondCashoutOdds;

	@FindBy (xpath="//div//span[@class='v-btn__content']//span[text()='Score']")
	private WebElement socreBtn;
	
	
	public PlayexchSportsBookPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnCashoutBtn() {
		cashoutBtn.click();
		test.info("Clicked on Cashout Button");
	}
	public String getPopupText() {
		action.explicitVisiblility(driver, cashoutPopup, 10);
		String text = cashoutPopup.getText();
		return text;
	}
	
	public void checkRatesValid() throws InterruptedException {
	  
		cashoutBtn.click();

	    try {
	        String expected = "Rates Not valid!";
	        String actual = cashoutPopup.getText();
            test.info("Popup : " +actual);
	        if (actual.equalsIgnoreCase(expected)) {
	            test.fail("Unable to use cashout feature");
	            throw new RuntimeException("Rates Not valid");
	        } else {
	            test.pass("No popup as -Rates Not Valid");
	        }
	    } catch (NoSuchElementException e) {
	        // Handle the case when the expected element is not found
	        test.pass("No popup found. Proceeding with the test case.");
	    }
	}

	
	public void verifyCashOutBtn() {
		test.info("CashOut is present : " + cashoutBtn.isEnabled());    
		boolean isEnabled = cashoutBtn.isEnabled();
	    if (isEnabled) {
	    	test.pass("Able to use cashout feature");
	    }
	    	else {
	    		test.fail("No cashout button");	
	    }			    
	}
	
	public void clickOnRandomOdds() {  //match odds after click on event [not on main page]
		 Random gen = new Random();
	        boolean buttonClicked = false;
	        int maxButtonsToCheck = 4; //will check 5 buttons

	        for (int i = 0; i < maxButtonsToCheck; i++) {
	            if (oddList.isEmpty()) {
	                break;  //after 4 buttons, it will stop
	            }

	            WebElement ele = oddList.get(gen.nextInt(oddList.size()));
	            if (isElementClickable(ele)) {
	                try {
	                    ele.click();
	                    String elementText = ele.getText();
	                    String extractedValue = extractFirstPart(elementText);
	                    test.info("Clicked on element: " + extractedValue);
	                    buttonClicked = true;
	                    break; // Exit the loop if a clickable button is found
	                } catch (WebDriverException e) {
	                    String elementText = ele.getText();
	                    String extractedValue = extractFirstPart(elementText);
	                    test.info("Unable to click on element: " + extractedValue);
	                    oddList.remove(ele);
	                }
	            } else {
	                String elementText = ele.getText();
	                String extractedValue = extractFirstPart(elementText);
	                test.info("Button not clickable: " + extractedValue);
	                oddList.remove(ele);
	            }
	        }

	        if (!buttonClicked) {
	            test.pass("No clickable buttons were found, and the markets odds are not visible");
	        }
         
	}
	
	 private boolean isElementClickable(WebElement element) {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); 
	            wait.until(ExpectedConditions.visibilityOf(element));
	            return element.isEnabled();
	        } catch (TimeoutException e) {
	            return false;
	        }
	    }
	
	private String extractFirstPart(String text) {
	    String[] parts = text.split("\\s");
	    String value = parts[0];
	    value = value.replaceAll("[^\\d.]", ""); // print before space
	    return value;
	}
	
	public void verifyCashoutOdds() {

	  
		String firstHodd = firstCashoutOdds.getText();
		String secondHodd = secondCashoutOdds.getText();
	
		double firstValue = Double.parseDouble(firstHodd);
		double secondValue = Double.parseDouble(secondHodd);
		
		test.info("First Horse"+ " " +firstValue + " | " + "Second Horse"+ " " +secondValue);

		double difference = Math.abs(firstValue - secondValue);

		if (difference <= 1.0) {
		    test.pass("Values match!");
		} else {
		    test.fail("Values do not match!");
		}
	
	}

	public void clickOnScore(){
		try {
			Thread.sleep(3000);
			socreBtn.click();
			test.info("Clicked on socre button");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
   
	/*
	 * int maxAttempts = 4;
         int attempts = 0;
         int randomIndex;
         List<WebElement> clickableButtons = new ArrayList<>();
         List<WebElement> nonClickableButtons = new ArrayList<>();

         while (attempts < maxAttempts) {
             randomIndex = new Random().nextInt(matchOdd.size());
             WebElement button = matchOdd.get(randomIndex);
             if (button.isEnabled()) {
                 clickableButtons.add(button);
                 button.click();  // Click the button
                 break;  
             } else {
                 nonClickableButtons.add(button);
             }
             attempts++;
         }

         if (clickableButtons.isEmpty()) {
             test.info("No clickable buttons found within the maximum attempts.");
      
         } else {
          
             for (WebElement button : clickableButtons) {
                 String buttonText = extractFirstPart(button.getText());
                 test.info("Clicked on odds  : " + buttonText);
             }
            
             for (WebElement button : nonClickableButtons) {
                 String buttonText = extractFirstPart(button.getText());
                 test.info("Unable to click on odds : " + buttonText);
             }
         }
	 */
}
