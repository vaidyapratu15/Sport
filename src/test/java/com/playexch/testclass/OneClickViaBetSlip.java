package com.playexch.testclass;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class OneClickViaBetSlip extends BaseTest {

	@Test(priority = 0)
	public void loginToWeb() throws EncryptedDocumentException, IOException, ParseException, InterruptedException {
		driver.get(Utilities.getJsonData("url"));
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl());
		test.info("Title - " + driver.getTitle());
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
	    });
	    sportsActions.put("soccer", () -> {
	        soccerPage.clickPlayexchOnSoccer();
	        test.info("Clicked on soccer button");
	    });
	    sportsActions.put("tennis", () -> {
	        tennisPage.clickPlayexchTennisbtn();
	        test.info("Clicked on tennis button");
	    });

	    for (Map.Entry<String, Runnable> entry : sportsActions.entrySet()) {
	        try {Thread.sleep(3000);
	            entry.getValue().run();
	           verifyMatchesAvailable();
	           oneClickPage.clickOnOddsButton();
	            break;
	        } catch (Exception e) {
	          
	        }
	    }
	}

	
	public void verifyMatchesAvailable() {
		cricketPage.setPlayexchMatch(driver);
		cricketPage.getPlayexchCurrentlyMatches();
		String actual = cricketPage.getPlayexchBackBtn();
		System.out.println(actual);
		Assert.assertEquals(actual, "Back");
	}
	
	//@Test(priority = 2, dependsOnMethods = { "verifyAvailableMatches"})
	public void checkToggleONOFF(){
		oneClickPage.checkActivationPopup();
	}
	
	@Test(priority = 3, dependsOnMethods = { "verifyAvailableMatches"})
	public void clickOneEditStake() throws InterruptedException {
		oneClickPage.setEditStake();
		Thread.sleep(3000);
	//	oneClickPage.verifyToggleBtnOn();
	}
	
	@Test(priority = 4, dependsOnMethods = { "clickOneEditStake" , "verifyAvailableMatches"})
	public void clickOnToggleBtn() throws InterruptedException {
		oneClickPage.clickOneClickBettingBtn();
		Thread.sleep(3000);
	}

    @Test(priority = 5, dependsOnMethods = { "clickOneEditStake" , "verifyAvailableMatches"})
    public void setEditStake() throws InterruptedException{
        oneClickPage.clickEditBtn();
    }

	@Test(priority = 6, dependsOnMethods = { "clickOneEditStake" , "verifyAvailableMatches"})
	public void editSatkeValue() throws InterruptedException{
        oneClickPage.setStakesValue("5");
        oneClickPage.clickOnSaveBtn();
    }
	
	@Test(priority = 7 ,dependsOnMethods = { "clickOneEditStake" , "verifyAvailableMatches"})
	public void verifyActiveStake() throws InterruptedException {
		cricketPage.clickPlayexchCricketbtn();
     	oneClickPage.getActivestakePopup();
	}

	 @Test(priority = 8, dependsOnMethods = { "clickOneEditStake" , "verifyAvailableMatches"})
	public void placeBetViaOneClick() throws Exception {
	    Map<String, Runnable> sportsActions = new LinkedHashMap<>(); 

	    sportsActions.put("cricket", () -> {
	        cricketPage.clickPlayexchCricketbtn();
	        test.info("Clicked on cricket button");
	    });
	    sportsActions.put("soccer", () -> {
	        soccerPage.clickPlayexchOnSoccer();
	        test.info("Clicked on soccer button");
	    });
	    sportsActions.put("tennis", () -> {
	        tennisPage.clickPlayexchTennisbtn();
	        test.info("Clicked on tennis button");
	    });

	    for (Map.Entry<String, Runnable> entry : sportsActions.entrySet()) {
	        try {
	            entry.getValue().run();
	            verifyMatchesAvailable();
	          //  openBetsPage.setRandomClick();
	            oneClickPage.clickOnOddsButton();
	            break;
	        } catch (Exception e) {
	          
	        }
	    }

	    try {
	        oneClickPage.getPlayexchBetPlacedMsg();
	    } catch (Exception e) {
	    }
	}

	@Test(priority =9,dependsOnMethods = { "clickOneEditStake" , "verifyAvailableMatches"})
	public void closeOneClickBetSetting() throws InterruptedException {
		Thread.sleep(3000);
		oneClickPage.offOneClickBet();
		
	}
	
    @Test(priority = 10,dependsOnMethods = { "clickOneEditStake" , "verifyAvailableMatches"})
	public void checkToggleButton() {
		try {
			oneClickPage.checkToggleBtnOff();
		}catch(Exception e) { }
	}
	
	@Test(priority =15)
	public void logOutToWeb() throws InterruptedException {
		Thread.sleep(3000);
		loginPage.clickPlayexchLogOut(driver);
	}

	




 
	
}
