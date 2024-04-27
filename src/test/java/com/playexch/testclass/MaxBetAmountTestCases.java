package com.playexch.testclass;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class MaxBetAmountTestCases extends BaseTest {
	
	
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
	
	public void verifyMatchesAvailable() throws InterruptedException {
		cricketPage.setPlayexchMatch(driver);
		cricketPage.getPlayexchCurrentlyMatches(); 
		String actual = cricketPage.getPlayexchBackBtn();
		Assert.assertEquals(actual, "Back");
	}
	
	public void verifyPopupForMaxStake() throws InterruptedException {
    	openBetsPage.setRandomClick();
	//	cricketPage.getPlayexchStakeValue(profilePage.getRandomStake(10));  //5 DIGIT STAKE 
    	cricketPage.getPlayexchStakeValue("999999");  
		cricketPage.clickPlayexchPlaceBtn();
		try { profilePage.getMinMaxAmount(); }catch(Exception e) { }
		profilePage.getInsufficientLimitPopup();
	   	
	}
    
	public void verifyMaxStakeValue() throws InterruptedException {
		openBetsPage.setRandomClick();
		profilePage.clearStakesField();
		cricketPage.getPlayexchStakeValue(profilePage.getRandomStake(16));  //16 DIGIT STAKE
		profilePage.getMinMaxAmount();
	}
	
	public void verifyPlaceBtnVisibility() {
		profilePage.setPlaceBetButton();
	}

	@Test(priority = 3)
	public void logOutToWeb() throws InterruptedException {
		loginPage.clickPlayexchLogOut(driver);
	}
	
	 @DataProvider(name = "sports")
	    public Object[][] getSports() {
	        return new Object[][]{
	                {"cricket"},
	                {"soccer"},
	                {"tennis"}
	        };
	    }

	    @Test(dataProvider = "sports")
	    public void testSportsBookMaxBet(String sport) throws InterruptedException {
	        switch (sport) {
	            case "cricket":
				Thread.sleep(3000);
	                cricketPage.clickPlayexchCricketbtn();
	                test.info("Clicked on Cricket");
	                break;
	            case "soccer":
	                soccerPage.clickPlayexchOnSoccer();
	                test.info("Clicked on Soccer");
	                break;
	            case "tennis":
	                tennisPage.clickPlayexchTennisbtn();
	                test.info("Clicked on Tennis");
	                break;
	        }

	        verifyMatchesAvailable();
	        verifyPopupForMaxStake();
	        verifyMaxStakeValue();
	        verifyPlaceBtnVisibility();
	    }

	//    @Test(priority = 2, dependsOnMethods = { "verifyMatchesAvailable", "verifyPopupForMaxStake" })

	
	

}
