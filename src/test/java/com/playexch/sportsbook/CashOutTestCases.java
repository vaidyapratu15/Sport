package com.playexch.sportsbook;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;


public class CashOutTestCases extends BaseTest {
	
	@Test(priority = 0)
	public void loginToWeb() throws EncryptedDocumentException, IOException, ParseException {
		driver.get(Utilities.getJsonData("url"));
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl()); 
		test.info("Username - " + loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password"));		
		loginPage.clickPlayexchLoginPageloginBtn();
	}
	
	@Test(priority = 1)
	public void verifyAvailableMatches() {
	 Map<String, Runnable> sportsActions = new LinkedHashMap<>(); 

	    sportsActions.put("cricket", () -> {
	        cricketPage.clickPlayexchCricketbtn();
	        test.info("Clicked on cricket button");
	        cricketPage.getPlayexchCricketEvents();
	    });
	    sportsActions.put("tennis", () -> {
	        tennisPage.clickPlayexchTennisbtn();
	        test.info("Clicked on tennis button");
	        tennisPage.getPlayexchTennisEvents();
	    });
	    for (Map.Entry<String, Runnable> entry : sportsActions.entrySet()) {
	        try {
	            entry.getValue().run();
	            verifyMatchesAvailable();
	            oneClickPage.clickOnOddsButton();    //if buttons not clicable then go for next
	            verifyMatchOdds();
	            break;
	        } catch (Exception e) {
	          
	        }
	    }
	}
	
	@Test(priority = 2)
	public void verifyRatesValidForCashout() throws InterruptedException {
		sportbookPg.checkRatesValid();
	}

	@Test(priority = 3, dependsOnMethods = "verifyRatesValidForCashout")
	public void checkPopup() throws InterruptedException {
	    try {
	        sportbookPg.clickOnCashoutBtn();
	    
	        String expected = "Net Exposure Not Found!";
	        String expected1 = "Stake value should be greater than 1";
	        String actual = sportbookPg.getPopupText();
	        test.info("Popup: " + actual);

	        if (actual.equalsIgnoreCase(expected) || actual.equalsIgnoreCase(expected1)) {
	            test.pass("Popup is as expected");
	            sportbookPg.clickOnRandomOdds();
	            cricketPage.getPlayexchStakeValue("9");
				cricketPage.clickPlayexchPlaceBtn();
				Thread.sleep(4000);
	        } else {
	            Assert.fail("Popup is not as expected");
	        }
	    } catch (TimeoutException e) {
	        test.pass("Popup was not displayed - so able to use cashout feature");
	    }
	}

	@Test(priority = 4, dependsOnMethods = {"verifyRatesValidForCashout" ,"checkPopup" })
	public void UseCashout() {
		sportbookPg.clickOnCashoutBtn();
	}
	
   @Test (priority = 5, dependsOnMethods =  {"verifyRatesValidForCashout" ,"checkPopup" })
   public void verifyCashoutOdds() {
	   sportbookPg.verifyCashoutOdds();   
   }
   
   @Test (priority = 6, dependsOnMethods =  {"verifyRatesValidForCashout" ,"checkPopup" })
   public void placeBet() {
		cricketPage.clickPlayexchPlaceBtn();
		test.info("Clicked on place button");
   }
	
   @Test(priority = 9)
	public void logOutToWeb() throws InterruptedException {
		loginPage.clickPlayexchLogOut(driver);
	} 

	public void verifyMatchesAvailable() {
		cricketPage.setPlayexchMatch(driver);
		cricketPage.getPlayexchCurrentlyMatches();
		String actual = cricketPage.getPlayexchBackBtn();
		System.out.println(actual);
		Assert.assertEquals(actual, "Back");
	}
	
	public void clickPlayexchCricketMatch(int matchNumber) throws NoSuchElementException {
	    switch (matchNumber) {
	        case 2:
	        	cricketPage.clickPlayexchCricketSecondMatch();
	            break;
	        case 3:
	        	cricketPage.clickPlayexchCricketThirdMatch();
	            break;
	        default:
	            throw new NoSuchElementException("Invalid match number: " + matchNumber);
	    }
	}

	public void verifyMatchOdds() throws InterruptedException {
	    try {
	        profilePage.getPlayexchFirstEventName(); 
	        cricketPage.clickPlayexchCricketFirstMatch();
	        cricketPage.setPlayexchMatchOdds(); 
	        sportbookPg.verifyCashOutBtn();
	    } catch (NoSuchElementException e) {
	    	cricketPage.scrollUpToCricketBtn();
	    	cricketPage.clickPlayexchCricketbtn();
	        
	        for (int i = 2; i <= 3; i++) {
	            try {
	                switch (i) {
	                    case 2:
	                        profilePage.getPlayexchSecondEventName();
	                        break;
	                    case 3:
	                        profilePage.getPlayexchThirdEventName();
	                        break;
	                }
	                clickPlayexchCricketMatch(i);
	                cricketPage.setPlayexchMatchOdds(); 
	                sportbookPg.verifyCashOutBtn();
	                break; // break out of the loop if the operation succeeds
	            } catch (NoSuchElementException ex) {
	            	cricketPage.scrollUpToCricketBtn();
	            	cricketPage.clickPlayexchCricketbtn();
	                if (i == 3) {
	                    throw ex; // re-throw the exception if all attempts fail
	                }
	            }
	        }
	    }
	}


}
