package com.playexch.testclass;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class UofTestCases extends BaseTest {

	@Test(priority = 0)
	public void loginToWeb() throws EncryptedDocumentException, IOException, ParseException {
		driver.get(Utilities.getJsonData("url"));
		updateNewVersion();
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl());
		test.info("Username - " + loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password"));
		loginPage.clickPlayexchLoginPageloginBtn();
	}

	@Test(priority = 1)
	public void CricketEventAvailable() throws InterruptedException {
		uofPage.clickPlayexchCricketbtn();
		Thread.sleep(2000);
		uofPage.getPlayexchCricketEvents();
	}
	
	public void clickPlayexchCricketMatch(int matchNumber) throws NoSuchElementException {
	    switch (matchNumber) {
	        case 2:
	            uofPage.clickPlayexchCricketSecondMatch();
	            break;
	        case 3:
	            uofPage.clickPlayexchCricketThirdMatch();
	            break;
	        case 4:
	            uofPage.clickPlayexchCricketForthMatch();
	            break;
	        default:
	            throw new NoSuchElementException("Invalid match number: " + matchNumber);
	    }
	}

	@Test(priority = 2)
	public void VerifyPrimiumMarket() throws InterruptedException {
	    try {
	        profilePage.getPlayexchFirstEventName(); 
	        uofPage.clickPlayexchCricketFirstMatch();
	        uofPage.setPlayexchPremiumMarket();  
	    } catch (NoSuchElementException e) {
	        uofPage.scrollUpToCricketBtn();
	        uofPage.clickPlayexchCricketbtn();
	        
	        for (int i = 2; i <= 4; i++) {  // for 4 matches
	            try {
	                switch (i) {    //case 1 for 1st cricket market
	                    case 2:
	                        profilePage.getPlayexchSecondEventName();
	                        break;
	                    case 3:
	                        profilePage.getPlayexchThirdEventName();
	                        break;
	                    case 4:
	                        profilePage.getPlayexchFourthEventName();
	                        break;
	                }
	                clickPlayexchCricketMatch(i);
	                uofPage.setPlayexchPremiumMarket(); 
	                break; // break out of the loop if the operation succeeds
	            } catch (NoSuchElementException ex) {
	                uofPage.scrollUpToCricketBtn();
	                uofPage.clickPlayexchCricketbtn();
	                if (i == 4) {
	                	test.info("premium cricket not visible");
	                    throw ex; // re-throw the exception if all attempts fail
	                }
	            }
	        }
	    }
	}

	@Test(priority = 3, dependsOnMethods = "VerifyPrimiumMarket")
	public void uofAllMarkets() throws InterruptedException {
		 uofPage.getAllUofMarket();
	}
	
	@Test(priority = 4, dependsOnMethods = "VerifyPrimiumMarket")
	public void randomClickOnOdds() throws InterruptedException {
		uofPage.setRandomClick();
	}
	
	@Test(priority = 5, dependsOnMethods = "VerifyPrimiumMarket")
	public void placeUofBets() throws InterruptedException {
		uofPage.getPlayexchStakeValue("10");
		uofPage.clickPlayexchPlaceBtn();
	}

	@Test(priority = 6, dependsOnMethods = "VerifyPrimiumMarket")
	public void VerifyAllBetPlaceInfo() throws InterruptedException {
		uofPage.setPlayexchPageScrollUp();
		openBetsPage.clickPlayexchOpenbetsBtn();
		Thread.sleep(3000);
		openBetsPage.getPlayexchMyAllPremiumBets();
	}

	@Test(priority = 7)
	public void logOutToWeb() throws InterruptedException {
		uofPage.setPlayexchPageScrollUp();
		loginPage.clickPlayexchLogOut(driver);
	}

	public void updateNewVersion() {
		loginPage.clickPlayexchUpdate();
	}

	public void getProfileInfo() throws InterruptedException {
		profilePage.clickPlayexchProfile();
		Thread.sleep(3000);
		test.info(profilePage.getPlayexchavailableBlns());
		test.info(profilePage.getPlayexchCreditLimit());
		test.info(profilePage.getPlayexchWinnings());
		test.info(profilePage.getPlayexchNetExposure());
		profilePage.clickPlayexchCloseBtn();
	}
	
//	@Test(priority = 2)
	public void verifyPrimiumMarke() throws InterruptedException {
//		profilePage.getPlayexchFirstEventName(); uofPage.clickPlayexchCricketFirstMatch();
//		profilePage.getPlayexchSecondEventName();uofPage.clickPlayexchCricketSecondMatch();			
//		profilePage.getPlayexchThirdEventName(); uofPage.clickPlayexchCricketThirdMatch();
//		profilePage.getPlayexchForthEventName(); uofPage.clickPlayexchCricketForthMatch();
		uofPage.setPlayexchPremiumMarket();
	}
	
//	uofPage.clickPremium2Market(); uofPage.clickPremium3Market();
	
  
}
